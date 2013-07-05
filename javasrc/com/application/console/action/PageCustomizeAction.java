package com.application.console.action;
import java.io.File;
import java.util.List;
import org.jdom.Element;

import com.application.console.biz.PageCustomizeBiz;
import com.application.console.model.PageWidget;
import com.controller.action.SupportAction;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;
/**
 * 页面定制动作类
 * @author wangyf
 * @version 1.0
 */
public class PageCustomizeAction extends SupportAction{
	private List pageWidgets;
	private PageWidget pageWidget;
	
	public List getPageWidgets() {
		return pageWidgets;
	}

	public void setPageWidgets(List pageWidgets) {
		this.pageWidgets = pageWidgets;
	}

	public PageWidget getPageWidget() {
		return pageWidget;
	}

	public void setPageWidget(PageWidget pageWidget) {
		this.pageWidget = pageWidget;
	}

	public String execute(){
		PageCustomizeBiz biz=new PageCustomizeBiz();
		this.setPageWidgets(biz.getPageWidgetList(this.getActionContext().get("resourceName")));
		this.getActionContext().set("resourceName", this.getActionContext().get("resourceName"));
		return this.SUCCESS;
	}
	public String getAddPageWidget(){		
		this.getActionContext().set("resourceName", this.getActionContext().get("resourceName"));
		return this.SUCCESS;
	}
	public String addPageWidget(){
		PageCustomizeBiz biz=new PageCustomizeBiz();
		biz.addPageWidget(this.getPageWidget(), this.getActionContext().get("resourceName"));
		return this.execute();
	}
	public String getUpdatePageWidget(){
		PageCustomizeBiz biz=new PageCustomizeBiz();
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("key");
		String pageCode=this.getActionContext().get("pageCode");	
		PageWidget vo=biz.getPageWidget(resourceName, pageKey, pageCode);
		this.setPageWidget(vo);
		this.getActionContext().set("resourceName",resourceName);
		return this.SUCCESS;
	}
	public String updatePageWidget(){
		String resourceName=this.getActionContext().get("resourceName");
		PageCustomizeBiz biz=new PageCustomizeBiz();
		biz.updatePageWidget(resourceName, pageWidget);
		return this.getUpdatePageWidget();
	}
	public String deletePageWidget(){
		PageCustomizeBiz biz=new PageCustomizeBiz();
		String[] idArr=this.getActionContext().getArray("id");
		String resourceName=this.getActionContext().get("resourceName");	
		biz.deletePageWidget(idArr, resourceName);
		return this.execute();
	}
	public String getPageCustomize(){
		String resourceName=this.getActionContext().get("resourceName");
		String pageKey=this.getActionContext().get("pageKey");
		String pageCode=this.getActionContext().get("pageCode");		
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);		
		DocObject docObject=DocFactory.getInstace().getDocObject(file.getPath());
		Element pageE=docObject.getDocElement(pageKey);
		if(pageE!=null){			
			Element searchE=pageE.getChild("SEARCH");
			Element gridE=pageE.getChild("GRID");
			Element formE=pageE.getChild("FORM");
			Element scriptE=pageE.getChild("SCRIPT");			
			if(searchE!=null){
				String searchKey=StringFormat.replaceNull(searchE.getAttributeValue("EID"));
				this.getActionContext().set("searchKey",searchKey );
				Element searchColumnSetE=searchE.getChild("COLUMNSET");
				if(searchColumnSetE!=null){
					String searchColumnSetKey=StringFormat.replaceNull(searchColumnSetE.getAttributeValue("EID"));
					this.getActionContext().set("searchColumnSetKey", searchColumnSetKey);
				}
			}
			if(gridE!=null){				
				String gridKey=StringFormat.replaceNull(gridE.getAttributeValue("EID"));		
				this.getActionContext().set("gridKey",gridKey );				
				Element gridDataSetE=gridE.getChild("DATASET");
				if(gridDataSetE!=null){
					String gridDataSetKey=StringFormat.replaceNull(gridDataSetE.getAttributeValue("EID"));
					this.getActionContext().set("gridDataSetKey", gridDataSetKey);					
					Element statmentE=gridDataSetE.getChild("STATEMENT");
					if(statmentE!=null){
						String gridStatementKey=StringFormat.replaceNull(statmentE.getAttributeValue("EID"));
						this.getActionContext().set("gridStatementKey", gridStatementKey);
					}
					Element interfaceE=gridDataSetE.getChild("INTERFACE");
					if(interfaceE!=null){
						String gridInterfaceKey=StringFormat.replaceNull(interfaceE.getAttributeValue("EID"));
						this.getActionContext().set("gridInterfaceKey", gridInterfaceKey);
					}				
				}			
				Element gridColumnSetE=gridE.getChild("COLUMNSET");
				if(gridColumnSetE!=null){
					String gridColumnSetKey=StringFormat.replaceNull(gridColumnSetE.getAttributeValue("EID"));
					this.getActionContext().set("gridColumnSetKey", gridColumnSetKey);
				}
				Element toolbarsE=gridE.getChild("ACTIONS");
				if(toolbarsE!=null){
					String gridToolbarsKey=StringFormat.replaceNull(toolbarsE.getAttributeValue("EID"));
					this.getActionContext().set("gridToolbarsKey", gridToolbarsKey);
				}
			}
			if(formE!=null){
				String formKey=StringFormat.replaceNull(formE.getAttributeValue("EID"));		
				this.getActionContext().set("formKey",formKey );
				Element formDataSetE=formE.getChild("DATASET");
				if(formDataSetE!=null){
					String formDataSetKey=StringFormat.replaceNull(formDataSetE.getAttributeValue("EID"));
					this.getActionContext().set("formDataSetKey", formDataSetKey);					
					Element statmentE=formDataSetE.getChild("STATEMENT");
					if(statmentE!=null){
						String formStatementKey=StringFormat.replaceNull(statmentE.getAttributeValue("EID"));
						this.getActionContext().set("formStatementKey", formStatementKey);
					}
					Element interfaceE=formDataSetE.getChild("INTERFACE");
					if(interfaceE!=null){
						String formInterfaceKey=StringFormat.replaceNull(interfaceE.getAttributeValue("EID"));
						this.getActionContext().set("formInterfaceKey", formInterfaceKey);
					}
					
				}			
				Element formColumnSetE=formE.getChild("COLUMNSET");
				if(formColumnSetE!=null){
					String formColumnSetKey=StringFormat.replaceNull(formColumnSetE.getAttributeValue("EID"));
					this.getActionContext().set("formColumnSetKey", formColumnSetKey);
				}
				Element toolbarsE=formE.getChild("ACTIONS");
				if(toolbarsE!=null){
					String formToolbarsKey=StringFormat.replaceNull(toolbarsE.getAttributeValue("EID"));
					this.getActionContext().set("formToolbarsKey", formToolbarsKey);
				}
			}
			if(scriptE!=null){
				String scriptKey=StringFormat.replaceNull(scriptE.getAttributeValue("EID"));		
				this.getActionContext().set("scriptKey",scriptKey);
			}
		}
		this.getActionContext().set("resourceName",resourceName );
		this.getActionContext().set("pageKey", pageKey);
		this.getActionContext().set("pageCode", pageCode);
		return this.SUCCESS;
	}
}
