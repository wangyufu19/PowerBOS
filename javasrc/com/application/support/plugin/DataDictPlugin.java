package com.application.support.plugin;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sqlMap.jdbc.JdbcFactory;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
import com.framework.config.Plugin;
import com.framework.view.data.CacheData;
/**
 * 数据字典类
 * @author wangyf
 * @version 1.0
 */
public class DataDictPlugin extends Plugin{	
	
	
	public DataDictPlugin(){
		
	}
	@Override
	public void load() {
		String sql="";		
		JdbcTmplt jdbcTmplt=JdbcFactory.buildJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select id,dict_code,dict_name from sm_t_data_dict where is_valid=1 and dict_parent_id='0' order by order_no";		
		try {
			List list=queryTmplt.iterator(sql);
			if(list==null) return;
			for(int i=0;i<list.size();i++){
				Object[] arr=(Object[])list.get(i);
				String parentId=String.valueOf(arr[0]);
				String key=String.valueOf(arr[1]);							
				List datadict=new ArrayList();
				sql="select dict_code,dict_name from sm_t_data_dict where dict_parent_id=@ order by order_no";
				sql=MakeUpUtil.makeUp(parentId, sql);
				List list1=queryTmplt.iterator(sql);	
				for(int j=0;j<list1.size();j++){
					Object[] arr1=(Object[])list1.get(j);						
					Map map=new HashMap();
					String code=String.valueOf(arr1[0]);
					String name=String.valueOf(arr1[1]);					
					map.put(code, name);
					datadict.add(map);				
				}
				CacheData.datadicts.put(key, datadict);								
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}	
	}	
}
