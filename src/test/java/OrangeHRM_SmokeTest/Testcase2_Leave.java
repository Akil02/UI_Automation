package OrangeHRM_SmokeTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Base;
import pageobject.Admin_Page;
import pageobject.HomePage;
import pageobject.LeavePage;
import pageobject.Start_Page;

public class Testcase2_Leave {

	public WebDriver driver = null;
    
	@Parameters("Orange")
    @BeforeClass
    public void Start(String val) throws Exception {
	  	driver = new Base().Driver_setup(val);
		new Start_Page(driver).Login();
    }
    
    @Test
    public void Leave_Accept() throws Exception {
    	new HomePage(driver).Leave();
    	new LeavePage(driver).ApproveLeave();
    }
    
    @AfterClass
    public void End() {
    	driver.close();
    }
}
