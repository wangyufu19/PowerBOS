package com.sqlMap;
import java.sql.SQLException;
/**
 * 事务操作接口
 * @author youfu.wang
 * @version 1.0
 */
public interface Transaction {
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public void commit()throws SQLException;
	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public void rollback()throws SQLException;
	/**
	 * 事务是否已经提交
	 * @return
	 */
	public boolean wasCommitted();
	/**
	 * 事务是否已经回滚
	 * @return
	 */
	public boolean wasRolledBack();

}
