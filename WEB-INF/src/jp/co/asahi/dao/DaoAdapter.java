package jp.co.asahi.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

public abstract class DaoAdapter implements IDaoDelete, IDaoInsert, IDaoSelect,
		IDaoUpdate, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int update(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(int... id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Model select(int... id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Model> selectList(int... id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Model> selectList(SearchModel searchModel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Model model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int... id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteList(int... id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

}
