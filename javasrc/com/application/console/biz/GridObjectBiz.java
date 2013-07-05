package com.application.console.biz;
import java.io.File;
import org.jdom.Element;

import com.application.console.model.GridBase;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;
/**
 * 网格对象业务类
 * @author wangyf
 * @version 1.0
 */
public class GridObjectBiz {
	
	public GridBase getGridObjectBase(String resourceName,String gridKey){
		GridBase gridBase=new GridBase();
		if("".equals(resourceName)||"".equals(gridKey)) return gridBase;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return gridBase;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element gridE=doc.getDocElement(gridKey);	
		if(gridE==null) return gridBase;		
		gridBase.setGridTitle(StringFormat.replaceNull(gridE.getAttributeValue("gridTitle")));
		gridBase.setLoadDataSetStyle(StringFormat.replaceNull(gridE.getAttributeValue("loadDataSetStyle")));
		gridBase.setLoadLinkToolbar(StringFormat.replaceNull(gridE.getAttributeValue("loadLinkToolbar")));
		gridBase.setLoadHandleToolbar(StringFormat.replaceNull(gridE.getAttributeValue("loadHandleToolbar")));
		gridBase.setLoadPageToolbar(StringFormat.replaceNull(gridE.getAttributeValue("loadPageToolbar")));
		gridBase.setPageFetchSize(StringFormat.replaceNull(gridE.getAttributeValue("pageFetchSize")));
		return gridBase;
	}
	public void saveGridObjectBase(String resourceName,String gridKey,GridBase gridBase){
		if("".equals(resourceName)||gridBase==null) return;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element gridE=doc.getDocElement(StringFormat.replaceNull(gridKey));	
		if(gridE==null) return;
		gridE.setAttribute("gridTitle", StringFormat.replaceNull(gridBase.getGridTitle()));
		gridE.setAttribute("loadDataSetStyle", StringFormat.replaceNull(gridBase.getLoadDataSetStyle()));
		gridE.setAttribute("loadLinkToolbar", StringFormat.replaceNull(gridBase.getLoadLinkToolbar()));
		gridE.setAttribute("loadHandleToolbar", StringFormat.replaceNull(gridBase.getLoadHandleToolbar()));
		gridE.setAttribute("loadPageToolbar", StringFormat.replaceNull(gridBase.getLoadPageToolbar()));
		gridE.setAttribute("pageFetchSize", StringFormat.replaceNull(gridBase.getPageFetchSize()));
		doc.saveDoc();
	}

}
