package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.Brand;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.BrandServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyBrandDataModel extends LazyDataModel<Brand> {

	private static final long serialVersionUID = 1L;

    private List<Brand> list;

    private SearchModel searchMode;

    public LazyBrandDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public Brand getRowData(String rowKey) {
		for (Brand brand : list) {
			if (String.valueOf(brand.getId()).equals(rowKey)) {
				return brand;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(Brand object) {
		 return object.getId();
	}

	@Override
	public List<Brand> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		BrandServiceImpl service = new BrandServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getBrandList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
