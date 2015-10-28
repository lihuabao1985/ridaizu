package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.GoodsDailiDao;
import jp.co.asahi.model.GoodsDaili;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "goodsDailiServiceImpl")
@ApplicationScoped
public class GoodsDailiServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private GoodsDailiDao dao;

	public GoodsDailiServiceImpl() {
		this.dao = new GoodsDailiDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (GoodsDailiDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<GoodsDaili> getGoodsDailiList(SearchModel searchModel) throws SQLException {

		return dao.selectGoodsDailiList(searchModel);
	}

	public boolean checkGoodsDaili(int dailiId, int goodsId) throws SQLException {

		return dao.checkGoodsDaili(dailiId, goodsId);
	}


	public boolean insert(Model model) {

		return dao.insert(model) > 0;
	}

	public boolean update(Model model) {

		return dao.update(model) > 0;
	}

	public boolean delete(int... id) {

		return dao.delete(id) > 0;
	}
}
