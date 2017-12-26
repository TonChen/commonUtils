package com.genaral.wechat.reply;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
public class BaseReplyMessage {
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	
	@XmlElement(name = "ToUserName")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	@XmlElement(name = "FromUserName")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	@XmlElement(name = "CreateTime")
	public long getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	
	@XmlElement(name = "MsgType")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
