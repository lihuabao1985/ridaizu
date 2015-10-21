package jp.co.asahi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

public interface IDaoSelect {

	public int selectCount(int... id) throws SQLException;

	public int selectCount(SearchModel searchModel) throws SQLException;

	public Model select(int... id) throws SQLException;

	public List<Model> selectList(int... id) throws SQLException;

	public List<Model> selectList(SearchModel searchModel) throws SQLException;

	public Model getModel(ResultSet rs) throws SQLException;

}
