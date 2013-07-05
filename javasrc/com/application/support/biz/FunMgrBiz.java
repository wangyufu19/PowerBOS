package com.application.support.biz;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.powerbosframework.log.LogFactory;
import com.powerbosframework.util.StringUtil;
import com.sqlMap.Transaction;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
import com.framework.common.base.BaseBiz;
import com.framework.common.util.SysConstants;
import com.framework.view.tree.TreeNode;
import com.framework.view.tree.XtreeLoader;
/**
 * 系统权限管理业务层
 * @author wangyf
 * @version 1.0
 */
public class FunMgrBiz extends BaseBiz{	
	public String deleteFun(){
		String msg=SysConstants.exce_succeed;
		String sql="";
		String[] idArr=this.reh.getArray("id");
		if(idArr==null) return msg;
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		Transaction tx=null;
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		try {
			tx=jdbcTmplt.beginTransaction();
			for(int i=0;i<idArr.length;i++){
				sql="select id from sm_t_function where function_parent_id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);			
				List list=queryTmplt.iterator(sql);
				if(list.size()>=1){
					msg="对不起,该权限存在下级,不能删除!";
					break;
				}			
				sql="delete from sm_t_function where id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);	
				jdbcTmplt.execute(sql);
			}
			tx.commit();
		} catch (SQLException e) {			
			e.printStackTrace();
			if(tx!=null){
				try {
					tx.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
					msg=SysConstants.exce_failed;				
				}
			}
			msg=SysConstants.exce_failed;				
		} 	
		return msg;
	}
	public String deleteOneFun(){
		String msg=SysConstants.exce_succeed;
		String sql="";
		String id=this.reh.get("SELECTEDID");
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select function_code from bos_t_function where function_parent_id=#";
		sql=MakeUpUtil.makeUp(id, sql);
		try {
			List list=queryTmplt.iterator(sql);
			if(list.size()>=1){
				msg="对不起,该权限存在下级,不能删除!";
			}else{
				sql="delete from bos_t_function where id=#";
				sql=MakeUpUtil.makeUp(id, sql);
				jdbcTmplt.execute(sql);
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
			msg=SysConstants.exce_failed;
		} 
		return msg;
	}	
	/**
	 * 得到排序号
	 * @param id
	 * @return
	 */
	public String getOrderNumber(String parentId){
		String result="0";
		String sql="";
		Object[] obj=null;		
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select max(order_no) from sm_t_function where function_parent_id=@";
		sql=MakeUpUtil.makeUp(parentId, sql);
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	
		if(obj!=null) result=StringUtil.replaceNull(String.valueOf(obj[0]));
		if("".equals(result)) result="0";		
		result=String.valueOf(Integer.parseInt(result)+1);		
		return result;
	}
	/**
	 * 验证权限编码唯一性
	 * @param id
	 * @param funCode
	 * @return
	 */
	public String checkFunCode(String id,String funCode){		
		String msg="成功";
		String sql="";
		Object[] obj=null;		
		if("".equals(funCode)) return msg;
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select id from sm_t_function where function_code=@ and " +
			"function_code not in(select function_code from sm_t_function where id=@)";
		String[] arr={funCode,id};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		if(obj!=null) msg="对不起,您输入的权限编码已经存在!";		
		return msg;
	}	
	/**
	 * 验证权限名称唯一性
	 * @param id
	 * @param funCode
	 * @return
	 */
	public String checkFunName(String id,String funName){		
		String msg="成功";
		String sql="";
		Object[] obj=null;		
		if("".equals(funName)) return msg;
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select id from sm_t_function where function_name=@ and " +
			"function_name not in(select function_name from sm_t_function where id=@)";
		String[] arr={funName,id};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		if(obj!=null) msg="对不起,您输入的权限名称已经存在!";		
		return msg;
	}	
	//得到下级资源权限列表
	public List getChildFunList(String parentId){
		List list=null;
		String sql="";
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select a.id,a.function_parent_id,a.function_name,"+
	        "case when (select count(*) from sm_t_function where function_parent_id=a.id)>=1"+
	        "then 'false' "+
	        "else 'true' "+
	        "end as isleaf "+
        "from sm_t_function a where a.function_parent_id=@ "+
        "order by a.order_no";
		sql=MakeUpUtil.makeUp(parentId, sql);
		try {
			list=queryTmplt.iterator(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
			LogFactory.getInstance().addLogFile(e);
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return list;
	}	
	//得到下级资源权限XML格式数据
	public String getChildFunXML(){
		String contextPath=this.reh.getRequestContextPath();
        String CODE=this.reh.get("CODE");
        String parentId=this.reh.get("parentId");      
        String target=this.reh.get("target");
        List list=null;      
		list=this.getChildFunList(parentId);
        if(list==null) return "";
        List treelist=new ArrayList();
		String treedoc="";
		XtreeLoader xtreeLoader=new XtreeLoader();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[])list.get(i);
			TreeNode tree=new TreeNode();
			tree.setId(String.valueOf(obj[0]));
			tree.setPid(String.valueOf(obj[1]));
			tree.setText(String.valueOf(obj[2]));   				
			if("false".equals(String.valueOf(obj[3])))
				tree.setSrc(contextPath+"/jsp/support/getChildFunTree.action?CODE="+CODE+"&parentId="+String.valueOf(obj[0])+"&target="+target);
			else tree.setSrc("");
			tree.setAction(contextPath+"/jsp/support/dynamic_page.jsp?CODE="+CODE+"&parentId="+String.valueOf(obj[0]));
			tree.setTarget(target);
			xtreeLoader.addTreeNode(tree);
		}			
    	treedoc=xtreeLoader.getDocString();
		return treedoc;
	}
}
