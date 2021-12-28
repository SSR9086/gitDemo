package excelIntegration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	public ArrayList<String> getExcelData(String sheetName, String columnName, String testcaseName) throws IOException {

		ArrayList<String> excelData = new ArrayList<String>();

		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(
				"C:\\Automation\\Workspace\\RestAssured\\AutomationFramework\\src\\test\\java\\excelIntegration\\ExcelData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheets = wb.getNumberOfSheets();
		// System.out.println("outside loop");

		for (int i = 0; i < sheets; i++) {
			if (wb.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();// Sheet is a collection
														// of rows
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();// Row is a
																// collection of
																// cells
				int k = 0;
				int column = 0;
				while (cell.hasNext()) {
					Cell value = cell.next();

					if (value.getStringCellValue().equalsIgnoreCase(columnName)) {
						column = k;
						// System.out.println("reached while-if loop");
					}
					k++;
				}
				System.out.println(column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
						Cell c=cv.next();
						if (c.getCellType()==CellType.STRING)
						{
							excelData.add(c.getStringCellValue());
						}else{
							excelData.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						
						}
							
							
						}
					}

				}

			}

		}

		return excelData;

	}

	public static void main(String args[]) throws IOException {
		
		DataDriven ddObj= new DataDriven();
	ArrayList<String> data=	ddObj.getExcelData("Rest Assured", "TestCases","TestCase3");
	for (int i=0;i<data.size();i++){
	System.out.println(data.get(i)); 
	}
	
	}

}
