package com.framework.config;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import org.jdom.Document;
import org.jdom.Element;

import com.framework.common.encrypt.Md5Encrypt;
import com.framework.common.util.SysConstants;
import com.framework.common.util.ClassProcessor;
import com.framework.common.util.XMLUtil;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;
import com.powerbosframework.util.StringUtil;

/**
 * 加载平台配置类
 * @author youfu.wang
 * @version 1.0
 */
public class Configuration {
	private static Logger log=LogFactory.getInstance();
	private XMLUtil XMLUtil=new XMLUtil();	
	 
	
	public Configuration(){
		
	}
	//加载配置
	public void load(){
		//加载系统资源配置
		this.loadSystemConfiguration();
		//加载页面资源数据
		XMLData XMLData=new XMLData();
		XMLData.load();
		//加载自定义插件配置
		try {
			this.loadPluginConfiguration();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {			
			e.printStackTrace();
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {			
			e.printStackTrace();
		} catch (InvocationTargetException e) {			
			e.printStackTrace();
		}
	}
	//加载环境配置
	private void loadSystemConfiguration(){	
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"system_configuration.xml";		
		Document doc=XMLUtil.parse(new File(configuration));
		if(doc==null) return;
		Element rootE=doc.getRootElement();	    	
		if(rootE==null) return;		
		//加载产品基本属性
		Element productInfoE=rootE.getChild("product_info");
		if(productInfoE!=null){
			Element productNameE=productInfoE.getChild("product_name");
			Element versionNumE=productInfoE.getChild("version_num");
			Element technologyCorpE=productInfoE.getChild("technology_corp");
			Element copyrightCorpE=productInfoE.getChild("copyright_corp");
			if(productNameE!=null) SysConstants.product_name=productNameE.getText();
			if(versionNumE!=null) SysConstants.version_num=versionNumE.getText();
			if(technologyCorpE!=null) SysConstants.technology_corp=technologyCorpE.getText();
			if(copyrightCorpE!=null) SysConstants.copyright_corp=copyrightCorpE.getText();
			log.info("product_name: "+SysConstants.product_name);
			log.info("version_num: "+SysConstants.version_num);
			log.info("technology_corp: "+SysConstants.technology_corp);
			log.info("copyright_corp: "+SysConstants.copyright_corp);
		}
		//加载运行环境属性
		Element runtimeEnvironmentE=rootE.getChild("runtime_environment");
		if(runtimeEnvironmentE!=null){
			Element runtimeServerE=runtimeEnvironmentE.getChild("runtime_server");
			Element runtimeModeE=runtimeEnvironmentE.getChild("runtime_mode");
			if(runtimeServerE!=null) SysConstants.runtime_server=runtimeServerE.getText();
			if(runtimeModeE!=null) SysConstants.runtime_mode=runtimeModeE.getText();
			log.info("runtime_server: "+SysConstants.runtime_server);
			log.info("runtime_mode: "+SysConstants.runtime_mode);
		}
		//加载安全会话属性
		Element securitySessionE=rootE.getChild("security_session");
		if(securitySessionE!=null){
			Element authE=securitySessionE.getChild("authentication");
			if(authE!=null){
				Element accountE=authE.getChild("account");
				if(accountE!=null){
					Element encryptedE=accountE.getChild("encrypted");
					Element usernameE=accountE.getChild("username");
					Element passwordE=accountE.getChild("password");
					if(encryptedE!=null) SysConstants.auth_encrypted=encryptedE.getTextTrim();
					if(usernameE!=null) SysConstants.auth_username=usernameE.getTextTrim();
					if(passwordE!=null) SysConstants.auth_password=passwordE.getTextTrim();
					
					if("false".equals(SysConstants.auth_encrypted)){
						SysConstants.auth_password=Md5Encrypt.md5(SysConstants.auth_password);
						passwordE.setText(SysConstants.auth_password);
						encryptedE.setText("true");
						XMLUtil.output(doc, configuration);
					}		
				}							
			}		
		}
		
		Element charsetCodeE=rootE.getChild("charset_code");
		Element debugLogE=rootE.getChild("debug_log");
		Element sessionLimitE=rootE.getChild("session_limit");
		Element sessionLogE=rootE.getChild("session_log");
		Element sessionTimeE=rootE.getChild("session_time");
		Element checkCodeE=rootE.getChild("check_code");
		
		if(charsetCodeE!=null) SysConstants.charset_code=charsetCodeE.getTextTrim();
		if(debugLogE!=null) SysConstants.debug_log=debugLogE.getTextTrim();
		if(sessionLimitE!=null) SysConstants.session_limit=sessionLimitE.getTextTrim();		
		if(sessionLogE!=null) SysConstants.session_log=sessionLogE.getTextTrim();
		if(sessionTimeE!=null) SysConstants.session_time=sessionTimeE.getTextTrim();
		if(checkCodeE!=null) SysConstants.check_code=checkCodeE.getTextTrim();
		
		
		log.info("charset_code: "+SysConstants.charset_code);
		log.info("debug_log: "+SysConstants.debug_log);
		log.info("session_limit: "+SysConstants.session_limit);
		log.info("session_log: "+SysConstants.session_log);
		log.info("session_time: "+SysConstants.session_time);
		log.info("check_code: "+SysConstants.check_code);
		
		Element fileTransactionE=rootE.getChild("file_transaction");
		if(fileTransactionE!=null){
			Element uploadPathE=fileTransactionE.getChild("upload_path");
			Element allowableExtensionsE=fileTransactionE.getChild("upload_allowable_extensions");			
			if(uploadPathE!=null) SysConstants.upload_path=uploadPathE.getText();
			if(allowableExtensionsE!=null) SysConstants.upload_allowable_extensions=allowableExtensionsE.getText();			
			log.info("upload_path: "+SysConstants.upload_path);
			log.info("upload_allowable_extensions: "+SysConstants.upload_allowable_extensions);
		}	
	}
	
	//加载自定义插件配置
	public void loadPluginConfiguration() throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
		String configuration=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"config"+File.separator+"plugin_configuration.xml";
		Document doc=XMLUtil.parse(new File(configuration));
		if(doc==null) return;
		Element rootE=doc.getRootElement();	    	
		if(rootE==null) return;	
		Element pluginsE=rootE.getChild("plugins");
		//加载自定义插件
		ClassProcessor classProcessor=new ClassProcessor();		
		String clazz="";
		if(pluginsE!=null){
			java.util.List plugins=pluginsE.getChildren("plugin");
			if(plugins==null) return;
			for(int i=0;i<plugins.size();i++){
				Element pluginE=(Element)plugins.get(i);				
				clazz=StringUtil.replaceNull(pluginE.getAttributeValue("class"));
				classProcessor.loadClass(clazz, "load");
			}
		}
	}

}
