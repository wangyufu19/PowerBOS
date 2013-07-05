package com.framework.workflow.dao;
import org.jbpm.graph.exe.ProcessInstance;

import com.framework.workflow.base.BasePersistence;

public class ProcessInstanceDao extends BasePersistence{
	public ProcessInstance start(String pdName){
		return super.newProcessInstanceForUpdate(pdName);
	}

}
