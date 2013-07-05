package com.sqlMap.jdbc;
import java.util.List;
import java.util.ArrayList;
/**
 * JDBC语句块模板类
 * @author youfu.wang
 * @version 1.0
 */
public class StatementTmplt {	
	private List sqls=new ArrayList();
	/**
	 * 加入SQL到语句块列表中
	 * @param sql
	 */
	public void addSQLList(String sql){
		sqls.add(sql);
	}
	/**
	 * 返回SQL到语句块列表
	 * @return
	 */
	public List getSQLList(){
		return sqls;
	}	
}
