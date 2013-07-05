package com.powerbosframework.util;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import com.framework.common.util.SysConstants;
/**
 * 字符串帮助类
 * @author wangyf	
 * @version 1.0
 */
public class StringUtil {
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
	 * 返回分离字符串数组,该字符串包括分隔符
	 * @param s
	 * @param s1
	 * @return
	 */
	public static String[] split(String s,String s1){
		String[] strArray=new String[3];
		String[] tmpArray=new String[1];
		if(s==null||"".equals(s)) return null;
		if(s.indexOf(s1)==-1){
			tmpArray[0]=s;
			return tmpArray;			
		}
		String[] arr=s.split(s1);
		if(arr==null) return null;
		if(arr.length==1){
			strArray[0]=arr[0];
		}else if(arr.length==2){
			strArray[0]=arr[0];
			strArray[1]=s1;
		}else if(arr.length==3){
			strArray[0]=arr[0];
			strArray[1]=s1;
			strArray[2]=arr[1];
		}		
		return strArray;
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
	 * 用于检查字符串与HTML页面相同符号
	 * 最终将这些字符串转换成HTML标准字符串
	 * @param s
	 * @return
	 */
	public static String htmlEncode(String s){
		StringBuilder buf=new StringBuilder(s);
		StringBuilder newbuf=new StringBuilder();
		for (int i = 0; i <buf.length(); i++) {
            char ch = buf.charAt(i);
            if (ch == '&') {
            	newbuf.append("&amp;");
            } else if (ch == '<') {
            	newbuf.append("&lt;");
            } else if (ch == '>') {
            	newbuf.append("&gt;");
            } else if (ch == '\r') {
            	newbuf.append("&#13;");
            } else if (ch == '\n') {
            	newbuf.append("&#10;");
            }else if(ch=='\''){
            	newbuf.append("&#39;;");
            }else if (ch == '\"') {
            	newbuf.append("&quot;");
            } else if (ch == '￥') {
            	newbuf.append("&yen;");
            } else if (ch == '＄') {
            	newbuf.append("&yen;");
            } else {
            	newbuf.append(ch);
            }
        }
		return newbuf.toString();
	}
	/**
	 * 替换"null"字符串
	 * @param str
	 */
	public static String replaceNull(String str){			
		if(str==null||str.equals("null")){			
			return "";
		}else 
			return str;			
	}
	/**
	 * 替换"null"字符串
	 * @param str
	 */
	public static String replaceNull(Object obj){		
		if(obj==null||obj.equals("null")){			
			return "";
		}else 
			return String.valueOf(obj);			
	}
	/**
	 * 替换"null"字符串
	 * @param str
	 */
	public static String[] replaceNull(String[] str){
		if(str==null) return null;
		for(int i=0;i<str.length;i++){
			if(str[i]==null){
				str[i]="";
			}
		}
		return str;		
	}
	/**
	 * 返回一个除去空字符的字符串
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if(str==null) return null;
		if("".equals(str)) return "";		
		int index = 0;
		char[] ch = new char[trimLength(str)];
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				ch[index++] = str.charAt(i);
			}
		}
		String argString = new String(ch);
		return argString;
	}
	/**
	 * 返回除去空格之后的字符长度
	 * @param str
	 * @return
	 */
	public static int trimLength(String str) {
		int index = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				index++;
			}
		}
		return index;
	}

	/**
	 * 返回删除右边字符的字符串
	 * @param str 原字符串
	 * @param ch 字符条件
	 * @return 
	 */
	public static String trimRight(String str,char ch){		
		StringBuffer buf=new StringBuffer();
		if(str==null) return str;		
		int index=0;
		for(int i=0;i<str.length();i++){
			index++;
			if(index>=str.length()&&str.charAt(i)==ch){
				break;
			}else
				buf.append(str.charAt(i));
		}
		return buf.toString();
	}
	/**
	 * 返回一个新字符串，它是此字符串的一个子字符串
	 * @param s 原字符串
	 * @param ch 字符条件
	 * @param ch1 字符条件
	 * @return
	 */
	public static String getSubString(String s,char ch,char ch1){
		int start=0;
		int end=0;		
	    start=s.indexOf(ch)+1;
	    end=s.indexOf(ch1);
	    if(end==-1) end=s.length();	  
	    return s.substring(start, end);
	}
	/**
	 * 返回一个新字符串，它是此字符串的一个子字符串
	 * @param s 原字符串
	 * @param ch 字符条件
	 * @return
	 */
	public static String getSubStrng(String s,char ch){
		int start=0;
		int end=0;		
	    start=s.indexOf(ch)+1;
	    end=s.length();
		return s.substring(start, end);
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
	/**
	 * 得到编码的字符串
	 * @param s
	 * @return
	 */
	public static String getURLEncode(String s) {
		if (s==null) return "";
		try {
			s = URLEncoder.encode(s, SysConstants.charset_code);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return s;
	}
	/**
	 * 返回GET编码字符串
	 * @param str
	 * @return
	 */
	public static String getGetEncode(String str){
		if(str==null||"".equals(str.equals(""))) return str;
		try {
			str=new String(str.getBytes(SysConstants.charset_code));
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 返回POST编码字符串
	 * @param str
	 * @return
	 */
	public static String getPostEncode(String str){
		if(str==null||"".equals(str.equals(""))) return str;
		try {
			str=new String(str.getBytes(SysConstants.iso_encode),SysConstants.charset_code);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 返回POST编码字符串
	 * @param str
	 * @return
	 */
	public static String getChineseDecode(String str){
		if(str==null||"".equals(str.equals(""))) return str;
		try {
			str=new String(str.getBytes(SysConstants.iso_encode),SysConstants.charset_code);
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 通过java自带函数判断字符串是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){   			
		    if (!Character.isDigit(str.charAt(i))){
		    	return false;
		    }
		}
	    return true;
	}
	/**
	 * 通过正值表达式判断字符串是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumericByMatch(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	} 
	/**
	 * 通过ASCII码判断字符串是否数字
	 * @param str
	 * @return
	 */
	public static boolean isNumericByAscii(String str){
	   for(int i=str.length();--i>=0;){
	      int chr=str.charAt(i);
	      if(chr<48 || chr>57)
	         return false;
	   }
	   return true;
	}
	/**
	 * 读取枚举内容
	 * @param str
	 * @return
	 */
	public static List accessEnmu(String str){
		List list=new ArrayList();
		String[] arr=split(str,',');
		if(arr==null) return list;
		int start=Integer.parseInt(arr[0]);
		int end=Integer.parseInt(arr[1]);
		for(int i=start;i<=end;i++){
			Map map=new HashMap();
			map.put(String.valueOf(i), String.valueOf(i));
			list.add(map);
		}
		return list;
	}
	/**
	 * 得到时间戳字符串
	 * @return ret
	 */
	public static String getTimeNum(){
		String ret="";
		ret=String.valueOf(new Date().getTime());
		return ret;
	}
	/**
	 * 将Vector对象数组转换成字符串二维数组
	 * @param v
	 * @return
	 */
	public static String[][] toArray(Vector[] v) {				
		int row = v.length;
		int col =0;
		if(v==null) return null;
		if(v.length>0){
			col=v[0].size();
		}		
		String[][] strArray =null;
		if(row>0&&col>0){
			strArray=new String[row][col];
		}	
		if(strArray==null) return null;		
		for (int i = 0; i < strArray.length; i++) {
			for (int j = 0; j < col; j++) {
				if (j == 0) {
					strArray[i][j] = String.valueOf(v[i].get(j));
				} else{		
					strArray[i][j] = String.valueOf(v[i].get(j));					
				}
			}
		}						
		return strArray;
	}	
    /**
     * 将Vector对象转换成字符串数组
     * @param v
     * @return
     */
	public static String[] toArray(Vector v) {
		if(v==null) return null;
		String[] strArray = new String[v.size()];		
		for (int i = 0; i < v.size(); i++) {
			strArray[i] = String.valueOf(v.get(i));			
		}
		return strArray;
	}
	/**
	 * 将List对象转换成字符串二维数组
	 * @param v
	 * @return
	 */
	public static String[][] toArray(List list){	
		if(list==null) return null;
		int row=list.size();
		int col=0;	
		String[] str=null;
		Object[] obj=list.toArray();
		
	    if(obj.length>0){
	    	str=(String[])obj[0];
	    }		   
	    if(str!=null&&str.length>0){
	    	col=str.length;
	    }	    
		String[][] ret=new String[row][col];
		
		for(int i=0;i<obj.length;i++){
			  String[] tmpStr=(String[])obj[i];
			  for(int j=0;j<tmpStr.length;j++){
				ret[i][j]=tmpStr[j];
			  }
		}
		return ret;
	}
	public static void main(String[] args){
		
		String s="9,3>=10,3";
		System.out.println("*****s="+StringUtil.split(s, ">=")[0].split(",")[2]);
		
	}
}
