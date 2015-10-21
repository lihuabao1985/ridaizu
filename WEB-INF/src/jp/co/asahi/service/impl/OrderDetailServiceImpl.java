package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.OrderDetailDao;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.OrderDetail;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "orderDetailServiceImpl")
@ApplicationScoped
public class OrderDetailServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private OrderDetailDao dao;

	public OrderDetailServiceImpl() {
		this.dao = new OrderDetailDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (OrderDetailDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<OrderDetail> getOrderDetailList(SearchModel searchModel) throws SQLException {

		return dao.selectOrderDetailList(searchModel);
	}

	public boolean insert(Model model) {

		return dao.insert(model) > 0;
	}

	public boolean update(Model model) {

		return dao.update(model) > 0;
	}

	public boolean delete(int... id) {

		return dao.delete(id) > 0;
	}
}
