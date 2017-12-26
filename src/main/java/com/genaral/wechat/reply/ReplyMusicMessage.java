package com.genaral.wechat.reply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class ReplyMusicMessage extends BaseReplyMessage{
	
	private Music music;
	
	@XmlElement(name = "Music")
	public void setMusic(Music music) {
		this.music = music;
	}
	
	public Music getMusic() {
		return music;
	}
}
