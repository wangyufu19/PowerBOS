package com.application.support.biz;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.framework.common.base.BaseBiz;
import com.framework.view.tree.TreeNode;
import com.framework.view.tree.XtreeLoader;
import com.powerbosframework.util.StringUtil;
import com.sqlMap.Transaction;
import com.sqlMap.jdbc.JdbcTmplt;
import com.sqlMap.jdbc.QueryTmplt;
import com.sqlMap.jdbc.util.MakeUpUtil;
/**
 * 数据字典业务类
 * @author wangyf
 * @version 1.0
 */
public class DataDictBiz extends BaseBiz{
	/**
	 * 删除字典
	 * @return
	 */
	public String deleteDict(){
		String msg="成功";
		String[] idArr=reh.getArray("id");
		String sql="";	
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();	
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		Transaction tx=null;		
		try {			
			tx=jdbcTmplt.beginTransaction();
			for(int i=0;i<idArr.length;i++){
				sql="select id from sm_t_data_dict where dict_parent_id=@";
				sql=MakeUpUtil.makeUp(idArr[i], sql);
				List list=queryTmplt.iterator(sql);
				if(list.size()>=1){
					msg="对不起,该字典存在下级,不能删除";
					break;
				}else{
					sql="delete from sm_t_data_dict where id=@";
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
	public String saveDict(){
		String msg="成功";
		String[] pidArr=reh.getArray("pid");
		String[] dictCodeArr=reh.getArray("dictCode");
		String[] dictNameArr=reh.getArray("dictName");
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();		
		try {			
			if(pidArr!=null){
				for(int i=0;i<pidArr.length;i++){
					String sql="update sm_t_data_dict set dict_code=@,dict_name=@ where id=@";
					String[] arr={dictCodeArr[i],dictNameArr[i],pidArr[i]};
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
		sql="select max(order_no) from sm_t_data_dict where dict_parent_id=@";
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
	 * 验证字典编码唯一性
	 * @param id
	 * @param typeCode
	 * @return
	 */
	public String checkDictCode(String id,String parentId,String dictCode){
		String msg="成功";
		Object obj=null;
		String sql="";
		if("".equals(dictCode)) return msg;
		sql="select id from sm_t_data_dict where dict_code=@ and " +
		"dict_code not in(select dict_code from sm_t_data_dict where id=@) and dict_parent_id=@";
		String[] arr={dictCode,id,parentId};
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
		if(obj!=null) msg="对不起,您输入的字典编码已经存在!";
		return msg;
	}
	public List getChildDataDict(String parentId){
		List list=null;
		String sql="";
		JdbcTmplt jdbcTmplt=this.reh.getJdbcTmplt();
		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
		sql="select a.id,a.dict_parent_id,a.dict_name," +
		"case when (select count(*) from sm_t_data_dict where dict_parent_id=a.id)>=1 " +
		"then 'false' " +
		"else 'true' " +
		"end as isleaf " +
		"from sm_t_data_dict a " +
		"where a.dict_parent_id=@ order by a.order_no";
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
	public String getChildDataDictXML(){
		String contextPath=this.reh.getRequestContextPath();
        String CODE=this.reh.get("CODE");
        String parentId=this.reh.get("parentId");
        String target=this.reh.get("target");     	
        if("".equals(parentId)||parentId==null)
    		parentId="0";	
        if("".equals(target)||target==null)
        	target="dtable";        
		List list=null;	
		list=this.getChildDataDict(parentId);
		if(list==null) return "";
		List treelist=new ArrayList();
		String treedoc="";
		XtreeLoader xtreeLoader=new XtreeLoader();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[])list.get(i);			
			TreeNode treeNode=new TreeNode();
			treeNode.setId(String.valueOf(obj[0]));
			treeNode.setPid(String.valueOf(obj[1]));
			treeNode.setText(String.valueOf(obj[2]));   			
			if("true".equals(String.valueOf(obj[3])))
				treeNode.setSrc("");
			else
				treeNode.setSrc(contextPath+"/jsp/support/getChildDataDictTree.action?CODE="+CODE+"&parentId="+String.valueOf(obj[0])+"&target="+target);
				
			treeNode.setAction(contextPath+"/jsp/support/dynamic_page.jsp?CODE="+CODE+"&parentId="+String.valueOf(obj[0]));
			treeNode.setTarget(target);
			xtreeLoader.addTreeNode(treeNode);
		}			
    	treedoc=xtreeLoader.getDocString();
    	return treedoc;
	}
}
