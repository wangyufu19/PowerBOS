package com.framework.view.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.view.util.StringFormat;

/**
 * 缓存数据类
 * @author youfu.wang
 * @version 1.0
 */
public class CacheData {
	
	public static Map datadicts=new HashMap();//数据字典缓存对象
	
	/***
	 * 得到数据字典对应键值
	 * @param dictCode
	 * @param s
	 * @return
	 */
	public static String getDataDict(String dictCode,String s){				
		List list=(List)(CacheData.datadicts.get(dictCode));				
		if(list==null) return s;
		for(int i=0;i<list.size();i++){
			Map datadict=(Map)list.get(i);
			String key=StringFormat.replaceNull(String.valueOf((datadict.keySet().toArray())[0]));
			String value=StringFormat.replaceNull(String.valueOf(datadict.get(key)));			
			if(key.equals(s)){
				return value;				
			}
		}
		return s;
	}
}
