package com.powerbosframework.web.controller;

import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.ActionContext;
import com.controller.action.ActionServlet;
import com.controller.action.GlobalMessage;
import com.controller.action.RequestProcessor;
import com.controller.action.SupportAction;
import com.controller.config.ActionConfig;
import com.controller.config.ModuleConfig;
import com.controller.exception.ActionException;
import com.controller.forward.ActionForward;
import com.controller.forward.Dispatcher;
import com.controller.forward.GlobalForward;
import com.powerbosframework.context.ApplicationContext;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;

/**
 * 代理请求处理器
 * 
 * @author youfu.wang
 * @version
 */
public class DelegatingRequestProcessor extends RequestProcessor {
	private static DelegatingRequestProcessor instance = null;
	private ApplicationContext applicationContext;
	private ActionServlet actionServlet;
	private ModuleConfig moduleConfig;
	
	private static Logger log = LogFactory.getInstance();

	public DelegatingRequestProcessor() {
		applicationContext = null;
		actionServlet = null;
		moduleConfig = null;
	}

	/**
	 * 得到代理请求处理器实例
	 * 
	 * @return
	 */
	public static DelegatingRequestProcessor getDelegatingRequestProcessor() {
		if (instance == null) {
			instance = new DelegatingRequestProcessor();
		}
		return instance;
	}

	/**
	 * 初始化请求处理器
	 * 
	 * @param actionServlet
	 * @param moduleConfig
	 * @throws ServletException
	 */
	public void init(ApplicationContext applicationContext,
			ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws ServletException {
		
		this.applicationContext = applicationContext;
		this.actionServlet = actionServlet;
		this.moduleConfig = moduleConfig;
	}

	/**
	 * 处理请求
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws ActionException
	 * @throws Exception
	 */
	public void process(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ActionException,
			Exception {
	
		String s = super.processPath(httpservletrequest, httpservletresponse);
		if (s == null)
			return;
		
		ActionConfig actionConfig = moduleConfig.findActionConfig(s);
		SupportAction supportAction = null;
		supportAction = (SupportAction) this.applicationContext.getBean(s);
		
		Dispatcher dispatcher = new Dispatcher(httpservletrequest,
				httpservletresponse);
		ActionContext actionContext = new ActionContext(httpservletrequest,
				httpservletresponse);
		actionContext.getValueStack().set(GlobalMessage.GLOBAL_KEY,
				GlobalMessage.GLOBAL_VALUE);
		if (supportAction == null) {
			GlobalForward globalforward = moduleConfig.findGlobalForward(s);
			httpservletrequest.removeAttribute(valueStack);
			httpservletrequest.setAttribute(valueStack, actionContext
					.getValueStack());
			dispatcher.processGlobalForward(globalforward, s);
		} else {
			actionContext.setServletContext(actionServlet.getServletContext());
			supportAction.setActionContext(actionContext);
			super.processActionParameter(actionContext, supportAction);
			String forward = super.processActionExecute(supportAction,
					actionConfig);
			processValueStack(httpservletrequest, supportAction);
			ActionForward actionforward = actionConfig
					.findActionForward(forward);
			dispatcher.processForwardConfig(actionforward);
			return;
		}
	}
	
}
