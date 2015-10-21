package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.BrandSeries;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.BrandSeriesServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyBrandSeriesDataModel extends LazyDataModel<BrandSeries> {

	private static final long serialVersionUID = 1L;

    private List<BrandSeries> list;

    private SearchModel searchMode;

    public LazyBrandSeriesDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public BrandSeries getRowData(String rowKey) {
		for (BrandSeries brandSeries : list) {
			if (String.valueOf(brandSeries.getId()).equals(rowKey)) {
				return brandSeries;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(BrandSeries object) {
		 return object.getId();
	}

	@Override
	public List<BrandSeries> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		BrandSeriesServiceImpl service = new BrandSeriesServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getBrandSeriesList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
