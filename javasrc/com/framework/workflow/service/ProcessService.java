package com.framework.workflow.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.jbpm.graph.def.ProcessDefinition;
/**
 * Jbpm流程服务类
 * @author wangyf
 * @version 1.0
 */
public class ProcessService {
	
	public ProcessService(){
		
	}
	
	public ProcessDefinition getProcessDefinition(InputStream in){
		return ProcessDefinition.parseXmlInputStream(in);
	}
	public ProcessDefinition getProcessDefinitionByXml(String xml){
		return ProcessDefinition.parseXmlString(xml);
	}
	public ProcessDefinition getProcessDefinitionByFileName(String xmlResource){
		return ProcessDefinition.parseXmlResource(xmlResource);
	}
	public ProcessDefinition getProcessDefinition(File file) throws FileNotFoundException{
		return ProcessDefinition.parseXmlInputStream(new FileInputStream(file));
	}

}
