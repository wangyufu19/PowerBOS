package com.controller.util;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * 类型转换器
 * @author wangyf
 * @version 1.0
 */
public class TypeConverter {
	
	public TypeConverter(){
		
	}
	public static boolean isModelObject(String type){		
		if(type==null) return false;
		if(type.equals("List")){
			return false;
		}else if(type.equals("String")){
			return false;
		}else if(type.equals("Map")){
			return false;
		}else if(type.equals("Iterator")){
			return false;
		}else if(type.equals("String[]")){
			return false;
		}else
			return true;	
	}
	public static boolean isPrimitive(String type){
		if(type==null) return false;
		if(type.equals("String")){
			return true;
		}else if(type.equals("Long")){
			return true;
		}else if(type.equals("Date")){
			return true;
		}
		return false;
	}
	public static boolean isArray(String type){
		if(type==null) return false;
		if(type.equals("String[]"))
			return true;
		return false;
	}
	public static Object convert(Object obj,String type){		
		if(type.equals("String")){
			return convertToString(obj);
		}else if(type.equals("Long")){
			return convertToLong(obj);
		}else if(type.equals("Date")){
			return convertToDate(obj);
		}else if(type.equals("Timestamp")){
			return convertToTimestamp(obj);
		}
		return obj;
	}
	public static String convertToString(Object obj){
		if(obj==null||obj.equals("")) return null;
		return String.valueOf(obj);
	}
	public static Long convertToLong(Object obj){
		if(obj==null||obj.equals("")) return null;
		return Long.valueOf(String.valueOf(obj));		
	}
	public static Date convertToDate(Object obj){
		if(obj==null||obj.equals("")) return null;
		Date dt = null;
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        try {
            dt = new Date(dtFmt.parse(obj.toString()).getTime());
        } catch (ParseException e) {           
            e.printStackTrace();
        }
        return dt;
	}
	public static Timestamp convertToTimestamp(Object obj) {
        if (obj== null ||obj.equals("")) return null;
        Timestamp dt = null;
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINA);
        try {
            dt = new Timestamp(dtFmt.parse(obj.toString()).getTime());
        } catch (ParseException e) {            
            e.printStackTrace();
        }
        return dt;
    }

}
