package com.controller.property;

import com.controller.impl.GetterImpl;
import com.controller.impl.SetterImpl;

public class BeanAccessor {
	
	public Getter getGetter(Class cls,String name){
		Getter getter=new GetterImpl(cls,name);		
		return getter;
	}
	public Setter getSetter(Class cls,String name){
		Setter setter=new SetterImpl(cls,name);		
		return setter;
	}
	

}
