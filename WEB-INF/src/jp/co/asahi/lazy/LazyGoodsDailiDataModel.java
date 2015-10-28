package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.GoodsDaili;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.GoodsDailiServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyGoodsDailiDataModel extends LazyDataModel<GoodsDaili> {

	private static final long serialVersionUID = 1L;

    private List<GoodsDaili> list;

    private SearchModel searchMode;

    public LazyGoodsDailiDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public GoodsDaili getRowData(String rowKey) {
		for (GoodsDaili goodsDaili : list) {
			if (String.valueOf(goodsDaili.getId()).equals(rowKey)) {
				return goodsDaili;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(GoodsDaili object) {
		 return object.getId();
	}

	@Override
	public List<GoodsDaili> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		GoodsDailiServiceImpl service = new GoodsDailiServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getGoodsDailiList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
