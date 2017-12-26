package com.genaral.wechat.reply;

import com.genaral.xml.JaxbBinder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "xml")
public class ReplyArticleMessage extends BaseReplyMessage{
	private int ArticleCount;
	private List<ArticleItem> Articles ;
	
	@XmlElement(name = "ArticleCount")
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	@XmlElementWrapper(name="Articles")
	@XmlElement(name = "item")
	public List<ArticleItem> getArticles() {
		return Articles;
	}
	public void setArticles(List<ArticleItem> articles) {
		Articles = articles;
	}
	
	public static void main(String[] args){
		JaxbBinder jb=new JaxbBinder(ReplyArticleMessage.class);
		ReplyArticleMessage ram = new ReplyArticleMessage();
		ram.setToUserName("1a");
		ram.setFromUserName("2b");
		ram.setCreateTime(new Date().getTime());
		ram.setMsgType("news");
		ram.setArticleCount(2);
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem a = new ArticleItem();
		a.setTitle("1");
		a.setPicUrl("http://www.baidu.com");
		a.setDescription("");
		a.setUrl("http://www.baidu.com");
		items.add(a);
		
		ArticleItem a2 = new ArticleItem();
		a2.setTitle("1");
		a2.setPicUrl("http://www.baidu.com");
		a2.setDescription("");
		a2.setUrl("http://www.baidu.com");
		items.add(a2);
		
		ram.setArticles(items);
		String xml = jb.toXml(ram, "UTF-8",true);
		System.out.println(xml);
	}
}
