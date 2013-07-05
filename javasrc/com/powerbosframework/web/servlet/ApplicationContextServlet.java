package com.powerbosframework.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.controller.action.ActionServlet;
import com.controller.config.ModuleConfig;
import com.powerbosframework.context.ApplicatioinContextException;
import com.powerbosframework.context.ApplicationContext;
import com.powerbosframework.context.XmlApplicationContext;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;
import com.powerbosframework.web.controller.DelegatingRequestProcessor;

/**
 * 应用上下文Servlet
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class ApplicationContextServlet extends HttpServlet {

	private static final long serialVersionUID = 4952612636757382137L;
	private ActionServlet actionServlet;
	private ApplicationContext applicationContext;

	private static Logger log = LogFactory.getInstance();

	public ApplicationContextServlet() {
		applicationContext= new XmlApplicationContext();
	}

	/**
	 * 销毁控制器所占内存
	 */
	public void destroy() {
		actionServlet.destroy();
		applicationContext.destory();
	}

	/**
	 * 初始化应用上下文
	 */
	public void init() {
		actionServlet = new ActionServlet(this.getServletContext());
		applicationContext.setActionServlet(actionServlet);
		try {
			applicationContext.load();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ApplicatioinContextException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * 处理GET请求方法
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws IOException,
	 *             ServletException
	 */
	public void doGet(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		this.doDelegatingRequestProcessor(ModuleConfig.getModuleConfig(
				httpservletrequest, this.getServletContext()));
		actionServlet.doGet(httpservletrequest, httpservletresponse);
	}

	/**
	 * 处理POST请求方法
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws IOException,
	 *             ServletException
	 */
	public void doPost(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		this.doDelegatingRequestProcessor(ModuleConfig.getModuleConfig(
				httpservletrequest, this.getServletContext()));
		actionServlet.doPost(httpservletrequest, httpservletresponse);
	}

	private void doDelegatingRequestProcessor(ModuleConfig moduleConfig)
			throws ServletException {
		DelegatingRequestProcessor delegatingRequestProcessor = DelegatingRequestProcessor
				.getDelegatingRequestProcessor();
		delegatingRequestProcessor.init(applicationContext, actionServlet,
				moduleConfig);		
		String s = "com.powerbos.framework.web.controller.DelegatingRequestProcessor";
		this.getServletContext().setAttribute(s, delegatingRequestProcessor);
	}

}
