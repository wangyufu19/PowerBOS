package com.application.console.action;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.jdom.Element;

import com.controller.action.SupportAction;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.framework.common.xmldoc.DocFactory;
import com.framework.common.xmldoc.DocObject;
import com.framework.view.Viewport;
import com.framework.view.adapter.ColumnDataReader;
import com.framework.view.adapter.ColumnEditorObject;
import com.framework.view.adapter.ColumnObject;
import com.framework.view.adapter.ColumnReader;
import com.framework.view.form.action.ActionRequest;
import com.framework.view.util.StringFormat;

/**
 * 动态页面执行类
 * @author wangyf
 * @version 1.0
 */
public class DynamicPageAction extends SupportAction{
	
	public String submitDynPageAction(){
		String msg="成功";
		String status="true";		
		HttpServletRequest request=this.getActionContext().getRequest();
		HttpServletResponse response=this.getActionContext().getResponse();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);		
		ActionRequest actionRequest=new ActionRequest(reh);		
		msg=actionRequest.execute();
		if(!msg.equals(SysConstants.exce_succeed)){
			status="false";
		}
		String successMsg="{\"status\":\""+status+"\",\"msg\":\""+msg+"\",\"freshOpener\":\""+actionRequest.getFreshOpener()+"\",\"closeWindow\":\""+actionRequest.getCloseWindow()+"\"}";
		try {
			
			PrintWriter out= response.getWriter();
			out.write(successMsg);
	        out.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return null;
	}
	public String loadDynPageData(){
		String data="";
		HttpServletRequest request=this.getActionContext().getRequest();
		HttpServletResponse response=this.getActionContext().getResponse();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);				
		Viewport viewport=new Viewport(reh);							
		data=viewport.render();			
		try {
			PrintWriter out= response.getWriter();
			out.write(data);
	        out.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return null;
	}
	
	public String loadSelectEditor(){
		HttpServletRequest request=this.getActionContext().getRequest();
		HttpServletResponse response=this.getActionContext().getResponse();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);		
		String xmlResource=reh.get("xmlResource");
		String key=reh.get("key");	
		String value=reh.get("value");			
		
		String resource=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+xmlResource;
		DocObject docObject=DocFactory.getInstace().getDocObject(resource);
		Element colE=docObject.getDocElement(key);
		ColumnReader columnReader=new ColumnReader(reh);
		ColumnObject column=columnReader.readColumnObject(colE);
		ColumnEditorObject columnEditorObject=column.getColumnEditorObject();	
		column.setDocType(columnEditorObject.getEditorDocType());
		ColumnDataReader columnDataReader=new ColumnDataReader(reh);			
		column=columnDataReader.read(column, null);
		StringBuilder buf=new StringBuilder();
		List list=column.getOption();
		buf.append("<option value=\"\">请选择</option>");
		for(int i=0;i<list.size();i++){
			Map opt=(Map)list.get(i);
			String val=StringFormat.replaceNull(String.valueOf((opt.keySet().toArray())[0]));
			String txt=StringFormat.replaceNull(String.valueOf(opt.get(val)));		
			buf.append("<option ");
			if(value.equals(txt))
				buf.append("value="+"\""+val+"\" selected>");
			else
				buf.append("value="+"\""+val+"\">");		
			buf.append(txt);
			buf.append("</option>");					
		}		
		try {
			PrintWriter out= response.getWriter();
			out.write(buf.toString());
	        out.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}		
		return null;
	}	
}
