package jp.co.asahi.bean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import jp.co.asahi.cache.CacheDataBean;
import jp.co.asahi.lazy.LazyWeiboZhuanfaDataModel;
import jp.co.asahi.model.WeiboZhuanfa;
import jp.co.asahi.model.form.WeiboZhuanfaForm;
import jp.co.asahi.model.search.WeiboZhuanfaSearch;
import jp.co.asahi.service.impl.WeiboZhuanfaServiceImpl;

@javax.faces.bean.ManagedBean(name = "weiboZhuanfaBean")
@SessionScoped
public class WeiboZhuanfaBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<WeiboZhuanfa> lazyModel;

    @ManagedProperty("#{weiboZhuanfaServiceImpl}")
    private WeiboZhuanfaServiceImpl weiboZhuanfaService;

    @ManagedProperty("#{weiboZhuanfaSearch}")
    private WeiboZhuanfaSearch weiboZhuanfaSearch;

    @ManagedProperty("#{weiboZhuanfaForm}")
    private WeiboZhuanfaForm weiboZhuanfaForm;

    @ManagedProperty("#{cacheDataBean}")
    private CacheDataBean cacheData;

    @PostConstruct
    public void init() {
    	searchWeiboZhuanfaSync();
    }

	public void searchWeiboZhuanfaSync() {
		lazyModel = new LazyWeiboZhuanfaDataModel(weiboZhuanfaSearch);
	}

	public void viewWeiboZhuanfa(WeiboZhuanfa weiboZhuanfa) throws SQLException {

		setInitValues(weiboZhuanfa);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 320);
        options.put("contentHeight", 200);

        RequestContext.getCurrentInstance().openDialog("weibo-zhuanfa", options, null);
    }

	private void setInitValues(WeiboZhuanfa weiboZhuanfa) throws SQLException {
		weiboZhuanfaForm.setId(String.valueOf(weiboZhuanfa.getId()));
		weiboZhuanfaForm.setWeiboId(weiboZhuanfa.getWeiboId());
		weiboZhuanfaForm.setWeiboName(weiboZhuanfa.getWeiboName());
		weiboZhuanfaForm.setZhuanfaCount(String.valueOf(weiboZhuanfa.getZhuanfaCount()));
	}

	public void updateWeiboZhuanfaResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("微博转发信息%s成功。", optStr);
		} else {
			resultStr = String.format("微博转发信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public LazyDataModel<WeiboZhuanfa> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<WeiboZhuanfa> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public WeiboZhuanfaServiceImpl getWeiboZhuanfaService() {
		return weiboZhuanfaService;
	}

	public void setWeiboZhuanfaService(WeiboZhuanfaServiceImpl weiboZhuanfaService) {
		this.weiboZhuanfaService = weiboZhuanfaService;
	}

	public WeiboZhuanfaSearch getWeiboZhuanfaSearch() {
		return weiboZhuanfaSearch;
	}

	public void setWeiboZhuanfaSearch(WeiboZhuanfaSearch weiboZhuanfaSearch) {
		this.weiboZhuanfaSearch = weiboZhuanfaSearch;
	}

	public WeiboZhuanfaForm getWeiboZhuanfaForm() {
		return weiboZhuanfaForm;
	}

	public void setWeiboZhuanfaForm(WeiboZhuanfaForm weiboZhuanfaForm) {
		this.weiboZhuanfaForm = weiboZhuanfaForm;
	}

	public CacheDataBean getCacheData() {
		return cacheData;
	}

	public void setCacheData(CacheDataBean cacheData) {
		this.cacheData = cacheData;
	}

}