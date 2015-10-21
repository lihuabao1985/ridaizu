package jp.co.asahi.model.search;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "brandSeriesSearch")
@SessionScoped
public class BrandSeriesSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if (!Strings.isNullOrEmpty(name)) {
			sb.append("AND ");
			sb.append("name like ?");
			sb.append("\n");
			conditionList.add("%" + name + "%");
		}

		if (!Strings.isNullOrEmpty(this.sortField)) {
			sb.append(String.format("ORDER BY %s %s \n", this.sortField, this.sortType.toString()));
		} else {
			sb.append("ORDER BY rgt_date DESC \n");
		}

		sb.append("LIMIT ?, ? ");
		conditionList.add(first);
		conditionList.add(pageSize);

		return sb.toString();
	}

	@Override
	public String getSelectCountSql() {
		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if (!Strings.isNullOrEmpty(name)) {
			sb.append("AND ");
			sb.append("name like ?");
			sb.append("\n");
			conditionList.add("%" + name + "%");
		}

		return sb.toString();
	}


}
