package com.framework.view.form.action;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import org.jdom.Element;
import com.framework.common.file.FileUtil;
import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.ClassProcessor;
import com.framework.common.util.SysConstants;
import com.framework.config.XMLData;
import com.framework.view.form.action.StatmentProcessor;
import com.framework.view.util.DataFormat;
import com.framework.view.util.RequestUtil;
import com.framework.view.util.StringFormat;
import com.framework.view.util.WidgetException;
import com.powerbosframework.log.LogFactory;
import com.sqlMap.Transaction;
import com.sqlMap.jdbc.JdbcTmplt;
/**
 * 动作请示处理类
 * @author wangyf
 * @version 1.0
 */
public class ActionProcessor {
	private RequestHash reh;
	private String executeType="statement";
	
	public ActionProcessor(RequestHash reh){
		this.reh=reh;
	}
	
	public String process(String code,String action){	
		String msg="成功";
		if(!XMLData.getXMLData().containsKey(code)){
			msg="对不起，代码:<br><b><font color=red>"+code+"</font></b><br>不存在，请联系管理员！";			
			try {				
				reh.getResponse().sendRedirect(reh.getRequestContextPath()+SysConstants.alert+"?msg="+DataFormat.getURLEncode(msg));
			} catch (IOException e) {				
				e.printStackTrace();
			}	
			return msg;
		}					
		if(!"".equals(action)){
			Map actions=(Map)XMLData.getXMLDataActions().get(code);
			if(actions==null) return "对不起,该表单没有任何动作,请查看相关配置!";		
			Element actionE=(Element)actions.get(action);
			if(actionE==null) return "对不起,该表单不包含"+action+"动作!";		
			msg=this.doProcess(actionE);
		}
		return msg;
	}	
	public String doProcess(Element actionE){
		String msg="成功";			
		if(!"".equals(StringFormat.replaceNull(actionE.getAttributeValue("executeType"))))
			this.executeType=StringFormat.replaceNull(actionE.getAttributeValue("executeType"));
		List list=actionE.getChildren();
		if(list==null) return msg;		
		if("statement".equals(this.executeType)){
			Element statmentE=actionE.getChild("STATEMENT");
			Element inputbeanE=actionE.getChild("INPUTBEAN");
			JdbcTmplt jdbcTmplt=reh.getJdbcTmplt();
			Transaction tx=null;
			try {
				tx=jdbcTmplt.beginTransaction();		
				StatmentProcessor statmentProcessor=new StatmentProcessor(reh);
				msg=statmentProcessor.doStatement(jdbcTmplt, statmentE,inputbeanE);			
				tx.commit();
			} catch (Exception e) {		
				if(tx!=null){
					try {
						tx.rollback();
					} catch (SQLException e1) {				
						e1.printStackTrace();
					}	
				}			
				e.printStackTrace();	
				LogFactory.getInstance().addLogFile(new WidgetException("表单请求处理持久化失败",e));
				return "操作失败";
			} 
		}else if("interface".equals(this.executeType)){
			Element interfaceE=actionE.getChild("INTERFACE");
			try{
				msg=this.invokeInterface(interfaceE);
			}catch(Exception e){
				e.printStackTrace();
				LogFactory.getInstance().addLogFile(new WidgetException("表单请求处理持久化失败",e));
				if(e.toString().indexOf("ClassNotFoundException")!=-1){
					return e.toString();
				}else if(e.toString().indexOf("NoSuchMethodException")!=-1){
					return e.toString();
				}else if(e.toString().indexOf("InvocationTargetException")!=-1){
					return e.toString();
				}else
					return "操作失败";
			}		
		}		
		return msg;
	}	
	/**
	 * 执行插件类
	 * @param classE
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	public String invokeInterface(Element interfaceE) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException{
		String msg="成功";
		if(interfaceE==null) return msg;
		ClassProcessor classProcessor=new ClassProcessor(reh);
		String className=StringFormat.replaceNull(interfaceE.getAttributeValue("className"));
		String methodName=StringFormat.replaceNull(interfaceE.getAttributeValue("methodName"));
		if("".equals(className)||"".equals(methodName)) return msg;		
		msg=StringFormat.replaceNull(classProcessor.process(className, methodName));		
		return msg;
	}
	/**
	 * 移动文件
	 * @param moveFileE
	 * @throws IOException 
	 */
	public String doMoveFile(Element moveFileE){
		FileUtil fileUtil=new FileUtil();
		if(moveFileE==null) return SysConstants.exce_succeed;
		String fileName=StringFormat.replaceNull(moveFileE.getAttributeValue("fileName"));
		String toPath=StringFormat.replaceNull(moveFileE.getAttributeValue("toPath"));
		String srcPath="";		
		if(fileName.indexOf("fun.get(")!=-1){
			String replacement=RequestUtil.getRequestParameterName(fileName);
		    String target=RequestUtil.getRequestParamNameStr(fileName);	 
	        replacement=reh.get(replacement);        
	        fileName=StringFormat.replace(fileName,target, replacement);	
		}
		if("".equals(fileName)) return SysConstants.exce_succeed;
		
		if(SysConstants.upload_path.startsWith("/")){
			srcPath=SysConstants.public_path+SysConstants.upload_path+File.separator+fileName;				
		}else
			srcPath=SysConstants.public_path+File.separator+SysConstants.upload_path+File.separator+fileName;
	    
		if(toPath.startsWith("/")){
			toPath=SysConstants.public_path+toPath+File.separator+fileName;
		}else
			toPath=SysConstants.public_path+File.separator+toPath+File.separator+fileName;
		try {
			fileUtil.move(srcPath, toPath, true);
		} catch (IOException e) {		
			e.printStackTrace();
			return "移动文件失败";
		}
		return SysConstants.exce_succeed;
	}
	/**
	 * 执行重定向页面
	 * @param redirectE
	 */
	public String doRedirect(Element redirectE){
		String redirect=StringFormat.replaceNull(redirectE.getAttributeValue("redirect"));
		if("".equals(redirect)) return "对不起,重定向地址不能为空!";
		while(redirect.indexOf("fun.get(")!=-1){
			String replacement=RequestUtil.getRequestParameterName(redirect);
		    String target=RequestUtil.getRequestParamNameStr(redirect);	 
	        replacement=reh.get(replacement);        
	        redirect=StringFormat.replace(redirect,target, replacement);		
		}
		try {
			reh.getResponse().sendRedirect(redirect);
		} catch (IOException e) {					
			e.printStackTrace();
			return "地址错误:"+redirect;
		}		
		return SysConstants.exce_succeed;
	}
}
