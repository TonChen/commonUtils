package com.genaral.wechat.reply;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "item")
public class ArticleItem {
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	@XmlElement(name = "Title")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	
	@XmlElement(name = "Description")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	@XmlElement(name = "PicUrl")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	@XmlElement(name = "Url")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
