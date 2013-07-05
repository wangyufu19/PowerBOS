package com.framework.common.excel.fun;
import java.util.Map;

import com.framework.common.excel.util.FunUtil;
/**
 * 电子表格常用函数类
 * @author wangyf
 * @version 1.0
 */
public class CommonFun {
	
	public int getSum(String s,Map datas){
		int v1=0;
		if(s==null||"".equals(s)) return 0;
		FunUtil funUtil=new FunUtil();
		v1=funUtil.getSum(s, datas);
		return v1;
	}
	public boolean getIf(String s,String s1,String operator){		
		if(s==null||"".equals(s)) return true;
		if(s1==null||"".equals(s1)) return true;
		if(operator==null||"".equals(operator)) return true;
		FunUtil funUtil=new FunUtil();
		return funUtil.getIf(s, s1, operator);		
	}
}
