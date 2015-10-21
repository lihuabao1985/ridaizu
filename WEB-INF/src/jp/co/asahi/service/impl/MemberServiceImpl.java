package jp.co.asahi.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jp.co.asahi.dao.DaoAdapter;
import jp.co.asahi.dao.impl.MemberDao;
import jp.co.asahi.model.Member;
import jp.co.asahi.model.Model;
import jp.co.asahi.model.search.SearchModel;
import jp.co.asahi.service.Service;

@ManagedBean(name = "memberServiceImpl")
@ApplicationScoped
public class MemberServiceImpl extends Service {

	private static final long serialVersionUID = 1L;

	private MemberDao dao;

	public MemberServiceImpl() {
		this.dao = new MemberDao();
	}

	@Override
	public void setDao(DaoAdapter dao) {
		this.dao = (MemberDao) dao;
	}

	@Override
	public int getCount(SearchModel searchModel) throws SQLException {
		return dao.selectCount(searchModel);
	}

	public List<Member> getMemberList(SearchModel searchModel) throws SQLException {

		return dao.selectMemberList(searchModel);
	}

	public int updateServiceTicket(Model model) {

		return dao.updateServiceTicket(model);
	}
}
