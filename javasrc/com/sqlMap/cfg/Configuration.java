package com.sqlMap.cfg;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.jdom.Document;
import org.jdom.Element;
import com.sqlMap.cfg.Environment;
import com.sqlMap.ContextFactory;
import com.sqlMap.cfg.ResourceFactory;
import com.sqlMap.exception.ConfigurationException;
import com.sqlMap.impl.ContextFactoryImpl;
import com.sqlMap.util.XmlUtil;
import com.sqlMap.util.StringUtil;
/**
 * SqlMap配置加载类
 * @author wangyf
 * @version 1.0
 */
public class Configuration {	
	private static String configuration="sqlMap.cfg.xml";	
	private static final String IDENTIFER="identifer";
	private static final String CONNECTION_TYPE="connection-type";
	private static final String JNDINAME="jndi_name";
	public static final String DRIVER="driver";
	public static final String URL="url";
	public static final String USERNAME="username";
	public static final String PASSWORD="password";	
	private static final String DIALECT="dialect";
	private static final String DIALECT_VERSION="dialect_version";
	private static final String SHOWSQL="show_sql";
	private static final String SQLLOG="sql_log";
	//数据源连接池属性常量
	public static final String CONNECTION_POOL_PROVIDER_CLASS="connection_pool_provider_class";
	public static final String CONNECTION_POOL_MIN_CONNECTIONS="connection_pool_min_connections";
	public static final String CONNECTION_POOL_MAX_CONNECTIONS="connection_pool_max_connections";
	//数据源缓存属性常量
	private static final String CACHE_PROVIDER_CLASS="cache_provider_class";
	private static final String CACHE_ALWAYS="cache_always";
	private static final String CACHE_MAX_ELEMENTS="cache_max_elements";
	private static final String CACHE_FREE_SECONDS="cache_free_seconds";
	static Class class$(String s){
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException classnotfoundexception) {			
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
	}
	public Configuration(){
	
	}
	/**
	 * 加载数据库配置
	 * @return
	 * @throws ConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Configuration configure() throws ConfigurationException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(configuration);
		if(stream==null){ 
			configuration="com/sqlMap/cfg/default.cfg.xml";
		    stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(configuration);
		}
		if(stream==null)	
			throw new ConfigurationException("持久化组件环境资源"+configuration+"不存在");
		return this.configure(stream);
	}
	/**
	 * 加载数据库配置
	 * @param resource
	 * @return
	 * @throws ConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Configuration configure(String resource) throws ConfigurationException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{	
		if(resource==null||"".equals(resource)) throw new ConfigurationException("持久化组件环境资源"+resource+"不存在");
		configuration=resource;
		InputStream stream=Thread.currentThread().getContextClassLoader().getResourceAsStream(configuration);
		if(stream==null) throw new ConfigurationException("持久化组件环境资源"+resource+"不存在");
		return this.configure(stream);
	}
	/**
	 * 加载数据库配置
	 * @param stream
	 * @return
	 * @throws ConfigurationException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Configuration configure(InputStream stream) throws ConfigurationException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Configuration configuration=new Configuration();
		XmlUtil xmlUtil=new XmlUtil();
		Document doc=xmlUtil.parse(stream);
		if(doc==null) throw new ConfigurationException("持久化组件环境配置格式发现异常");
		this.configureDatasource(doc);
		this.configureMetadata(doc);
		return configuration;
	}
	private void configureDatasource(Document doc) throws ConfigurationException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Element rootE=doc.getRootElement();
		if(rootE==null) throw new ConfigurationException("持久化组件环境配置格式发现异常");
		Element datasourcesE=rootE.getChild("datasources");
		if(datasourcesE==null) throw new ConfigurationException("持久化组件环境数据源配置格式发现异常");
		List list=null;
		list=datasourcesE.getChildren("datasource");
		if(list==null) throw new ConfigurationException("持久化组件环境数据源配置格式发现异常");
		for(int i=0;i<list.size();i++){
			Element datasourceE=(Element)list.get(i);
			String identifer=StringUtil.replaceNull(datasourceE.getAttributeValue("identifer"));
			String connectionType=StringUtil.replaceNull(datasourceE.getAttributeValue("connection-type"));
			String isDefault=StringUtil.replaceNull(datasourceE.getAttributeValue("is-default"));	
			Map datasource=new HashMap();
			datasource.put(Configuration.IDENTIFER, identifer);
			datasource.put(Configuration.CONNECTION_TYPE, connectionType);
			List pros=null;
			pros=datasourceE.getChildren("property");
			if(pros!=null){
				for(int j=0;j<pros.size();j++){
					Element proE=(Element)pros.get(j);
					String name=StringUtil.replaceNull(proE.getAttributeValue("name"));
					String value=StringUtil.replaceNull(proE.getAttributeValue("value"));						
					if(Configuration.JNDINAME.equals(name)){					
						datasource.put(Configuration.JNDINAME, value);
					}else if(Configuration.DRIVER.equals(name)){
						datasource.put(Configuration.DRIVER, value);
					}else if(Configuration.URL.equals(name)){
						datasource.put(Configuration.URL, value);
					}else if(Configuration.USERNAME.equals(name)){
						datasource.put(Configuration.USERNAME, value);
					}else if(Configuration.PASSWORD.equals(name)){
						datasource.put(Configuration.PASSWORD, value);
					}else if(Configuration.CONNECTION_POOL_PROVIDER_CLASS.equals(name)){
						datasource.put(Configuration.CONNECTION_POOL_PROVIDER_CLASS, value);
					}else if(Configuration.DIALECT.equals(name)){
						datasource.put(Configuration.DIALECT, value);
					}else if(Configuration.DIALECT_VERSION.equals(name)){						
						datasource.put(Configuration.DIALECT_VERSION, value);
					}else if(Configuration.SHOWSQL.equals(name)){
						datasource.put(Configuration.SHOWSQL, value);
					}else if(Configuration.SQLLOG.equals(name)){
						datasource.put(Configuration.SQLLOG, value);
					}else if(Configuration.CONNECTION_POOL_MIN_CONNECTIONS.equals(name)){
						datasource.put(Configuration.CONNECTION_POOL_MIN_CONNECTIONS, value);
					}else if(Configuration.CONNECTION_POOL_MAX_CONNECTIONS.equals(name)){
						datasource.put(Configuration.CONNECTION_POOL_MAX_CONNECTIONS, value);
					}
				}
			}
			if("true".equals(isDefault)){
				Environment.defaultDatasource=datasource;
			}
			if("".equals(identifer)) throw new ConfigurationException("持久化组件环境非默认数据源配置标识符属性不能为空");
			Environment.datasources.put(identifer, datasource);
			//加载JDBC连接池供应商类
			if("jdbc".equals(connectionType.toLowerCase())&&!"".equals(Configuration.CONNECTION_POOL_PROVIDER_CLASS)){
				String providerClass=String.valueOf(datasource.get(Configuration.CONNECTION_POOL_PROVIDER_CLASS));
				Object obj=class$(providerClass).newInstance();
				Method method=obj.getClass().getMethod("init", new String().getClass());
				method.invoke(obj,identifer);
			}
		}
		Element cacheE=datasourcesE.getChild("cache");
		if(cacheE!=null){
			List pros=null;
			pros=cacheE.getChildren("property");
			if(pros!=null){
				String cacheProviderClass="";
				boolean cacheAlways=true;
				int cacheMaxElements=10000;
				long cacheFreeSeconds=300;
				
				for(int i=0;i<pros.size();i++){
					Element proE=(Element)pros.get(i);
					String name=StringUtil.replaceNull(proE.getAttributeValue("name"));
					String value=StringUtil.replaceNull(proE.getAttributeValue("value"));	
					if(Configuration.CACHE_PROVIDER_CLASS.equals(name)){
						cacheProviderClass=value;
					}else if(Configuration.CACHE_ALWAYS.equals(name)){
						cacheAlways=Boolean.parseBoolean(value);
					}else if(Configuration.CACHE_MAX_ELEMENTS.equals(name)){
						cacheMaxElements=Integer.valueOf(value);
					}else if(Configuration.CACHE_FREE_SECONDS.equals(name)){
						cacheFreeSeconds=Long.valueOf(value);
					}
				}
				
				if(!"".equals(cacheProviderClass)){
					Object obj=class$(cacheProviderClass).newInstance();
					Method method=obj.getClass().getMethod("init",boolean.class,int.class,long.class);
					method.invoke(obj,cacheAlways,cacheMaxElements,cacheFreeSeconds);
				}
			}			
		}
	}
	private void configureMetadata(Document doc) throws ConfigurationException{
		Element rootE=doc.getRootElement();
		if(rootE==null) throw new ConfigurationException("持久化组件环境配置格式发现异常");
		Element metadatasE=rootE.getChild("metadatas");
		if(metadatasE==null) throw new ConfigurationException("持久化组件环境原数据配置格式发现异常");
		List list=null;
		list=metadatasE.getChildren("metadata");
		for(int i=0;i<list.size();i++){
			Element sqlMapE=(Element)list.get(i);
			String resource=sqlMapE.getAttributeValue("resource");		
			ResourceFactory.getInstance().addResource(resource);
		}
	}
	/**
	 * 构建一个数据库上下文工厂
	 * @return
	 */
	public ContextFactory buildContextFactory(){
		ContextFactory contextFactory=new ContextFactoryImpl();
		return contextFactory;
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Configuration configuration=new Configuration();
		try {
			configuration.configure();			
		} catch (ConfigurationException e) {		
			e.printStackTrace();
		}		
	}	
}
