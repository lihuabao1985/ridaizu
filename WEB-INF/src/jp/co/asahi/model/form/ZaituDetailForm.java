package jp.co.asahi.model.form;

import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "zaituDetailForm")
@SessionScoped
public class ZaituDetailForm extends Model {

	private static final long serialVersionUID = 1L;

	/** 在途明细ID */
	private String id;

	/** 在途ID */
	private String zaituId;

	/** 在途时间 */
	private String zaituDate;

	/** 商品条形码 */
	private String goodsBarcode;

	/** 商品数量 */
	private String count;

	/** 进价 */
	private String price;

	/** 备注 */
	private String beizhu;

	private List<SelectItem> zaituSelectItemList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZaituId() {
		return zaituId;
	}

	public void setZaituId(String zaituId) {
		this.zaituId = zaituId;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getZaituDate() {
		return zaituDate;
	}

	public void setZaituDate(String zaituDate) {
		this.zaituDate = zaituDate;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public List<SelectItem> getZaituSelectItemList() {
		return zaituSelectItemList;
	}

	public void setZaituSelectItemList(List<SelectItem> zaituSelectItemList) {
		this.zaituSelectItemList = zaituSelectItemList;
	}

	public void clearAll() {
		id = null;
		zaituId = null;
		zaituDate = null;
		goodsBarcode = null;
		count = null;
		price = null;
		beizhu = null;
		zaituSelectItemList = null;
	}

}
