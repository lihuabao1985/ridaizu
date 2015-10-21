package jp.co.asahi.common;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;


public class MessageResource {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE_JA = ResourceBundle
			.getBundle(BUNDLE_NAME, new java.util.Locale(Locale.JAPAN.getLanguage(), Locale.JAPAN.getCountry()));

	private static final ResourceBundle RESOURCE_BUNDLE_EN = ResourceBundle
	.getBundle(BUNDLE_NAME, new java.util.Locale(Locale.US.getLanguage(), Locale.US.getCountry()));
	
	public static final String WEBAPP_ROOT = getString("WEBAPP_ROOT");
	
	private MessageResource() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE_EN.getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	public static String getString(String key, HttpServletRequest req){
		

		String lang = req.getParameter("lang");
		if(Common.isEmpty(lang)){
			lang = req.getParameter("hl");
		}
		if(Common.isEmpty(lang)){
			lang = (String)req.getSession().getAttribute("lang");
			if(Common.isEmpty(lang)){
				lang = getAcceptLanguage(req);
				if(Common.isEmpty(lang)){
					if(Common.isMobile(req)){
						lang = "ja";
					}else{
						lang = "en";
					}
				}
			}
		}
		req.getSession().setAttribute("lang", lang);
		return getString(key, lang);
		
	}
	public static String getString(String key, String lang){
		
		if(lang == null){
			return getString(key);
		}
		try{
			if(lang.equals("en")){
				return RESOURCE_BUNDLE_EN.getString(key);
				
			}else if(lang.equals("ja")){
				return RESOURCE_BUNDLE_JA.getString(key);
			}else{
				return getString(key);
			}
		}catch(MissingResourceException e){
			return null;
		}
		
		
	}
	
	public static int getInt(String key){
		return Integer.parseInt(getString(key));
	}
	
	/**
	 * HTTPヘッダ Accept-Language内の言語指定文字を返す。
	 * @param request
	 * @return
	 */
	private static String getAcceptLanguage(HttpServletRequest request){
		String lang = request.getHeader("Accept-Language");
		String result = null;
		if(lang != null && !lang.equals("")){
			String[] ss = lang.split(",");
			lang = ss[0];
			String[] ls = lang.split("-");
			lang = ls[0];
			result = lang;
		}
		return result;
	}
}
