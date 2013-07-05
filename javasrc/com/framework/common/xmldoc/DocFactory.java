package com.framework.common.xmldoc;
import com.framework.common.xmldoc.impl.DocObjectImpl;
/**
 * 功能说明:文档工厂
 * @author wangyf
 * @create-date 2010-11-18
 */
public class DocFactory {	
	private static DocFactory instance=null;	
	public static DocFactory getInstace(){
		if(instance==null)
			instance=new DocFactory();
		return instance;
	}	
	/**
	 * 得到文档对象
	 * @param fullPath
	 * @return
	 */
	public DocObject getDocObject(String fullPath){
		ElInit init=new ElInit();    	
    	if(fullPath==null) return null;
    	init.init(fullPath);    	
    	DocObject obj=new DocObjectImpl();
    	obj.setElHash(init.getElHash());    
    	obj.setDocConfig(fullPath);
    	return obj;
	}
}
