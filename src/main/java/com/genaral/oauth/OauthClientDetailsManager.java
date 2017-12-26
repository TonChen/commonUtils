/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2015
 */

package com.genaral.oauth;

import com.genaral.base.BaseManager;
import com.genaral.base.EntityDao;
import com.genaral.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class OauthClientDetailsManager extends BaseManager<OauthClientDetails, String> {

    private OauthClientDetailsDao oauthClientDetailsDao;

    /**
     * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
     */
    public void setOauthClientDetailsDao(OauthClientDetailsDao dao) {
        this.oauthClientDetailsDao = dao;
    }

    public EntityDao getEntityDao() {
        return this.oauthClientDetailsDao;
    }

    @Transactional(readOnly = true)
    public Page findPage(OauthClientDetailsQuery query) {
        return oauthClientDetailsDao.findPage(query);
    }

}
