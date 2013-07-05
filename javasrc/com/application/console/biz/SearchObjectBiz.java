package com.application.console.biz;
import java.io.File;
import org.jdom.Element;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 查询对象业务类
 * @author wangyf
 * @version 1.0
 */
public class SearchObjectBiz {
	
	public String[] getSearchObjectBase(String resourceName,String searchKey){
		String[] arr=new String[2];
		arr[0]="";
		arr[1]="";		
		if("".equals(resourceName)||"".equals(searchKey)) return arr;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return arr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element searchE=doc.getDocElement(searchKey);
		if(searchE==null) return arr;
		arr[0]=StringFormat.replaceNull(searchE.getAttributeValue("searchTitle"));
		arr[1]=StringFormat.replaceNull(searchE.getAttributeValue("loadColumnSetStyle"));
		return arr;
	}
	public void saveSearchObjectBase(String resourceName,String searchKey,String searchTitle,String loadColumnSetStyle){
		if("".equals(resourceName)||"".equals(searchKey)) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element searchE=doc.getDocElement(searchKey);
		if(searchE==null) return;
		searchE.setAttribute("searchTitle", StringFormat.replaceNull(searchTitle));
		searchE.setAttribute("loadColumnSetStyle", StringFormat.replaceNull(loadColumnSetStyle));
		doc.saveDoc();
	}
}
