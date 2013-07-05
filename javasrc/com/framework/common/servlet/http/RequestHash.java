package com.framework.common.servlet.http;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sqlMap.SqlMapClient;
import com.sqlMap.SqlMapFactory;
import com.sqlMap.jdbc.JdbcFactory;
import com.sqlMap.jdbc.JdbcTmplt;
/**
 * HTTP系统请求类
 * @author wangyf
 * @version 1.0
 */
public class RequestHash extends ReqAndResHttp{
	private static Map<String, Object> sessionHash=new HashMap<String, Object>();
	private SqlMapClient sqlMapClient=null;	
	private JdbcTmplt jdbcTmplt=null;	
	
	public RequestHash(HttpServletRequest request){
		super(request);
	}
	public RequestHash(HttpServletRequest request,HttpServletResponse response){
		super(request,response);
	}
	public RequestHash(HttpServletRequest request,HttpServletResponse response,boolean encode){
		super(request,response,encode);
	}
	public HttpServletRequest getReqeust(){
		return super.getHttpServletRequest();
	}
	public HttpServletResponse getResponse(){
		return super.getHttpServletResponse();
	}
	public HttpSession getSession(){				
		HttpSession httpSession = getReqeust().getSession();		
		return httpSession;
	}	
	public String get(String key){
		return super.get(key);
	}
	public String[] getArray(String key){
		return super.getArray(key);
	}	
	public String getJqueryParameterSerialize(){
		String jqueryParameterSerialize=super.getJqueryParameterSerialize();
		if(jqueryParameterSerialize.startsWith("&"))
			jqueryParameterSerialize=jqueryParameterSerialize.substring(1, jqueryParameterSerialize.length());
		return jqueryParameterSerialize;
	}
	
	public String getRequestContextPath(){
		return this.getReqeust().getContextPath();
	}  	
	public Map getSessionHash(){
		return sessionHash;
	}
	public Map getParamHash(){
		return super.getParamHash();
	}
	public String getCurrentFormName(){
		return String.valueOf(getSessionHash().get("FORM"));
	}
	public SqlMapClient getSqlMapClient() {
		if(sqlMapClient==null){
			sqlMapClient=SqlMapFactory.buildSqlMapClient();			
		}
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	public JdbcTmplt getJdbcTmplt(){
		if(jdbcTmplt==null){
			jdbcTmplt=JdbcFactory.buildJdbcTmplt();			
			//System.out.println("*************new connection*************");
		}
		return jdbcTmplt;		
	}
	public void setJdbcTmplt(JdbcTmplt jdbcTmplt){
		this.jdbcTmplt=jdbcTmplt;
	}
	public void clear(){	
		if(jdbcTmplt!=null)			
			try {
				jdbcTmplt.close();			
				//System.out.println("*************close connection*************");
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		jdbcTmplt=null;
		if(sqlMapClient!=null)
			try {
				sqlMapClient.close();			
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		sqlMapClient=null;
		super.getRequestHash().clear();		
		super.getParamHash().clear();
	}
}
