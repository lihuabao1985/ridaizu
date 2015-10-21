package jp.co.asahi.model.search;

import java.io.Serializable;
import java.util.List;

public class SearchModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum EnumSortType {
		DESC, ASC
	}

	protected int first;

	protected int pageSize;

	protected String sortField;

	protected EnumSortType sortType;

	protected List<Object> conditionList;

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public EnumSortType getSortType() {
		return sortType;
	}

	public void setSortType(EnumSortType sortType) {
		this.sortType = sortType;
	}

	public List<Object> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<Object> conditionList) {
		this.conditionList = conditionList;
	}

	public String getSelectSql() {
		return null;
	}

	public String getSelectCountSql() {

		return null;
	}
}
