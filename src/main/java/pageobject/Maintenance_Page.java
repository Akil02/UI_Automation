package pageobject;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Common.Base;

public class Maintenance_Page {
      
	WebDriver driver = null;
	Base a1 = new Base();
	By conf = By.xpath("//*[@type = 'submit']");
	By username1 = By.xpath("//*[@name = 'username']");
    By password1 = By.xpath("//*[@name = 'password']");
    By inp = By.xpath("//*[text() = 'Employee Name']//ancestor::div[1]//following::div//div//div//input");        
    By slEmp = By.xpath("//*[text() = 'Selected Employee']//ancestor::div[2]");
    By access = By.xpath("//*[text() = 'Access Records']");
    
	public Maintenance_Page(WebDriver driver) {
		this.driver = driver;
	}
	
	public void Adm_Access() throws Exception {
    	String Pass1 = a1.Prop().getProperty("Password");
		driver.findElement(password1).sendKeys(Pass1);
    	driver.findElement(conf).click();
	}
	
	public void GetEmployee(String name) throws Exception {
		String name1 = "Maintain";
		Actions act = new Actions(driver);
		driver.findElement(access).click();
		Thread.sleep(1000);
		act.moveToElement(driver.findElement(inp)).click().sendKeys(name).build().perform();
		Thread.sleep(4000);
		act.moveToElement(driver.findElement(inp)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		driver.findElement(conf).click();		
		Thread.sleep(2000);
		a1.TakeImage("Maintanace_Page", driver);
	}
}
