package com.framework.common.file;
import com.framework.common.servlet.http.RequestHash;
/**
 * 本类用于文件下载服务
 */
public class Download {
	private RequestHash reh;
	/**
	 * 构造函数，生成Download的对象。
	 */
	public Download(RequestHash reh) {
		this.reh=reh;
	}
	/**
	 * 下载WORK,EXCEL,PDF
	 * @param fullPathName
	 * @param fileName
	 * @param fileType
	 */
	public void download(String fullPathName,String fileName,String fileType){	
		if(fileType.equals("doc")){
			reh.getResponse().setContentType("application/vnd.ms-word;charset=GBK");
			fileName=fileName.concat(".doc");
	    }else if(fileType.equals("pdf")){
	    	reh.getResponse().setContentType("application/pdf;charset=GBK");	
	    	fileName=fileName.concat(".pdf");
	    }else if(fileType.equals("xls")){
	    	reh.getResponse().setContentType("application/vnd.ms-excel;charset=GBK");
	    	fileName=fileName.concat(".xls");
	    }		
		java.io.BufferedInputStream bis=null;
		java.io.BufferedOutputStream  bos=null;
		try {
			reh.getResponse().setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("gb2312"),"iso8859-1"));
			bis =new java.io.BufferedInputStream(new java.io.FileInputStream(fullPathName));
			bos=new java.io.BufferedOutputStream(reh.getResponse().getOutputStream()); 
			byte[] buff = new byte[2048];
			int bytesRead;
			while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff,0,bytesRead);
			}
			if (bis != null)bis.close();		
			if (bos != null)bos.close();	
		} catch (Exception e) {		
			e.printStackTrace();
		}	
	}
	/**
	 * 下载附件
	 * @param fullPathName
	 * @param fileName
	 */
	public void download(String fullPathName,String fileName){
		java.io.BufferedInputStream bis=null;
		java.io.BufferedOutputStream  bos=null;
		try {
			reh.getResponse().setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("gb2312"),"iso8859-1"));
			bis =new java.io.BufferedInputStream(new java.io.FileInputStream(fullPathName));
			bos=new java.io.BufferedOutputStream(reh.getResponse().getOutputStream()); 
			byte[] buff = new byte[2048];
			int bytesRead;
			while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff,0,bytesRead);
			}
			if (bis != null)bis.close();		
			if (bos != null)bos.close();	
		} catch (Exception e) {		
			e.printStackTrace();
		}	
	}

}
