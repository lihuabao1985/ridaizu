package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.ZaituDao;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "zaituServiceImpl")
@ApplicationScoped
public class ZaituServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private ZaituDao dao;

	public ZaituServiceImpl() {
		this.dao = new ZaituDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (ZaituDao) dao;
	}

	public int getId(Timestamp zaituDate) throws SQLException {
		return dao.selectId(zaituDate);
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Zaitu> getZaituList() throws SQLException {

		return dao.selectZaituList();
	}

	public List<Zaitu> getZaituList(SearchModel searchModel) throws SQLException {

		return dao.selectZaituList(searchModel);
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
