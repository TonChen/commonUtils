package com.genaral.wechat.receive;

import javax.xml.bind.annotation.XmlElement;

public class BaseReceiveMessage{
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String MsgId;
	
	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	@XmlElement(name = "CreateTime")
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	@XmlElement(name = "MsgId")
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
