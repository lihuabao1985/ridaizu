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

import jp.co.asahi.lazy.LazyBrandDataModel;
import jp.co.asahi.model.Brand;
import jp.co.asahi.model.form.BrandForm;
import jp.co.asahi.model.search.BrandSearch;
import jp.co.asahi.service.impl.BrandServiceImpl;
import jp.co.asahi.util.DateUtil;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "brandBean")
@SessionScoped
public class BrandBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Brand> lazyModel;

    @ManagedProperty("#{brandServiceImpl}")
    private BrandServiceImpl brandService;

    @ManagedProperty("#{brandSearch}")
    private BrandSearch brandSearch;

    @ManagedProperty("#{brandForm}")
    private BrandForm brandForm;

	private List<Brand> selectedBrandList;

    @PostConstruct
    public void init() {
    	searchBrandSync();
    }

	public void searchBrandSync() {
		lazyModel = new LazyBrandDataModel(brandSearch);
	}

	public void viewRegisterBrand() {

		brandForm.clearAll();

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 330);

        RequestContext.getCurrentInstance().openDialog("brand", options, null);
    }

	public void viewUpdateBrand(Brand brand) throws SQLException {

		setInitValues(brand);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 330);
        options.put("contentHeight", 365);

        RequestContext.getCurrentInstance().openDialog("brand", options, null);
    }

	private void setInitValues(Brand brand) throws SQLException {
		brandForm.setId(String.valueOf(brand.getId()));
		brandForm.setName(brand.getName());
		brandForm.setSummary(brand.getSummary());
		brandForm.setDetail(brand.getDetail());
		brandForm.setHomepage(brand.getHomepage());
		brandForm.setPhotoUrl(brand.getPhotoUrl());
		brandForm.setThumbnailsUrl(brand.getThumbnailsUrl());
		brandForm.setDelFlg(brand.isDelFlg());
	}

	public void registerBrand() {
		boolean result = brandService.insert(getBrand());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateBrand() throws SQLException {
		boolean result = brandService.update(getBrand());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteBrand() {

		if (selectedBrandList == null || selectedBrandList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的品牌信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedBrandList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedBrandList.size(); i++) {
			Brand brand = selectedBrandList.get(i);

			ids[i] = brand.getId();
			postNameList.add(brand.getName());
		}

		boolean result = brandService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("品牌[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("品牌[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Brand delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerBrandResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateBrandResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("品牌信息%s成功。", optStr);
		} else {
			resultStr = String.format("品牌信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private Brand getBrand() {

		Brand brand = new Brand();

		if(!Strings.isNullOrEmpty(brandForm.getId())) {
			brand.setId(Integer.valueOf(brandForm.getId()));
		}
		brand.setName(brandForm.getName());
		brand.setSummary(brandForm.getSummary());
		brand.setDetail(brandForm.getDetail());
		brand.setHomepage(brandForm.getHomepage());
		brand.setPhotoUrl(brandForm.getPhotoUrl());
		brand.setThumbnailsUrl(brandForm.getThumbnailsUrl());
		brand.setDelFlg(brandForm.isDelFlg());

		Timestamp currentDateTime = DateUtil.getCurrentDateTime();

		if(Strings.isNullOrEmpty(brandForm.getId())) {
			brand.setRgtOpt("admin");
			brand.setRgtDate(currentDateTime);
		}

		brand.setUpdOpt("admin");
		brand.setUpdDate(currentDateTime);

		return brand;
	}

	public LazyDataModel<Brand> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Brand> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public BrandServiceImpl getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandServiceImpl brandService) {
		this.brandService = brandService;
	}

	public BrandSearch getBrandSearch() {
		return brandSearch;
	}

	public void setBrandSearch(BrandSearch brandSearch) {
		this.brandSearch = brandSearch;
	}

	public BrandForm getBrandForm() {
		return brandForm;
	}

	public void setBrandForm(BrandForm brandForm) {
		this.brandForm = brandForm;
	}

	public List<Brand> getSelectedBrandList() {
		return selectedBrandList;
	}

	public void setSelectedBrandList(List<Brand> selectedBrandList) {
		this.selectedBrandList = selectedBrandList;
	}

}