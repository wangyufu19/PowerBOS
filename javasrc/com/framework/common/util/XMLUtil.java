package com.framework.common.util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * XML帮助类
 * @author wangyf
 * @version 1.0
 */
public class XMLUtil {
	
	public XMLUtil(){
		
	}
	
	public Document parse(String resource){				
		SAXBuilder builder=new SAXBuilder(false);				
		Document doc=null;			
		ByteArrayInputStream bin=null;
		try {
			bin = new ByteArrayInputStream(resource.getBytes(SysConstants.charset_code));
		} catch (UnsupportedEncodingException e1) {			
			e1.printStackTrace();
		}
		try {
			doc=builder.build(bin);
		} catch (JDOMException e) {		
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return doc;				
	}
	
	public Document parse(File file){		
		SAXBuilder builder=new SAXBuilder();		
		Document doc=null;						
		try {
			doc=builder.build(file);			
		} catch (JDOMException e) {				
			e.printStackTrace();
		} catch (IOException e) {				
			e.printStackTrace();
		}			
		return doc;				
	}
	public Document parse(InputStream stream){
		SAXBuilder builder=new SAXBuilder();	
		Document doc=null;	
		try {
			doc=builder.build(stream);
		} catch (JDOMException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return doc;
	}
	public Document parse(URL url){
		SAXBuilder builder=new SAXBuilder();	
		Document doc=null;	
		try {
			doc=builder.build(url);
		} catch (JDOMException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return doc;
	}
	public Format getFormat() {
		Format format = Format.getPrettyFormat();
		format.setEncoding(SysConstants.charset_code);
		format.setIndent("  ");
		format.setExpandEmptyElements(false);
		return format;
	}
	public void output(Document doc,String arg1){
		if(doc==null||arg1==null) return;
		File file=new File(arg1);
		this.output(doc, file);
	}
	public void output(Document doc,File file){
		XMLOutputter outputter = new XMLOutputter(getFormat());
		FileOutputStream out=null;		
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		}
		try {			
			outputter.output(doc, out);
			out.flush();
			out.close();			
		} catch (Exception e) {			
			e.printStackTrace(System.out);
		}		
	}
}
