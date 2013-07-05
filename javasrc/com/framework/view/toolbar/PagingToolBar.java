package com.framework.view.toolbar;
import com.framework.common.servlet.http.RequestHash;

/**
 * 分页工具条组件类
 * @author wangyf
 * @version 1.0
 */
public class PagingToolBar {
	private RequestHash reh;
	private int pageSize;//页面记录大小
	private int currentPage;//当前页
	private int maxPage;//最大页
	private int firstResult;//起始记录
	private int maxResult;//最大记录	
	
	
	public PagingToolBar(RequestHash reh){
		this.reh=reh;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	

	public String render(){
		StringBuilder buf=new StringBuilder();
		String contextPath=reh.getRequestContextPath();
		String form=String.valueOf(reh.getSessionHash().get("FORM"));
		buf.append("<table class=\"pagenavigator\" cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>\n")
		   .append("<tbody>\n");
		buf.append("<tr>\n");
//		   .append("<td width=\"20%\" align=center>每页显示条数：\n")
//		   .append("<select name=\"PAGESIZE\" class=\"select\" style=\"width:40px\" onChange=\"javascript:search_f();\">\n");
//		for(int i=1;i<=5;i++){
//			if(this.pageSize==i*this.pageSize){
//				buf.append("<option selected value=\""+i*this.pageSize+"\">"+i*this.pageSize+"<option>");						  
//			}else{
//				buf.append("<option value=\""+i*this.pageSize+"\">"+i*this.pageSize+"<option>");	
//			}
//		}
//		buf.append("</select>\n");
//		buf.append("</td>\n");
		buf.append("<td align=center>\n")
		   .append("<a href=\"javascript:firstClick(document."+form+","+this.getCurrentPage()+","+this.getMaxPage()+",document."+form+".currentPage)\">")
		   .append("<img src=\""+contextPath+"/lib/default/images/first.gif\" width=\"23\" height=\"14\" border=0 align=absMiddle title=\"首页\"></a>\n")
		   .append("<a href=\"javascript:previousClick(document."+form+","+this.getCurrentPage()+","+this.getMaxPage()+",document."+form+".currentPage)\">")
		   .append("<img src=\""+contextPath+"/lib/default/images/previous.gif\" width=\"23\" height=\"14\" border=0 align=absMiddle title=\"上一页\"></a>")
		   .append("&nbsp;&nbsp;<strong>当前显示 :&nbsp;</strong>"+this.getFirstResult()+"-"+this.getMaxResult()+"条 of "+this.getCurrentPage()+"/"+this.getMaxPage()+"页 \n")
		   .append("<a href=\"javascript:nextClick(document."+form+","+this.getCurrentPage()+","+this.getMaxPage()+",document."+form+".currentPage)\">")
		   .append("<img src=\""+contextPath+"/lib/default/images/next.gif\" width=\"23\" height=\"14\" border=0 align=absMiddle title=\"下一页\"></a>\n")
		   .append("<a href=\"javascript:endClick(document."+form+","+this.getCurrentPage()+","+this.getMaxPage()+",document."+form+".currentPage)\">")
		   .append("<img src=\""+contextPath+"/lib/default/images/last.gif\" width=\"23\" height=\"14\" border=0 align=absMiddle title=\"末页\"></a>\n")
		   .append("</td>\n")
		   .append("</tr>\n")
		   .append("</tbody>\n")				
		   .append("</table>\n");
		return buf.toString();
	}
}
