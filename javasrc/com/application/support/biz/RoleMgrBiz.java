package com.application.support.biz;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.sqlMap.Transaction;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
import com.framework.common.base.BaseBiz;
import com.application.support.biz.SessionMonitor;


/**
 * 角色管理业务类
 * @author wangyf
 * @version 1.0
 */
public class RoleMgrBiz extends BaseBiz{
	/**
	 * 删除系统角色
	 * @return
	 */
	public String deleteOneRole(){
		String msg="成功";
		String roleId=reh.get("SELECTEDID");
		String sql="";
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select * from bos_t_user_role where role_id=#";
		sql=MakeUpUtil.makeUp(roleId, sql);
		Object obj=null;
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
			return "操作失败";
		}
		if(obj!=null) return "对不起,该角色已经授于用户!";		
		Transaction tx=null;
		try {
			tx=jdbcTmplt.beginTransaction();
			sql="delete from bos_t_role where id=#";
			sql=MakeUpUtil.makeUp(roleId, sql);
			jdbcTmplt.execute(sql);
			sql="delete from bos_t_role_function where role_id=#";
			sql=MakeUpUtil.makeUp(roleId, sql);
			tx.commit();
		} catch (SQLException e) {		
			if(tx!=null){
				try {
					tx.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return "操作失败";
		}
		return msg;
	}
	public String saveRole(){
		String msg="成功";
		String[] pidArr=reh.getArray("pid");
		String[] roleNameArr=reh.getArray("roleName");
		String[] roleTypeArr=reh.getArray("roleType");
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();		
		try {			
			if(pidArr!=null){
				for(int i=0;i<pidArr.length;i++){
					String sql="update sm_t_role set role_name=@,role_type=@ where id=@";
					String[] arr={roleNameArr[i],roleTypeArr[i],pidArr[i]};
					sql=MakeUpUtil.makeUp(arr, sql);
					jdbcTmplt.execute(sql);
				}			
			}		
	    } catch (SQLException e) {			
			msg="操作失败";
			e.printStackTrace();
	    }	
		return msg;
	}
	/**
	 * 验证角色编码唯一性
	 * @param id
	 * @param roleCode
	 * @return
	 */
	public String checkRoleCode(String id,String roleCode){		
		String msg="成功";
		String sql="";
		Object[] obj=null;		
		if("".equals(roleCode)) return msg;
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select * from sm_t_role where role_code=@ and " +
			"role_code not in(select role_code from sm_t_role where id=@)";
		String[] arr={roleCode,id};
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
		if(obj!=null) msg="对不起,您输入的角色编码已经存在!";		
		return msg;
	}
	/**
	 * 得到当前用户角色权限列表
	 * @param roleId
	 * @return
	 */
	public String getRolePerviewListByUser(String roleId){	
		StringBuffer buf=new StringBuffer();
		buf.append("var xtree=new WebFXTree('功能权限树导航')\n");
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		try {
			this.putChildPerviewList(queryTmplt, roleId,"0",buf);
		} catch (NumberFormatException e) {		
			e.printStackTrace();
		} catch (SQLException e) {		
			e.printStackTrace();
		}		
		buf.append("document.write(xtree)\n");			
		return buf.toString();
	}
	/**
	 * 得到当前用户角色权限列表
	 * @param queryTmplt
	 * @param roleId
	 * @param pId
	 * @param buf
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void putChildPerviewList(QueryTmplt queryTmplt,String roleId,String pId,StringBuffer buf) throws NumberFormatException, SQLException{
		List list=null;				
		String sql="select a.id,a.function_parent_id,a.function_code,a.function_name,(case (select count(b.function_id) "+
            "from sm_t_role_function b where b.role_id=@ and b.function_id=a.id) when 1 then 'true' else 'false' end) as checked "+ 
            "from sm_t_function a where a.function_parent_id=@ order by a.order_no";
		String[] arr={roleId,pId};
		sql=MakeUpUtil.makeUp(arr, sql);
		list=queryTmplt.iterator(sql);
		if(list==null) list=new ArrayList();
		SessionMonitor sessionMonitor=new SessionMonitor();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj=(Object[])list.get(i);		
				//if(sessionMonitor.getLoginInfo(reh.getSession()).getPerviews().containsKey(function.getFunctionCode())){
					String id=String.valueOf(obj[0]);					
					String parentId=String.valueOf(obj[1]);
					String name=String.valueOf(obj[3]);	
					String checked=String.valueOf(obj[4]);	
					String xtree="";;
					String parentXtree="";;
					if(parentId.equals("0")){						
						xtree="xtree"+id.substring(0, 8);
						parentXtree="xtree";
					}else{
						xtree="xtree"+id.substring(0, 8);
						parentXtree="xtree"+parentId.substring(0, 8);
					}				
					if("true".equals(checked)){
						buf.append(xtree+"=new WebFXCheckBoxTreeItem('&nbsp"+name+"','"+id+"','','"+checked+"')\n");
					}else
						buf.append(xtree+"=new WebFXCheckBoxTreeItem('&nbsp"+name+"','"+id+"','','')\n");				
					buf.append(parentXtree+".add("+xtree+")\n");					
					this.putChildPerviewList(queryTmplt,roleId,id, buf);
				//}							
			}
		}		
	}
	/**
	 * 保存角色权限
	 * @return
	 */
	public String saveRolePerviewList(){
		String msg="成功";
		String[] checkboxid=reh.getArray("checkboxid");
		String roleId=reh.get("roleId");
		if(checkboxid==null) return msg;		
		String sql="";	
		sql="delete from sm_t_role_function where role_id=@";
		sql=MakeUpUtil.makeUp(roleId, sql);
		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();		
		Transaction tx=null;
		try {
			tx=jdbcTmplt.beginTransaction();
			jdbcTmplt.execute(sql);
			for(int i=0;i<checkboxid.length;i++){		
				sql="insert into sm_t_role_function(id,role_id,function_id) values(@,@,@)";
				String id=String.valueOf(new IdentifierGenerator().getUUID());			
				String[] arr={id,roleId, checkboxid[i]};
				sql=MakeUpUtil.makeUp(arr, sql);
				jdbcTmplt.execute(sql);						
			}
			tx.commit();
		} catch (SQLException e1) {		
			if(tx!=null){
				try {
					tx.rollback();
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
			e1.printStackTrace();
			msg="失败";
		} 			
		return msg;
	}

}
