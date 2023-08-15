package pageobject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Base;


public class Start_Page {
     public WebDriver driver = null;
     By username1 = By.xpath("//*[@name = 'username']");
     By password1 = By.xpath("//*[@name = 'password']");
     By button1 = By.xpath("//*[@type= 'submit']");
    
    public Start_Page(WebDriver driver) {
    	this.driver = driver;
    }
    
    public  void Login() throws Exception {
    	Base a = new Base();
    	String User = a.Prop().getProperty("Username");
    	String Pass = a.Prop().getProperty("Password");
    	Thread.sleep(2000);
    	driver.findElement(username1).sendKeys(User);
    	driver.findElement(password1).sendKeys(Pass);
    	driver.findElement(button1).click();
    }
}
