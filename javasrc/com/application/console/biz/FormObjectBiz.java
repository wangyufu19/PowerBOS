package com.application.console.biz;
import java.io.File;
import org.jdom.Element;

import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 表单对象业务类
 * @author wangyf
 * @version 1.0
 */
public class FormObjectBiz {
	
	public String[] getFormObjectBase(String resourceName,String formKey){
		String[] arr=new String[3];
		arr[0]="";
		arr[1]="";
		arr[2]="";
		if("".equals(resourceName)||"".equals(formKey)) return arr;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return arr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element formE=doc.getDocElement(formKey);
		if(formE==null) return arr;
		arr[0]=StringFormat.replaceNull(formE.getAttributeValue("formTitle"));
		arr[1]=StringFormat.replaceNull(formE.getAttributeValue("loadDataSetStyle"));
		arr[2]=StringFormat.replaceNull(formE.getAttributeValue("loadColumnSetStyle"));
		return arr;
	}
	public void saveFormObjectBase(String resourceName,String formKey,String formTitle,String loadDataSetStyle,String loadColumnSetStyle){
		if("".equals(resourceName)||"".equals(formKey)) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element formE=doc.getDocElement(formKey);
		if(formE==null) return;
		formE.setAttribute("formTitle", StringFormat.replaceNull(formTitle));
		formE.setAttribute("loadDataSetStyle", StringFormat.replaceNull(loadDataSetStyle));
		formE.setAttribute("loadColumnSetStyle", StringFormat.replaceNull(loadColumnSetStyle));
		doc.saveDoc();
	}
}
