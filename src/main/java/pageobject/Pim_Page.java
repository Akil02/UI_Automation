package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Pim_Page {

    public WebDriver driver = null;
    By Add_Emp = By.xpath("//*[text() = 'Add Employee']");
    By name = By.xpath("//*[@name = 'firstName']");
    By Mname = By.xpath("//*[@name = 'middleName']");
    By Lastname = By.xpath("//*[@name = 'lastName']");
    By emp_id = By.xpath("//*[text()= 'Employee Id']//following::div[1]//input");
    By save = By.xpath("//*[@type= 'submit']");
    
	public Pim_Page(WebDriver driver) {
		this.driver = driver;
	}
	
	public void Add_Employee() throws Exception {
		Thread.sleep(1000);
		driver.findElement(Add_Emp).click();
	}
	
	public void First_Name(String name1) throws Exception {
		Thread.sleep(1000);
		driver.findElement(name).clear();
		driver.findElement(name).sendKeys(name1);
	}
	
	public void Last_Name(String name1) throws Exception {
		Thread.sleep(1000);
		driver.findElement(Lastname).clear();
		driver.findElement(Lastname).sendKeys(name1);
	}
	
	public void Mid_Name(String Mnam) throws Exception {
		Thread.sleep(1000);
		driver.findElement(Mname).sendKeys(Mnam);
	}
	
	public void Emp_ID(String id) throws Exception {
		Thread.sleep(1000);
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(emp_id)).doubleClick().click().sendKeys(Keys.BACK_SPACE).build().perform();
		driver.findElement(emp_id).sendKeys(id);
	}
	
	public void Save_Button() throws Exception {
		Thread.sleep(1000);
		driver.findElement(save).click();
		Thread.sleep(6000);
	}
}
