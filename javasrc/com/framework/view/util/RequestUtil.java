package com.framework.view.util;
import java.util.Map;

import com.framework.view.util.StringFormat;
/**
 * 请求帮助类
 * @author wangyf
 * @version 1.0
 */
public class RequestUtil {

	/**
	 * 返回数组对象索引编号
	 * @param s
	 * @return
	 */
	public static int getObjectIndex(String s){
		int index;		
		int start=0;
		int end=0;	
		//得到请求对象索引,起始位置从零开始:obj[0]
		if(s.indexOf("obj[")!=-1){
			s=s.substring(s.indexOf("obj[")+4, s.length());
			end=s.indexOf("]");
		}
		index=Integer.parseInt(s.substring(start, end));
		return index;
	}
	/**
	 * 返回BEAN名称
	 * @param s
	 * @return
	 */
	public static String getBeanName(String s){
		String s1="";		
		int start=0;
		int end=0;		
		//得到请求BEAN:obj.get(name)
		if(s.indexOf("obj.get(")!=-1){
			s=s.substring(s.indexOf("obj.get(")+8, s.length());
			end=s.indexOf(")");	
		}
		s1=s.substring(start, end);		
		return s1;
	}
	/**
	 * 返回列索引
	 * @param s
	 * @return
	 */
	public static String getColumnIndex(String s){
		String s1="";		
		int start=0;
		int end=0;		
		//得到请求列索引:fun.getString(index)
		if(s.indexOf("fun.getString(")!=-1){
			s=s.substring(s.indexOf("fun.getString(")+14, s.length());
			end=s.indexOf(")");	
		}
		s1=s.substring(start, end);		
		return s1;
	}
	/**
	 * 返回字符串的枚举参数
	 * @param s
	 * @return
	 */
	public static String getEnumParameter(String s){
		String ret="";		
		int start=0;
		int end=0;				
		if(s.indexOf("enum(")!=-1){			
			s=s.substring(s.indexOf("enum(")+5, s.length());
			end=s.indexOf(")");		
		}	       		
		ret=StringFormat.replaceNull(s.substring(start, end));		
		return ret;
	}
	/**
	 * 返回规则的参数数组
	 * fun.getWordbook(static,001)
	 * @param s
	 * @return
	 */
	public static String getFormulaParameter(String s){
		String s1="";
		int start=0;
		int end=0;			
		if(s==null) return null;
		if(s.indexOf("fun.getDataList(")!=-1){
			s=s.substring(s.indexOf("fun.getDataList(")+16, s.length());
			end=s.indexOf(")");
		}else if(s.indexOf("fun.getDict(")!=-1){
			s=s.substring(s.indexOf("fun.getDict(")+12, s.length());
			end=s.indexOf(")");
		}
		s1=s.substring(start, end);		
		return s1;
	}
	/**
	 * 返回请求参数名
	 * @param s
	 * @return s1
	 */
	public static String getRequestParameterName(String s){		
		String s1="";		
		int start=0;
		int end=0;		
		//得到请求访求为:fun.get(s)
		if(s.indexOf("fun.get(")!=-1){			
			s=s.substring(s.indexOf("fun.get(")+8, s.length());
			end=s.indexOf(")");		
		}	
        //得到请求访求为:fun.getArray(s)
		if(s.indexOf("fun.getArray(")!=-1){			
			s=s.substring(s.indexOf("fun.getArray(")+13, s.length());
			end=s.indexOf(")");		
		}			
		s1=s.substring(start, end);		
		return s1;
	}
	/**
	 * 返回请求参数字符串
	 * @param str
	 * @return s
	 */
	public static String getRequestParamNameStr(String s){
		String s1="";
		int start=0;
		int end=0;		
		if(s.indexOf("fun.get(")!=-1){			
			s=s.substring(s.indexOf("fun.get("), s.length());
			end=s.indexOf(")")+1;		
		}
		if(s.indexOf("fun.get(")!=-1){
			s=s.substring(s.indexOf("fun.get("), s.length());
			end=s.indexOf(")")+1;
		}		
		s1=s.substring(start, end);
		return s1;
	}
	/**
	 * 返回表态参数请求字符串
	 * @param s
	 * @return
	 */
	public static String getSessionParamNameStr(String s){
		String s1="";
		int start=0;
		int end=0;	
		if(s.indexOf("fun.getSession(")!=-1){
			s=s.substring(s.indexOf("fun.getSession("), s.length());
			end=s.indexOf(")")+1;
		}
		s1=s.substring(start, end);
		return s1;
	}
	/**
	 * 返回Session参数名
	 * @param s
	 * @return s1
	 */
	public static String getSessionParameterName(String s){
		String s1="";
		int start=0;
		int end=0;
		//得到Session参数为:fun.getSession(s)
		if(s.indexOf("fun.getSession(")!=-1){			
			s=s.substring(s.indexOf("fun.getSession(")+15, s.length());			
			end=s.indexOf(")");
		}		
		s1=s.substring(start, end);
		return s1;
	}
	/**
	 * 格式化Session值
	 * @param session
	 * @param s
	 * @return
	 */
	public static String formatSession(Map session,String s){
		String s1="";
		String target="";
		String replacement="";					
		if(s.indexOf("fun.getSession(FORM)")!=-1){
			target="fun.getSession(FORM)";
			replacement=String.valueOf(session.get("FORM"));				
		}else if(s.indexOf("fun.getSession(DYNPAGE)")!=-1){					
			target="fun.getSession(DYNPAGE)";
			replacement=String.valueOf(session.get("DYNPAGE"));					
		}else if(s.indexOf("fun.getSession(PATH)")!=-1){
			target="fun.getSession(PATH)";
			replacement=String.valueOf(session.get("PATH"));		
		}else if(s.indexOf("fun.getSession(ROWNUM)")!=-1){
			target="fun.getSession(ROWNUM)";
			replacement=String.valueOf(session.get("ROWNUM"));					
		}else{		
			target=String.valueOf(getSessionParamNameStr(s));				
			replacement=StringFormat.replaceNull(String.valueOf(session.get(getSessionParameterName(s))));			
		}			
		s1=StringFormat.replace(s, target, StringFormat.replaceNull(replacement));		
		return s1;
	}
	
	public static void main(String[] args){		
		
	}
}
