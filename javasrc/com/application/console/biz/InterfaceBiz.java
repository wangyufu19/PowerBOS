package com.application.console.biz;
import java.io.File;
import org.jdom.Element;

import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 外部接口业务类
 * @author wangyf
 * @version 1.0
 */
public class InterfaceBiz {
	
	public String[] getGridInterfaceBase(String resourceName,String interfaceKey){
		String[] arr=new String[2];
		arr[0]="";
		arr[1]="";
		if("".equals(resourceName)||"".equals(interfaceKey)) return arr;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return arr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element interfaceE=doc.getDocElement(interfaceKey);
		if(interfaceE==null) return arr;
		arr[0]=StringFormat.replaceNull(interfaceE.getAttributeValue("className"));
		arr[1]=StringFormat.replaceNull(interfaceE.getAttributeValue("methodName"));
		return arr;
	}
	public void saveGridInterfaceBase(String resourceName,String interfaceKey,String className,String methodName){
		if("".equals(resourceName)||"".equals(interfaceKey)) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element interfaceE=doc.getDocElement(interfaceKey);
		if(interfaceE==null) return;
		interfaceE.setAttribute("className", StringFormat.replaceNull(className));		
		interfaceE.setAttribute("methodName", StringFormat.replaceNull(methodName));
		doc.saveDoc();
	}

}
