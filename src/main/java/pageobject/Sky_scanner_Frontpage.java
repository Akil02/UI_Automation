package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Sky_scanner_Frontpage {
    public WebDriver driver = null;
    By From = By.xpath("//*[@class = 'SearchControls_grid__NjczM']//div//div//div//label//div//div//input");
    By To = By.xpath("//*[@class = 'SearchControls_grid__NjczM']//div[2]//div//div//label//div//div//input");
    By depart = By.xpath("//*[@data-testid = 'dates-container']//div//div//button//span[2]");
    By return1 = By.xpath("//*[@data-testid = 'dates-container']//div[2]//div//button//span[2]");
    By travel_cabin = By.xpath("//*[@class = 'SearchControls_grid__NjczM']//div[4]//div//button//span[2]");
    By search = By.xpath("//*[text() = 'Search']");
    
    //date
    By wholemonth = By.xpath("//*[@class = 'DatePickerDesktop_calendarTab__MzViO']//div//button[2]");
    By monthList =  By.xpath("//*[@class = 'MonthSelectionList_monthSelectionList__NDQ3Z']//div//button");
    By rightclick = By.xpath("//*[@class = 'CustomCalendar_header__ZjE2Z'] //button[2]");
    By leftclick = By.xpath("//*[@class = 'CustomCalendar_header__ZjE2Z'] //button[1]");
    By leftmonth = By.xpath("//*[@class = 'CustomCalendar_calendarsContainer__NTQyN'] //div[1]//h2");
    By rightmonth = By.xpath("//*[@class = 'CustomCalendar_calendarsContainer__NTQyN'] //div[2]//h2");
    By row = By.xpath("//*[@class = 'CustomCalendar_calendarsContainer__NTQyN'] //div[2] //*[@role = 'row']");
    
	public Sky_scanner_Frontpage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void From_Location(String name) throws Exception {
		driver.findElement(From).clear();
		driver.findElement(From).sendKeys(name);
		driver.findElement(From).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(From).click();
		Thread.sleep(2000);
	}
	
	public void To_Location(String name) throws Exception {
		driver.findElement(To).clear();
		driver.findElement(To).sendKeys(name);
		driver.findElement(To).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(To).click();
		Thread.sleep(2000);
	}
	
	public void DepartLoc(String day,String month, String year) {
		 driver.findElement(depart).click();
		 driver.findElement(wholemonth).click();
		 boolean found = false;
		 for(int i = 0;i<12;i++) {
			 if(driver.findElement(leftmonth).getText().equals(month) || 
					 driver.findElement(rightmonth).getText().equals(month)) {
				 found = true;
				 break;
			 }
			 else {
				 driver.findElement(rightclick).click();
			 }
		 }
		 if(found == false) {
			 for(int i = 0;i<12;i++) {
				 if(driver.findElement(leftmonth).getText().equals(month) || 
						 driver.findElement(rightmonth).getText().equals(month)) {
					 found = true;
					 break;
				 }
				 else {
					 driver.findElement(leftclick).click();
				 }
			 }
		 }
		 int s = driver.findElements(row).size();
		 for(int i = 0;i<s;i++) {
			 String n = "//*[@class = 'CustomCalendar_calendarsContainer__NTQyN'] //div[2] //*[@role = 'row']//div["+i+"]//button[@type = 'button' and @aria-hidden = 'false']";
			 List<WebElement> a = driver.findElements(By.xpath(n));
			 for(int j = 0;j<a.size();j++) {
				 if(a.get(j).getText().equals(day)) {
					 a.get(j).click();
					 break;
				 }
			 }
		 }
	}
	
}
