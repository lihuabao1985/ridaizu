package jp.co.asahi.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import jp.co.asahi.lazy.LazyOrderDataModel;
import jp.co.asahi.model.Order;
import jp.co.asahi.model.form.OrderForm;
import jp.co.asahi.model.search.OrderSearch;
import jp.co.asahi.service.impl.OrderServiceImpl;

import org.primefaces.model.LazyDataModel;

@javax.faces.bean.ManagedBean(name = "orderBean")
@SessionScoped
public class OrderBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<Order> lazyModel;

    @ManagedProperty("#{orderServiceImpl}")
    private OrderServiceImpl orderService;

    @ManagedProperty("#{orderSearch}")
    private OrderSearch orderSearch;

    @ManagedProperty("#{orderForm}")
    private OrderForm orderForm;

	private List<Order> selectedOrderList;

    @PostConstruct
    public void init() {
    	searchOrderSync();
    }

	public void searchOrderSync() {
		lazyModel = new LazyOrderDataModel(orderSearch);
	}

	public LazyDataModel<Order> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Order> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public OrderServiceImpl getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderServiceImpl orderService) {
		this.orderService = orderService;
	}

	public OrderSearch getOrderSearch() {
		return orderSearch;
	}

	public void setOrderSearch(OrderSearch orderSearch) {
		this.orderSearch = orderSearch;
	}

	public OrderForm getOrderForm() {
		return orderForm;
	}

	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}

	public List<Order> getSelectedOrderList() {
		return selectedOrderList;
	}

	public void setSelectedOrderList(List<Order> selectedOrderList) {
		this.selectedOrderList = selectedOrderList;
	}

}