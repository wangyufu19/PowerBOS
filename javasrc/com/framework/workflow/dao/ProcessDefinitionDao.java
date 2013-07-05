package com.framework.workflow.dao;
import org.jbpm.graph.def.ProcessDefinition;

import com.framework.workflow.base.BasePersistence;
public class ProcessDefinitionDao extends BasePersistence{
	public String deploy(ProcessDefinition processDefinition){
		String ret="成功";
		try{
			super.deployProcessDefinition(processDefinition);
		}catch(Exception e){
			ret="失败";
			System.out.println("********************ret="+ret);
			e.printStackTrace();
		}finally{
			super.close();
		}
		return ret;
	}

}
