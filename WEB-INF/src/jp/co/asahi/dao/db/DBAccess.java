package jp.co.asahi.dao.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public final class DBAccess {
	private DBAccess(){
		
	}
	/**
	 * システムログ
	 */
	private static Logger logger = Logger.getLogger(DBAccess.class);
	/**
	 * Select文を発行する
	 * @param sql 実行するSQL
	 * @return 結果のセットを返す。内部エラーが発生した場合はnull。
	 * @author jin@isr.co.jp
	 * @data   2012/01/24
	 */
	public static ResultSet query(String sql){
		try{
			return executeQuery(sql);
		}catch(SQLException e){
			logger.error("",e);
		}
		return null;
	}
	/**
	 * Select文を発行する
	 * @param sql 実行するSQL
	 * @param objects パラメータ
	 * @return 結果のセットを返す。内部エラーが発生した場合はnull。
	 * @author jin@isr.co.jp
	 * @data   2012/01/24
	 */
	public static ResultSet query(String sql,Object...objects){
		try{
			System.out.println(getTraceSQL(sql, objects));
			return executeQuery(sql,objects);
		}catch(SQLException e){
			logger.error("",e);
		}
		return null;
	}
	/**
	 * Insert Delete Update 文を発行する。
	 * @param sql 実行するSQL
	 * @return 成功した場合は対象レコードの件数を返す。エラーの場合は-1
	 * @author jin@isr.co.jp
	 * @data   2012/01/24
	 */
	public static int update(String sql){
		try {
			return executeUpdate(getPreparedStatement(sql));
		} catch (SQLException e) {
			logger.error("",e);
		}
		return -1;
	}
	/**
	 * Delete Update 文を発行する。
	 * @param sql 実行するSQL
	 * @param objects パラメータ
	 * @return 成功した場合は対象レコードの件数を返す。エラーの場合は-1
	 * @author jin@isr.co.jp
	 * @data   2012/01/24
	 */
	public static int update(String sql,Object...objects){
		try {
			System.out.println(getTraceSQL(sql, objects));
			return executeUpdate(getPreparedStatement(sql,objects));
		} catch (SQLException e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * Select文を発行する
	 * @param sql 実行するSQL
	 * @return 結果の第１行の第１列の値を返す。内部エラーが発生した場合はnull。
	 * @author jin@isr.co.jp
	 * @data   2012/01/24
	 */
	public static Object single(String sql){
		try{
			ResultSet rs = executeQuery(sql);
			if(rs.next()) return rs.getObject(1);
		}catch(SQLException e){
			logger.error("",e);
		}
		return null;
	}
	/**
	 * Select文を発行する
	 * @param sql 実行するSQL
	 * @param objects パラメータ
	 * @return 結果の第１行の第１列の値を返す。内部エラーが発生した場合はnull。
	 * @author jin@isr.co.jp
	 * @data   2012/01/24
	 */
	public static Object single(String sql,Object...objects){
		try{
			ResultSet rs = executeQuery(sql,objects);
			if(rs.next()) return rs.getObject(1);
		}catch(SQLException e){
			logger.error("",e);
		}
		return null;
	}
	private static int executeUpdate(PreparedStatement pstmt) throws SQLException{
		return pstmt.executeUpdate();
	}
	private static ResultSet executeQuery(String sql,Object...objects) throws SQLException{
		return executeQuery(getPreparedStatement(sql,objects));
	}
	private static ResultSet executeQuery(PreparedStatement pstmt) throws SQLException{
		return pstmt.executeQuery();
	}
	private static PreparedStatement getPreparedStatement(String sql,Object...objects) throws SQLException{
		PreparedStatement pstmt = getPreparedStatement(sql);
		setParameters(pstmt,objects);
		return pstmt;
	}
	private static PreparedStatement getPreparedStatement(String sql) throws SQLException{
		return DBManager.getConnection().prepareStatement(sql);
	}
	private static void setParameters(PreparedStatement pstmt,Object...objects) throws SQLException{
		for (int i = 0; i < objects.length; i++) {
			pstmt.setObject(i + 1, objects[i]);
		}
	}

	/**
	 * SQLトレース用の文字列を取得する。
	 *
	 * @param sql
	 * @param params
	 * @return String SQL文
	 */
	private static String getTraceSQL(String sql, Object[] params) {
		char[] array = sql.toCharArray();
		StringBuffer sb = new StringBuffer();
		int p = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '?') {
				sb.append("'");
				if(params[p] == null){
					sb.append("null");
				}else{
					sb.append(params[p].toString());
				}
				sb.append("'");
				p++;
			} else {
				sb.append(array[i]);
			}
		}
		return sb.toString();
	}
}
