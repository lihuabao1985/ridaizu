package jp.co.asahi.model;


public class OrderDetail extends Model {

	private static final long serialVersionUID = 1L;
	/** ID */
	public int id;

	/** 订单编号 */
	public String dingdanBianhao;

	/** 标题 */
	public String biaoti;

	/** 价格 */
	public double jiage;

	/** 购买数量 */
	public int goumaiShuliang;

	/** 外部系统编号 */
	public String waibuXitongBianhao;

	/** 商品属性 */
	public String shangpinShuxing;

	/** 套餐信息 */
	public String taocanXinxi;

	/** 备注 */
	public String beizhu;

	/** 订单状态 */
	public String dingdanZhuangtai;

	/** 商家编码 */
	public String shangjiaBianma;

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

	public String getBiaoti() {
		return biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public double getJiage() {
		return jiage;
	}

	public void setJiage(double jiage) {
		this.jiage = jiage;
	}

	public int getGoumaiShuliang() {
		return goumaiShuliang;
	}

	public void setGoumaiShuliang(int goumaiShuliang) {
		this.goumaiShuliang = goumaiShuliang;
	}

	public String getWaibuXitongBianhao() {
		return waibuXitongBianhao;
	}

	public void setWaibuXitongBianhao(String waibuXitongBianhao) {
		this.waibuXitongBianhao = waibuXitongBianhao;
	}

	public String getShangpinShuxing() {
		return shangpinShuxing;
	}

	public void setShangpinShuxing(String shangpinShuxing) {
		this.shangpinShuxing = shangpinShuxing;
	}

	public String getTaocanXinxi() {
		return taocanXinxi;
	}

	public void setTaocanXinxi(String taocanXinxi) {
		this.taocanXinxi = taocanXinxi;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getDingdanZhuangtai() {
		return dingdanZhuangtai;
	}

	public void setDingdanZhuangtai(String dingdanZhuangtai) {
		this.dingdanZhuangtai = dingdanZhuangtai;
	}

	public String getShangjiaBianma() {
		return shangjiaBianma;
	}

	public void setShangjiaBianma(String shangjiaBianma) {
		this.shangjiaBianma = shangjiaBianma;
	}

}
