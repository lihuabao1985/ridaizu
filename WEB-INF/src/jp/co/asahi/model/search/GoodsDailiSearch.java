package jp.co.asahi.model.search;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "goodsDailiSearch")
@SessionScoped
public class GoodsDailiSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private String dailiName;

	public String getDailiName() {
		return dailiName;
	}

	public void setDailiName(String dailiName) {
		this.dailiName = dailiName;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if (!Strings.isNullOrEmpty(dailiName)) {
			sb.append("AND ");
			sb.append("daili_name like ?");
			sb.append("\n");
			conditionList.add("%" + dailiName + "%");
		}

		if (!Strings.isNullOrEmpty(this.sortField)) {
			sb.append(String.format("ORDER BY %s %s \n", this.sortField, this.sortType.toString()));
		} else {
			sb.append("ORDER BY id DESC \n");
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

		if (!Strings.isNullOrEmpty(dailiName)) {
			sb.append("AND ");
			sb.append("daili_name like ?");
			sb.append("\n");
			conditionList.add("%" + dailiName + "%");
		}

		return sb.toString();
	}


}
