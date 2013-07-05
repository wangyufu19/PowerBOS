package com.framework.common.xmldoc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.framework.common.file.FileUtil;
import com.framework.common.util.SysConstants;

/**
 * 功能说明:XML数据总线
 * @author wangyf
 * @version 1.0
 */
public class DocXMLUtil {
	public static HashMap<String, Document> docsHash=new HashMap<String, Document>();
	private String encoding="GB2312";
	public DocXMLUtil(){
		
	}
	/**
	 * 返回XML编码格式
	 */
	public Format getFormat() {
		Format format = Format.getPrettyFormat();
		format.setEncoding(encoding);
		format.setIndent("  ");
		format.setExpandEmptyElements(false);
		return format;
	}
	/**
	 * 构建文档对象
	 * @param path
	 * @return
	 */
	public Document buildDocument(String path){
		File file=new File(path);
		return this.buildDocument(file);
	}
	/**
	 * 构建文档对象
	 * @param file
	 * @return
	 */
	public Document buildDocument(File file){
		SAXBuilder builder=new SAXBuilder();
		Document doc=null;	
		if(file==null) return doc;
		if(!file.exists()) return doc;
		
		if(docsHash.containsKey(file.getPath())){						
			return (Document)docsHash.get(file.getPath());					
		}		
		try {
			doc=builder.build(file);			
		} catch (JDOMException e) {				
			e.printStackTrace();
		} catch (IOException e) {				
			e.printStackTrace();
		}	
		if(doc!=null){
			setFlag(doc);
		}		
		docsHash.remove(file.getPath());
		docsHash.put(file.getPath(), doc);		
		return doc;	
	}
	/**
	 * 构建文档对象
	 * @param file
	 */
	public void reBuildDocument(File file){
		SAXBuilder builder=new SAXBuilder();
		Document doc=null;	
		if(file==null||!file.exists()){			
			return;
		}	
		try {
			doc=builder.build(file);			
		} catch (JDOMException e) {				
			e.printStackTrace();
		} catch (IOException e) {				
			e.printStackTrace();
		}	
		if(doc!=null){
			setFlag(doc);
		}			
		docsHash.remove(file.getPath());
		docsHash.put(file.getPath(), doc);		
	}
	
	/**
	* 输出文档对象
	* @param arg1
	*/
	public void outputDocumentFile(String arg1){
		outputDocumentFile(new File(arg1));		
	}
	/**
	* 输出文档对象 
	* @param file  
	*/
	public void outputDocumentFile(File file){				
		XMLOutputter outputter = new XMLOutputter(getFormat());
		FileOutputStream out=null;
		Document doc=null;
		if(SysConstants.runtime_mode.equals("true")){
			String timestamp="";
			Calendar calendar = Calendar.getInstance();
			int sysMonth = calendar.get(2) + 1;
			timestamp += calendar.get(1) + "-" + sysMonth + "-" + calendar.get(5) + " "
					+ calendar.get(11)+calendar.get(12)+calendar.get(13);
			String fileName=file.getName().substring(0, file.getName().indexOf(".xml"))+"_"+timestamp+".xml";
			FileUtil fileUtil=new FileUtil();
			String newfile=SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"page"+File.separator+"bak"+File.separator+fileName;
			String oldfile=file.getPath();
			try {
				fileUtil.move(oldfile, newfile, false);
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		if(!docsHash.containsKey(file.getPath())){
			doc=buildDocument(file);			
			if(doc==null) return;
		}
		doc=(Document)docsHash.get(file.getPath());
		if(doc==null) return;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		}
		try {
			doc=(Document)doc.clone();	
		    removeFlag(doc);
			outputter.output(doc, out);
			out.flush();
			out.close();			
		} catch (Exception e) {			
			e.printStackTrace(System.out);
		}		
	}
	/**
	 * 设置文档元素标识
	 * @param doc
	 */
	public void setFlag(Document doc){
		if(doc==null) return;
		ElSequence elObj=new ElSequence();		
		doc.getRootElement().setAttribute("EID", "EID_0");		
		setFlag(doc.getRootElement(),elObj);		
		doc.getRootElement().setAttribute("idValue",""+elObj.getValue());
	}
	/**
	 * 设置文档元素标识
	 * @param e
	 * @param elObj
	 */
	public void setFlag(Element e,ElSequence elObj){		
		if(e==null||elObj==null) return;		
		if(!e.isRootElement()||e.getAttributeValue("EID")==null){	
			elObj.setElIncrement();
			e.setAttribute("EID", "EID_"+elObj.getValue());
		}			
		List list=e.getChildren();
		if(list==null) return;	
		for(int i=0;i<list.size();i++){
			Element sub=(Element)list.get(i);
			setFlag(sub,elObj);
		}			
	}
	/**
	 * 移除文档元素标识
	 * @param doc
	 */
	public void removeFlag(Document doc){
		if(doc==null) return;
		doc.getRootElement().removeAttribute("idValue");
		removeFlag(doc.getRootElement());		
	}
	/**
	 * 移除文档元素标识
	 * @param e
	 */
	public void removeFlag(Element e){
		if(e==null) return;
		List list=e.getChildren();
		if(list==null) return;		
		for(int i=0;i<list.size();i++){
			Element sub=(Element)list.get(i);
			removeFlag(sub);
		}		
		e.removeAttribute("EID");		
	}
}
