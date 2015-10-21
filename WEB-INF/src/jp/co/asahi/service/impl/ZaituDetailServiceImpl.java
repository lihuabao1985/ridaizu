package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.ZaituDetailDao;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.ZaituDetail;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "zaituDetailServiceImpl")
@ApplicationScoped
public class ZaituDetailServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private ZaituDetailDao dao;

	public ZaituDetailServiceImpl() {
		this.dao = new ZaituDetailDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (ZaituDetailDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public int getCount(Timestamp zaituDate, String barcode) throws SQLException {
		return dao.selectGoodsCount(zaituDate, barcode);
	}

	public int getCount(Timestamp zaituDate, String barcode, int count) throws SQLException {
		return dao.selectCount(zaituDate, barcode, count);
	}

	public List<ZaituDetail> getZaituDetailList(SearchModel searchModel) throws SQLException {

		return dao.selectZaituDetailList(searchModel);
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

	public boolean deleteByZaituId(int... zaituId) {

		return dao.deleteByZaituId(zaituId) > 0;
	}
}
