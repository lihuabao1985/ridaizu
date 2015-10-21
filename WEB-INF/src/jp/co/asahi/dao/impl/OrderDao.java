package jp.co.asahi.dao.impl;

import jp.co.asahi.dao.db.Dao;
import jp.co.asahi.model.Order;

public class OrderDao extends Dao {

	public int insert(Order order) {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into d_order \n");
		sql.append("(dingdan_bianhao, maijia_huiyuanming, maijia_zhifubao_zhanghao, maijia_yingfu_huokuan, maijia_yingfu_youfei, \n");
		sql.append("maijia_zhifu_jifen, zongjine, fandian_jifen, maijia_shiji_zhifu_jine, maijia_shiji_zhifu_jifen, \n");
		sql.append("dingdan_zhuangtai, maijia_liuyan, shouhuoren_xingming, shouhuo_dizhi, yunsong_fangshi, \n");
		sql.append("lianxi_dianhua, lianxi_shouji, dingdan_chuangjian_shijian, dingdan_zhifu_shijian, shangpin_biaoti, \n");
		sql.append("shangpin_zhonglei, wuliu_danhao, wuliu_gongsi, dingdan_beizhu, shangpin_zongshuliang, \n");
		sql.append("dianpu_id, dianpu_mingcheng) \n");
		sql.append("values(?, ?, ?, ?, ?, \n");
		sql.append("?, ?, ?, ?, ?, \n");
		sql.append("?, ?, ?, ?, ?, \n");
		sql.append("?, ?, ?, ?, ?, \n");
		sql.append("?, ?, ?, ?, ?, \n");
		sql.append("?, ?)");

		Object[] params = new Object[] {
				order.getDingdanBianhao(), order.getMaijiaHuiyuanming(), order.getMaijiaZhifubaoZhanghao(), order.getMaijiaYingfuHuokuan(), order.getMaijiaYingfuYoufei(),
				order.getMaijiaZhifuJifen(), order.getZongjine(), order.getFandianJifen(), order.getMaijiaShijiZhifuJine(), order.getMaijiaShijiZhifuJifen(),
				order.getDingdanZhuangtai(), order.getMaijiaLiuyan(), order.getShouhuorenXingming(), order.getShouhuoDizhi(), order.getYunsongFangshi(),
				order.getLianxiDianhua(), order.getLianxiShouji(), order.getDingdanChuangjianShijian(), order.getDingdanZhifuShijian(), order.getShangpinBiaoti(),
				order.getShangpinZhonglei(), order.getWuliuDanhao(), order.getWuliuGongsi(), order.getDingdanBeizhu(), order.getShangpinZongshuliang(),
				order.getDianpuId(), order.getDianpuMingcheng()
		};

		return this.doUpdate(sql.toString(), params);
	}

}
