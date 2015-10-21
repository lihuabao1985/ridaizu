package jp.co.asahi.bean;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import jp.co.asahi.cache.CacheDataBean;
import jp.co.asahi.dao.db.DBManager;
import jp.co.asahi.lazy.LazyZaituDetailDataModel;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.ZaituDetail;
import jp.co.asahi.model.form.ZaituDetailForm;
import jp.co.asahi.model.search.ZaituDetailSearch;
import jp.co.asahi.service.impl.ZaituDetailServiceImpl;
import jp.co.asahi.service.impl.ZaituServiceImpl;
import jp.co.asahi.util.DateUtil;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@javax.faces.bean.ManagedBean(name = "zaituDetailBean")
@SessionScoped
public class ZaituDetailBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<ZaituDetail> lazyModel;

    @ManagedProperty("#{zaituServiceImpl}")
    private ZaituServiceImpl zaituService;

    @ManagedProperty("#{zaituDetailServiceImpl}")
    private ZaituDetailServiceImpl zaituDetailService;

    @ManagedProperty("#{zaituDetailSearch}")
    private ZaituDetailSearch zaituDetailSearch;

    @ManagedProperty("#{zaituDetailForm}")
    private ZaituDetailForm zaituDetailForm;

	private List<ZaituDetail> selectedZaituDetailList;


    private UploadedFile goodsFile;

    private Date zaituDate;

    @ManagedProperty("#{cacheDataBean}")
    private CacheDataBean cacheData;

    @PostConstruct
    public void init() {
    	searchZaituDetailSync();
    }

	public void searchZaituDetailSync() {
    	systemLogger.debug("Start searchZaituDetailSync.");
		lazyModel = new LazyZaituDetailDataModel(zaituDetailSearch);
    	systemLogger.debug("End searchZaituDetailSync.");
	}

	public void viewRegisterZaituDetail() {

		zaituDetailForm.clearAll();
		zaituDetailForm.setZaituSelectItemList(cacheData.getZaituList());

		view("zaitu-detail", 330, 300);
    }

	public void viewRegisterZaituDetailList() {
    	systemLogger.debug("Start viewRegisterZaituDetailList.");
		view("zaitu-detail-list", 310, 150);
    	systemLogger.debug("End viewRegisterZaituDetailList.");
    }

	public void viewRegisterZaituDetailCheck() {

		view("zaitu-detail-check", 420, 250);
    }

	public void viewUpdateZaituDetail(ZaituDetail zaituDetail) throws SQLException {

		setInitValues(zaituDetail);
		view("zaitu-detail", 330, 335);
    }

	public void view(String pageName, int contentWidth, int contentHeight){
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        options.put("contentWidth", contentWidth);
        options.put("contentHeight", contentHeight);

        RequestContext.getCurrentInstance().openDialog(pageName, options, null);
	}

	private void setInitValues(ZaituDetail zaituDetail) throws SQLException {
		zaituDetailForm.setId(String.valueOf(zaituDetail.getId()));
		zaituDetailForm.setZaituId(String.valueOf(zaituDetail.getZaituId()));
		zaituDetailForm.setGoodsBarcode(zaituDetail.getGoodsBarcode());
		zaituDetailForm.setCount(String.valueOf(zaituDetail.getCount()));
		zaituDetailForm.setPrice(String.valueOf(zaituDetail.getPrice()));
		zaituDetailForm.setBeizhu(zaituDetail.getBeizhu());
		zaituDetailForm.setZaituSelectItemList(cacheData.getZaituList());
	}

	public void registerZaituDetail() {
		boolean result = zaituDetailService.insert(getZaituDetail());

		RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void updateZaituDetail() throws SQLException {
		boolean result = zaituDetailService.update(getZaituDetail());

        RequestContext.getCurrentInstance().closeDialog(result);
    }

	public void deleteZaituDetail() {

		if (selectedZaituDetailList == null || selectedZaituDetailList.size() < 1) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "请选择要删除的在途商品信息。");
	        FacesContext.getCurrentInstance().addMessage(null, message);

	        return ;
		}

		int[] ids = new int[selectedZaituDetailList.size()];
		List<String> postNameList = new ArrayList<String> ();
		for (int i = 0; i < selectedZaituDetailList.size(); i++) {
			ZaituDetail zaituDetail = selectedZaituDetailList.get(i);

			ids[i] = zaituDetail.getId();
			postNameList.add(zaituDetail.getGoodsBarcode());
		}

		boolean result = zaituDetailService.delete(ids);

		String resultStr = null;
		if (result) {
			resultStr = String.format("在途商品[%s]删除成功。", Joiner.on(",").join(postNameList));
		} else {
			resultStr = String.format("在途商品[%s]删除失败。", Joiner.on(",").join(postNameList));
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ZaituDetail delete result", resultStr);
        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void registerZaituDetailResult(SelectEvent event) {
		boolean result = (Boolean) event.getObject();

		handleResult(result, "登录");
	}

	public void updateZaituDetailResult(SelectEvent event) {

		boolean result = (Boolean) event.getObject();

		handleResult(result, "更新");
	}

	private void handleResult(boolean result, String optStr) {

		String resultStr = null;
		if (result) {
			resultStr = String.format("在途详细信息%s成功。", optStr);
		} else {
			resultStr = String.format("在途详细信息%s失败。", optStr);;
		}

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", resultStr);

        FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private ZaituDetail getZaituDetail() {

		ZaituDetail zaituDetail = new ZaituDetail();
		if(!Strings.isNullOrEmpty(zaituDetailForm.getId())) {
			zaituDetail.setId(Integer.valueOf(zaituDetailForm.getId()));
		}

		if(!Strings.isNullOrEmpty(zaituDetailForm.getZaituId())) {
			zaituDetail.setZaituId(Integer.valueOf(zaituDetailForm.getZaituId()));
		}

		zaituDetail.setGoodsBarcode(zaituDetailForm.getGoodsBarcode());

		if(!Strings.isNullOrEmpty(zaituDetailForm.getCount())) {
			zaituDetail.setCount(Integer.valueOf(zaituDetailForm.getCount()));
		}
		if(!Strings.isNullOrEmpty(zaituDetailForm.getPrice())) {
			zaituDetail.setPrice(Double.valueOf(zaituDetailForm.getPrice()));
		}

		zaituDetail.setBeizhu(zaituDetailForm.getBeizhu());

		return zaituDetail;
	}

    public void upload() {
    	systemLogger.debug("Start upload.");
        if(goodsFile == null) {
        	systemLogger.debug("error" + goodsFile.getFileName() + " is upload error.");
            FacesMessage message = new FacesMessage("error", goodsFile.getFileName() + " is upload error.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        try {
			DataInputStream in = new DataInputStream(goodsFile.getInputstream());
			BufferedReader br= new BufferedReader(new InputStreamReader(in,"utf-8"));

			Multimap<String,ZaituDetail> zaituDetailMultimap = ArrayListMultimap.create();

			String line;
			int lineCount = 0;

			while( (line = br.readLine()) != null ) {

				if (lineCount == 0) {
					lineCount++;
					continue;
				}
				lineCount++;
				System.out.println(lineCount);

				String[] goodsList = Arrays.copyOf(line.split(","), 7);

				String date = goodsList[0];

				if (Strings.isNullOrEmpty(date)) {
					continue;
				}

				String barcode = goodsList[1];
				String goodsCount = goodsList[3];
				String price = goodsList[4];
				String beizhu = goodsList[6];

				ZaituDetail zaitu = new ZaituDetail();
				zaitu.setZaituDateString(date);
				zaitu.setGoodsBarcode(barcode);

				if (!Strings.isNullOrEmpty(goodsCount)) {
					zaitu.setCount(Integer.valueOf(goodsCount.trim()));
				}

				if (!Strings.isNullOrEmpty(price)) {
					zaitu.setPrice(Double.valueOf(price.trim()));
				}
				zaitu.setBeizhu(beizhu);

				zaituDetailMultimap.put(date, zaitu);
			}
			br.close();

        	systemLogger.debug("zaituDetailMultimap count: " + zaituDetailMultimap.size());

			Zaitu zaitu = null;

			Timestamp currentDatetime = DateUtil.getCurrentDateTime();

			DBManager.beginTransaction();

			for (String date : zaituDetailMultimap.keySet()) {

				List<ZaituDetail> zaituDetailList = (List<ZaituDetail>)zaituDetailMultimap.get(date);

				System.out.println(String.format("%s size : %d", date, zaituDetailList.size()));

				int goodsTotalCount = 0;
				double goodsTotalPrice = 0;

				for (ZaituDetail zaituDetail : zaituDetailList) {
					goodsTotalCount += zaituDetail.getCount();
					goodsTotalPrice += (zaituDetail.getPrice() * zaituDetail.getCount());
				}

				zaitu = new Zaitu();
				zaitu.setZaituDate(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, date));
				zaitu.setGoodsTotalCount(goodsTotalCount);
				zaitu.setGoodsTotalPrice(goodsTotalPrice);

				zaitu.setRgtOpt("admin");
				zaitu.setRgtDate(currentDatetime);
				zaitu.setUpdOpt("admin");
				zaitu.setUpdDate(currentDatetime);

				int zaituId = zaituService.getId(zaitu.getZaituDate());
				if (zaituId > 0) {
					zaituService.delete(zaituId);
					zaituDetailService.deleteByZaituId(zaituId);
				}

				if (!zaituService.insert(zaitu)) {
					throw new SQLException("Insert zaitu error.");
				}

				for (ZaituDetail zaituDetail : zaituDetailList) {

					zaituDetail.setZaituId(zaituService.getId(zaitu.getZaituDate()));

					if (!zaituDetailService.insert(zaituDetail)) {
						throw new SQLException("Insert zaitu detail error.");
					}
				}
			}

			DBManager.commitTransaction();

	    	systemLogger.debug("Success" + goodsFile.getFileName() + " is uploaded.");

            FacesMessage message = new FacesMessage("Success", goodsFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			DBManager.rollbackTransaction();
	    	systemLogger.debug(e.getMessage(), e);
			return ;
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			DBManager.rollbackTransaction();
	    	systemLogger.debug(e.getMessage(), e);
			return ;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			DBManager.rollbackTransaction();
	    	systemLogger.debug(e.getMessage(), e);
			return ;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			DBManager.rollbackTransaction();
	    	systemLogger.debug(e.getMessage(), e);
			return ;
		}

		RequestContext.getCurrentInstance().closeDialog(true);
    	systemLogger.debug("End upload.");
    }


    public void checkZaituDetail() {
        if(goodsFile == null) {
            FacesMessage message = new FacesMessage("error", goodsFile.getFileName() + " is upload error.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

		try {
			InputStream ips = new DataInputStream(goodsFile.getInputstream());
			HSSFWorkbook wb = new HSSFWorkbook(ips);
			HSSFSheet sheet = wb.getSheetAt(0);
			List<ZaituDetail> zaituDetailList = new ArrayList<ZaituDetail>();
			boolean b = true;
			for (Iterator ite = sheet.rowIterator(); ite.hasNext();) {

				HSSFRow row = (HSSFRow) ite.next();

				if (b) {
					b = false;
					continue;
				}

				if (row.getCell(0) == null) {
					continue;
				}


				ZaituDetail zaitu = new ZaituDetail();
				zaitu.setGoodsBarcode(String.valueOf((long) row.getCell(1).getNumericCellValue()));
				zaitu.setCount(Integer.valueOf(String.valueOf((long) row.getCell(2).getNumericCellValue())));

				zaituDetailList.add(zaitu);
			}

			Timestamp tZaituDate = null;
			if (zaituDate != null) {
				tZaituDate = new Timestamp(zaituDate.getTime());
			}

			String format = "条形码【%s】的在途数量为【%d】，仓库盘点数量为【%d】，共少【%d】件。";

			StringBuilder sb = new StringBuilder();

			for (ZaituDetail zaituDetail : zaituDetailList) {
				int count = zaituDetailService.getCount(tZaituDate, zaituDetail.getGoodsBarcode(), zaituDetail.getCount());

				if (count == 0) {
					int zaituCount = zaituDetailService.getCount(tZaituDate, zaituDetail.getGoodsBarcode());
					sb.append(String.format(format, zaituDetail.getGoodsBarcode(), zaituCount, zaituDetail.getCount(),
							zaituCount - zaituDetail.getCount())).append("\r\n");
				}

				System.out.println(String.format("barcode[%s] count: %d", zaituDetail.getGoodsBarcode(), count));
			}

            FacesMessage message = new FacesMessage(sb.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public LazyDataModel<ZaituDetail> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<ZaituDetail> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public ZaituDetailServiceImpl getZaituDetailService() {
		return zaituDetailService;
	}

	public void setZaituDetailService(ZaituDetailServiceImpl zaituDetailService) {
		this.zaituDetailService = zaituDetailService;
	}

	public ZaituDetailSearch getZaituDetailSearch() {
		return zaituDetailSearch;
	}

	public void setZaituDetailSearch(ZaituDetailSearch zaituDetailSearch) {
		this.zaituDetailSearch = zaituDetailSearch;
	}

	public ZaituDetailForm getZaituDetailForm() {
		return zaituDetailForm;
	}

	public void setZaituDetailForm(ZaituDetailForm zaituDetailForm) {
		this.zaituDetailForm = zaituDetailForm;
	}

	public List<ZaituDetail> getSelectedZaituDetailList() {
		return selectedZaituDetailList;
	}

	public void setSelectedZaituDetailList(List<ZaituDetail> selectedZaituDetailList) {
		this.selectedZaituDetailList = selectedZaituDetailList;
	}

	public CacheDataBean getCacheData() {
		return cacheData;
	}

	public void setCacheData(CacheDataBean cacheData) {
		this.cacheData = cacheData;
	}

	public UploadedFile getGoodsFile() {
		return goodsFile;
	}

	public void setGoodsFile(UploadedFile goodsFile) {
		this.goodsFile = goodsFile;
	}

	public ZaituServiceImpl getZaituService() {
		return zaituService;
	}

	public void setZaituService(ZaituServiceImpl zaituService) {
		this.zaituService = zaituService;
	}

	public Date getZaituDate() {
		return zaituDate;
	}

	public void setZaituDate(Date zaituDate) {
		this.zaituDate = zaituDate;
	}

}