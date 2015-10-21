package jp.co.asahi.dao;


public interface IDaoDelete {

	public int delete(int... id);
	
	public int deleteList(int... id);
	
	public int deleteAll();
	
}
