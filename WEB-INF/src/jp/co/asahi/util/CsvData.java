package jp.co.asahi.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import jp.co.asahi.dao.db.OrderDetailDao;
import jp.co.asahi.model.OrderDetail;

import com.google.common.base.Strings;

class CsvData {

    public static void main(String[] A00){

    	/**
    	ExportItemlList2011-01-01_2013-05-07.csv
		ExportItemlList2013-05-07_2013-08-31.csv
		ExportItemlList2013-09-01_2013-12-31.csv
		ExportItemlList2014-01-01_2014-04-30.csv
		ExportItemlList2014-05-01_2014-08-31.csv
		ExportItemlList2014-09-01_2014-12-31.csv
		ExportItemlList2015-01-01_2015-03-31.csv
    	 */

		String[]  strs = new String[] {
		    	"ExportItemlList2011-01-01_2013-05-07.csv",
				"ExportItemlList2013-05-07_2013-08-31.csv",
				"ExportItemlList2013-09-01_2013-12-31.csv",
				"ExportItemlList2014-01-01_2014-04-30.csv",
				"ExportItemlList2014-05-01_2014-08-31.csv",
				"ExportItemlList2014-09-01_2014-12-31.csv",
				"ExportItemlList2015-01-01_2015-03-31.csv"
		};


        OrderDetailDao dao = null;
        try {

        	dao = new OrderDetailDao();

        	for (String string : strs) {
	            String L00 = string;
	            DataInputStream in = new DataInputStream(new FileInputStream(new File(L00)));
	            BufferedReader L01= new BufferedReader(new InputStreamReader(in,"GBK"));
	            String L02;
	            int count = 0;
	            while( (L02 = L01.readLine()) != null ) {
	            	if (count == 0) {
	            		count++;
	            		continue;
	            	}
	                String[] L03 = Arrays.copyOf(L02.split(","), 9);

	            	/** 订单编号 */
	            	String dingdanBianhao = filterStr(L03[0]);
	            	/** 标题 */
	            	String biaoti = filterStr(L03[1]);
	            	/** 价格 */
	            	double jiage = 0;
	            	if (!Strings.isNullOrEmpty(filterStr(L03[2]))) {
	            		jiage = Double.valueOf(filterStr(L03[2]));
	            	}
	            	/** 购买数量 */
	            	int goumaiShuliang = 0;
	            	if (!Strings.isNullOrEmpty(filterStr(L03[3]))) {
	            		goumaiShuliang = Integer.valueOf(filterStr(L03[3]));
	            	}
	            	/** 外部系统编号 */
	            	String waibuXitongBianhao = filterStr(L03[4]);
	            	/** 商品属性 */
	            	String shangpinShuxing = filterStr(L03[5]);
	            	/** 套餐信息 */
	            	String taocanXinxi = filterStr(L03[6]);
	            	/** 备注 */
	            	String beizhu = filterStr(L03[7]);
	            	/** 订单状态 */
	            	String dingdanZhuangtai = filterStr(L03[8]);
	            	/** 商家编码 */
	            	String shangjiaBianma = filterStr(L03[9]);

	            	OrderDetail orderDetail = new OrderDetail();
	            	orderDetail.setDingdanBianhao(dingdanBianhao);
	            	orderDetail.setBiaoti(biaoti);
	            	orderDetail.setJiage(jiage);
	            	orderDetail.setGoumaiShuliang(goumaiShuliang);
	            	orderDetail.setWaibuXitongBianhao(waibuXitongBianhao);
	            	orderDetail.setShangpinShuxing(shangpinShuxing);
	            	orderDetail.setTaocanXinxi(taocanXinxi);
	            	orderDetail.setBeizhu(beizhu);
	            	orderDetail.setDingdanZhuangtai(dingdanZhuangtai);
	            	orderDetail.setShangjiaBianma(shangjiaBianma);

	                System.out.printf("订单编号:%s %n", L03[0]);

	                dao.insert(orderDetail);
	            }
	            L01.close();

			}
        } catch(FileNotFoundException L04) {
        } catch(IOException L05) {
        } finally {
        	dao.closeConnection();
        }
    }

    public static String filterStr(String str) {

    	if (str == null)
    		return null;

    	return str.replaceAll("\"", "");
    }
}