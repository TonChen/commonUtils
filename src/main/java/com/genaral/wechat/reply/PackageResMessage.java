package com.genaral.wechat.reply;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "xml")
public class PackageResMessage {
	private String AppId;
	private String TimeStamp;
	private String NonceStr;
	private String AppSignature;
	private String SignMethod; 
	
	private String Package;
	private String RetCode; 
	private String RetErrMsg;
	
	@XmlElement(name = "Package")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getPackage() {
		return Package;
	}
	public void setPackage(String package1) {
		Package = package1;
	}
	
	@XmlElement(name = "RetCode")
	public String getRetCode() {
		return RetCode;
	}
	public void setRetCode(String retCode) {
		RetCode = retCode;
	}
	
	@XmlElement(name = "RetErrMsg")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getRetErrMsg() {
		return RetErrMsg;
	}
	public void setRetErrMsg(String retErrMsg) {
		RetErrMsg = retErrMsg;
	}
	
	@XmlElement(name = "AppId")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
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
	
	@XmlElement(name = "NonceStr")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getNonceStr() {
		return NonceStr;
	}
	public void setNonceStr(String nonceStr) {
		NonceStr = nonceStr;
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
