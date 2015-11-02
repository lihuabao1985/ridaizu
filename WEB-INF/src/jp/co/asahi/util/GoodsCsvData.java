package jp.co.asahi.util;

import java.io.FileInputStream;
import java.sql.SQLException;

import jp.co.asahi.dao.db.GoodsDao;
import jp.co.asahi.model.Goods;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.base.Strings;

public class GoodsCsvData {
	public static void main(String[] args) {

		GoodsDao dao = null;

		try {
			FileInputStream fi = new FileInputStream("商品资料 (1).xlsx");
			Workbook book = new XSSFWorkbook(fi);
			// Workbook book =new HSSFWorkbook(fi);
			fi.close();
			// for(Sheet sheet:book){ // XSSFWorkbookの場合

			dao = new GoodsDao();
			dao.beginTransaction();

			String format = "商品名称【%s】	销售属性【%s】	商家编码【%s】	进价【%s】	销售价【%s】	成本价【%s】	重量【%s】";

			boolean b = false;
			for (int s = 0; s < book.getNumberOfSheets(); ++s) { // 全シートをなめる(※)
				Sheet sheet = book.getSheetAt(s);
				// sheet.setForceFormulaRecalculation(true); // 数式解決(※２)
				System.out.println("--- " + sheet.getSheetName() + " ---");

				for (Row row : sheet) { // 全行をなめる
//					for (Cell cell : row) { // 全セルをなめる
//						System.out.print(getStr(cell) + " ");
//					}
//					System.out.println("");

					if (!b) {
						b = true;
						continue;
					}

					String s1 = getStr(row.getCell(0)).replaceAll("\n", "");
					String s2 = getStr(row.getCell(1));
					String s3 = getStr(row.getCell(2));
					String s4 = getStr(row.getCell(3));
					String s5 = getStr(row.getCell(4));
					String s6 = getStr(row.getCell(5));
					String s7 = getStr(row.getCell(6));

					System.out.println(String.format(format, s1,  s2,  s3,  s4,  s5,  s6,  s7));

					Goods goods = new  Goods();
					goods.setName(s1);
					goods.setTaobaoName(s1);
					goods.setXiaoshouShuxing(s2);
					goods.setGoodsCode(s3);
					goods.setBarcode(s3);
					if (!Strings.isNullOrEmpty(s4)) {
						goods.setPrice(Double.valueOf(s4));
					}

					if (!Strings.isNullOrEmpty(s5)) {
						goods.setSalesPrice(Double.valueOf(s5));
					}

					if (!Strings.isNullOrEmpty(s6)) {
						goods.setChengbenjia(Double.valueOf(s6));
					}

					if (!Strings.isNullOrEmpty(s7)) {
						goods.setWeight(Double.valueOf(s7).intValue());
					}

					if (dao.insert(goods) < 1) {
						throw new SQLException("insert goods error.");
					}
				}
			}

			dao.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace(System.err);
			dao.rollbackTransaction();
			System.exit(1);
		} finally {
			if (dao != null) {
				dao.closeConnection();
			}
		}
		System.exit(0);
	}

	public static String getStr(Cell cell) { // データ型毎の読み取り
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
			// return cell.getStringCellValue();(※）
		case Cell.CELL_TYPE_NUMERIC:
			//Double.toString(cell.getNumericCellValue());
			return String.valueOf((long)cell.getNumericCellValue()) ;
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		}
		return "";// CELL_TYPE_BLANK,CELL_TYPE_ERROR
	}
}