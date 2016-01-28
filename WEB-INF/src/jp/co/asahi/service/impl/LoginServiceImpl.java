package jp.co.asahi.service.impl;

import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.LoginDao;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "loginServiceImpl")
@ApplicationScoped
public class LoginServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private LoginDao dao;

	public LoginServiceImpl() {
		this.dao = new LoginDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (LoginDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	
}
