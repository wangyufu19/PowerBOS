package com.sqlMap.jdbc;
import com.sqlMap.ContextFactory;
import com.sqlMap.cfg.Configuration;
import com.sqlMap.exception.ConfigurationException;
/**
 * JDBC工厂类
 * @author youfu.wang
 * @version 1.0
 */
public class JdbcFactory {
	private static Configuration configuration=new Configuration();
	private static ContextFactory contextFactory;
	
	public JdbcFactory(){
		
	}
	static {
    	try {    		
    		System.err
			.println("%%%%Creating JdbcFactory %%%%");
    		configuration.configure();
    		contextFactory=configuration.buildContextFactory();
		} catch (Exception e) {
			System.err
					.println("%%%% Error Creating JdbcFactory %%%%");
			e.printStackTrace();
		}
    }
	/**
	 * 构建一个jdbc模板对象
	 * @return
	 */
	public static JdbcTmplt buildJdbcTmplt(){
    	JdbcTmplt jdbcTmplt=null;
		try {
			jdbcTmplt = contextFactory.getJdbcTmplt();
		} catch (ConfigurationException e) {			
			e.printStackTrace();
		}
    	return jdbcTmplt;
	}
	/**
	 * 构建一个jdbc模板对象
	 * @param identifer
	 * @return
	 */
	public static JdbcTmplt buildJdbcTmplt(String identifer){
    	JdbcTmplt jdbcTmplt=null;
		try {
			jdbcTmplt = contextFactory.getJdbcTmplt(identifer);
		} catch (ConfigurationException e) {			
			e.printStackTrace();
		}
    	return jdbcTmplt;
	}
	public static void main(String[] s){
		
	}
}
