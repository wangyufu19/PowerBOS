package com.framework.view.tree;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.framework.view.tree.TreeNode;
import com.framework.view.util.StringFormat;
/**
 * 动态树加载器类
 * @author wangyf
 * @version 1.0
 */
public class XtreeLoader {
	private List treeNodes=new ArrayList();

	public XtreeLoader(){
		
	}
	public void addTreeNode(TreeNode treeNode){
		treeNodes.add(treeNode);
	}
	public List getTreeNodes() {
		return treeNodes;
	}	
	public String getDocString(){
		String treedoc="";
		Document doc=new Document();
		Element rootE=new Element("tree");
		doc.setRootElement(rootE);
		Format format = Format.getPrettyFormat();
		format.setEncoding("gb2312");
		format.setIndent("  ");
		format.setExpandEmptyElements(false);
		if(treeNodes==null) return treedoc;
		for(int i=0;i<treeNodes.size();i++){
			TreeNode treeNode=(TreeNode)treeNodes.get(i);
			Element subE=new Element("tree");
			subE.setAttribute("text",StringFormat.replaceNull(treeNode.getText()));
			subE.setAttribute("src", StringFormat.replaceNull(treeNode.getSrc()));
			subE.setAttribute("action",StringFormat.replaceNull(treeNode.getAction()));
			subE.setAttribute("target", StringFormat.replaceNull(treeNode.getTarget()));
			rootE.addContent(subE);
		}
	    XMLOutputter output=new XMLOutputter(format);		
		treedoc=output.outputString(doc);
		return treedoc;
	}
	public String render(){
		StringBuilder buf=new StringBuilder();
		if(treeNodes==null) return buf.toString();
		for(int i=0;i<treeNodes.size();i++){
			TreeNode treeNode=(TreeNode)treeNodes.get(i);
			if("true".equals(treeNode.getIsleaf())){
				buf.append("var subtree=new WebFXTreeItem(\""+treeNode.getText()+"\",\""+treeNode.getAction()+"\");\n");
				buf.append("subtree.target=\""+treeNode.getTarget()+"\";\n");
				buf.append("tree.add(subtree);\n");
			}else{
				buf.append("var subtree=new WebFXLoadTreeItem(\""+treeNode.getText()+"\",\""+treeNode.getSrc()+"\",\""+treeNode.getAction()+"\",tree);\n");
				buf.append("subtree.target=\""+treeNode.getTarget()+"\";\n");				
			}
		}
		return buf.toString();
	}
}
