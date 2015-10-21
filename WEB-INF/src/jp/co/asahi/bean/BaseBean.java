package jp.co.asahi.bean;

import isr.saml.client.servlets.SamlClientServlet;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.asahi.common.Def;
import jp.co.asahi.model.User;

import org.apache.log4j.Logger;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static Logger systemLogger = Logger.getLogger("system");

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected String getRealPath(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}

	protected void dispatch(String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().dispatch(url);
	}

	protected void redirect(String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}

	/**
	 * ログイン済みの情報を返す。未ログインの場合null
	 * @param session
	 * @return
	 */
	protected String getLoginUser(){
		return SamlClientServlet.getLoginUserName(getRequest());
	}

	/**
	 * ログイン済みの情報を返す。未ログインの場合null
	 * @param session
	 * @return
	 */
	protected User getUser(){

		User user = (User)getSession().getAttribute(Def.SESSION_USER);
//
//		try {
//			if (user == null) {
//				UserServiceImpl service = new UserServiceImpl();
//				user = service.getUser(getLoginUser());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Get user error.");
//		}

		return user;
	}
}