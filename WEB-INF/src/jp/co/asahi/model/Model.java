package jp.co.asahi.model;

import java.io.Serializable;


public class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;

	private String value;

	private String checkboxHtml;

	private String updateHtml;

	private String deleteHtml;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCheckboxHtml() {
		return checkboxHtml;
	}

	public void setCheckboxHtml(String id, int value) {
		String format = "<input type='checkbox' id='cbSingle%s' name='cbSingle%s' value='%d' onclick='checkSingle(this);'/>";
		this.checkboxHtml = String.format(format, id, id, value);
	}

	public String getUpdateHtml() {
		return updateHtml;
	}

	public void setUpdateHtml(String id, int value) {
		String format = "<input type='button' value='编辑' onclick='update%s(%d);'/>";
		this.updateHtml = String.format(format, id, value);
	}

	public String getDeleteHtml() {
		return deleteHtml;
	}

	public void setDeleteHtml(String id, int value) {
		String format = "<input type='button' value='删除' onclick='delete%s(%d);'/>";
		this.deleteHtml = String.format(format, id, value);
	}

}
