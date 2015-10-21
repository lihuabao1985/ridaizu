package jp.co.asahi.bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import jp.co.asahi.lazy.LazyZaituDataModel;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.form.ZaituForm;
import jp.co.asahi.model.search.ZaituSearch;
import jp.co.asahi.service.impl.ZaituServiceImpl;
import jp.co.asahi.util.DateUtil;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "zaituBean")
@SessionScoped
public class ZaituBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Zaitu> lazyModel;

    @ManagedProperty("#{zaituServiceImpl}")
    private ZaituServiceImpl zaituService;

    @ManagedProperty("#{zaituSearch}")
    private ZaituSearch zaituSearch;

    @ManagedProperty("#{zaituForm}")
    private ZaituForm zaituForm;

	private List<Zaitu> selectedZaituList;

    @PostConstruct
    public void init() {
    	searchZaituSync();
    }

	public void searchZaituSync() {
		lazyModel = new LazyZaituDataModel(zaituSearch);
	}

	public void viewRegisterZaitu() {

		zaituForm.clearAll();

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 330);

        RequestContext.getCurrentInstance().openDialog("zaitu", options, null);
    }

	public void viewUpdateZaitu(Zaitu zaitu) throws SQLException {

		setInitValues(zaitu);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 365);

        RequestContext.getCurrentInstance().openDialog("zaitu", options, null);
    }

	private void setInitValues(Zaitu zaitu) throws SQLException {
		zaituForm.setId(String.valueOf(zaitu.getId()));
		zaituForm.setZaituDate(zaitu.getZaituDate());
		zaituForm.setGoodsTotalCount(String.valueOf(zaitu.getGoodsTotalCount()));
		zaituForm.setGoodsTotalPrice(String.valueOf(zaitu.getGoodsTotalPrice()));
		zaituForm.setFreightTotal(String.valueOf(zaitu.getFreightTotal()));
		zaituForm.setDaohuoFlg(zaitu.isDaohuoFlg());
		zaituForm.setDaohuoDate(zaitu.getDaohuoDate());
		zaituForm.setBeizhu(zaitu.getBeizhu());
	}

	public void registerZaitu() {
		boolean result = zaituService.insert(getZaitu());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateZaitu() throws SQLException {
		boolean result = zaituService.update(getZaitu());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteZaitu() {

		if (selectedZaituList == null || selectedZaituList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的在途信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedZaituList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedZaituList.size(); i++) {
			Zaitu zaitu = selectedZaituList.get(i);

			ids[i] = zaitu.getId();
			postNameList.add(zaitu.getZaituDateString());
		}

		boolean result = zaituService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("在途[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("在途[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaitu delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerZaituResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateZaituResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("在途信息%s成功。", optStr);
		} else {
			resultStr = String.format("在途信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private Zaitu getZaitu() {

		Zaitu zaitu = new Zaitu();

		if(!Strings.isNullOrEmpty(zaituForm.getId())) {
			zaitu.setId(Integer.valueOf(zaituForm.getId()));
		}

		if (zaituForm.getZaituDate() != null) {
			zaitu.setZaituDate(new Timestamp(zaituForm.getZaituDate().getTime()));
		}

		if(!Strings.isNullOrEmpty(zaituForm.getGoodsTotalCount())) {
			zaitu.setGoodsTotalCount(Integer.valueOf(zaituForm.getGoodsTotalCount()));
		}

		if(!Strings.isNullOrEmpty(zaituForm.getGoodsTotalPrice())) {
			zaitu.setGoodsTotalPrice(Double.valueOf(zaituForm.getGoodsTotalPrice()));
		}

		if(!Strings.isNullOrEmpty(zaituForm.getFreightTotal())) {
			zaitu.setFreightTotal(Double.valueOf(zaituForm.getFreightTotal()));
		}

		zaitu.setDaohuoFlg(zaituForm.isDaohuoFlg());

		if(zaituForm.getDaohuoDate() != null) {
			zaitu.setDaohuoDate(new Timestamp(zaituForm.getDaohuoDate().getTime()));
		}

		zaitu.setBeizhu(zaituForm.getBeizhu());

		Timestamp currentDateTime = DateUtil.getCurrentDateTime();

		if(Strings.isNullOrEmpty(zaituForm.getId())) {
			zaitu.setRgtOpt("admin");
			zaitu.setRgtDate(currentDateTime);
		}

		zaitu.setUpdOpt("admin");
		zaitu.setUpdDate(currentDateTime);

		return zaitu;
	}

	public LazyDataModel<Zaitu> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Zaitu> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public ZaituServiceImpl getZaituService() {
		return zaituService;
	}

	public void setZaituService(ZaituServiceImpl zaituService) {
		this.zaituService = zaituService;
	}

	public ZaituSearch getZaituSearch() {
		return zaituSearch;
	}

	public void setZaituSearch(ZaituSearch zaituSearch) {
		this.zaituSearch = zaituSearch;
	}

	public ZaituForm getZaituForm() {
		return zaituForm;
	}

	public void setZaituForm(ZaituForm zaituForm) {
		this.zaituForm = zaituForm;
	}

	public List<Zaitu> getSelectedZaituList() {
		return selectedZaituList;
	}

	public void setSelectedZaituList(List<Zaitu> selectedZaituList) {
		this.selectedZaituList = selectedZaituList;
	}

}