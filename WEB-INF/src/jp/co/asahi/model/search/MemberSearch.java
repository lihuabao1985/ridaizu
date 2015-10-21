package jp.co.asahi.model.search;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "memberSearch")
@SessionScoped
public class MemberSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private String wangwangNo;

	private String grade;

	public String getWangwangNo() {
		return wangwangNo;
	}

	public void setWangwangNo(String wangwangNo) {
		this.wangwangNo = wangwangNo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if(!Strings.isNullOrEmpty(wangwangNo)) {
			sb.append("AND ");
			sb.append("wangwang_no LIKE ? \n");

			conditionList.add("%" + wangwangNo + "%");
		}

		if(!Strings.isNullOrEmpty(grade)) {
			sb.append("AND ");
			sb.append("grade = ? \n");

			conditionList.add(grade);
		}

		if (!Strings.isNullOrEmpty(this.sortField)) {
			sb.append(String.format("ORDER BY %s %s \n", this.sortField, this.sortType.toString()));
		} else {
			sb.append("ORDER BY last_trade_date DESC \n");
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

		if(!Strings.isNullOrEmpty(wangwangNo)) {
			sb.append("AND ");
			sb.append("wangwang_no LIKE ? \n");

			conditionList.add("%" + wangwangNo + "%");
		}

		if(!Strings.isNullOrEmpty(grade)) {
			sb.append("AND ");
			sb.append("grade = ? \n");

			conditionList.add(grade);
		}

		return sb.toString();
	}


}
