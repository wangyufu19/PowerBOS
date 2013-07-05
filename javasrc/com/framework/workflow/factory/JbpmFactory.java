package com.framework.workflow.factory;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmConfiguration;
/**
 * Jbpm工厂类
 * @author wangyf
 * @version 1.0 
 */
public class JbpmFactory {	
	private static JbpmConfiguration jbpmConfiguration=JbpmConfiguration.getInstance();

	public static JbpmContext getJbpmContext(){		
	    JbpmContext context = null;		
		if(context==null){
			context=jbpmConfiguration.createJbpmContext();
		}		
		return context;
	}	
	public static JbpmConfiguration getJbpmConfiguration(){
		return jbpmConfiguration;
	}	
	public void createSchame(){
		jbpmConfiguration.createSchema();
	}
	public void dropSchame(){
		jbpmConfiguration.dropSchema();
	}
	

}
