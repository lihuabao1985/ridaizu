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
import jp.co.asahi.model.GoodsDaili;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "goodsDailiDao")
@ApplicationScoped
public class GoodsDailiDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM v_goods_daili \n");
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

	public boolean checkGoodsDaili(int dailiId, int goodsId) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM v_goods_daili where daili_id = ? AND goods_id = ? \n");

		Object[] params = new Object[] { dailiId, goodsId };

		ResultSet rs = DBAccess.query(sql.toString(), params);

		if (rs == null) {
			return false;
		}

		boolean result = true;

		if (rs.next()) {
			result = rs.getInt("count") > 0;
		}

		rs.close();

		return result;
	}

	public List<GoodsDaili> selectGoodsDailiList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * \n");
		sql.append("FROM v_goods_daili \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<GoodsDaili> modelList = new ArrayList<GoodsDaili> ();
		while (rs.next()) {
			modelList.add((GoodsDaili)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		GoodsDaili goodsDaili = new GoodsDaili();
		goodsDaili.setId(rs.getInt("id"));
		goodsDaili.setDailiId(rs.getInt("daili_id"));
		goodsDaili.setDailiName(rs.getString("daili_name"));
		goodsDaili.setGoodsId(rs.getInt("goods_id"));
		goodsDaili.setGoodsName(rs.getString("goods_name"));
		goodsDaili.setTaobaoName(rs.getString("taobao_name"));
		goodsDaili.setBarcode(rs.getString("barcode"));
		goodsDaili.setMinCount(rs.getInt("min_count"));
		goodsDaili.setPrice(rs.getDouble("price"));

		return goodsDaili;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO m_goods_daili \n");
		sql.append("(daili_id, goods_id, min_count, price) \n");
		sql.append("VALUES(?, ?, ?, ?) \n");

		GoodsDaili goodsDaili = (GoodsDaili) model;

		Object[] params = new Object[] {
				goodsDaili.getDailiId(), goodsDaili.getGoodsId(), goodsDaili.getMinCount(), goodsDaili.getPrice()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_goods_daili \n");
		sql.append("SET \n");
		sql.append("daili_id = ?, \n");
		sql.append("goods_id = ?, \n");
		sql.append("min_count = ?, \n");
		sql.append("price = ? \n");
		sql.append("WHERE id = ? \n");

		GoodsDaili goodsDaili = (GoodsDaili) model;

		Object[] params = new Object[] {
				goodsDaili.getDailiId(), goodsDaili.getGoodsId(), goodsDaili.getMinCount(), goodsDaili.getPrice(),
				goodsDaili.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM m_goods_daili WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
