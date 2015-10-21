package jp.co.asahi.dao.db;

import static com.google.common.base.Preconditions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.asahi.model.Model;
import jp.co.asahi.model.ZaituDetail;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.util.DateUtil;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

public class ZaituDetailDao extends Dao {

	public int selectCount(Timestamp zaituDate, String barcode, int count) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM v_zaitu_detail where zaitu_date=? AND goods_barcode=? AND count=? \n");

		Object[] params = new Object[] { zaituDate, barcode, count };

		ResultSet rs = this.doSelect(sql.toString(), params);

		int i = 0;

		if (rs == null) {
			return i;
		}

		if (rs.next()) {
			i = rs.getInt("count");
		}

		rs.close();

		return i;
	}

	public int selectCount(Timestamp zaituDate, String barcode) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM v_zaitu_detail where zaitu_date=? AND goods_barcode=? \n");

		Object[] params = new Object[] { zaituDate, barcode };

		ResultSet rs = this.doSelect(sql.toString(), params);

		int i = 0;

		if (rs == null) {
			return i;
		}

		if (rs.next()) {
			i = rs.getInt("count");
		}

		rs.close();

		return i;
	}

	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM d_zaitu_detail \n");
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

	public List<ZaituDetail> selectZaituDetailList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, zaitu_id, zaitu_date, goods_barcode, count, price, beizhu \n");
		sql.append("FROM v_zaitu_detail \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<ZaituDetail> modelList = new ArrayList<ZaituDetail> ();
		while (rs.next()) {
			modelList.add((ZaituDetail)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		ZaituDetail zaituDetail = new ZaituDetail();
		zaituDetail.setId(rs.getInt("id"));
		zaituDetail.setZaituId(rs.getInt("zaitu_id"));
		zaituDetail.setZaituDate(rs.getTimestamp("zaitu_date"));

		if (rs.getTimestamp("zaitu_date") != null) {
			zaituDetail.setZaituDateString(DateUtil.dateToString(rs.getTimestamp("zaitu_date"), DateUtil.SHORT_DATE_HYPHEN));
		}

		zaituDetail.setGoodsBarcode(rs.getString("goods_barcode"));
		zaituDetail.setCount(rs.getInt("count"));
		zaituDetail.setPrice(rs.getDouble("price"));
		zaituDetail.setBeizhu(rs.getString("beizhu"));

		return zaituDetail;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO d_zaitu_detail \n");
		sql.append("(zaitu_id, goods_barcode, count, price, beizhu) \n");
		sql.append("VALUES(?, ?, ?, ?, ?) \n");

		ZaituDetail zaituDetail = (ZaituDetail) model;

		Object[] params = new Object[] {
				zaituDetail.getZaituId(), zaituDetail.getGoodsBarcode(), zaituDetail.getCount(), zaituDetail.getPrice(), zaituDetail.getBeizhu()
		};

		return DBAccess.update(sql.toString(), params);
	}

	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE d_zaitu_detail \n");
		sql.append("SET \n");
		sql.append("zaitu_id = ?, \n");
		sql.append("goods_barcode = ?, \n");
		sql.append("count = ?, \n");
		sql.append("price = ?, \n");
		sql.append("beizhu = ? \n");
		sql.append("WHERE id = ? \n");

		ZaituDetail zaituDetail = (ZaituDetail) model;

		Object[] params = new Object[] {
				zaituDetail.getZaituId(), zaituDetail.getGoodsBarcode(), zaituDetail.getCount(), zaituDetail.getPrice(), zaituDetail.getBeizhu(),
				zaituDetail.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	public int delete(int... id) {
		String sql = "DELETE FROM d_zaitu_detail WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

	public int deleteByZaituId(int... zaituId) {
		String sql = "DELETE FROM d_zaitu_detail WHERE zaitu_id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", zaituId.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(zaituId).toArray());
	}
}
