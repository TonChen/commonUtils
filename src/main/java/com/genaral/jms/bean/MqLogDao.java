/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2016
 */

package com.genaral.jms.bean;

import com.genaral.base.BaseIbatis3Dao;
import com.genaral.page.Page;
import org.springframework.stereotype.Repository;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@Repository
public class MqLogDao extends BaseIbatis3Dao<MqLog, Long> {

    @Override
    public String getIbatisMapperNamesapce() {
        return "MqLog";
    }

    public void saveOrUpdate(MqLog entity) {
        if (entity.getId() == null)
            save(entity);
        else
            update(entity);
    }

    public Page findPage(MqLogQuery query) {
        return pageQuery("MqLogFindPage", query);
    }


}
