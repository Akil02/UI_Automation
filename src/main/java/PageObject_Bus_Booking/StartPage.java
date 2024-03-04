package PageObject_Bus_Booking;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage {
    WebDriver driver;
    By Fname = By.xpath("//*[text() = 'From']//preceding-sibling::input");
    By Cdrop = By.xpath("//*[@role = 'button']//ul//li[1]");
    By Tname = By.xpath("//*[text() = 'To']//preceding-sibling::input");
    By date_time = By.xpath("//*[@class = 'dateText']");
    By days = By.xpath("//*[contains(@class,'DayTiles__CalendarDaysSpan')]");
    By Month_year = By.xpath("//*[contains(@class,'DatePicker__MainBlock')]//div//div[2]");
    By navbutton = By.xpath("//*[contains(@class,'DayNavigator')]//div[3]");
    By search = By.xpath("//*[text() = 'SEARCH BUSES']");
    
	public StartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void From(String name) {
		driver.findElement(Fname).sendKeys(name);
		driver.findElement(Cdrop).click();
	}
	
	public void To(String name) {
		driver.findElement(Tname).sendKeys(name);
		driver.findElement(Cdrop).click();
	}
	
	public void Date(String date, String Mon, int Yer) throws Exception {
		driver.findElement(date_time).click();
		List<WebElement> MY = driver.findElements(Month_year);
		String MonYear = MY.get(0).getText();
		System.out.println(MonYear);
		String v = MonYear.substring(4,8);
		int Year = Integer.parseInt(v);
		List<WebElement> li = driver.findElements(days);
		if(Yer >= Year) {
		 boolean run = true;
		 while(run) {
		   li = driver.findElements(days);
		   System.out.println(li.size());
		   MY = driver.findElements(Month_year);
		   String MonYear1 = MY.get(0).getText();
		   String Month = MonYear1.substring(0,3);
		   int Year1 = Integer.parseInt(MonYear1.substring(4,8));
		   if(Year1 <= Yer) {	 
			for(int i = 0;i<li.size();i++) {
				if(li.get(i).getText().equalsIgnoreCase(date) && Mon.equalsIgnoreCase(Month)) {
					run = false;
					li.get(i).click();
					break;
				}
			}
			if(run == true) {
			  driver.findElement(navbutton).click();
			  Thread.sleep(2000);
			}
		   }
		   else {
			   System.out.println("Please give the proper value here");
			   break;
		   }
		 }
	    }
		else {
			System.out.println("Please give Proper date");
		}
	}
	
	public void Search() {
		driver.findElement(search).click();
	}
}
