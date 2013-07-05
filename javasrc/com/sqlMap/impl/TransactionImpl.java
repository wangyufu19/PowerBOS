package com.sqlMap.impl;
import java.sql.Connection;
import java.sql.SQLException;
import com.sqlMap.Transaction;
/**
 * 事务操作实现类
 * @author youfu.wang
 * @version 1.0
 */
public class TransactionImpl implements Transaction{
	private Connection connection;
	private boolean committed=true;
	private boolean rolledBack=true;
	
	public TransactionImpl(Connection connection){
		this.connection=connection;
	}
	/**
	 * 提交事务
	 * @throws SQLException
	 */
	public void commit() throws SQLException {		
		connection.commit();
		connection.setAutoCommit(true);	
		this.committed=connection.getAutoCommit();
	}
	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public void rollback() throws SQLException {
		connection.rollback();
		this.rolledBack=false;
	}
	/**
	 * 事务是否已经提交
	 * @return
	 */
	public boolean wasCommitted() {		
		return this.committed;
	}
	/**
	 * 事务是否已经回滚
	 * @return
	 */
	public boolean wasRolledBack() {		
		return this.rolledBack;
	}

}
