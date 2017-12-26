package com.genaral.jms;

import com.aliyun.openservices.ons.api.*;
import com.genaral.jms.bean.MqLog;
import com.genaral.jms.bean.MqLogManager;
import com.genaral.sequence.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;
@Service
public class MsgProducer {
	public Logger log = LoggerFactory.getLogger(getClass());
	//公众账号ID
	@Value("${mq.pushtopic.name}")
	private String pushtopic;
	@Autowired
	private com.aliyun.openservices.ons.api.bean.ProducerBean producerBean;
	@Autowired
	private IdWorker idWorker;
	@Autowired
	private MqLogManager mqLogManager;
	
	public String getPushtopic() {
		return pushtopic;
	}
	public void setPushtopic(String pushtopic) {
		this.pushtopic = pushtopic;
	}
	
	public com.aliyun.openservices.ons.api.bean.ProducerBean getProducerBean() {
		return producerBean;
	}
	public void setProducerBean(
			com.aliyun.openservices.ons.api.bean.ProducerBean producerBean) {
		this.producerBean = producerBean;
	}
	/**
	 * @param tags
	 * @param keys
	 * @param body
	 * @return
	 */
	public SendResult sendMsg(String tags,String keys,String body){
        return sendMsg( tags, keys, body ,null);
	}
	
	
	/**
	 * @param tags
	 * @param keys
	 * @param body
	 * 			这个配置项配置了从1级开始，各级延时的时间，可以修改这个指定级别的延时时间
	 * @return
	 */
	public SendResult sendMsg(String tags,String keys,String body,Date date){
		return sendMsg( tags, keys, body ,date, true);
	}
	
	/**
	 * @param tags
	 * @param keys
	 * @param body
	 * 			这个配置项配置了从1级开始，各级延时的时间，可以修改这个指定级别的延时时间
	 * @return
	 */
	public SendResult sendMsg(String tags,String keys,String body,Date date,boolean syn){
		try {
//			log.info("Feb eight 2017 tags="+tags);
//			log.info("Feb eight 2017 keys="+keys);
//			log.info("Feb eight 2017 body="+body);
//			log.info("Feb eight 2017 date="+date);
//			log.info("Feb eight 2017 syn="+syn);
			Message msg = new Message(pushtopic,// topic
					tags,// tag
					keys,// key
					body.getBytes());// body
			if(date!=null){
				long timeStamp =date.getTime();
				msg.setStartDeliverTime(timeStamp);
			}
//			log.info("Feb eight 2017 msg="+msg);
			SendResult sendResult = producerBean.send(msg);
			log.info("【MQ_MESSAGE1】:"+tags+":"+sendResult.getMessageId()+":"+sendResult.toString());
			//新增消息
			if(syn){
				MqLog mqLog = new MqLog();
				mqLog.setId(idWorker.nextId());
				mqLog.setMqId(sendResult.getMessageId());
				mqLog.setMqKey(keys);
				mqLog.setMqBody(body);
				mqLog.setMqTopic(pushtopic);
				mqLog.setMqTag(tags);
				mqLog.setSendNum(0);
				mqLog.setStatus(1);
				mqLog.setCreateTime(new Date());
				mqLogManager.save(mqLog);
			}
			return sendResult;
		} catch (Exception e) {
			log.info("==============【消息MQ推送失败】===================e="+e);
			e.printStackTrace();
            return null;
		}
	}
	
	/**
	 * @param tags
	 * @param keys
	 * @param body
	 * @param delayTime 毫秒
	 * @return
	 */
	public void sendMsgDelay(final String tags,final String keys,final String body,final Long delayTime){
		try {
			Message msg = new Message(pushtopic,// topic
					tags,// tag
					keys,// key
					body.getBytes());// body
			if(delayTime!=null){
			    msg.setStartDeliverTime(System.currentTimeMillis() + delayTime);
			}
	        SendResult sendResult = producerBean.send(msg);
	        log.info(sendResult.getMessageId()+":"+sendResult.toString());
	        //新增消息
	        MqLog mqLog = new MqLog();
	        mqLog.setId(idWorker.nextId());
	        mqLog.setMqId(sendResult.getMessageId());
	        mqLog.setMqKey(keys);
	        mqLog.setMqTag(tags);
	        mqLog.setMqBody(body);
	        mqLog.setCreateTime(new Date());
	        mqLog.setStatus(1);
	        mqLog.setSendNum(0);
	        mqLogManager.save(mqLog);
		} catch (Exception e) {
			log.info("==============【消息MQ推送失败】===================");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param tags
	 * @param keys
	 * @param body
	 * @param params 长度不能超过50
	 * @return
	 */
	public SendResult sendMsgParams(String tags,String keys,String body,String params){
		try {
			Message msg = new Message(pushtopic,// topic
					tags,// tag
					keys,// key
					body.getBytes());// body
			Properties properties = new Properties();
			properties.setProperty("params", params);
			msg.setUserProperties(properties);
			SendResult sendResult = producerBean.send(msg);
			log.info("【MQ_MESSAGE1】:"+tags+":"+sendResult.getMessageId()+":"+sendResult.toString());
			//新增消息
			MqLog mqLog = new MqLog();
			mqLog.setId(idWorker.nextId());
			mqLog.setMqId(sendResult.getMessageId());
			mqLog.setMqKey(keys);
			mqLog.setMqTag(tags);
			mqLog.setMqBody(body);
			mqLog.setReqParam(params);
			mqLog.setCreateTime(new Date());
			mqLog.setStatus(1);
			mqLog.setSendNum(0);
			mqLogManager.save(mqLog);
			return sendResult;
		} catch (Exception e) {
			log.info("==============【消息MQ推送失败】===================");
			e.printStackTrace();
            return null;
		}
	}
	
	 public static void main(String[] args) {
	        Properties properties = new Properties();
	        properties.put(PropertyKeyConst.ProducerId, "PID_HHSC_DEV");// ProducerId需要设置您自己的
	        properties.put(PropertyKeyConst.AccessKey,"G8vIOjCPfPDflv5V");// AccessKey 需要设置您自己的
	        properties.put(PropertyKeyConst.SecretKey, "uR48kNr0tKr9LEKo8oCLgp4g7aGvgM");// SecretKey 需要设置您自己的
	        Producer producer = ONSFactory.createProducer(properties);
	        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
	        producer.start();

	        //循环发送消息
	        while(true){
	            Message msg = new Message( //
	                // Message Topic
	                "Hhsc_Trade_Topic_Dev",
	                // Message Tag,
	                // 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过滤
	                "TAG_TEST",
	                // Message Body
	                // 任何二进制形式的数据， ONS不做任何干预，
	                // 需要Producer与Consumer协商好一致的序列化和反序列化方式
	                "Hello ONS".getBytes());
	            // 设置代表消息的业务关键属性，请尽可能全局唯一。
	            // 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
	            // 注意：不设置也不会影响消息正常收发
	            msg.setKey("ORDERID_100");
	            // 发送消息，只要不抛异常就是成功
	            SendResult sendResult = producer.send(msg);
	            System.out.println(sendResult);
	        }

	        // 在应用退出前，销毁Producer对象
	        // 注意：如果不销毁也没有问题
	    }
}
