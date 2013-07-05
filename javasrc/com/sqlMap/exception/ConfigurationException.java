package com.sqlMap.exception;
import java.lang.Exception;
/**
 * SqlMap配置异常类
 * @author youfu.wang
 * @version 1.0
 */
public class ConfigurationException extends Exception{

	private static final long serialVersionUID = -4579997898182271719L;
	public ConfigurationException(){
		super();
	}
	public ConfigurationException(String message){
		super(message);
	}
	public ConfigurationException(String message,Throwable cause){
		super(message,cause);
	}
	public ConfigurationException(Throwable cause){
		super(cause);
	}

}
