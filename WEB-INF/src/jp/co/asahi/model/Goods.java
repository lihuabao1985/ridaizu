package jp.co.asahi.model;

import java.sql.Timestamp;

public class Goods extends Model {

	private static final long serialVersionUID = 1L;

	/** 商品ID */
	private int id;

	/** 商品名 */
	private String name;

	/** 淘宝商品名 */
	private String taobaoName;

	/** 商品编码 */
	private String goodsCode;

	/** 商品条形码 */
	private String barcode;

	/** 商品类型ID */
	private int goodsTypeId;

	/** 商品规格 */
	private String capacity;

	/** 商品重量 */
	private int weight;

	/** 商品批发价格 */
	private double wholesalePrice;

	/** 商品市场价格 */
	private double price;

	/** 商品售价 */
	private double salesPrice;

	/** 商品简介 */
	private String summary;

	/** 商品详细 */
	private String detail;

	/** 商品成分 */
	private String ingredient;

	/** 商品效果 */
	private String effect;

	/** 适合年龄段 */
	private int suitAgeId;

	/** 使用方法 */
	private String instructions;

	/** 注意事项 */
	private String precautions;

	/** 商品链接 */
	private String goodsUrl;

	/** 商品图片链接 */
	private String photoUrl;

	/** 商品缩略图链接 */
	private String thumbnailsUrl;

	/** 删除标识 */
	private boolean delFlg;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaobaoName() {
		return taobaoName;
	}

	public void setTaobaoName(String taobaoName) {
		this.taobaoName = taobaoName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(int goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public int getSuitAgeId() {
		return suitAgeId;
	}

	public void setSuitAgeId(int suitAgeId) {
		this.suitAgeId = suitAgeId;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getPrecautions() {
		return precautions;
	}

	public void setPrecautions(String precautions) {
		this.precautions = precautions;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getThumbnailsUrl() {
		return thumbnailsUrl;
	}

	public void setThumbnailsUrl(String thumbnailsUrl) {
		this.thumbnailsUrl = thumbnailsUrl;
	}

	public boolean isDelFlg() {
		return delFlg;
	}

	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
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

}
