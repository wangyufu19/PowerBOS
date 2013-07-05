package com.sqlMap.util;

import com.sqlMap.cfg.Environment;
/**
 * SqlMap字符串帮助类
 * @author youfu.wang
 *
 */
public class StringUtil {
	/**
	 * 返回移除左边空字符的字符串
	 * @param s
	 * @return
	 */
	public static String trimleft(String s) {
		String s1 = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				s1 = s.substring(i);
				break;
			}
		}
		return s1;
	}
	/**
	 * 替换字符串
	 * @param s
	 * @param target
	 * @param replacement
	 * @return
	 */
	public static String replace(String s,String target,String replacement){
		String s1="";		
		if(!"".equals(s)){
			s1=s.replace(target, replacement);
		}				
		return s1;
	}
	/**
	 * 替换字符串的空值（null指针或"null"字符）
	 * @param s
	 * @return
	 */
	public static String replaceNull(String s){	
		String ret="";
		if(s==null||s.equals("null")){			
			return ret;
		}else 
			return s;			
	}
	/**
	 * 替换字符串的空值（null指针或"null"字符）
	 * @param obj
	 * @return
	 */
	public static Object replaceNull(Object obj){		
		if(obj==null||obj.equals("null")){			
			return "";
		}else 
			return obj;			
	}
	private static int getSubStringNumber(String s,char ch){
		int num=1;
		if(s.indexOf(ch)!=-1){
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)==ch){
					num++;
				}
			}
		}		
		return num;
	}
	/**
	 * 拆分成一个字符串数组
	 * @param s
	 * @param ch
	 * @return
	 */
	public static String[] split(String s,char ch){
		String[] strArray=null;	
		if(s==null||"".equals(s)) return strArray;				
		if(s.indexOf(ch)!=-1){		
			strArray=new String[getSubStringNumber(s,ch)];						
			for(int i=0;i<strArray.length;i++){		
				if(s.indexOf(ch)!=-1){
					strArray[i]=s.substring(0, s.indexOf(ch));						
					s=s.substring(s.indexOf(",")+1, s.length());			
				}else
					strArray[i]=s.substring(0, s.length());
			}
		}else{
			strArray=new String[1];
			strArray[0]=s.substring(0, s.length());
		}
		return strArray;
	}
	/**
     * 根据给定的字符串替换成SQL语法的字符;例如:'string'
     * @param str
     * @return ret
     */
	public static String checkString(String str) {
		String ret = "";
		StringBuffer buf=new StringBuffer();
		if(str==null||"".equals(str)){
			ret= "'" + "" + "'";
		}	
		if(str!=null){				
			for(int i=0;i<str.length();i++){
				if(str.charAt(i)=='\''){
					buf.append(str.charAt(i)+"\'");
				}else
					buf.append(str.charAt(i));
			}		
			str=buf.toString();
			ret = "'" + str + "'";			
		}			
		return ret;
	}	
    /**
     * 根据给定的数字类型变量,如果是空值,将其替换成标准的SQL语句;例如:""->"null"
     * @param str
     * @return ret
     */
	public static String checkNumber(String str){		
		String ret="";
		if(str==null||"".equals(str)){
			ret="null";
		}else 
			ret=str;
		return ret;
	}
	
	/**
	 * 根据给定的日期类型参数,如果是空值,将其替换成数据库系统的日期或时间;例如:Oracle:sysdate
	 * @param str
	 * @return
	 */
	public static String checkDate(String str){
		String ret="";		
		if(str==null||"".equals(str))
			ret="null";
		if("oracle".equals(Environment.dialect)){
			ret="to_date('"+str+"','YYYY-MM-DD HH24:MI:SS')";
		}else if("mssql".equals(Environment.dialect)){
			ret="convert(datetime,'"+str+"')";
		}else if("mysql".equals(Environment.dialect)){
			
		}else if("kingbase".equals(Environment.dialect)){
			ret="cast('"+str+"' as timestamp)";	
		}
		return ret;
	}
	
}
