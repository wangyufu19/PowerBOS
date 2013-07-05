//package com.framework.common.excel.data;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//import com.framework.common.excel.util.XMLDataUtil;
///**
// * XMLData数据处理类
// * @author wangyf
// * @version 1.0
// */
//public class XMLData {
//	/**
//	 * 得到xls的XMLData数据
//	 * @param srcfile
//	 * @param tofile
//	 * @return
//	 */
//	public String getXMLData(String srcfile,String tofile){
//		String xmlData="";
//		XMLDataUtil convert=new XMLDataUtil();		
//		try {
//			convert.convertXlsToXML(srcfile, tofile);
//		} catch (Exception e) {			
//			e.printStackTrace();
//		}
//		xmlData=this.getFileInputstream(tofile);
//		return xmlData;
//	}
//	/**
//	 * 保存xls的XMLData数据
//	 * @param xmlData
//	 * @param srcfile
//	 * @param tofile
//	 */
//	public void saveXMLData(String xmlData,String srcfile,String tofile){		
//		XMLDataUtil convert=new XMLDataUtil();		
//		try {
//			byte[] buffer =xmlData.getBytes("utf-8");
//			File file=new File(srcfile);
//			if(file.exists()) file.deleteOnExit();
//			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//			bos.write(buffer);
//			bos.close();
//			convert.convertXMLToXls(srcfile, tofile);
//		} catch (UnsupportedEncodingException e) {		
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {		
//			e.printStackTrace();
//		} catch (IOException e) {			
//			e.printStackTrace();
//		} catch (Exception e) {			
//			e.printStackTrace();
//		}		
//	}
//	/**
//	 * 得到文件流的字符串
//	 * @param arg1
//	 * @return
//	 */
//	public String getFileInputstream(String arg1){
//		String s = null;
//		FileInputStream in = null;
//		try {
//			in = new FileInputStream(new File(arg1));
//			int i = in.available();
//			byte abyte0[] = new byte[i];
//			in.read(abyte0);
//			in.close();
//			s = new String(abyte0, 0, abyte0.length, "utf-8");			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return s;
//	}	
//
//}
