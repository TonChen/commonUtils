package com.genaral.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.cfth.core.util.DateConvertUtils;

import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * EXCEL工具类扩展,excel列合并...
 * @author rsh 2016-06-02
 * @version 1.0
 *
 */
public class ExcelUtilExtend {

	/**
	 * 导出文件excel
	 * @param os 输出流
	 * @param dataRecord excel填充数据
	 * @throws Exception
	 */
	public static void exportAsExcel(OutputStream os, DataRecord dataRecord) throws Exception{
		if(os==null)
			throw new NullPointerException("os not Cannot null");
		if(dataRecord==null)
			throw new NullPointerException("dataRecord not Cannot null");
		// 建立excel文件  
        WritableWorkbook wwb = Workbook.createWorkbook(os);
		
        // 创建一个工作表
        WritableSheet ws = wwb.createSheet(dataRecord.getSheetName()==null?"工作表":dataRecord.getSheetName(), 1);
        
        int row = 0;
        if(dataRecord.getTitle()!=null){
        	//设置标题文字格式
            WritableFont wf1 = new WritableFont(WritableFont.ARIAL,14,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf1 = new WritableCellFormat(wf1);
            wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf1.setAlignment(Alignment.CENTRE);
             
            ws.addCell(new Label(0, row, dataRecord.getTitle()==null?"标题":dataRecord.getTitle(), wcf1));
            //合并单元格，第一个参数：要合并的单元格最左上角的列号，
            //第二个参数：要合并的单元格最左上角的行号，
            //第三个参数：要合并的单元格最右角的列号，
            //第四个参数：要合并的单元格最右下角的行号，
            ws.mergeCells(0, row, dataRecord.getColumnName().length-1, 0);
            ws.setRowView(0, 1000, false); //设置标题行高
            row ++;
        }
        
    	//设置列头单元格的文字格式
        WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);//通过调整宽度和高度自动换行
        
        //设置列头名
        String[] columnName = dataRecord.getColumnName();
        for (int j=0;j<columnName.length;j++){
        	ws.addCell(new Label(j, row, columnName[j], wcf));
        }
        row ++;
         
        WritableFont dataWf = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
        //文本
        WritableCellFormat dataWcf1 = new WritableCellFormat(dataWf);
        dataWcf1.setAlignment(jxl.format.Alignment.LEFT);
        dataWcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
        dataWcf1.setWrap(true);//通过调整宽度和高度自动换行
         
        //数字
        DisplayFormat displayFormat2 = NumberFormats.DEFAULT;
        WritableCellFormat dataWcf2 = new WritableCellFormat(dataWf, displayFormat2);
        dataWcf2.setAlignment(jxl.format.Alignment.LEFT);
        dataWcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
        dataWcf2.setWrap(true);//通过调整宽度和高度自动换行
         
        //日期
        jxl.write.DateFormat dateFormat = new jxl.write.DateFormat("yyyy-MM-dd HH:mm:ss");
        WritableCellFormat dataWcf3 = new WritableCellFormat(dataWf, dateFormat);
        dataWcf3.setAlignment(jxl.format.Alignment.LEFT);
        dataWcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
        dataWcf3.setWrap(true);//通过调整宽度和高度自动换行
         
        List<DataRow> dataRows = dataRecord.getDataRows();
        short[] colunmnWith = dataRecord.getColumnWith();
        int[] columnMerge = dataRecord.getColumnMerge();

        int columnWith = DataCell.DEAFULT_COLUMN_WITH;
        int size = row;
        String[] temp = new String[columnMerge==null?0:columnMerge.length];
        temp[0] = "&合并&";
        
        int num = 0;
        boolean b = false;
		boolean b1 = false;
        for(int i = 0; i < dataRows.size(); i ++){
        	DataRow dataRow = dataRows.get(i);
 			List<DataCell> dataCells = dataRow.getDataCells();
 			Integer rowHeight = dataRow.getRowHeight();
 			
 			for(int j = 0; j < dataCells.size(); j ++){
 				DataCell dataCell = dataCells.get(j);
 				if(dataCell==null)
 					continue;
 				
 				int type = dataCell.getType();
 				Object data = dataCell.getData();
 				
 				if(data==null)
 					data = "";
 				
 				boolean isColumnMerge = false;
 				if(columnMerge!=null){
 					for(int column : columnMerge){
 						if(column == j){
 							isColumnMerge = true;
 							break;
 						}
 					}
 				}
 				if(isColumnMerge){
 					if(data!=null && !"".equals(data) && j==0){
						String lastData = temp[0];
 						if(lastData!=null && lastData.equals(data.toString())){
 							num ++;
 							b = true;
 							b1 = false;
 	 					}else{
 	 						b = false;
 							b1 = true;
 	 					}
 						temp[0] = data.toString();
 					}
 				}
 				//如果该列需要合并而且符合合并条件则不再向该单元格写入数据
 				if(isColumnMerge && b)
 					continue;
 				
 				switch (type) {
					case DataCell.DATA_TYPE_TEXT:
						Label label = new Label(j, size, String.valueOf(data), dataWcf1);
						ws.addCell(label);
						break;
						
					case DataCell.DATA_TYPE_NUMBER:
						jxl.write.Number number = new jxl.write.Number(j, size, (Double)data, dataWcf2);
						ws.addCell(number);
						break;
					
					case DataCell.DATA_TYPE_DATE:
						jxl.write.DateTime dateTime = new jxl.write.DateTime(j, size, DateConvertUtils.parse(String.valueOf(data), dataCell.getDateFormat()) , dataWcf3);
						ws.addCell(dateTime);
						break;
						
					case DataCell.DATA_TYPE_IMG:
						if(data != null && !data.equals("")){
							File fileImage = ExcelUtil.getFile(String.valueOf(data));
							if(fileImage!=null){
								Integer imgWidth = dataCell.getImgWidth();
								Integer imgHeigth = dataCell.getImgHeight();
								if(imgWidth==null || imgHeigth==null){
									//BufferedImage bufImage = ImageIO.read(fileImage);
									imgHeigth = 1;
						            imgWidth = 1;
								}
								WritableImage image = new WritableImage(j, size, imgWidth, imgHeigth, fileImage);
								ws.addImage(image);
								break;
							}
						}
						ws.addCell(new Label(j, size, "", dataWcf1));
						break;
						
					case DataCell.DATA_TYPE_BOOLEAN:
						jxl.write.Boolean labelB = new jxl.write.Boolean(j, size, (Boolean)data , dataWcf1);
						ws.addCell(labelB);
						break;
						
					default:
						ws.addCell(new Label(j, size, String.valueOf(data), dataWcf1));
						break;
				}
 				//设置列宽
 				if(colunmnWith!=null && colunmnWith.length>=(j+1))
 					columnWith = colunmnWith[j];
 				ws.setColumnView(j, columnWith);
 			}
 			//设置行高
 			if(rowHeight!=null)
 				ws.setRowView(size, rowHeight, false); 
 			
 			/*//最后一条数据写入后，如果前面数据需要合并则进行合并
 			if(i==dataRows.size()-1){
 				if(num>0){
 					//合并单元格，第一个参数：要合并的单元格最左上角的列号，
 			        //第二个参数：要合并的单元格最左上角的行号，
 			        //第三个参数：要合并的单元格最右角的列号，
 			        //第四个参数：要合并的单元格最右下角的行号，
 					if(columnMerge!=null){
 							for(int column : columnMerge){
 								int n = size - num;
 								int r = size;
 								ws.mergeCells(column, n, column, r);
 							}
 						}
 			        b1 = false;
 			        num = 0;
 				}
 				
 			}else{
 				//合并
 	 			if(num>0 && b1){
 					//合并单元格，第一个参数：要合并的单元格最左上角的列号，
 		            //第二个参数：要合并的单元格最左上角的行号，
 		            //第三个参数：要合并的单元格最右角的列号，
 		            //第四个参数：要合并的单元格最右下角的行号，
 					if(columnMerge!=null){
 	 					for(int column : columnMerge){
 	 						int n = size - num -1;
 	 						int r = size - 1;
 	 						ws.mergeCells(column, n, column, r);
 	 					}
 	 				}
 		            b1 = false;
 		            num = 0;
 				}
 			}*/
 			
 			//合并
 			if((num>=1 && b1) || (num>=1 && (i==dataRows.size()-1))){
// 				System.out.println("=="+num);
				//合并单元格，第一个参数：要合并的单元格最左上角的列号，
	            //第二个参数：要合并的单元格最左上角的行号，
	            //第三个参数：要合并的单元格最右角的列号，
	            //第四个参数：要合并的单元格最右下角的行号，
				if(columnMerge!=null){
 					for(int column : columnMerge){
 						int n = 0;
 						int r = 0;
 						if(i==dataRows.size()-1){
 							n = size - num;
 							r = size;
 						}else{
 							n = size - num -1;
							r = size - 1;
 						}
						ws.mergeCells(column, n, column, r);
 					}
 				}
	            b1 = false;
	            num = 0;
			}
	 			
 			size ++;
 		}
        
        //输出文件
        wwb.write();
        wwb.close();
        
	}
	
	public static void main(String[] args) {
		DataRecord dataRecord = new DataRecord();
		dataRecord.setTitle("A佣金明细");
		dataRecord.setColumnName(new String[]{"订单时间","状态","商品名称","数量","单价","总价","佣金比例","B店","金额","操作时间","状态"});
		dataRecord.setColumnMerge(new int[]{0, 1});
		
		DataRow row = new DataRow();
		row.addDataCell("2016/3/25", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("已完成", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("洁柔古龙卷纸JJ095-02(10卷)", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("2", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("25.5", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("51", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("1%", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("新都总部体验店", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("0.51", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("", DataCell.DATA_TYPE_TEXT);
		row.addDataCell("待确认", DataCell.DATA_TYPE_TEXT);
		dataRecord.addDataRow(row);
		
		DataRow row1 = new DataRow();
		row1.addDataCell("2016/3/26", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("已完成", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("洁柔古龙卷纸JJ095-02(10卷)123", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("2123", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("25.5123", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("51123", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("1%123", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("新都总部体验店", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("0.51123", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("", DataCell.DATA_TYPE_TEXT);
		row1.addDataCell("待确认", DataCell.DATA_TYPE_TEXT);
		dataRecord.addDataRow(row1);
		
		DataRow row2 = new DataRow();
		row2.addDataCell("2016/3/26", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("已完成", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("洁柔古龙卷纸JJ095-02(10卷)1212321", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("2", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("25.5", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("51", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("1%", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("新都总部体验店", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("0.51", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("", DataCell.DATA_TYPE_TEXT);
		row2.addDataCell("待确认", DataCell.DATA_TYPE_TEXT);
		dataRecord.addDataRow(row2);
		
		DataRow row3 = new DataRow();
		row3.addDataCell("2016/3/26", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("已完成", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("洁柔古龙卷纸JJ095-02(10卷)12123123", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("2123", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("25.5123", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("51123", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("1%123", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("新都总部体验店", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("0.51123", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("", DataCell.DATA_TYPE_TEXT);
		row3.addDataCell("待确认", DataCell.DATA_TYPE_TEXT);
		dataRecord.addDataRow(row3);
		
		try {
			exportAsExcel(new FileOutputStream(new File("E:\\test.xlsx")), dataRecord);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
