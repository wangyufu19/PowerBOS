package com.application.console.action;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;

import com.controller.action.ActionContext;
import com.controller.action.SupportAction;
import com.framework.common.util.SysConstants;
import com.framework.common.util.XMLUtil;
import com.powerbosframework.util.StringUtil;

public class SysDatabseConfigAction extends SupportAction{
	
	public String getSysDatabaseConfig(){
		
		XMLUtil XMLUtil=new XMLUtil();	
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"database_configuration.xml";
		Document doc=XMLUtil.parse(new File(configuration));
		if(doc==null) return this.SUCCESS;
		Element rootE=doc.getRootElement();	    	
		if(rootE==null) return this.SUCCESS;	
		Element databaseSetE=rootE.getChild("database_set");
		if(databaseSetE==null) return this.SUCCESS;
		Element dialectE=databaseSetE.getChild("dialect");
		Element driverE=databaseSetE.getChild("driver");
		Element urlE=databaseSetE.getChild("url");
		Element usernameE=databaseSetE.getChild("username");
		Element passwordE=databaseSetE.getChild("password");
	
		ActionContext actionContext=this.getActionContext();
		if(dialectE!=null) actionContext.set("dialect", StringUtil.replaceNull(dialectE.getTextTrim()));
		if(driverE!=null) actionContext.set("driver", StringUtil.replaceNull(driverE.getTextTrim()));
		if(urlE!=null) actionContext.set("url", StringUtil.replaceNull(urlE.getTextTrim()));
		if(usernameE!=null) actionContext.set("username", StringUtil.replaceNull(usernameE.getTextTrim()));
		if(passwordE!=null) actionContext.set("password", StringUtil.replaceNull(passwordE.getTextTrim()));
		
		return this.SUCCESS;
	}

	public String saveSysDatabaseConfig(){
		XMLUtil XMLUtil=new XMLUtil();	
		ActionContext actionContext=this.getActionContext();
		String dialect=actionContext.get("dialect");
		String driver=actionContext.get("driver");
		String url=actionContext.get("url");
		String username=actionContext.get("username");
		String password=actionContext.get("password");
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"database_configuration.xml";
		Document doc=XMLUtil.parse(new File(configuration));
		if(doc==null) return this.getSysDatabaseConfig();
		Element rootE=doc.getRootElement();	    	
		if(rootE==null) return this.getSysDatabaseConfig();	
		Element databaseSetE=rootE.getChild("database_set");
		if(databaseSetE==null) return this.getSysDatabaseConfig();
		
		Element dialectE=databaseSetE.getChild("dialect");
		Element driverE=databaseSetE.getChild("driver");
		Element urlE=databaseSetE.getChild("url");
		Element usernameE=databaseSetE.getChild("username");
		Element passwordE=databaseSetE.getChild("password");
		
		if(dialectE!=null){
			dialectE.setText(dialect);
		}
		if(driverE!=null){
			driverE.setText(driver);
		}
		if(urlE!=null){
			urlE.setText(url);
		}
		if(usernameE!=null){
			usernameE.setText(username);
		}
		if(passwordE!=null){
			passwordE.setText(password);
		}
		XMLUtil.output(doc, configuration);
		return this.getSysDatabaseConfig();
	}
}
