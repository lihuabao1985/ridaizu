package jp.co.asahi.model;

import java.sql.Timestamp;

public class Zaitu extends Model {

	private static final long serialVersionUID = 1L;

	/** 在途ID */
	private int id;

	/** 在途日期 */
	private Timestamp zaituDate;

	private String zaituDateString;

	/** 商品总数 */
	private int goodsTotalCount;

	/** 商品总价 */
	private double goodsTotalPrice;

	/** 运费总价 */
	private double freightTotal;

	/** 到货标识 */
	private boolean daohuoFlg;

	/** 到货日期 */
	private Timestamp daohuoDate;

	private String daohuoDateString;

	/** 备注 */
	private String beizhu;

	/** 登录者 */
	private String rgtOpt;

	/** 登录时间 */
	private Timestamp rgtDate;

	/** 更新者 */
	private String updOpt;

	/** 更新时间 */
	private Timestamp updDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getZaituDate() {
		return zaituDate;
	}

	public void setZaituDate(Timestamp zaituDate) {
		this.zaituDate = zaituDate;
	}

	public int getGoodsTotalCount() {
		return goodsTotalCount;
	}

	public void setGoodsTotalCount(int goodsTotalCount) {
		this.goodsTotalCount = goodsTotalCount;
	}

	public double getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(double goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}

	public double getFreightTotal() {
		return freightTotal;
	}

	public void setFreightTotal(double freightTotal) {
		this.freightTotal = freightTotal;
	}

	public boolean isDaohuoFlg() {
		return daohuoFlg;
	}

	public void setDaohuoFlg(boolean daohuoFlg) {
		this.daohuoFlg = daohuoFlg;
	}

	public Timestamp getDaohuoDate() {
		return daohuoDate;
	}

	public void setDaohuoDate(Timestamp daohuoDate) {
		this.daohuoDate = daohuoDate;
	}

	public String getRgtOpt() {
		return rgtOpt;
	}

	public void setRgtOpt(String rgtOpt) {
		this.rgtOpt = rgtOpt;
	}

	public Timestamp getRgtDate() {
		return rgtDate;
	}

	public void setRgtDate(Timestamp rgtDate) {
		this.rgtDate = rgtDate;
	}

	public String getUpdOpt() {
		return updOpt;
	}

	public void setUpdOpt(String updOpt) {
		this.updOpt = updOpt;
	}

	public Timestamp getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getZaituDateString() {
		return zaituDateString;
	}

	public void setZaituDateString(String zaituDateString) {
		this.zaituDateString = zaituDateString;
	}

	public String getDaohuoDateString() {
		return daohuoDateString;
	}

	public void setDaohuoDateString(String daohuoDateString) {
		this.daohuoDateString = daohuoDateString;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

}
