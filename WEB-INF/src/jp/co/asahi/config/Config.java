package jp.co.asahi.config;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Config {


	private static final String BUNDLE_NAME = "config"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Config() {
	}

	public static String getString(String key) {
		return getString(key,null);
	}
	public static String getString(String key,String def) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return def;
		}
	}

	public static String getStringNotBlank(String key,String def) {
		try {
			if (RESOURCE_BUNDLE.getString(key) == null || "".equals(RESOURCE_BUNDLE.getString(key).trim()))
				return def;

			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return def;
		}
	}

	public static int getInt(String key){
		return getInt(key,0);
	}

	public static int getInt(String key,int def){
		try{
			return Integer.parseInt(getString(key));
		}catch(Exception e){
			return def;
		}
	}

	public static double getDouble(String key){
		return getDouble(key, 0.00d);
	}

	public static double getDouble(String key,double def){
		try{
			return Double.parseDouble(getString(key));
		}catch(Exception e){
			return def;
		}
	}

	public static boolean getBoolean(String key){
		return getBoolean(key,false);
	}

	public static boolean getBoolean(String key,boolean def){
		try{
			return Boolean.parseBoolean(getString(key));
		}catch(Exception e){
			return def;
		}
	}

	public static String getString(String boudleName, String key,String def) {
		try {
			return ResourceBundle.getBundle(boudleName).getString(key);
		} catch (MissingResourceException e) {
			return def;
		}
	}
}
