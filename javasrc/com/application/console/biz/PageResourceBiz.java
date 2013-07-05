package com.application.console.biz;
import java.io.File;

import java.util.List;
import java.util.ArrayList;
import org.jdom.Document;
import org.jdom.Element;

import com.application.console.model.PageResource;
import com.framework.common.base.BaseBiz;
import com.framework.common.util.SysConstants;
import com.framework.common.util.XMLUtil;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.common.util.DateUtil;
import com.framework.view.util.StringFormat;


/**
 * 页面资源管理业务类
 * @author wangyf
 * @version 1.0
 */
public class PageResourceBiz extends BaseBiz{
	
	public List getPageResourceList(){
		List resources=new ArrayList();
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page";
		File file=new File(fullPath);
		if(!file.exists()) return resources;		
		File[] listFiles=file.listFiles();
		if(listFiles==null) return resources;
		for(int i=0;i<listFiles.length;i++){
			File tmpFile=listFiles[i];
			if(tmpFile.isFile()){
				DocObject doc=DocFactory.getInstace().getDocObject(tmpFile.getPath());
				Element rootE=doc.getDocRootElement();
				if(rootE==null) continue;
				PageResource pageResource=new PageResource();
				pageResource.setResourceName(tmpFile.getName());
				pageResource.setCreateTime(StringFormat.replaceNull(rootE.getAttributeValue("createTime")));
				pageResource.setResourceSize(String.valueOf(tmpFile.length()));
				if(!"".equals(pageResource.getResourceName()))
					resources.add(pageResource);
			}			
		}
		return resources;
	}
	public void addPageResource(PageResource vo){			
		if(vo==null) return;
		if("".equals(StringFormat.replaceNull(vo.getResourceName()))) return;
		XMLUtil xmlUitl=new XMLUtil();			
		if(!StringFormat.replaceNull(vo.getResourceName()).endsWith(".xml"))
			vo.setResourceName(vo.getResourceName()+".xml");		
		String resourcePath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+StringFormat.replaceNull(vo.getResourceName());		
		File file=new File(resourcePath);	
		vo.setCreateTime(DateUtil.getYYYYMMDDHHMMSS(System.currentTimeMillis()));
		Document doc=new Document();
		Element rootE=new Element("CONFIGURATION");	
		doc.setRootElement(rootE);
		xmlUitl.output(doc, file);		
	}
	public void deletePageResources(String[] resources){
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page";
		if(resources==null) return;
		File file=new File(fullPath);
		if(!file.exists()) return;		
		File[] listFiles=file.listFiles();
		if(listFiles==null) return;
		for(int i=0;i<resources.length;i++){
			for(int j=0;j<listFiles.length;j++){
				File tmpFile=listFiles[j];
				if(tmpFile.getName().equals(resources[i]))
					tmpFile.delete();
			}
		}		
	}

}
