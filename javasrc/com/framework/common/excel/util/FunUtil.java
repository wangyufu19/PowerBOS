package com.framework.common.excel.util;
import java.util.Map;
import java.util.HashMap;

import com.framework.common.excel.util.CalculateUtil;
import com.framework.common.excel.util.IfUtil;

/**
 * 电子表格函数帮助类
 * @author wangyf
 * @version 1.0
 */
public class FunUtil {	
	/**
	 * 计算单2个以上单元格加减
	 * @param s
	 * @param datas
	 * @return
	 */
	public int getSum(String s,Map datas){		
		CalculateUtil calculate=new CalculateUtil();
		return calculate.calculateSum(s, datas);
	}
	/**
	 * 条件判断两个单元格
	 * @param s
	 * @param s1
	 * @param operator
	 * @return
	 */
	public boolean getIf(String s,String s1,String operator){
		IfUtil ifUtil=new IfUtil();
		return ifUtil.getOperationResult(s, s1, operator);
	}
	public static void main(String[] args){	   
		String s="1,2+{2010001}1,3+{2010002}1,4";
		String s1="1";
		HashMap datas=new HashMap();
		HashMap data=new HashMap();
		HashMap data1=new HashMap();
		HashMap data2=new HashMap();
		data.put("1,2", "1");
		
		data1.put("1,3", "2");
		
		data2.put("1,4", "3");
		
		datas.put("default", data);
		datas.put("2010001", data1);
		datas.put("2010002", data2);
		FunUtil FunUtil=new FunUtil();		
		s=String.valueOf(FunUtil.getSum(s, datas));
		System.out.println("******s="+s);
	}
}
