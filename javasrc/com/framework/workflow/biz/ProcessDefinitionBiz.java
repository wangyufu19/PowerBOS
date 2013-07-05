package com.framework.workflow.biz;
import java.io.InputStream;

import com.framework.workflow.dao.ProcessDefinitionDao;
import com.framework.workflow.service.ProcessService;
public class ProcessDefinitionBiz {
	
	public String doployProcessDefintion(InputStream in){
		ProcessService service=new ProcessService();
		ProcessDefinitionDao dao=new ProcessDefinitionDao();
		return dao.deploy(service.getProcessDefinition(in));
	}

}
