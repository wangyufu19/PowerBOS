package com.framework.view.tree;
import java.util.List;
import java.util.ArrayList;

import com.framework.view.tree.TreeNode;
/**
 * 树加载器类
 * @author wangyf
 * @version 1.0
 */
public class TreeLoader {
	private List treeNodes=new ArrayList();
	
	public TreeLoader(){
		
	}
	public void addTreeNode(TreeNode treeNode){
		treeNodes.add(treeNode);
	}
	public List getTreeNodes(){
		return treeNodes;
	}
	public String render(){		
		StringBuilder buf=new StringBuilder();		
    	if(treeNodes==null) return buf.toString();
    	for(int i=0;i<treeNodes.size();i++){
    		TreeNode treeNode=(TreeNode)treeNodes.get(i);
    		buf.append("d.add(")
		       .append("\'"+treeNode.getId()+"\',")
		       .append("\'"+treeNode.getPid()+"\',")
		       .append("\'"+treeNode.getText()+"\',")			
		       .append("\'"+treeNode.getSrc()+"\'")
		       .append(");")
		       .append("\n");
    	}
    	return buf.toString();
	}

}
