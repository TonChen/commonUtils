package com.genaral.base;

import com.genaral.object.ObjectUtil;
import com.genaral.page.PageRequest;
import com.genaral.page.PageRequestFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;


/**
 * @author badqiu
 */
@Transactional
public abstract class BaseManager<E, PK extends Serializable> {

    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    protected abstract EntityDao getEntityDao();

    public E getById(PK id) throws DataAccessException {
        return (E) getEntityDao().getById(id);
    }

    protected boolean isEmpty(Object o) {
        return ObjectUtil.isEmpty(o);
    }

    @SuppressWarnings("unchecked")
    public List<E> findAll() throws DataAccessException {
        return getEntityDao().findAll();
    }

    public E findOneBy(PageRequest query) throws DataAccessException {
        Object object = getEntityDao().findOneBy(query);
        if (object == null) return null;
        else return (E) object;
    }


    @SuppressWarnings("unchecked")
    public List<E> findListBy(PageRequest query) throws DataAccessException {
        return getEntityDao().findListBy(query);
    }

    /**
     * 根据id检查是否插入或是更新数据
     */
    public void saveOrUpdate(E entity) throws DataAccessException {
        getEntityDao().saveOrUpdate(entity);
    }

    /**
     * 插入数据
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(E entity) throws DataAccessException {
        getEntityDao().save(entity);
    }

    public void newSave(E entity) throws DataAccessException {
        getEntityDao().save(entity);
    }

    public void removeById(PK id) throws DataAccessException {
        getEntityDao().deleteById(id);
    }

    public void update(E entity) throws DataAccessException {
        getEntityDao().update(entity);
    }

    public void newUpdate(E entity) throws DataAccessException {
        getEntityDao().update(entity);
    }

    public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException {
        return getEntityDao().isUnique(entity, uniquePropertyNames);
    }

    public boolean isNullOrEmptyString(Object o) {
        return ObjectUtil.isNullOrEmptyString(o);
    }


    public <T extends PageRequest> T newQuery(Class<T> queryClazz,
                                              String defaultSortColumns) {
        PageRequest query = PageRequestFactory.bindPageRequest(
                org.springframework.beans.BeanUtils
                        .instantiateClass(queryClazz), getRequest(),
                defaultSortColumns);
        return (T) query;
    }
}
