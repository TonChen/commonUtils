package com.genaral.excel;

import com.genaral.excel.bean.DataCell;
import com.genaral.excel.bean.DataRecord;
import com.genaral.excel.bean.DataRow;
import com.genaral.object.ObjectUtil;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by renfx on 2016/6/24.
 */
public class Excel2007Utils {
    private Logger log = LoggerFactory.getLogger(getClass());
    private Workbook workbook;
    public Excel2007Utils(){
        workbook = new SXSSFWorkbook(100);
    }
    public Excel2007Utils(int bufferSize){
        this.workbook = new SXSSFWorkbook(bufferSize);
    }
    public CellStyle createTitleCellStyle(){
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.ROYAL_BLUE.index);
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        //cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return cellStyle;
    }
    public CellStyle createColTitleCellStyle(){
    	CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(HSSFColor.ROYAL_BLUE.index);
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        cellStyle.setFont(font);
        //cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        return cellStyle;
    }
    public CellStyle createDefaultCellStyle(){
    	CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        //cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        //cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);//单元格自动换行
        return cellStyle;
    }

    /**
     *
     * @param cell
     * @param format
     */
    public void setCellDataFormat(XSSFCell cell, String format){
        XSSFCellStyle cellStyle = cell.getCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(format));
        cell.setCellStyle(cellStyle);
    }
    public void setDataRecordCell(Cell cell ,Object data) throws Exception{
        if(data instanceof String){
            cell.setCellValue(String.valueOf(data));
        }else if(data instanceof Double){
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        }else if(data instanceof Integer){
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        }else if(data instanceof Long){
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        }else if(data instanceof Float){
            cell.setCellValue(Double.valueOf(String.valueOf(data)));
        }else if(data instanceof Boolean){
            cell.setCellValue((Boolean)data);
        }else if(data instanceof Date){
            cell.setCellValue((Date)data);
        }
    }
    public void setDataRecordCell(Cell cell ,Object data,int type) throws Exception{
        if(data==null){
            cell.setCellValue("");
            return;
        }
        switch (type) {
            case DataCell.DATA_TYPE_TEXT:
                cell.setCellValue(String.valueOf(data));
                break;

            case DataCell.DATA_TYPE_NUMBER:
                cell.setCellValue(Double.valueOf(String.valueOf(data)));
                break;

            case DataCell.DATA_TYPE_DATE:
                cell.setCellValue((Date)data);
                break;

            case DataCell.DATA_TYPE_IMG:
                cell.setCellValue(String.valueOf(data));
                break;

            case DataCell.DATA_TYPE_BOOLEAN:
                cell.setCellValue((Boolean)data);
                break;

            default:
                cell.setCellValue(String.valueOf(data));
                break;
        }
    }
    public void mergedRegion(Sheet sheet,int firstRow,int lastRow,int firstCol,int lastCol){
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }
    public void exportAsExcel(OutputStream os, DataRecord dataRecord) throws Exception{
        log.info("exportAsExcel start"+new Date());
        Sheet sheet = workbook.createSheet(dataRecord.getSheetName()==null?"工作表":dataRecord.getSheetName());
        int rowIndex = 0;
        if(dataRecord.getTitle()!=null){
            Row titleRow = sheet.createRow(rowIndex);
            titleRow.setHeight((short)800);
            Cell titleCell = titleRow.createCell(0);
            mergedRegion(sheet,0,0,0,dataRecord.getColumnName().length-1);
            titleCell.setCellStyle(createTitleCellStyle());
            titleCell.setCellValue(dataRecord.getTitle());
            rowIndex++;
        }
        //设置列头名
        String[] columnName = dataRecord.getColumnName();
        Row colTitleRow = sheet.createRow(rowIndex);
        colTitleRow.setHeight((short)600);

        CellStyle cellStyle = createColTitleCellStyle();
        for(int i =0,n=columnName.length;i<n;i++){
            sheet.setColumnWidth(i, columnName[i].getBytes().length * 2*256);
            Cell cell = colTitleRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnName[i]);
        }
        log.info("exportAsExcel loop start "+new Date());
        rowIndex++;
        List<DataRow> dataRows = dataRecord.getDataRows();
//        short[] colunmnWith = dataRecord.getColumnWith();
//        int columnWith = DataCell.DEAFULT_COLUMN_WITH;
        int i =0;
        for(DataRow dataRow : dataRows) {
            List<DataCell> dataCells = dataRow.getDataCells();
            Row row = sheet.createRow(i+rowIndex);
            Integer rowHeight = dataRow.getRowHeight();
            if(rowHeight!=null) row.setHeight((short)(int)rowHeight);
            int j=0;
            for (DataCell dataCell : dataCells) {
                if(dataCell==null)
                    continue;
                int type = dataCell.getType();
                Object data = dataCell.getData();
                Cell cell = row.createCell(j);
                setDataRecordCell(cell,data,type);
//                if(colunmnWith!=null && colunmnWith.length>=(j+1))//设置列宽
//                    columnWith = colunmnWith[j];
//                sheet.setColumnWidth(j,columnWith*100);
                j++;
                
            }
            i++;
        }
        log.info("exportAsExcel workbook.write start "+new Date());
        workbook.write(os);
        os.close();
        log.info("exportAsExcel workbook.write end "+new Date());
    }

    /**
     * 导出
     * @param response
     * @param fileName 文件名
     * @param title 标题
     * @param collection 数据集合 Collection<Map>
     * @param columnNames 列头
     * @throws Exception
     */
    public void exportByList(HttpServletResponse response,String fileName,String title,Collection<? extends Map> collection,Collection<String> columnNames) throws Exception {
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"8859_1")+".xlsx");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型
        OutputStream os = response.getOutputStream();
        exportByList(os,collection,title,columnNames);
    }
    /**
     * 导出 用LIST<Map>里的Map的key作为列头
     * @param response
     * @param fileName 文件名
     * @param title 标题
     * @param collection 数据集合 Collection<Map>
     * @throws Exception
     */
    public void exportByList(HttpServletResponse response,String fileName,String title,Collection<? extends Map> collection) throws Exception {
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"8859_1")+".xlsx");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型
        OutputStream os = response.getOutputStream();
        exportByList(os,collection,title);
    }

    /**
     *  用LIST<Map>里的Map的key作为列头
     *  若用mybatis查询返回List，若整列的数据都不存在，则列头就不存在
     * @param os 流
     * @param collection 数据集合 Collection<Map>
     * @param title 标题
     * @throws Exception
     */
    public void exportByList(OutputStream os, Collection<? extends Map> collection,String title) throws Exception{
        Map<String, Object> map0 = collection.iterator().next();
        Set<String> columnNames = map0.keySet();
        exportByList(os,collection,title,columnNames);
    }

    /**
     * 自定义列头
     * @param os 流
     * @param collection 数据集合 Collection<Map>
     * @param title 标题
     * @param columnNames 列名
     * @throws Exception
     */
    public void exportByList(OutputStream os, Collection<? extends Map> collection,String title,Collection<String> columnNames) throws Exception{
        Sheet sheet = workbook.createSheet("Sheet");
        if(collection.size()>0){

            int rowIndex = 0;
            if(ObjectUtil.isNullOrEmptyString(title)){
                //标题
                Row titleRow = sheet.createRow(rowIndex);
                titleRow.setHeight((short)800);
                Cell titleCell = titleRow.createCell(0);
//                mergedRegion(sheet,0,0,0,columnNames.size()-1);
                titleCell.setCellStyle(createTitleCellStyle());
                titleCell.setCellValue(title);
                rowIndex++;
            }
            //设置列头名
            Row colTitleRow = sheet.createRow(rowIndex);
            colTitleRow.setHeight((short)600);
            int colIndex = 0;
            CellStyle cellStyle = createColTitleCellStyle();
            for(String columnName:columnNames){
                sheet.setColumnWidth(colIndex, columnName.getBytes().length * 300);
                Cell cell = colTitleRow.createCell(colIndex);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(columnName);
                colIndex++;
            }
            rowIndex++;
            int i =0;
            for(Map<String, Object> map : collection) {
                Row row = sheet.createRow(i + rowIndex);
                row.setHeight((short)400);
                int cellIndex = 0;
                for (String key : columnNames) {
                    Object value = map.get(key);
                    String stringValue = String.valueOf(value);
                    if(key.getBytes().length<stringValue.getBytes().length){//列宽自适应
                        sheet.setColumnWidth(cellIndex, stringValue.getBytes().length * 300);
                    }
                    Cell cell = row.createCell(cellIndex);
                    setDataRecordCell(cell, value);
                    cellIndex++;
                }
                i++;
            }
        }
        workbook.write(os);
        os.close();
    }
    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
}
