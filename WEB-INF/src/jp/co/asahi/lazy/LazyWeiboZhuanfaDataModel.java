package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.WeiboZhuanfa;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.WeiboZhuanfaServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyWeiboZhuanfaDataModel extends LazyDataModel<WeiboZhuanfa> {

	private static final long serialVersionUID = 1L;

    private List<WeiboZhuanfa> list;

    private SearchModel searchMode;

    public LazyWeiboZhuanfaDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public WeiboZhuanfa getRowData(String rowKey) {
		for (WeiboZhuanfa weiboZhuanfa : list) {
			if (String.valueOf(weiboZhuanfa.getId()).equals(rowKey)) {
				return weiboZhuanfa;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(WeiboZhuanfa object) {
		 return object.getId();
	}

	@Override
	public List<WeiboZhuanfa> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		WeiboZhuanfaServiceImpl service = new WeiboZhuanfaServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getWeiboZhuanfaList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
