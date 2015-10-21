package jp.co.asahi.lazy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.co.asahi.model.Member;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.model.search.SearchModel.EnumSortType;
import jp.co.asahi.service.impl.MemberServiceImpl;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyMemberDataModel extends LazyDataModel<Member> {

	private static final long serialVersionUID = 1L;

    private List<Member> list;

    private SearchModel searchMode;

    public LazyMemberDataModel(SearchModel searchMode) {
    	this.searchMode = searchMode;
    }

	@Override
	public Member getRowData(String rowKey) {
		for (Member member : list) {
			if (String.valueOf(member.getUserId()).equals(rowKey)) {
				return member;
			}
		}
        return null;
	}

	@Override
	public Object getRowKey(Member object) {
		 return object.getUserId();
	}

	@Override
	public List<Member> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		searchMode.setFirst(first);
		searchMode.setPageSize(pageSize);
		searchMode.setSortField(sortField);

		if (SortOrder.ASCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.ASC);
		} else if (SortOrder.DESCENDING == sortOrder) {
			searchMode.setSortType(EnumSortType.DESC);
		}

		MemberServiceImpl service = new MemberServiceImpl();

        int dataSize = 0;

        try {
			dataSize = service.getCount(searchMode);
	        this.setRowCount(dataSize);

			list = service.getMemberList(searchMode);
		} catch (SQLException e) {
			e.printStackTrace();
		}


        return list;
	}

}
