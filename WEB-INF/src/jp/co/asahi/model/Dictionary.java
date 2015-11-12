package jp.co.asahi.model;

import java.sql.Timestamp;

public class Dictionary extends Model {

	private static final long serialVersionUID = 1L;

	private int id;

	private String type;

	private String value;

	private String name;

	private int orderNum;

	private String comment;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
