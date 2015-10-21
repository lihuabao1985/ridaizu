package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.Goods;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.GoodsServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyGoodsDataModel extends LazyDataModel<Goods> {

	private static final long serialVersionUID = 1L;

    private List<Goods> list;

    private SearchModel searchMode;

    public LazyGoodsDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public Goods getRowData(String rowKey) {
		for (Goods goods : list) {
			if (String.valueOf(goods.getId()).equals(rowKey)) {
				return goods;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(Goods object) {
		 return object.getId();
	}

	@Override
	public List<Goods> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		GoodsServiceImpl service = new GoodsServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getGoodsList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
