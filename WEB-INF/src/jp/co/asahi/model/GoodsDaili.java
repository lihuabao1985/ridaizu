package jp.co.asahi.model;


public class GoodsDaili extends Model {

	private static final long serialVersionUID = 1L;

	/** id */
	private int id;

	/** 代理ID */
	private int dailiId;

	/** 代理名 */
	private String dailiName;

	/** 商品ID */
	private int goodsId;

	/** 商品名 */
	private String goodsName;

	/** 商品淘宝名 */
	private String taobaoName;

	/** 商品条形码 */
	private String barcode;

	/** 最小数量 */
	private int minCount;

	/** 代理价格 */
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDailiId() {
		return dailiId;
	}

	public void setDailiId(int dailiId) {
		this.dailiId = dailiId;
	}

	public String getDailiName() {
		return dailiName;
	}

	public void setDailiName(String dailiName) {
		this.dailiName = dailiName;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getTaobaoName() {
		return taobaoName;
	}

	public void setTaobaoName(String taobaoName) {
		this.taobaoName = taobaoName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getMinCount() {
		return minCount;
	}

	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
