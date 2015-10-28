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

import jp.co.asahi.lazy.LazyDailiDataModel;
import jp.co.asahi.model.Daili;
import jp.co.asahi.model.form.DailiForm;
import jp.co.asahi.model.search.DailiSearch;
import jp.co.asahi.service.impl.DailiServiceImpl;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "dailiBean")
@SessionScoped
public class DailiBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Daili> lazyModel;

    @ManagedProperty("#{dailiServiceImpl}")
    private DailiServiceImpl dailiService;

    @ManagedProperty("#{dailiSearch}")
    private DailiSearch dailiSearch;

    @ManagedProperty("#{dailiForm}")
    private DailiForm dailiForm;

	private List<Daili> selectedDailiList;

    @PostConstruct
    public void init() {
    	searchDailiSync();
    }

	public void searchDailiSync() {
		lazyModel = new LazyDailiDataModel(dailiSearch);
	}

	public void viewRegisterDaili() {

		dailiForm.clearAll();

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 330);

        RequestContext.getCurrentInstance().openDialog("daili", options, null);
    }

	public void viewUpdateDaili(Daili daili) throws SQLException {

		setInitValues(daili);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 365);

        RequestContext.getCurrentInstance().openDialog("daili", options, null);
    }

	private void setInitValues(Daili daili) throws SQLException {
		dailiForm.setId(String.valueOf(daili.getId()));
		dailiForm.setName(daili.getName());
		dailiForm.setWangwang(daili.getWangwang());
		dailiForm.setWeixin(daili.getWeixin());
		dailiForm.setQq(daili.getQq());
		dailiForm.setTel(daili.getTel());
		dailiForm.setEmail(daili.getEmail());
		dailiForm.setDelFlg(daili.isDelFlg());
	}

	public void registerDaili() {
		boolean result = dailiService.insert(getDaili());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateDaili() throws SQLException {
		boolean result = dailiService.update(getDaili());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteDaili() {

		if (selectedDailiList == null || selectedDailiList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的代理信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedDailiList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedDailiList.size(); i++) {
			Daili daili = selectedDailiList.get(i);

			ids[i] = daili.getId();
			postNameList.add(daili.getName());
		}

		boolean result = dailiService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("代理[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("代理[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Daili delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerDailiResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateDailiResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("代理信息%s成功。", optStr);
		} else {
			resultStr = String.format("代理信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private Daili getDaili() {

		Daili daili = new Daili();

		if(!Strings.isNullOrEmpty(dailiForm.getId())) {
			daili.setId(Integer.valueOf(dailiForm.getId()));
		}
		daili.setName(dailiForm.getName());
		daili.setWangwang(dailiForm.getWangwang());
		daili.setWeixin(dailiForm.getWeixin());
		daili.setQq(dailiForm.getQq());
		daili.setTel(dailiForm.getTel());
		daili.setEmail(dailiForm.getEmail());
		daili.setDelFlg(dailiForm.isDelFlg());

		return daili;
	}

	public LazyDataModel<Daili> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Daili> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public DailiServiceImpl getDailiService() {
		return dailiService;
	}

	public void setDailiService(DailiServiceImpl dailiService) {
		this.dailiService = dailiService;
	}

	public DailiSearch getDailiSearch() {
		return dailiSearch;
	}

	public void setDailiSearch(DailiSearch dailiSearch) {
		this.dailiSearch = dailiSearch;
	}

	public DailiForm getDailiForm() {
		return dailiForm;
	}

	public void setDailiForm(DailiForm dailiForm) {
		this.dailiForm = dailiForm;
	}

	public List<Daili> getSelectedDailiList() {
		return selectedDailiList;
	}

	public void setSelectedDailiList(List<Daili> selectedDailiList) {
		this.selectedDailiList = selectedDailiList;
	}

}