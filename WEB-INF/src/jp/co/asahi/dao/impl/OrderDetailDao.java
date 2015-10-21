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
import jp.co.asahi.model.OrderDetail;
import jp.co.asahi.model.search.SearchModel;

@ManagedBean(name = "orderDetailDao")
@ApplicationScoped
public class OrderDetailDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM d_order_detail \n");
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

	public List<OrderDetail> selectOrderDetailList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * \n");
		sql.append("FROM d_order_detail \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<OrderDetail> modelList = new ArrayList<OrderDetail> ();
		while (rs.next()) {
			modelList.add((OrderDetail)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(rs.getInt("id"));
		orderDetail.setDingdanBianhao(rs.getString("dingdan_bianhao"));
		orderDetail.setBiaoti(rs.getString("biaoti"));
		orderDetail.setJiage(rs.getDouble("jiage"));
		orderDetail.setGoumaiShuliang(rs.getInt("goumai_shuliang"));
		orderDetail.setWaibuXitongBianhao(rs.getString("waibu_xitong_bianhao"));
		orderDetail.setShangpinShuxing(rs.getString("shangpin_shuxing"));
		orderDetail.setTaocanXinxi(rs.getString("taocan_xinxi"));
		orderDetail.setBeizhu(rs.getString("beizhu"));
		orderDetail.setDingdanZhuangtai(rs.getString("dingdan_zhuangtai"));
		orderDetail.setShangjiaBianma(rs.getString("shangjia_bianma"));

		return orderDetail;
	}

}
