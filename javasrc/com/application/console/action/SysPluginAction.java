package com.application.console.action;

import com.application.console.biz.SysPluginBiz;
import com.application.console.model.SysPlugin;
import com.controller.action.SupportAction;

public class SysPluginAction extends SupportAction{
	private java.util.List sysPlugins;
	private SysPluginBiz sysPluginBiz;
	private SysPlugin sysPlugin;
	
	public java.util.List getSysPlugins() {
		return sysPlugins;
	}


	public void setSysPlugins(java.util.List sysPlugins) {
		this.sysPlugins = sysPlugins;
	}

	public SysPluginBiz getSysPluginBiz() {
		return sysPluginBiz;
	}
	public void setSysPluginBiz(SysPluginBiz sysPluginBiz) {
		this.sysPluginBiz = sysPluginBiz;
	}
	public SysPlugin getSysPlugin() {
		return sysPlugin;
	}

	public void setSysPlugin(SysPlugin sysPlugin) {
		this.sysPlugin = sysPlugin;
	}

	public String execute(){	
		this.setSysPlugins(sysPluginBiz.getSysPluginList());
		return this.SUCCESS;
	}
	public String getAddSysPlugin(){		
		return this.SUCCESS;
	}
	public String addSysPlugin(){		
		sysPluginBiz.addSysPlugin(this.getSysPlugin());
		return this.execute();
	}
	public String getUpdateSysPlugin(){			
		this.setSysPlugin(sysPluginBiz.getSysPlugin(this.getActionContext().get("key")));
		return this.SUCCESS;
	}
	public String updateSysPlugin(){		
		sysPluginBiz.updateSysPlugin(this.getSysPlugin());
		return this.execute();
	}
	public String deleteSysPlugin(){		
		sysPluginBiz.deleteSysPlugin(this.getActionContext().getArray("id"));
		return this.execute();
	}
}
