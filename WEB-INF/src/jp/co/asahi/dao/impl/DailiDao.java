package jp.co.asahi.dao.impl;

import static com.google.common.base.Preconditions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.db.DBAccess;
import jp.co.asahi.model.Daili;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "dailiDao")
@ApplicationScoped
public class DailiDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM m_daili \n");
		sql.append(searchModel.getSelectCountSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		int count = 0;

		if (rs == null) {
			return count;
		}

		if (rs.next()) {
			count = rs.getInt("count");
		}

		rs.close();

		return count;
	}

	public List<Daili> selectDailiList() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, name \n");
		sql.append("FROM m_daili \n");

		ResultSet rs = DBAccess.query(sql.toString());

		List<Daili> modelList = new ArrayList<Daili> ();
		while (rs.next()) {
			Daili daili = new Daili();
			daili.setId(rs.getInt("id"));
			daili.setName(rs.getString("name"));

			modelList.add(daili);
		}

		rs.close();

		return modelList;
	}

	public List<Daili> selectDailiList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * \n");
		sql.append("FROM m_daili \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Daili> modelList = new ArrayList<Daili> ();
		while (rs.next()) {
			modelList.add((Daili)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Daili daili = new Daili();
		daili.setId(rs.getInt("id"));
		daili.setName(rs.getString("name"));
		daili.setWangwang(rs.getString("wangwang"));
		daili.setWeixin(rs.getString("weixin"));
		daili.setQq(rs.getString("qq"));
		daili.setTel(rs.getString("tel"));

		return daili;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO m_daili \n");
		sql.append("(name, wangwang, weixin, qq, tel) \n");
		sql.append("VALUES(?, ?, ?, ?, ?) \n");

		Daili daili = (Daili) model;

		Object[] params = new Object[] {
				daili.getName(), daili.getWangwang(), daili.getWeixin(), daili.getQq(), daili.getTel()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_daili \n");
		sql.append("SET \n");
		sql.append("name = ?, \n");
		sql.append("wangwang = ?, \n");
		sql.append("weixin = ?, \n");
		sql.append("qq = ?, \n");
		sql.append("tel = ? \n");
		sql.append("WHERE id = ? \n");

		Daili daili = (Daili) model;

		Object[] params = new Object[] {
				daili.getName(), daili.getWangwang(), daili.getWeixin(), daili.getQq(), daili.getTel(),
				daili.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM m_daili WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
