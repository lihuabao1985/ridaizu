package jp.co.asahi.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

public abstract class Service implements Serializable {

	private static final long serialVersionUID = 1L;

	public void setDao(DaoAdapter dao) {

	}

	public int getCount() throws SQLException {
		return 0;
	}

	public int getCount(SearchModel searchModel) throws SQLException {
		return 0;
	}

	public List<Model> getModelList(int... id) throws SQLException {
		return null;
	}

	public List<Model> getModelList(SearchModel searchModel) throws SQLException {
		return null;
	}

	public Model getModel(int... id) throws SQLException {
		return null;
	}

	public boolean insertModel(Model model) {
		return false;
	}

	public boolean updateModel(Model model) {
		return false;
	}

	public boolean  deleteModel(int... id) {
		return false;
	}

	public boolean deleteModelList(int... id) {
		return false;
	}

	public boolean deleteAllModel() {
		return false;
	}

}
