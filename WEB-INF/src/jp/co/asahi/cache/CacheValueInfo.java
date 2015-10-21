package jp.co.asahi.cache;

import java.util.Date;

public class CacheValueInfo {
	enum CacheType{
		AbsoluteTime,
		None
	}
	private Object value;
	private long absoluteTime = 0;
	private CacheType cacheType;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public long getAbsoluteTime() {
		return absoluteTime;
	}
	public void setAbsoluteTime(long absoluteTime) {
		this.absoluteTime = absoluteTime;
	}

	public CacheValueInfo(Object value){
		this.value = value;
		this.cacheType = CacheType.None;
	}
	public CacheValueInfo(Object value,Date absoluteTime){
		this.value = value;
		this.absoluteTime = absoluteTime.getTime();
		this.cacheType = CacheType.AbsoluteTime;
	}
	public boolean checkTime(){
		if(cacheType == CacheType.AbsoluteTime){
			return new Date().getTime() > absoluteTime? false: true;
		}
		return true;
	}
}
