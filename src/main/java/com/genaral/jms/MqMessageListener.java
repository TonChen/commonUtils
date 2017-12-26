package com.genaral.jms;

import com.alibaba.citrus.util.ExceptionUtil;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.genaral.jms.bean.MqLogManager;
import com.genaral.spring.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MqMessageListener implements MessageListener {
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Action consume(Message msg, ConsumeContext context) {
        MqLogManager mqLogManager = (MqLogManager) SpringContextUtil.getBean("mqLogManager");
        try {
            log.info("Receive: " + msg.getMsgID());
            log.info("收到消息:" + msg);
            // 执行TopicTest1的消费逻辑
            if (msg.getTag() != null && msg.getTag().equals("TAG_ORDERS_TMS")) {
                // 执行TagA的消费
                String keys = msg.getKey();
                String body = new String(msg.getBody());
                //TODO
                return Action.CommitMessage;
            } else if (msg.getTag() != null && msg.getTag().equals("TAG_ORG_SYN")) {
            } else {
                return Action.CommitMessage;
            }
            return Action.CommitMessage;
        } catch (Exception e) {
            //消费失败
            e.printStackTrace();
            log.info("消息处理异常:" + ExceptionUtil.getStackTrace(e));
            mqLogManager.updateLog(msg.getMsgID(), null, msg.getTag(), "HHSC", "处理异常:" + e.getMessage(), 2);
            return Action.ReconsumeLater;
        }
    }

}
