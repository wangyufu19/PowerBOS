package com.sqlMap.util;
import java.util.Date;
import java.sql.Timestamp;
import com.sqlMap.cfg.Environment;
import com.sqlMap.type.DateType;
import com.sqlMap.type.TimestampType;

/**
 * 数据类型帮助类
 * @author youfu.wang
 * @version 1.0
 */
public class TypeUtil {
	
	
	/**
	 * 判断对象是否基本对象类型
	 * @param obj
	 * @return
	 */
	public static boolean isPrimitive(Object obj){
		if(obj==null) return true;	
		if(isPrimitive(obj.getClass().getSimpleName())){
			return true;
		}else if(isPrimitive(obj.getClass().getName())){
			return true;
		}
		return false;
	}
	/**
	 * 判断是否基本对象类型
	 * @param arg1
	 * @return
	 */
	public static boolean isPrimitive(String arg1){		
		if(arg1==null||"null".equals(arg1)||"".equals(arg1)) return true;
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
	 * 格式化字符串属性的特殊值,例如:单引号(')
	 * @param s
	 */
	private static String formatValue(String s){
		StringBuilder buf=new StringBuilder();
		if(s==null||"".equals(s)){	
			return "'" + "" + "'";
		}
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='\''){
				buf.append(s.charAt(i)+"\'");
			}else
				buf.append(s.charAt(i));
		}
		return buf.toString();
	}    
	/**
	 * 格式化数据类型对象值
	 * @param arg1
	 * @param value
	 * @return
	 */
	public static String formatValue(String arg1,Object value){		
		String s="";			
		if(value==null||"".equals(value)) return "null";
		if("java.lang.Long".equals(arg1)||"Long".equals(arg1)){
			s=String.valueOf(value);
		}else if("java.lang.Float".equals(arg1)||"Float".equals(arg1)){
			s=String.valueOf(value);
		}else if("java.lang.String".equals(arg1)||"String".equals(arg1)){
			s="'"+formatValue(String.valueOf(value))+"'";
		}else if("java.util.Date".equals(arg1)||"Date".equals(arg1)){
			DateType dataType=new DateType();			
			if(Environment.dialect.equals("oracle"))
				s="to_date('"+dataType.format((Date)value)+"','YYYY-MM-DD')";
			else if(Environment.dialect.equals("mssql"))
				s="convert(datetime,'"+dataType.format((Date)value)+"')";
			else if(Environment.dialect.equals("kingbase"))
				s="cast('"+dataType.format((Date)value)+"' as timestamp)";			
		}else if("java.sql.Timestamp".equals(arg1)||"Timestamp".equals(arg1)){
			TimestampType timestampType=new TimestampType();
			if(Environment.dialect.equals("oracle"))
				s="to_date('"+timestampType.format((Timestamp)value)+"','YYYY-MM-DD HH24:MI:SS')";
			else if(Environment.dialect.equals("mssql"))
				s="convert(datetime,'"+timestampType.format((Timestamp)value)+"')";
			else if(Environment.dialect.equals("kingbase"))
				s="cast('"+timestampType.format((Timestamp)value)+"' as timestamp)";	
		}else 
			s=value.toString();
		return s;
	}	
}
