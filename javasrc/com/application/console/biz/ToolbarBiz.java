package com.application.console.biz;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import org.jdom.Element;

import com.application.console.model.SetBean;
import com.application.console.model.Toolbar;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;
/**
 * 工具条业务类
 * @author wangyf
 * @version 1.0
 */
public class ToolbarBiz {
	
	public List getToolbarList(String resourceName,String toolbarsKey){
		List toolbars=new ArrayList();
		if("".equals(resourceName)||"".equals(toolbarsKey)) return toolbars;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return toolbars;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarsE=doc.getDocElement(toolbarsKey);			
		List list=toolbarsE.getChildren("ACTION");
		if(list==null) return toolbars;
		for(int i=0;i<list.size();i++){
			Element actionE=(Element)list.get(i);
			Toolbar toolbar=new Toolbar();
			toolbar.setKey(StringFormat.replaceNull(actionE.getAttributeValue("EID")));
			toolbar.setCode(StringFormat.replaceNull(actionE.getAttributeValue("code")));
			toolbar.setName(StringFormat.replaceNull(actionE.getAttributeValue("name")));		
			toolbar.setFreshOpener(StringFormat.replaceNull(actionE.getAttributeValue("freshOpener")));
			toolbar.setCloseWindow(StringFormat.replaceNull(actionE.getAttributeValue("closeWindow")));
			toolbars.add(toolbar);
		}
		return toolbars;		
	}
	public void saveToolbarList(String resourceName,String toolbarsKey,String[] toolbarKeyArr,String[] codeArr,String[] nameArr){
		if("".equals(resourceName)||"".equals(toolbarsKey)) return;			
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarsE=doc.getDocElement(toolbarsKey);
		if(toolbarsE==null) return;		
		List list=new ArrayList();
		if(nameArr!=null){					
			for(int i=0;i<nameArr.length;i++){					
				Element toolbarE=doc.getDocElement(toolbarKeyArr[i]);					
				if(toolbarE!=null){					
					toolbarE.setAttribute("code", codeArr[i]);
					toolbarE.setAttribute("name", nameArr[i]);						
					list.add(toolbarE);
				}else{
					toolbarE=new Element("ACTION");
					toolbarE.setAttribute("code", codeArr[i]);
					toolbarE.setAttribute("name", nameArr[i]);	
					list.add(toolbarE);					
				}				
			}			
		}			
		toolbarsE.removeContent();	
		for(int i=0;i<list.size();i++){
			Element subE=(Element)list.get(i);
			doc.addDocElement(toolbarsE, subE);
		}		
		doc.saveDoc();			
	}
	
	public void saveToolbarList(String resourceName,String toolbarsKey,String[] toolbarKeyArr,String[] codeArr,String[] nameArr,String[] freshOpenerArr,String[] closeWindowArr){
		if("".equals(resourceName)||"".equals(toolbarsKey)) return;			
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarsE=doc.getDocElement(toolbarsKey);
		if(toolbarsE==null) return;
		List list=new ArrayList();	
		if(nameArr!=null){					
			for(int i=0;i<nameArr.length;i++){						
				Element toolbarE=doc.getDocElement(toolbarKeyArr[i]);					
				if(toolbarE!=null){					
					toolbarE.setAttribute("code", codeArr[i]);
					toolbarE.setAttribute("name", nameArr[i]);		
					toolbarE.setAttribute("freshOpener", freshOpenerArr[i]);
					toolbarE.setAttribute("closeWindow", closeWindowArr[i]);					
					list.add(toolbarE);
				}else{
					toolbarE=new Element("ACTION");
					toolbarE.setAttribute("code", codeArr[i]);
					toolbarE.setAttribute("name", nameArr[i]);		
					toolbarE.setAttribute("freshOpener", freshOpenerArr[i]);
					toolbarE.setAttribute("closeWindow", closeWindowArr[i]);
					list.add(toolbarE);			
				}				
			}			
		}		
		toolbarsE.removeContent();	
		for(int i=0;i<list.size();i++){
			Element subE=(Element)list.get(i);
			doc.addDocElement(toolbarsE, subE);
		}
		doc.saveDoc();			
	}
	public void addToolbar(String resourceName,String toolbarKey,Toolbar toolbar){
		
	}
	
	public Toolbar getToolbarBase(String resourceName,String toolbarKey){
		Toolbar toolbar=new Toolbar();
    	if("".equals(resourceName)||"".equals(toolbarKey)) return toolbar;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return toolbar;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);	
		if(toolbarE==null) return toolbar;
		toolbar.setLoadCondition(StringFormat.replaceNull(toolbarE.getAttributeValue("loadCondition")));
		toolbar.setExecuteType(StringFormat.replaceNull(toolbarE.getAttributeValue("executeType")));
		toolbar.setHref(StringFormat.replaceNull(toolbarE.getAttributeValue("href")));		
		toolbar.setOnclick(StringFormat.replaceNull(toolbarE.getAttributeValue("onclick")));		
    	return toolbar;
    }
    public void saveToolbarBase(String resourceName,String toolbarKey,Toolbar toolbar){    
    	if("".equals(resourceName)||"".equals(toolbarKey)||toolbar==null) return;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);	
		if(toolbarE==null) return;
		toolbarE.setAttribute("loadCondition", StringFormat.replaceNull(toolbar.getLoadCondition()));
		toolbarE.setAttribute("executeType", StringFormat.replaceNull(toolbar.getExecuteType()));
		toolbarE.setAttribute("href", StringFormat.replaceNull(toolbar.getHref()));
		toolbarE.setAttribute("onclick", StringFormat.replaceNull(toolbar.getOnclick()));
		doc.saveDoc();
    }
    public String[] getToolbarStatement(String resourceName,String toolbarKey){
    	String[] statementArr=new String[4];
    	statementArr[0]="";
    	statementArr[1]="";
    	statementArr[2]="";
    	statementArr[3]="";
    	if("".equals(resourceName)||"".equals(toolbarKey)) return statementArr;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return statementArr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);	
		if(toolbarE==null) return statementArr;
		Element statementE=toolbarE.getChild("STATEMENT");
		if(statementE==null){
			statementE=new Element("STATEMENT");
			doc.addDocElement(toolbarE, statementE);				
			doc.saveDoc();
		}
		statementArr[0]=StringFormat.replaceNull(statementE.getAttributeValue("EID"));
		statementArr[1]=StringFormat.replaceNull(statementE.getAttributeValue("parameterClass"));
		statementArr[2]=StringFormat.replaceNull(statementE.getAttributeValue("resultMap"));
		statementArr[3]=StringFormat.replaceNull(statementE.getText());		
    	return statementArr;
    }
    public void saveToolbarStatement(String resourceName,String statementKey,String statementText,String parameterClass,String resultMap){
    	if("".equals(resourceName)||"".equals(statementKey)) return;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());		
		Element statementE=doc.getDocElement(statementKey);
		if(statementE==null) return;
		statementE.setAttribute("parameterClass", StringFormat.replaceNull(parameterClass));
		statementE.setAttribute("resultMap", StringFormat.replaceNull(resultMap));
		doc.addDocElementCDATA(statementKey, statementText);
		doc.saveDoc();
    }
    public List getToolbarInput(String resourceName,String toolbarKey){
    	List setbeans=new ArrayList();
    	if("".equals(resourceName)||"".equals(toolbarKey)) return setbeans;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return setbeans;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);	
		if(toolbarE==null) return setbeans;
		Element inputbeanE=toolbarE.getChild("INPUTBEAN");
		if(inputbeanE==null){
			inputbeanE=new Element("INPUTBEAN");
			doc.addDocElement(toolbarE, inputbeanE);				
			doc.saveDoc();
		}
		List list=inputbeanE.getChildren("SETBEAN");
		if(list==null) return setbeans;
		for(int i=0;i<list.size();i++){
			Element setbeanE=(Element)list.get(i);
			SetBean setBean=new SetBean();
			setBean.setKey(StringFormat.replaceNull(setbeanE.getAttributeValue("EID")));
			setBean.setName(StringFormat.replaceNull(setbeanE.getAttributeValue("name")));
			setBean.setValue(StringFormat.replaceNull(setbeanE.getAttributeValue("value")));
			setbeans.add(setBean);
		}
    	return setbeans;
    }
    public void saveToolbarInput(String resourceName,String toolbarKey,String[] nameArr,String[] valueArr){
    	if("".equals(resourceName)||"".equals(toolbarKey)) return;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);
		if(toolbarE==null) return;
		Element inputbeanE=toolbarE.getChild("INPUTBEAN");
		if(inputbeanE==null) return;
		inputbeanE.removeChildren("SETBEAN");
		if(nameArr!=null){
			for(int i=0;i<nameArr.length;i++){
				Element setbeanE=new Element("SETBEAN");
				setbeanE.setAttribute("name", StringFormat.replaceNull(nameArr[i]));
				setbeanE.setAttribute("value", StringFormat.replaceNull(valueArr[i]));
				doc.addDocElement(inputbeanE, setbeanE);
			}
		}
		doc.saveDoc();		
    }
    public String[] getToolbarInterface(String resourceName,String toolbarKey){
    	String[] interfaceArr=new String[2];
    	interfaceArr[0]="";
		interfaceArr[1]="";
    	if("".equals(resourceName)||"".equals(toolbarKey)) return interfaceArr;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return interfaceArr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);	
		if(toolbarE==null) return interfaceArr;
		Element interfaceE=toolbarE.getChild("INTERFACE");
		if(interfaceE==null){
			interfaceE=new Element("INTERFACE");
			doc.addDocElement(toolbarE, interfaceE);
			doc.saveDoc();
		}		
		interfaceArr[0]=StringFormat.replaceNull(interfaceE.getAttributeValue("className"));
		interfaceArr[1]=StringFormat.replaceNull(interfaceE.getAttributeValue("methodName"));
		return interfaceArr;
    }
    public void saveToolbarInterface(String resourceName,String toolbarKey,String className,String methodName){    	
    	if("".equals(resourceName)||"".equals(toolbarKey)) return;	
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element toolbarE=doc.getDocElement(toolbarKey);	
		if(toolbarE==null) return;
		Element interfaceE=toolbarE.getChild("INTERFACE");
		if(interfaceE==null) return;
		interfaceE.setAttribute("className", StringFormat.replaceNull(className));
		interfaceE.setAttribute("methodName", StringFormat.replaceNull(methodName));
		doc.saveDoc();
    }
}
