package com.application.console.biz;
import java.io.File;
import org.jdom.Element;

import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 脚本对象业务类
 * @author wangyf
 * @version 1.0
 */
public class ScriptObjectBiz {
	
	public String getScriptObjectContent(String resourceName,String scriptKey){
		String scriptText="";
		if("".equals(resourceName)||"".equals(scriptKey)) return scriptText;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return scriptText;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element scriptE=doc.getDocElement(scriptKey);
		if(scriptE==null) return scriptText;
		scriptText=StringFormat.replaceNull(scriptE.getText());		
		return scriptText;
	}
	public void saveScriptObjectContent(String resourceName,String scriptKey,String scriptText){
		if("".equals(resourceName)||"".equals(scriptKey)) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element scriptE=doc.getDocElement(scriptKey);
		if(scriptE==null) return;
		doc.addDocElementCDATA(scriptKey, scriptText);
		doc.saveDoc();
	}
}
