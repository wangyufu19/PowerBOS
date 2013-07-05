package com.framework.common.util;
/**
 * 系统常量类
 * @author wangyf
 * @version 1.0
 */
public class SysConstants {
    //Constants for Common Request Attribute
	public static final String timeover="/jsp/common/overtime.jsp";
	public static final String alert="/jsp/common/alert.jsp";
	//Constants for Common Session Attribute
	public static final String user_key="user.key";
	public static final String img_key="img.key";	
	public static final String mail_key="mail.key";
	public static final Long short_online_time=300L;
	public static final Long long_on_time=1800L;
	public static final String rownum_key="rownum.key";
	public static final String show_type="show.type.key";
	//Constants for Global 	
	public static final String exce_succeed="成功";
	public static final String exce_failed="操作失败";
	public static final String exce_show_add="showAdd";
	public static final String exce_add="add";
	public static final String exce_update="update";
	public static final String exce_view="view";
	public static final String exce_delete="delete";
	public static final String exce_delete_one="deleteOne";	
	public static String context_path="";
	public static String public_path="";	
	public static String iso_encode="ISO-8859-1";
	public static String tree_root_id="-1";	
//	//Constants for XML 
//	public static String xml_version="1.0";
//	public static String xml_encode="GB2312";
//	public static String xml_root_id="EID_0";
	//Constants for Dynamicpage 
	public static String app_name="支撑系统";
	public static String dynamic_page="dynamic_page.jsp";
	public static String dynamic_page_size="8";
	//Constants for Product Configuration
	public static String product_name="JAVA快速开发平台";
	public static String version_num="V1.0";
	public static String copyright_corp="东蓝数码";
	public static String technology_corp="东蓝数码";
	//Constants for Runtime Environment Configuration
	public static String runtime_server="tomcat";
	public static String runtime_mode="true";	
	//Constants for Security Session Configuration
	public static String auth_encrypted="";
	public static String auth_username="";
	public static String auth_password="";
	//Constants for Other Configuration	
	public static String charset_code="utf-8";
	public static String debug_log="false";
	public static String session_limit="false";
	public static String session_log="true";
	public static String session_time="false";
	public static String check_code="false";
	//Constants for Upload File Configuration
	public static String upload_path="/temp";
	public static String upload_allowable_extensions="zip,rar,doc,xls,txt,bmp,jpg,jpeg,gif";
	
}
