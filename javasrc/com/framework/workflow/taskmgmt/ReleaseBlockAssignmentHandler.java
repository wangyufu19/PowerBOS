package com.framework.workflow.taskmgmt;

import org.jbpm.graph.exe.*;
import org.jbpm.taskmgmt.def.*;
import org.jbpm.taskmgmt.exe.Assignable;
public class ReleaseBlockAssignmentHandler implements AssignmentHandler{

	public void assign(Assignable assign, ExecutionContext context) throws Exception {
		assign.setActorId("180000000000000000");
		
	}

}
