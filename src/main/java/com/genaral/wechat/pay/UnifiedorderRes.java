package com.genaral.wechat.pay;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "xml")
public class UnifiedorderRes {
	private String returnCode;
	private String returnMsg;
	private String appid;
	private String mchId;
	private String deviceInfo;
	private String nonceStr;
	private String sign;
	private String resultCode;
	private String errCode;
	private String errCodeDes;
	private String tradeType;
	private String prepayId ;
	private String codeUrl;
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "return_code")
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "return_msg")
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "appid")
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "mch_id")
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "device_info")
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "nonce_str")
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "sign")
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "result_code")
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "err_code")
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "err_code_des")
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "trade_type")
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "prepay_id")
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "code_url")
	public String getCodeUrl() {
		return codeUrl;
	}
	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
}
