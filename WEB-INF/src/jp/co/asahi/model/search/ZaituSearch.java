package jp.co.asahi.model.search;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "zaituSearch")
@SessionScoped
public class ZaituSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private Date startZaituDate;

	private Date endZaituDate;

	public Date getStartZaituDate() {
		return startZaituDate;
	}

	public void setStartZaituDate(Date startZaituDate) {
		this.startZaituDate = startZaituDate;
	}

	public Date getEndZaituDate() {
		return endZaituDate;
	}

	public void setEndZaituDate(Date endZaituDate) {
		this.endZaituDate = endZaituDate;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		// 在途日期
		if (startZaituDate != null && endZaituDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(startZaituDate);
			conditionList.add(endZaituDate);
		} else if (startZaituDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date >= ? ");
			sb.append("\n");
			conditionList.add(startZaituDate);
		} else if (endZaituDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date <= ? ");
			sb.append("\n");
			conditionList.add(endZaituDate);
		}

		if (!Strings.isNullOrEmpty(this.sortField)) {
			sb.append(String.format("ORDER BY %s %s \n", this.sortField, this.sortType.toString()));
		} else {
			sb.append("ORDER BY zaitu_date DESC \n");
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

		// 在途日期
		if (startZaituDate != null && endZaituDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(startZaituDate);
			conditionList.add(endZaituDate);
		} else if (startZaituDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date >= ? ");
			sb.append("\n");
			conditionList.add(startZaituDate);
		} else if (endZaituDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date <= ? ");
			sb.append("\n");
			conditionList.add(endZaituDate);
		}

		return sb.toString();
	}


}
