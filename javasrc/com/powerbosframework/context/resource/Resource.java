package com.powerbosframework.context.resource;
import java.io.IOException;
import java.io.InputStream;
/**
 * 应用上下文资源管理接口
 * @author youfu.wang
 * @version 1.0
 */
public interface Resource {
	/**
	 * 资源文件是否存在
	 * @return
	 */
	public boolean exists();
	/**
	 * 得到资源文件输入流
	 * @throws IOException
	 * @return
	 */
	public InputStream getInputStream() throws IOException ;
	/**
	 * 得到资源文件名称
	 * @return
	 */
	public String getResourceName();
}
