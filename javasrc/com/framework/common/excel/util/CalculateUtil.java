package com.framework.common.excel.util;
import java.util.Map;

import com.powerbosframework.util.StringUtil;
/**
 * 电子表格计算帮助类
 * @author wangyf
 * @version 1.0
 */
public class CalculateUtil {	
	private static final String DEFAULT_SHEET="default"; 
	/**
	 * 计算单元格加减总和
	 * @param s
	 * @param datas
	 * @return
	 */
	public int calculateSum(String s,Map datas){		
		int s1=0;
		String s2="";//运算符左右条件字符串描述
		String s3="";//单元格字符串描述
		String s4="";//单元格工作表描述
		if(s==null||"".equals(s)) return 0;			
		int startIndex=0;
		int endIndex=0;				
		char endOperator='+';		
		for(int i=0;i<s.length();i++){						
			if(s.charAt(i)=='+'||s.charAt(i)=='-'){				
				endIndex=i;		
				if(s.substring(startIndex, endIndex)!=null&&!"".equals(s.substring(startIndex, endIndex))){
					s3=s.substring(startIndex, endIndex);						
					if(s3.indexOf("{")!=-1&&s3.indexOf("}")!=-1){				
						s4=s3.substring(s3.indexOf("{")+1, s3.indexOf("}"));				
						if(s4!=null&&!"".equals(s4)){ 
							Map data=(Map)datas.get(s4);				
							s4=s3.substring(s3.indexOf("}")+1, s3.length());					
							if(data!=null){
								if(data.containsKey(s4))
									s2=String.valueOf(data.get(s4));								
							}					
						}
					}else{
						Map data=(Map)datas.get(this.DEFAULT_SHEET);		
						if(data!=null){
							if(data.containsKey(s3))
								s2=String.valueOf(data.get(s3));
						}						
					}					
				}
					
				if(s2!=null&&!"".equals(s2)&&!"null".equals(s2)&&StringUtil.isNumeric(s2)){
					if(endOperator=='+'){
						s1=s1+Integer.parseInt(s2);
					}else if(endOperator=='-'){
						s1=s1-Integer.parseInt(s2);
					}
				}								
				startIndex=endIndex+1;		
				s2="";			
				endOperator=s.charAt(i);
			}				
		}			
		
		if(s.substring(startIndex, s.length())!=null&&!"".equals(s.substring(startIndex, s.length()))){
			s3=s.substring(startIndex, s.length());
			if(s3.indexOf("{")!=-1&&s3.indexOf("}")!=-1){				
				s4=s3.substring(s3.indexOf("{")+1, s3.indexOf("}"));				
				if(s4!=null&&!"".equals(s4)){ 
					Map data=(Map)datas.get(s4);				
					s4=s3.substring(s3.indexOf("}")+1, s3.length());					
					if(data!=null){
						if(data.containsKey(s4))
							s2=String.valueOf(data.get(s4));						
					}					
				}
			}else{
				Map data=(Map)datas.get(this.DEFAULT_SHEET);
				if(data!=null){
					if(data.containsKey(s3))
						s2=String.valueOf(data.get(s3));	
				}				
			}				
		}	
		if(s2!=null&&!"".equals(s2)&&!"null".equals(s2)&&StringUtil.isNumeric(s2)){
			if(endOperator=='+')
				s1=s1+Integer.parseInt(s2);
			else if(endOperator=='-'){
				s1=s1-Integer.parseInt(s2);
			}			
		}			
		return s1;
	}

}
