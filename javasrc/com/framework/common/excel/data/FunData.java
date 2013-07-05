package com.framework.common.excel.data;
import java.util.Map;
import java.util.HashMap;
/**
 * 电子表格函数数据类
 * @author wangyf
 * @version 1.0
 */
public class FunData {	
	private Map datas=new HashMap();
	
	/**
	 * 推入数据到堆栈
	 * @param key 
	 * @param value 
	 */
	public void putData(Object key,Object value){
		datas.put(key, value);
	}	
	/**
	 * 得到数据堆栈
	 * @return
	 */
	public Map getData(){
		return datas;
	}

}
