package jp.co.asahi.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.co.asahi.cache.CacheDataBean;

public class AsahiServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("加载业务字典");
		try {

			new CacheDataBean();

		} catch (Exception e) {
			System.out.println("加载业务字典失败！");
			e.printStackTrace();
		}
	}
}
