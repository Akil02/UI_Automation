package OrangeHRM_SmokeTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import Common.Base;
import pageobject.HomePage;
import pageobject.Maintenance_Page;
import pageobject.Sky_scanner_Frontpage;
import pageobject.Start_Page;

public class Sample {
    static WebDriver driver = null;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
       driver = new Base().Driver_setup("Ordhrd");
       new Start_Page(driver).Login();
       new HomePage(driver).Maint();
       new Maintenance_Page(driver).Adm_Access();
	}

}
