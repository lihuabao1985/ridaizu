package jp.co.asahi.cache;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;

import jp.co.asahi.cache.CacheManager.CacheKey;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.service.impl.ZaituServiceImpl;
import jp.co.asahi.util.DateUtil;

@javax.faces.bean.ManagedBean(name = "cacheDataBean")
@ApplicationScoped
public class CacheDataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public CacheDataBean() {
		getGradeList();
		getZaituList();
	}


	public List<SelectItem> getGradeList() {
		List<SelectItem> selectItemList = (List<SelectItem>) CacheManager.get(CacheKey.GradeList);
		if(selectItemList == null){
	        selectItemList = new ArrayList<SelectItem>();
	        selectItemList.add(new SelectItem("普通会员", "普通会员"));
			selectItemList.add(new SelectItem("高级会员", "高级会员"));
			selectItemList.add(new SelectItem("VIP会员", "VIP会员"));
			selectItemList.add(new SelectItem("至尊VIP会员", "至尊VIP会员"));

			CacheManager.set(CacheKey.PaymentTypeSelectItemList, selectItemList);
		}
		return selectItemList;
	}

	public List<SelectItem> getZaituList() {
		List<SelectItem> selectItemList = (List<SelectItem>) CacheManager.get(CacheKey.ZaituSelectItemList);
		if(selectItemList == null){

	        selectItemList = new ArrayList<SelectItem>();

			ZaituServiceImpl service = new ZaituServiceImpl();
			List<Zaitu> tmpZaituList = null;

			try {
				tmpZaituList = service.getZaituList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Zaitu zaitu : tmpZaituList) {
				selectItemList.add(new SelectItem(zaitu.getId(), DateUtil.dateToString(zaitu.getZaituDate(), DateUtil.SHORT_DATE_HYPHEN)));
			}

			if(selectItemList != null && !selectItemList.isEmpty()){
				CacheManager.set(CacheKey.ZaituSelectItemList, selectItemList);
			}
		}
		return selectItemList;
	}






	/*
	public List<String> getConsigneeCorList() {
		List<String> consigneeCorList = (List<String>) CacheManager.get(CacheKey.ConsigneeCorList);
		if(consigneeCorList == null){
			ConsigneeServiceImpl service = new ConsigneeServiceImpl();

			try {
				consigneeCorList = service.getCneeCorList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(consigneeCorList != null && !consigneeCorList.isEmpty()){
				CacheManager.set(CacheKey.ConsigneeCorList, consigneeCorList);
			}
		}
		return consigneeCorList;
	}

	public List<String> getCorpOceanList() {
		List<String> corpOceanList = (List<String>) CacheManager.get(CacheKey.CorpOceanList);
		if(corpOceanList == null){
			ArrivalNoticeServiceImpl service = new ArrivalNoticeServiceImpl();

			try {
				corpOceanList = service.getCorpOceanList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(corpOceanList != null && !corpOceanList.isEmpty()){
				CacheManager.set(CacheKey.CorpOceanList, corpOceanList);
			}
		}
		return corpOceanList;
	}

	public void setConsigneeCorList(List<String> consigneeCorList) {
		CacheManager.set(CacheKey.ConsigneeCorList, consigneeCorList);
	}

	public List<String> getCorjpNoList() {
		List<String> corjpNoList = (List<String>) CacheManager.get(CacheKey.CorjpNoList);
		if(corjpNoList == null){
			AgencyServiceImpl service = new AgencyServiceImpl();

			try {
				corjpNoList = service.getCorjpNoList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(corjpNoList != null && !corjpNoList.isEmpty()){
				CacheManager.set(CacheKey.CorjpNoList, corjpNoList);
			}
		}
		return corjpNoList;
	}

	public void setCorjpNoList(List<String> corjpNoList) {
		CacheManager.set(CacheKey.CorjpNoList, corjpNoList);
	}

	public List<SelectItem> getContainerSizeSelectItemList() {
		List<SelectItem> containerSizeSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.ContainerSizeSelectItemList);
		if(containerSizeSelectItemList == null){

	        containerSizeSelectItemList = new ArrayList<SelectItem>();

			ContainerSizeServiceImpl service = new ContainerSizeServiceImpl();
			List<ContainerSize> tmpContainerSizeList = null;

			try {
				tmpContainerSizeList = service.getContainerSizeList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (ContainerSize containerSize : tmpContainerSizeList) {
				containerSizeSelectItemList.add(new SelectItem(containerSize.getCsize(), containerSize.getCsize()));
			}

			if(containerSizeSelectItemList != null && !containerSizeSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.ContainerSizeSelectItemList, containerSizeSelectItemList);
			}
		}
		return containerSizeSelectItemList;
	}

	public void setContainerSizeSelectItemList(
			List<SelectItem> containerSizeSelectItemList) {
		CacheManager.set(CacheKey.ContainerSizeSelectItemList, containerSizeSelectItemList);
	}

	public List<SelectItem> getCurrencyTypeSelectItemList() {
		List<SelectItem> currencyTypeSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.CurrencyTypeSelectItemList);
		if(currencyTypeSelectItemList == null){

	        currencyTypeSelectItemList = new ArrayList<SelectItem>();

			CurrencyTypeServiceImpl service = new CurrencyTypeServiceImpl();
			List<CurrencyType> tmpCurrencyTypeList = null;

			try {
				tmpCurrencyTypeList = service.getCurrencyTypeList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (CurrencyType currencyType : tmpCurrencyTypeList) {
				currencyTypeSelectItemList.add(new SelectItem(currencyType.getTypeName(), currencyType.getTypeName()));
			}

			if(currencyTypeSelectItemList != null && !currencyTypeSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.CurrencyTypeSelectItemList, currencyTypeSelectItemList);
			}
		}
		return currencyTypeSelectItemList;
	}

	public void setCurrencyTypeSelectItemList(List<SelectItem> currencyTypeSelectItemList) {
		CacheManager.set(CacheKey.CurrencyTypeSelectItemList, currencyTypeSelectItemList);
	}

	public List<SelectItem> getPaymentTypeSelectItemList() {
		List<SelectItem> paymentTypeSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.PaymentTypeSelectItemList);
		if(paymentTypeSelectItemList == null){

	        paymentTypeSelectItemList = new ArrayList<SelectItem>();

			PaymentTypeServiceImpl service = new PaymentTypeServiceImpl();
			List<PaymentType> tmpPaymentTypeList = null;

			try {
				tmpPaymentTypeList = service.getPaymentTypeList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (PaymentType paymentType : tmpPaymentTypeList) {
				paymentTypeSelectItemList.add(new SelectItem(paymentType.getTypeName(), paymentType.getTypeName()));
			}

			if(paymentTypeSelectItemList != null && !paymentTypeSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.PaymentTypeSelectItemList, paymentTypeSelectItemList);
			}
		}
		return paymentTypeSelectItemList;
	}

	public void setPaymentTypeSelectItemList(List<SelectItem> paymentTypeSelectItemList) {
		CacheManager.set(CacheKey.PaymentTypeSelectItemList, paymentTypeSelectItemList);
	}
	*/
}
