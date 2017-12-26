package com.model;


/**
 * 上传文件到oss返回数据
 * @author rsh 2015-11-19
 *
 */
public class UploadFileOssResult {

	private String url;
	
	private String md5;
	
	private String result;
	
	private String bucketName;
	
	private String objectName;
	
	private JsonBean json;

	public JsonBean getJson() {
		return json;
	}

	public void setJson(JsonBean json) {
		this.json = json;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
}
