package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.Daili;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.DailiServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyDailiDataModel extends LazyDataModel<Daili> {

	private static final long serialVersionUID = 1L;

    private List<Daili> list;

    private SearchModel searchMode;

    public LazyDailiDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public Daili getRowData(String rowKey) {
		for (Daili daili : list) {
			if (String.valueOf(daili.getId()).equals(rowKey)) {
				return daili;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(Daili object) {
		 return object.getId();
	}

	@Override
	public List<Daili> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		DailiServiceImpl service = new DailiServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getDailiList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
