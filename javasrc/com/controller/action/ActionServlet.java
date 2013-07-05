package com.controller.action;    
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import org.jdom.Document;
import org.jdom.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.controller.action.RequestProcessor;
import com.controller.config.ConfigConstants;
import com.controller.config.ModuleConfig;
import com.controller.exception.ActionException;
import com.controller.exception.ControllerException;
import com.controller.util.ModuleParser;
import com.controller.util.ValueStack;
/**
 * 控制器类
 * @author youfu.wang
 * @version 1.0
 */
public class ActionServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1018314453204557858L;
	protected String configuration;
	protected ServletContext servletContext=null;
	protected static Log log;
	static Class com$controller$action$ActionServlet;
	
	public ActionServlet(){
		configuration="controller.xml";
	}
	public ActionServlet(ServletContext servletContext){
		this.servletContext=servletContext;	
		configuration="controller.xml";
	}
	/**
	 * 销毁控制器所占内存
	 */
	public void destroy(){
		ModuleConfig config=null;
		if(config==null)
			config=(ModuleConfig)getServletContext().getAttribute("com.controller.config.module");			
		config.freeze();
		servletContext.removeAttribute("com.controller.config.module");
		ValueStack.vs.clear();		
	}
	
	/**
	 * 初始化控制器配置
	 */
	public void init(){			
		try {								
			this.configuration(configuration);
		} catch (ControllerException e) {		
			e.printStackTrace();
		}		
	}
	/**
	 * 设置ServletContext
	 * @param servletContext
	 */
	public void setServletContext(ServletContext servletContext){
		this.servletContext=servletContext;	
	}
	/**
	 * 得到ServletContext
	 */
	public ServletContext getServletContext(){
		return this.servletContext;
	}
	/**
	 * 加载控制器配置
	 * @param s
	 * @throws ControllerException
	 */
	public void configuration(String s) throws ControllerException{		
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(s);
		if(stream==null) throw new ControllerException("控制器配置"+s+"不存在!");
	    ModuleParser parser=new ModuleParser();   
	    Document doc=parser.parseResource(stream);
	    if(doc==null) throw new ControllerException("控制器配置格式发现异常!");
	    Element rootE=doc.getRootElement();
	    if(rootE==null) return;  
	    //加载控制器常量配置
	    this.configurationConstants(rootE);
	    //加载控制器模块资源
		List list=rootE.getChildren("include");
		if(list==null) return;
		ModuleConfig config=new ModuleConfig();
		for(int i=0;i<list.size();i++){
			Element includeE=(Element)list.get(i);
			String resource=includeE.getAttributeValue("resource");
			this.addModuleResource(resource,config);
		}
		servletContext.setAttribute("com.controller.config.module", config);		
	}
	/**
	 * 加载控制器常量配置
	 * @param rootE
	 */
	private void configurationConstants(Element rootE){
		List list=rootE.getChildren("constant");
		if(list==null) return;	
		if(log.isDebugEnabled())
			log.debug("Initializing constants configuration");	
		for(int i=0;i<list.size();i++){
			Element conE=(Element)list.get(i);
			String name=conE.getAttributeValue("name");
			if(name==null) name="";
			String value=conE.getAttributeValue("value");
			if(value==null) value="";
			if("controller.encoding".equals(name)){
				ConfigConstants.controller_encoding=value;
			}else if("controller.pagesize".equals(name)){
				ConfigConstants.controller_pagesize=value;
			}else if("controller.request.processor".equals(name)){
				ConfigConstants.controller_request_processor=value;
			}
		}
	}
	/**
	 * 增加控制器模块资源
	 * @param resource
	 * @param config
	 * @throws ControllerException
	 */
	private void addModuleResource(String resource,ModuleConfig config) throws ControllerException{	
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
		if(stream==null) throw new ControllerException("控制器模块资源配置"+resource+"不存在!");
		if(log.isDebugEnabled())
			log.debug("Initializing resource configuration from "+resource);	
		ModuleParser moduleResource=new ModuleParser();   
		moduleResource.parseIncludeResource(stream);
		moduleResource.push(config);	
	}	
	private synchronized RequestProcessor getRequestProcessor(ModuleConfig moduleConfig){
		RequestProcessor requestprocessor = getProcessorForConfig(moduleConfig);
		if(requestprocessor==null){
			if(!ConfigConstants.controller_request_processor.equals("")||ConfigConstants.controller_request_processor!=null){
				requestprocessor=(RequestProcessor)servletContext.getAttribute(ConfigConstants.controller_request_processor);
			}else{
				requestprocessor=RequestProcessor.getInstance();
			}			
		}
		try {
			requestprocessor.init(this, moduleConfig);
		} catch (ServletException e) {			
			e.printStackTrace();
		}
		String s = "com.controller.action.RequestProcessor";
		servletContext.setAttribute(s, requestprocessor);
        return requestprocessor;
	}
	private synchronized RequestProcessor getProcessorForConfig(ModuleConfig config){
		String s = "com.controller.action.RequestProcessor";
		return (RequestProcessor)servletContext.getAttribute(s);
	}
	/**
	 * 处理GET请求方法
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws IOException, ServletException
	 */
	public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
     throws IOException, ServletException {
		try {
			process(httpservletrequest, httpservletresponse);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 处理POST请求方法
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws IOException, ServletException
	 */
	public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	     throws IOException, ServletException{
	    try {
			process(httpservletrequest, httpservletresponse);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}
	private synchronized void process(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ControllerException{
		ModuleConfig config=ModuleConfig.getModuleConfig(httpservletrequest, servletContext);
		if(config==null){
			throw new ControllerException("控制器模块资源配置不存在!");
		}
		RequestProcessor requestprocessor=getProcessorForConfig(config);
		if(requestprocessor==null){		
			requestprocessor=this.getRequestProcessor(config);
		}
		try {
			requestprocessor.process(httpservletrequest, httpservletresponse);
		} catch (Exception e) {			
			e.printStackTrace();
		} catch (ActionException e) {			
			e.printStackTrace();
		}
	}
	static Class class$(String s){
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException classnotfoundexception) {			
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
	static{
		log=LogFactory.getLog(com$controller$action$ActionServlet!=null?com$controller$action$ActionServlet:(com$controller$action$ActionServlet=class$("com.controller.action.ActionServlet")));
	}
	public static void main(String[] args){
		ActionServlet actionDispatcher=new ActionServlet();
		actionDispatcher.init();
	}

}
