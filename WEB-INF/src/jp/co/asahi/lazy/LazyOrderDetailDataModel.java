package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.OrderDetail;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.OrderDetailServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyOrderDetailDataModel extends LazyDataModel<OrderDetail> {

	private static final long serialVersionUID = 1L;

    private List<OrderDetail> list;

    private SearchModel searchMode;

    public LazyOrderDetailDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public OrderDetail getRowData(String rowKey) {
		for (OrderDetail orderDetail : list) {
			if (String.valueOf(orderDetail.getId()).equals(rowKey)) {
				return orderDetail;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(OrderDetail object) {
		 return object.getId();
	}

	@Override
	public List<OrderDetail> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		OrderDetailServiceImpl service = new OrderDetailServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getOrderDetailList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
