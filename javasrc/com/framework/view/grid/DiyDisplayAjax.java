package com.framework.view.grid;
//package com.framework.widget.grid;
//import java.sql.SQLException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//import org.jdom.Element;
//
//import com.application.support.domain.DiyDisplay;
//import com.framework.common.ajax.buffalo.AjaxService;
//import com.framework.common.servlet.http.RequestHash;
//import com.framework.common.util.StringUtil;
//import com.powerbos.framework.assemble.data.Column;
//import com.powerbos.framework.assemble.data.ColumnParser;
//import com.powerbos.framework.assemble.util.HtmlUtil;
//import com.powerbos.framework.configuration.WformXMLData;
//import com.sqlMap.Transaction;
//import com.sqlMap.jdbc.JdbcTmplt;
//import com.sqlMap.jdbc.QueryTmplt;
///**
// * 功能说明:显示自定义列
// * @author wangyf 
// * @version 1.0
// */
//public class DiyDisplayAjax extends AjaxService{
//	private static final String AJAX_RETURN_MSG="ajaxReturnMsg"; 
//	private static final String AJAX_RETURN_DIY_DISPLAY="ajaxReturnDiyDisplay";
//	private static final String AJAX_RETURN_CODE="ajaxReturnCode";
//	
//	private RequestHash reh;
//	
//	public Map getDiyColumn(String code,String L0){
//		String msg="成功";
//		Map map=new HashMap();
//		try{		
//			reh=super.getReh();			
//			map.put(AJAX_RETURN_MSG, msg);
//			map.put(AJAX_RETURN_DIY_DISPLAY, this.getDiyAllColumns(code, L0));
//		}catch(Exception e){
//			map.put(AJAX_RETURN_MSG, "失败");
//			map.put(AJAX_RETURN_DIY_DISPLAY,"");
//			e.printStackTrace();
//		}			
//		return map;
//	}
//	public String getDiyAllColumns(String code,String L0){		
//		StringBuilder buf=new StringBuilder();		
//		Element codeE=(Element)WformXMLData.getWebForms().get(code);		
//		if(codeE==null) return "";
//		Element listE=codeE.getChild("LIST");
//		if(listE==null) return "";				
//		Element fieldsE=listE.getChild("FIELDS");
//		ColumnParser columnParser=new ColumnParser();
//		columnParser.parse(fieldsE);
//		Map columns=columnParser.getDisplayColumns();	
//		buf.append("<table ")
//		   .append("id="+"\""+L0+"\" ")
//		   .append("class="+"\"tlistbody\" ")
//		   .append("width="+"\"100%\" ")
//		   .append("border="+"\"0\" ")
//		   .append("align="+"\"center\" ")
//		   .append("cellPadding="+"\"0\" ")
//		   .append("cellSpacing="+"\"1\">\n")
//		   .append("<thead>\n")	
//		   .append("<tr class="+"\"tablehead\">\n");	
//		buf.append("<th>\n")
//		   .append("&nbsp;")
//	       .append("</th>\n")
//		   .append("<th>\n")
//		   .append("列名")
//		   .append("</th>\n")		  
//		   .append("</tr>\n")
//		   .append("</thead>\n")	
//		   .append("<tbody>\n");	
//		String sql="select * from bos_t_diy_display where user_id=$userId$ and diy_code=$diyCode$";
//		String returnClass="com.powerbos.application.support.domain.DiyDisplay";
//		String resultMap="diyDisplayResult";
//		DiyDisplay vo=new DiyDisplay();
//		vo.setUserId(Long.valueOf(reh.getLoginInfo().getUserId()));
//		vo.setDiyCode(code);
//		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
//		QueryTmplt queryTmplt=jdbcTmplt.createQueryTmplt();
//		List list=null;
//		try {
//			list=queryTmplt.iterator(sql, vo, returnClass, resultMap);
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		} finally{
//			try {
//				jdbcTmplt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		Map diycolumns=new HashMap();
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				DiyDisplay diyDisplay=(DiyDisplay)list.get(i);
//				diycolumns.put(diyDisplay.getDiyColumn(), diyDisplay);
//			}
//		}
//		buf.append(this.getDiyColumns(columns,diycolumns));		
//		return StringUtil.replaceNull(buf.toString());
//	}
//	public String getDiyColumns(Map columns,Map diycolumns){
//		StringBuilder buf=new StringBuilder();
//		HtmlUtil htmlUtil=new HtmlUtil(reh);
//		int index=0;
//		for(Iterator it=columns.keySet().iterator();it.hasNext();){
//			String key=it.next().toString();
//			Column column=(Column)columns.get(key);
//			String css="rowOdd";
//			if(index%2==1){
//				css="rowEven";
//			}
//			buf.append("<tr ")
//			   .append("class="+"\""+css+"\" ")
//			   .append("onMouseOver="+"\""+"this.className="+"\'"+"rowHover"+"\'"+"\" ")
//			   .append("onmouseout="+"\""+"this.className="+"\'"+css+"\'"+"\"")
//			   .append(">\n");	
//			buf.append("<td>");
//			column.setValue(column.getId());
//			if(diycolumns.containsKey(column.getId()))
//				column.setChecked("checked");
//			if(diycolumns==null) column.setChecked("checked");
//			if(diycolumns.size()<1)column.setChecked("checked");
//			column.setId("colId");
//			column.setName("colId");
//			column.setDisplayType("checkbox");			
//			buf.append(htmlUtil.getHtmlContent(column));			
//			buf.append("</td>\n");
//			buf.append("<td>");		
//			column.setId("colName");
//			column.setName("colName");
//			column.setDisplayType("hidden");
//			column.setValue(column.getDisplayName());			
//			buf.append(htmlUtil.getHtmlContent(column));						
//			buf.append("</td>\n");	
//			buf.append("</tr>\n");
//			index++;
//		}		
//		buf.append("</tbody>\n");
//		buf.append("</table>\n");			
//		return buf.toString();
//	}
//	public Map saveDiyColumn(String code,String colStr){		
//		String msg="成功";
//		Map map=new HashMap();
//		try{
//			reh=super.getReh();			
//			this.setDiyAllColumns(code, colStr);
//			map.put(AJAX_RETURN_MSG, msg);
//			map.put(AJAX_RETURN_CODE, code);
//		}catch(Exception e){
//			map.put(AJAX_RETURN_MSG, "失败");
//			e.printStackTrace();
//		}
//		return map;
//	}
//	public void setDiyAllColumns(String code,String colStr){			
//		String[] colArray=StringUtil.split(StringUtil.trimRight(colStr, ','), ',');
//		if(colArray==null) return;
//		if(colArray.length<1) return;
//		String sql="";
//		String resultMap="diyDisplayResult";
//		DiyDisplay vo=new DiyDisplay();		
//		Transaction tx=null;
//		JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
//		sql="delete from bos_t_diy_display where user_id=$userId$ and diy_code=$diyCode$";
//		vo.setDiyCode(code);
//		vo.setUserId(Long.valueOf(reh.getLoginInfo().getUserId()));
//		try {
//			jdbcTmplt.execute(sql, vo, resultMap);
//			for(int i=0;i<colArray.length;i++){
//				sql="insert into bos_t_diy_display(id,user_id,diy_code,diy_column) values($id$,$userId$,$diyCode$,$diyColumn$)";
//				vo.setDiyColumn(colArray[i]);
//				jdbcTmplt.insert(sql, vo, resultMap);
//			}
//		} catch (SQLException e) {		
//			if(tx!=null)
//				try {
//					tx.rollback();
//				} catch (SQLException e1) {					
//					e1.printStackTrace();
//				}
//			e.printStackTrace();
//		} finally{
//			try {
//				jdbcTmplt.close();
//			} catch (SQLException e) {				
//				e.printStackTrace();
//			}
//		}		
//	}
//}
