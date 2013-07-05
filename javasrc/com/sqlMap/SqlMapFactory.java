package com.sqlMap;
import com.sqlMap.SqlMapClient;
import com.sqlMap.cfg.Configuration;
import com.sqlMap.exception.ConfigurationException;

/**
 * SqlMapClient工厂类
 * @author wangyf
 * @version 1.0
 */
public class SqlMapFactory {
	private static Configuration configuration=new Configuration();
	private static ContextFactory contextFactory;
	public SqlMapFactory(){
		
	}
	static {
    	try {    		
    		System.err
			.println("%%%%Creating SqlMapFactory %%%%");
    		configuration.configure();
    		contextFactory=configuration.buildContextFactory();
		} catch (Exception e) {
			System.err
					.println("%%%% Error Creating SqlMapFactory %%%%");
			e.printStackTrace();
		}
    }
	/**
	 * 构建一个SqlMapClient对象
	 * @return
	 */
	public static SqlMapClient buildSqlMapClient(){
		SqlMapClient sqlMapClient=null;
		try {
			sqlMapClient = contextFactory.getSqlMapClient();
		} catch (ConfigurationException e) {			
			e.printStackTrace();
		}
		return sqlMapClient;
	} 	
	/**
	 * 构建一个SqlMapClient对象
	 * @param identifer
	 * @return
	 */
	public static SqlMapClient buildSqlMapClient(String identifer){
		SqlMapClient sqlMapClient=null;
		try {
			sqlMapClient = contextFactory.getSqlMapClient(identifer);
		} catch (ConfigurationException e) {			
			e.printStackTrace();
		}
		return sqlMapClient;
	} 
}
