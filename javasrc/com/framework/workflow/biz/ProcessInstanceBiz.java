package com.framework.workflow.biz;
import org.jbpm.graph.exe.ProcessInstance;

import com.framework.workflow.dao.ProcessInstanceDao;

public class ProcessInstanceBiz {
	public String start(String pdName){
		String ret="成功";
		try{
			ProcessInstanceDao dao=new ProcessInstanceDao();
			ProcessInstance instance=dao.newProcessInstanceForUpdate(pdName);
			instance.signal();
		}catch(Exception e){
			e.printStackTrace();
			return "失败";
		}		
		return ret;
		
	}
	

}
