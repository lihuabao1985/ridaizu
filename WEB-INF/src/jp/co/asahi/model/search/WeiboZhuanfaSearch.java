package jp.co.asahi.model.search;

import java.util.ArrayList;

import javax.faces.bean.SessionScoped;

import com.google.common.base.Strings;

@javax.faces.bean.ManagedBean(name = "weiboZhuanfaSearch")
@SessionScoped
public class WeiboZhuanfaSearch extends SearchModel {

	private static final long serialVersionUID = 1L;

	private String weiboId;

	private String weiboName;

	public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getWeiboName() {
        return weiboName;
    }

    public void setWeiboName(String weiboName) {
        this.weiboName = weiboName;
    }

    @Override
	public String getSelectSql() {

		StringBuffer sb = new StringBuffer();
		conditionList = new ArrayList<Object>();

		sb.append("WHERE 1=1 \n");

		if(!Strings.isNullOrEmpty(weiboId)) {
			sb.append("AND ");
			sb.append("weibo_id LIKE ? \n");

			conditionList.add("%" + weiboId + "%");
		}

		if(!Strings.isNullOrEmpty(weiboName)) {
			sb.append("AND ");
			sb.append("weibo_name LIKE ? \n");

			conditionList.add("%" + weiboName + "%");
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

        if(!Strings.isNullOrEmpty(weiboId)) {
            sb.append("AND ");
            sb.append("weibo_id LIKE ? \n");

            conditionList.add("%" + weiboId + "%");
        }

        if(!Strings.isNullOrEmpty(weiboName)) {
            sb.append("AND ");
            sb.append("weibo_name LIKE ? \n");

            conditionList.add("%" + weiboName + "%");
        }

		return sb.toString();
	}


}
