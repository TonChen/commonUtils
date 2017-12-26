package com.genaral.wechat.receive;


import com.genaral.xml.JaxbBinder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class ReceiveMessage extends BaseReceiveMessage{
	//文本消息//text 
	private String Content;
	
	//图片消息//image 
	private String PicUrl;
	
	//地理位置消息 //location
	private String Location_X;//地理位置纬度 
	private String Location_Y;//地理位置经度
	private String Scale;//地图缩放大小
	private String Label;//地理位置信息
	
	//链接消息//link
	private String Title; //消息标题
	private String Description; //消息描述
	private String Url;//消息链接 
	
	//时间推送//event
	private String Event; //事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件) 
	private String EventKey;//事件KEY值，与自定义菜单接口中KEY值对应 
	
	
	private String MediaId; 	//语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format; 	//语音格式，如amr，speex等 
	
	private String ThumbMediaId; 	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。 
	
	private String nickName;
	private int sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	
	private ScanCodeInfo scanCodeInfo;
	
	@XmlElement(name = "ScanCodeInfo")
	public ScanCodeInfo getScanCodeInfo() {
		return scanCodeInfo;
	}
	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		this.scanCodeInfo = scanCodeInfo;
	}
	
	@XmlElement(name = "nickname")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@XmlElement(name = "sex")
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	@XmlElement(name = "language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	@XmlElement(name = "city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement(name = "province")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	@XmlElement(name = "country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlElement(name = "headimgurl")
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	@XmlElement(name = "Content")
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
	@XmlElement(name = "PicUrl")
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	@XmlElement(name = "Location_X")
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	
	@XmlElement(name = "Location_Y")
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	
	@XmlElement(name = "Scale")
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	
	@XmlElement(name = "Label")
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	
	@XmlElement(name = "Title")
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	@XmlElement(name = "Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	@XmlElement(name = "Url")
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	@XmlElement(name = "Event")
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	
	@XmlElement(name = "EventKey")
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	@XmlElement(name = "MediaId")
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	@XmlElement(name = "Format")
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
	@XmlElement(name = "ThumbMediaId")
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	public static void main(String[] args){
		String code = "http://weixin.qq.com/r/dUyPl_DEgL3-raT09xln?o1d86dee2cdadd7ce";
		System.out.println(code.substring(44));
		JaxbBinder jb=new JaxbBinder(ReceiveMessage.class);
		String xml = "<xml><ToUserName><![CDATA[gh_d494715ad675]]></ToUserName><FromUserName><![CDATA[oo1M6t9j9yTwDO8i8M0OxMPPoCNA]]></FromUserName><CreateTime>1428738718</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[scancode_waitmsg]]></Event><EventKey><![CDATA[10073515000001]]></EventKey><ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType><ScanResult><![CDATA[http://weixin.qq.com/r/dUyPl_DEgL3-raT09xln?o1d86dee2cdadd7ce]]></ScanResult></ScanCodeInfo></xml>";
//		xml = xml.replaceAll("<![CDATA[", "").replaceAll("]]", "");
//		System.out.println(xml);
		ReceiveMessage receive = jb.fromXml(xml);
//		if("CLICK".equals(receive.getEvent().toUpperCase())){
//			System.out.println(receive.getEventKey());
//		}
		System.out.println(receive.getScanCodeInfo().getScanResult());
		
	}
}
