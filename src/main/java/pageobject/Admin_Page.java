package pageobject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Common.Base;


public class Admin_Page {
    public WebDriver driver = null;
    public Statement statement = null;
    public ResultSet resultSet = null;
    public Connection connection = null;
    Base wait = new Base();
    
	By AddUser_btn = By.xpath("//*[@class = 'orangehrm-header-container']//button");
	By User_dropdown = By.xpath("//*[@class = 'oxd-form-row']//div//div[1]//div//div[2]//div//div//div");
	By Emp_name = By.xpath("//div[@class = 'oxd-autocomplete-wrapper']//div//input");
	By Stat = By.xpath("//*[@class = 'oxd-form-row']//div//div[3]//div//div[2]//div//div//div");
	By Username1 = By.xpath("//*[@class = 'oxd-form-row']//div//div[4]//div//div[2]//input");
	By Password1 = By.xpath("//*[@class = 'oxd-form-row user-password-row']//div//div[1]//div[1]//div[2]//input");
	By ConPassword = By.xpath("//*[@class = 'oxd-form-row user-password-row']//div//div[2]//div[1]//div[2]//input");
	By Save = By.xpath("//*[@type = 'submit']");
	By Sys_username = By.xpath("//*[@class = 'oxd-form-row']//div//div//div//div[2]//input");
	By table_username = By.xpath("//*[@class = 'oxd-table-card']//div//div[2]//div");
	
	
	public Admin_Page(WebDriver driver) {
		this.driver = driver;
	}
	
	public void UserRole(String role) throws Exception {
		wait.ExplicitStop(driver, AddUser_btn, 20);
		driver.findElement(AddUser_btn).click();
		driver.findElement(User_dropdown).click();
		if(role.equals("Admin")) {
			driver.findElement(User_dropdown).sendKeys(Keys.DOWN);
			driver.findElement(User_dropdown).sendKeys(Keys.ENTER);
		}
		else {
			driver.findElement(User_dropdown).sendKeys(Keys.DOWN);
			driver.findElement(User_dropdown).sendKeys(Keys.DOWN);
			driver.findElement(User_dropdown).sendKeys(Keys.ENTER);;
		} 
	}
	
	public void EmployeeName(String name) throws InterruptedException {
		driver.findElement(Emp_name).sendKeys(name);
		Thread.sleep(2000);
		driver.findElement(Emp_name).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		driver.findElement(Emp_name).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}
	
	public void Status(String status1) throws Exception {
		driver.findElement(Stat).click();
		if(status1.equals("Enabled")) {
			driver.findElement(Stat).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(Stat).sendKeys(Keys.ENTER);
		}
		else {
			driver.findElement(Stat).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(Stat).sendKeys(Keys.ARROW_DOWN);
			driver.findElement(Stat).sendKeys(Keys.ENTER);;
		}
		Thread.sleep(2000);
	}
	
	public void UserName(String name) throws InterruptedException {
		driver.findElement(Username1).sendKeys(name);
		Thread.sleep(2000);
	}
	
	public void Password(String Pass) throws Exception  {
		driver.findElement(Password1).sendKeys(Pass);
		Thread.sleep(2000);
	}
	
	public void ConfirmPassword(String Cpass) throws Exception {
		driver.findElement(ConPassword).sendKeys(Cpass);
		Thread.sleep(2000);
	    driver.findElement(Save).click();
	    Thread.sleep(5000);
	}
	
	public void Add_User(String E, String name, String op,String val,String pass,String conf) throws Exception {
		Thread.sleep(1000);
		Admin_Page apage = new Admin_Page(driver);
		apage.UserRole(E);
		apage.EmployeeName(name);
		apage.Status(op);
		apage.UserName(val);
		apage.Password(pass);
		apage.ConfirmPassword(conf);  
		apage.System_Username(val);
		Thread.sleep(2000);
		
	}  
	
	public void System_Username(String user) throws Exception {
		driver.findElement(Sys_username).sendKeys(user);
		Thread.sleep(1000);
		driver.findElement(Save).click();
	}
}
