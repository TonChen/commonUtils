/**
 * 
 */
package com.genaral.excel;

import com.alibaba.fastjson.JSONObject;
import com.genaral.date.DateConvertUtils;
import com.genaral.excel.bean.DataCell;
import com.genaral.excel.bean.DataRecord;
import com.genaral.excel.bean.DataRow;
import com.genaral.opencsv.CSVReader;
import com.genaral.sequence.RandomUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.Boolean;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * EXCEL导出工具类
 * @author rsh 2015-09-17
 * @version 1.0
 */
public class ExcelUtil {
	
	protected static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	
	/**
	 * 修改下载文件名
	 * @param request
	 * @param name
	 * @return
	 */
	public static String exchangeDocumentName(HttpServletRequest request, String name) {
		String agent = request.getHeader("USER-AGENT");
		try {
			if (null != agent && -1 != agent.indexOf("MSIE"))
				name = URLEncoder.encode(name, "UTF-8");
			else if (null != agent && -1 != agent.indexOf("Mozilla"))
				name = new String(name.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			name = "document";
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 导出文件csv格式<br>
	 * 1、文件内容只能是文字类型，无法导出其他类型数据，例如：图片，数值...<br>
	 * 2、无法设置其他样式，比如列宽，行高...
	 * @param response
	 * @param fileName
	 * @param dataRecord
	 * @throws Exception
	 */
	public static void exportAsCsv(HttpServletResponse response, String fileName, DataRecord dataRecord) throws Exception{
		response.setContentType("application/csv;charset=GBK"); 
		response.setHeader("Content-Disposition", "attachment; filename="+fileName+".csv");
		PrintWriter writer = response.getWriter();
		
		String[] columnName = dataRecord.getColumnName();
		StringBuffer column = new StringBuffer();
		for(int i=0; i<columnName.length; i++){
			column.append(columnName[i]);
			if((i+1) == columnName.length){
				column.append("\n");
			}else{
				column.append(",");
			}
		}
		writer.print(column.toString());
		List<DataRow> dataRows = dataRecord.getDataRows();
		for(DataRow dataRow : dataRows){
			List<DataCell> dataCells = dataRow.getDataCells();
			StringBuffer sb = new StringBuffer("");
			for(int j=0; j<dataCells.size(); j++){
				DataCell dataCell = dataCells.get(j);
				sb.append(String.valueOf(dataCell.getData()));
				if((j+1) == dataCells.size()){
					sb.append("\n");
				}else{
					sb.append(",");
				}
			}
			writer.print(sb.toString());
		}
		writer.flush();
		writer.close();
	}
	
	/**
	 * 导出文件csv格式<br>
	 * 1、文件内容只能是文字类型，无法导出其他类型数据，例如：图片，数值...<br>
	 * 2、无法设置其他样式，比如列宽，行高...
	 * @param path 文件保存地址
	 * @param fileName 文件名称
	 * @param dataRecord 文件数据
	 * @throws Exception
	 */
	public static String exportAsCsv(String path, String fileName, DataRecord dataRecord) throws Exception{
		if(path==null || path.equals(""))
			throw new Exception("file path cannot is null!");
		
		if(fileName==null || fileName.equals(""))
			throw new Exception("file name cannot is null!");
		
		if(!new File(path).exists()) new File(path).mkdir();
		
		String filepath = path + File.separator + fileName + ".csv";
		File file = new File(filepath);
		
		FileOutputStream out = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		
		String[] columnName = dataRecord.getColumnName();
		StringBuffer column = new StringBuffer();
		for(int i=0; i<columnName.length; i++){
			column.append(columnName[i]);
			if((i+1) == columnName.length){
				column.append("\n");
			}else{
				column.append(",");
			}
		}
		
		bw.write(column.toString());
		List<DataRow> dataRows = dataRecord.getDataRows();
		for(DataRow dataRow : dataRows){
			List<DataCell> dataCells = dataRow.getDataCells();
			StringBuffer sb = new StringBuffer("");
			for(int j=0; j<dataCells.size(); j++){
				DataCell dataCell = dataCells.get(j);
				sb.append(String.valueOf(dataCell.getData()));
				if((j+1) == dataCells.size()){
					sb.append("\n");
				}else{
					sb.append(",");
				}
			}
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
		return filepath;
	}
	
	
	/**
	 * 导入文件csv格式<br>
	 * @param path 文件地址
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsCsv(String path) throws Exception{
		if(path==null || path.equals("")){
			return null;
		}
		File file = new File(path);
		if(!file.exists()){
			return null;
		}
		FileInputStream in = new FileInputStream(file);
		return importAsCsv(in);
	}
	
	/**
	 * 导入文件csv格式<br>
	 * @param in 文件流
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsCsv(InputStream in) throws Exception{
		return  importAsCsv(in, "UTF-8");	
	}
	
	
	/**
	 * 导入文件csv格式(以逗号分隔处理)<br>
	 * @param in csv文件流
	 * @param charset 编码格式  默认“UTF-8”
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsCsv(InputStream in, String charset) throws Exception{
		if(in==null) return null;
		if(charset==null) charset = "utf-8";
		DataRecord dataRecord = new DataRecord();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, charset));
			String temp = null;
			while((temp = reader.readLine()) != null){
				//为什么每行最后加上一个空串，因为如果最后一个逗号（,）后为空时，分割的数据长度就不够。
				temp = temp+" ";
				String[] dataRowStr = temp.split(",");
				DataRow dataRow = new DataRow();
				for(int i=0; i<dataRowStr.length; i++){
					String dataCellStr = dataRowStr[i];
					//把刚才在最后加上的空串去掉
					if(i+1==dataRowStr.length){
						if(dataCellStr!=null)
							dataCellStr = dataCellStr.substring(0, dataCellStr.length()-1);
					}
					dataRow.addDataCell(dataCellStr==null?null:dataCellStr, DataCell.DATA_TYPE_TEXT);
				}
				dataRecord.addDataRow(dataRow);
			}
		} catch (Exception e) {
			log.error("导入文件csv格式异常", e);
			e.printStackTrace();
		} finally {
			if(reader!=null) reader.close();
			if(in!=null) in.close();
		}
		return dataRecord;
	}
	
	
	/**
	 * 导入文件csv格式(opencsv处理)<br>
	 * @param path 文件地址
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsCsvByopencsv(String path) throws Exception{
		if(path==null) return null;
		return importAsCsvByopencsv(path, "UTF-8");
	}
	
	/**
	 * 导入文件csv格式(opencsv处理)<br>
	 * @param path 文件地址
	 * @param charset 编码  默认“UTF-8”
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsCsvByopencsv(String path, String charset) throws Exception{
		if(path==null) return null;
		return importAsCsvByopencsv(new File(path), charset);
	}
	
	/**
	 * 导入文件csv格式(opencsv处理)<br>
	 * @param file csv文件
	 * @param charset 编码
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsCsvByopencsv(File file, String charset) throws Exception{
		if(file==null) return null;
		if(!file.exists()) return null;
		if(charset==null) charset = "utf-8";
		DataRecord dataRecord = new DataRecord();
		InputStreamReader fReader = null;
		CSVReader csvReader = null;
		try {
			fReader = new InputStreamReader(new FileInputStream(file), charset);
			csvReader = new CSVReader(fReader);
			//文件头
			String[] strs = csvReader.readNext();  
			DataRow titleDataRow = new DataRow();
	        if(strs != null && strs.length > 0){
	            for(String titleStr : strs){
	            	titleDataRow.addDataCell(titleStr==null?null:titleStr, DataCell.DATA_TYPE_TEXT);
	            }
	            dataRecord.addDataRow(titleDataRow);
	        } 
	        //文件内容
	        List<String[]> list = csvReader.readAll();
	        for(String[] ss : list){
	        	DataRow dataRow = new DataRow();
	            for(String data : ss){
	            	dataRow.addDataCell(data==null?null:data, DataCell.DATA_TYPE_TEXT);
	            }
	            dataRecord.addDataRow(dataRow);
	        }  
		} catch (Exception e) {
			log.error("导入文件csv格式异常", e);
			e.printStackTrace();
		} finally {
			if(csvReader!=null) csvReader.close();
		}
		return dataRecord;
	}
	
	/**
	 * 导出文件excel格式
	 * @param response 请求响应
	 * @param fileName 文件名 不包括文件后缀
	 * @param dataRecord 导出数据
	 * @throws Exception
	 */
	public static void exportAsExcel(HttpServletResponse response, String fileName, DataRecord dataRecord) throws Exception{
		 response.reset();// 清空输出流  
         response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"8859_1")+".xls");// 设定输出文件头  
         response.setContentType("application/msexcel");// 定义输出类型 
         OutputStream os = response.getOutputStream();
         
         exportAsExcel(os, dataRecord);
	}
    public static void exportAs2007Excel(HttpServletResponse response, String fileName, DataRecord dataRecord) throws Exception{
		 response.reset();// 清空输出流
         response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"8859_1")+".xlsx");// 设定输出文件头
         response.setContentType("application/msexcel");// 定义输出类型
         OutputStream os = response.getOutputStream();
         Excel2007Utils excel2007Utils = new Excel2007Utils();
         excel2007Utils.exportAsExcel(os, dataRecord);
	}
    public static void exportAs2007ExcelBySql(HttpServletResponse response, String fileName,String title, List<JSONObject> list) throws Exception{
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"8859_1")+".xlsx");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型
        OutputStream os = response.getOutputStream();
        Excel2007Utils excel2007Utils = new Excel2007Utils();
        excel2007Utils.exportByList(os, list, title);
    }
	
	/**
	 * 导出文件excel格式 （默认保存到用户文件夹）
	 * @param dataRecord 导出数据
	 * @throws Exception
	 */
	public static String exportAsExcel(DataRecord dataRecord) throws Exception{
		String path = System.getProperty("user.home") + File.separator + "upload" ;
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}
		path = path + File.separator + RandomUtil.randomNum(10)+".xls";
		FileOutputStream out = new FileOutputStream(new File(path));
        exportAsExcel(out, dataRecord);
        
        return path;
	}

	
	/**
	 * 导出文件excel格式
	 * @param path 文件保存地址 不能为空
	 * @param fileName 文件名  不包括文件后缀
	 * @param dataRecord 导出数据
	 * @throws Exception
	 */
	public static String exportAsExcel(String path, String fileName, DataRecord dataRecord) throws Exception{
		if(path==null || path.equals(""))
			throw new Exception("file path cannot is null!");
		
		if(fileName==null || fileName.equals(""))
			throw new Exception("file name cannot is null!");
		
		if(!new File(path).exists()) new File(path).mkdir();
		
		path = path + File.separator + fileName+".xls";
		File file = new File(path);
		FileOutputStream out = new FileOutputStream(file);
        exportAsExcel(out, dataRecord);
        
        return path;
	}

	/**
	 * 导出文件excel格式
	 * @param os 输出流
	 * @param dataRecord 数据
	 * @throws Exception
	 */
	public static void exportAsExcel(OutputStream os, DataRecord dataRecord) throws Exception{
		//需要合并列
		if(dataRecord.getColumnMerge()!=null){
			ExcelUtilExtend.exportAsExcel(os, dataRecord);
			
		}else{
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
	        int columnWith = DataCell.DEAFULT_COLUMN_WITH;
	        int size = row;
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
								File fileImage = getFile(String.valueOf(data));
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
	 			size ++;
	 		}
	        
	        //输出文件
	        wwb.write();
	        wwb.close();
		}
	}


	/** 
    * 给单元格加注释 
    * @param label 
    * @param comments 
    */ 
	public void setCellComments(Object label,String comments){  
       WritableCellFeatures cellFeatures = new WritableCellFeatures();  
       cellFeatures.setComment(comments);  
       if(label instanceof jxl.write.Number){  
           jxl.write.Number num = (jxl.write.Number)label;
           num.setCellFeatures(cellFeatures);  
       }else if(label instanceof jxl.write.Boolean){  
           jxl.write.Boolean bool = (jxl.write.Boolean)label;  
           bool.setCellFeatures(cellFeatures);  
       }else if(label instanceof jxl.write.DateTime){  
           jxl.write.DateTime dt = (jxl.write.DateTime)label;  
           dt.setCellFeatures(cellFeatures);  
       }else{  
           Label _label = (Label)label;  
           _label.setCellFeatures(cellFeatures);  
       }  
	} 
	   
	/**
	 * 获取导出图片
	 * @param data
	 * @return
	 */
	public static File getFile(String data){
		if(data==null || data.equals(""))
			return null;
		
		//网络图片
		if(data.startsWith("http")){
			String imgPath = dowlodInternetImg(data);
			return new File(imgPath);
			
		}else{//本地图片
			return new File(data);
		}
	}
	
	/**
	 * 下载网络图片
	 * @param imgUrl
	 * @return
	 */
	public static String dowlodInternetImg(String imgUrl){
		if(imgUrl==null || imgUrl.equals(""))
			return null;
		
		String path = System.getProperty("user.home")+File.separator + "upload" ;
		File file = new File(path);
		if(!file.exists()){
			file.mkdir();
		}
		path = path + File.separator + RandomUtil.randomNum(10)+".png";
		try {
			URL url = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5 * 1000);
			InputStream in = conn.getInputStream();
			
			byte[] data = redadInputStrem(in);  
			//new一个文件对象用来保存图片，默认保存当前工程根目录  
	        File imageFile = new File(path);  
	        //创建输出流  
	        FileOutputStream outStream = new FileOutputStream(imageFile);  
	        //写入数据  
	        outStream.write(data);  
	        //关闭输出流  
	        outStream.close();  
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 文件输入流转为字节
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] redadInputStrem(InputStream inStream) throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=inStream.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}
		inStream.close();
		return out.toByteArray();
	}
	
	
	/**
	 * 导入文件excel格式<br>
	 * @param path 文件地址
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsExcel(String path) throws Exception{
		if(path==null || path.equals("")){
			log.error("文件路径path为空");
			return null;
		}
		File file = new File(path);
		if(!file.exists()){
			log.error("文件不存在");
			return null;
		}
		FileInputStream in = new FileInputStream(file);
		return importAsExcel(in);
	}
	
	/**
	 * 导入文件excel格式<br>
	 * @param in 文件输入流
	 * @return DataRecord
	 * @throws Exception
	 */
	public static DataRecord importAsExcel(InputStream in) throws Exception{
		List<DataRecord> list = importAsExcels(in);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}

	/**
	 * 导入文件excel格式<br>
	 * @param path 文件地址
	 * @return List<DataRecord> excel多页
	 * @throws Exception
	 */
	public static List<DataRecord> importAsExcels(String path) throws Exception{
		if(path==null || path.equals("")){
			log.error("文件路径path为空");
			return null;
		}
		File file = new File(path);
		if(!file.exists()){
			log.error("文件不存在");
			return null;
		}
		FileInputStream in = new FileInputStream(file);
		return importAsExcels(in);
	}
	
	
	/**
	 * 导入文件excel格式<br>
	 * @param in 文件输入流
	 * @return DataRecord
	 * @throws Exception
	 */
	public static List<DataRecord> importAsExcels(InputStream in) throws Exception{
		if(in==null) return null;
		List<DataRecord> dataRecords = new ArrayList<DataRecord>();
		//获取excel文件
		Workbook wb = Workbook.getWorkbook(in);
		if(wb==null) return null;
		//获取excel工作薄数值
		Sheet[] sheets = wb.getSheets();
		if(sheets==null || sheets.length==0)
			return null;
		for (int i = 0; i < sheets.length; i++) {
			DataRecord dataRecord = new DataRecord();
			Sheet rs = wb.getSheet(i);
			int rows = rs.getRows();
			for (int j = 0; j < rows; j++) {
				DataRow dataRow = new DataRow();
				Cell[] cells = rs.getRow(j);
				for(int k=0; k<cells.length; k++)
					dataRow.addDataCell(cells[k].getContents(), DataCell.DATA_TYPE_TEXT);
				dataRecord.addDataRow(dataRow);
			}
			dataRecords.add(dataRecord);
		}
		wb.close();
		in.close();
		
		return dataRecords;
	}
	
	
	public static void main(String[] args) {
		String img = "http://daihuo2.oss-cn-hangzhou.aliyuncs.com/2015/9/16/351554052641728_201591663834.jpeg";
		String imgPath = dowlodInternetImg(img);
		
		String path = System.getProperty("user.home");
		path = path+File.separator+"upload"+File.separator+"456.png";
		
		//图片转格式
//		ImageUtil.convert(imgPath, "png", path);
	}

	
}
