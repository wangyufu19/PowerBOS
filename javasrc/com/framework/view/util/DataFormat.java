package com.framework.view.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import com.framework.common.util.SysConstants;
/**
 * 数据格式化类
 * @author wangyf
 * @version 1.0
 */
public class DataFormat {
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
	 * 用于检查字符串与HTML页面相同符号
	 * 最终将这些字符串转换成HTML标准字符串
	 * @param s
	 * @return
	 */
	public static String xmlEncode(String s){
		StringBuilder buf=new StringBuilder(s);
		StringBuilder newbuf=new StringBuilder();
		for (int i = 0; i <buf.length(); i++) {
            char ch = buf.charAt(i);
            if (ch == '&') {
            	newbuf.append("&amp;");
            }else if (ch == '<') {
            	newbuf.append("&lt;");
            }else if (ch == '>') {
            	newbuf.append("&gt;");
            }else if (ch == '\"') {
            	newbuf.append("&quot;");
            } else {
            	newbuf.append(ch);
            }
        }
		return newbuf.toString();
	}
}
