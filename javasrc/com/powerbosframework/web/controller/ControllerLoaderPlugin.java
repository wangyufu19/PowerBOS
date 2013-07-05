package com.powerbosframework.web.controller;

import com.controller.action.ActionServlet;
import com.powerbosframework.log.LogFactory;
import com.powerbosframework.log.Logger;

/**
 * 控制器加载插件管理
 * 
 * @author youfu.wang
 * @version 1.0
 */
public class ControllerLoaderPlugin {
	private ActionServlet actionServlet;
	private static Logger log = LogFactory.getInstance();

	public ControllerLoaderPlugin() {

	}

	/**
	 * 初始化控制器配置
	 */
	public void init(ActionServlet actionServlet) {
		this.actionServlet = actionServlet;
		this.actionServlet.init();
	}

}
