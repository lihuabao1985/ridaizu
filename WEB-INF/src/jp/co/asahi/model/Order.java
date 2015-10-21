package jp.co.asahi.model;

import java.sql.Timestamp;

public class Order extends Model {

	private static final long serialVersionUID = 1L;

	/** ID */
	public int id;

	/** 订单编号 */
	public String dingdanBianhao;

	/** 买家会员名 */
	public String maijiaHuiyuanming;

	/** 买家支付宝账号 */
	public String maijiaZhifubaoZhanghao;

	/** 买家应付货款 */
	public double maijiaYingfuHuokuan;

	/** 买家应付邮费 */
	public double maijiaYingfuYoufei;

	/** 买家支付积分 */
	public int maijiaZhifuJifen;

	/** 总金额 */
	public double zongjine;

	/** 返点积分 */
	public int fandianJifen;

	/** 买家实际支付金额 */
	public double maijiaShijiZhifuJine;

	/** 买家实际支付积分 */
	public int maijiaShijiZhifuJifen;

	/** 订单状态 */
	public String dingdanZhuangtai;

	/** 买家留言 */
	public String maijiaLiuyan;

	/** 收货人姓名 */
	public String shouhuorenXingming;

	/** 收货地址 */
	public String shouhuoDizhi;

	/** 运送方式 */
	public String yunsongFangshi;

	/** 联系电话 */
	public String lianxiDianhua;

	/** 联系手机 */
	public String lianxiShouji;

	/** 订单创建时间 */
	public Timestamp dingdanChuangjianShijian;

	/** 订单付款时间 */
	public Timestamp dingdanZhifuShijian;

	/** 宝贝标题 */
	public String shangpinBiaoti;

	/** 宝贝种类 */
	public String shangpinZhonglei;

	/** 物流单号 */
	public String wuliuDanhao;

	/** 物流公司 */
	public String wuliuGongsi;

	/** 订单备注 */
	public String dingdanBeizhu;

	/** 宝贝总数量 */
	public int shangpinZongshuliang;

	/** 店铺Id */
	public String dianpuId;

	/** 店铺名称 */
	public String dianpuMingcheng;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDingdanBianhao() {
		return dingdanBianhao;
	}

	public void setDingdanBianhao(String dingdanBianhao) {
		this.dingdanBianhao = dingdanBianhao;
	}

	public String getMaijiaHuiyuanming() {
		return maijiaHuiyuanming;
	}

	public void setMaijiaHuiyuanming(String maijiaHuiyuanming) {
		this.maijiaHuiyuanming = maijiaHuiyuanming;
	}

	public String getMaijiaZhifubaoZhanghao() {
		return maijiaZhifubaoZhanghao;
	}

	public void setMaijiaZhifubaoZhanghao(String maijiaZhifubaoZhanghao) {
		this.maijiaZhifubaoZhanghao = maijiaZhifubaoZhanghao;
	}

	public double getMaijiaYingfuHuokuan() {
		return maijiaYingfuHuokuan;
	}

	public void setMaijiaYingfuHuokuan(double maijiaYingfuHuokuan) {
		this.maijiaYingfuHuokuan = maijiaYingfuHuokuan;
	}

	public double getMaijiaYingfuYoufei() {
		return maijiaYingfuYoufei;
	}

	public void setMaijiaYingfuYoufei(double maijiaYingfuYoufei) {
		this.maijiaYingfuYoufei = maijiaYingfuYoufei;
	}

	public int getMaijiaZhifuJifen() {
		return maijiaZhifuJifen;
	}

	public void setMaijiaZhifuJifen(int maijiaZhifuJifen) {
		this.maijiaZhifuJifen = maijiaZhifuJifen;
	}

	public double getZongjine() {
		return zongjine;
	}

	public void setZongjine(double zongjine) {
		this.zongjine = zongjine;
	}

	public int getFandianJifen() {
		return fandianJifen;
	}

	public void setFandianJifen(int fandianJifen) {
		this.fandianJifen = fandianJifen;
	}

	public double getMaijiaShijiZhifuJine() {
		return maijiaShijiZhifuJine;
	}

	public void setMaijiaShijiZhifuJine(double maijiaShijiZhifuJine) {
		this.maijiaShijiZhifuJine = maijiaShijiZhifuJine;
	}

	public int getMaijiaShijiZhifuJifen() {
		return maijiaShijiZhifuJifen;
	}

	public void setMaijiaShijiZhifuJifen(int maijiaShijiZhifuJifen) {
		this.maijiaShijiZhifuJifen = maijiaShijiZhifuJifen;
	}

	public String getDingdanZhuangtai() {
		return dingdanZhuangtai;
	}

	public void setDingdanZhuangtai(String dingdanZhuangtai) {
		this.dingdanZhuangtai = dingdanZhuangtai;
	}

	public String getMaijiaLiuyan() {
		return maijiaLiuyan;
	}

	public void setMaijiaLiuyan(String maijiaLiuyan) {
		this.maijiaLiuyan = maijiaLiuyan;
	}

	public String getShouhuorenXingming() {
		return shouhuorenXingming;
	}

	public void setShouhuorenXingming(String shouhuorenXingming) {
		this.shouhuorenXingming = shouhuorenXingming;
	}

	public String getShouhuoDizhi() {
		return shouhuoDizhi;
	}

	public void setShouhuoDizhi(String shouhuoDizhi) {
		this.shouhuoDizhi = shouhuoDizhi;
	}

	public String getYunsongFangshi() {
		return yunsongFangshi;
	}

	public void setYunsongFangshi(String yunsongFangshi) {
		this.yunsongFangshi = yunsongFangshi;
	}

	public String getLianxiDianhua() {
		return lianxiDianhua;
	}

	public void setLianxiDianhua(String lianxiDianhua) {
		this.lianxiDianhua = lianxiDianhua;
	}

	public String getLianxiShouji() {
		return lianxiShouji;
	}

	public void setLianxiShouji(String lianxiShouji) {
		this.lianxiShouji = lianxiShouji;
	}

	public Timestamp getDingdanChuangjianShijian() {
		return dingdanChuangjianShijian;
	}

	public void setDingdanChuangjianShijian(Timestamp dingdanChuangjianShijian) {
		this.dingdanChuangjianShijian = dingdanChuangjianShijian;
	}

	public Timestamp getDingdanZhifuShijian() {
		return dingdanZhifuShijian;
	}

	public void setDingdanZhifuShijian(Timestamp dingdanZhifuShijian) {
		this.dingdanZhifuShijian = dingdanZhifuShijian;
	}

	public String getShangpinBiaoti() {
		return shangpinBiaoti;
	}

	public void setShangpinBiaoti(String shangpinBiaoti) {
		this.shangpinBiaoti = shangpinBiaoti;
	}

	public String getShangpinZhonglei() {
		return shangpinZhonglei;
	}

	public void setShangpinZhonglei(String shangpinZhonglei) {
		this.shangpinZhonglei = shangpinZhonglei;
	}

	public String getWuliuDanhao() {
		return wuliuDanhao;
	}

	public void setWuliuDanhao(String wuliuDanhao) {
		this.wuliuDanhao = wuliuDanhao;
	}

	public String getWuliuGongsi() {
		return wuliuGongsi;
	}

	public void setWuliuGongsi(String wuliuGongsi) {
		this.wuliuGongsi = wuliuGongsi;
	}

	public String getDingdanBeizhu() {
		return dingdanBeizhu;
	}

	public void setDingdanBeizhu(String dingdanBeizhu) {
		this.dingdanBeizhu = dingdanBeizhu;
	}

	public int getShangpinZongshuliang() {
		return shangpinZongshuliang;
	}

	public void setShangpinZongshuliang(int shangpinZongshuliang) {
		this.shangpinZongshuliang = shangpinZongshuliang;
	}

	public String getDianpuId() {
		return dianpuId;
	}

	public void setDianpuId(String dianpuId) {
		this.dianpuId = dianpuId;
	}

	public String getDianpuMingcheng() {
		return dianpuMingcheng;
	}

	public void setDianpuMingcheng(String dianpuMingcheng) {
		this.dianpuMingcheng = dianpuMingcheng;
	}

}
