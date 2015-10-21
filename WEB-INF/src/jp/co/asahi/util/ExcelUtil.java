package jp.co.asahi.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.asahi.dao.db.ZaituDetailDao;
import jp.co.asahi.model.ZaituDetail;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * 帳票(Excel)出力用ユーティリティクラス.
 *
 * @version 1.0
 * @author tadashi.takayama
 *
 */
public class ExcelUtil {

	/**
	 * 读取一个excel文件的内容
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// extractor();
		readTable();
	}

	// 通过对单元格遍历的形式来获取信息 ，这里要判断单元格的类型才可以取出值
	public static void readTable() throws Exception {
		InputStream ips = new FileInputStream("10.10到货单.xls");
		HSSFWorkbook wb = new HSSFWorkbook(ips);
		HSSFSheet sheet = wb.getSheetAt(0);
		List<ZaituDetail> zaituDetailList = new ArrayList<ZaituDetail>();
		boolean b = true;
		for (Iterator ite = sheet.rowIterator(); ite.hasNext();) {

			HSSFRow row = (HSSFRow) ite.next();

			if (b) {
				b = false;
				continue;
			}

			if (row.getCell(0) == null) {
				continue;
			}


			ZaituDetail zaitu = new ZaituDetail();
			zaitu.setGoodsBarcode(String.valueOf((long) row.getCell(1).getNumericCellValue()));
			zaitu.setCount(Integer.valueOf(String.valueOf((long) row.getCell(2).getNumericCellValue())));

			zaituDetailList.add(zaitu);
//			System.out.print(row.getCell(0).getRichStringCellValue().toString() + " ");
//			System.out.print(String.valueOf((long) row.getCell(1).getNumericCellValue()) + " ");
//			System.out.print(String.valueOf((long) row.getCell(2).getNumericCellValue()) + " ");
//			System.out.println();
		}


		ZaituDetailDao dao = new ZaituDetailDao();

		Timestamp date = DateUtil.stringToTimestamp(DateUtil.SHORT_DATE, "2015/10/02");

		for (ZaituDetail zaituDetail : zaituDetailList) {
			int count = dao.selectCount(date, zaituDetail.getGoodsBarcode(), zaituDetail.getCount());
			System.out.println(String.format("barcode[%s] count: %d", zaituDetail.getGoodsBarcode(), count));
		}


	}

	// 直接抽取excel中的数据
	public static void extractor() throws Exception {
		InputStream ips = new FileInputStream("d://test2-1.xls");
		HSSFWorkbook wb = new HSSFWorkbook(ips);
		ExcelExtractor ex = new ExcelExtractor(wb);
		ex.setFormulasNotResults(true);
		ex.setIncludeSheetNames(false);
		String text = ex.getText();
		System.out.println(text);
	}
}
