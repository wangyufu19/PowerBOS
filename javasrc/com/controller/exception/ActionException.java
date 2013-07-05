package com.controller.exception;
import com.controller.exception.ControllerException;
public class ActionException extends ControllerException{
	private static final long serialVersionUID = -1031675894162071321L;
	public ActionException(){
		super();
	}
	public ActionException(String message){
		super(message);
	}
}
