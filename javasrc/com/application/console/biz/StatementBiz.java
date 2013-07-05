package com.application.console.biz;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import org.jdom.Element;
import org.jdom.CDATA;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.util.StringFormat;

/**
 * 语句对象业务类
 * @author wangyf
 * @version 1.0
 */
public class StatementBiz {
	
	public String[] getGridStatmentBase(String resourceName,String statementKey){
		String[] arr=new String[3];
		arr[0]="";
		arr[1]="";
		arr[2]="";
		if("".equals(resourceName)||"".equals(statementKey)) return arr;
		String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return arr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);
		if(statementE==null) return arr;
		arr[0]=StringFormat.replaceNull(statementE.getAttributeValue("parameterClass"));
		arr[1]=StringFormat.replaceNull(statementE.getAttributeValue("returnClass"));
		arr[2]=StringFormat.replaceNull(statementE.getAttributeValue("resultMap"));
		return arr;		
	}
    public void saveGridStatementBase(String resourceName,String statementKey,String parameterClass,String returnClass,String resultMap){
    	if("".equals(resourceName)||"".equals(statementKey)) return;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);
		if(statementE==null) return;
		statementE.setAttribute("parameterClass", StringFormat.replaceNull(parameterClass));
		statementE.setAttribute("returnClass", StringFormat.replaceNull(returnClass));
		statementE.setAttribute("resultMap", StringFormat.replaceNull(resultMap));
		doc.saveDoc();
    }
    public String[] getGridStatementSelect(String resourceName,String statementKey){
    	String[] arr=new String[2];
    	arr[0]="";
    	arr[1]="";
    	if("".equals(resourceName)||"".equals(statementKey)) return arr;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return arr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);
		if(statementE==null) return arr;
		Element selectE=statementE.getChild("SELECT");
		if(selectE==null){
			selectE=new Element("SELECT");
			doc.addDocElement(statementKey, selectE);				
			doc.saveDoc();
		}
		String selectKey=StringFormat.replaceNull(selectE.getAttributeValue("EID"));
		String selectText=StringFormat.replaceNull(selectE.getText());
		arr[0]=selectKey;
		arr[1]=selectText;
		return arr;
    }
    public void saveGridStatementSelect(String resourceName,String selectKey,String selectText){
    	if("".equals(resourceName)||"".equals(selectKey)) return;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element selectE=doc.getDocElement(selectKey);
		if(selectE==null) return;
		doc.addDocElementCDATA(selectKey, selectText);
		doc.saveDoc();
    }
    public List getGridStatementWhere(String resourceName,String statementKey){
    	List wheres=new ArrayList();
    	if("".equals(resourceName)||"".equals(statementKey)) return wheres;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);		
		if(!file.exists()) return wheres;	
		if(statementE==null) return wheres;
		Element wheresE=statementE.getChild("WHERES");
		if(wheresE==null){
			wheresE=new Element("WHERES");
			doc.addDocElement(statementKey, wheresE);				
			doc.saveDoc();
		}
		List list=wheresE.getChildren("WHERE");
		if(list==null) return wheres;
		for(int i=0;i<list.size();i++){
			Element whereE=(Element)list.get(i);
			Map where=new HashMap();
			where.put(StringFormat.replaceNull(whereE.getAttributeValue("loadCondition")),StringFormat.replaceNull(whereE.getText()));
			wheres.add(where);
		}
		return wheres;
    }
    public void saveGridStatementWhere(String resourceName,String statementKey,String[] loadConditionArr,String[] whereTextArr){
    	if("".equals(resourceName)||"".equals(statementKey)) return;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);		
		if(!file.exists()) return;	
		if(statementE==null) return;
		Element wheresE=statementE.getChild("WHERES");
		if(wheresE==null) return;
		wheresE.removeChildren("WHERE");
		if(loadConditionArr!=null){			
			for(int i=0;i<loadConditionArr.length;i++){
				Element whereE=new Element("WHERE");
				whereE.setAttribute("loadCondition", StringFormat.replaceNull(loadConditionArr[i]));
				CDATA CDATA=new CDATA(StringFormat.replaceNull(whereTextArr[i]));
				whereE.addContent(CDATA);
				doc.addDocElement(wheresE, whereE);
			}				
		}
		doc.saveDoc();
    }
    public String[] getGridStatementOrderby(String resourceName,String statementKey){
    	String[] arr=new String[2];
    	arr[0]="";
    	arr[1]="";
    	if("".equals(resourceName)||"".equals(statementKey)) return arr;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return arr;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);
		if(statementE==null) return arr;
		Element orderbyE=statementE.getChild("ORDERBY");
		if(orderbyE==null){
			orderbyE=new Element("ORDERBY");
			doc.addDocElement(statementKey, orderbyE);				
			doc.saveDoc();
		}
		String orderbyKey=StringFormat.replaceNull(orderbyE.getAttributeValue("EID"));
		String orderbyText=StringFormat.replaceNull(orderbyE.getText());
		arr[0]=orderbyKey;
		arr[1]=orderbyText;
		return arr;
    }
    public void saveGridStatementOrderby(String resourceName,String orderbyKey,String orderbyText){
    	if("".equals(resourceName)||"".equals(orderbyKey)) return;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);
		if(!file.exists()) return;	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element orderbyE=doc.getDocElement(orderbyKey);
		if(orderbyE==null) return;
		doc.addDocElementCDATA(orderbyKey, orderbyText);
		doc.saveDoc();
    }
    public List getGridStatementInput(String resourceName,String statementKey){
    	List setbeans=new ArrayList();
    	if("".equals(resourceName)||"".equals(statementKey)) return setbeans;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);		
		if(!file.exists()) return setbeans;	
		if(statementE==null) return setbeans;
		Element inputbeanE=statementE.getChild("INPUTBEAN");
		if(inputbeanE==null){
			inputbeanE=new Element("INPUTBEAN");
			doc.addDocElement(statementKey, inputbeanE);				
			doc.saveDoc();
		}
		List list=inputbeanE.getChildren("SETBEAN");
		if(list==null) return setbeans;
		for(int i=0;i<list.size();i++){
			Element setBeanE=(Element)list.get(i);
			Map setbean=new HashMap();
			setbean.put(StringFormat.replaceNull(setBeanE.getAttributeValue("name")), setBeanE.getAttributeValue("value"));
			setbeans.add(setbean);
		}
		return setbeans;
    }
    public void saveGridStatementInput(String resourceName,String statementKey,String[] nameArr,String[] valueArr){
    	if("".equals(resourceName)||"".equals(statementKey)) return;
    	String fullPath=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+resourceName;
		File file=new File(fullPath);	
		DocObject doc=DocFactory.getInstace().getDocObject(file.getPath());
		Element statementE=doc.getDocElement(statementKey);		
		if(!file.exists()) return;	
		if(statementE==null) return;
		Element inputbeanE=statementE.getChild("INPUTBEAN");
		if(inputbeanE==null) return;
		inputbeanE.removeChildren("SETBEAN");
		if(nameArr!=null){			
			for(int i=0;i<nameArr.length;i++){
				Element setBeanE=new Element("SETBEAN");
				setBeanE.setAttribute("name", StringFormat.replaceNull(nameArr[i]));
				setBeanE.setAttribute("value", StringFormat.replaceNull(valueArr[i]));
				doc.addDocElement(inputbeanE, setBeanE);
			}				
		}
		doc.saveDoc();
    }
}
