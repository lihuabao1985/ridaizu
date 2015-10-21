package jp.co.asahi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 帳票(Excel)出力用ユーティリティクラス.
 *
 * @version 1.0
 * @author tadashi.takayama
 *
 */
public class Test {

//	private static final String format = "COMMENT ON COLUMN %s.%s IS '%s';";
	private static final String format1 = "\t/** %s */";
	private static final String format2 = "\tprivate String %s;";
	private static final String table = "w_shoukankijitsukanri2";

	public static boolean parseBoolean(String str) {

		return "1".equals(str);
	}

	public static void main(String... args) throws Exception {

//		List<String> list =getList();
//		Set<String> set = new HashSet<String> ();
//
//		for (String string : list) {
//
//			String str = string.replaceAll("[_]", "").toLowerCase();
//			set.add(str);
//
//			System.out.println(str);
//		}
//
//		System.out.println(String.format("list size: %d", list.size()));
//		System.out.println(String.format("set size: %d", set.size()));


		String filePath1 = "txt1.txt";
		String filePath2 = "txt2.txt";

		List<String> list1 = getStringByFile1(filePath1);
		List<String> list2 = getStringByFile2(filePath2);

		for (int i = 0; i < list1.size(); i++) {
			String str1 = list1.get(i);
			String str2 = list2.get(i);

			System.out.println(String.format(format1, str1));
			System.out.println(String.format(format2, tmp(str2)));
			System.out.println("");
		}


//        Workbook book;
//        book= Workbook.getWorkbook(new File("test.xlsx"));
	}

	private static String tmp(String str) {

		String[] strs = str.split("_");
		if (strs.length < 2)
			return strs[0];

		String tmpStr = "";
		for (int i = 0; i < strs.length; i++) {
			String string = strs[i];

			if (i == 0) {
				tmpStr += toLowerCaseInitial(string, true);
			} else {
				tmpStr += toLowerCaseInitial(string, false);
			}
		}

		return tmpStr;

	}

    /**
     * 将一个字符串的首字母改为大写或者小写
     *
     * @param srcString 源字符串
     * @param flag            大小写标识，ture小写，false大些
     * @return 改写后的新字符串
     */
    public static String toLowerCaseInitial(String srcString, boolean flag) {
            StringBuilder sb = new StringBuilder();
            if (flag) {
                    sb.append(Character.toLowerCase(srcString.charAt(0)));
            } else {
                    sb.append(Character.toUpperCase(srcString.charAt(0)));
            }
            sb.append(srcString.substring(1));
            return sb.toString();
    }


	public static List<String> getStringByFile2(String filePath) throws IOException {
        List<String> list = new ArrayList<String>();

		File file = new File(filePath);
		BufferedReader reader = null;
		try {
//			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
//				System.out.println("line " + line + ": " + tempString.substring(0, tempString.indexOf(" ")));
				line++;
				if (tempString.contains(" ")) {
					list.add(tempString.substring(0, tempString.indexOf(" ")));
				} else {
					list.add(tempString);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

        return  list;
	}


	public static List<String> getStringByFile1(String filePath) throws IOException {
		List<String> list = new ArrayList<String>();

		File file = new File(filePath);
		BufferedReader reader = null;
		try {
//			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
//				System.out.println("line " + line + ": " + tempString);
				line++;
				list.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

        return  list;
	}


	public static List<String> getList() {
		List<String> list = new ArrayList<String> ();
		list.add("v_6375_honninkakunin");
		list.add("v_6377_honninkakunin");
		list.add("v_anken_dale_flow_syuukei1");
		list.add("v_anken_dale_flow_syuukei1_1");
		list.add("v_anken_dale_flow_syuukei2");
		list.add("v_anken_list_shoukai");
		list.add("v_baikyaku_jiseki_list1");
		list.add("v_baikyaku_jiseki_list2_kisyu");
		list.add("v_baikyaku_jiseki_list3_tousibi");
		list.add("v_baikyaku_jiseki_list4_tousigaku");
		list.add("v_baikyaku_jiseki_list5_mijyoujyo");
		list.add("v_baikyaku_jiseki_list6");
		list.add("v_baikyaku_jiseki_list7_jyoujyo");
		list.add("v_baikyaku_soneki_meisai1");
		list.add("v_baikyaku_soneki_meisai2");
		list.add("v_baikyakueki_santei");
		list.add("v_baikyakueki_santei_1");
		list.add("v_baikyakueki_santei_2");
		list.add("v_baikyakueki_santei_chokkin1");
		list.add("v_baikyakueki_santei_chokkin2");
		list.add("v_baikyakueki_santei_chokkin2_1");
		list.add("v_baikyakueki_santei_chokkin2_2");
		list.add("v_baikyakueki_santei_chokkin3");
		list.add("v_baikyakueki_santei_kokyakugamen");
		list.add("v_baikyakueki_santei_kokyakugamen_1");
		list.add("v_baikyakueki_santei_merge");
		list.add("v_bunseki_hatune_koubo_gyoushu1");
		list.add("v_bunseki_hatune_koubo_gyoushu2");
		list.add("v_bunseki_hatune_koubo_shukanji1");
		list.add("v_bunseki_hatune_koubo_shukanji2");
		list.add("v_bunseki_hatune_koubo_sijyou1");
		list.add("v_bunseki_hatune_koubo_sijyou2");
		list.add("v_bunseki_hatune_koubo_todouhuken1");
		list.add("v_bunseki_hatune_koubo_todouhuken2");
		list.add("v_bunseki_ipoichiranhyo1");
		list.add("v_d_syouyo");
		list.add("v_deguchijoukyou04_hiexit_meigara");
		list.add("v_eigyou_nippou");
		list.add("v_fund_idou7");
		list.add("v_fund_idou8");
		list.add("v_fund_idou91");
		list.add("v_fund_idou92");
		list.add("v_fund_idou93");
		list.add("v_fund_idou930");
		list.add("v_fund_jissek3");
		list.add("v_fund_kijyunbi_zandaka1");
		list.add("v_fund_kijyunbi_zandaka2");
		list.add("v_fund_kijyunbi_zandaka3");
		list.add("v_genbutu_idou");
		list.add("v_genbutu_new03");
		list.add("v_genbutu_new031");
		list.add("v_genbutu_new04");
		list.add("v_genbutu_new05");
		list.add("v_genbutu_new05_1");
		list.add("v_genbutu_new06");
		list.add("v_genbutu_new07");
		list.add("v_genbutu_new070");
		list.add("v_genbutu_new08");
		list.add("v_genbutu_syukei_sansyouyou");
		list.add("v_genson1");
		list.add("v_genson2");
		list.add("v_genson31");
		list.add("v_genson32");
		list.add("v_genson33_syutokugo");
		list.add("v_genson34");
		list.add("v_genson35");
		list.add("v_genson3_syutokuji");
		list.add("v_genson4");
		list.add("v_genson9991");
		list.add("v_genson9992");
		list.add("v_genson_36_syutokumae");
		list.add("v_genson_a");
		list.add("v_haitoukin_kanri1");
		list.add("v_haitoukin_kanri2");
		list.add("v_haitoukin_kanri3");
		list.add("v_haitoukin_kanri4");
		list.add("v_helpmsg");
		list.add("v_henkou_lkiroku");
		list.add("v_hyouka_ikkatu_sansyou");
		list.add("v_irrjikkounendo_q6");
		list.add("v_jyoujyo_hoyuukabu_list");
		list.add("v_kabarai_tax_new0");
		list.add("v_kabarai_tax_new01");
		list.add("v_kabarai_tax_new02");
		list.add("v_kabarai_tax_new03");
		list.add("v_kabarai_tax_new04");
		list.add("v_kabarai_tax_new05");
		list.add("v_kansa_houjin");
		list.add("v_karibaraizeikin_qnew_sinhaitou");
		list.add("v_karibaraizeikin_qnew_sinrisoku");
		list.add("v_keiyaku_bunsyou");
		list.add("v_kessan_bps1");
		list.add("v_kessan_nyuryoku_check1");
		list.add("v_kessan_nyuryoku_check2");
		list.add("v_kessan_nyuryoku_check22");
		list.add("v_kessan_nyuryoku_check6");
		list.add("v_kessan_nyuryoku_check7");
		list.add("v_koukai_jisseki_list");
		list.add("v_koukai_jisseki_list_1");
		list.add("v_koukai_jisseki_samu_fand_koukainendo");
		list.add("v_koukai_jisseki_samu_fand_koukainendo_douji");
		list.add("v_koukai_jisseki_samu_fand_tousinendo");
		list.add("v_koukai_jisseki_samu_fand_tousinendo_douji");
		list.add("v_koukai_jisseki_samu_goukei_koukai_nendo");
		list.add("v_koukai_jisseki_samu_goukei_koukai_nendo_douji");
		list.add("v_koukai_jisseki_samu_goukei_tousi_nendo");
		list.add("v_koukai_jisseki_samu_goukei_tousi_nendo_douji");
		list.add("v_koukai_jisseki_samu_nendo3");
		list.add("v_koukaijisseki_init_01_init_tousi");
		list.add("v_koukaijisseki_init_02");
		list.add("v_koukaijisseki_init_03");
		list.add("v_koukaijisseki_init_04");
		list.add("v_koukaijisseki_init_05");
		list.add("v_koukaijisseki_init_06_fund");
		list.add("v_meigara_list");
		list.add("v_meigarabetu_sonekia");
		list.add("v_nissei_root_kanrihyou_tuikatuosihandan1");
		list.add("v_nissei_root_kanrihyou_tuikatuosihandan2");
		list.add("v_nissei_root_kanrihyou_tuikatuosihandan3");
		list.add("v_product_number1");
		list.add("v_product_number1_1");
		list.add("v_product_number2");
		list.add("v_product_number3");
		list.add("v_product_number4");
		list.add("v_product_number5");
		list.add("v_q01_acust_select1");
		list.add("v_q01_acust_select11");
		list.add("v_q01_acust_select14");
		list.add("v_q01_acust_select2");
		list.add("v_q01_acust_select2_1");
		list.add("v_q01_acust_select5");
		list.add("v_q11_d_acust");
		list.add("v_q11_meigara");
		list.add("v_q136_cf_jisseki_max");
		list.add("v_q13_kessann");
		list.add("v_q1611_shinmeigara_zandaka1");
		list.add("v_q1611_shinmeigara_zandaka1_1");
		list.add("v_q1612_shinmeigara_zandaka2");
		list.add("v_q1613_shinmeigara_zandaka3");
		list.add("v_q1614_shinmeigara_zandaka4");
		list.add("v_q1615_shinmeigara_zandaka5");
		list.add("v_q1616_shinmeigara_zandaka6");
		list.add("v_q161_acust_meigara_zandaka1");
		list.add("v_q162_acust_meigara_zandaka2");
		list.add("v_q163_acust_meigara_zandaka3");
		list.add("v_q164_acust_meigara_zandaka4");
		list.add("v_q164_acust_meigara_zandaka4_1");
		list.add("v_q166_acust_meigara_zandaka6");
		list.add("v_q167_acust_zandaka7");
		list.add("v_q167_acust_zandaka7pre");
		list.add("v_q167_acust_zandaka7pre2");
		list.add("v_q168_meigara_zandaka1");
		list.add("v_q168_meigara_zandaka2");
		list.add("v_q184_kabunushi_kousei_sihonseisaku");
		list.add("v_q18721_kabunushi_shihon_seisaku_goukei");
		list.add("v_q1872_kabunushi_shihon_seisaku");
		list.add("v_q18731_chokkin_hakkouzumi1");
		list.add("v_q18731_chokkin_hakkouzumi2");
		list.add("v_q1874_kabunushi_jika");
		list.add("v_q1875_kabunushi_jika");
		list.add("v_q1876_kabunushi_init_torihiki");
		list.add("v_q189_kabusiki_t_bunkatu");
		list.add("v_q19_d_yakuin");
		list.add("v_q222_t_anken");
		list.add("v_q223_anken_report");
		list.add("v_q275_tousi_keiyaku");
		list.add("v_q291_mendan_kiroku_search");
		list.add("v_q291_mendan_kiroku_touroku");
		list.add("v_q29975_insaidai_list5");
		list.add("v_q29975_insaidai_list5_1");
		list.add("v_q312_zandaka_syukei");
		list.add("v_q31_yusyou");
		list.add("v_q32_yukasyouken_torihiki");
		list.add("v_q37_kabu_up");
		list.add("v_q48_sai_up");
		list.add("v_q492_hyouka_zandaka_syukei");
		list.add("v_q651_unq");
		list.add("v_q652_unq_list");
		list.add("v_q653_unq_list");
		list.add("v_q654_unq_select");
		list.add("v_q656_unq_hassou_kouho");
		list.add("v_q6598_unq_mail");
		list.add("v_q659_enquete_mikaitou");
		list.add("v_q660_enquete_hassou");
		list.add("v_q894_shoukyaku_yotei");
		list.add("v_qf164_acust_meigara_zandaka");
		list.add("v_qf31_yusyou");
		list.add("v_qf32_yuka_syouken_torihiki");
		list.add("v_qqirr_jikkounendo_q1");
		list.add("v_qyukamemo");
		list.add("v_risoku_yotei1");
		list.add("v_risoku_yotei2");
		list.add("v_risoku_yotei3");
		list.add("v_risoku_yotei4");
		list.add("v_satei_init_syutokubi");
		list.add("v_sessyoku_rireki1");
		list.add("v_sessyoku_rireki2");
		list.add("v_sessyoku_rireki3");
		list.add("v_shain_touroku");
		list.add("v_shoukan_kijitu_kanri1");
		list.add("v_shoukan_kijitu_kanrid");
		list.add("v_shoukan_kijitu_kanrid_1");
		list.add("v_shoukan_kijitu_kanrid_2");
		list.add("v_shoukan_kijitu_kanrid_3");
		list.add("v_shouken_daikou");
		list.add("v_shozoku_company");
		list.add("v_shukanji");
		list.add("v_stat_q00_finance");
		list.add("v_stat_q01_finance");
		list.add("v_stat_q1_acust_goukei");
		list.add("v_stat_q4_union_acust_zandaka");
		list.add("v_stat_q821_d_acust_zandaka");
		list.add("v_stat_q82_union_zandaka");
		list.add("v_sub_renban_baibai_max");
		list.add("v_syoukan_kijitukanri_c");
		list.add("v_syouyo01");
		list.add("v_syouyo011");
		list.add("v_syouyo02");
		list.add("v_syouyo02_1");
		list.add("v_syouyo03");
		list.add("v_syouyo04");
		list.add("v_syouyo05");
		list.add("v_syouyo06");
		list.add("v_syouyo07");
		list.add("v_syouyo08");
		list.add("v_syouyo16");
		list.add("v_syouyo17");
		list.add("v_syouyo18");
		list.add("v_syouyo20");
		list.add("v_syouyo99");
		list.add("v_syouyo_haitou");
		list.add("v_syouyo_risoku");
		list.add("v_tairyou_hoyuu3");
		list.add("v_tairyou_hoyuu4");
		list.add("v_tairyou_hoyuu5");
		list.add("v_tairyou_hoyuu6");
		list.add("v_tairyou_hoyuu7");
		list.add("v_tairyou_hoyuu7_1");
		list.add("v_tairyou_hoyuu7_2");
		list.add("v_tairyou_hoyuu8");
		list.add("v_tairyou_hoyuu_syutoku_hiduke1");
		list.add("v_tairyou_hoyuu_syutoku_hiduke2");
		list.add("v_tairyou_hoyuu_syutoku_hiduke3_tousho");
		list.add("v_tairyou_hoyuu_syutoku_hiduke4");
		list.add("v_tairyou_hoyuu_syutoku_hiduke5_tuika");
		list.add("v_tairyou_hoyuu_syutoku_hiduke6");
		list.add("v_tairyou_hoyuu_syutoku_hiduke7_sankaiikousyutoku");
		list.add("v_tairyou_hoyuu_syutoku_hiduke9_list");
		list.add("v_tantou_rireki");
		list.add("v_tantou_shasu0");
		list.add("v_tantou_shasu1");
		list.add("v_tantou_shasu2");
		list.add("v_tantou_shasu3");
		list.add("v_tantou_shasu4");
		list.add("v_tantou_shasu5");
		list.add("v_tantou_shasu6");
		list.add("v_tousi_meisai_excel");
		list.add("v_tousi_meisai_excel_1");
		list.add("v_tousi_meisai_excel_samari");
		list.add("v_tousi_meisai_jyunbi1_excel");
		list.add("v_tousi_meisai_jyunbi2_excel");
		list.add("v_tousi_meisai_jyunbi3");
		list.add("v_tousidaki_card_anken_tantousha1");
		list.add("v_tousidaki_card_anken_tantousha2");
		list.add("v_tousisaki_card_anken_route");
		list.add("v_tousisaki_card_anken_route_jikkoubun");
		list.add("v_tousisaki_card_anken_route_jikkoubun0");
		list.add("v_tousisaki_card_anken_route_jyunbi");
		list.add("v_tousisaki_card_anken_route_zenbu");
		list.add("v_tousisaki_card_anken_route_zenbu0");
		list.add("v_tousisaki_card_base");
		list.add("v_tousisaki_card_chokkin_kessan1");
		list.add("v_tousisaki_card_chokkin_kessan2");
		list.add("v_tousisaki_card_chokkin_shihonseisaku1");
		list.add("v_tousisaki_card_chokkin_shihonseisaku2_hakkou_kakaku");
		list.add("v_tousisaki_card_chokkin_shihonseisaku3");
		list.add("v_tousisaki_card_chokkin_shihonseisaku4_kabusu");
		list.add("v_tousisaki_card_chokkin_zandaka1");
		list.add("v_tousisaki_card_chokkin_zandaka2");
		list.add("v_tousisaki_card_chokkin_zandaka3");
		list.add("v_tousisaki_card_chokkin_zandaka3_1");
		list.add("v_tousisaki_card_daihyousha");
		list.add("v_tousisaki_card_ikan");
		list.add("v_tousisaki_card_kessan");
		list.add("v_tousisaki_card_t_kessan1");
		list.add("v_tousisaki_card_t_kessan2");
		list.add("v_tousisaki_card_t_kessan_kijyun_data3");
		list.add("v_tousisaki_card_t_kessanbi_kijyun_data1");
		list.add("v_tousisaki_card_t_kessanbi_kijyun_data2");
		list.add("v_tousisaki_card_tousirireki");
		list.add("v_tousisaki_card_zandaka");
		list.add("v_tousisaki_card_zandaka_init");
		list.add("v_tousisaki_list0");
		list.add("v_tousisaki_list_torihikijisseki");
		list.add("v_tousisaki_syokai_tousigaku1");
		list.add("v_tousisaki_syokai_tousigaku2");
		list.add("v_tt_rogu");
		list.add("v_tyusyutu_9000_kakuduke_summary");
		list.add("v_tyusyutu_931_kakuduke1");
		list.add("v_tyusyutu_932_kakuduke2");
		list.add("v_unq_add");
		list.add("v_unq_add0");
		list.add("v_unq_add1");
		list.add("v_unq_add2");
		list.add("v_unq_add3");
		list.add("v_work_geiseki3_zanari");
		list.add("v_work_torihiki_jikkou2_koujyun");
		list.add("v_work_torihiki_jikkou_kani1");
		list.add("v_work_torihiki_jikkou_kani1_zentorihiki");
		list.add("v_work_torihiki_jikkou_kani3_rihaikinnozoku");
		list.add("v_work_torihiki_jikkou_kani_zandaka1");
		list.add("v_work_zandaka1");
		list.add("v_work_zandaka1_zanari");
		list.add("v_work_zandaka1_zanari_torihiki1");
		list.add("v_work_zandaka1_zanari_torihiki2");
		list.add("v_work_zandaka1_zanarituika0");
		list.add("v_work_zandaka1_zanarituika1");
		list.add("v_work_zandaka1_zanarituika2");
		list.add("v_work_zandaka1_zanarituika20");
		list.add("v_work_zandaka1_zanarituika3");
		list.add("v_work_zandaka1_zanarituika4");
		list.add("v_youkenren01_tousyo_syutokubi");
		list.add("v_youkenren02");
		list.add("v_youkenren05_zandaka");
		list.add("v_youkenren05_zandaka_1");
		list.add("v_youkenren_hakkousumi_kabusu1");
		list.add("v_youkenren_hakkousumi_kabusu2");
		list.add("v_youkenren_hakkousumi_kabusu3");
		list.add("v_youkenren_hakkousumi_kabusu4");
		list.add("v_youkenren_hakkousumi_kabusu5");
		list.add("v_youkenren_hakkousumi_kabusu6");
		list.add("v_youkenren_hakkousumi_kabusu7");
		list.add("v_youkenren_tantou_rireki_kensu02");
		list.add("v_yukasyouken_zandaka_q3_tousinegappi");
		list.add("v_yuzei_syoukyaku_meisai");
		list.add("v_yuzeisyoukyaku_meisai");

		return list;
	}

}
