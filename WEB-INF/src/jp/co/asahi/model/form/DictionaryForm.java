package jp.co.asahi.model.form;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "dictionaryForm")
@SessionScoped
public class DictionaryForm extends Model {

	private static final long serialVersionUID = 1L;

	private int id;

	private String type;

	private String value;

	private String name;

	private int orderNum;

	private String comment;

	private boolean delFlg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isDelFlg() {
		return delFlg;
	}

	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
	}

	public void clearAll() {
		id = 0;
		type = null;
		value = null;
		name = null;
		orderNum = 0;
		comment = null;
		delFlg = false;
	}

}
