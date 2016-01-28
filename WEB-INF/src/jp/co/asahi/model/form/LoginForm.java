package jp.co.asahi.model.form;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm extends Model {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
