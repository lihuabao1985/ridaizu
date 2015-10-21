package jp.co.asahi.model.form;

import java.util.List;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.model.Model;

@javax.faces.bean.ManagedBean(name = "memberForm")
@SessionScoped
public class MemberForm extends Model {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String wangwangNo;

	private String serviceTicket;

	private String useServiceTicket;

	private List<String> serviceTicketList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWangwangNo() {
		return wangwangNo;
	}

	public void setWangwangNo(String wangwangNo) {
		this.wangwangNo = wangwangNo;
	}

	public String getServiceTicket() {
		return serviceTicket;
	}

	public void setServiceTicket(String serviceTicket) {
		this.serviceTicket = serviceTicket;
	}

	public String getUseServiceTicket() {
		return useServiceTicket;
	}

	public void setUseServiceTicket(String useServiceTicket) {
		this.useServiceTicket = useServiceTicket;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getServiceTicketList() {
		return serviceTicketList;
	}

	public void setServiceTicketList(List<String> serviceTicketList) {
		this.serviceTicketList = serviceTicketList;
	}

}
