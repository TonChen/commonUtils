package com.genaral.xml;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class XMLUtil {

	public static Document str2Doc(String xml) {
		return str2Doc(xml, "utf-8");
	}

	public static Document str2Doc(String xml, String encode) {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(new ByteArrayInputStream(xml
					.getBytes(encode))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parser.getDocument();
	}

	public static String doc2Str(Document doc) {
		return doc2Str(doc, "utf-8");
	}

	public static String doc2Str(Document doc, String encode){
		TransformerFactory tf = TransformerFactory.newInstance();
		String xmlStr = null;
		try {
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding", encode);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(bos));
			xmlStr = bos.toString(encode);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return xmlStr;
	}

}
