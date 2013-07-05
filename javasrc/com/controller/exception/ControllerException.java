package com.controller.exception;
import java.lang.Throwable;
public class ControllerException extends Throwable{
	private static final long serialVersionUID = -5499592625332535399L;
	
	public ControllerException(){
		super();
	}
	public ControllerException(String message){
		super(message);
	}
	public ControllerException(String message,Throwable cause){
		super(message,cause);
	}
	public ControllerException(Throwable cause){
		super(cause);
	}

}
