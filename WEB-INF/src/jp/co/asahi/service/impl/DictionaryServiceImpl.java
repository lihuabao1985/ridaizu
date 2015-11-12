package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.DictionaryDao;
import jp.co.asahi.model.Dictionary;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.myenum.DictionaryTypeEnum;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "dictionaryServiceImpl")
@ApplicationScoped
public class DictionaryServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private DictionaryDao dao;

	public DictionaryServiceImpl() {
		this.dao = new DictionaryDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (DictionaryDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Dictionary> getDictionaryList(DictionaryTypeEnum dictionaryTypeEnum) throws SQLException {

		return dao.selectDictionaryList(dictionaryTypeEnum);
	}

	public List<Dictionary> getDictionaryList(SearchModel searchModel) throws SQLException {

		return dao.selectDictionaryList(searchModel);
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
