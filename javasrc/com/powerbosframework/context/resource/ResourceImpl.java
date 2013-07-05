package com.powerbosframework.context.resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import com.powerbosframework.context.resource.Resource;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;
/**
 * 应用上下文资源管理实现类
 * @author youfu.wang
 * @version 1.0
 */
public class ResourceImpl implements Resource{
	private File file=null;
	private static Logger log=LogFactory.getInstance();
	
	public ResourceImpl(String resource){
		file=new File(Thread.currentThread().getContextClassLoader().getResource(resource).getPath());
	}
	@Override
	/**
	 * 资源文件是否存在
	 * @return
	 */
	public boolean exists() {		
		if(file==null) return false;		
		if(file.exists()) return true;
		return false;
	}
	/**
	 * 得到资源文件输入流
	 * @throws IOException 
	 * @return
	 */
	@Override	
	public InputStream getInputStream() throws IOException {		
		if(file==null) return null;				
		InputStream stream=null;
		try{						
			if(file.exists()&&file.isFile()){				
				stream=new FileInputStream(file);
			}			
		}catch(Exception e){
			throw new IOException(e);
		}		
		return stream;	
	}
	/**
	 * 得到资源文件名称
	 * @return
	 */
	@Override
	public String getResourceName() {	
		if(file==null) return null;
		return file.getName();
	}
}
