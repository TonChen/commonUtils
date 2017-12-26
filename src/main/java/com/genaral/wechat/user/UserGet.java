package com.genaral.wechat.user;

public class UserGet {
	public int total;
	public int count;
	public String next_openid;
	public UserData data;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	public UserData getData() {
		return data;
	}
	public void setData(UserData data) {
		this.data = data;
	}
}
