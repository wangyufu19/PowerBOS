package com.framework.view.form;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
/**
 * 表单脚本类
 * @author wangyf
 * @version 1.0
 */
public class FormScript {
	private RequestHash reh;
	
	public FormScript(RequestHash reh){
		this.reh=reh;
	}
	public String render(Element scriptE){
		if(scriptE==null) return "";
		StringBuilder buf=new StringBuilder();
		buf.append("<script LANGUAGE=\"JavaScript\" type=\"text/javascript\">\n");
		String script=StringFormat.replaceNull(scriptE.getText());	
		while(script.indexOf("fun.getSession")!=-1){
			script=RequestUtil.formatSession(reh.getSessionHash(), script);			
		}		
		while(script.indexOf("fun.get")!=-1){
			String replacement=RequestUtil.getRequestParameterName(script);
		    String target=RequestUtil.getRequestParamNameStr(script);	 
	        replacement=reh.get(replacement);        
	        script=StringFormat.replace(script,target, replacement);		
		}
		while(script.indexOf("&nbsp;")!=-1){
			script=script.replace("&nbsp;", "");
		}
		buf.append(script+"\n");
		buf.append("</script>\n");
		return StringFormat.replaceNull(buf.toString());
	}
}
