package com.genaral.wechat.receive;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "xml")
public class WarnReqMessage{
	private String AppId;
	private String ErrorType; 
	private String Description; 
	private String AlarmContent;
	private String TimeStamp;
	private String AppSignature;
	private String SignMethod; 
	
	@XmlElement(name = "AppId")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getAppId() {
		return AppId;
	}
	public void setAppId(String appId) {
		AppId = appId;
	}
	
	@XmlElement(name = "ErrorType")
	public String getErrorType() {
		return ErrorType;
	}
	public void setErrorType(String errorType) {
		ErrorType = errorType;
	}
	
	@XmlElement(name = "Description")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	@XmlElement(name = "AlarmContent")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getAlarmContent() {
		return AlarmContent;
	}
	public void setAlarmContent(String alarmContent) {
		AlarmContent = alarmContent;
	}
	
	@XmlElement(name = "TimeStamp")
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	
	@XmlElement(name = "AppSignature")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getAppSignature() {
		return AppSignature;
	}
	public void setAppSignature(String appSignature) {
		AppSignature = appSignature;
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
