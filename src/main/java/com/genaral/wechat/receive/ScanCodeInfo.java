package com.genaral.wechat.receive;

import com.genaral.wechat.adapter.AdapterCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "ScanCodeInfo")
public class ScanCodeInfo {
	private String scanResult;
	
	@XmlElement(name = "ScanResult")
	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getScanResult() {
		return scanResult;
	}
	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}
	
	private String scanType;

	@XmlElement(name = "ScanType")
	public String getScanType() {
		return scanType;
	}
	public void setScanType(String scanType) {
		scanType = scanType;
	}
}
