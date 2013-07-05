package com.controller.action;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.controller.action.ActionContext;
import com.controller.action.ActionServlet;
import com.controller.action.SupportAction;
import com.controller.config.ActionConfig;
import com.controller.config.ModuleConfig;
import com.controller.exception.ActionException;
import com.controller.forward.ActionForward;
import com.controller.forward.Dispatcher;
import com.controller.forward.GlobalForward;
import com.controller.property.BeanAccessor;
import com.controller.property.Getter;
import com.controller.property.Setter;
import com.controller.util.RequestUtil;
import com.controller.util.TypeConverter;

/**
 * 请求处理器
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class RequestProcessor {
	private ActionServlet actionServlet;
	private ModuleConfig moduleConfig;
	private Map actions;
	public static final String valueStack = "valueStack";
	private static RequestProcessor instance = null;
	private static Log log;
	static Class com$controller$action$RequestProcessor; /* synthetic field */

	public RequestProcessor() {
		actionServlet = null;
		moduleConfig = null;
		actions = new HashMap();
	}

	public static RequestProcessor getInstance() {
		if (instance == null)
			instance = new RequestProcessor();
		return instance;
	}

	/**
	 * 初始化请求处理器
	 * 
	 * @param actionServlet
	 * @param moduleConfig
	 * @throws ServletException
	 */
	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig)
			throws ServletException {
		synchronized (actions) {
			actions.clear();
		}
		this.actionServlet = actionServlet;
		this.moduleConfig = moduleConfig;
	}

	/**
	 * 处理请求
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @throws Exception
	 * @throws ActionException
	 */
	public void process(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception,
			ActionException {
		String s = processPath(httpservletrequest, httpservletresponse);
		if (s == null)
			return;
		if (log.isDebugEnabled())
			log.debug("Processing a '" + httpservletrequest.getMethod()
					+ "' for path '" + s + "'");
		ActionConfig actionconfig = moduleConfig.findActionConfig(s);
		SupportAction supportaction = processActionCreate(httpservletrequest,
				httpservletresponse, actionconfig);
		Dispatcher dispatcher = new Dispatcher(httpservletrequest,
				httpservletresponse);
		ActionContext actionContext = new ActionContext(httpservletrequest,
				httpservletresponse);
		actionContext.getValueStack().set(GlobalMessage.GLOBAL_KEY,
				GlobalMessage.GLOBAL_VALUE);
		if (supportaction == null) {
			GlobalForward globalforward = moduleConfig.findGlobalForward(s);
			httpservletrequest.removeAttribute(valueStack);
			httpservletrequest.setAttribute(valueStack, actionContext
					.getValueStack());
			dispatcher.processGlobalForward(globalforward, s);
		} else {
			actionContext.setServletContext(getServletContext());
			supportaction.setActionContext(actionContext);
			processActionParameter(actionContext, supportaction);
			String forward = processActionExecute(supportaction, actionconfig);
			processValueStack(httpservletrequest, supportaction);
			ActionForward actionforward = actionconfig
					.findActionForward(forward);
			dispatcher.processForwardConfig(actionforward);
			return;
		}
	}

	/**
	 * 处理action请求参数
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @param supportaction
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	public void processActionParameter(ActionContext actionContext,
			SupportAction supportaction) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		Field[] fields = supportaction.getClass().getDeclaredFields();
		BeanAccessor bean = new BeanAccessor();
		for (Field field : fields) {
			String name = field.getName();
			String type = field.getType().getSimpleName();
			Getter getter = bean.getGetter(supportaction.getClass(), name);
			if (getter.get(supportaction) != null) continue;
			boolean isInterface = field.getType().isInterface();
			Setter setter = bean.getSetter(supportaction.getClass(), name);
			if (TypeConverter.isPrimitive(type)) {
				Object value = actionContext.get(name);
				setter.set(supportaction, value);
			} else if (TypeConverter.isModelObject(type) && !isInterface) {
				Object obj = field.getType().newInstance();
				Field[] modleFields = obj.getClass().getDeclaredFields();
				for (Field modleField : modleFields) {
					name = modleField.getName();
					type = modleField.getType().getSimpleName();
					Setter moduleSetter = bean.getSetter(obj.getClass(), name);
					Object value = TypeConverter.convert(actionContext
							.get(name), type);
					if (value != null)
						moduleSetter.set(obj, value);
				}
				setter.set(supportaction, obj);
			} else if (TypeConverter.isArray(type) && !isInterface) {
				Object[] objs = actionContext.getArray(name);
				setter.set(supportaction, objs);
			}
		}
	}

	/**
	 * 处理action属性对象,存入系统的ValueStack
	 * 
	 * @param supportaction
	 */
	public void processValueStack(HttpServletRequest httpservletrequest,
			SupportAction supportaction) {
		Field[] fields = supportaction.getClass().getDeclaredFields();
		BeanAccessor bean = new BeanAccessor();
		for (Field field : fields) {
			String name = field.getName();
			Getter getter = bean.getGetter(supportaction.getClass(), name);
			Object value = getter.get(supportaction);
			supportaction.getActionContext().set(name, value);
		}
		httpservletrequest.removeAttribute(valueStack);
		httpservletrequest.setAttribute(valueStack, supportaction
				.getActionContext().getValueStack());
	}

	/**
	 * 处理请求的action执行
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @param supportaction
	 * @param actionconfig
	 * @return
	 * @throws Exception
	 */
	public String processActionExecute(SupportAction supportaction,
			ActionConfig actionconfig) throws Exception {
		String forward = "";
		String methodName = "";
		try {
			methodName = actionconfig.getMethod();
			if (methodName == null)
				methodName = "";
			// 默认调用execute方法
			if ("".equals(methodName))
				methodName = "execute";
			if (log.isDebugEnabled())
				log.debug("invoking method is " + methodName + " of "
						+ supportaction.getClass().toString());
			Method method = supportaction.getClass().getMethod(methodName);
			forward = String.valueOf(method.invoke(supportaction));
		} catch (Exception exception) {
			throw exception;
		}
		return forward;
	}

	private ServletContext getServletContext() {
		return actionServlet.getServletContext();
	}

	/**
	 * 创建请求的action实例
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @param actionconfig
	 * @return
	 */
	private SupportAction processActionCreate(
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, ActionConfig actionconfig) {
		if (actionconfig == null) {
			return null;
		}
		String s = actionconfig.getClazz();
		if (log.isDebugEnabled()) {
			log.debug("Looking for SupportAction instance for class " + s);
		}
		SupportAction supportaction = null;
		synchronized (actions) {
			supportaction = (SupportAction) actions.get(s);
			if (supportaction != null) {
				if (log.isTraceEnabled()) {
					log.trace("Returning existing SupportAction instance");
				}
				SupportAction supportaction1 = supportaction;
				return supportaction1;
			}
			if (log.isTraceEnabled())
				log.trace("Creating new SupportAction instance");
			try {
				supportaction = (SupportAction) RequestUtil
						.applicationInstance(s);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			actions.put(s, supportaction);
		}
		return supportaction;
	}

	/**
	 * 处理页面请求的路径,并且返回该请求路径
	 * 
	 * @param httpservletrequest
	 * @param httpservletresponse
	 * @return
	 */
	public String processPath(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) {
		String s = null;
		s = (String) httpservletrequest
				.getAttribute("javax.servlet.include.path_info");
		if (s == null)
			s = httpservletrequest.getPathInfo();
		if (s != null && s.length() >= 0) {
			return s;
		}

		s = (String) httpservletrequest
				.getAttribute("javax.servlet.include.servlet_path");
		if (s == null)
			s = httpservletrequest.getServletPath();
		s = RequestUtil.redirectURL(s);
		return s;
	}

	static Class class$(String s) {
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}

	static {
		log = LogFactory
				.getLog(com$controller$action$RequestProcessor != null ? com$controller$action$RequestProcessor
						: (com$controller$action$RequestProcessor = class$("com.controller.action.RequestProcessor")));
	}

}
