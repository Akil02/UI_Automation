package OrangeHRM_SmokeTest;

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
import pageobject.Maintenance_Page;
import pageobject.Start_Page;

public class Testcase5_Maintenance {

	public WebDriver driver;
    public Admin_Page apage = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public Row row = null;
    public Cell cel = null;
    
	@Parameters("Orange")
    @BeforeClass
    public void Start(String val) throws Exception {
		workbook = new Base().excel();
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(1);
	  	driver = new Base().Driver_setup(val);
		new Start_Page(driver).Login();
    }
	
	@Test
	public void AccessRecords() throws Exception {
		 String name = row.getCell(1).getStringCellValue();
		 new HomePage(driver).Maint();
		 new Maintenance_Page(driver).Adm_Access();
		 new Maintenance_Page(driver).GetEmployee(name);
	}
	
	@AfterClass
    public void End() {
    	driver.close();
    }  
}
