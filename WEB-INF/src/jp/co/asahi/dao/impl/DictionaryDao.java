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
import jp.co.asahi.model.Dictionary;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.myenum.DictionaryTypeEnum;
import jp.co.asahi.model.search.SearchModel;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "dictionaryDao")
@ApplicationScoped
public class DictionaryDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM m_dictionary \n");
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

	public List<Dictionary> selectDictionaryList(DictionaryTypeEnum dictionaryTypeEnum) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM m_dictionary where type = ? \n");

		ResultSet rs = DBAccess.query(sql.toString(), dictionaryTypeEnum.toString());

		List<Dictionary> modelList = new ArrayList<Dictionary> ();
		while (rs.next()) {
			modelList.add((Dictionary)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	public List<Dictionary> selectDictionaryList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * \n");
		sql.append("FROM m_dictionary \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Dictionary> modelList = new ArrayList<Dictionary> ();
		while (rs.next()) {
			modelList.add((Dictionary)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Dictionary dictionary = new Dictionary();
		dictionary.setId(rs.getInt("id"));
		dictionary.setType(rs.getString("type"));
		dictionary.setValue(rs.getString("value"));
		dictionary.setName(rs.getString("name"));
		dictionary.setOrderNum(rs.getInt("order_num"));
		dictionary.setComment(rs.getString("comment"));
		dictionary.setDelFlg(rs.getBoolean("del_flg"));
		dictionary.setRgtOpt(rs.getString("rgt_opt"));
		dictionary.setRgtDate(rs.getTimestamp("rgt_date"));
		dictionary.setUpdOpt(rs.getString("upd_opt"));
		dictionary.setUpdDate(rs.getTimestamp("upd_date"));

		return dictionary;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO m_dictionary \n");
		sql.append("(name, taobao_name, dictionary_code, barcode, xiaoshou_shuxing, dictionary_type_id, \n");
		sql.append("capacity, weight, chengbenjia, wholesale_price, price, sales_price, \n");
		sql.append("summary, detail, ingredient, effect, nianlingceng_id, \n");
		sql.append("instructions, precautions, dictionary_url, photo_url, thumbnails_url, \n");
		sql.append("del_flg, rgt_opt, rgt_date, upd_opt, upd_date) \n");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");

		Dictionary dictionary = (Dictionary) model;

		Object[] params = new Object[] {
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_dictionary \n");
		sql.append("SET \n");
		sql.append("name = ?, \n");
		sql.append("taobao_name = ?, \n");
		sql.append("dictionary_code = ?, \n");
		sql.append("barcode = ?, \n");
		sql.append("xiaoshou_shuxing = ?, \n");
		sql.append("dictionary_type_id = ?, \n");
		sql.append("capacity = ?, \n");
		sql.append("weight = ?, \n");
		sql.append("chengbenjia = ?, \n");
		sql.append("wholesale_price = ?, \n");
		sql.append("price = ?, \n");
		sql.append("sales_price = ?, \n");
		sql.append("summary = ?, \n");
		sql.append("detail = ?, \n");
		sql.append("ingredient = ?, \n");
		sql.append("effect = ?, \n");
		sql.append("nianlingceng_id = ?, \n");
		sql.append("instructions = ?, \n");
		sql.append("precautions = ?, \n");
		sql.append("dictionary_url = ?, \n");
		sql.append("photo_url = ?, \n");
		sql.append("thumbnails_url = ?, \n");
		sql.append("del_flg = ?, \n");
		sql.append("upd_opt = ?, \n");
		sql.append("upd_date = ? \n");
		sql.append("WHERE id = ? \n");

		Dictionary dictionary = (Dictionary) model;

		Object[] params = new Object[] {
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM m_dictionary WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
