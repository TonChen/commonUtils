package com.genaral.wechat.receive;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;


@XmlRootElement(name = "xml")
public class PayFeedBackMessage{
	private String AppId;
	private String TimeStamp;
	private String OpenId;
	private String AppSignature;
	private String MsgType;
	private String FeedBackId;
	private String TransId;
	private String Reason;
	private String Solution;
	private String ExtInfo;
	private List<Item> PicInfo ;
	private String SignMethod; 
	
	@XmlElement(name = "OpenId")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	
	@XmlElement(name = "AppId")
	public String getAppId() {
		return AppId;
	}
	public void setAppId(String appId) {
		AppId = appId;
	}
	
	@XmlElement(name = "TimeStamp")
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	
	@XmlElement(name = "AppSignature")
	public String getAppSignature() {
		return AppSignature;
	}
	public void setAppSignature(String appSignature) {
		AppSignature = appSignature;
	}
	
	@XmlElement(name = "MsgType")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	@XmlElement(name = "FeedBackId")
	public String getFeedBackId() {
		return FeedBackId;
	}
	public void setFeedBackId(String feedBackId) {
		FeedBackId = feedBackId;
	}
	
	@XmlElement(name = "TransId")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getTransId() {
		return TransId;
	}
	public void setTransId(String transId) {
		TransId = transId;
	}
	
	@XmlElement(name = "Reason")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	
	@XmlElement(name = "Solution")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getSolution() {
		return Solution;
	}
	public void setSolution(String solution) {
		Solution = solution;
	}
	
	@XmlElement(name = "ExtInfo")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getExtInfo() {
		return ExtInfo;
	}
	public void setExtInfo(String extInfo) {
		ExtInfo = extInfo;
	}
	
	@XmlElementWrapper(name="PicInfo")
	@XmlElement(name = "item")
	public List<Item> getPicInfo() {
		return PicInfo;
	}
	public void setPicInfo(List<Item> picInfo) {
		PicInfo = picInfo;
	}
	
	@XmlElement(name = "SignMethod")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getSignMethod() {
		return SignMethod;
	}
	public void setSignMethod(String signMethod) {
		SignMethod = signMethod;
	}
	
	public static void main(String[] args){
		
	}
}
