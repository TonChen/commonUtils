package com.genaral.wechat.pay;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "xml")
public class RefundQueryRes {
	private String returnCode;
	private String returnMsg;
	private String resultCode;
	private String errCode;
	private String errCodeDes;
	
	private String appid;
	private String mchId;
	private String deviceInfo;
	private String nonceStr;
	private String sign;
	
	private String transactionId;
	private String outTradeNo ;
	private int totalFee;
	private String feeType;
	private int cashFee;
	private String cashFeeType;
	private int couponRefundFee;
	private int refundCount;
	
	private String outRefundNo;
	private int refundFee;
	private String refundId;
	private String refundStatus;
	
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
	@XmlElement(name = "fee_type")
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	@XmlElement(name = "total_fee")
	public int getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	
	@XmlElement(name = "cash_fee")
	public int getCashFee() {
		return cashFee;
	}
	public void setCashFee(int cashFee) {
		this.cashFee = cashFee;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "transaction_id")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "out_trade_no")
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "out_refund_no_0")
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "refund_id_0")
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
	@XmlElement(name = "refund_fee_0")
	public int getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(int refundFee) {
		this.refundFee = refundFee;
	}
	
	
	@XmlElement(name = "coupon_refund_fee")
	public int getCouponRefundFee() {
		return couponRefundFee;
	}
	public void setCouponRefundFee(int couponRefundFee) {
		this.couponRefundFee = couponRefundFee;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "cash_fee_type")
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	
	@XmlElement(name = "refund_count")
	public int getRefundCount() {
		return refundCount;
	}
	public void setRefundCount(int refundCount) {
		this.refundCount = refundCount;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "refund_status_0")
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
}
