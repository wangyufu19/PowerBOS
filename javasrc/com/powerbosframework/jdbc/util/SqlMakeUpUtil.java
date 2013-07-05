package com.powerbosframework.jdbc.util;

import java.sql.Timestamp;
import java.util.Date;

import com.powerbosframework.jdbc.util.TypeConvertUtil;

/**
 * SQL语句参数拼装类
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class SqlMakeUpUtil {
	/**
	 * 替换多个通配符参数"?"
	 * 
	 * @param sql
	 * @param args
	 * @param dialect
	 * @return
	 */
	public static String makeUp(String sql, Object[] args, String dialect) {
		if (args == null)
			return sql;
		if (args.length < 1)
			return sql;
		int index = 0;
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < sql.length(); i++) {
			if (sql.charAt(i) == '?') {
				buf.append(getParameterValues(args[index++], dialect));
			} else {
				buf.append(sql.charAt(i));
			}
		}
		return buf.toString();
	}

	/**
	 * 格式化字符串属性的特殊值,例如:单引号(')
	 * 
	 * @param s
	 */
	private static String formatValue(String s) {
		StringBuilder buf = new StringBuilder();
		if (s == null || "".equals(s)) {
			return "'" + "" + "'";
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\'') {
				buf.append(s.charAt(i) + "\'");
			} else
				buf.append(s.charAt(i));
		}
		return buf.toString();
	}

	/**
	 * 得到通配符参数值
	 * 
	 * @param arg
	 * @param dialect
	 * @return
	 */
	private static String getParameterValues(Object value, String dialect) {
		
		if (value == null)
			return "?";
		if (value instanceof java.lang.Long) {
			return String.valueOf(value);
		} else if (value instanceof java.lang.Float) {
			return String.valueOf(value);
		} else if (value instanceof java.lang.String) {
			return "'" + formatValue(String.valueOf(value)) + "'";
		} else if (value instanceof java.util.Date) {
			if (dialect.equals("oracle"))
				return "to_date('"
						+ TypeConvertUtil.convertOfDate((Date) value)
						+ "','YYYY-MM-DD')";
			else if (dialect.equals("mssql"))
				return "convert(datetime,'"
						+ TypeConvertUtil.convertOfDate((Date) value) + "')";
			else if (dialect.equals("kingbase"))
				return "cast('" + TypeConvertUtil.convertOfDate((Date) value)
						+ "' as timestamp)";
		} else if (value instanceof java.sql.Timestamp) {
			if (dialect.equals("oracle"))
				return "to_date('"
						+ TypeConvertUtil.convertOfTimestamp((Timestamp) value)
						+ "','YYYY-MM-DD HH24:MI:SS')";
			else if (dialect.equals("mssql"))
				return "convert(datetime,'"
						+ TypeConvertUtil.convertOfTimestamp((Timestamp) value)
						+ "')";
			else if (dialect.equals("kingbase"))
				return "cast('"
						+ TypeConvertUtil.convertOfTimestamp((Timestamp) value)
						+ "' as timestamp)";
		} else {
			return value.toString();
		}
		return "?";
	}

}
