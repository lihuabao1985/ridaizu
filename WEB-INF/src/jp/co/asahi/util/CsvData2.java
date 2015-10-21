package jp.co.asahi.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Arrays;

import jp.co.asahi.dao.db.OrderDao;
import jp.co.asahi.model.Order;

import com.google.common.base.Strings;

public class CsvData2 {

	public static void main(String[] A00){

    	/**
    	ExportOrderList2011-01-01_2013-05-07.csv
		ExportOrderList2013-05-07_2013-08-31.csv
		ExportOrderList2013-09-01_2013-12-31.csv
		ExportOrderList2014-01-01_2014-04-30.csv
		ExportOrderList2014-05-01_2014-08-31.csv
		ExportOrderList2014-09-01_2014-12-31.csv
		ExportOrderList2015-01-01_2015-03-31.csv
    	 */

		String[]  strs = new String[] {
		    	"ExportOrderList2011-01-01_2013-05-07.csv",
				"ExportOrderList2013-05-07_2013-08-31.csv",
				"ExportOrderList2013-09-01_2013-12-31.csv",
				"ExportOrderList2014-01-01_2014-04-30.csv",
				"ExportOrderList2014-05-01_2014-08-31.csv",
				"ExportOrderList2014-09-01_2014-12-31.csv",
				"ExportOrderList2015-01-01_2015-03-31.csv"
		};


        OrderDao dao = null;
        try {

        	dao = new OrderDao();

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
	                String[] L03 = Arrays.copyOf(L02.split(","), 26);

	            	/** 订单编号 */
	            	 String dingdanBianhao = filterStr(L03[0]);
	            	/** 买家会员名 */
	            	 String maijiaHuiyuanming = filterStr(L03[1]);
	            	/** 买家支付宝账号 */
	            	 String maijiaZhifubaoZhanghao = filterStr(L03[2]);
	            	/** 买家应付货款 */
	            	 double maijiaYingfuHuokuan = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[3]))) {
	            		 maijiaYingfuHuokuan = Double.valueOf(filterStr(L03[3]));
	            	 }
	            	/** 买家应付邮费 */
	            	 double maijiaYingfuYoufei = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[4]))) {
	            		 maijiaYingfuYoufei = Double.valueOf(filterStr(L03[4]));
	            	 }
	            	/** 买家支付积分 */
	            	 int maijiaZhifuJifen = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[5]))) {
	            		 maijiaZhifuJifen = Integer.valueOf(filterStr(L03[5]));
	            	 }
	            	/** 总金额 */
	            	 double zongjine = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[6]))) {
	            		 zongjine = Double.valueOf(filterStr(L03[6]));
	            	 }
	            	/** 返点积分 */
	            	 int fandianJifen = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[7]))) {
	            		 fandianJifen = Integer.valueOf(filterStr(L03[7]));
	            	 }
	            	/** 买家实际支付金额 */
	            	 double maijiaShijiZhifuJine = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[8]))) {
	            		 maijiaShijiZhifuJine = Double.valueOf(filterStr(L03[8]));
	            	 }
	            	/** 买家实际支付积分 */
	            	 int maijiaShijiZhifuJifen = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[9]))) {
	            		 maijiaShijiZhifuJifen = Integer.valueOf(filterStr(L03[9]));
	            	 }
	            	/** 订单状态 */
	            	 String dingdanZhuangtai = filterStr(L03[10]);
	            	/** 买家留言 */
	            	 String maijiaLiuyan = filterStr(L03[11]);
	            	/** 收货人姓名 */
	            	 String shouhuorenXingming = filterStr(L03[12]);
	            	/** 收货地址 */
	            	 String shouhuoDizhi = filterStr(L03[13]);
	            	/** 运送方式 */
	            	 String yunsongFangshi = filterStr(L03[14]);
	            	/** 联系电话 */
	            	 String lianxiDianhua = filterStr(L03[15]);
	            	/** 联系手机 */
	            	 String lianxiShouji = filterStr(L03[16]);
	            	/** 订单创建时间 */
	            	 Timestamp dingdanChuangjianShijian = null;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[17]))) {
	            		 dingdanChuangjianShijian = DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, filterStr(L03[17]));
	            	 }
	            	/** 订单付款时间 */
	            	 Timestamp dingdanZhifuShijian = null;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[18]))) {
	            		 dingdanZhifuShijian = DateUtil.stringToTimestamp(DateUtil.LONG_DATE_HYPHEN, filterStr(L03[18]));
	            	 }
	            	/** 宝贝标题 */
	            	 String shangpinBiaoti = filterStr(L03[19]);
	            	/** 宝贝种类 */
	            	 String shangpinZhonglei = filterStr(L03[20]);
	            	/** 物流单号 */
	            	 String wuliuDanhao = filterStr(L03[21]);
	            	/** 物流公司 */
	            	 String wuliuGongsi = filterStr(L03[22]);
	            	/** 订单备注 */
	            	 String dingdanBeizhu = filterStr(L03[23]);
	            	/** 宝贝总数量 */
	            	 int shangpinZongshuliang = 0;
	            	 if (!Strings.isNullOrEmpty(filterStr(L03[24]))) {
	            		 shangpinZongshuliang = Integer.valueOf(filterStr(L03[24]));
	            	 }
	            	/** 店铺Id */
	            	 String dianpuId = filterStr(L03[25]);
	            	/** 店铺名称 */
	            	 String dianpuMingcheng = filterStr(L03[26]);

	            	 Order order = new Order();

	        		order.setDingdanBianhao(dingdanBianhao);
	        		order.setMaijiaHuiyuanming(maijiaHuiyuanming);
	        		order.setMaijiaZhifubaoZhanghao(maijiaZhifubaoZhanghao);
	        		order.setMaijiaYingfuHuokuan(maijiaYingfuHuokuan);
	        		order.setMaijiaYingfuYoufei(maijiaYingfuYoufei);
	        		order.setMaijiaZhifuJifen(maijiaZhifuJifen);
	        		order.setZongjine(zongjine);
	        		order.setFandianJifen(fandianJifen);
	        		order.setMaijiaShijiZhifuJine(maijiaShijiZhifuJine);
	        		order.setMaijiaShijiZhifuJifen(maijiaShijiZhifuJifen);
	        		order.setDingdanZhuangtai(dingdanZhuangtai);
	        		order.setMaijiaLiuyan(maijiaLiuyan);
	        		order.setShouhuorenXingming(shouhuorenXingming);
	        		order.setShouhuoDizhi(shouhuoDizhi);
	        		order.setYunsongFangshi(yunsongFangshi);
	        		order.setLianxiDianhua(lianxiDianhua);
	        		order.setLianxiShouji(lianxiShouji);
	        		order.setDingdanChuangjianShijian(dingdanChuangjianShijian);
	        		order.setDingdanZhifuShijian(dingdanZhifuShijian);
	        		order.setShangpinBiaoti(shangpinBiaoti);
	        		order.setShangpinZhonglei(shangpinZhonglei);
	        		order.setWuliuDanhao(wuliuDanhao);
	        		order.setWuliuGongsi(wuliuGongsi);
	        		order.setDingdanBeizhu(dingdanBeizhu);
	        		order.setShangpinZongshuliang(shangpinZongshuliang);
	        		order.setDianpuId(dianpuId);
	        		order.setDianpuMingcheng(dianpuMingcheng);

	                System.out.printf("订单编号:%s \n", L03[0]);

	                dao.insert(order);
	            }
	            L01.close();

			}
        } catch(FileNotFoundException L04) {
//            System.out.println(L00 + "が見つかりませでした。");
        } catch(IOException L05) {
//            System.out.println(L00 + "を読み込めませんでした。");
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