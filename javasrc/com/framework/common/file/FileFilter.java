package com.framework.common.file;
import com.framework.common.util.SysConstants;
/**
 * 功能说明:文件类型过滤类
 * @author wangyf
 * @version 1.0
 */
public class FileFilter {
	public FileFilter(){
		
	}
	public boolean allowTypes(String fileName){
		boolean bool=false;
		String[] types=SysConstants.upload_allowable_extensions.split(",");
		for(String type:types){					
			if(fileName.toLowerCase().endsWith(type)){
				bool=true;
				break;
			}
		}		
		return bool;
	}

}
