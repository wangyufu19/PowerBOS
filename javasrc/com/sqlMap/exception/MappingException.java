package com.sqlMap.exception;
import java.lang.Throwable;
import java.lang.Exception;
/**
 * SqlMap对象关系映射异常类
 * @author youfu.wang
 * @version
 */
public class MappingException extends Exception{
	
	public MappingException(){
		super();
	}
	public MappingException(String message){
		super(message);
	}
	public MappingException(String message,Throwable cause){
		super(message,cause);
	}
	public MappingException(Throwable cause){
		super(cause);
	}

}
