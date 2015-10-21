package jp.co.asahi.model.form;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "goodsForm")
@SessionScoped
public class GoodsForm extends Model {

	private static final long serialVersionUID = 1L;

	/** 商品ID */
	private String id;

	/** 商品名 */
	private String name;

	/** 淘宝商品名 */
	private String taobaoName;

	/** 商品编码 */
	private String goodsCode;

	/** 商品条形码 */
	private String barcode;

	/** 商品类型ID */
	private String goodsTypeId;

	/** 商品规格 */
	private String capacity;

	/** 商品重量 */
	private String weight;

	/** 商品批发价格 */
	private String wholesalePrice;

	/** 商品市场价格 */
	private String price;

	/** 商品售价 */
	private String salesPrice;

	/** 商品简介 */
	private String summary;

	/** 商品详细 */
	private String detail;

	/** 商品成分 */
	private String ingredient;

	/** 商品效果 */
	private String effect;

	/** 适合年龄段 */
	private String suitAgeId;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(String wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
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

	public String getSuitAgeId() {
		return suitAgeId;
	}

	public void setSuitAgeId(String suitAgeId) {
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

	public void clearAll() {
		id = null;
		name = null;
		taobaoName = null;
		goodsCode = null;
		barcode = null;
		goodsTypeId = null;
		capacity = null;
		weight = null;
		wholesalePrice = null;
		price = null;
		salesPrice = null;
		summary = null;
		detail = null;
		ingredient = null;
		effect = null;
		suitAgeId = null;
		instructions = null;
		precautions = null;
		goodsUrl = null;
		photoUrl = null;
		thumbnailsUrl = null;
		delFlg = false;
	}

}
