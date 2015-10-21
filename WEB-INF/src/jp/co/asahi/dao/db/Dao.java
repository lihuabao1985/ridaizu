package jp.co.asahi.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;

import jp.co.asahi.config.Config;

/**
 * DB接続
 * <BR>
 * @author tsushima
 *
 */
public class Dao {

	/**
	 * ステートメン�?
	 */
	private PreparedStatement stmt = null;

	/**
	 * リザルトセッ�?
	 */
	private ResultSet rs = null;



	/**
	 * コネクション
	 */
	private Connection con = null;


	/**
	 * システムログ
	 */
//	private final Logger log = Logger.getLogger(this.getClass().getName());


	/**
	 * コネクション数内部カウン�?
	 */
//	private static int ccount = 0;

	/**
	 * デフォルトコンストラクタ
	 */
	public Dao() {
		// log.debug("Dao Initialize Start.");
		try {
			// ドライバクラスをロー�?
			Class.forName("com.mysql.jdbc.Driver");
			// データベースへ接�?
			String format = "jdbc:mysql://%s:%s/%s";
			String url = String.format(format,
					Config.getString("DB_HOSTNAME"), Config.getString("DB_HOSTPORT"), Config.getString("DB_DBNAME"));
			// log.debug("DB : " + url);
			con = DriverManager.getConnection(url,
					Config.getString("DB_USERNAME"),
					Config.getString("DB_PASSWORD"));

			con.setAutoCommit(true);
			// log.debug("Database Access Object Create Success.");
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Dao Initialize Exception.");
		}
		// log.debug("Dao Initialize End.");
	}

	/**
	 * デフォルトコンストラクタ
	 */
	public Dao(String databaseName) {
		// log.debug("Dao Initialize Start.");
		try {
			// ドライバクラスをロー�?
			Class.forName("com.mysql.jdbc.Driver");
			// データベースへ接�?
			String format = "jdbc:mysql://%s:%s/%s";
			String url = String.format(format,
					Config.getString("DB_HOSTNAME"), Config.getString("DB_HOSTPORT"), databaseName);
			// log.debug("DB : " + url);
			con = DriverManager.getConnection(url,
					Config.getString("DB_USERNAME"),
					Config.getString("DB_PASSWORD"));

			con.setAutoCommit(true);
			// log.debug("Database Access Object Create Success.");
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Dao Initialize Exception.");
		}
		// log.debug("Dao Initialize End.");
	}

	/**
	 * DBコネクションを切�?
	 *
	 * @return 切断が成功した場合true
	 */
	public boolean closeConnection(){
		// log.debug("Dao Close Start.");
		releaseStatement();

		if(con == null){
			// log.error("closeConnection Connection is null.");
			return false;
		}

		try{
//			ccount--;
			// log.debug("Dao Connection Count[" + ccount + "]");
			con.close();
			// log.debug("release dbconn");
		}catch(Exception e){
			// log.error("Connection Close Exception.",e);
			return false;
		}
		con = null;
		// log.debug("Dao Close End.");
		return true;

	}

	/**
	 * トランザクション開始。（オートコミットを無効�?
	 *
	 * @return 成功した場合 true
	 */
	public boolean beginTransaction(){
		// log.debug("beginTransaction Start.");
		if(con == null){
			// log.error("beginTransaction Connection is null.");
			return false;
		}

		try{
			con.setAutoCommit(false);
		}catch(Exception e){
			// log.error("beginTransaction Exception.",e);
		}
		// log.debug("beginTransaction End.");

		return true;
	}

	/**
	 * トランザクション終了。（コミット-オートコミットを有効�?
	 *
	 * @return 成功した場合 true
	 */
	public boolean commitTransaction(){
		// log.debug("commitTransaction Start.");
		if(con == null){
			// log.error("commitTransaction Connection is null.");
			return false;
		}

		try{
			releaseStatement();
			con.commit();
			con.setAutoCommit(true);
		}catch(Exception e){
			// log.error("commitTransaction Exception.",e);
		}
		// log.debug("commitTransaction End.");
		return true;
	}

	/**
	 * ロールバック
	 *
	 * @return 成功した場合 true
	 */
	public boolean rollbackTransaction(){
		// log.debug("rollbackTransaction Start.");
		if(con == null){
			// log.error("rollbackTransaction Connection is null.");
			return false;
		}

		try{
			con.rollback();
			con.setAutoCommit(false);
		}catch(Exception e){
			// log.error("rollbackTransaction Exception.",e);
		}

		// log.debug("rollbackTransaction End.");
		return true;
	}


	/**
	 * 使用中のステートメント・リザルトセットをクリアする�?
	 *
	 * @return 成功した場合�?true
	 */
	private boolean releaseStatement(){
		// log.debug("releaseStatement Start.");
		try{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
		}catch(Exception e){
			// log.error("releaseStatement Exception.",e);
			rs = null;
			stmt = null;
			return false;
		}
		// log.debug("releaseStatement End.");
		return true;
	}


	/**
	 * Select文を発行する
	 *
	 * @param sql 実行するSQL
	 * @return
	 */
	public ResultSet doSelect(String sql){
		return doSelect(sql,new Object[0]);
	}


	/**
	 * パラメタ付きSQL文を発行する
	 *
	 * @param sql SQL�?
	 * @param params パラメー�?
	 * @return 結果のセットを返す�?内部エラーが発生した場合はnull�?
	 */
	public ResultSet doSelect(String sql,Object[] params){
		// log.debug("doSelect Start.");

		releaseStatement();

		if(con == null){
			// log.error("doSelect Connection is null.");
			return null;
		}


//		System.out.println("SQL:" + getTraceSQL(sql,params));
		// log.debug("SQL:" + getTraceSQL(sql,params));

		try{
			stmt = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				if (params[i] == null) {
					stmt.setNull(i + 1, Types.VARCHAR);
				} else {
					if(params[i].getClass() == Timestamp.class){
						stmt.setTimestamp(i + 1, (Timestamp)params[i]);
					}else if(params[i].getClass() == String.class){
						String s = params[i].toString();
						if (s.length() == 0) {
							stmt.setNull(i + 1, Types.VARCHAR);
						} else {
							stmt.setString(i + 1, s);
						}
					}else{
						stmt.setObject(i + 1, params[i]);
					}
				}
			}
			rs = stmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			// log.error("doSelect Exception.",e);
			rs = null;
		}
		// log.debug("doSelect End.");
		return rs;
	}



	/**
	 * Insert Delete Update 文を発行する。（DBに対してdoUpdateという意味）
	 *
	 * @param sql 実行するSQL
	 * @return
	 */
	public int doUpdate(String sql){
		return doUpdate(sql,new Object[0]);
	}

	/**
	 * Insert Delete Update 文を発行する�?
	 *
	 * @param sql 実行するSQL
	 * @param params バインド変数�?
	 * @return 成功した場合は対象レコードの件数を返す�?エラーの場合�?1
	 */
	public int doUpdate(String sql,Object[] params){
		// log.debug("doUpdate Start.");
		int ret = 0;

		releaseStatement();

		if(con == null){
			// log.error("doUpdate Connection is null.");
			return -1;
		}

		// log.debug("SQL:" + getTraceSQL(sql,params));

		try{
			stmt = con.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				if (params[i] == null) {
					stmt.setNull(i + 1, Types.VARCHAR);
				} else {
					if(params[i].getClass() == Timestamp.class){
						stmt.setTimestamp(i + 1, (Timestamp)params[i]);
					}else if(params[i].getClass() == String.class){
						String s = params[i].toString();
						if (s.length() == 0) {
							stmt.setNull(i + 1, Types.VARCHAR);
						} else {
							stmt.setString(i + 1, s);
						}
					}else{
						stmt.setObject(i + 1, params[i]);
					}
				}
			}

			ret = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("doUpdate Exception.",e);

			System.out.println("SQL:" + getTraceSQL(sql,params));
			return -1;
		}
		// log.debug("doUpdate End.");
		return ret;
	}

	/**
	 * SQLトレース用の文字列を取得する�?
	 *
	 * @param sql
	 * @param params
	 * @return String SQL�?
	 */
	private  String getTraceSQL(String sql, Object[] params) {
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




	/**
	 * パラメタで指定した名前のシーケンスを左埋めで取得する�?
	 *
	 * @param seqname
	 * @return
	 */
	public String getSeq(String seqname){
		return getSeq(seqname,0,' ');
	}
	/**
	 * パラメタで指定した名前のシーケンスを左埋めで取得する�?
	 *
	 * @param seqname 取得するシーケンスの名前
	 * @param length 文字列長
	 * @param chr 埋め文字
	 * @return 成功した場合シーケンス�? エラーの場合はnull
	 */
	public String getSeq(String seqname,int length,char chr){
		// log.debug("getSeq Start.");
		String ret = null;

		StringBuffer sql = new StringBuffer();
		if(length != 0){
			sql.append("SELECT lpad(NEXTVAL(?)," + length + ",\'" + chr + "\')");
		}else{
			sql.append("SELECT NEXTVAL(?)");
		}

		String[] params = {seqname};

		ResultSet rs = this.doSelect(sql.toString(),params);
		try{
			rs.next();
			//!!注意!! lpad関数により取得している為、取得名はlpadになる�?
			if(length != 0){
				ret = rs.getString("lpad");
			}else{
				ret = rs.getString("nextval");
			}
		}catch(Exception e){
			// log.debug("getSeq Exception.",e);
			return null;
		}
		// log.debug("getSeq End.");
		return ret;
	}
}
