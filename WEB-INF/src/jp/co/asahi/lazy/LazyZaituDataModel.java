package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.ZaituServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyZaituDataModel extends LazyDataModel<Zaitu> {

	private static final long serialVersionUID = 1L;

    private List<Zaitu> list;

    private SearchModel searchMode;

    public LazyZaituDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public Zaitu getRowData(String rowKey) {
		for (Zaitu zaitu : list) {
			if (String.valueOf(zaitu.getId()).equals(rowKey)) {
				return zaitu;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(Zaitu object) {
		 return object.getId();
	}

	@Override
	public List<Zaitu> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		ZaituServiceImpl service = new ZaituServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getZaituList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
