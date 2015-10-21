package jp.co.asahi.dao.db;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public final class DBManager {
	private DBManager(){

	}
	/**
	 * システムログ
	 */
	private static final Logger LOG = Logger.getLogger(DBAccess.class);
	private static final ThreadLocal<Connection> CONNS = new ThreadLocal<Connection>();
	private static DataSource ds = null;

	static{initDao();}

	private static void initDao(){
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {}
	}
	public static void setDataSource(DataSource dataSource){
		ds = dataSource;
	}
	/**
	 * 当ThreadのDao対象を取得する
	 * @return
	 * @ticket
	 * @author jin@isr.co.jp
	 * @data   2011/12/27
	 */
	public static Connection getConnection(){
		try {
			Connection conn = CONNS.get();
			if(conn ==null || conn.isClosed()){
				conn = ds.getConnection();
				CONNS.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			LOG.error("", e);
			return null;
		}
	}
	/**
	 * 当ThreadのDao対象を解放する
	 *
	 * @ticket
	 * @author jin@isr.co.jp
	 * @data   2011/12/27
	 */
	public static void closeConnection(){
		Connection conn = CONNS.get();
		if(conn == null)return;
		try {
			if(!conn.isClosed()) conn.close();
		} catch (SQLException e) {LOG.error("", e);}
		conn = null;
		CONNS.set(null);
		CONNS.remove();
	}


	/**
	 * トランザクション開始。（オートコミットを無効�?
	 *
	 * @return 成功した場合 true
	 * @throws SQLException
	 */
	public static boolean beginTransaction() throws SQLException{
		LOG.debug("beginTransaction Start.");
		Connection conn = CONNS.get();
		if(conn ==null || conn.isClosed()){
			conn = ds.getConnection();
			CONNS.set(conn);
		}

		if(conn == null){
			 LOG.error("beginTransaction Connection is null.");
			return false;
		}

		try{
			conn.setAutoCommit(false);
		}catch(Exception e){
			 LOG.error("beginTransaction Exception.",e);
		}
		 LOG.debug("beginTransaction End.");

		return true;
	}

	/**
	 * トランザクション終了。（コミット-オートコミットを有効�?
	 *
	 * @return 成功した場合 true
	 * @throws SQLException
	 */
	public static boolean commitTransaction() throws SQLException{
		 LOG.debug("commitTransaction Start.");
		Connection conn = CONNS.get();
		if(conn ==null || conn.isClosed()){
			conn = ds.getConnection();
			CONNS.set(conn);
		}

		if(conn == null){
			 LOG.error("commitTransaction Connection is null.");
			return false;
		}

		try{
			conn.commit();
			conn.setAutoCommit(true);
		}catch(Exception e){
			 LOG.error("commitTransaction Exception.",e);
		}
		 LOG.debug("commitTransaction End.");
		return true;
	}

	/**
	 * ロールバック
	 *
	 * @return 成功した場合 true
	 * @throws SQLException
	 */
	public static boolean rollbackTransaction() {
		 LOG.debug("rollbackTransaction Start.");
		Connection conn = CONNS.get();
		if(conn == null){
			 LOG.error("rollbackTransaction Connection is null.");
			return false;
		}

		try{
			conn.rollback();
			conn.setAutoCommit(false);
		}catch(Exception e){
			 LOG.error("rollbackTransaction Exception.",e);
		}

		 LOG.debug("rollbackTransaction End.");
		return true;
	}


}
