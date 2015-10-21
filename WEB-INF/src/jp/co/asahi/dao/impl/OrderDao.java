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
import jp.co.asahi.model.Model;
import jp.co.asahi.model.Order;
import jp.co.asahi.model.search.SearchModel;

@ManagedBean(name = "orderDao")
@ApplicationScoped
public class OrderDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM d_order \n");
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

	public List<Order> selectOrderList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * \n");
		sql.append("FROM d_order \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Order> modelList = new ArrayList<Order> ();
		while (rs.next()) {
			modelList.add((Order)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setDingdanBianhao(rs.getString("dingdan_bianhao"));
		order.setMaijiaHuiyuanming(rs.getString("maijia_huiyuanming"));
		order.setMaijiaZhifubaoZhanghao(rs.getString("maijia_zhifubao_zhanghao"));
		order.setMaijiaYingfuHuokuan(rs.getDouble("maijia_yingfu_huokuan"));
		order.setMaijiaYingfuYoufei(rs.getDouble("maijia_yingfu_youfei"));
		order.setMaijiaZhifuJifen(rs.getInt("maijia_zhifu_jifen"));
		order.setZongjine(rs.getDouble("zongjine"));
		order.setFandianJifen(rs.getInt("fandian_jifen"));
		order.setMaijiaShijiZhifuJine(rs.getDouble("maijia_shiji_zhifu_jine"));
		order.setMaijiaShijiZhifuJifen(rs.getInt("maijia_shiji_zhifu_jifen"));
		order.setDingdanZhuangtai(rs.getString("dingdan_zhuangtai"));
		order.setMaijiaLiuyan(rs.getString("maijia_liuyan"));
		order.setShouhuorenXingming(rs.getString("shouhuoren_xingming"));
		order.setShouhuoDizhi(rs.getString("shouhuo_dizhi"));
		order.setYunsongFangshi(rs.getString("yunsong_fangshi"));
		order.setLianxiDianhua(rs.getString("lianxi_dianhua"));
		order.setLianxiShouji(rs.getString("lianxi_shouji"));
		order.setDingdanChuangjianShijian(rs.getTimestamp("dingdan_chuangjian_shijian"));
		order.setDingdanZhifuShijian(rs.getTimestamp("dingdan_zhifu_shijian"));
		order.setShangpinBiaoti(rs.getString("shangpin_biaoti"));
		order.setShangpinZhonglei(rs.getString("shangpin_zhonglei"));
		order.setWuliuDanhao(rs.getString("wuliu_danhao"));
		order.setWuliuGongsi(rs.getString("wuliu_gongsi"));
		order.setDingdanBeizhu(rs.getString("dingdan_beizhu"));
		order.setShangpinZongshuliang(rs.getInt("shangpin_zongshuliang"));
		order.setDianpuId(rs.getString("dianpu_id"));
		order.setDianpuMingcheng(rs.getString("dianpu_mingcheng"));

		return order;
	}

//	public int insert(Model model) {
//
//		checkNotNull(model);
//
//		StringBuffer sql = new StringBuffer();
//		sql.append("INSERT INTO d_order \n");
//		sql.append("(name, summary, detail, homepage, \n");
//		sql.append("photo_url, thumbnails_url, del_flg, rgt_opt, rgt_date, upd_opt, upd_date) \n");
//		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n");
//
//		Order order = (Order) model;
//
//		Object[] params = new Object[] {
//				order.getName(), order.getSummary(), order.getDetail(), order.getHomepage(),
//				order.getPhotoUrl(), order.getThumbnailsUrl(), order.isDelFlg(), order.getRgtOpt(), order.getRgtDate(), order.getUpdOpt(), order.getUpdDate()
//		};
//
//		return DBAccess.update(sql.toString(), params);
//	}

//	@Override
//	public int update(Model model) {
//
//		checkNotNull(model);
//
//		StringBuffer sql = new StringBuffer();
//		sql.append("UPDATE d_order \n");
//		sql.append("SET \n");
//		sql.append("name = ?, \n");
//		sql.append("summary = ?, \n");
//		sql.append("detail = ?, \n");
//		sql.append("homepage = ?, \n");
//		sql.append("photo_url = ?, \n");
//		sql.append("thumbnails_url = ?, \n");
//		sql.append("del_flg = ?, \n");
//		sql.append("upd_opt = ?, \n");
//		sql.append("upd_date = ? \n");
//		sql.append("WHERE id = ? \n");
//
//		Order order = (Order) model;
//
//		Object[] params = new Object[] {
//				order.getName(), order.getSummary(), order.getDetail(), order.getHomepage(),
//				order.getPhotoUrl(), order.getThumbnailsUrl(), order.isDelFlg(),  order.getUpdOpt(), order.getUpdDate(),
//				order.getId()
//		};
//
//		return DBAccess.update(sql.toString(), params);
//	}

//	@Override
//	public int delete(int... id) {
//		String sql = "DELETE FROM d_order WHERE id in(%s);";
//
//		String c = "?";
//		c += Strings.repeat(",?", id.length - 1);
//
//		sql = String.format(sql, c);
//
//		return DBAccess.update(sql.toString(), Ints.asList(id).toArray());
//	}

}
