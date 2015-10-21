package jp.co.asahi.dao.impl;

import static com.google.common.base.Preconditions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.db.DBAccess;
import jp.co.asahi.model.Member;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;

@ManagedBean(name = "memberDao")
@ApplicationScoped
public class MemberDao extends DaoAdapter {

	private static final long serialVersionUID = 1L;

	@Override
	public int selectCount(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) as count FROM m_taobao_member \n");
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

	public List<Member> selectMemberList(SearchModel searchModel) throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *, service_ticket - use_service_ticket as after_service_ticket FROM m_taobao_member \n");
		sql.append(searchModel.getSelectSql());

		Object[] params = searchModel.getConditionList().toArray();

		ResultSet rs = DBAccess.query(sql.toString(), params);

		List<Member> modelList = new ArrayList<Member> ();
		while (rs.next()) {
			modelList.add((Member)getModel(rs));
		}

		rs.close();

		return modelList;
	}

	@Override
	public Model getModel(ResultSet rs) throws SQLException {

		checkNotNull(rs);

		Member member = new Member();
		member.setUserId(rs.getInt("user_id"));
		member.setWangwangNo(rs.getString("wangwang_no"));
		member.setLink(rs.getString("link"));
		member.setUserName(rs.getString("user_name"));
		member.setSex(rs.getString("sex"));
		member.setMobile(rs.getString("mobile"));
		member.setEmail(rs.getString("email"));
		member.setBirth(rs.getString("birth"));
		member.setProvince(rs.getString("province"));
		member.setCity(rs.getString("city"));
		member.setGrade(rs.getString("grade"));
		member.setTradeTotal(rs.getString("trade_total"));
		member.setTurnover(rs.getString("turnover"));
		member.setGoodsCount(rs.getString("goods_count"));
		member.setAddress(rs.getString("address"));
		member.setLastTradeDate(rs.getString("last_trade_date"));
		member.setAddFlg(rs.getBoolean("add_flg"));
		member.setAddedFlg(rs.getBoolean("added_flg"));
		member.setServiceTicket(rs.getInt("service_ticket"));
		member.setUseServiceTicket(rs.getInt("use_service_ticket"));
		member.setAfterServiceTicket(rs.getInt("after_service_ticket"));
		member.setSendMsgFlg(rs.getBoolean("send_msg_flg"));

		return member;
	}

	public int updateServiceTicket(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_taobao_member \n");
		sql.append("SET \n");
		sql.append("use_service_ticket = ? \n");
		sql.append("WHERE user_id = ? \n");

		Member member = (Member) model;

		Object[] params = new Object[] {
				member.getUseServiceTicket(), member.getUserId()
		};

		return DBAccess.update(sql.toString(), params);
	}

	@Override
	public int update(Model model) {

		checkNotNull(model);

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE m_taobao_member \n");
		sql.append("SET \n");
		sql.append("CORJP_NO = ?, \n");
		sql.append("NAME1 = ?, \n");
		sql.append("TEL1 = ?, \n");
		sql.append("FAX1 = ?, \n");
		sql.append("MAN1 = ?, \n");
		sql.append("NAME2 = ?, \n");
		sql.append("TEL2 = ?, \n");
		sql.append("FAX2 = ?, \n");
		sql.append("MAN2 = ?, \n");
		sql.append("BANK = ?, \n");
		sql.append("KOUZA_NO = ?, \n");
		sql.append("KOZA_NAME = ? \n");
		sql.append("WHERE CORJP_ID = ? \n");

//		Member member = (Member) model;

		Object[] params = new Object[] {
		};


		return DBAccess.update(sql.toString(), params);
	}

}
