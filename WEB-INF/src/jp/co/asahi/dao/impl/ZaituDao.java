package jp.co.asahi.dao.impl;

import static com.google.common.base.Preconditions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.db.DBAccess;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.util.DateUtil;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "zaituDao")
@ApplicationScoped
public class ZaituDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM d_zaitu \n");
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

	public int selectId(Timestamp zaituDate) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id FROM d_zaitu where zaitu_date = ? \n");

		Object[] params = new Object[] { zaituDate };

		ResultSet rs = DBAccess.query(sql.toString(), params);

		int count = 0;

		if (rs == null) {
			return count;
		}

		if (rs.next()) {
			count = rs.getInt("id");
		}

		rs.close();

		return count;
	}

	public List<Zaitu> selectZaituList() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, zaitu_date \n");
		sql.append("FROM d_zaitu \n");

		ResultSet rs = DBAccess.query(sql.toString());

		List<Zaitu> modelList = new ArrayList<Zaitu> ();
		while (rs.next()) {
			Zaitu zaitu = new Zaitu();
			zaitu.setId(rs.getInt("id"));
			zaitu.setZaituDate(rs.getTimestamp("zaitu_date"));

			modelList.add(zaitu);
		}

		rs.close();

		return modelList;
	}

	public List<Zaitu> selectZaituList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, zaitu_date, goods_total_count, goods_total_price, freight_total, \n");
		sql.append("daohuo_flg, daohuo_date, beizhu, rgt_opt, rgt_date, upd_opt, upd_date \n");
		sql.append("FROM d_zaitu \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Zaitu> modelList = new ArrayList<Zaitu> ();
		while (rs.next()) {
			modelList.add((Zaitu)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Zaitu zaitu = new Zaitu();
		zaitu.setId(rs.getInt("id"));
		zaitu.setZaituDate(rs.getTimestamp("zaitu_date"));

		if (rs.getTimestamp("zaitu_date") != null) {
			zaitu.setZaituDateString(DateUtil.dateToString(rs.getTimestamp("zaitu_date"), DateUtil.SHORT_DATE_HYPHEN));
		}

		zaitu.setGoodsTotalCount(rs.getInt("goods_total_count"));
		zaitu.setGoodsTotalPrice(rs.getDouble("goods_total_price"));
		zaitu.setFreightTotal(rs.getDouble("freight_total"));
		zaitu.setDaohuoFlg(rs.getBoolean("daohuo_flg"));
		zaitu.setDaohuoDate(rs.getTimestamp("daohuo_date"));

		if (rs.getTimestamp("daohuo_date") != null) {
			zaitu.setDaohuoDateString(DateUtil.dateToString(rs.getTimestamp("daohuo_date"), DateUtil.SHORT_DATE_HYPHEN));
		}

		zaitu.setBeizhu(rs.getString("beizhu"));

		zaitu.setRgtOpt(rs.getString("rgt_opt"));
		zaitu.setRgtDate(rs.getTimestamp("rgt_date"));
		zaitu.setUpdOpt(rs.getString("upd_opt"));
		zaitu.setUpdDate(rs.getTimestamp("upd_date"));

		return zaitu;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO d_zaitu \n");
		sql.append("(zaitu_date, goods_total_count, goods_total_price, freight_total, \n");
		sql.append("daohuo_flg, daohuo_date, beizhu, rgt_opt, rgt_date, upd_opt, upd_date) \n");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");

		Zaitu zaitu = (Zaitu) model;

		Object[] params = new Object[] {
				zaitu.getZaituDate(), zaitu.getGoodsTotalCount(), zaitu.getGoodsTotalPrice(), zaitu.getFreightTotal(),
				zaitu.isDaohuoFlg(), zaitu.getDaohuoDate(), zaitu.getBeizhu(), zaitu.getRgtOpt(), zaitu.getRgtDate(), zaitu.getUpdOpt(), zaitu.getUpdDate()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE d_zaitu \n");
		sql.append("SET \n");
		sql.append("zaitu_date = ?, \n");
		sql.append("goods_total_count = ?, \n");
		sql.append("goods_total_price = ?, \n");
		sql.append("freight_total = ?, \n");
		sql.append("daohuo_flg = ?, \n");
		sql.append("daohuo_date = ?, \n");
		sql.append("beizhu = ?, \n");
		sql.append("upd_opt = ?, \n");
		sql.append("upd_date = ? \n");
		sql.append("WHERE id = ? \n");

		Zaitu zaitu = (Zaitu) model;

		Object[] params = new Object[] {
				zaitu.getZaituDate(), zaitu.getGoodsTotalCount(), zaitu.getGoodsTotalPrice(), zaitu.getFreightTotal(),
				zaitu.isDaohuoFlg(), zaitu.getDaohuoDate(), zaitu.getBeizhu(), zaitu.getUpdOpt(), zaitu.getUpdDate(), zaitu.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM d_zaitu WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
