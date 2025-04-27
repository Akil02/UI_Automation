package OrangeHRM_SmokeTest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Base;
import pageobject.HomePage;
import pageobject.RecruitmentPage;
import pageobject.Start_Page;

public class Testcase3_Recruitment {

	public WebDriver driver = null;
    
	@Parameters("Orange")
    @BeforeClass
    public void Start(String val) throws Exception {
	  	driver = new Base().Driver_setup(val);
		new Start_Page(driver).Login();
    }
	 
	 @Test
	 public void Recruitment_HiredList() throws Exception {
		 new HomePage(driver).Recruitment();
		 new RecruitmentPage(driver).HiredList();
		 Thread.sleep(1000);
	 }
	 
	 @Test
	 public void Recruitment_RejectionList1() throws Exception {
		 new RecruitmentPage(driver).RejectedList();
	 }
	 
	 @Test(dependsOnMethods = {"Recruitment_RejectionList1"})
	 public void Recruitment_Rejected1() throws Exception {
		 new RecruitmentPage(driver).Rejected();
		 System.out.println("Completed");
	 }
	 
	 @AfterClass
	 public void End() throws SQLException {
	    driver.close();
	 }
}
