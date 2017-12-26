package com.genaral.wechat.hbinfo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hbinfo")
public class Hbinfo {
	private String openid;
	private String status;
	private int amount;
	private String rcvTime;
	
	@XmlElement(name = "openid")
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@XmlElement(name = "amount")
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@XmlElement(name = "rcv_time")
	public String getRcvTime() {
		return rcvTime;
	}
	
	public void setRcvTime(String rcvTime) {
		this.rcvTime = rcvTime;
	}
}
