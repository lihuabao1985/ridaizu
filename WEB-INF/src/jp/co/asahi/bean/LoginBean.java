package jp.co.asahi.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.google.common.base.Strings;

import jp.co.asahi.model.Login;
import jp.co.asahi.model.form.LoginForm;
import jp.co.asahi.model.search.LoginSearch;
import jp.co.asahi.service.impl.LoginServiceImpl;

@javax.faces.bean.ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
    @ManagedProperty("#{loginServiceImpl}")
    private LoginServiceImpl loginService;

    @ManagedProperty("#{loginForm}")
    private LoginForm loginForm;

    @PostConstruct
    public void init() {
    }
    
    public String redirectPage(){
        //遷移先のページを返す
        String uri = null;
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (session.getAttribute("user") != null && !"".equals(session.getAttribute("user"))) {
            uri = "index.xhtml";
        }
        
        return uri;
    }
    
    public void login() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        System.out.println(session.getAttribute("user"));
        session.setAttribute("user", null);
        
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "用户名或密码错误！", "用户名或密码错误！");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }
        
        LoginSearch search = new LoginSearch();
        search.setUsername(username);
        search.setPassword(password);
        try {
            int count = loginService.getCount(search);
            if (count == 1) {
                session.setAttribute("user", username);
                return ;
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "用户名或密码错误！", "用户名或密码错误！");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void viewLogin(Login login) throws SQLException {


        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 320);
        options.put("contentHeight", 200);

        RequestContext.getCurrentInstance().openDialog("weibo-zhuanfa", options, null);
    }

	public void updateLoginResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("微博转发信息%s成功。", optStr);
		} else {
			resultStr = String.format("微博转发信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public LoginServiceImpl getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

}