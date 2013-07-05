package com.application.support.biz;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.application.support.dao.UserDao;
import com.application.support.model.Function;
import com.application.support.model.LoginInfo;
import com.framework.common.base.BaseBiz;
import com.sqlMap.jdbc.JdbcTmplt;
/**
 * 用户权限菜单类
 * @author wangyf
 * @version 1.0
 */
public class UserMenuBiz extends BaseBiz{	
	/**
	 * 加载用户授权菜单
	 * @param loginInfo
	 * @param parentId
	 * @return
	 */
	public List loadUserPerviewMenu(LoginInfo loginInfo,String parentId){
		UserDao dao=new UserDao();	
		dao.setJdbcTmplt(this.getJdbcTmplt());
		List list=null;	
		try {
			list=dao.getUserPerviewMenu(parentId);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		List list1=new ArrayList();
		Map perviews=(Map)(loginInfo).getPerviews();	
		
		for(int i=0;i<list.size();i++){
			Function function=(Function)list.get(i);
			if(perviews.containsKey(function.getFunctionCode()))
				list1.add(function);
		}
		return list1;
	}
	/**
	 * 加载用户授权菜单
	 * @param loginInfo
	 * @param String contextPath
	 * @return
	 */
	public String loadUserPerviewMenuXTree(LoginInfo loginInfo,String contextPath){		
		String xtreedoc="";
		StringBuilder buf=new StringBuilder();
		Map perviews=(Map)(loginInfo).getPerviews();				
		if(!"/".endsWith(contextPath)) contextPath=contextPath.concat("/");
		JdbcTmplt jdbcTmplt=this.getJdbcTmplt();	
		this.putXTreeDoc(buf,contextPath,perviews,jdbcTmplt, "0");
		xtreedoc=buf.toString();			
		return xtreedoc;
	}
	public void putXTreeDoc(StringBuilder buf,String contextPath,Map perviews,JdbcTmplt jdbcTmplt,String parentId){
		List list=null;
		String sql="";
		String returnClass="function";
		String resultMap="funResult";	
		sql="select id,function_code,function_parent_id,function_name,function_url from bos_t_function " +
			"where function_parent_id=$functionParentId$ order by order_no";	
		try {
			list=jdbcTmplt.createQueryTmplt().iterator(sql, Long.valueOf(parentId), returnClass, resultMap);
		} catch (NumberFormatException e) {		
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		if(list==null) return;
		for(int i=0;i<list.size();i++){
			Function fun=(Function)list.get(i);
			if(perviews.containsKey(fun.getFunctionCode())){
				if("0".equals(String.valueOf(fun.getFunctionParentId())))
					buf.append("var xtree"+fun.getId()+"=new WebFXTreeItem(\""+fun.getFunctionName()+"\",\"\",root,\"\",\"\",\"mainboard\");\n");
				else{
					if("".equals(fun.getFunctionUrl()))
						buf.append("var xtree"+fun.getId()+"=new WebFXTreeItem(\""+fun.getFunctionName()+"\",\"\","+"xtree"+String.valueOf(fun.getFunctionParentId())+",\"\",\"\",\"mainboard\");\n");
					else
						buf.append("var xtree"+fun.getId()+"=new WebFXTreeItem(\""+fun.getFunctionName()+"\",\""+contextPath.concat(fun.getFunctionUrl())+"\","+"xtree"+String.valueOf(fun.getFunctionParentId())+",\"\",\"\",\"mainboard\");\n");
				}
					
				this.putXTreeDoc(buf,contextPath,perviews,jdbcTmplt,String.valueOf(fun.getId()));
			}			
		}
	}

}
