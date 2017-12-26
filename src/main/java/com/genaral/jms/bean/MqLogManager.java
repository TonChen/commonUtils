/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2016
 */

package com.genaral.jms.bean;

import com.genaral.base.BaseManager;
import com.genaral.base.EntityDao;
import com.genaral.page.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class MqLogManager extends BaseManager<MqLog, Long> {

    private MqLogDao mqLogDao;

    /**
     * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写
     */
    public void setMqLogDao(MqLogDao dao) {
        this.mqLogDao = dao;
    }

    public EntityDao getEntityDao() {
        return this.mqLogDao;
    }

    @Transactional(readOnly = true)
    public Page findPage(MqLogQuery query) {
        return mqLogDao.findPage(query);
    }

    /**
     * @param msgId
     * @param req
     * @param busCode
     * @param busChannel
     * @param res
     * @param status
     */
    public int updateLog(String msgId, String req, String busCode, String busChannel, String res, int status) {
        MqLogQuery mqLogQuery = new MqLogQuery();
        mqLogQuery.setMqId(msgId);
        MqLog mqLog = findOneBy(mqLogQuery);
        if (mqLog == null) {
            log.info("未找到消息:" + msgId);
            return -1;
        }
        if (mqLog.getStatus() != 0) {
            //已完成的不处理
            mqLog.setBusCode(busCode);
            mqLog.setBusChannel(busChannel);
            mqLog.setReqParam(req);
            if (res != null) {
                if (res.length() > 499) {
                    res = res.substring(0, 499);
                }
            }
            mqLog.setResMsg(res);
            mqLog.setStatus(status);
            mqLog.setOpTime(new Date());
            mqLog.setSendNum(mqLog.getSendNum() == null ? 1 : (mqLog.getSendNum() + 1));
            update(mqLog);
            return mqLog.getSendNum();
        }
        return -1;
    }
}
