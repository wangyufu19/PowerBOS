package com.powerbosframework.jdbc.core;

import java.sql.Connection;
import java.sql.SQLException;

import com.powerbosframework.jdbc.core.Transaction;

public class TransactionManager implements Transaction {
	private Connection connection;
	private boolean committed = true;
	private boolean rolledBack = true;

	public TransactionManager(Connection connection){
		this.connection = connection;
	
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		connection.setAutoCommit(false);
		connection.commit();
		connection.setAutoCommit(true);
		this.committed = connection.getAutoCommit();
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public void rollback() throws SQLException {
		connection.rollback();
		this.rolledBack = false;
	}

	/**
	 * 事务是否已经提交
	 * 
	 * @return
	 */
	public boolean wasCommitted() {
		return this.committed;
	}

	/**
	 * 事务是否已经回滚
	 * 
	 * @return
	 */
	public boolean wasRolledBack() {
		return this.rolledBack;
	}

}
