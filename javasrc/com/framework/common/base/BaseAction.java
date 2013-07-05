package com.framework.common.base;
import com.framework.common.servlet.http.RequestHash;
/**
 * 功能说明:基础Action组件类
 * @author wangyf
 * @version 1.0
 */
public class BaseAction {
	public RequestHash reh;
	public BaseAction(){
		
	}
	public RequestHash getReh() {
		return reh;
	}
	public void setReh(RequestHash reh) {
		this.reh = reh;
	}

	
}
