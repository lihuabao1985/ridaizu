package jp.co.asahi.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.asahi.common.Common;
import jp.co.asahi.config.Config;
import jp.co.asahi.dao.db.DBManager;
import jp.co.asahi.model.AllowRemoteIpList;

import org.apache.log4j.Logger;

public class GlobalFilter implements Filter {

	protected static Logger systemLogger = Logger.getLogger("system");

	private static final AllowRemoteIpList allowRemoteIpList = new AllowRemoteIpList(
			Arrays.asList(Config.getString("ALLOW_ACCESS_IP_LIST").split(",")));

	private static final String LOG_FORMAT = "Servlet path: %s, PathInfo: %s, Remote ip: %s";

	private static final String NOT_ALLOW_REDIRECT_URL = Config.getString("NOT_ALLOW_REDIRECT_URL");

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        
		String requestUrl = request.getRequestURI();
		//Staticファイルをスキップする
		if(requestUrl.indexOf("/global/")>-1 || requestUrl.indexOf("/images/")>-1 || requestUrl.indexOf("/js/")>-1
				|| requestUrl.indexOf("/css/")>-1 || requestUrl.indexOf("/javax.faces.resource/")>-1 || requestUrl.indexOf("login.xhtml")>-1 ){
			chain.doFilter(req, res);
			return;
		}

		String remoteIp = Common.getRemoteAddress(request);
		systemLogger.debug(String.format(LOG_FORMAT, request.getServletPath(), request.getPathInfo(), remoteIp));

		try{

	        Object loginInfo = session.getAttribute("user");
	        if (loginInfo == null || "".equals(loginInfo)) {
	            response.sendRedirect(Config.getString("NOT_ALLOW_REDIRECT_URL"));
	            return ;
	        }
	        
//			if (!allowRemoteIpList.isAllow(remoteIp)) {
//				response.sendRedirect(NOT_ALLOW_REDIRECT_URL);
//				return;
//			} else {
				chain.doFilter(req, res);
//			}
		}finally{
			DBManager.closeConnection();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
