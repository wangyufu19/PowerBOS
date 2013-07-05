package com.framework.common.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.framework.common.util.SysConstants;
import com.framework.config.Configuration;
import com.framework.config.ConfigurationFactory;

/**
 * 系统配置加载类
 * @author wangyf
 * @version 1.0
 */
public class SysServlet extends HttpServlet {  	
	private static final long serialVersionUID = 1L;
	
    public SysServlet(){       	
    	
    }    
	public void init() throws ServletException {	
		SysConstants.public_path = getServletContext().getRealPath("");		
		Configuration configuration=ConfigurationFactory.newInstance();
		try{						
			configuration.load();						
		}catch(Exception e){
			e.printStackTrace();
		}		
	}	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	public void destroy(){		
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
