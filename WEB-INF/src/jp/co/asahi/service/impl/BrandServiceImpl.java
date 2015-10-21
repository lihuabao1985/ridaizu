package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.BrandDao;
import jp.co.asahi.model.Brand;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "brandServiceImpl")
@ApplicationScoped
public class BrandServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private BrandDao dao;

	public BrandServiceImpl() {
		this.dao = new BrandDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (BrandDao) dao;
	}

	public int getId(Timestamp brandDate) throws SQLException {
		return dao.selectId(brandDate);
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Brand> getBrandList() throws SQLException {

		return dao.selectBrandList();
	}

	public List<Brand> getBrandList(SearchModel searchModel) throws SQLException {

		return dao.selectBrandList(searchModel);
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
