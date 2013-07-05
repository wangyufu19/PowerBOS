package com.sqlMap.log;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.log.Logger;
import com.sqlMap.log.LogFactory;
/**
 * 数据库日志类
 * @author youfu.wang
 * @version 1.0
 */
public class DBLogger {
	private static final Logger log=LogFactory.getInstance();
	public static final String insert="insert";
	public static final String update="update";
	public static final String delete="delete";
	public static String show_sql="";
	public static String sql_log="";
	
	/**
	 * 保存数据库操作日志
	 * @param sql
	 * @throws SQLException
	 */
	public static void saveLog(Connection connection,PreparedStatement pstmt,String sql) throws SQLException{	
		
		if(sql_log.equals("true")){
			String content=sql;
			String actionType="insert";
			if(content.indexOf(insert)!=-1){
				actionType=insert;
			}else if(content.indexOf(update)!=-1){
				actionType=update;
			}else if(content.indexOf(delete)!=-1){
				actionType=delete;
			}				
			sql="insert into sm_t_db_log(id,content,action_type) values(" +		
			        "'"+String.valueOf(new IdentifierGenerator().getUUID())+"',"+
					"'"+formatValue(content)+"'," +
					"'"+actionType+"')";		
			if(show_sql.equals("true"))
				log.info("sql: "+sql);
			Statement stmt=connection.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();		
		}
	}	
	/**
	 * 格式化字符串属性的特殊值,例如:单引号(')
	 * @param s
	 */
	private static String formatValue(String s){
		StringBuilder buf=new StringBuilder();
		if(s==null||"".equals(s)){	
			return "'" + "" + "'";
		}
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='\''){
				buf.append(s.charAt(i)+"\'");
			}else
				buf.append(s.charAt(i));
		}
		return buf.toString();
	}
}
