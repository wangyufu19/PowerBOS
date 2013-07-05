package com.framework.view.adapter;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
import com.powerbosframework.util.StringUtil;
import com.sqlMap.impl.SqlParserImpl;
import com.sqlMap.parser.SqlParser;
/**
 * 语句对象读取器类
 * @author wangyf
 * @version 1.0
 */
public class StatementReader {
	private RequestHash reh;
	
	public StatementReader(RequestHash reh){
		this.reh=reh;
	}
	public String getStatement(Element statementE){
		StringBuilder buf=new StringBuilder();
		if(statementE==null) return "";
		Element selectE=statementE.getChild("SELECT");
		buf.append(this.getStatementSelect(selectE));		
		buf.append(this.getStatementWhere(statementE));
		buf.append(this.getStatementGroupBy(statementE.getChild("GROUPBY")));
		buf.append(this.getStatementOrderBy(statementE));
		return buf.toString();
	}
	public String getStatement(Element statementE,String diySort){
		StringBuilder buf=new StringBuilder();	
		if(statementE==null) return buf.toString();
		Element selectE=statementE.getChild("SELECT");
		buf.append(this.getStatementSelect(selectE));		
		buf.append(this.getStatementWhere(statementE));
		buf.append(this.getStatementGroupBy(statementE.getChild("GROUPBY")));
		buf.append(this.getStatementOrderBy(statementE,diySort));
		return buf.toString();
	}
	public String getStatement(Element statementE,Object parameterClass,String resultMap,String diySort){
		StringBuilder buf=new StringBuilder();
		if(statementE==null) return "";		
		SqlParser sqlParser=new SqlParserImpl();
		Element selectE=statementE.getChild("SELECT");
		buf.append(sqlParser.parseQuerySQL(this.getStatementSelect(selectE),parameterClass,resultMap));		
		buf.append(sqlParser.parseIsNotEmpty(this.getStatementWhere(statementE), parameterClass));
		buf.append(this.getStatementGroupBy(statementE.getChild("GROUPBY")));
		buf.append(this.getStatementOrderBy(statementE,diySort));
		return buf.toString();
	}
	public String getStatementSelect(Element selectE){
		StringBuilder buf=new StringBuilder();
		if(selectE==null) return "";
		buf.append(StringFormat.replaceNull(selectE.getText()));
		return buf.toString();
	}
	public String getStatementWhere(Element statementE){
		StringBuilder buf=new StringBuilder();
		boolean bool=true;
		String loadCondition="";
		String select="";
		int index=0;
		if(statementE==null) return "";		
		Element wheresE=statementE.getChild("WHERES");
		if(wheresE==null) return "";
		List list=new ArrayList();	
		list=wheresE.getChildren("WHERE");
		if(list==null) return "";		
		Element selectE=statementE.getChild("SELECT");
		if(selectE==null) 
			select="";
		else
			select=StringFormat.replaceNull(selectE.getText());		
		for(int i=0;i<list.size();i++){
			Element whereE=(Element)list.get(i);				
			loadCondition=StringFormat.replaceNull(whereE.getAttributeValue("loadCondition"));			
			//解析显示条件			
			if(loadCondition.indexOf("fun.get(")!=-1){
				String replacement=RequestUtil.getRequestParameterName(loadCondition);
			    String target=RequestUtil.getRequestParamNameStr(loadCondition);	 
		        replacement=reh.get(replacement);  
		        loadCondition=StringFormat.replace(loadCondition,target, replacement);	
			}
			if(loadCondition.indexOf("fun.getSession")!=-1){
				loadCondition=RequestUtil.formatSession(reh.getSessionHash(), loadCondition);				
			}
			
			bool=StringFormat.convertOfBoolean(StringFormat.replaceNull(loadCondition));  
			
			if(bool&&!StringFormat.replaceNull(whereE.getText()).equals("")){
				index++;
				if(select.indexOf("where")!=-1||select.indexOf("WHERE")!=-1){
					if(index==1){									
						buf.append(" and "+whereE.getText());			
					}else{
						buf.append(" and "+whereE.getText());
					}
				}else{
					if(index==1){	
						buf.append(" where "+whereE.getText());
					}else{
						buf.append(" and "+whereE.getText());
					}
				}				
			}						
		}			
		return buf.toString();
	}
	public String getStatementGroupBy(Element groupbyE){
		StringBuilder buf=new StringBuilder();
		if(groupbyE==null) return "";
		buf.append(" "+StringFormat.replaceNull(groupbyE.getText()));
		return buf.toString();
	}	
	public String getStatementOrderBy(Element statementE){
		StringBuilder buf=new StringBuilder();
		if(statementE==null) return "";
		List list=statementE.getChildren("ORDERBY");
		if(list==null) return "";
		for(int i=0;i<list.size();i++){
			Element orderbyE=(Element)list.get(i);
			buf.append(" "+StringUtil.replaceNull(orderbyE.getText()));		
		}
		return buf.toString();
	}
	public String getStatementOrderBy(Element statementE,String diySort){
		StringBuilder buf=new StringBuilder();
		if(statementE==null) return "";
		List list=statementE.getChildren("ORDERBY");
		if(list==null) return "";
		for(int i=0;i<list.size();i++){
			Element orderbyE=(Element)list.get(i);
			//解析显示条件	
			boolean bool=true;
			String condition="";
			condition=StringFormat.replaceNull(orderbyE.getAttributeValue("condition"));								
			if(condition.indexOf("fun.getSession")!=-1){
				condition=RequestUtil.formatSession(reh.getSessionHash(), condition);				
			}		
			bool=StringFormat.convertOfBoolean(StringFormat.replaceNull(condition));  
			if(bool) buf.append(" "+StringFormat.replaceNull(orderbyE.getText()));				
		}
		return buf.toString();
	}	
}
