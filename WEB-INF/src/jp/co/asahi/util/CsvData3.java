package jp.co.asahi.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import jp.co.asahi.dao.impl.ZaituDao;
import jp.co.asahi.dao.impl.ZaituDetailDao;
import jp.co.asahi.model.Zaitu;
import jp.co.asahi.model.ZaituDetail;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class CsvData3 {

	public static void main(String[] A00){

		String[]  strs = new String[] {
				"日代族_在途商品 - 在途列表 (4).csv"
		};


        ZaituDao zaituDao = null;
        ZaituDetailDao zaituDetailDao = null;
        try {

        	zaituDao = new ZaituDao();
        	zaituDetailDao = new ZaituDetailDao();

        	for (String string : strs) {

        		String L00 = string;
	            DataInputStream in = new DataInputStream(new FileInputStream(new File(L00)));
	            BufferedReader L01= new BufferedReader(new InputStreamReader(in,"utf-8"));
	            String L02;

	            Multimap<String,ZaituDetail> zaituDetailMultimap = ArrayListMultimap.create();

	            int lineCount = 0;

	            while( (L02 = L01.readLine()) != null ) {

	            	if (lineCount == 0) {
	            		lineCount++;
	            		continue;
	            	}
	            	lineCount++;
	            	System.out.println(lineCount);

	            	String[] goodsList = Arrays.copyOf(L02.split(","), 7);

	            	String date = goodsList[0];

	            	if (Strings.isNullOrEmpty(date)) {
	            		continue;
	            	}

	            	String barcode = goodsList[1];
	            	String goodsCount = goodsList[3];
	            	String price = goodsList[4];
	            	String beizhu = goodsList[6];

	            	ZaituDetail zaitu = new ZaituDetail();
	            	zaitu.setZaituDateString(date);
	            	zaitu.setGoodsBarcode(barcode);

	            	if (!Strings.isNullOrEmpty(goodsCount)) {
	            		zaitu.setCount(Integer.valueOf(goodsCount.trim()));
	            	}

	            	if (!Strings.isNullOrEmpty(price)) {
	            		zaitu.setPrice(Double.valueOf(price.trim()));
	            	}
	            	zaitu.setBeizhu(beizhu);

	            	zaituDetailMultimap.put(date, zaitu);
	            }
	            L01.close();

	            Zaitu zaitu = null;

	            Timestamp currentDatetime = DateUtil.getCurrentDateTime();

	            for (String date : zaituDetailMultimap.keySet()) {

	            	List<ZaituDetail> zaituDetailList = (List<ZaituDetail>)zaituDetailMultimap.get(date);

	            	System.out.println(String.format("%s size : %d", date, zaituDetailList.size()));

	            	int goodsTotalCount = 0;
	            	double goodsTotalPrice = 0;

	            	for (ZaituDetail zaituDetail : zaituDetailList) {
	            		goodsTotalCount += zaituDetail.getCount();
	            		goodsTotalPrice += (zaituDetail.getPrice() * zaituDetail.getCount());
					}

					zaitu = new Zaitu();
					zaitu.setZaituDate(DateUtil.stringToTimestamp(DateUtil.SHORT_DATE_HYPHEN, date));
					zaitu.setGoodsTotalCount(goodsTotalCount);
					zaitu.setGoodsTotalPrice(goodsTotalPrice);

					zaitu.setRgtOpt("admin");
					zaitu.setRgtDate(currentDatetime);
					zaitu.setUpdOpt("admin");
					zaitu.setUpdDate(currentDatetime);

					zaituDao.insert(zaitu);

	            	for (ZaituDetail zaituDetail : zaituDetailList) {

	            		zaituDetail.setZaituId(zaituDao.selectId(zaitu.getZaituDate()));
	            		zaituDetailDao.insert(zaituDetail);
					}

				}

			}
        } catch(FileNotFoundException L04) {
        		L04.printStackTrace();
//            System.out.println(L00 + "が見つかりませでした。");
        } catch(Exception L05) {
        	L05.printStackTrace();
//            System.out.println(L00 + "を読み込めませんでした。");
        } finally {
        }
    }

     public static String filterStr(String str) {

    	if (str == null)
    		return null;

    	return str.replaceAll("\"", "");
    }
}