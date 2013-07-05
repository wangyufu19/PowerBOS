package com.framework.view.util;
import java.lang.Exception;
/**
 * 组件异常类
 * @author wangyf
 * @version 1.0
 */
public class WidgetException extends Exception{
	private static final long serialVersionUID = -4591155516517841942L;
	
	public WidgetException(){
		super();
	}
	public WidgetException(String message){
		super(message);
	}
	public WidgetException(String message,Throwable cause){
		super(message,cause);	
	}
	public WidgetException(Throwable cause){
		super(cause);
	}	
}
