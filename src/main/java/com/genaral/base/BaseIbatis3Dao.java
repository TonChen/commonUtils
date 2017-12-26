package com.genaral.base;

import com.genaral.object.ObjectUtil;
import com.genaral.page.Page;
import com.genaral.page.PageRequest;
import com.genaral.properties.PropertyUtils;
import com.genaral.sequence.IdWorker;
import com.genaral.spring.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author badqiu
 * @version 1.0
 */
public abstract class BaseIbatis3Dao<E, PK extends Serializable> extends DaoSupport implements EntityDao<E, PK> {
    protected final Log log = LogFactory.getLog(getClass());
    private SqlSession sqlSession;
    private boolean externalSqlSession;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalSqlSession) {
            this.sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        }
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSession = sqlSessionTemplate;
        this.externalSqlSession = true;
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    @Override
    protected void checkDaoConfig() {
        Assert.notNull(this.sqlSession, "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
    }

    public List<E> selectList(String statementName, PageRequest query) {
        return getSqlSession().selectList(statementName, query);
    }

    public E selectOne(String statementName, PageRequest query) {
        return getSqlSession().selectOne(statementName, query);
    }

    public E findOneBy(PageRequest query) {
        return getSqlSession().selectOne(getOptionStatement(), query);
    }

    public List<E> findListBy(PageRequest query) {
        return getSqlSession().selectList(getOptionStatement(), query);
    }

    @Override
    public E getById(PK primaryKey) {
        return getSqlSession().selectOne(getFindByPrimaryKeyStatement(), primaryKey);
    }

    public Number getByNumber(String statementName, PageRequest query) {
        Number totalCount = (Number) getSqlSession().selectOne(statementName, query);
        return totalCount;
    }

    @Override
    public void deleteById(PK id) {
        int affectCount = getSqlSession().delete(getDeleteStatement(), id);
    }

    @Override
    public void save(E entity) {
        Class clazz = entity.getClass();
        try {
            Method getId = clazz.getDeclaredMethod("getId");
            Object id = getId.invoke(entity);
            if (id == null) {
                Method setId = clazz.getDeclaredMethod("setId", String.class);
                //setId.invoke(entity, UUIDLong.longUUID());
                IdWorker idWorker = (IdWorker) SpringContextUtil.getBean("idWorker");
                setId.invoke(entity, idWorker.nextIdStr());
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        prepareObjectForSaveOrUpdate(entity);
        int affectCount = getSqlSession().insert(getInsertStatement(), entity);
    }

    @Override
    public void update(E entity) {
        prepareObjectForSaveOrUpdate(entity);
        int affectCount = getSqlSession().update(getUpdateStatement(), entity);
    }

    /**
     * 用于子类覆盖,在insert,update之前调用
     *
     * @param o
     */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }

    public String getIbatisMapperNamesapce() {
        throw new RuntimeException("not yet implement");
    }

    public String getFindByPrimaryKeyStatement() {
        return getIbatisMapperNamesapce() + "GetById";
    }

    public String getInsertStatement() {
        return getIbatisMapperNamesapce() + "Insert";
    }

    public String getUpdateStatement() {
        return getIbatisMapperNamesapce() + "Update";
    }

    public String getDeleteStatement() {
        return getIbatisMapperNamesapce() + "Delete";
    }

    public String getOptionStatement() {
        return getIbatisMapperNamesapce() + "FindPage";
    }

    public String getCountStatementForPaging(String statementName) {
        return statementName + "Count";
    }

    protected Page pageQuery(String statementName, PageRequest pageRequest) {
        return pageQuery(getSqlSession(), statementName, getCountStatementForPaging(statementName), pageRequest);
    }

    public static Page pageQuery(SqlSession sqlSessionTemplate, String statementName, String countStatementName, PageRequest pageRequest) {

        Number totalCount = (Number) sqlSessionTemplate.selectOne(countStatementName, pageRequest);
        //List list2 = sqlSessionTemplate.selectList(statementName,pageRequest);

        if (totalCount == null || totalCount.longValue() <= 0) {
            return new Page(pageRequest, 0);
        }

        Page page = new Page(pageRequest, totalCount.intValue());

        //其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
        Map filters = new HashMap();
        filters.put("offset", page.getFirstResult());
        filters.put("pageSize", page.getPageSize());
        filters.put("lastRows", page.getFirstResult() + page.getPageSize());
        filters.put("sortColumns", pageRequest.getSortColumns());

        Map parameterObject = PropertyUtils.describe(pageRequest);
        filters.putAll(parameterObject);
        RowBounds rb = new RowBounds(page.getFirstResult(), page.getPageSize());
        List list = sqlSessionTemplate.selectList(statementName, filters, rb);
        page.setResult(list);
        return page;
    }

    @Override
    public List findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isUnique(E entity, String uniquePropertyNames) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        //ignore
    }


    //	public static class SqlSessionTemplate {
//		SqlSessionFactory sqlSessionFactory;
//		protected final Log logger = LogFactory.getLog(getClass());
//		public SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//			this.sqlSessionFactory = sqlSessionFactory;
//		}
//
    public Object execute(SqlSessionCallback action) {
        SqlSession session = null;
        try {
            session = getSqlSession();
//			session.getConnection().setAutoCommit(true);
//			int ts = session.getConnection().getTransactionIsolation();
//			if(ts==Connection.TRANSACTION_READ_UNCOMMITTED){
//				logger.info("TRANSACTION_READ_UNCOMMITTED");
//			}else if(ts==Connection.TRANSACTION_READ_COMMITTED){
//				logger.info("TRANSACTION_READ_COMMITTED");
//			}else if(ts==Connection.TRANSACTION_REPEATABLE_READ){
//				logger.info("TRANSACTION_REPEATABLE_READ");
//			}else if(ts==Connection.TRANSACTION_SERIALIZABLE){
//				logger.info("TRANSACTION_SERIALIZABLE");
//			}else if(ts==Connection.TRANSACTION_NONE){
//				logger.info("TRANSACTION_NONE");
//			}
            Object result = action.doInSession(session);
//			logger.info("result:"+result);
//			session.commit();
            return result;
        } finally {
//			if(session != null) session.close();
//			logger.info("session close");
        }
    }

    //		public Object selectOne(final String statement,final Object parameter) {
//			return execute(new SqlSessionCallback() {
//				public Object doInSession(SqlSession session) {
//					return session.selectOne(statement, parameter);
//				}
//			});
//		}
//		
//		public List selectList(final String statement,final Object parameter,final int offset,final int limit) {
//			return (List)execute(new SqlSessionCallback() {
//				public Object doInSession(SqlSession session) {
//					return session.selectList(statement, parameter, new RowBounds(offset,limit));
//				}
//			});
//		}
//
//		public List selectList(final String statement,final Object parameter) {
//			return (List)execute(new SqlSessionCallback() {
//				public Object doInSession(SqlSession session) {
//					return session.selectList(statement, parameter);
//				}
//			});
//		}
//		
//		public int delete(final String statement,final Object parameter) {
//			return (Integer)execute(new SqlSessionCallback() {
//				public Object doInSession(SqlSession session) {
//					return session.delete(statement, parameter);
//				}
//			});
//		}
//		
    public int update(final String statement, final Object parameter) {
        return (Integer) execute(new SqlSessionCallback() {
            @Override
            public Object doInSession(SqlSession session) {
                return session.update(statement, parameter);
            }
        });
    }
//		
//		public int insert(final String statement,final Object parameter) {
//			return (Integer)execute(new SqlSessionCallback() {
//				public Object doInSession(SqlSession session) {
//					return session.insert(statement, parameter);
//				}
//			});
//		}
//	} 

    public static interface SqlSessionCallback {

        public Object doInSession(SqlSession session);

    }

    protected boolean isEmpty(Object o) {
        return ObjectUtil.isEmpty(o);
    }

}
