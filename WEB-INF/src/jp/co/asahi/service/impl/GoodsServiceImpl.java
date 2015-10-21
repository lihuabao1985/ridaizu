package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.GoodsDao;
import jp.co.asahi.model.Goods;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "goodsServiceImpl")
@ApplicationScoped
public class GoodsServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private GoodsDao dao;

	public GoodsServiceImpl() {
		this.dao = new GoodsDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (GoodsDao) dao;
	}

	public int getId(Timestamp goodsDate) throws SQLException {
		return dao.selectId(goodsDate);
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Goods> getGoodsList() throws SQLException {

		return dao.selectGoodsList();
	}

	public List<Goods> getGoodsList(SearchModel searchModel) throws SQLException {

		return dao.selectGoodsList(searchModel);
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
