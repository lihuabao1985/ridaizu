package jp.co.asahi.model;

import java.sql.Timestamp;

public class Brand extends Model {

	private static final long serialVersionUID = 1L;

	/** 品牌ID */
	private int id;

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
