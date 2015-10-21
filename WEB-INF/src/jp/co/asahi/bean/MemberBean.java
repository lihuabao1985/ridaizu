package jp.co.asahi.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import jp.co.asahi.cache.CacheDataBean;
import jp.co.asahi.lazy.LazyMemberDataModel;
import jp.co.asahi.model.Member;
import jp.co.asahi.model.form.MemberForm;
import jp.co.asahi.model.search.MemberSearch;
import jp.co.asahi.service.impl.MemberServiceImpl;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "memberBean")
@SessionScoped
public class MemberBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Member> lazyModel;

    @ManagedProperty("#{memberServiceImpl}")
    private MemberServiceImpl memberService;

    @ManagedProperty("#{memberSearch}")
    private MemberSearch memberSearch;

    @ManagedProperty("#{memberForm}")
    private MemberForm memberForm;

    @ManagedProperty("#{cacheDataBean}")
    private CacheDataBean cacheData;

    @PostConstruct
    public void init() {
    	searchMemberSync();
    }

	public void searchMemberSync() {
		lazyModel = new LazyMemberDataModel(memberSearch);
	}

	public void viewMember(Member member) throws SQLException {

		setInitValues(member);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 320);
        options.put("contentHeight", 200);

        RequestContext.getCurrentInstance().openDialog("member", options, null);
    }

	private void setInitValues(Member member) throws SQLException {
		memberForm.setUserId(String.valueOf(member.getUserId()));
		memberForm.setWangwangNo(member.getWangwangNo());
		memberForm.setServiceTicket(String.valueOf(member.getServiceTicket()));
		memberForm.setUseServiceTicket(String.valueOf(member.getUseServiceTicket()));
		memberForm.setServiceTicketList(getServiceTicketList(member.getGrade()));
	}

	private List<String> getServiceTicketList(String grade) {

		// member.getGrade()
		int count = 0;
		if ("普通会员".equals(grade)) {
			count = 1;
		} else if ("高级会员".equals(grade)) {
			count = 2;
		} else if ("VIP会员".equals(grade)) {
			count = 3;
		} else if ("至尊VIP会员".equals(grade)) {
			count = 5;
		}

		List<String> list = new ArrayList<String> ();
		list.add("0");
		for (int i = 1; i <= count; i++) {
			list.add(String.valueOf(i));
		}

		return list;
	}

	public void updateMember() throws SQLException {


		Member member = new Member();
		member.setUserId(Integer.valueOf(memberForm.getUserId()));

		if (Strings.isNullOrEmpty(memberForm.getUseServiceTicket())) {
			member.setUseServiceTicket(0);
		} else {
			member.setUseServiceTicket(Integer.valueOf(memberForm.getUseServiceTicket()));
		}

		boolean result = memberService.updateServiceTicket(member) > 0;

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateMemberResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("会员信息%s成功。", optStr);
		} else {
			resultStr = String.format("会员信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public LazyDataModel<Member> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Member> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public MemberServiceImpl getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}

	public MemberSearch getMemberSearch() {
		return memberSearch;
	}

	public void setMemberSearch(MemberSearch memberSearch) {
		this.memberSearch = memberSearch;
	}

	public MemberForm getMemberForm() {
		return memberForm;
	}

	public void setMemberForm(MemberForm memberForm) {
		this.memberForm = memberForm;
	}

	public CacheDataBean getCacheData() {
		return cacheData;
	}

	public void setCacheData(CacheDataBean cacheData) {
		this.cacheData = cacheData;
	}

}