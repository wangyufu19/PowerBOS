package com.application.support.biz;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

import com.application.support.model.User;
import com.framework.common.base.BaseBiz;
import com.framework.common.file.Download;
import com.framework.common.util.SysConstants;
import com.sqlMap.SqlMapClient;
import com.sqlMap.SqlMapFactory;
/**
 * 本类用于生成数据报表
 * @author wangyf
 * @version 1.0
 */
public class UserDataMgrBiz extends BaseBiz{
	//导出用户数据
	public void expUserData() throws SQLException, UnsupportedEncodingException{
		Long orgId=Long.valueOf((reh.get("parentId")));		
		String reportType=reh.get("reportType");
		String reportName=reh.get("reportName");
		String sqlMapId="getOrgUser";		
		SqlMapClient sqlMapClient=SqlMapFactory.buildSqlMapClient();
		List list=sqlMapClient.createQuery().list(orgId, sqlMapId);
		sqlMapClient.close();
		if(list==null) return;
		String expPath=SysConstants.public_path+File.separator+"upload"+File.separator+reportName+".xls";			
		//构建Workbook对象
		HSSFWorkbook wwb=new HSSFWorkbook();				
		//创建Excel工作表
		HSSFSheet sheet=wwb.createSheet(reportName);
		sheet.setSelected(true);
		//设置单元格样式
		HSSFCellStyle style=wwb.createCellStyle();

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); 
		style.setBottomBorderColor(HSSFColor.BLACK.index); 
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN); 
		style.setLeftBorderColor(HSSFColor.BLACK.index); 
		style.setBorderRight(HSSFCellStyle.BORDER_THIN); 
		style.setRightBorderColor(HSSFColor.BLACK.index); 
		style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		
//		style.setFillForegroundColor(HSSFColor.YELLOW.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//设置字体		
//		HSSFFont font=wwb.createFont();
//		font.setFontHeightInPoints((short)12);				
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setColor(HSSFColor.RED.index);
//		style.setFont(font);
		//设置自动换行
		//style.setWrapText(true);
		for(int i=0;i<list.size();i++){
			User user=(User)list.get(i);
			//创建行
			HSSFRow row=sheet.createRow(i);
			for(int j=0;j<5;j++){
				String value="";
				if(j==0){
					value=String.valueOf((user.getId()));
				}else if(j==1){
					value=user.getUserName();
				}else if(j==2){
					value=user.getLoginName();
				}else if(j==3){
					value=String.valueOf((user.getUserType()));
				}else if(j==4){
					value=String.valueOf((user.getUserStatus()));
				}
				//创建单元格							
				HSSFCell cell=row.createCell(j);				
				cell.setCellValue(value);				
				cell.setCellStyle(style);
			}		
		}				
		//写入Excel工作表	
		try {
			OutputStream out = new FileOutputStream(expPath);
			wwb.write(out);
			//输出流对象
			if(out!=null)
				out.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}			
		//下载报表文件
		Download download=new Download(reh);
		download.download(expPath, reportName, reportType);		
		File file=new File(expPath);
		file.delete();		
	}
}
