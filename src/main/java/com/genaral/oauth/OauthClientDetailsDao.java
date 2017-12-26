/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2015
 */

package com.genaral.oauth;

import com.genaral.base.BaseIbatis3Dao;
import com.genaral.page.Page;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@Repository
public class OauthClientDetailsDao extends BaseIbatis3Dao<OauthClientDetails, String> {

    @Override
    public String getIbatisMapperNamesapce() {
        return "OauthClientDetails";
    }

    public void saveOrUpdate(OauthClientDetails entity) {
        if (entity.getClientId() == null)
            save(entity);
        else
            update(entity);
    }

    public Page findPage(OauthClientDetailsQuery query) {
        return pageQuery("OauthClientDetailsFindPage", query);
    }


}
