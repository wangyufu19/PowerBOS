package com.framework.view.data;
import java.sql.SQLException;
import org.jdom.Element;
import com.framework.common.servlet.http.RequestHash;
import com.framework.view.adapter.ParameterClassReader;
import com.framework.view.adapter.StatementReader;
import com.framework.view.util.WidgetException;
/**
 * 表单数据类
 * @author wangyf
 * @version 1.0
 */
public class FormData {
	private RequestHash reh;
	private String parameterClass;
	private String returnClass;
	private String resultMap;
	
	public FormData(RequestHash reh){
		this.reh=reh;
	}

	public String getParameterClass() {
		return parameterClass;
	}

	public void setParameterClass(String parameterClass) {
		this.parameterClass = parameterClass;
	}

	public String getReturnClass() {
		return returnClass;
	}

	public void setReturnClass(String returnClass) {
		this.returnClass = returnClass;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}
	public Object loadFormData(Element statementE) throws SQLException, WidgetException{
		Object obj=null;
		StatementReader statementReader=new StatementReader(reh);		
		String sql=statementReader.getStatement(statementE);
		ParameterClassReader parameterClassReader=new ParameterClassReader(reh);			
		try {
			obj=parameterClassReader.read(statementE);
		} catch (Exception e) {
			throw new WidgetException("表单对象的数据集加载参数类失败!",e);
		}			
		DataResult dataQuery=new DataResult(reh.getJdbcTmplt());
		obj=dataQuery.getDataResult(sql, obj, returnClass, resultMap);
		return obj;
	}
}
