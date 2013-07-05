package com.framework.common.excel.util;
/**
 * 电子表格条件判断帮助类
 * @author wangyf
 * @version 1.0
 */
public class IfUtil {
	/**
	 * 得到数据运算结果布尔值
	 * @param s
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean getOperationResult(String s,String s1,String s2){
		if(s==null||s1==null||s2==null) return true;		
		if(">=".equals(s2)){
			return getGreatEqualResult(s,s1);
		}else if(">".equals(s2)){
			return getGreatThanResult(s,s1);
		}else if("<=".equals(s2)){
			return getLessEqualResult(s,s1);
		}else if("<".equals(s2)){
			return getLessThanResult(s,s1);
		}else if("==".equals(s2)||"=".equals(s2)){
			return getEqualThanResult(s,s1);
		}
		return false;
	}
	/**
	 * 得到大于运算结果
	 * @param s
	 * @param s1
	 * @return
	 */
	public static boolean getGreatThanResult(String s,String s1){
		if(s==null||s1==null) return true;
		if(Integer.parseInt(s)>Integer.parseInt(s1)) return true;
		return false;
	}
	/**
	 * 得到大于等于运算结果
	 * @param s
	 * @param s1
	 * @return
	 */
	public static boolean getGreatEqualResult(String s,String s1){
		if(s==null||s1==null) return true;
		if(Integer.parseInt(s)>=Integer.parseInt(s1)) return true;
		return false;
	}
	/**
	 * 得到小于运算结果
	 * @param s
	 * @param s1
	 * @return
	 */
	public static boolean getLessThanResult(String s,String s1){
		if(s==null||s1==null) return true;
		if(Integer.parseInt(s)<Integer.parseInt(s1)) return true;
		return false;
	}
	/**
	 * 得到小于等于运算结果
	 * @param s
	 * @param s1
	 * @return
	 */
	public static boolean getLessEqualResult(String s,String s1){
		if(s==null||s1==null) return true;
		if(Integer.parseInt(s)<=Integer.parseInt(s1)) return true;
		return false;
	}
	/**
	 * 得到等于运算结果
	 * @param s
	 * @param s1
	 * @return
	 */
	public static boolean getEqualThanResult(String s,String s1){
		if(s==null||s1==null) return true;
		if(Integer.parseInt(s)==Integer.parseInt(s1)) return true;
		return false;
	}
	
}
