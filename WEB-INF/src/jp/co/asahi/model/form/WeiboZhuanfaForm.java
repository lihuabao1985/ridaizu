package jp.co.asahi.model.form;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "weiboZhuanfaForm")
@SessionScoped
public class WeiboZhuanfaForm extends Model {

	private static final long serialVersionUID = 1L;

	private String id;

	private String weiboId;

	private String weiboName;

	private String zhuanfaCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getWeiboName() {
        return weiboName;
    }

    public void setWeiboName(String weiboName) {
        this.weiboName = weiboName;
    }

    public String getZhuanfaCount() {
        return zhuanfaCount;
    }

    public void setZhuanfaCount(String zhuanfaCount) {
        this.zhuanfaCount = zhuanfaCount;
    }

}
