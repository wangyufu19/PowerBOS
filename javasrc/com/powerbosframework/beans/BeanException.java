package com.powerbosframework.beans;
import java.lang.Exception;
/**
 * POJO异常类
 * @author youfu.wang
 * @version 1.0
 */
public class BeanException extends Exception{
	public BeanException(){
		super();
	}
	public BeanException(String message){
		super(message);
	}
	public BeanException(String message,Throwable cause){
		super(message,cause);	
	}
	public BeanException(Throwable cause){
		super(cause);
	}	
}
