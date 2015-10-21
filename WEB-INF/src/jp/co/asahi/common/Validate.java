package jp.co.asahi.common;

import java.sql.Timestamp;
import java.util.regex.Pattern;

import jp.co.asahi.util.DateUtil;

/**
 * @author tsushima
 * 各入力のチェックメソッド群
 */
public class Validate {
	/**
	 * メールアドレスのパターン
	 */
	//private static final Pattern patternMailAddress =
	//	Pattern.compile("[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+");


	/**
	 * ユーザーIDのパターン
	 */
	private static final Pattern patternUserId =
		Pattern.compile("[\\w\\-\\.@\']+");

	/**
	 * パスワードのパターン
	 */
	private static final Pattern patternPassword =
		Pattern.compile("[[\\p{Graph}][ ]]*");

	/**
	 * 日付のパターン
	 */
	private static final Pattern patternDate = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\/\\/\\s]?((((0?"
			+ "[13578])|(1[02]))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))"
			+ "|(((0?[469])|(11))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
			+ "(0?2[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"
			+ "35679])|([13579][01345789]))[\\/\\/\\s]?((((0?[13578])|(1[02]))"
			+ "[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))"
			+ "[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/\\/\\s]?((0?["
			+ "1-9])|(1[0-9])|(2[0-8]))))))");

	/**
	 * ログイン時に入力するユーザID
	 * @param _arg
	 * @return 入力値として正常な場合True
	 */
	public static boolean checkUserId(String _arg){
		if(!Common.isEmpty(_arg)){
			if(_arg.length() <= 128){
				if(patternUserId.matcher(_arg).matches()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * ログイン時に入力するパスワード<br>
	 * @param _arg
	 * @return 入力値として正常な場合True
	 */
	public static boolean checkPassword(String _arg){
		if(!Common.isEmpty(_arg)){
			if(_arg.length() <= 128){
				if(patternPassword.matcher(_arg).matches()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 日付チェック
	 * @param _arg
	 * @return
	 */
	public static boolean checkDate(String _arg) {
		if(!Common.isEmpty(_arg)){
			if(patternDate.matcher(_arg).matches()){
				return true;
			}
		}

		return false;
	}

	/**
	 * 日付チェック
	 * @param _arg
	 * @return
	 */
	public static boolean isAfterToday(String _arg) {

		Timestamp currentDateTime = DateUtil.getCurrentDateTime();
		Timestamp expirationDate  = DateUtil.stringToTimestamp(
										DateUtil.SHORT_DATE,
										DateUtil.addDayDate(_arg, 1));

		return expirationDate.after(currentDateTime);
	}

}
