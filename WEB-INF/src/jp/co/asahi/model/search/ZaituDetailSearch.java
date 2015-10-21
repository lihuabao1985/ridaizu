package jp.co.asahi.model.search;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "zaituDetailSearch")
@SessionScoped
public class ZaituDetailSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private Date startZaituDetailDate;

	private Date endZaituDetailDate;

	public Date getStartZaituDetailDate() {
		return startZaituDetailDate;
	}

	public void setStartZaituDetailDate(Date startZaituDetailDate) {
		this.startZaituDetailDate = startZaituDetailDate;
	}

	public Date getEndZaituDetailDate() {
		return endZaituDetailDate;
	}

	public void setEndZaituDetailDate(Date endZaituDetailDate) {
		this.endZaituDetailDate = endZaituDetailDate;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		// 在途日期
		if (startZaituDetailDate != null && endZaituDetailDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(startZaituDetailDate);
			conditionList.add(endZaituDetailDate);
		} else if (startZaituDetailDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date >= ? ");
			sb.append("\n");
			conditionList.add(startZaituDetailDate);
		} else if (endZaituDetailDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date <= ? ");
			sb.append("\n");
			conditionList.add(endZaituDetailDate);
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
		if (startZaituDetailDate != null && endZaituDetailDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(startZaituDetailDate);
			conditionList.add(endZaituDetailDate);
		} else if (startZaituDetailDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date >= ? ");
			sb.append("\n");
			conditionList.add(startZaituDetailDate);
		} else if (endZaituDetailDate != null) {
			sb.append("AND ");
			sb.append("zaitu_date <= ? ");
			sb.append("\n");
			conditionList.add(endZaituDetailDate);
		}

		return sb.toString();
	}


}
