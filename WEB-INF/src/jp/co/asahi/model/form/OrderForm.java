package jp.co.asahi.model.form;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "orderForm")
@SessionScoped
public class OrderForm extends Model {

	private static final long serialVersionUID = 1L;

	/** 品牌ID */
	private String id;

	/** 品牌名 */
	private String name;

	/** 品牌简介 */
	private String summary;

	/** 品牌详细介绍 */
	private String detail;

	/** 品牌主页 */
	private String homepage;

	/** 品牌焦点图链接 */
	private String photoUrl;

	/** 品牌缩略图链接 */
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

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
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
		summary = null;
		detail = null;
		homepage = null;
		photoUrl = null;
		thumbnailsUrl = null;
		delFlg = false;
	}

}
