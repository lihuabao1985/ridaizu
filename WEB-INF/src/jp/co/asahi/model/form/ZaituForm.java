package jp.co.asahi.model.form;

import java.util.Date;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "zaituForm")
@SessionScoped
public class ZaituForm extends Model {

	private static final long serialVersionUID = 1L;

	/** 在途ID */
	private String id;

	/** 在途日期 */
	private Date zaituDate;

	/** 商品总数 */
	private String goodsTotalCount;

	/** 商品总价 */
	private String goodsTotalPrice;

	/** 运费总价 */
	private String freightTotal;

	/** 到货标识 */
	private boolean daohuoFlg;

	/** 到货日期 */
	private Date daohuoDate;

	/** 备注 */
	private String beizhu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getZaituDate() {
		return zaituDate;
	}

	public void setZaituDate(Date zaituDate) {
		this.zaituDate = zaituDate;
	}

	public String getGoodsTotalCount() {
		return goodsTotalCount;
	}

	public void setGoodsTotalCount(String goodsTotalCount) {
		this.goodsTotalCount = goodsTotalCount;
	}

	public String getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(String goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}

	public String getFreightTotal() {
		return freightTotal;
	}

	public void setFreightTotal(String freightTotal) {
		this.freightTotal = freightTotal;
	}

	public boolean isDaohuoFlg() {
		return daohuoFlg;
	}

	public void setDaohuoFlg(boolean daohuoFlg) {
		this.daohuoFlg = daohuoFlg;
	}

	public Date getDaohuoDate() {
		return daohuoDate;
	}

	public void setDaohuoDate(Date daohuoDate) {
		this.daohuoDate = daohuoDate;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public void clearAll() {
		id = null;
		zaituDate = null;
		goodsTotalCount = null;
		goodsTotalPrice = null;
		freightTotal = null;
		daohuoFlg = false;
		daohuoDate = null;
		beizhu = null;
	}

}
