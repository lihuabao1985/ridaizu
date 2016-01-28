package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.WeiboZhuanfaDao;
import jp.co.asahi.model.WeiboZhuanfa;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "weiboZhuanfaServiceImpl")
@ApplicationScoped
public class WeiboZhuanfaServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private WeiboZhuanfaDao dao;

	public WeiboZhuanfaServiceImpl() {
		this.dao = new WeiboZhuanfaDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (WeiboZhuanfaDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<WeiboZhuanfa> getWeiboZhuanfaList(SearchModel searchModel) throws SQLException {

		return dao.selectWeiboZhuanfaList(searchModel);
	}
	
}
