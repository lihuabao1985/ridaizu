package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.Order;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.OrderServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyOrderDataModel extends LazyDataModel<Order> {

	private static final long serialVersionUID = 1L;

    private List<Order> list;

    private SearchModel searchMode;

    public LazyOrderDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public Order getRowData(String rowKey) {
		for (Order order : list) {
			if (String.valueOf(order.getId()).equals(rowKey)) {
				return order;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(Order object) {
		 return object.getId();
	}

	@Override
	public List<Order> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		OrderServiceImpl service = new OrderServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getOrderList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
