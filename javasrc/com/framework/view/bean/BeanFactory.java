package com.framework.view.bean;
import com.framework.view.bean.BaseGetter;
import com.framework.view.bean.BaseSetter;
import com.framework.view.bean.Setter;
/**
 * JAVABEAN工厂类
 * @author wangyf
 * @version 1.0
 */
public class BeanFactory {
	public static Getter getGetter(Class cls,String propertyName){
		Getter getter=new BaseGetter(cls,propertyName);		
		return getter;
	}
	public static Setter getSetter(Class cls,String propertyName){
		Setter setter=new BaseSetter(cls,propertyName);		
		return setter;
	}

}
