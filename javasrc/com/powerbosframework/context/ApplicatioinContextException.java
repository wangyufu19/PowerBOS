package com.powerbosframework.context;
import java.lang.Exception;
/**
 * 应用上下文异常类
 * @author youfu.wang
 * @version 1.0
 */
public class ApplicatioinContextException extends Exception{
	public ApplicatioinContextException(){
		super();
	}
	public ApplicatioinContextException(String message){
		super(message);
	}
	public ApplicatioinContextException(String message,Throwable cause){
		super(message,cause);	
	}
	public ApplicatioinContextException(Throwable cause){
		super(cause);
	}	
}
