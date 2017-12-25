package com.genaral.excel.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * 导出数据一行数据封装类
 * @author rsh
 * @version 1.0
 */
public class DataRow {

	private List<DataCell> dataCells;
	private int rowHeight;
	
	public DataRow() {
		dataCells = new ArrayList<DataCell>();
	}
	
	public DataRow(int rowHeight) {
		dataCells = new ArrayList<DataCell>();
		this.rowHeight = rowHeight;
	}

	public List<DataCell> getDataCells() {
		return dataCells;
	}

	public void setDataCells(List<DataCell> dataCells) {
		this.dataCells = dataCells;
	}
	
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * 单位 px 像素
	 * @param rowHeight
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = (rowHeight * 20);
	}

	/**
	 * 加入列数据 文字类型
	 * @param data 数据
	 * @param type 0：文字，1：数字，2：日期，3：图片，4：boolean布尔值
	 */
	public void addDataCell(Object data, int type){
		dataCells.add(new DataCell(data, type));
	}
	
	/**
	 * 加入列数据  日期类型
	 * @param data 数据 
	 * @param type 0：文字，1：数字，2：日期，3：图片，4：boolean布尔值
	 * @param format 日期格式 yyyy-MM-dd，yyyy-MM-dd HH:mm:ss, 默认yyyy-MM-dd
	 */
	public void addDataCell(Object data, int type, String format){
		if(format==null || format.equals(""))
			format = "yyyy-MM-dd";
		dataCells.add(new DataCell(data, type, format));
	}
	
	
	/**
	 * 加入列数据  图片
	 * @param data 数据
	 * @param type 数据类型   0：文字，1：数字，2：日期，3：图片，4：boolean布尔值
	 * @param width 图片宽度  是基于该列的高（取值0-1，也可以其他值如果是大于1则是跨单元格）
	 * @param height 图片高度  是基于该列的宽（取值0-1，也可以其他值如果是大于1则是跨单元格）
	 */
	public void addDataCell(Object data, int type, int width, int height){
		dataCells.add(new DataCell(data, type, width, height));
	}
	
}
