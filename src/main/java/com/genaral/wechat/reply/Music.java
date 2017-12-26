package com.genaral.wechat.reply;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "Music")
public class Music {
	private String Title;
	private String Description;
	private String MusicUrl;
	private String HQMusicUrl;
	
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
	
	@XmlElement(name = "MusicUrl")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getMusicUrl() {
		return MusicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}
	
	@XmlElement(name = "HQMusicUrl")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
}
