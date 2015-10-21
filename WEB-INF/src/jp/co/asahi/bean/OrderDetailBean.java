package jp.co.asahi.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import jp.co.asahi.lazy.LazyOrderDetailDataModel;
import jp.co.asahi.model.OrderDetail;
import jp.co.asahi.model.form.OrderDetailForm;
import jp.co.asahi.model.search.OrderDetailSearch;
import jp.co.asahi.service.impl.OrderDetailServiceImpl;

import org.primefaces.model.LazyDataModel;

@javax.faces.bean.ManagedBean(name = "orderDetailBean")
@SessionScoped
public class OrderDetailBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<OrderDetail> lazyModel;

    @ManagedProperty("#{orderDetailServiceImpl}")
    private OrderDetailServiceImpl orderDetailService;

    @ManagedProperty("#{orderDetailSearch}")
    private OrderDetailSearch orderDetailSearch;

    @ManagedProperty("#{orderDetailForm}")
    private OrderDetailForm orderDetailForm;

	private List<OrderDetail> selectedOrderDetailList;

    @PostConstruct
    public void init() {
    	searchOrderDetailSync();
    }

	public void searchOrderDetailSync() {
		lazyModel = new LazyOrderDetailDataModel(orderDetailSearch);
	}

	public LazyDataModel<OrderDetail> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<OrderDetail> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public OrderDetailServiceImpl getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailServiceImpl orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	public OrderDetailSearch getOrderDetailSearch() {
		return orderDetailSearch;
	}

	public void setOrderDetailSearch(OrderDetailSearch orderDetailSearch) {
		this.orderDetailSearch = orderDetailSearch;
	}

	public OrderDetailForm getOrderDetailForm() {
		return orderDetailForm;
	}

	public void setOrderDetailForm(OrderDetailForm orderDetailForm) {
		this.orderDetailForm = orderDetailForm;
	}

	public List<OrderDetail> getSelectedOrderDetailList() {
		return selectedOrderDetailList;
	}

	public void setSelectedOrderDetailList(List<OrderDetail> selectedOrderDetailList) {
		this.selectedOrderDetailList = selectedOrderDetailList;
	}

}