package jp.co.asahi.cache;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;

import jp.co.asahi.cache.CacheManager.CacheKey;
import jp.co.asahi.model.Daili;
import jp.co.asahi.model.Dictionary;
import jp.co.asahi.model.Goods;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.myenum.DictionaryTypeEnum;
import jp.co.asahi.service.impl.DailiServiceImpl;
import jp.co.asahi.service.impl.DictionaryServiceImpl;
import jp.co.asahi.service.impl.GoodsServiceImpl;
import jp.co.asahi.service.impl.ZaituServiceImpl;
import jp.co.asahi.util.DateUtil;

@javax.faces.bean.ManagedBean(name = "cacheDataBean")
@ApplicationScoped
public class CacheDataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public CacheDataBean() {
		getGradeList();
		getZaituList();
		getDailiSelectItemList();
		getGoodsSelectItemList();

		getPifuTypeSelectItemList();
		getNianlingcengSelectItemList();
		getGoodsTypeSelectItemList();
		getJiageduanSelectItemList();
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

	public List<SelectItem> getDailiSelectItemList() {
		List<SelectItem> dailiSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.DailiSelectItemList);
		if(dailiSelectItemList == null){

	        dailiSelectItemList = new ArrayList<SelectItem>();

			DailiServiceImpl service = new DailiServiceImpl();
			List<Daili> tmpDailiList = null;

			try {
				tmpDailiList = service.getDailiList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Daili daili : tmpDailiList) {
				dailiSelectItemList.add(new SelectItem(daili.getId(), daili.getName()));
			}

			if(dailiSelectItemList != null && !dailiSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.DailiSelectItemList, dailiSelectItemList);
			}
		}
		return dailiSelectItemList;
	}

	public void setDailiSelectItemList(List<SelectItem> dailiSelectItemList) {
		CacheManager.set(CacheKey.DailiSelectItemList, dailiSelectItemList);
	}

	public List<SelectItem> getGoodsSelectItemList() {
		List<SelectItem> goodsSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.GoodsSelectItemList);
		if(goodsSelectItemList == null){

	        goodsSelectItemList = new ArrayList<SelectItem>();

			GoodsServiceImpl service = new GoodsServiceImpl();
			List<Goods> tmpGoodsList = null;

			try {
				tmpGoodsList = service.getGoodsList();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Goods goods : tmpGoodsList) {
				goodsSelectItemList.add(new SelectItem(goods.getId(), goods.getName()));
			}

			if(goodsSelectItemList != null && !goodsSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.GoodsSelectItemList, goodsSelectItemList);
			}
		}
		return goodsSelectItemList;
	}

	public void setGoodsSelectItemList(
			List<SelectItem> goodsSelectItemList) {
		CacheManager.set(CacheKey.GoodsSelectItemList, goodsSelectItemList);
	}



	public List<SelectItem> getPifuTypeSelectItemList() {
		List<SelectItem> pifuTypeSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.PifuTypeSelectItemList);
		if(pifuTypeSelectItemList == null){

	        pifuTypeSelectItemList = new ArrayList<SelectItem>();

			DictionaryServiceImpl service = new DictionaryServiceImpl();
			List<Dictionary> tmpPifuTypeList = null;

			try {
				tmpPifuTypeList = service.getDictionaryList(DictionaryTypeEnum.PIFU_TYPE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Dictionary pifuType : tmpPifuTypeList) {
				pifuTypeSelectItemList.add(new SelectItem(pifuType.getId(), pifuType.getName()));
			}

			if(pifuTypeSelectItemList != null && !pifuTypeSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.PifuTypeSelectItemList, pifuTypeSelectItemList);
			}
		}
		return pifuTypeSelectItemList;
	}

	public void setPifuTypeSelectItemList(
			List<SelectItem> pifuTypeSelectItemList) {
		CacheManager.set(CacheKey.PifuTypeSelectItemList, pifuTypeSelectItemList);
	}

	public List<SelectItem> getNianlingcengSelectItemList() {
		List<SelectItem> nianlingcengSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.NianlingcengSelectItemList);
		if(nianlingcengSelectItemList == null){

	        nianlingcengSelectItemList = new ArrayList<SelectItem>();

			DictionaryServiceImpl service = new DictionaryServiceImpl();
			List<Dictionary> tmpNianlingcengList = null;

			try {
				tmpNianlingcengList = service.getDictionaryList(DictionaryTypeEnum.NIANLINGCENG);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Dictionary nianlingceng : tmpNianlingcengList) {
				nianlingcengSelectItemList.add(new SelectItem(nianlingceng.getId(), nianlingceng.getName()));
			}

			if(nianlingcengSelectItemList != null && !nianlingcengSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.NianlingcengSelectItemList, nianlingcengSelectItemList);
			}
		}
		return nianlingcengSelectItemList;
	}

	public void setNianlingcengSelectItemList(
			List<SelectItem> nianlingcengSelectItemList) {
		CacheManager.set(CacheKey.NianlingcengSelectItemList, nianlingcengSelectItemList);
	}

	public List<SelectItem> getGoodsTypeSelectItemList() {
		List<SelectItem> goodsTypeSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.GoodsTypeSelectItemList);
		if(goodsTypeSelectItemList == null){

	        goodsTypeSelectItemList = new ArrayList<SelectItem>();

			DictionaryServiceImpl service = new DictionaryServiceImpl();
			List<Dictionary> tmpGoodsTypeList = null;

			try {
				tmpGoodsTypeList = service.getDictionaryList(DictionaryTypeEnum.GOODS_TYPE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Dictionary goodsType : tmpGoodsTypeList) {
				goodsTypeSelectItemList.add(new SelectItem(goodsType.getId(), goodsType.getName()));
			}

			if(goodsTypeSelectItemList != null && !goodsTypeSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.GoodsTypeSelectItemList, goodsTypeSelectItemList);
			}
		}
		return goodsTypeSelectItemList;
	}

	public void setGoodsTypeSelectItemList(
			List<SelectItem> goodsTypeSelectItemList) {
		CacheManager.set(CacheKey.GoodsTypeSelectItemList, goodsTypeSelectItemList);
	}

	public List<SelectItem> getJiageduanSelectItemList() {
		List<SelectItem> jiageduanSelectItemList = (List<SelectItem>) CacheManager.get(CacheKey.JiageduanSelectItemList);
		if(jiageduanSelectItemList == null){

	        jiageduanSelectItemList = new ArrayList<SelectItem>();

			DictionaryServiceImpl service = new DictionaryServiceImpl();
			List<Dictionary> tmpJiageduanList = null;

			try {
				tmpJiageduanList = service.getDictionaryList(DictionaryTypeEnum.JIAGEDUAN);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Dictionary jiageduan : tmpJiageduanList) {
				jiageduanSelectItemList.add(new SelectItem(jiageduan.getId(), jiageduan.getName()));
			}

			if(jiageduanSelectItemList != null && !jiageduanSelectItemList.isEmpty()){
				CacheManager.set(CacheKey.JiageduanSelectItemList, jiageduanSelectItemList);
			}
		}
		return jiageduanSelectItemList;
	}

	public void setJiageduanSelectItemList(
			List<SelectItem> jiageduanSelectItemList) {
		CacheManager.set(CacheKey.JiageduanSelectItemList, jiageduanSelectItemList);
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
