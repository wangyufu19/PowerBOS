package com.controller.property;
import java.lang.reflect.Method;

public interface Getter {
	public Object get(Object obj);
	public Method getMethod();
	public String getMethodName();
	public Class getReturnType();

}
