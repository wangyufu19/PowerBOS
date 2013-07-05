package com.framework.common.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.framework.common.file.Download;
import com.framework.common.servlet.http.RequestHash;
/**
 * 本类是用于文件的下载服务的。该类处理了所有需要服务器处理的下载请求。
 *<p><b>参见：</b><a href="../{@docRoot}/examples/file.html">例程</a><br>
 */
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -1107858534795303465L;
	private final String CLASSPATH = "";
	private final String designer = "";

	/**
	 * 初始化Servlet。导入了初始变量值。
	 * @param conf	Servlet的配置文件。由系统提供。
     * @throws  ServletException    Servlet发生错误时抛出异常。
	 */
    public void init(ServletConfig conf) throws ServletException  {
		super.init(conf);
    }


    /**
     * 响应文件下载请求。
     * @param req  HttpServletRequest
     * @param res  HttpServletRespones
     * @throws  ServletException   Servlet发生错误时抛出异常。
     * @throws  IOException        I/O发生错误时抛出异常。
     */
    public void service(HttpServletRequest req, HttpServletResponse res)
                         throws ServletException, IOException {
    	String srcFileFullPath="";
    	String downloadName="";
    	try {
    		RequestHash reh =new RequestHash(req,res);
			Download download=new Download(reh);
			download.download(srcFileFullPath, downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
