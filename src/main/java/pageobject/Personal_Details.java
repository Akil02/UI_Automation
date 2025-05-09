package pageobject;

import java.time.Duration;
import java.util.List;

import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Base;

public class Personal_Details {
    public WebDriver driver = null;
    
    By first_name = By.xpath("//*[@name = 'firstName']");
    By mid_name = By.xpath("//*[@name = 'middleName']");
    By lastname = By.xpath("//*[@name = 'lastName']");
    By emp_id = By.xpath("//*[text()= 'Employee Id']//following::div[1]//input");
    By oth_id = By.xpath("//*[text()= 'Other Id']//following::div[1]//input");
    By national = By.xpath("//*[text()= 'Nationality']//following::div[1]//div//div//div[1]");
    By role = By.xpath("//div[@role = 'option']//span");
    By date_Year = By.xpath("//*[text()= 'Date of Birth']/following::div//div//div//input");
    By year_drop = By.xpath("//*[@class = 'oxd-calendar-selector-year']//div//p");
    By year_and_month = By.xpath("//*[@class = 'oxd-calendar-dropdown']//li");
    By month_drop = By.xpath("//*[@class='oxd-calendar-selector-month']//div//p");
    By date = By.xpath("//div[@class = 'oxd-calendar-dates-grid']//div");
    By gender = By.xpath("//*[@class = '--gender-grouped-field']//div//span");
    By inp_gender = By.xpath("//*[@type = 'radio']");
    By button = By.xpath("//*[@type = 'submit']");
    By input_bloodtype = By.xpath("//*[text() = 'Blood Type']//following::div[1]//div[1]//div[1]//div[1]");
    By bloodtype = By.xpath("//*[@role = 'listbox']//div");
    By addfilebutton = By.xpath("//*[text() = 'Attachments']//following-sibling::button");
    By atttachfile = By.xpath("//*[@type = 'file']");
  //  By image_click = By.xpath("//*[@class= 'employee-image']");
    By image_click = By.xpath("//*[@class= 'employee-image']//ancestor::div[1]");
    By img_pic = By.xpath("//*[@type = 'file']//following::div//div//img");
    By p_details = By.xpath("//*[@role = 'tablist']//div[1]//a");
    
	public Personal_Details(WebDriver driver) {
		this.driver = driver;
	}
	
	public void PersonlD(String oid,String role1,String year,String month,String date1,String gen
			,String blood1) throws Exception {
		driver.findElement(oth_id).sendKeys(oid);
		Thread.sleep(2000);  
		List<WebElement> na = driver.findElements(national);
		na.get(0).click();
		List<WebElement> v = driver.findElements(role);
		int si = v.size();
		System.out.println(si);
		for(int i = 0;i<si;i++) {
			String a1 = "";
			try {
			  a1 = ""+v.get(i).getText();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			if(a1.equals(role1)) {
				v.get(i).click();
				break;
			}
		}   
		Thread.sleep(2000);   
		driver.findElement(date_Year).click();
		Thread.sleep(2000);
		driver.findElement(year_drop).click();
		Thread.sleep(2000);
		List<WebElement> b = driver.findElements(year_and_month);
		for(int i = 0;i<b.size();i++) {
			String b1 = ""+b.get(i).getText();
			if(b1.equals(year)) {
				b.get(i).click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(month_drop).click();
		List<WebElement> c = driver.findElements(year_and_month);
		for(int i = 0;i<c.size();i++) {
			String c1 = ""+c.get(i).getText();
			if(c1.equals(month)) {
				c.get(i).click();
				break;
			}
		}  
		List<WebElement> d = driver.findElements(date);
		for(int i = 0;i<d.size();i++) {
			String c1 = ""+d.get(i).getText();
			if(c1.equals(date1)) {
				d.get(i).click();
				break;
			}
		}
		//gender
		List<WebElement> gend = driver.findElements(inp_gender);
		Actions action = new Actions(driver);
		System.out.println(gend.size());
		if(gen.equals("Male")) {
			action.moveToElement(gend.get(0)).click().build().perform();
		}
		else {
			try {
				action.moveToElement(gend.get(0)).click().build().perform();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}  
		List<WebElement> but = driver.findElements(button);
		int attempts = 0;

        while (attempts < 3) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.elementToBeClickable(but.get(0)));
                but.get(0).click();
                break; // Button is clickable
            } catch (Exception e) {
                System.out.println("Attempt " + (attempts + 1) + ": Button not clickable yet. Waiting...");
            }
            attempts++;
        }
		driver.findElement(input_bloodtype).click();
		List<WebElement> blood = driver.findElements(bloodtype);
		Thread.sleep(1000);
		for(int i = 1;i<=blood.size();i++) {
			String a2 = blood.get(i).getText();
			if(a2.equals(blood1)) {
				blood.get(i).click();
				break;
			}
		}
		Thread.sleep(2000);
		driver.findElement(addfilebutton).click();
		Thread.sleep(1000);
		driver.findElement(atttachfile).sendKeys(System.getProperty("user.dir")+"\\src\\main\\java\\Repository\\DummyFile.txt");
		Thread.sleep(2000);
		attempts = 0;

        while (attempts < 3) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                wait.until(ExpectedConditions.elementToBeClickable(but.get(1)));
                but.get(1).click();
                break; // Button is clickable
            } catch (Exception e) {
                System.out.println("Attempt " + (attempts + 1) + ": Button not clickable yet. Waiting...");
            }
            attempts++;
        }
		Thread.sleep(2000);
	}
	
	public void Profile_Image() throws Exception {
		String filePath = "C:\\Users\\A AKIL GANESH\\L1.jpg";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Wait for up to 10 seconds
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(image_click)));
        try {
        driver.findElement(image_click).click();
		Thread.sleep(8000);
	/*	WebElement fileInput = wait.until(ExpectedConditions.elementToBeClickable(img_pic));
		fileInput.sendKeys(filePath); */
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].setAttribute('style', arguments[1])", driver.findElement(img_pic), "0");
		js.executeScript("arguments[0].setAttribute('class', arguments[1])", driver.findElement(img_pic), "a");
		driver.findElement(img_pic).sendKeys(filePath);
		Thread.sleep(8000);
		driver.findElement(button).click();
		Thread.sleep(8000);
		driver.findElement(p_details).click();
		Thread.sleep(5000);
        }
        catch(Exception e) {
        	System.out.println("----------------");
        	System.out.println(e.getMessage());
        }
        
	}
}
