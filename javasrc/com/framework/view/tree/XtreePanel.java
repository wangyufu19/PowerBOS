package com.framework.view.tree;
import com.framework.view.tree.TreeNode;
import com.framework.view.tree.XtreeLoader;
/**
 * 动态树面板组件类
 * @author wangyf
 * @version 1.0
 */
public class XtreePanel {
	private String contextPath="";
	private TreeNode rootNode;
	private XtreeLoader xtreeLoader;
	
	public XtreePanel(){
		
	}
	
	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public XtreeLoader getXtreeLoader() {
		return xtreeLoader;
	}

	public void setXtreeLoader(XtreeLoader xtreeLoader) {
		this.xtreeLoader = xtreeLoader;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		//渲染树图标配置
		buf.append("webFXTreeConfig.rootIcon=\""+contextPath+"/lib/default/images/dtree/base.gif\";\n");
		buf.append("webFXTreeConfig.openRootIcon=\""+contextPath+"/lib/default/images/dtree/base.gif\";\n");		
		buf.append("webFXTreeConfig.folderIcon=\""+contextPath+"/lib/default/images/xtree/xp/folder.png\";\n");
		buf.append("webFXTreeConfig.openFolderIcon=\""+contextPath+"/lib/default/images/xtree/xp/openfolder.png\";\n");		
		buf.append("webFXTreeConfig.fileIcon=\""+contextPath+"/lib/default/images/xtree/xp/file.png\";\n");
		buf.append("webFXTreeConfig.lMinusIcon=\""+contextPath+"/lib/default/images/xtree/xp/Lminus.png\";\n");
		buf.append("webFXTreeConfig.lPlusIcon=\""+contextPath+"/lib/default/images/xtree/xp/Lplus.png\";\n");
		buf.append("webFXTreeConfig.tMinusIcon=\""+contextPath+"/lib/default/images/xtree/xp/Tminus.png\";\n");
		buf.append("webFXTreeConfig.tPlusIcon=\""+contextPath+"/lib/default/images/xtree/xp/Tplus.png\";\n");
		buf.append("webFXTreeConfig.iIcon=\""+contextPath+"/lib/default/images/xtree/xp/I.png\";\n");
		buf.append("webFXTreeConfig.lIcon=\""+contextPath+"/lib/default/images/xtree/xp/L.png\";\n");
		buf.append("webFXTreeConfig.tIcon=\""+contextPath+"/lib/default/images/xtree/xp/T.png\";\n");
		buf.append("webFXTreeConfig.blankIcon=\""+contextPath+"/lib/default/images/xtree/blank.png\";\n");	
	     
		//渲染树根结点
		if(rootNode!=null){
			buf.append("var tree=new WebFXTree(\""+rootNode.getText()+"\",\""+rootNode.getAction()+"\");\n");
			buf.append("tree.target=\""+rootNode.getTarget()+"\";\n");
		}
		//渲染树结结点
		if(xtreeLoader!=null){
			buf.append(xtreeLoader.render());
		}
		buf.append("document.write(tree);\n");
		return buf.toString();
	}	
}
