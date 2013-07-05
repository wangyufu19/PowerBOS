//package com.framework.common.excel.util;
//import java.io.File;
//import org.jawin.COMException;
//import org.jawin.DispatchPtr;
//import org.jawin.win32.Ole32;
///**
// * XMLData数据帮助类
// * @author wangyf
// * @version 1.0
// */
//public class XMLDataUtil {
//	private final int XLS_TO_XML=46;
//	private final int XML_TO_XLS=18;
//	/**
//	 * EXCEL转换至XML
//	 * @param srcfile
//	 * @param tofile
//	 * @throws Exception
//	 */
//	public void convertXlsToXML(String srcfile,String tofile) throws Exception{		
//		File tmpfile=new File(srcfile);
//		if(!tmpfile.exists()) throw new Exception(srcfile+"报表模板XLS文件没有找到");			
//		this.convertFile(tmpfile.getPath(),tofile,this.XLS_TO_XML);
//	}
//	/**
//	 * XML转换至EXCEL
//	 * @param srcfile
//	 * @param tofile
//	 * @throws Exception
//	 */
//	public void convertXMLToXls(String srcfile,String tofile) throws Exception{		
//		File tmpfile=new File(srcfile);
//		if(!tmpfile.exists()) throw new Exception(srcfile+"报表模板XML文件没有找到");			
//		this.convertFile(tmpfile.getPath(), tofile, this.XML_TO_XLS);
//	}
//	/**
//	 * 通过JAWIN组件相互转换EXCEL和XML
//	 * @param arg1 源文件
//	 * @param arg2 目标文件
//	 * @param index
//	 * @throws Exception
//	 */
//	public void convertFile(String arg1,String arg2,int index) throws Exception{		
//		try {
//			Ole32.CoInitialize();
//			File tofile= new File(arg2);
//			if (tofile.exists()) {
//				tofile.delete();
//			}
//			DispatchPtr app = new DispatchPtr("Excel.Application");
//			app.put("Visible", false);
//			DispatchPtr books = (DispatchPtr) app.get("Workbooks");
//			DispatchPtr book = (DispatchPtr) books.invoke("Open", arg1);
//			book.invoke("SaveAs", arg2, new Integer(index));
//			app.invoke("Quit");
//			Ole32.CoUninitialize();
//		} catch (COMException e) {		
//			e.printStackTrace();
//		}	
//	}
//}
