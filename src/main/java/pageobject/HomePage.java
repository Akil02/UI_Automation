package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public WebDriver driver = null;
    By ad_btn = By.xpath("//*[@class = 'oxd-sidepanel-body']//ul//li//a//span[text() = 'Admin']");
	By leave_bth = By.xpath("//*[@class = 'oxd-sidepanel-body']//ul//li[3]//a//span[text() = 'Leave']");
    By Per = By.xpath("//*[@class = 'oxd-sidepanel-body']//ul//li[6]//a//span");
    By rec = By.xpath("//*[@class = 'oxd-sidepanel-body']//ul//li[5]//a//span[text() = 'Recruitment']");
	By p = By.xpath("//*[@class = 'oxd-sidepanel-body']//ul//li[2]//a//span");
    By m = By.xpath("//*[@class = 'oxd-sidepanel-body']//ul//li[10]//a//span");
	
    public HomePage(WebDriver driver){
		this.driver = driver;
	}
	public void Adminbutton() {
		driver.findElement(ad_btn).click();
	}
	
	public void Leave() {
		driver.findElement(leave_bth).click();
	}
	
	public void Personal() {
		driver.findElement(Per).click();
	}
	
	public void Recruitment() {
		driver.findElement(rec).click();
	}
	
	public void Pim() {
		driver.findElement(p).click();
	}
	
	public void Maint() {
		driver.findElement(m).click();
	}
}
