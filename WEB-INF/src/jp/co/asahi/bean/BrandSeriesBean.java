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

import jp.co.asahi.lazy.LazyBrandSeriesDataModel;
import jp.co.asahi.model.BrandSeries;
import jp.co.asahi.model.form.BrandSeriesForm;
import jp.co.asahi.model.search.BrandSeriesSearch;
import jp.co.asahi.service.impl.BrandSeriesServiceImpl;
import jp.co.asahi.util.DateUtil;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "brandSeriesBean")
@SessionScoped
public class BrandSeriesBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<BrandSeries> lazyModel;

    @ManagedProperty("#{brandSeriesServiceImpl}")
    private BrandSeriesServiceImpl brandSeriesService;

    @ManagedProperty("#{brandSeriesSearch}")
    private BrandSeriesSearch brandSeriesSearch;

    @ManagedProperty("#{brandSeriesForm}")
    private BrandSeriesForm brandSeriesForm;

	private List<BrandSeries> selectedBrandSeriesList;

    @PostConstruct
    public void init() {
    	searchBrandSeriesSync();
    }

	public void searchBrandSeriesSync() {
		lazyModel = new LazyBrandSeriesDataModel(brandSeriesSearch);
	}

	public void viewRegisterBrandSeries() {

		brandSeriesForm.clearAll();

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 330);

        RequestContext.getCurrentInstance().openDialog("brand-series", options, null);
    }

	public void viewUpdateBrandSeries(BrandSeries brandSeries) throws SQLException {

		setInitValues(brandSeries);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 365);

        RequestContext.getCurrentInstance().openDialog("brand-series", options, null);
    }

	private void setInitValues(BrandSeries brandSeries) throws SQLException {
		brandSeriesForm.setId(String.valueOf(brandSeries.getId()));
		brandSeriesForm.setName(brandSeries.getName());
		brandSeriesForm.setSummary(brandSeries.getSummary());
		brandSeriesForm.setDetail(brandSeries.getDetail());
		brandSeriesForm.setHomepage(brandSeries.getHomepage());
		brandSeriesForm.setPhotoUrl(brandSeries.getPhotoUrl());
		brandSeriesForm.setThumbnailsUrl(brandSeries.getThumbnailsUrl());
		brandSeriesForm.setDelFlg(brandSeries.isDelFlg());
	}

	public void registerBrandSeries() {
		boolean result = brandSeriesService.insert(getBrandSeries());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateBrandSeries() throws SQLException {
		boolean result = brandSeriesService.update(getBrandSeries());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteBrandSeries() {

		if (selectedBrandSeriesList == null || selectedBrandSeriesList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的品牌系列信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedBrandSeriesList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedBrandSeriesList.size(); i++) {
			BrandSeries brandSeries = selectedBrandSeriesList.get(i);

			ids[i] = brandSeries.getId();
			postNameList.add(brandSeries.getName());
		}

		boolean result = brandSeriesService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("品牌系列[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("品牌系列[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "BrandSeries delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerBrandSeriesResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateBrandSeriesResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("品牌系列信息%s成功。", optStr);
		} else {
			resultStr = String.format("品牌系列信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private BrandSeries getBrandSeries() {

		BrandSeries brandSeries = new BrandSeries();

		if(!Strings.isNullOrEmpty(brandSeriesForm.getId())) {
			brandSeries.setId(Integer.valueOf(brandSeriesForm.getId()));
		}
		brandSeries.setName(brandSeriesForm.getName());
		brandSeries.setSummary(brandSeriesForm.getSummary());
		brandSeries.setDetail(brandSeriesForm.getDetail());
		brandSeries.setHomepage(brandSeriesForm.getHomepage());
		brandSeries.setPhotoUrl(brandSeriesForm.getPhotoUrl());
		brandSeries.setThumbnailsUrl(brandSeriesForm.getThumbnailsUrl());
		brandSeries.setDelFlg(brandSeriesForm.isDelFlg());

		Timestamp currentDateTime = DateUtil.getCurrentDateTime();

		if(Strings.isNullOrEmpty(brandSeriesForm.getId())) {
			brandSeries.setRgtOpt("admin");
			brandSeries.setRgtDate(currentDateTime);
		}

		brandSeries.setUpdOpt("admin");
		brandSeries.setUpdDate(currentDateTime);

		return brandSeries;
	}

	public LazyDataModel<BrandSeries> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<BrandSeries> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public BrandSeriesServiceImpl getBrandSeriesService() {
		return brandSeriesService;
	}

	public void setBrandSeriesService(BrandSeriesServiceImpl brandSeriesService) {
		this.brandSeriesService = brandSeriesService;
	}

	public BrandSeriesSearch getBrandSeriesSearch() {
		return brandSeriesSearch;
	}

	public void setBrandSeriesSearch(BrandSeriesSearch brandSeriesSearch) {
		this.brandSeriesSearch = brandSeriesSearch;
	}

	public BrandSeriesForm getBrandSeriesForm() {
		return brandSeriesForm;
	}

	public void setBrandSeriesForm(BrandSeriesForm brandSeriesForm) {
		this.brandSeriesForm = brandSeriesForm;
	}

	public List<BrandSeries> getSelectedBrandSeriesList() {
		return selectedBrandSeriesList;
	}

	public void setSelectedBrandSeriesList(List<BrandSeries> selectedBrandSeriesList) {
		this.selectedBrandSeriesList = selectedBrandSeriesList;
	}

}