package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.DailiDao;
import jp.co.asahi.model.Daili;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "dailiServiceImpl")
@ApplicationScoped
public class DailiServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private DailiDao dao;

	public DailiServiceImpl() {
		this.dao = new DailiDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (DailiDao) dao;
	}

	public int getId(Timestamp dailiDate) throws SQLException {
		return dao.selectId(dailiDate);
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Daili> getDailiList() throws SQLException {

		return dao.selectDailiList();
	}

	public List<Daili> getDailiList(SearchModel searchModel) throws SQLException {

		return dao.selectDailiList(searchModel);
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
