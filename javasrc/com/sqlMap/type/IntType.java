package com.sqlMap.type;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 整型类型对象
 * @author youfu.wang
 * @version 1.0
 */
public class IntType {
	/**
	 * 从数据集中得到指定列的值
	 * @param rst
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public java.lang.Integer get(ResultSet rst,String name) throws SQLException{
		if(rst.getObject(name)==null) return null;
		return rst.getInt(name);
	}
}
