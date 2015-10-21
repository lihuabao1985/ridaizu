package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.OrderDao;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.Order;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "orderServiceImpl")
@ApplicationScoped
public class OrderServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private OrderDao dao;

	public OrderServiceImpl() {
		this.dao = new OrderDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (OrderDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Order> getOrderList(SearchModel searchModel) throws SQLException {

		return dao.selectOrderList(searchModel);
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
