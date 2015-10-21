package jp.co.asahi.model.search;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import jp.co.asahi.util.DateUtil;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "userSearch")
@SessionScoped
public class UserSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private String refNo;

	private String corpOcean;

	private String hblNo;

	private String oblNo;

	private String portOfLoading;

	private String portOfDischarge;

	private String startRegDateYear;

	private String endRegDateYear;

	private String startRegDateMonth;

	private String endRegDateMonth;

	private String startRegDateDay;

	private String endRegDateDay;

	private Timestamp startRegDate;

	private Timestamp endRegDate;

	private String startEtaYear;

	private String endEtaYear;

	private String startEtaMonth;

	private String endEtaMonth;

	private String startEtaDay;

	private String endEtaDay;

	private Timestamp startEta;

	private Timestamp endEta;

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getCorpOcean() {
		return corpOcean;
	}

	public void setCorpOcean(String corpOcean) {
		this.corpOcean = corpOcean;
	}

	public String getHblNo() {
		return hblNo;
	}

	public void setHblNo(String hblNo) {
		this.hblNo = hblNo;
	}

	public String getOblNo() {
		return oblNo;
	}

	public void setOblNo(String oblNo) {
		this.oblNo = oblNo;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public String getPortOfDischarge() {
		return portOfDischarge;
	}

	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}

	public String getStartRegDateYear() {
		return startRegDateYear;
	}

	public void setStartRegDateYear(String startRegDateYear) {
		this.startRegDateYear = startRegDateYear;
	}

	public String getEndRegDateYear() {
		return endRegDateYear;
	}

	public void setEndRegDateYear(String endRegDateYear) {
		this.endRegDateYear = endRegDateYear;
	}

	public String getStartRegDateMonth() {
		return startRegDateMonth;
	}

	public void setStartRegDateMonth(String startRegDateMonth) {
		this.startRegDateMonth = startRegDateMonth;
	}

	public String getEndRegDateMonth() {
		return endRegDateMonth;
	}

	public void setEndRegDateMonth(String endRegDateMonth) {
		this.endRegDateMonth = endRegDateMonth;
	}

	public String getStartRegDateDay() {
		return startRegDateDay;
	}

	public void setStartRegDateDay(String startRegDateDay) {
		this.startRegDateDay = startRegDateDay;
	}

	public String getEndRegDateDay() {
		return endRegDateDay;
	}

	public void setEndRegDateDay(String endRegDateDay) {
		this.endRegDateDay = endRegDateDay;
	}

	public Timestamp getStartRegDate() {
		return startRegDate;
	}

	public void setStartRegDate(Timestamp startRegDate) {
		this.startRegDate = startRegDate;
	}

	public Timestamp getEndRegDate() {
		return endRegDate;
	}

	public void setEndRegDate(Timestamp endRegDate) {
		this.endRegDate = endRegDate;
	}

	public String getStartEtaYear() {
		return startEtaYear;
	}

	public void setStartEtaYear(String startEtaYear) {
		this.startEtaYear = startEtaYear;
	}

	public String getEndEtaYear() {
		return endEtaYear;
	}

	public void setEndEtaYear(String endEtaYear) {
		this.endEtaYear = endEtaYear;
	}

	public String getStartEtaMonth() {
		return startEtaMonth;
	}

	public void setStartEtaMonth(String startEtaMonth) {
		this.startEtaMonth = startEtaMonth;
	}

	public String getEndEtaMonth() {
		return endEtaMonth;
	}

	public void setEndEtaMonth(String endEtaMonth) {
		this.endEtaMonth = endEtaMonth;
	}

	public String getStartEtaDay() {
		return startEtaDay;
	}

	public void setStartEtaDay(String startEtaDay) {
		this.startEtaDay = startEtaDay;
	}

	public String getEndEtaDay() {
		return endEtaDay;
	}

	public void setEndEtaDay(String endEtaDay) {
		this.endEtaDay = endEtaDay;
	}

	public Timestamp getStartEta() {
		return startEta;
	}

	public void setStartEta(Timestamp startEta) {
		this.startEta = startEta;
	}

	public Timestamp getEndEta() {
		return endEta;
	}

	public void setEndEta(Timestamp endEta) {
		this.endEta = endEta;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE CORP_OCEAN = ? \n");
		conditionList.add(corpOcean);

		if(!Strings.isNullOrEmpty(refNo)) {
			sb.append("AND ");
			sb.append("REF_NO LIKE ? \n");

			conditionList.add("%" + refNo + "%");
		}

		if(!Strings.isNullOrEmpty(hblNo)) {
			sb.append("AND ");
			sb.append("H_BLNO = ? \n");

			conditionList.add(hblNo);
		}

		if(!Strings.isNullOrEmpty(oblNo)) {
			sb.append("AND ");
			sb.append("O_BLNO = ? \n");

			conditionList.add(oblNo);
		}

		if(!Strings.isNullOrEmpty(portOfLoading)) {
			sb.append("AND ");
			sb.append("PORT_OF_LOADING = ? \n");

			conditionList.add(portOfLoading);
		}

		if(!Strings.isNullOrEmpty(portOfDischarge)) {
			sb.append("AND ");
			sb.append("PORT_OF_DISCHARGE = ? \n");

			conditionList.add(portOfDischarge);
		}

		// 登録時間
		String startRegDate = mergerDate(this.startRegDateYear, this.startRegDateMonth, null, true);
		String endRegDate = null;
		if (!Strings.isNullOrEmpty(this.endRegDateMonth)) {
			endRegDate = mergerDate(this.endRegDateYear, this.endRegDateMonth,
				String.valueOf(DateUtil.getLastDay(mergerDate(this.endRegDateYear, this.endRegDateMonth, null, true))), false);
		} else {
			endRegDate = mergerDate(this.endRegDateYear, this.endRegDateMonth,
					String.valueOf(DateUtil.getLastDay(startRegDate)), false);
		}

		// 登録時間
		if (startRegDate != null && endRegDate != null) {
			sb.append("AND ");
			sb.append("REG_DATE BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startRegDate));
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endRegDate));
		} else if (startRegDate != null) {
			sb.append("AND ");
			sb.append("REG_DATE >= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startRegDate));
		} else if (endRegDate != null) {
			sb.append("AND ");
			sb.append("REG_DATE <= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endRegDate));
		}

		// ETA時間
		String startEta = mergerDate(this.startEtaYear, this.startEtaMonth, null, true);
		String endEta = null;
		if (!Strings.isNullOrEmpty(this.endEtaMonth)) {
			endEta = mergerDate(this.endEtaYear, this.endEtaMonth,
					String.valueOf(DateUtil.getLastDay(mergerDate(this.endEtaYear, this.endEtaMonth, null, true))), false);
		} else {
			endRegDate = mergerDate(this.endEtaYear, this.endEtaMonth,
					String.valueOf(DateUtil.getLastDay(startEta)), false);
		}

		// ETA時間
		if (startEta != null && endEta != null) {
			sb.append("AND ");
			sb.append("ETA BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startEta));
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endEta));
		} else if (startEta != null) {
			sb.append("AND ");
			sb.append("ETA >= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startEta));
		} else if (endEta != null) {
			sb.append("AND ");
			sb.append("ETA <= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endEta));
		}

		if (!Strings.isNullOrEmpty(this.sortField)) {
			sb.append(String.format("ORDER BY %s %s \n", this.sortField, this.sortType.toString()));
		} else {
			sb.append("ORDER BY REG_DATE DESC \n");
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

		sb.append("WHERE CORP_OCEAN = ? \n");
		conditionList.add(corpOcean);

		if(!Strings.isNullOrEmpty(refNo)) {
			sb.append("AND ");
			sb.append("REF_NO LIKE ? \n");

			conditionList.add("%" + refNo + "%");
		}

		if(!Strings.isNullOrEmpty(hblNo)) {
			sb.append("AND ");
			sb.append("H_BLNO = ? \n");

			conditionList.add(hblNo);
		}

		if(!Strings.isNullOrEmpty(oblNo)) {
			sb.append("AND ");
			sb.append("O_BLNO = ? \n");

			conditionList.add(oblNo);
		}

		if(!Strings.isNullOrEmpty(portOfLoading)) {
			sb.append("AND ");
			sb.append("PORT_OF_LOADING = ? \n");

			conditionList.add(portOfLoading);
		}

		if(!Strings.isNullOrEmpty(portOfDischarge)) {
			sb.append("AND ");
			sb.append("PORT_OF_DISCHARGE = ? \n");

			conditionList.add(portOfDischarge);
		}

		// 登録時間
		String startRegDate = mergerDate(this.startRegDateYear, this.startRegDateMonth, null, true);
		String endRegDate = null;
		if (!Strings.isNullOrEmpty(this.endRegDateMonth)) {
			endRegDate = mergerDate(this.endRegDateYear, this.endRegDateMonth,
				String.valueOf(DateUtil.getLastDay(mergerDate(this.endRegDateYear, this.endRegDateMonth, null, true))), false);
		} else {
			endRegDate = mergerDate(this.endRegDateYear, this.endRegDateMonth,
					String.valueOf(DateUtil.getLastDay(startRegDate)), false);
		}

		// 登録時間
		if (startRegDate != null && endRegDate != null) {
			sb.append("AND ");
			sb.append("REG_DATE BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startRegDate));
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endRegDate));
		} else if (startRegDate != null) {
			sb.append("AND ");
			sb.append("REG_DATE >= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startRegDate));
		} else if (endRegDate != null) {
			sb.append("AND ");
			sb.append("REG_DATE <= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endRegDate));
		}

		// ETA時間
		String startEta = mergerDate(this.startEtaYear, this.startEtaMonth, null, true);
		String endEta = null;
		if (!Strings.isNullOrEmpty(this.endEtaMonth)) {
			endEta = mergerDate(this.endEtaYear, this.endEtaMonth,
					String.valueOf(DateUtil.getLastDay(mergerDate(this.endEtaYear, this.endEtaMonth, null, true))), false);
		} else {
			endRegDate = mergerDate(this.endEtaYear, this.endEtaMonth,
					String.valueOf(DateUtil.getLastDay(startEta)), false);
		}

		// ETA時間
		if (startEta != null && endEta != null) {
			sb.append("AND ");
			sb.append("ETA BETWEEN ? AND ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startEta));
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endEta));
		} else if (startEta != null) {
			sb.append("AND ");
			sb.append("ETA >= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, startEta));
		} else if (endEta != null) {
			sb.append("AND ");
			sb.append("ETA <= ? ");
			sb.append("\n");
			conditionList.add(DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, endEta));
		}

		return sb.toString();
	}

	public static String mergerDate(String year, String month, String day, boolean isStart) {
		StringBuffer str = new StringBuffer();

		if (Strings.isNullOrEmpty(year)) {
			return null;
		}

		String split1 = "-";
		String split2 = "0";
		String split3 = "01";
		String split4 = "12";
		String split5 = "31 23:59:59";

		str.append(year).append(split1);

		if (Strings.isNullOrEmpty(month)) {
			if (isStart) {
				str.append(split3).append(split1);
			} else {
				str.append(split4).append(split1);
			}
		} else {
			String tmp = month.length() == 2 ? month : split2 + month;
			str.append(tmp).append(split1);
		}
		if (Strings.isNullOrEmpty(day)) {
			if (isStart) {
				str.append(split3);
			} else {
				str.append(split5);
			}
		} else {
			String tmp = day.length() == 2 ? day : split2 + day;
			str.append(tmp + " 23:59:59");
		}

		return str.toString();
	}


}
