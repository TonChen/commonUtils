/**
 * 
 */
package com.genaral.excel.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 导出数据封装类
 * @author rsh
 * @version 1.0
 */
public class DataRecord {

	private short[] columnWith;
	private String[] columnName;
	private int[] columnMerge;//需要合并的列，从0开始.
	private String sheetName;
	private String title;
	private String subTitle;
	private List<DataRow> dataRows;
	
	public DataRecord() {
		this.dataRows = new ArrayList<DataRow>();
	}
	
	public DataRecord(String title) {
		this.dataRows = new ArrayList<DataRow>();
		this.title = title;
	}
	
	public DataRecord(String title, List<DataRow> dataRows) {
		this.title = title;
		this.dataRows = dataRows;
	}
	
	public short[] getColumnWith() {
		return columnWith;
	}
	
	/**
	 * excel列宽,数组长度和列名数组长度相等
	 * @param columnWith
	 */
	public void setColumnWith(short[] columnWith) {
		this.columnWith = columnWith;
	}
	
	public String[] getColumnName() {
		if(columnName==null) return new String[]{"","","","",""};
		return columnName;
	}
	/**
	 * excel列名,数组长度和列宽数组长度相等
	 * @param columnName
	 */
	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}
	

	public int[] getColumnMerge() {
		return columnMerge;
	}

	/**
	 * excel需要合并的列数据，从0开始.(导出时会根据第一列数据来判断是否合并，本条数据和上一条数据相同则合并)
	 * @param columnMerge
	 */
	public void setColumnMerge(int[] columnMerge) {
		this.columnMerge = columnMerge;
	}

	public String getSheetName() {
		return sheetName;
	}

	/**
	 * excel工作表名称
	 * @param sheetName
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * excel标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSubTitle() {
		return subTitle;
	}
	
	/**
	 * excel副标题
	 * @param subTitle
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	public List<DataRow> getDataRows() {
		return dataRows;
	}
	
	/**
	 * excel数据
	 * @param dataRows
	 */
	public void setDataRows(List<DataRow> dataRows) {
		this.dataRows = dataRows;
	}
	
	/**
	 * 加入整行数据
	 * @param dataRow
	 */
	public void addDataRow(DataRow dataRow){
		dataRows.add(dataRow);
	}
	
	/**
	 * 获取行数
	 * @param dataRow
	 */
	public int getRows(){
		return dataRows==null?0:dataRows.size();
	}
}
