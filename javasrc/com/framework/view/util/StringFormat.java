package com.framework.view.util;
/**
 * 字符串格式化类
 * @author wangyf
 * @version 1.0
 */
public class StringFormat {
	/**
	 * 返回分离字符数组
	 * @param s
	 * @param ch 分离符:'|'
	 * @return
	 */
	public static String[] split(String s,char ch){
		String[] strArray=null;	
		if(s==null||"".equals(s)) return strArray;				
		if(s.indexOf(ch)!=-1){		
			int num=1;		
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)==ch){
					num++;
				}
			}					
			strArray=new String[num];						
			for(int i=0;i<strArray.length;i++){		
				if(s.indexOf(ch)!=-1){
					strArray[i]=s.substring(0, s.indexOf(ch));						
					s=s.substring(s.indexOf(ch)+1, s.length());			
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
	 * 替换"null"字符串
	 * @param s1
	 */
	public static String replaceNull(String s1){			
		if(s1==null||s1.equals("null")){			
			return "";
		}else 
			return s1;			
	}
	/**
	 * 替换"null"字符串
	 * @param obj
	 */
	public static String replaceNull(Object obj){		
		if(obj==null||obj.equals("null")){			
			return "";
		}else 
			return String.valueOf(obj);			
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
	 * 转换字符串运算结果为布偶型;支持的转换类型("A"=="B","A"!="B","A>"B")
	 * @param arg1
	 * @return
	 */
	public static boolean convertOfBoolean(String s){		
		boolean bool=true;		
		String[] arr=null;;				
		if(s.indexOf("==")!=-1){
			arr=s.split("==");			
		}else if(s.indexOf("!=")!=-1){
			arr=s.split("!=");
		}else if(s.indexOf(">")!=-1){
			arr=s.split(">");
		}
		if(arr!=null){					
			if(arr.length>=2){						
				if(s.indexOf("==")!=-1){		
					if(arr[0].equals(arr[1])){
						bool=true;
					}else
						bool=false;
				}else if(s.indexOf("!=")!=-1){
					if(!arr[0].equals(arr[1])){						
						bool=true;
					}else
						bool=false;
				}else if(s.indexOf(">")!=-1){					
					if(Integer.parseInt(arr[0])>Integer.parseInt(arr[1])){
						bool=true;
					}else
						bool=false;
				}
				
			}else if(arr.length==1){						
				if(s.indexOf("==")!=-1){
					if("null".equals(arr[0])){						
						bool=true;
					}else
						bool=false;
				}else if(s.indexOf("!=")!=-1){
					if(!"null".equals(arr[0])){						
						bool=true;
					}else
						bool=false;
				}				
			}else if(arr.length==0){
				if(s.indexOf("==")!=-1){
					bool=true;
				}else if(s.indexOf("!=")!=-1){
					bool=false;
				}
				
			}
		}	
		return bool;
	}	
}
