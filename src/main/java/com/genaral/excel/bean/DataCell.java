package com.genaral.excel.bean;


/**
 * 导出数据列数据
 * @author rsh
 * @version 1.0
 */
public class DataCell {
	
	/** 文字 */
	public static final int DATA_TYPE_TEXT = 0;
	/** 数字 */
	public static final int DATA_TYPE_NUMBER = 1;
	/** 日期 */
	public static final int DATA_TYPE_DATE = 2;
	/** 图片 */
	public static final int DATA_TYPE_IMG = 3;
	/** boolean 布尔类型 */
	public static final int DATA_TYPE_BOOLEAN = 4;
	
	/** 默认列宽 */
	public static final int DEAFULT_COLUMN_WITH = 15;
	
	private Object data;
	private int type; // 0：文字，1：数字，2：日期，3：图片，4：boolean布尔值
	
	private String dateFormat = "yyyy-MM-dd";//日期格式
	
	private Integer imgHeight;//图片高-是基于该列的高（取值0-1，也可以其他值如果是大于1则是跨单元格）
	private Integer imgWidth;//图片宽-是基于该列的宽（取值0-1，也可以其他值如果是大于1则是跨单元格）
	
	public DataCell(Object data, int type) {
		super();
		this.data = data;
		this.type = type;
	}
	
	public DataCell(Object data, int type, String dateFormat) {
		super();
		this.data = data;
		this.type = type;
		this.dateFormat = dateFormat;
	}
	
	public DataCell(Object data, int type, Integer imgWidth, Integer imgHeight) {
		super();
		this.data = data;
		this.type = type;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getDataString() {
		if(data==null) return "";
		return String.valueOf(data);
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public Integer getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}

	public Integer getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
