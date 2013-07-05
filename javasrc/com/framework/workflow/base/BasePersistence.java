package com.framework.workflow.base;
import java.util.List;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.db.GraphSession;
import org.jbpm.JbpmContext;

import com.framework.workflow.factory.JbpmFactory;
/**
 * Jbpm基础持久化类
 * @author wangyf
 * @version 1.0
 */
public class BasePersistence {
	private JbpmContext context=JbpmFactory.getJbpmContext();
	private GraphSession graphSession;
	public BasePersistence(){
		graphSession=context.getGraphSession();
	}
	public void close(){
		if(context!=null){			
			context.close();
		}
	}
	public ProcessDefinition getProcessDefinition(long id){
		return graphSession.getProcessDefinition(id);
	}
	public ProcessDefinition getProcessDefinition(String pdName){
		return graphSession.findLatestProcessDefinition(pdName);
	}	
	public void deployProcessDefinition(ProcessDefinition processDefinition){		
		context.deployProcessDefinition(processDefinition);
	}
	public ProcessInstance getProcessInstance(long id){
		return graphSession.getProcessInstance(id);
	}
	public List findProcessInstance(long id){
		return graphSession.findProcessInstances(id);
	}
	
	public ProcessInstance newProcessInstance(String pdName){			
		return context.newProcessInstance(pdName);
	}
	public ProcessInstance newProcessInstanceForUpdate(String pdName){
		return context.newProcessInstanceForUpdate(pdName);
	}
	
	
	
}
