package com.application.console.action;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;

import com.controller.action.ActionContext;
import com.controller.action.SupportAction;
import com.framework.common.util.SysConstants;
import com.framework.common.util.XMLUtil;

public class SysConfigurationAction extends SupportAction{
	

	public String getSystemConfig(){
		ActionContext actionContext=this.getActionContext();
		actionContext.set("productName", SysConstants.product_name);
		actionContext.set("versionNum", SysConstants.version_num);
		actionContext.set("runtimeServer", SysConstants.runtime_server);
		if("true".equals(SysConstants.runtime_mode)){
			actionContext.set("runtimeMode","开发模式");
		}else{
			actionContext.set("runtimeMode","部署模式");
		}		
		actionContext.set("charsetCode", SysConstants.charset_code);
		actionContext.set("debugLog", SysConstants.debug_log);
		actionContext.set("sessionLimit", SysConstants.session_limit);
		actionContext.set("sessionLog", SysConstants.session_log);
		actionContext.set("sessionTime", SysConstants.session_time);
		actionContext.set("uploadPath", SysConstants.upload_path);
		actionContext.set("uploadAllowableExtensions", SysConstants.upload_allowable_extensions);
		return this.SUCCESS;
	}
	
	public String saveSysConfig(){
		ActionContext actionContext=this.getActionContext();
		String charsetCode=actionContext.get("charsetCode");
		String debugLog=actionContext.get("debugLog");
		String sessionLimit=actionContext.get("sessionLimit");
		String sessionLog=actionContext.get("sessionLog");
		String sessionTime=actionContext.get("sessionTime");
		String uploadPath=actionContext.get("uploadPath");
		String uploadAllowableExtensions=actionContext.get("uploadAllowableExtensions");

		XMLUtil XMLUtil=new XMLUtil();	
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"system_configuration.xml";
		Document doc=XMLUtil.parse(new File(configuration));
		if(doc==null) return this.getSystemConfig();
		Element rootE=doc.getRootElement();	    	
		if(rootE==null) return this.getSystemConfig();		
		
		Element charsetCodeE=rootE.getChild("charset_code");
		Element debugLogE=rootE.getChild("debug_log");
		Element sessionLimitE=rootE.getChild("session_limit");
		Element sessionLogE=rootE.getChild("session_log");
		Element sessionTimeE=rootE.getChild("session_time");
		
		
		if(charsetCodeE!=null){
			SysConstants.charset_code=charsetCode;
			charsetCodeE.setText(charsetCode);
		}
		if(debugLogE!=null){
			SysConstants.debug_log=debugLog;
			debugLogE.setText(debugLog);
		}
		if(sessionLimitE!=null){
			SysConstants.session_limit=sessionLimit;	
			sessionLimitE.setText(sessionLimit);
		}
		if(sessionLogE!=null){
			SysConstants.session_log=sessionLog;
			sessionLogE.setText(sessionLog);
		}
		if(sessionTimeE!=null){
			SysConstants.session_time=sessionTime;
			sessionTimeE.setText(sessionTime);
		}
		
		
		Element fileTransactionE=rootE.getChild("file_transaction");
		if(fileTransactionE!=null){
			Element uploadPathE=fileTransactionE.getChild("upload_path");
			Element allowableExtensionsE=fileTransactionE.getChild("upload_allowable_extensions");			
			if(uploadPathE!=null){
				SysConstants.upload_path=uploadPath;
				uploadPathE.setText(uploadPath);
			}
			if(allowableExtensionsE!=null){
				SysConstants.upload_allowable_extensions=uploadAllowableExtensions;		
				allowableExtensionsE.setText(uploadAllowableExtensions);
			}
		}
		XMLUtil.output(doc, configuration);
		return this.getSystemConfig();
	}
	

}
