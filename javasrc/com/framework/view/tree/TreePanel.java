package com.framework.view.tree;
import com.framework.common.util.SysConstants;
import com.framework.view.Panel;
import com.framework.view.tree.TreeLoader;
import com.framework.view.tree.TreeNode;
/**
 * 树面板组件类
 * @author wangyf
 * @version 1.0
 */
public class TreePanel extends Panel{
	private String contextPath="";
	private String useIcons="true";//显示图标
	private String rootLinks="true";//树根链接
	private String folderLinks="true";//目录链接
	private String onclickType="url";//单击类型
	private TreeNode rootNode;
	private TreeLoader treeLoader;
	
	public TreePanel(){
		
	}
	public TreePanel(String contextPath,String useIcons,String rootLinks,String folderLinks){
		this.contextPath=contextPath;
		this.useIcons=useIcons;
		this.rootLinks=rootLinks;
		this.folderLinks=folderLinks;		
	}
	public TreePanel(String contextPath,String useIcons,String rootLinks,String folderLinks,String onclickType){
		this.contextPath=contextPath;
		this.useIcons=useIcons;
		this.rootLinks=rootLinks;
		this.folderLinks=folderLinks;
		this.onclickType=onclickType;
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	public TreeLoader getTreeLoader() {
		return treeLoader;
	}
	public void setTreeLoader(TreeLoader treeLoader) {
		this.treeLoader = treeLoader;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();		
		buf.append("d=new dTree('d');\n");
		//渲染树图标
		buf.append("d.icon={\n")
	       .append("root:'"+contextPath+"/lib/default/images/dtree/base.gif',\n")
	       .append("folder:'"+contextPath+"/lib/default/images/dtree/folder.gif',\n")
	       .append("folderOpen:'"+contextPath+"/lib/default/images/dtree/folderopen.gif',\n")
	       .append("node:'"+contextPath+"/lib/default/images/dtree/page.gif',\n")
	       .append("empty:'"+contextPath+"/lib/default/images/dtree/empty.gif',\n")
	       .append("line:'"+contextPath+"/lib/default/images/dtree/line.gif',\n")
	       .append("join:'"+contextPath+"/lib/default/images/dtree/join.gif',\n")
	       .append("joinBottom:'"+contextPath+"/lib/default/images/dtree/joinbottom.gif',\n")
	       .append("plus:'"+contextPath+"/lib/default/images/dtree/plus.gif',\n")
	       .append("plusBottom:'"+contextPath+"/lib/default/images/dtree/plusbottom.gif',\n")
	       .append("minus:'"+contextPath+"/lib/default/images/dtree/minus.gif',\n")
	       .append("minusBottom:'"+contextPath+"/lib/default/images/dtree/minusbottom.gif',\n")
	       .append("nlPlus:'"+contextPath+"/lib/default/images/dtree/nolines_plus.gif',\n")
	       .append("nlMinus:'"+contextPath+"/lib/default/images/dtree/nolines_minus.gif'\n")
	       .append("};\n");   		
		//渲染树对象配置
	    buf.append("d.config.onclickType=\""+onclickType+"\";\n")
	       .append("d.config.folderLinks="+folderLinks+";\n")
	       .append("d.config.useStatusText=true;\n")
	       .append("d.config.useCookies=false;\n")
	       .append("d.config.useIcons="+useIcons+";\n")
	       .append("d.config.closeSameLevel=false;\n");	
	    //渲染树根结点
	    if(rootNode!=null){
	    	buf.append("d.add(")
		       .append("\'"+rootNode.getId()+"\',")
		       .append("\'"+SysConstants.tree_root_id+"\',")
		       .append("\'"+rootNode.getText()+"\',");
			if("true".equals(rootLinks))			
				buf.append("\'"+rootNode.getSrc()+"\'");
			else
				buf.append("\'javascript:void(0);\'");	     
		    buf.append(");")
		       .append("\n");	
	    }
	    //渲染树子结点
	    if(treeLoader!=null){
	    	buf.append(treeLoader.render());
	    }
	    buf.append("document.write(d);");			
		return buf.toString();
	}
}
