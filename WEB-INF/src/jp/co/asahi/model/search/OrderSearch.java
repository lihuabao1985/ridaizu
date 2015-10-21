package jp.co.asahi.model.search;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "orderSearch")
@SessionScoped
public class OrderSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private String dingdanBianhao;

	public String getDingdanBianhao() {
		return dingdanBianhao;
	}

	public void setDingdanBianhao(String dingdanBianhao) {
		this.dingdanBianhao = dingdanBianhao;
	}

	@Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if (!Strings.isNullOrEmpty(dingdanBianhao)) {
			sb.append("AND ");
			sb.append("dingdan_bianhao = ?");
			sb.append("\n");
			conditionList.add(dingdanBianhao);
		}

		if (!Strings.isNullOrEmpty(this.sortField)) {
			sb.append(String.format("ORDER BY %s %s \n", this.sortField, this.sortType.toString()));
		} else {
			sb.append("ORDER BY dingdan_zhifu_shijian DESC \n");
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

		if (!Strings.isNullOrEmpty(dingdanBianhao)) {
			sb.append("AND ");
			sb.append("dingdan_bianhao = ?");
			sb.append("\n");
			conditionList.add(dingdanBianhao);
		}

		return sb.toString();
	}


}
