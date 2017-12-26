package com.genaral.wechat.receive;


import com.genaral.xml.JaxbBinder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class PackageReqMessage{
	private String OpenId;
	private String AppId;
	private String IsSubscribe; 
	private String ProductId; 
	private String TimeStamp;
	private String NonceStr;
	private String AppSignature;
	private String SignMethod; 
	
	@XmlElement(name = "OpenId")
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
	
	@XmlElement(name = "IsSubscribe")
	public String getIsSubscribe() {
		return IsSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		IsSubscribe = isSubscribe;
	}
	
	@XmlElement(name = "ProductId")
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	
	@XmlElement(name = "TimeStamp")
	public String getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	
	@XmlElement(name = "NonceStr")
	public String getNonceStr() {
		return NonceStr;
	}
	public void setNonceStr(String nonceStr) {
		NonceStr = nonceStr;
	}
	
	@XmlElement(name = "AppSignature")
	public String getAppSignature() {
		return AppSignature;
	}
	public void setAppSignature(String appSignature) {
		AppSignature = appSignature;
	}
	
	@XmlElement(name = "SignMethod")
	public String getSignMethod() {
		return SignMethod;
	}
	public void setSignMethod(String signMethod) {
		SignMethod = signMethod;
	}
	
	public static void main(String[] args){
		JaxbBinder jb=new JaxbBinder(PackageReqMessage.class);
		String xml = "<xml><OpenId><![CDATA[oKBvfjjprj6RRfNrvI98p3KGi5B0]]></OpenId><AppId><![CDATA[wxf5a4d0fb3d65a9b6]]></AppId><IsSubscribe>1</IsSubscribe><ProductId><![CDATA[0148083fd7fe22aa]]></ProductId><TimeStamp>1408896159</TimeStamp><NonceStr><![CDATA[VGtSB0Mss2uAEaSn]]></NonceStr><AppSignature><![CDATA[d44894287a6cf22102f17f453849e38716449e90]]></AppSignature><SignMethod><![CDATA[sha1]]></SignMethod></xml>";
//		xml = xml.replaceAll("<![CDATA[", "").replaceAll("]]", "");
//		System.out.println(xml);
		PackageReqMessage receive = jb.fromXml(xml);
		System.out.println(receive.getProductId());
	}
}
