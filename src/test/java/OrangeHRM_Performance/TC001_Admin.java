package OrangeHRM_Performance;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Base;
import pageobject.Admin_Page;
import pageobject.HomePage;
import pageobject.Start_Page;

public class TC001_Admin {

	WebDriver driver;
    public Admin_Page apage = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public Row row = null;
    public Cell cel = null;
    
    
    @Parameters("Orange")
    @BeforeClass
    public void Start(String val) throws Exception {
	  	driver = new Base().Driver_setup(val);
		new Start_Page(driver).Login();
		workbook = new Base().excel();
		sheet = workbook.getSheetAt(0);
		
    }
    
    @Test
    public void Admin_Add_New_User() throws Exception {
    	new HomePage(driver).Adminbutton();
    	int last = sheet.getLastRowNum();
    	for(int i = 1;i<=last;i++) {
    		row = sheet.getRow(i);
    		double a = Math.ceil(Math.random()*10000000);
            int b = (int)a;
            String g = ""+b;
            Cell cell = row.createCell(3);
            cell.setCellValue("A"+g);
            new Base().WrightExcel(workbook);
            new Admin_Page(driver).Add_User(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), 
            row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue());
    	}
    }
    
    @AfterClass
    public void End() {
    	driver.close();
    }
}
