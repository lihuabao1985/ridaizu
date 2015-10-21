package jp.co.asahi.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jp.co.asahi.config.Config;
import jp.co.asahi.util.Util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;

import com.google.common.base.Strings;

public class Common {
	/**
	 * システムログ
	 */
	protected static Logger systemLogger = Logger.getLogger("system");

	/**
	 * 数値が含まれている事を示すパターン
	 */
	private final static Pattern patternHasNumeric =
		Pattern.compile("[[\\p{Graph}][ ]]*[0-9]+[[\\p{Graph}][ ]]*");

	/**
	 * アルファベットが含まれている事を示すパターン
	 */
	private final static Pattern patternHasAlphabet =
		Pattern.compile("[[\\p{Graph}][ ]]*[a-zA-Z]+[[\\p{Graph}][ ]]*");

	/**
	 * 大文字が含まれている事を示すパターン
	 */
	private final static Pattern patternHasCapital =
		Pattern.compile("[[\\p{Graph}][ ]]*[A-Z]+[[\\p{Graph}][ ]]*");

	/**
	 * 小文字が含まれている事を示すパターン
	 */
	private final static Pattern patternHasSmall =
		Pattern.compile("[[\\p{Graph}][ ]]*[a-z]+[[\\p{Graph}][ ]]*");

	/**
	 * 記号が含まれている事を示すパターン
	 */
	private final static Pattern patternHasSign =
		Pattern.compile("[[\\p{Graph}][ ]]*[[\\p{Punct}][ ]]+[[\\p{Graph}][ ]]*");

	/**
	 * DBデータキャッシュ時間
	 */
	private static long maxCacheTime = 0L;

	/**
	 * 引数の文字列が空白、またはnullであるかチェックする。
	 * @param _arg チェック対象文字列
	 * @return 空文字または空白の場合true
	 */
	public static boolean isEmpty(String _arg)
	{
		if(_arg != null && !_arg.equals(""))
		{
			return false;
		}
		return true;
	}


	/**
	 * 引数の文字列が空白、またはnullであるかチェックする。
	 * @param _arg チェック対象文字列
	 * @return 空文字または空白の場合true
	 */
	public static boolean isEmpty(Object _arg)
	{
		if(_arg == null)
		{
			return true;
		}
		if(_arg instanceof String){
			return Common.isEmpty(_arg.toString());
		}
		return false;
	}

	/**
	 * 数字が含まれているかチェック。
	 * @param _arg
	 * @return
	 */
	public static boolean hasNumeric(String _arg){
		if(patternHasNumeric.matcher(_arg).matches()){
			return true;
		}
		return false;
	}

	/**
	 * アルファベットが含まれているかチェック。
	 * @param _arg
	 * @return
	 */
	public static boolean hasAlphabet(String _arg){
		if(patternHasAlphabet.matcher(_arg).matches()){
			return true;
		}
		return false;
	}

	/**
	 * 大文字が含まれているかチェック。
	 * @param _arg
	 * @return
	 */
	public static boolean hasCapital(String _arg){
		if(patternHasCapital.matcher(_arg).matches()){
			return true;
		}
		return false;
	}

	/**
	 * 小文字が含まれているかチェック。
	 * @param _arg
	 * @return
	 */
	public static boolean hasSmall(String _arg){
		if(patternHasSmall.matcher(_arg).matches()){
			return true;
		}
		return false;
	}

	/**
	 * 記号が含まれているかチェック。
	 * @param _arg
	 * @return
	 */
	public static boolean hasSign(String _arg){
		if(patternHasSign.matcher(_arg).matches()){
			return true;
		}
		return false;
	}

	/**
	 * LDAPのフィルタリング条件用のエスケープ処理<br>
	 * RFC2254 Page6 5. Examples
	 * @param _arg
	 * @return
	 */
	public static String escapeForLdapFilter(String _arg){
		String result = _arg.replaceAll("\\\\", "\\\\5c");
		result = result.replaceAll("\\(", "\\\\28");
		result = result.replaceAll("\\)", "\\\\29");
		result = result.replaceAll("\\*", "\\\\2a");
		return result;
	}

	/**
	 * プロキシが経由された場合付与されるヘッダ”X-Forwarded-For”
	 * が存在すれば優先して返す。
	 * @param req
	 * @return
	 */
    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }

	/**
	 * 各キャリアのユーザーエージェント判別用
	 */
	private static final String TYPE_STR_DOCOMO = "DoCoMo";
	private static final String TYPE_STR_JPHONE = "J-PHONE";
	private static final String TYPE_STR_VODAFONE = "Vodafone";
	private static final String TYPE_STR_KDDI = "KDDI-";
	private static final String TYPE_STR_AU = "UP.Browser";
	private static final String TYPE_STR_SOFTBANK = "SoftBank";
	private static final String TYPE_WILLCOM = "WILLCOM";
	private static final String TYPE_DDIPOCKET = "DDIPOCKET";
	private static final String TYPE_MOBILE = "Mobile";
	private static final String TYPE_SAFARI = "Safari";
	private static final String TYPE_IPHONE = "iPhone";
	private static final String TYPE_ANDROID = "Android";
	private static final String TYPE_IPAD = "iPad";

	public static boolean isMobile(HttpServletRequest request){
		String strUserAgent = request.getHeader("User-Agent");
		if(strUserAgent.indexOf(TYPE_STR_DOCOMO) != -1){
			return true;
		}else if(strUserAgent.indexOf(TYPE_STR_JPHONE) != -1){
			return true;
		}else if(strUserAgent.indexOf(TYPE_STR_VODAFONE) != -1){
			return true;
		}else if(strUserAgent.indexOf(TYPE_STR_SOFTBANK) != -1){
			return true;
		}else if(strUserAgent.indexOf(TYPE_STR_KDDI) != -1 || strUserAgent.indexOf(TYPE_STR_AU) != -1){
			return true;
		}else if(strUserAgent.indexOf(TYPE_WILLCOM) != -1 || strUserAgent.indexOf(TYPE_DDIPOCKET) != -1){
			return true;
		}
		return false;
	}

	public static boolean isSmartphone(HttpServletRequest request){

		String strUserAgent = request.getHeader("User-Agent");

		if(strUserAgent.indexOf(TYPE_MOBILE) != -1 && strUserAgent.indexOf(TYPE_SAFARI) != -1){
			systemLogger.debug("from smartphone browser access.");
			return true;
		}

		// アンドロイドタブレット端末からのアクセス
		if(strUserAgent.indexOf(TYPE_ANDROID) != -1 && strUserAgent.indexOf(TYPE_SAFARI) != -1){
			systemLogger.debug("from android tablet browser access.");
			return true;
		}

		// iPhoneでのカスタマイズブラウザ
		if(strUserAgent.indexOf(TYPE_MOBILE) != -1 && strUserAgent.indexOf(TYPE_IPHONE) != -1){
			systemLogger.debug("from iPhone customize browser access.");
			return true;
		}

		// iPadからのアクセス
		if(strUserAgent.indexOf(TYPE_MOBILE) != -1 && strUserAgent.indexOf(TYPE_IPAD) != -1){
			systemLogger.debug("from iPad customize browser access.");
			return true;
		}

		return false;
	}

	public static String getCarrierName(HttpServletRequest request){
		String strUserAgent = request.getHeader("User-Agent");
		if(strUserAgent.indexOf(TYPE_STR_DOCOMO) != -1){
			return "DoCoMo";
		}else if(strUserAgent.indexOf(TYPE_STR_JPHONE) != -1){
			return "J-Phone";
		}else if(strUserAgent.indexOf(TYPE_STR_VODAFONE) != -1){
			return "Vodafone";
		}else if(strUserAgent.indexOf(TYPE_STR_SOFTBANK) != -1){
			return "SoftBank";
		}else if(strUserAgent.indexOf(TYPE_STR_KDDI) != -1 || strUserAgent.indexOf(TYPE_STR_AU) != -1){
			return "AU";
		}else if(strUserAgent.indexOf(TYPE_WILLCOM) != -1 || strUserAgent.indexOf(TYPE_DDIPOCKET) != -1){
			return "WILLCOM";
		}
		return MessageResource.getString("LABEL_OTHER",request);
	}

	public static String getCarrierId(HttpServletRequest request){
		String strUserAgent = request.getHeader("User-Agent");
		if(strUserAgent.indexOf(TYPE_STR_DOCOMO) != -1){
			return "1";
		}else if(strUserAgent.indexOf(TYPE_STR_JPHONE) != -1){
			return "3";
		}else if(strUserAgent.indexOf(TYPE_STR_VODAFONE) != -1){
			return "3";
		}else if(strUserAgent.indexOf(TYPE_STR_SOFTBANK) != -1){
			return "3";
		}else if(strUserAgent.indexOf(TYPE_STR_KDDI) != -1 || strUserAgent.indexOf(TYPE_STR_AU) != -1){
			return "2";
		}else if(strUserAgent.indexOf(TYPE_WILLCOM) != -1 || strUserAgent.indexOf(TYPE_DDIPOCKET) != -1){
			return "4";
		}
		return "0";
	}

	/**
	 * ランダムな端末IDを取得して返す。
	 * @return
	 */
	public static String generateTerminalId(){
		String result = RandomStringUtils.randomAlphanumeric(32);
		return result;
	}





	/**
	 * URLをデコードする.
	 *
	 * @param url URL
	 * @param encType エンドタイプ
	 * @return デコード後URL
	 */
	public static String getDecodeURL(String url, String encType) {
		String result = url;

		// URLがエンコードされた場合、デコードする。
		try {
			new URL(url);
		} catch (MalformedURLException e) {

			try {
				result = URLDecoder.decode(url, encType);
			} catch (UnsupportedEncodingException e1) {

				systemLogger.error("URL decode failed", e);
			}
		}

		return result;
	}

	public static String byteArrayToHexString (byte[] byteArray) {
		StringBuffer stringBuffer = new StringBuffer(byteArray.length * 2);
		for (int count = 0; count < byteArray.length; count ++) {
			if (((int) byteArray[count] & 0xff) < 0x10)
				stringBuffer.append("0");
			stringBuffer.append(Long.toString((int) byteArray[count] & 0xff, 16));
		}
		return stringBuffer.toString();
	}

	public static byte[] hexStringToByteArray (String hexString) {
		byte[] byteArray = new byte[hexString.length()/2];
		for (int count = 0; count < hexString.length()/2; count++) {
			String byteString = hexString.substring(count*2, count*2 + 2);
			byteArray[count] = (byte)(Integer.parseInt(byteString, 16) & 0xff);
		}
		return byteArray;
	}

	public static byte[] encrypt(byte[] key, byte[] plain) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		return cipher.doFinal(plain);
	}

	public static byte[] decrypt(byte[] key, byte[] encrypted) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		return cipher.doFinal(encrypted);
	}

	public static byte[] generateSecretKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * Turns array of bytes into string
	 *
	 * @param buf
	 *            Array of bytes to convert to hex string
	 * @return Generated hex string
	 */
	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**
	 * リクエスト要求を解析
	 * @param request
	 * @return 解析結果.
	 */
	public static String getRequestXML(HttpServletRequest request) {
		systemLogger.debug("analyze request xml.");
		try {
			BufferedReader in = request.getReader();

			StringBuffer sbXML = new StringBuffer();
			String line = null;
			while (in.ready() && (line = in.readLine()) != null) {
			    //有用なデータなし
				sbXML.append(line);
			}

			// 入力ストリームを閉じます
			in.close();

			systemLogger.debug("request xml: " + sbXML.toString());
			return sbXML.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			systemLogger.info("Reader XML Exception :" + e);
			return null;
		}
	}

	/**
	 * 暗号鍵を取得して返す。
	 * @return
	 */
	public static String generateEncryptKey(){
		String result = RandomStringUtils.randomAlphanumeric(16);
		return result;
	}

	public static String getRequestMessage(HttpServletRequest httpServletRequest) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
		String requestMessage = "";
		String line = null;
		while(null != (line = bufferedReader.readLine())) {
			requestMessage += line;
		}
		return requestMessage;
	}

	public static String getServletContextInitValue(ServletContext servletContext, String param, String def) {
		String rtn = null;

		String val = servletContext.getInitParameter(param);

		if (!Common.isEmpty(val)) {
			rtn = val;
		} else {
			rtn = def;
		}

		return rtn;
	}

	public static long getMaxCacheTime() {
		if (maxCacheTime == 0L) {
			if (isEmpty(Config.getString("CACHE_UPDATE_TIME"))) {
				maxCacheTime = 3600000;
			} else {
				maxCacheTime = Config.getInt("CACHE_UPDATE_TIME") * 60000;
			}
		}

		return maxCacheTime;
	}

    public static Locale getLocale(HttpServletRequest req) {
        Locale l = Locale.getDefault();

        try {
            l = new Locale(getLang(req));
        } catch (Exception e) {
        }

        return l;
    }

	/**
	 * リクエストからlangパラメータを取得する。<br>
	 * パラメータが存在しなければセッションの値を返し、<br>
	 * パラメータが存在したらセッションの値を上書きする。<br>
	 *
	 * @param request
	 * @return
	 */
	public static String getLang(HttpServletRequest request) {
		String lang = request.getParameter("lang");

		if (lang == null)
			lang = request.getParameter("hl");

		if (lang == null)
			lang = (String) request.getSession().getAttribute("lang");

		if (lang == null) {
			String acceptLangHeader = request.getHeader("Accept-Language");

			if (acceptLangHeader != null) {
				String[] acceptLanguages = acceptLangHeader.split(",");
				if (acceptLanguages.length > 0)
					lang = acceptLanguages[0];
			}
		}

		return lang;
	}

	public static String formatColumnName(String columnName) {

		char[] chars = columnName.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (isUpperCase(c)) {
				sb.append("_").append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static boolean isUpperCase(char c) {

		return Character.isUpperCase(c);
	}

	public static String getAreaByZipcode(String zipcode) {

		String searchUrl = String.format("http://zip.cgis.biz/xml/zip.php?zn=%s", zipcode.replaceAll("-", ""));

		String xml = getUrlContent(searchUrl);
		String zipcodeName = null;
		Document doc = Util.createJdomDoc(xml);
		List<Element> elementList = doc.getRootElement().getChild("ADDRESS_value").getChildren("value");
		for (Element element : elementList) {
			String attrValue = element.getAttributeValue("city");
			if (!Strings.isNullOrEmpty(attrValue)) {
				zipcodeName = attrValue;
				break;
			}
		}

		return zipcodeName;
	}

	public static String getUrlContent(String sUrl) {
		StringBuffer content = new StringBuffer();
		BufferedReader reader = null;
		URLConnection connection = null;

		try {
			URL url = new URL(sUrl);
			connection = url.openConnection();
			InputStream inputstream = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputstream,
					"utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}

	public static int getPrice(double cost) {

		return (int)((cost + Config.getInt("PROFIT"))  * (1 + Config.getDouble("TAX")/100));
	}

	public static String formatNumberForPrefixZero(String value, int length) {

		if (Strings.isNullOrEmpty(value)) {
			return null;
		}

		String format = "%0" + length + "d";

		return String.format(format, Integer.valueOf(value));
	}

	public static void main(String[] args) {
	    System.out.println(formatNumberForPrefixZero("10010", 5));
	}



}
