package com.application.support.biz;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.application.support.model.Organization;
import com.framework.common.base.BaseBiz;
import com.framework.view.tree.TreeNode;
import com.framework.view.tree.XtreeLoader;
import com.sqlMap.Transaction;
import com.sqlMap.id.IdentifierGenerator;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
/**
 * 组织机构业务类
 * @author wangyf
 * @version 1.0
 */
public class OrgMgrBiz extends BaseBiz{
	//得到下级组织机构列表
	public List getChildOrganizationList(String parentId){		
		List list=null;
		String sql="";
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select a.id,a.org_parent_id,a.org_code,a.org_name," +
		"case when (select count(*) from sm_t_org where org_parent_id=a.id)>=1 " +
		"then 'false' " +
		"else 'true' " +
		"end as isleaf " +
		"from sm_t_org a " +
		"where a.org_parent_id=@ order by a.org_code_tree";
		sql=MakeUpUtil.makeUp(parentId, sql);
		try {
			list=queryTmplt.iterator(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				jdbcTmplt.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		return list;
	}
	//得到下级组织机构XML格式数据
	public String getChildOrganizationXML(){
		String contextPath=this.reh.getRequestContextPath();
        String CODE=this.reh.get("CODE");
        String parentId=this.reh.get("parentId");
        String target=this.reh.get("target");     	
        if("".equals(parentId)||parentId==null)
    		parentId="0";	
        if("".equals(target)||target==null)
        	target="dtable";        
		List list=null;	
		list=this.getChildOrganizationList(parentId);
		if(list==null) return "";
		List treelist=new ArrayList();
		String treedoc="";
		XtreeLoader xtreeLoader=new XtreeLoader();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[])list.get(i);			
			TreeNode treeNode=new TreeNode();
			treeNode.setId(String.valueOf(obj[0]));
			treeNode.setPid(String.valueOf(obj[1]));
			treeNode.setText(String.valueOf(obj[3]));   			
			if("true".equals(String.valueOf(obj[4])))
				treeNode.setSrc("");
			else
				treeNode.setSrc(contextPath+"/jsp/support/getChildOrgTree.action?CODE="+CODE+"&parentId="+String.valueOf(obj[0])+"&target="+target);
				
			treeNode.setAction(contextPath+"/jsp/support/dynamic_page.jsp?CODE="+CODE+"&parentId="+String.valueOf(obj[0]));
			treeNode.setTarget(target);
			xtreeLoader.addTreeNode(treeNode);
		}			
    	treedoc=xtreeLoader.getDocString();
    	return treedoc;
	}
	public String getOrgParentId(String orgId){
		String orgParentId="0";
		Object[] obj=null;
		String sql="";
		if("".equals(orgId)) return orgParentId;
		sql="select org_parent_id from sm_t_org where id=@";		
		sql=MakeUpUtil.makeUp(orgId, sql);
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
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
		if(obj!=null) orgParentId=String.valueOf(obj[0]);
		return orgParentId;
	}
	public String addOrg(){		
		String msg="成功";
		String sql="";
		Organization org=new Organization();		
		org.setOrgParentId(this.reh.get("parentId"));
		org.setOrgCode(this.reh.get("orgCode"));
		org.setOrgName(this.reh.get("orgName"));
		org.setOrgType(Long.valueOf(this.reh.get("orgType")));
		org.setLinkName(this.reh.get("linkName"));
		org.setMobilePhone(this.reh.get("mobilePhone"));
		org.setTelePhone(this.reh.get("telePhone"));
		org.setPostcode(this.reh.get("postCode"));
		org.setAddress(this.reh.get("address"));
		org.setOrgDesc(this.reh.get("orgDesc"));
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select org_code_tree from sm_t_org where id=@";
		sql=MakeUpUtil.makeUp(String.valueOf(org.getOrgParentId()), sql);
		Object[] obj=null;
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
			msg="操作失败";
		}
		org.setId(String.valueOf(new IdentifierGenerator().getUUID()));		
		if(obj==null)
			org.setOrgCodeTree(org.getOrgCode());
		else
			org.setOrgCodeTree(String.valueOf(obj[0]).concat(".")+org.getOrgCode());			
		sql="insert into sm_t_org(id,org_parent_id,org_code,org_code_tree,org_name,org_type,link_name,mobile_phone," +
			"tele_phone,postcode,address,org_desc) values(@,@,@,@,@,#,@,@,@,@,@,@)";
		String[] arr={org.getId(),org.getOrgParentId(),org.getOrgCode(),org.getOrgCodeTree(),
				      org.getOrgName(),String.valueOf(org.getOrgType()),org.getLinkName(),org.getMobilePhone(),
				      org.getTelePhone(),org.getPostcode(),org.getAddress(),org.getOrgDesc()};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			jdbcTmplt.execute(sql);
		} catch (SQLException e) {	
			msg="操作失败";
			e.printStackTrace();
		}		
		return msg;
	}
	public String updateOrg(){
		String msg="成功";
		String sql="";
		Organization org=new Organization();
		org.setId(this.reh.get("id"));		
		org.setOrgParentId(this.reh.get("parentId"));
		org.setOrgCode(this.reh.get("orgCode"));
		org.setOrgName(this.reh.get("orgName"));
		org.setOrgType(Long.valueOf(this.reh.get("orgType")));
		org.setLinkName(this.reh.get("linkName"));
		org.setMobilePhone(this.reh.get("mobilePhone"));
		org.setTelePhone(this.reh.get("telePhone"));
		org.setPostcode(this.reh.get("postCode"));
		org.setAddress(this.reh.get("address"));
		org.setOrgDesc(this.reh.get("orgDesc"));
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select org_code_tree from sm_t_org where id=@";
		sql=MakeUpUtil.makeUp(String.valueOf(org.getOrgParentId()), sql);
		Object[] obj=null;
		try {
			obj=queryTmplt.getArray(sql);
		} catch (SQLException e) {			
			e.printStackTrace();
			msg="操作失败";
		}
		if(obj==null)
			org.setOrgCodeTree(org.getOrgCode());
		else
			org.setOrgCodeTree(String.valueOf(obj[0]).concat(".")+org.getOrgCode());	
		sql="update sm_t_org set org_code=@,org_code_tree=@,org_name=@,org_type=#,link_name=@,mobile_phone=@," +
			"tele_phone=@,postcode=@,address=@,org_desc=@ where id=@";
		String[] arr={org.getOrgCode(),org.getOrgCodeTree(),org.getOrgName(),String.valueOf(org.getOrgType()),
				org.getLinkName(),org.getMobilePhone(),org.getTelePhone(),org.getPostcode(),org.getAddress(),
				org.getOrgDesc(),org.getId()};
		sql=MakeUpUtil.makeUp(arr, sql);
		try {
			jdbcTmplt.execute(sql);
		} catch (SQLException e) {	
			msg="操作失败";
			e.printStackTrace();
		}		
		return msg;
	}
	public String deleteOrg(){
		String msg="成功";
		String sql="";
		String[] idArr=this.reh.getArray("id");
		if(idArr==null) return msg;
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		Transaction tx=null;		
		try {
			tx=jdbcTmplt.beginTransaction();			
			for(int i=0;i<idArr.length;i++){
				
				sql="select id from sm_t_user where org_id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);
				List list1=queryTmplt.iterator(sql);
				if(list1.size()>=1){
					msg="对不起,该部门存在用户,不能删除!";
					break;
				}
				
				sql="select id from sm_t_org where org_parent_id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);
				List list=queryTmplt.iterator(sql);
				if(list.size()>=1){
					msg="对不起,该部门存在下级,不能删除!";
					break;
				}else{					
					sql="delete from sm_t_org where id=@";
					sql=MakeUpUtil.makeUp(idArr[i], sql);
					jdbcTmplt.execute(sql);								
				}
			}
			tx.commit();
		} catch (SQLException e) {	
			if(tx!=null){
				try {
					tx.rollback();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}
			msg="操作失败";
			e.printStackTrace();
		}
		return msg;
	}	
	/**
	 * 验证组织机构编码唯一性
	 * @param id
	 * @param orgCode
	 * @return
	 */
	public String checkOrgCode(String id,String orgCode){
		String msg="成功";
		Object obj=null;
		String sql="";
		if("".equals(orgCode)) return msg;
		sql="select org_code from sm_t_org where org_code=@ and " +
		"org_code not in(select org_code from sm_t_org where id=@)";
		String[] arr={orgCode,id};
		sql=MakeUpUtil.makeUp(arr, sql);
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
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
		if(obj!=null) msg="对不起,您输入的部门编码已经存在!";
		return msg;
	}
	/**
	 * 验证组织机构名称唯一性
	 * @param id
	 * @param orgName
	 * @return
	 */
	public String checkOrgName(String id,String orgName){
		String msg="成功";
		Object obj=null;
		String sql="";
		if("".equals(orgName)) return msg;
		sql="select org_name from sm_t_org where org_name=@ and " +
		"org_name not in(select org_name from sm_t_org where id=@)";
		String[] arr={orgName,id};
		sql=MakeUpUtil.makeUp(arr, sql);
		JdbcTmplt jdbcTmplt=this.getReh().getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
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
		if(obj!=null) msg="对不起,您输入的部门名称已经存在!";
		return msg;	
	}
}
