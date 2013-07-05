package com.framework.view.data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 数据类型转换器类
 * @author wangyf
 * @version 1.0
 */
public class DataTypeConverter {
	/**
	 * 判断是否基本对象类型
	 * @param arg1
	 * @return
	 */
	public static boolean isPrimitive(String arg1){		
		if(arg1==null||"null".equals(arg1)||"".equals(arg1)) return false;
		if(arg1.equals("String")||arg1.equals("java.lang.String")){
			return true;
		}else if(arg1.equals("Long")||arg1.equals("java.lang.Long")){
			return true;
		}else if(arg1.equals("Date")||arg1.equals("java.util.Date")){
			return true;
		}else if(arg1.equals("Timestamp")||arg1.equals("java.sql.Timestamp")){
			return true;
		}
		return false;
	}
	/**
	 * 转换java类型到对象类型
	 * @return
	 */
	public static Object convertToObject(String str,String type){
		if("".equals(str)) return str;
		if("java.lang.Integer".equals(type)||"Integer".equals(type)){	
			return convertToInteger(str);
		}else if("java.lang.Long".equals(type)||"Long".equals(type)){	
			return convertToLong(str);
		}else if("java.lang.Float".equals(type)||"Float".equals(type)){
			return convertToFloat(str);
		}else if("java.lang.String".equals(type)||"String".equals(type)){
			return str;
		}else if("java.util.Date".equals(type)||"Date".equals(type)){
			return convertToDate(str);
		}else
			return str;
	}
	/**
	 * String转换为Integer
	 * @param str
	 * @return
	 */
	public static Integer convertToInteger(String str){
		if (str == null || str.equals(""))  return null;    
		return Integer.valueOf(str);
	}
	/**
	 * String转换为Long
	 * @param str
	 * @return
	 */
	public static Long convertToLong(String str){		
		if (str == null || str.equals(""))  return null;            
		return Long.valueOf(str);
	}
	/**
	 *String转换为float
	 * @param str
	 * @return
	 */
	public static Float convertToFloat(String str){
		if(str==null||"".equals(str)) return null;
		return Float.valueOf(str);
	}
	/**
	 * String转换为float
	 * @param str
	 * @param scale
	 * @return
	 */
	public static Float convertToFloat(String str,int scale){
		if(str==null||"".equals(str)) return null;
		BigDecimal b1 = new BigDecimal(str);
		BigDecimal b2 = new BigDecimal("1");
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).floatValue();
	}
	/**
	 * String转换为Date
	 * @param str
	 * @return
	 */
	public static Date convertToDate(String str){
		if (str == null || str.equals(""))  return null;                  
        Date dt = null;
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        try {
            dt = new Date(dtFmt.parse(str).getTime());
        } catch (ParseException e) {           
            e.printStackTrace();
        }
        return dt;
	}	
	/**
	 * Long转换为String
	 * @param str
	 * @return
	 */
	public static String converToString(Long str){
		if(str==null) return null;
		return String.valueOf(str);
	}
	/**
	 * 格式化日期为字符串
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		if (date == null)
	            return null;
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        return dtFmt.format(date);
	}
	/**
	 * 格式化时间为字符串
	 * @param timestamp
	 * @return
	 */
	public static String format(Timestamp timestamp){
		if(timestamp==null) return null;
		DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
		return dtFmt.format(timestamp);		
	}
	public static void main(String[] args){
		System.out.println("******"+DataTypeConverter.convertToFloat("10.2"));
		
	}
}
