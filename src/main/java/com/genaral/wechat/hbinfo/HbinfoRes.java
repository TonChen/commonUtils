package com.genaral.wechat.hbinfo;


import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
@XmlRootElement(name = "xml")
public class HbinfoRes {
	private String returnCode;
	private String returnMsg;
	private String sign;
	private String resultCode;
	private String errCode;
	private String errCodeDes;
	
	private String mchBillno;
	private String mchId;
	private String detailId;
	private String status;
	private String sendType;
	private String hbType;
	private int totalNum;
	private int totalAmount;
	private String reason;
	private String sendTime;
	private String refundTime;
	private int refundAmount;
	private String wishing;
	private String remark;
	private String actName;
	private List<Hbinfo> hblist;
	
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
	@XmlElement(name = "mch_billno")
	public String getMchBillno() {
		return mchBillno;
	}
	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
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
	@XmlElement(name = "detail_id")
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "send_type")
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "hb_type")
	public String getHbType() {
		return hbType;
	}
	public void setHbType(String hbType) {
		this.hbType = hbType;
	}
	
	@XmlElement(name = "total_num")
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	
	@XmlElement(name = "total_amount")
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "reason")
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "send_time")
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "refund_time")
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	
	
	@XmlElement(name = "refund_amount")
	public int getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "wishing")
	public String getWishing() {
		return wishing;
	}
	
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@XmlElement(name = "act_name")
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
	@XmlElementWrapper(name="hblist")
	@XmlElement(name = "hbinfo")
	public List<Hbinfo> getHblist() {
		return hblist;
	}
	public void setHblist(List<Hbinfo> hblist) {
		this.hblist = hblist;
	}
}
