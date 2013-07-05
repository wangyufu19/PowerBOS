package com.sqlMap.type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.sqlMap.cfg.Environment;
/**
 * 日期类型对象
 * @author youfu.wang
 * @version 1.0
 */
public class DateType {
	/**
	 * 从数据集中得到指定列的值
	 * @param rst
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public Object get(ResultSet rst,String name) throws SQLException{
		return this.parseDate(rst.getDate(name));
	}
	private Date parseDate(Object s) {
        if (s == null || s.equals(""))
            return null;       
        Date dt = null;
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        try {
            dt = new Date(dtFmt.parse(s.toString()).getTime());
        } catch (ParseException e) {           
            e.printStackTrace();
        }
        return dt;
    }
	/**
	 * 将日期对象格式化为"yyyy-MM-dd"的字符串
	 * @param date
	 * @return
	 */
	public String format(Date date){
		if (date == null)
	            return null;
        DateFormat dtFmt = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        return dtFmt.format(date);
	}
	/**
	 * 将日期对象格式化为指定的模式字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public String format(Date date,String pattern){
		if (date == null)
            return null;
	    DateFormat dtFmt = new SimpleDateFormat(pattern,Locale.CHINA);
	    return dtFmt.format(date);
	}
	/**
	 * 得到数据库日期格式
	 * to_char('column','yyyy-mm-dd')
	 * @param s
	 * @return
	 */	
	public static String getdbyyyymmdd(String s){
		if(Environment.dialect.equals("oracle")){
			return "to_char("+s+",'YYYY-MM-DD')";
		}
		return "";
	}
	/**
	 * 得到数据库日期格式
	 * to_char('column','yyyy-mm-dd hh:mi:ss')
	 * @param s
	 * @return
	 */
	public static String getdbyyyymmddhhmiss(String s){
		if(Environment.dialect.equals("oracle")){
			return "to_char("+s+",'YYYY-MM-DD HH24:MI:SS')";
		}
		return "";
	}
    /**
     * 得到插入数据库日期格式
     * to_date('sysdate','yyyy-mm-dd')
     * @param s
     * @return
     */
	public static String insertdbyyyymmdd(String s){
		if(Environment.dialect.equals("oracle")){
			return "to_date('"+s+"','YYYY-MM-DD')";
		}else if(Environment.dialect.equals("mssql")){			
			return "convert(datetime,'"+s+"')";
		}else if(Environment.dialect.equals("mysql")){
			
		}else if(Environment.dialect.equals("kingbase")){
			return "cast("+s+" as timestamp)";	
		}
		return "";	
	}
    /**
     * 得到插入数据库日期格式
     * to_date('sysdate','yyyy-mm-dd hh:mi:ss')
     * @param s
     * @return
     */
	public static String insertdbyyyymmddhhmiss(String s){
		if(Environment.dialect.equals("oracle")){
			return "to_date('"+s+"','YYYY-MM-DD HH24:MI:SS')";
		}else if(Environment.dialect.equals("mssql")){
			return "convert(datetime,'"+s+"')"; 
		}else if(Environment.dialect.equals("mysql")){
			
		}else if(Environment.dialect.equals("kingbase")){
			return "cast("+s+" as timestamp)";	
		}
		return "";	
	}
	/**
	 * 得到插入数据库系统日期 
	 * @return
	 */
	public static String insertdbsysdate(){
		if(Environment.dialect.equals("oracle")){
			return "sysdate";
		}
		return "";
	}
}
