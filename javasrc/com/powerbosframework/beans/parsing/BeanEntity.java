package com.powerbosframework.beans.parsing;
import java.util.List;

/**
 * POJO实体类
 * @author youfu.wang
 * @version 1.0
 */
public class BeanEntity {
	private String id;
	private String clazz;
	private String singleton;
	private List properties;
	
	public String getSingleton() {
		return singleton;
	}
	public void setSingleton(String singleton) {
		this.singleton = singleton;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public List getProperties() {
		return properties;
	}
	public void setProperties(List properties) {
		this.properties = properties;
	}
}
