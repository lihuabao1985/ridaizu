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
import jp.co.asahi.model.Brand;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "brandDao")
@ApplicationScoped
public class BrandDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM m_brand \n");
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

	public int selectId(Timestamp brandDate) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id FROM m_brand where brand_date = ? \n");

		Object[] params = new Object[] { brandDate };

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

	public List<Brand> selectBrandList() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, name \n");
		sql.append("FROM m_brand \n");

		ResultSet rs = DBAccess.query(sql.toString());

		List<Brand> modelList = new ArrayList<Brand> ();
		while (rs.next()) {
			Brand brand = new Brand();
			brand.setId(rs.getInt("id"));
			brand.setName(rs.getString("name"));

			modelList.add(brand);
		}

		rs.close();

		return modelList;
	}

	public List<Brand> selectBrandList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("id, name, summary, detail, homepage, \n");
		sql.append("photo_url, thumbnails_url, del_flg, rgt_opt, rgt_date, upd_opt, upd_date \n");
		sql.append("FROM m_brand \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Brand> modelList = new ArrayList<Brand> ();
		while (rs.next()) {
			modelList.add((Brand)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Brand brand = new Brand();
		brand.setId(rs.getInt("id"));
		brand.setName(rs.getString("name"));
		brand.setSummary(rs.getString("summary"));
		brand.setDetail(rs.getString("detail"));
		brand.setHomepage(rs.getString("homepage"));
		brand.setPhotoUrl(rs.getString("photo_url"));
		brand.setThumbnailsUrl(rs.getString("thumbnails_url"));
		brand.setDelFlg(rs.getBoolean("del_flg"));
		brand.setRgtOpt(rs.getString("rgt_opt"));
		brand.setRgtDate(rs.getTimestamp("rgt_date"));
		brand.setUpdOpt(rs.getString("upd_opt"));
		brand.setUpdDate(rs.getTimestamp("upd_date"));

		return brand;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO m_brand \n");
		sql.append("(name, summary, detail, homepage, \n");
		sql.append("photo_url, thumbnails_url, del_flg, rgt_opt, rgt_date, upd_opt, upd_date) \n");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");

		Brand brand = (Brand) model;

		Object[] params = new Object[] {
				brand.getName(), brand.getSummary(), brand.getDetail(), brand.getHomepage(),
				brand.getPhotoUrl(), brand.getThumbnailsUrl(), brand.isDelFlg(), brand.getRgtOpt(), brand.getRgtDate(), brand.getUpdOpt(), brand.getUpdDate()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_brand \n");
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

		Brand brand = (Brand) model;

		Object[] params = new Object[] {
				brand.getName(), brand.getSummary(), brand.getDetail(), brand.getHomepage(),
				brand.getPhotoUrl(), brand.getThumbnailsUrl(), brand.isDelFlg(),  brand.getUpdOpt(), brand.getUpdDate(),
				brand.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM m_brand WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
