package jp.co.asahi.model.search;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "loginSearch")
@SessionScoped
public class LoginSearch extends SearchModel {

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

    @Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if(!Strings.isNullOrEmpty(username)) {
			sb.append("AND ");
			sb.append("username = ? \n");

			conditionList.add(username);
		}

		if(!Strings.isNullOrEmpty(password)) {
			sb.append("AND ");
			sb.append("password = ? \n");

			conditionList.add(password);
		}

		return sb.toString();
	}

	@Override
	public String getSelectCountSql() {
		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

        if(!Strings.isNullOrEmpty(username)) {
            sb.append("AND ");
            sb.append("username = ? \n");

            conditionList.add(username);
        }

        if(!Strings.isNullOrEmpty(password)) {
            sb.append("AND ");
            sb.append("password = ? \n");

            conditionList.add(password);
        }

		return sb.toString();
	}


}
