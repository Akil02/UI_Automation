package OrangeHRM_SmokeTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Base;
import Common.Listener;
import pageobject.Admin_Page;
import pageobject.HomePage;
import pageobject.Personal_Details;
import pageobject.Pim_Page;
import pageobject.Start_Page;

public class Testcase4_PersonalInfo {

	public WebDriver driver = null;
    public Admin_Page apage = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public Row row = null;
    public Cell cel = null;
    
    @Parameters("Orange")
    @BeforeClass
    public void Start(String val) throws Exception {
    	double c2 = Math.random()*1000000;
	  	driver = new Base().Driver_setup(val);
		new Start_Page(driver).Login();
		workbook = new Base().excel();
		sheet = workbook.getSheetAt(1);
		row = sheet.getRow(1);	
		Cell cel = row.createCell(3);
		cel.setCellValue((int)c2);
		new HomePage(driver).Pim();
    }
    
    @Test(priority = 1)
    public void PIM_AddEmployee() throws Exception {
    	int a = (int)row.getCell(3).getNumericCellValue();
    	String a1 = ""+a;
    	new Pim_Page(driver).Add_Employee();
    	new Pim_Page(driver).First_Name(row.getCell(0).getStringCellValue());
    	new Pim_Page(driver).Mid_Name(row.getCell(1).getStringCellValue());
    	new Pim_Page(driver).Last_Name(row.getCell(2).getStringCellValue());
    	new Pim_Page(driver).Emp_ID(a1);
    	new Pim_Page(driver).Save_Button();
    }
    
    @Test(priority = 2)
    public void PersonalDetails() throws Exception {
    	double o = row.getCell(4).getNumericCellValue();
    	int y1 = (int)row.getCell(6).getNumericCellValue();
    	int d1 = (int)row.getCell(8).getNumericCellValue();
    	int o1 = (int)o;
    	String oid = ""+o1;
    	String nat = row.getCell(5).getStringCellValue();
    	String y = ""+y1;
    	String d = ""+d1;
    	String m = row.getCell(7).getStringCellValue();
    	String ge = row.getCell(9).getStringCellValue();
    	String b = row.getCell(10).getStringCellValue();
    	System.out.println(ge);
    	new Personal_Details(driver).PersonlD(oid,nat,y,m,d,ge,b);
    }
    
 /*   @Test(priority = 2)
    public void Image_Add() throws Exception {
    	new Personal_Details(driver).Profile_Image();
    }  */
    
    @AfterClass
    public void End() {
    	driver.close();
    } 
}
