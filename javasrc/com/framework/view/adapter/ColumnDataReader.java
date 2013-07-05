package com.framework.view.adapter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.support.plugin.DataDictPlugin;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.adapter.ColumnObject;
import com.framework.view.bean.BeanMgr;
import com.framework.view.data.CacheData;
import com.framework.view.data.DataTypeConverter;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;

/**
 * 列模型数据读取器类
 * @author wangyf
 * @version 1.0
 */
public class ColumnDataReader {
	private RequestHash reh;
	
	public ColumnDataReader(RequestHash reh){
		this.reh=reh;
	}
	
	public ColumnObject read(ColumnObject column,Object obj){
		if(column==null) return null;
		ColumnObject tmpColumn=column.copy(column);
		this.loadColumnValue(obj, tmpColumn);		
		this.loadColumnFormula(obj, tmpColumn);
		this.loadColumnHref(obj, tmpColumn);
		this.loadColumnOnclick(obj, tmpColumn);
		return tmpColumn;
	}
	public void loadColumnValue(Object obj,ColumnObject column){
		if(column==null) return;
		column.setValue(this.formatRequestValue(this.formatBeanValue(obj, StringFormat.replaceNull(column.getValue()))));	
	}
	public String formatBeanValue(Object obj,String s){
		BeanMgr beanMgr=new BeanMgr();			
		while(s.indexOf("obj.get(")!=-1){			
			if(obj!=null){
				String property=RequestUtil.getBeanName(s);
				String target="obj.get("+property+")";		
				if(beanMgr.getBean(obj, property) instanceof java.sql.Timestamp){
					String replacement=DataTypeConverter.format((Timestamp)beanMgr.getBean(obj, property));
					s=StringFormat.replace(s, target, replacement);	
				}else if(beanMgr.getBean(obj, property) instanceof java.util.Date){					
					String replacement=DataTypeConverter.format((Date)beanMgr.getBean(obj, property));
					s=StringFormat.replace(s, target, replacement);	
				}else{				
					String replacement=String.valueOf(beanMgr.getBean(obj, property));	
					s=StringFormat.replace(s, target, replacement);						
				}
			}else
				s="";
		}
		return s;
	}
	public String formatRequestValue(String s){
		while(s.indexOf("fun.get(")!=-1){
			String replacement=RequestUtil.getRequestParameterName(s);
		    String target=RequestUtil.getRequestParamNameStr(s);	 
	        replacement=reh.get(replacement);        
	        s=StringFormat.replace(s,target, replacement);		
		}	
		if(s.indexOf("fun.getSession")!=-1){
			s=RequestUtil.formatSession(reh.getSessionHash(), s);			
		}	
		return s;
	}
	
	public void loadColumnFormula(Object obj,ColumnObject column){
		if(column==null) return;	
		String formula=column.getRefFormula();		
		if("".equals(formula)) return;			
		if("hidden".equals(column.getDocType())||"text".equals(column.getDocType())){			
			//普通文本框值或输入文本框
			if(formula.indexOf("fun.getDict")!=-1){				
				String[] strArray=StringFormat.split(RequestUtil.getFormulaParameter(formula), ',');				
				if(strArray==null) return;						
				if(strArray.length==1){
					//column.setValue(DataDict.getDataDict(strArray[0], column.getValue()));	
					column.setDocExtend(CacheData.getDataDict(strArray[0], column.getValue()));
				}else if(strArray.length==2){										
					if("static".equals(strArray[0])){
						//column.setValue(DataDict.getDataDict(strArray[1], column.getValue()));	
						column.setDocExtend(CacheData.getDataDict(strArray[1], column.getValue()));
					}
				}				
			}else{							
				//formula=this.formatRequestValue(obj,formula);										
				List list=null;
				//list=dataDict.getFormulaData(formula);			
				if(list==null) return;
				for(int i=0;i<list.size();i++){
					Object[] objArray=(Object[])list.get(i);
					if(objArray[0].equals(column.getValue())){
						column.setValue(String.valueOf(objArray[1]));
						break;
					}
				}				
			}			
		}else if("select".equals(column.getDocType())){
			//选择框集合值
			if(formula.indexOf("fun.getDict")!=-1){				
				String[] strArray=StringFormat.split(RequestUtil.getFormulaParameter(formula), ',');
				if(strArray==null) return;
				List list=null;
				if(strArray.length==1){					
					list=(List)(CacheData.datadicts.get(strArray[0]));							
					column.setOption(list);
				}else if(strArray.length==2){
					if("static".equals(strArray[0])){
						list=(List)(CacheData.datadicts.get(strArray[1]));					
						column.setOption(list);
					}					
				}			
			}else{					
				//formula=this.formatRequestValue(obj,formula);
				List list=null;
				//list=dataDict.getFormulaData(formula);					
				if(list==null) return;
				List optionList=new ArrayList();
				for(int i=0;i<list.size();i++){
					Object[] objArray=(Object[])list.get(i);
					Map options=new HashMap();
					options.put(objArray[0], objArray[1]);
					optionList.add(options);
				}
				column.setOption(optionList);				
			}
		}else if("multiSelect".equals(column.getDocType())){
			if(formula.indexOf("fun.getDict")!=-1){
				String[] strArray=StringFormat.split(RequestUtil.getFormulaParameter(formula), ',');
				if(strArray==null) return;
				List list=null;
				if(strArray.length==1){
					list=(List)(CacheData.datadicts.get(strArray[0]));
					column.setOption(list);
				}else if(strArray.length==2){
					if("static".equals(strArray[0])){
						list=(List)(CacheData.datadicts.get(strArray[1]));
						column.setOption(list);
					}				
				}			
			}else{				
				//formula=this.formatRequestValue(obj,formula);
				List list=null;
				//list=dataDict.getFormulaData(formula);					
				if(list==null) return;
				List optionList=new ArrayList();
				for(int i=0;i<list.size();i++){
					Object[] objArray=(Object[])list.get(i);
					Map options=new HashMap();
					options.put(objArray[0], objArray[1]);
					optionList.add(options);
				}
				column.setOption(optionList);
				
			}
		}else if("checkbox".equals(column.getDocType())){
			//复选框值			
			if(formula.indexOf("obj.get(")!=-1){
				if(obj!=null){
					BeanMgr beanMgr=new BeanMgr();			
					String property=RequestUtil.getBeanName(formula);
					String target="obj.get("+property+")";			
					String replacement=String.valueOf(beanMgr.getBean(obj, property));	
					formula=StringFormat.replace(formula, target, replacement);	
					column.setChecked(formula);
				} 					
			}			
		}
		column.setRefFormula(formula);
	}
	public void loadColumnHref(Object obj,ColumnObject column){
		if(column==null) return;	
		String href=column.getDocHref();
		href=this.formatBeanValue(obj, href);
		href=this.formatRequestValue(href);
		column.setDocHref(href);
	}
	public void loadColumnOnclick(Object obj,ColumnObject column){
		if(column==null) return;	
		String onclick=column.getDocOnclick();
		onclick=this.formatBeanValue(obj, onclick);
		onclick=this.formatRequestValue(onclick);
		column.setDocOnclick(onclick);
	}
}
