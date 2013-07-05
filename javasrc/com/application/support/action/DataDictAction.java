package com.application.support.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.framework.common.servlet.http.RequestHash;
import com.application.support.biz.DataDictBiz;
import com.controller.action.SupportAction;

/**
 * 数据字典动作类
 * @author wangyf
 * @version 1.0
 */
public class DataDictAction extends SupportAction{
	
	public String getChildDataDictTree(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
	    response.setCharacterEncoding("gb2312");
        response.setContentType("text/xml charset=gb2312");   
        response.setHeader("Pragma","No-cache"); 
    	response.setHeader("Cache-Control","no-cache"); 
    	response.setDateHeader("Expires", 0);   
        String treedoc="";
        RequestHash reh=new RequestHash(request,response);
        DataDictBiz biz=new DataDictBiz();
        biz.setReh(reh);
        try {        
			treedoc=biz.getChildDataDictXML();
			PrintWriter out = response.getWriter();
			out.write(treedoc);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
        return null;
	}
	public String getDictOrderNumber(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);	
		String orderNo="1";
		String parentId=reh.get("parentId");
		try {    		
			DataDictBiz biz=new DataDictBiz();
			biz.setReh(reh);
			orderNo=biz.getOrderNumber(parentId);
			PrintWriter out = response.getWriter();
			out.write(orderNo);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
	public String checkDictCode(){
		HttpServletResponse response=this.getActionContext().getResponse();
		HttpServletRequest request=this.getActionContext().getRequest();
		response.setContentType("text/html;charset=utf-8");
		RequestHash reh=new RequestHash(request,response,false);			
		String msg="成功";
		String id=reh.get("id");
		String parentId=reh.get("parentId");
		String dictCode=reh.get("dictCode");
		try {    		
			DataDictBiz biz=new DataDictBiz();
			biz.setReh(reh);
			msg=biz.checkDictCode(id, parentId, dictCode);
			PrintWriter out = response.getWriter();
			out.write(msg);
	        out.flush();	       
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return null;
	}
}
