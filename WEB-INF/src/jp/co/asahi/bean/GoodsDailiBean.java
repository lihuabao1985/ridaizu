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
import jp.co.asahi.dao.db.DBManager;
import jp.co.asahi.lazy.LazyGoodsDailiDataModel;
import jp.co.asahi.model.Goods;
import jp.co.asahi.model.GoodsDaili;
import jp.co.asahi.model.form.GoodsDailiForm;
import jp.co.asahi.model.search.GoodsDailiSearch;
import jp.co.asahi.service.impl.GoodsDailiServiceImpl;
import jp.co.asahi.service.impl.GoodsServiceImpl;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "goodsDailiBean")
@SessionScoped
public class GoodsDailiBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<GoodsDaili> lazyModel;

    @ManagedProperty("#{goodsDailiServiceImpl}")
    private GoodsDailiServiceImpl goodsDailiService;

    @ManagedProperty("#{goodsServiceImpl}")
    private GoodsServiceImpl goodsService;

    @ManagedProperty("#{goodsDailiSearch}")
    private GoodsDailiSearch goodsDailiSearch;

    @ManagedProperty("#{goodsDailiForm}")
    private GoodsDailiForm goodsDailiForm;

	private List<GoodsDaili> selectedGoodsDailiList;

    @ManagedProperty("#{cacheDataBean}")
    private CacheDataBean cacheData;

    @PostConstruct
    public void init() {
    	searchGoodsDailiSync();
    }

	public void searchGoodsDailiSync() {
		lazyModel = new LazyGoodsDailiDataModel(goodsDailiSearch);
	}

	public void viewRegisterGoodsDaili() {

		goodsDailiForm.clearAll();
		goodsDailiForm.setDailiSelectItemList(cacheData.getDailiSelectItemList());
		goodsDailiForm.setGoodsSelectItemList(cacheData.getGoodsSelectItemList());

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 530);
        options.put("contentHeight", 330);

        RequestContext.getCurrentInstance().openDialog("goods-daili", options, null);
    }

	public void viewRegisterAllGoodsDaili() {

		goodsDailiForm.clearAll();
		goodsDailiForm.setDailiSelectItemList(cacheData.getDailiSelectItemList());
		goodsDailiForm.setGoodsSelectItemList(cacheData.getGoodsSelectItemList());

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 300);
        options.put("contentHeight", 150);

        RequestContext.getCurrentInstance().openDialog("goods-daili-all", options, null);
    }

	public void viewUpdateGoodsDaili(GoodsDaili goodsDaili) throws SQLException {

		setInitValues(goodsDaili);

        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", 530);
        options.put("contentHeight", 365);

        RequestContext.getCurrentInstance().openDialog("goods-daili", options, null);
    }

	private void setInitValues(GoodsDaili goodsDaili) throws SQLException {
		goodsDailiForm.setId(String.valueOf(goodsDaili.getId()));
		goodsDailiForm.setDailiId(String.valueOf(goodsDaili.getDailiId()));
		goodsDailiForm.setGoodsId(String.valueOf(goodsDaili.getGoodsId()));
		goodsDailiForm.setMinCount(String.valueOf(goodsDaili.getMinCount()));
		goodsDailiForm.setPrice(String.valueOf(goodsDaili.getPrice()));
		goodsDailiForm.setDailiSelectItemList(cacheData.getDailiSelectItemList());
		goodsDailiForm.setGoodsSelectItemList(cacheData.getGoodsSelectItemList());
	}

	public void registerGoodsDaili() {
		boolean result = goodsDailiService.insert(getGoodsDaili());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void registerAllGoodsDaili() {

		String dailiId = goodsDailiForm.getDailiId();

		if (Strings.isNullOrEmpty(dailiId)) {
			return ;
		}

		try {
			List<Goods> goodsList = goodsService.getGoodsList();
			if (goodsList == null || goodsList.size() == 0) {
				return ;
			}

			DBManager.beginTransaction();

			GoodsDaili goodsDaili = null;
			for (Goods goods : goodsList) {

				if (goodsDailiService.checkGoodsDaili(Integer.valueOf(dailiId), goods.getId())) {
					continue ;
				}

				goodsDaili = new GoodsDaili();
				goodsDaili.setDailiId(Integer.valueOf(dailiId));
				goodsDaili.setGoodsId(goods.getId());
				goodsDaili.setMinCount(1);
				goodsDaili.setPrice(goods.getWholesalePrice());

				if (!goodsDailiService.insert(goodsDaili)) {
					throw new SQLException("Insert goods daili info error.");
				}
			}

			DBManager.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			DBManager.rollbackTransaction();
		}

		RequestContext.getCurrentInstance().closeDialog(true);
    }

	public void updateGoodsDaili() throws SQLException {
		boolean result = goodsDailiService.update(getGoodsDaili());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteGoodsDaili() {

		if (selectedGoodsDailiList == null || selectedGoodsDailiList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的代理商品信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedGoodsDailiList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedGoodsDailiList.size(); i++) {
			GoodsDaili goodsDaili = selectedGoodsDailiList.get(i);

			ids[i] = goodsDaili.getId();
			postNameList.add(goodsDaili.getGoodsName());
		}

		boolean result = goodsDailiService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("代理商品[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("代理商品[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "GoodsDaili delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerGoodsDailiResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateGoodsDailiResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("代理商品信息%s成功。", optStr);
		} else {
			resultStr = String.format("代理商品信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private GoodsDaili getGoodsDaili() {

		GoodsDaili goodsDaili = new GoodsDaili();

		if(!Strings.isNullOrEmpty(goodsDailiForm.getId())) {
			goodsDaili.setId(Integer.valueOf(goodsDailiForm.getId()));
		}

		if(!Strings.isNullOrEmpty(goodsDailiForm.getDailiId())) {
			goodsDaili.setDailiId(Integer.valueOf(goodsDailiForm.getDailiId()));
		}

		if(!Strings.isNullOrEmpty(goodsDailiForm.getGoodsId())) {
			goodsDaili.setGoodsId(Integer.valueOf(goodsDailiForm.getGoodsId()));
		}

		if(!Strings.isNullOrEmpty(goodsDailiForm.getMinCount())) {
			goodsDaili.setMinCount(Integer.valueOf(goodsDailiForm.getMinCount()));
		}

		if(!Strings.isNullOrEmpty(goodsDailiForm.getPrice())) {
			goodsDaili.setPrice(Double.valueOf(goodsDailiForm.getPrice()));
		}

		return goodsDaili;
	}

	public LazyDataModel<GoodsDaili> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<GoodsDaili> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public GoodsDailiServiceImpl getGoodsDailiService() {
		return goodsDailiService;
	}

	public void setGoodsDailiService(GoodsDailiServiceImpl goodsDailiService) {
		this.goodsDailiService = goodsDailiService;
	}

	public GoodsDailiSearch getGoodsDailiSearch() {
		return goodsDailiSearch;
	}

	public void setGoodsDailiSearch(GoodsDailiSearch goodsDailiSearch) {
		this.goodsDailiSearch = goodsDailiSearch;
	}

	public GoodsDailiForm getGoodsDailiForm() {
		return goodsDailiForm;
	}

	public void setGoodsDailiForm(GoodsDailiForm goodsDailiForm) {
		this.goodsDailiForm = goodsDailiForm;
	}

	public List<GoodsDaili> getSelectedGoodsDailiList() {
		return selectedGoodsDailiList;
	}

	public void setSelectedGoodsDailiList(List<GoodsDaili> selectedGoodsDailiList) {
		this.selectedGoodsDailiList = selectedGoodsDailiList;
	}

	public CacheDataBean getCacheData() {
		return cacheData;
	}

	public void setCacheData(CacheDataBean cacheData) {
		this.cacheData = cacheData;
	}

	public GoodsServiceImpl getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(GoodsServiceImpl goodsService) {
		this.goodsService = goodsService;
	}

	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}