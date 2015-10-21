package jp.co.asahi.dao.impl;

import jp.co.asahi.dao.db.Dao;
import jp.co.asahi.model.OrderDetail;

public class OrderDetailDao extends Dao {

	public int insert(OrderDetail orderDetail) {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into d_order_detail \n");
		sql.append("(dingdan_bianhao, biaoti, jiage, goumai_shuliang,waibu_xitong_bianhao, \n");
		sql.append("shangpin_shuxing, taocan_xinxi, beizhu, dingdan_zhuangtai, shangjia_bianma) \n");
		sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Object[] params = new Object[] {
				orderDetail.getDingdanBianhao(), orderDetail.getBiaoti(), orderDetail.getJiage(), orderDetail.getGoumaiShuliang(), orderDetail.getWaibuXitongBianhao(),
				orderDetail.getShangpinShuxing(), orderDetail.getTaocanXinxi(), orderDetail.getBeizhu(), orderDetail.getDingdanZhuangtai(), orderDetail.getShangjiaBianma()
		};

		return this.doUpdate(sql.toString(), params);
	}

}
