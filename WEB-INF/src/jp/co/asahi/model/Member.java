package jp.co.asahi.model;

public class Member extends Model {

	private static final long serialVersionUID = 1L;

	private int userId;

	private String wangwangNo;

	private String link;

	private String userName;

	private String sex;

	private String mobile;

	private String email;

	private String birth;

	private String province;

	private String city;

	private String grade;

	private String tradeTotal;

	private String turnover;

	private String goodsCount;

	private String address;

	private String lastTradeDate;

	private boolean addFlg;

	private boolean addedFlg;

	private int serviceTicket;

	private int useServiceTicket;

	private int afterServiceTicket;

	private boolean sendMsgFlg;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getWangwangNo() {
		return wangwangNo;
	}

	public void setWangwangNo(String wangwangNo) {
		this.wangwangNo = wangwangNo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTradeTotal() {
		return tradeTotal;
	}

	public void setTradeTotal(String tradeTotal) {
		this.tradeTotal = tradeTotal;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAddFlg() {
		return addFlg;
	}

	public void setAddFlg(boolean addFlg) {
		this.addFlg = addFlg;
	}

	public boolean isAddedFlg() {
		return addedFlg;
	}

	public void setAddedFlg(boolean addedFlg) {
		this.addedFlg = addedFlg;
	}

	public int getServiceTicket() {
		return serviceTicket;
	}

	public void setServiceTicket(int serviceTicket) {
		this.serviceTicket = serviceTicket;
	}

	public boolean isSendMsgFlg() {
		return sendMsgFlg;
	}

	public void setSendMsgFlg(boolean sendMsgFlg) {
		this.sendMsgFlg = sendMsgFlg;
	}

	public String getLastTradeDate() {
		return lastTradeDate;
	}

	public void setLastTradeDate(String lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}

	public int getUseServiceTicket() {
		return useServiceTicket;
	}

	public void setUseServiceTicket(int useServiceTicket) {
		this.useServiceTicket = useServiceTicket;
	}

	public int getAfterServiceTicket() {
		return afterServiceTicket;
	}

	public void setAfterServiceTicket(int afterServiceTicket) {
		this.afterServiceTicket = afterServiceTicket;
	}

}
