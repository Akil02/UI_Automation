package OrangeHRM_SmokeTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Base;
import pageobject.HomePage;
import pageobject.Start_Page;
import pageobject.Admin_Page;

public class Testcase1_Admin{
    public WebDriver driver;
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
		row = sheet.getRow(1);
		double a = Math.ceil(Math.random()*10000000);
        int b = (int)a;
        String g = ""+b;
        Cell cell = row.createCell(3);
        cell.setCellValue(g+"A");
        new Base().WrightExcel(workbook);
		
    }
    
    @Test(priority = 1)
    public void Admin_UserRole() throws Exception {
    	System.out.println("Check "+row.getCell(0));
        apage = new Admin_Page(driver);
		new HomePage(driver).Adminbutton();
		apage.UserRole(row.getCell(0).getStringCellValue());
    }

    @Test(priority = 2)
    public void Admin_EmployeeName() throws Exception {
    	System.out.println("Check "+row.getCell(1));
    	apage = new Admin_Page(driver);
		apage.EmployeeName(row.getCell(1).getStringCellValue()); 
    }  
    @Test(priority = 3)
    public void Admin_Status() throws Exception {
    	System.out.println("Check "+row.getCell(2));
    	apage = new Admin_Page(driver);
    	apage.Status(row.getCell(2).getStringCellValue());
    }
    
    @Test(priority = 4)
    public void Admin_Username() throws Exception {	
    	System.out.println("Check "+row.getCell(3));
    //	String c = ""+row.getCell(3).getNumericCellValue();
    	apage = new Admin_Page(driver);
    	try {
		  apage.UserName(row.getCell(3).getStringCellValue());
    	}
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    @Test(priority = 5)
    public void Admin_Password() throws Exception {
    	System.out.println("Check "+row.getCell(4));
    	apage = new Admin_Page(driver);  	
		apage.Password(row.getCell(4).getStringCellValue());
    }
    
    @Test(priority = 6)
    public void Admin_ConfirmPassword() throws Exception {
    	System.out.println("Check "+row.getCell(5));
    	apage = new Admin_Page(driver);
		apage.ConfirmPassword(row.getCell(5).getStringCellValue()); 
    }
    
    @Test(dependsOnMethods = {"Admin_ConfirmPassword"})
    public void Admin_SystemUsername() throws Exception {
    	apage = new Admin_Page(driver);
		apage.System_Username(row.getCell(3).getStringCellValue());
    }
    @AfterClass
    public void End() {
    	driver.close();
    }  
}
