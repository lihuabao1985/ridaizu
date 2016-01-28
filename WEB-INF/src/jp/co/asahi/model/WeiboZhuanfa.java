package jp.co.asahi.model;

public class WeiboZhuanfa extends Model {

	private static final long serialVersionUID = 1L;
	
	private int id;

	private String weiboId;

	private String weiboName;

	private int zhuanfaCount;
	
	private String discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getZhuanfaCount() {
        return zhuanfaCount;
    }

    public void setZhuanfaCount(int zhuanfaCount) {
        this.zhuanfaCount = zhuanfaCount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
