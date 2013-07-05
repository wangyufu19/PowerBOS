package com.powerbosframework.jdbc.core;

import java.sql.SQLException;

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
