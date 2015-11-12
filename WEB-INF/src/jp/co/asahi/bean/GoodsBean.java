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

import jp.co.asahi.lazy.LazyGoodsDataModel;
import jp.co.asahi.model.Goods;
import jp.co.asahi.model.form.GoodsForm;
import jp.co.asahi.model.search.GoodsSearch;
import jp.co.asahi.service.impl.GoodsServiceImpl;
import jp.co.asahi.util.DateUtil;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "goodsBean")
@SessionScoped
public class GoodsBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Goods> lazyModel;

    @ManagedProperty("#{goodsServiceImpl}")
    private GoodsServiceImpl goodsService;

    @ManagedProperty("#{goodsSearch}")
    private GoodsSearch goodsSearch;

    @ManagedProperty("#{goodsForm}")
    private GoodsForm goodsForm;

	private List<Goods> selectedGoodsList;

    @PostConstruct
    public void init() {
    	searchGoodsSync();
    }

	public void searchGoodsSync() {
		lazyModel = new LazyGoodsDataModel(goodsSearch);
	}

	public void viewRegisterGoods() {

		goodsForm.clearAll();

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 450);

        RequestContext.getCurrentInstance().openDialog("goods", options, null);
    }

	public void viewUpdateGoods(Goods goods) throws SQLException {

		setInitValues(goods);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 450);

        RequestContext.getCurrentInstance().openDialog("goods", options, null);
    }

	private void setInitValues(Goods goods) throws SQLException {
		goodsForm.setId(String.valueOf(goods.getId()));
		goodsForm.setName(goods.getName());
		goodsForm.setTaobaoName(goods.getTaobaoName());
		goodsForm.setXiaoshouShuxing(goods.getXiaoshouShuxing());
		goodsForm.setGoodsCode(goods.getGoodsCode());
		goodsForm.setBarcode(goods.getBarcode());
		goodsForm.setGoodsTypeId(String.valueOf(goods.getGoodsTypeId()));
		goodsForm.setCapacity(goods.getCapacity());
		goodsForm.setWeight(String.valueOf(goods.getWeight()));
		goodsForm.setChengbenjia(String.valueOf(goods.getChengbenjia()));
		goodsForm.setWholesalePrice(String.valueOf(goods.getWholesalePrice()));
		goodsForm.setPrice(String.valueOf(goods.getPrice()));
		goodsForm.setSalesPrice(String.valueOf(goods.getSalesPrice()));
		goodsForm.setSummary(goods.getSummary());
		goodsForm.setDetail(goods.getDetail());
		goodsForm.setIngredient(goods.getIngredient());
		goodsForm.setEffect(goods.getEffect());
		goodsForm.setNianlingcengId(String.valueOf(goods.getNianlingcengId()));
		goodsForm.setInstructions(goods.getInstructions());
		goodsForm.setPrecautions(goods.getPrecautions());
		goodsForm.setGoodsUrl(goods.getGoodsUrl());
		goodsForm.setPhotoUrl(goods.getPhotoUrl());
		goodsForm.setThumbnailsUrl(goods.getThumbnailsUrl());
		goodsForm.setDelFlg(goods.isDelFlg());

	}

	public void registerGoods() {
		boolean result = goodsService.insert(getGoods());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateGoods() throws SQLException {
		boolean result = goodsService.update(getGoods());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteGoods() {

		if (selectedGoodsList == null || selectedGoodsList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的商品信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedGoodsList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedGoodsList.size(); i++) {
			Goods goods = selectedGoodsList.get(i);

			ids[i] = goods.getId();
			postNameList.add(goods.getName());
		}

		boolean result = goodsService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("商品[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("商品[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Goods delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerGoodsResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateGoodsResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("商品信息%s成功。", optStr);
		} else {
			resultStr = String.format("商品信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private Goods getGoods() {

		Goods goods = new Goods();
		if(!Strings.isNullOrEmpty(goodsForm.getId())) {
			goods.setId(Integer.valueOf(goodsForm.getId()));
		}
		goods.setName(goodsForm.getName());
		goods.setTaobaoName(goodsForm.getTaobaoName());
		goods.setXiaoshouShuxing(goodsForm.getXiaoshouShuxing());
		goods.setGoodsCode(goodsForm.getGoodsCode());
		goods.setBarcode(goodsForm.getBarcode());
		if (goodsForm.getGoodsTypeId() != null) {
			goods.setGoodsTypeId(Integer.valueOf(goodsForm.getGoodsTypeId()));
		}

		goods.setCapacity(goodsForm.getCapacity());
		if (goodsForm.getWeight() != null) {
			goods.setWeight(Integer.valueOf(goodsForm.getWeight()));
		}

		if (goodsForm.getChengbenjia() != null) {
			goods.setChengbenjia(Double.valueOf(goodsForm.getChengbenjia()));
		}

		if (goodsForm.getWholesalePrice() != null) {
			goods.setWholesalePrice(Double.valueOf(goodsForm.getWholesalePrice()));
		}

		if (goodsForm.getPrice() != null) {
			goods.setPrice(Double.valueOf(goodsForm.getPrice()));
		}

		if (goodsForm.getSalesPrice() != null) {
			goods.setSalesPrice(Double.valueOf(goodsForm.getSalesPrice()));
		}

		goods.setSummary(goodsForm.getSummary());
		goods.setDetail(goodsForm.getDetail());
		goods.setIngredient(goodsForm.getIngredient());
		goods.setEffect(goodsForm.getEffect());
		if (goodsForm.getNianlingcengId() != null) {
			goods.setNianlingcengId(Integer.valueOf(goodsForm.getNianlingcengId()));
		}

		goods.setInstructions(goodsForm.getInstructions());
		goods.setPrecautions(goodsForm.getPrecautions());
		goods.setGoodsUrl(goodsForm.getGoodsUrl());
		goods.setPhotoUrl(goodsForm.getPhotoUrl());
		goods.setThumbnailsUrl(goodsForm.getThumbnailsUrl());
		goods.setDelFlg(goodsForm.isDelFlg());

		Timestamp currentDateTime = DateUtil.getCurrentDateTime();

		if(Strings.isNullOrEmpty(goodsForm.getId())) {
			goods.setRgtOpt("admin");
			goods.setRgtDate(currentDateTime);
		}

		goods.setUpdOpt("admin");
		goods.setUpdDate(currentDateTime);

		return goods;
	}

	public LazyDataModel<Goods> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Goods> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public GoodsServiceImpl getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsServiceImpl goodsService) {
		this.goodsService = goodsService;
	}

	public GoodsSearch getGoodsSearch() {
		return goodsSearch;
	}

	public void setGoodsSearch(GoodsSearch goodsSearch) {
		this.goodsSearch = goodsSearch;
	}

	public GoodsForm getGoodsForm() {
		return goodsForm;
	}

	public void setGoodsForm(GoodsForm goodsForm) {
		this.goodsForm = goodsForm;
	}

	public List<Goods> getSelectedGoodsList() {
		return selectedGoodsList;
	}

	public void setSelectedGoodsList(List<Goods> selectedGoodsList) {
		this.selectedGoodsList = selectedGoodsList;
	}

}