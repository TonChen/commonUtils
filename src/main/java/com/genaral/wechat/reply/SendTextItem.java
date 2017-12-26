package com.genaral.wechat.reply;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "text")
public class SendTextItem {
	private String content;

	@XmlElement(name = "content")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
