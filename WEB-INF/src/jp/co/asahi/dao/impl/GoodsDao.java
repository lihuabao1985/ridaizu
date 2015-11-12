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
import jp.co.asahi.model.Goods;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

@ManagedBean(name = "goodsDao")
@ApplicationScoped
public class GoodsDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM m_goods \n");
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

	public int selectId(Timestamp goodsDate) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id FROM m_goods where goods_date = ? \n");

		Object[] params = new Object[] { goodsDate };

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

	public List<Goods> selectGoodsList() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM m_goods \n");

		ResultSet rs = DBAccess.query(sql.toString());

		List<Goods> modelList = new ArrayList<Goods> ();
		while (rs.next()) {
			modelList.add((Goods)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	public List<Goods> selectGoodsList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * \n");
		sql.append("FROM m_goods \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Goods> modelList = new ArrayList<Goods> ();
		while (rs.next()) {
			modelList.add((Goods)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Goods goods = new Goods();
		goods.setId(rs.getInt("id"));
		goods.setName(rs.getString("name"));
		goods.setTaobaoName(rs.getString("taobao_name"));
		goods.setGoodsCode(rs.getString("goods_code"));
		goods.setBarcode(rs.getString("barcode"));
		goods.setXiaoshouShuxing(rs.getString("xiaoshou_shuxing"));
		goods.setGoodsTypeId(rs.getInt("goods_type_id"));
		goods.setCapacity(rs.getString("capacity"));
		goods.setWeight(rs.getInt("weight"));
		goods.setChengbenjia(rs.getDouble("chengbenjia"));
		goods.setWholesalePrice(rs.getDouble("wholesale_price"));
		goods.setPrice(rs.getDouble("price"));
		goods.setSalesPrice(rs.getDouble("sales_price"));
		goods.setSummary(rs.getString("summary"));
		goods.setDetail(rs.getString("detail"));
		goods.setIngredient(rs.getString("ingredient"));
		goods.setEffect(rs.getString("effect"));
		goods.setNianlingcengId(rs.getInt("nianlingceng_id"));
		goods.setInstructions(rs.getString("instructions"));
		goods.setPrecautions(rs.getString("precautions"));
		goods.setGoodsUrl(rs.getString("goods_url"));
		goods.setPhotoUrl(rs.getString("photo_url"));
		goods.setThumbnailsUrl(rs.getString("thumbnails_url"));
		goods.setDelFlg(rs.getBoolean("del_flg"));
		goods.setRgtOpt(rs.getString("rgt_opt"));
		goods.setRgtDate(rs.getTimestamp("rgt_date"));
		goods.setUpdOpt(rs.getString("upd_opt"));
		goods.setUpdDate(rs.getTimestamp("upd_date"));

		return goods;
	}

	public int insert(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO m_goods \n");
		sql.append("(name, taobao_name, goods_code, barcode, xiaoshou_shuxing, goods_type_id, \n");
		sql.append("capacity, weight, chengbenjia, wholesale_price, price, sales_price, \n");
		sql.append("summary, detail, ingredient, effect, nianlingceng_id, \n");
		sql.append("instructions, precautions, goods_url, photo_url, thumbnails_url, \n");
		sql.append("del_flg, rgt_opt, rgt_date, upd_opt, upd_date) \n");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");

		Goods goods = (Goods) model;

		Object[] params = new Object[] {
				goods.getName(), goods.getTaobaoName(), goods.getGoodsCode(), goods.getBarcode(), goods.getXiaoshouShuxing(), goods.getGoodsTypeId(),
				goods.getCapacity(), goods.getWeight(), goods.getChengbenjia(), goods.getWholesalePrice(), goods.getPrice(), goods.getSalesPrice(),
				goods.getSummary(), goods.getDetail(), goods.getIngredient(), goods.getEffect(), goods.getNianlingcengId(),
				goods.getInstructions(), goods.getPrecautions(), goods.getGoodsUrl(), goods.getPhotoUrl(), goods.getThumbnailsUrl(),
				goods.isDelFlg(), goods.getRgtOpt(), goods.getRgtDate(), goods.getUpdOpt(), goods.getUpdDate()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_goods \n");
		sql.append("SET \n");
		sql.append("name = ?, \n");
		sql.append("taobao_name = ?, \n");
		sql.append("goods_code = ?, \n");
		sql.append("barcode = ?, \n");
		sql.append("xiaoshou_shuxing = ?, \n");
		sql.append("goods_type_id = ?, \n");
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
		sql.append("goods_url = ?, \n");
		sql.append("photo_url = ?, \n");
		sql.append("thumbnails_url = ?, \n");
		sql.append("del_flg = ?, \n");
		sql.append("upd_opt = ?, \n");
		sql.append("upd_date = ? \n");
		sql.append("WHERE id = ? \n");

		Goods goods = (Goods) model;

		Object[] params = new Object[] {
				goods.getName(), goods.getTaobaoName(), goods.getGoodsCode(), goods.getBarcode(), goods.getXiaoshouShuxing(), goods.getGoodsTypeId(),
				goods.getCapacity(), goods.getWeight(), goods.getChengbenjia(), goods.getWholesalePrice(), goods.getPrice(), goods.getSalesPrice(),
				goods.getSummary(), goods.getDetail(), goods.getIngredient(), goods.getEffect(), goods.getNianlingcengId(),
				goods.getInstructions(), goods.getPrecautions(), goods.getGoodsUrl(), goods.getPhotoUrl(), goods.getThumbnailsUrl(),
				goods.isDelFlg(), goods.getUpdOpt(), goods.getUpdDate(),
				goods.getId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int delete(int... id) {
		String sql = "DELETE FROM m_goods WHERE id in(%s);";

		String c = "?";
		c += Strings.repeat(",?", id.length - 1);

		sql = String.format(sql, c);

		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
	}

}
