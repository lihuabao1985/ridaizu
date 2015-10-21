package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.ZaituDetail;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.ZaituDetailServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyZaituDetailDataModel extends LazyDataModel<ZaituDetail> {

	private static final long serialVersionUID = 1L;

    private List<ZaituDetail> list;

    private SearchModel searchMode;

    public LazyZaituDetailDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public ZaituDetail getRowData(String rowKey) {
		for (ZaituDetail zaituDetail : list) {
			if (String.valueOf(zaituDetail.getId()).equals(rowKey)) {
				return zaituDetail;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(ZaituDetail object) {
		 return object.getId();
	}

	@Override
	public List<ZaituDetail> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		ZaituDetailServiceImpl service = new ZaituDetailServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getZaituDetailList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
