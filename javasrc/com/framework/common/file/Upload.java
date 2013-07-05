package com.framework.common.file;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;

import com.framework.common.util.SysConstants;
import com.powerbosframework.util.StringUtil;
/**
 * 功能说明:文件上传类
 * @author wangyf
 * @version 1.0
 */
public class Upload {
	public HashMap doUpload(HttpServletRequest request){
		HashMap<String, String> tmpHash=new HashMap<String, String>();		
		try{			
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//设置上传工厂限制
			factory.setSizeThreshold(1024*1024*20);
			factory.setRepository(new File(SysConstants.public_path));
			//创建上传文件对象
			ServletFileUpload upload=new ServletFileUpload(factory);
			//设置最大
			upload.setSizeMax(1024*1024*20);
			//处理请求
			List items=upload.parseRequest(request);	
			//创建文件过滤器
			FileFilter filer=new FileFilter();
			for(Iterator it=items.iterator();it.hasNext();){
				FileItem item=(FileItem)it.next();
				if(item.isFormField()){
					String name=item.getFieldName();					
					String value=StringUtil.getPostEncode(item.getString());										
					if(tmpHash.containsKey(name)){						
						tmpHash.remove(name);
					}
					tmpHash.put(name, value);
				}else{
					String fileName=StringUtil.replaceNull(item.getName());
					if("".equals(fileName)) continue;
					//String fieldName=item.getFieldName();	
					String attachName=StringUtil.getTimeNum()+fileName.substring(fileName.lastIndexOf("."), fileName.length());	
					String attachDisplayName=fileName.substring(fileName.lastIndexOf(File.separator)+1,fileName.lastIndexOf("."));
					String attachPath=this.getAttachPath(attachName);				
					File file=new File(attachPath);
					if(!file.exists()){
						file.mkdirs();
					}				
					String attachSize=String.valueOf(item.getSize());					
					tmpHash.put("attachSize", attachSize);
					file.delete();					
					if(filer.allowTypes(fileName)){								
						FileOutputStream fos=new FileOutputStream(file);
						if(item.isInMemory()){//如果上传的文件在内存
							fos.write(item.get());
						}else{
							InputStream in=item.getInputStream();
							byte[] buffer=new byte[1024];
							int len=0;
							while((len=in.read(buffer))>0){
								fos.write(buffer, 0, len);
							}
							in.close();
							fos.close();							
						}						
						tmpHash.put("attachPath", attachPath);
						tmpHash.put("attachName", attachName);	
						tmpHash.put("attachDisplayName", attachDisplayName);											
					}					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return tmpHash;
	}
	
	public String getAttachPath(String attachName){
		String path="";				
		if(SysConstants.upload_path.indexOf("/")!=-1){
			path=SysConstants.public_path+SysConstants.upload_path+File.separator+attachName;	
		}else
			path=SysConstants.public_path+File.separator+SysConstants.upload_path+File.separator+attachName;		
		return path;
	}		
}
