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
import jp.co.asahi.model.BrandSeries;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "brandSeriesDao")
@ApplicationScoped
public class BrandSeriesDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM m_brand_series \n");
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

	public int selectId(Timestamp brandSeriesDate) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id FROM m_brand_series where brandSeries_date = ? \n");

		Object[] params = new Object[] { brandSeriesDate };

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

	public List<BrandSeries> selectBrandSeriesList() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, name \n");
		sql.append("FROM m_brand_series \n");

		ResultSet rs = DBAccess.query(sql.toString());

		List<BrandSeries> modelList = new ArrayList<BrandSeries> ();
		while (rs.next()) {
			BrandSeries brandSeries = new BrandSeries();
			brandSeries.setId(rs.getInt("id"));
			brandSeries.setName(rs.getString("name"));

			modelList.add(brandSeries);
		}

		rs.close();

		return modelList;
	}

	public List<BrandSeries> selectBrandSeriesList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, name, summary, detail, homepage, \n");
		sql.append("photo_url, thumbnails_url, del_flg, rgt_opt, rgt_date, upd_opt, upd_date \n");
		sql.append("FROM m_brand_series \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<BrandSeries> modelList = new ArrayList<BrandSeries> ();
		while (rs.next()) {
			modelList.add((BrandSeries)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		BrandSeries brandSeries = new BrandSeries();
		brandSeries.setId(rs.getInt("id"));
		brandSeries.setName(rs.getString("name"));
		brandSeries.setSummary(rs.getString("summary"));
		brandSeries.setDetail(rs.getString("detail"));
		brandSeries.setHomepage(rs.getString("homepage"));
		brandSeries.setPhotoUrl(rs.getString("photo_url"));
		brandSeries.setThumbnailsUrl(rs.getString("thumbnails_url"));
		brandSeries.setDelFlg(rs.getBoolean("del_flg"));
		brandSeries.setRgtOpt(rs.getString("rgt_opt"));
		brandSeries.setRgtDate(rs.getTimestamp("rgt_date"));
		brandSeries.setUpdOpt(rs.getString("upd_opt"));
		brandSeries.setUpdDate(rs.getTimestamp("upd_date"));

		return brandSeries;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO m_brand_series \n");
		sql.append("(name, summary, detail, homepage, \n");
		sql.append("photo_url, thumbnails_url, del_flg, rgt_opt, rgt_date, upd_opt, upd_date) \n");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");

		BrandSeries brandSeries = (BrandSeries) model;

		Object[] params = new Object[] {
				brandSeries.getName(), brandSeries.getSummary(), brandSeries.getDetail(), brandSeries.getHomepage(),
				brandSeries.getPhotoUrl(), brandSeries.getThumbnailsUrl(), brandSeries.isDelFlg(), brandSeries.getRgtOpt(), brandSeries.getRgtDate(), brandSeries.getUpdOpt(), brandSeries.getUpdDate()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_brand_series \n");
		sql.append("SET \n");
		sql.append("name = ?, \n");
		sql.append("summary = ?, \n");
		sql.append("detail = ?, \n");
		sql.append("homepage = ?, \n");
		sql.append("photo_url = ?, \n");
		sql.append("thumbnails_url = ?, \n");
		sql.append("del_flg = ?, \n");
		sql.append("upd_opt = ?, \n");
		sql.append("upd_date = ? \n");
		sql.append("WHERE id = ? \n");

		BrandSeries brandSeries = (BrandSeries) model;

		Object[] params = new Object[] {
				brandSeries.getName(), brandSeries.getSummary(), brandSeries.getDetail(), brandSeries.getHomepage(),
				brandSeries.getPhotoUrl(), brandSeries.getThumbnailsUrl(), brandSeries.isDelFlg(),  brandSeries.getUpdOpt(), brandSeries.getUpdDate(),
				brandSeries.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM m_brand_series WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
