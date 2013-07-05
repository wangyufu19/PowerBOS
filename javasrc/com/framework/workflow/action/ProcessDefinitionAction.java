package com.framework.workflow.action;
import java.io.File;

import com.framework.common.file.FileUtil;
import com.framework.common.util.SysConstants;
import com.framework.workflow.biz.ProcessDefinitionBiz;
import com.framework.workflow.conf.JbpmConstants;
public class ProcessDefinitionAction {
	
	public void deployProcessDefinition(){
		FileUtil util=new FileUtil();
		String fileName=SysConstants.public_path+JbpmConstants.SEPARATOR+"SYSTEM"+JbpmConstants.SEPARATOR+"jbpm"+JbpmConstants.SEPARATOR+"definition"+JbpmConstants.SEPARATOR+JbpmConstants.JBPMDEFINITIONFILENAME;
		System.out.println("******filename="+fileName);
		if(!util.exists(fileName)){
			return;
		}
		ProcessDefinitionBiz biz=new ProcessDefinitionBiz();
		biz.doployProcessDefintion(util.getInputStream(new File(fileName)));		
	}

}
