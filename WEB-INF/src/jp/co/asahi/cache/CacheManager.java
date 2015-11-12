package jp.co.asahi.cache;

import java.util.Calendar;
import java.util.HashMap;

import jp.co.asahi.config.Config;

public final class CacheManager {

	private static final int CACHE_EXPIRE_TIME = Config.getInt("CACHE_EXPIRE_TIME", 10);

	public static enum CacheKey{
		UnitPrice,
		ConsigneeCorList,
		CorpOceanList,
		CorjpNoList,
		VesselNameList,
		VesselCorList,
		WarehouseNameList,
		ContainerSizeSelectItemList,
		OhnoSelectItemList,
		CurrencyTypeSelectItemList,
		PackageKindSelectItemList,
		PortOfDischargeSelectItemList,
		PortOfLoadingSelectItemList,
		TransportTypeSelectItemList,
		PaymentTypeSelectItemList,
		MeimuSelectItemList,
		NaccsDictionaryMap,
		GradeList,
		ZaituSelectItemList,
		DailiSelectItemList,
		GoodsSelectItemList,
		PifuTypeSelectItemList,
		NianlingcengSelectItemList,
		GoodsTypeSelectItemList,
		JiageduanSelectItemList
	}
	private CacheManager(){
	}
	private static HashMap<String, CacheValueInfo> data = new HashMap<String, CacheValueInfo>();
	/**
	 * Set value by key into cache
	 *
	 * @param key
	 *            Cache key
	 * @param value
	 *            Cache value
	 */
	public static void set(CacheKey key,Object value){
		set(key.toString(),value,CACHE_EXPIRE_TIME);
	}
	/**
	 * Set value by key into cache
	 *
	 * @param key
	 *            Cache key
	 * @param value
	 *            Cache value
	 * @param minutes
	 *            Expiration time
	 */
	public static void set(CacheKey key,Object value,int minutes){
		set(key.toString(),value,minutes);
	}
	/**
	 * Set value by key into cache
	 *
	 * @param key
	 *            Cache key
	 * @param value
	 *            Cache value
	 */
	public static void set(String key, Object value) {
		set(key,value,CACHE_EXPIRE_TIME);
	}

	/**
	 * Set value by key into cache
	 *
	 * @param key
	 *            Cache key
	 * @param value
	 *            Cache value
	 * @param minutes
	 *            Expiration time
	 */
	public static void set(String key, Object value, int minutes) {
		//キャッシュ時間が0分以上の場合だけキャッシュする
		if(minutes > 0){
			synchronized (data) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, minutes);
				data.put(key, new CacheValueInfo(value, cal.getTime()));
			}
		}
	}

	/**
	 * Get value by key from cache
	 *
	 * @param key
	 *            Cache key
	 * @return Cache key or null
	 */
	public static Object get(String key) {
		CacheValueInfo cache = data.get(key);
		if (cache == null || !cache.checkTime()){
			return null;
		}
		return cache.getValue();
	}
	/**
	 * Get value by key from cache
	 *
	 * @param key
	 *            Cache key
	 * @return Cache key or null
	 */
	public static Object get(CacheKey key){
		return get(key.toString());
	}
}
