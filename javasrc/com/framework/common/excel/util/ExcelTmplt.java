//package com.framework.common.excel.util;
//import com.framework.common.excel.data.XMLData;
///**
// * Excel模板类
// * @author wangyf
// * @version 1.0
// */
//public class ExcelTmplt {
//	/**
//	 * 得到模板XMLData
//	 * @param srcfile
//	 * @param tofile
//	 * @return
//	 */
//	public String getXMLData(String srcfile,String tofile){
//		String xmlData="";		
//		XMLData XMLData=new XMLData();
//		xmlData=this.convertToHtml(XMLData.getXMLData(srcfile, tofile));		
//		return xmlData;
//	}
//	/**
//	 * 保存模板XMLData
//	 * @param xmlData
//	 * @param srcfile
//	 * @param tofile
//	 */
//	public void saveXMLData(String xmlData,String srcfile,String tofile){	
//		XMLData XMLData=new XMLData();
//		XMLData.saveXMLData(xmlData, srcfile, tofile);
//	}
//	
//	/**
//	 * 解析字符串的HMTL特殊字符
//	 * @param arg1
//	 * @return
//	 */
//	public String convertToHtml(String arg1){
//		StringBuilder buf=new StringBuilder(arg1);
//		StringBuilder newbuf=new StringBuilder();
//		for (int i = 0; i <buf.length(); i++) {
//            char ch = buf.charAt(i);
//            if (ch == '&') {
//            	newbuf.append("&amp;");
//            } else if (ch == '<') {
//            	newbuf.append("&lt;");
//            } else if (ch == '>') {
//            	newbuf.append("&gt;");
//            } else if (ch == '\r') {
//            	newbuf.append("&#13;");
//            } else if (ch == '\n') {
//            	newbuf.append("&#10;");
//            } else if (ch == '"') {
//            	newbuf.append("&quot;");
//            } else if (ch == '￥') {
//            	newbuf.append("&yen;");
//            } else if (ch == '＄') {
//            	newbuf.append("&yen;");
//            } else {
//            	newbuf.append(ch);
//            }
//        }
//		return newbuf.toString();
//	}
//}
