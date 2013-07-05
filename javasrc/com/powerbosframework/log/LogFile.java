package com.powerbosframework.log;
import java.io.File;
import java.util.Calendar;

import com.framework.common.file.FileUtil;
import com.framework.common.util.SysConstants;

/**
 * 日志文件类
 * @author wangyf
 * @version 1.0
 */
public class LogFile {
	
	public void log(String message){
		String path=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"log";
		StringBuilder buf=new StringBuilder();
		String fileName="";
		String oldMessage="";
		Calendar calendar = Calendar.getInstance();
		int sysMonth = calendar.get(2) + 1;
		fileName+=calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5) + ".txt";	
		path=path+File.separator+fileName;
		FileUtil fileUtil=new FileUtil();
		if(new File(path).exists())
			oldMessage=fileUtil.getInputStreamString(path);
		buf.append(oldMessage);
		buf.append(System.getProperty("line.separator"));
		buf.append(message);		
		fileUtil.write(buf.toString(), path);
	}
}
