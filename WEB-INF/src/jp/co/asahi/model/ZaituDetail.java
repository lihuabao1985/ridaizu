package jp.co.asahi.model;

import java.sql.Timestamp;


public class ZaituDetail extends Model {

	private static final long serialVersionUID = 1L;

	/** 在途明细ID */
	private int id;

	/** 在途ID */
	private int zaituId;

	/** 在途时间 */
	private Timestamp zaituDate;

	/** 在途时间 */
	private String zaituDateString;

	/** 商品条形码 */
	private String goodsBarcode;

	/** 商品数量 */
	private int count;

	/** 进价 */
	private double price;

	/** 备注 */
	private String beizhu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZaituId() {
		return zaituId;
	}

	public void setZaituId(int zaituId) {
		this.zaituId = zaituId;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public Timestamp getZaituDate() {
		return zaituDate;
	}

	public void setZaituDate(Timestamp zaituDate) {
		this.zaituDate = zaituDate;
	}

	public String getZaituDateString() {
		return zaituDateString;
	}

	public void setZaituDateString(String zaituDateString) {
		this.zaituDateString = zaituDateString;
	}

}
