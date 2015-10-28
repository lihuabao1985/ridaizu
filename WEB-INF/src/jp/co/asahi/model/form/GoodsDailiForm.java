package jp.co.asahi.model.form;

import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "goodsDailiForm")
@SessionScoped
public class GoodsDailiForm extends Model {

	private static final long serialVersionUID = 1L;

	/** id */
	private String id;

	/** 代理ID */
	private String dailiId;

	/** 商品ID */
	private String goodsId;

	/** 最小数量 */
	private String minCount;

	/** 代理价格 */
	private String price;

	private List<SelectItem> dailiSelectItemList;

	private List<SelectItem> goodsSelectItemList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDailiId() {
		return dailiId;
	}

	public void setDailiId(String dailiId) {
		this.dailiId = dailiId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getMinCount() {
		return minCount;
	}

	public void setMinCount(String minCount) {
		this.minCount = minCount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<SelectItem> getDailiSelectItemList() {
		return dailiSelectItemList;
	}

	public void setDailiSelectItemList(List<SelectItem> dailiSelectItemList) {
		this.dailiSelectItemList = dailiSelectItemList;
	}

	public List<SelectItem> getGoodsSelectItemList() {
		return goodsSelectItemList;
	}

	public void setGoodsSelectItemList(List<SelectItem> goodsSelectItemList) {
		this.goodsSelectItemList = goodsSelectItemList;
	}

	public void clearAll() {
		id = null;
		dailiId = null;
		goodsId = null;
		minCount = null;
		price = null;
		dailiSelectItemList = null;
		goodsSelectItemList = null;
	}

}
