package com.framework.view.form.action;
import java.util.Map;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.config.XMLData;
import com.framework.view.form.action.ActionProcessor;
import com.framework.view.util.StringFormat;

/**
 * 表单事件请求类
 * @author wangyf
 * @version 1.0
 */
public class ActionRequest {
	public RequestHash reh;
	private String freshTree;
	private String freshOpener;
	private String closeWindow;
	
	public ActionRequest(RequestHash reh){
		this.reh=reh;
	}
	public String execute(){
		String code=reh.get("CODE");
		String action=reh.get("action");
		Map actions=(Map)XMLData.getXMLDataActions().get(code);
		Element actionE=(Element)actions.get(reh.get("action"));			
		if(actionE!=null){
			this.freshTree=StringFormat.replaceNull(actionE.getAttributeValue("freshTree"));
			this.freshOpener=StringFormat.replaceNull(actionE.getAttributeValue("freshOpener"));
			this.closeWindow=StringFormat.replaceNull(actionE.getAttributeValue("closeWindow"));
		}		
		ActionProcessor processor=new ActionProcessor(reh);		
		String msg=processor.process(code,action);		
		return msg;
	}
	public String getFreshTree() {
		return freshTree;
	}

	public void setFreshTree(String freshTree) {
		this.freshTree = freshTree;
	}

	public String getFreshOpener() {
		return freshOpener;
	}

	public void setFreshOpener(String freshOpener) {
		this.freshOpener = freshOpener;
	}

	public String getCloseWindow() {
		return closeWindow;
	}

	public void setCloseWindow(String closeWindow) {
		this.closeWindow = closeWindow;
	}
}
