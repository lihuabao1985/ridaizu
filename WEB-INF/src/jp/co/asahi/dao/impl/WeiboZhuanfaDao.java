package jp.co.asahi.dao.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.db.DBAccess;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.WeiboZhuanfa;
import jp.co.asahi.model.search.SearchModel;

@ManagedBean(name = "weiboZhuanfaDao")
@ApplicationScoped
public class WeiboZhuanfaDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM d_weibo_zhuanfa \n");
		sql.append(searchModel.getSelectCountSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		int count = 0;

		if (rs == null) {
			return count;
		}

		if (rs.next()) {
			count = rs.getInt("count");
		}

		rs.close();

		return count;
	}

	public List<WeiboZhuanfa> selectWeiboZhuanfaList(SearchModel searchModel) throws SQLException {

	    StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM d_weibo_zhuanfa \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<WeiboZhuanfa> modelList = new ArrayList<WeiboZhuanfa> ();
		while (rs.next()) {
			modelList.add((WeiboZhuanfa)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		WeiboZhuanfa weiboZhuanfa = new WeiboZhuanfa();
		weiboZhuanfa.setId(rs.getInt("id"));
		weiboZhuanfa.setWeiboId(rs.getString("weibo_id"));
		weiboZhuanfa.setWeiboName(rs.getString("weibo_name"));
		int zhuanfaCount = rs.getInt("zhuanfa_count");
		weiboZhuanfa.setZhuanfaCount(zhuanfaCount);
		weiboZhuanfa.setDiscount(getDiscount(zhuanfaCount));

		return weiboZhuanfa;
	}

	public String getDiscount(int zhuanfaCount) {
	    
	    String discount = "";
	    
	    if (zhuanfaCount >= 50) {
            discount = "3%";
        } else if (zhuanfaCount >= 30) {
            discount = "2%";
        } else if (zhuanfaCount >= 10) {
            discount = "1%";
        } else {
            discount = "无";
        }
	    
	    return discount;
	}
	
	@Override
	public int update(Model model) {

		checkNotNull(model);
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE d_weibo_zhuanfa \n");
		sql.append("SET \n");
		sql.append("weibo_id = ?, \n");
		sql.append("weibo_name = ?, \n");
		sql.append("zhuanfa_count = ? \n");
		sql.append("WHERE id = ? \n");

		WeiboZhuanfa weiboZhuanfa = (WeiboZhuanfa) model;

		Object[] params = new Object[] {
		        weiboZhuanfa.getWeiboId(), weiboZhuanfa.getWeiboName(), weiboZhuanfa.getZhuanfaCount(),
		        weiboZhuanfa.getId()
		};


		return DBAccess.update(sql.toString(), params);
	}

}
