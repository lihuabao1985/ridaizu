package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.BrandSeriesDao;
import jp.co.asahi.model.BrandSeries;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "brandSeriesServiceImpl")
@ApplicationScoped
public class BrandSeriesServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private BrandSeriesDao dao;

	public BrandSeriesServiceImpl() {
		this.dao = new BrandSeriesDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (BrandSeriesDao) dao;
	}

	public int getId(Timestamp brandSeriesDate) throws SQLException {
		return dao.selectId(brandSeriesDate);
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<BrandSeries> getBrandSeriesList() throws SQLException {

		return dao.selectBrandSeriesList();
	}

	public List<BrandSeries> getBrandSeriesList(SearchModel searchModel) throws SQLException {

		return dao.selectBrandSeriesList(searchModel);
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
