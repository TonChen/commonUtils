package com.genaral.wechat.reply;

import com.genaral.xml.JaxbBinder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class SendTextMessage extends BaseSendMessage{
	private SendTextItem text;
	
	@XmlElement(name = "text")
	public SendTextItem getText() {
		return text;
	}

	public void setText(SendTextItem text) {
		this.text = text;
	}
	
	public static void main(String[] args){
		JaxbBinder jb=new JaxbBinder(SendTextMessage.class);
		SendTextMessage ram = new SendTextMessage();
		ram.setToUserName("1");
		ram.setMsgType("text");
		SendTextItem text = new SendTextItem();
		text.setContent("七夕不走寻常路！回馈支持拈花微笑的小伙伴们，全场商品不涨价！更有五款新品限时折扣，赶紧预定享特惠吧～戳→→<a href=\"http: //nhwx520.com/pages/Nhwxm/home.do?from=topic\">七夕预定</a>。");
		ram.setText(text);
		String xml = jb.toXml(ram, "UTF-8",true);
		System.out.println(xml);
	}
	
}
