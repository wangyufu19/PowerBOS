package com.sqlMap.jdbc.util;
import com.sqlMap.util.StringUtil;
/**
 * 语句通配符组装
 * @author wangyf
 * @version 1.0
 */
public class MakeUpUtil {
	/**
	 * 替换单个通配符参数;1:"@"代表字符型;2:"#"代表数字型;3:"$"代表日期型
	 * @param param
	 * @param sql
	 * @return
	 */
	public static String makeUp(String param, String sql) {		
		if (param == null || sql == null) return "";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < sql.length(); i++) {			
			if (sql.charAt(i) == '@') {
				if (param == null || param.equals("")) {
					buf.append("null");
				} else
					buf.append(StringUtil.checkString(param));
			} else if (sql.charAt(i) == '#') {
				if (param == null || param.equals("")) {
					buf.append("null");
				} else
					buf.append(param);
			} else if(sql.charAt(i)=='$'){
				if (param == null || param.equals("")) {
					buf.append("null");
				} else
					buf.append(param);
			}else
				buf.append(sql.charAt(i));
		}
		sql = buf.toString();
		
		return sql;
	}
    /**
     * 替换多个通配符参数;1:"@"代表字符型;2:"#"代表数字型;3:"$"代表日期型
     * @param params
     * @param sql
     * @return
     */
	public static String makeUp(String[] params, String sql) {		
		int parmIndex = 0;	
		if (params==null || sql == null) return "";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < sql.length(); i++) {
			if (sql.charAt(i) == '@') {				
				if (params.length < 1 && params == null) {				
				} else if(parmIndex<params.length){
					buf.append(StringUtil.checkString(params[parmIndex++]));					
				}
			} else if (sql.charAt(i) == '#') {						
				if (params.length < 1 && params == null) {					
				} else if(parmIndex<params.length){							
					buf.append(StringUtil.checkNumber(params[parmIndex++]));
				}
			}else if(sql.charAt(i)=='$'){				
				if (params.length < 1 && params == null) {					
				} else if(parmIndex<params.length){					
					buf.append(StringUtil.checkDate(params[parmIndex++]));
				}
			}else
				buf.append(sql.charAt(i));
		}
		sql = buf.toString();	
		return sql;
	}

}
