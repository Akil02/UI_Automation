package PageObject_Bus_Booking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Base;

public class BusTicketsPage {

	WebDriver driver;
	By All_Busses = By.xpath("//*[contains(@class, 'travels')]");
	By Buss_time = By.xpath("//*[contains(@class,'dp-time')]");
	
	public BusTicketsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void AddBussed_Excel() throws Exception {
		Thread.sleep(9000);
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		try {
		  if(driver.findElement(By.xpath("//*[@class = 'icon icon-close']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@class = 'icon icon-close']")).click();
		  }
		}
		catch(Exception e) {
			
		}
		Thread.sleep(15000);
	    WebElement element = driver.findElement(By.xpath("//*[@alt='footerLogo']"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    long startTime = System.currentTimeMillis();

        // Scroll down for 20 seconds
        while ((System.currentTimeMillis() - startTime) < 10000) {
            // Scroll down using JavaScript
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // You can add a sleep here to control the scrolling speed if needed
            // Thread.sleep(1000);
        }
		List<WebElement> lis = driver.findElements(All_Busses);
		List<WebElement> time = driver.findElements(Buss_time);
		System.out.println(lis.size()+" last : "+time.size());
		XSSFWorkbook book = new Base().excel(); 
		XSSFSheet sheet = book.getSheetAt(3);
		int num = 1;
		for(int i = 0;i<lis.size();i++) {
			String val = lis.get(i).getText();
			sheet.createRow(num).createCell(0).setCellValue(val);
			sheet.createRow(num).createCell(1).setCellValue(time.get(i).getText());
			String p = "";
			if(val.subSequence(0, 2).equals("Ad") && driver.findElement(By.xpath("//*[text() = '"+val.substring(2,val.length())+"']//span")).isDisplayed()) {
				String c = lis.get(i).getText();
				p = c.substring(2,c.length());
			}
			else {
				p = lis.get(i).getText();
			}
			String v = "//*[text() = "+"\""+p+"\"]//parent::div//following-sibling::div[5]//div//div";
			System.out.println("//*[text() = "+"\""+p+"\"]//parent::div//following-sibling::div[5]//div//div");
			WebElement ele = driver.findElement(By.xpath(v));
			if(!ele.getAttribute("class").equalsIgnoreCase("starts")) {
				String v1 = v+"//span";
				String v2 = v+"//following-sibling::div//span";
				if(driver.findElement(By.xpath(v1)).getAttribute("class").equalsIgnoreCase("strike")) {
					String old_am = driver.findElement(By.xpath(v1)).getText();
					String am = driver.findElement(By.xpath(v2)).getText();
					sheet.getRow(num).createCell(2).setCellValue(old_am);
					sheet.getRow(num).createCell(3).setCellValue(am);
				}
				else {
					String am1 = driver.findElement(By.xpath(v1)).getText();
					sheet.getRow(num).createCell(3).setCellValue(am1);
				}
			}
			else {
				String v3 = v+"//following-sibling::div//span";
				String v4 = v+"//following-sibling::div//following-sibling::div//span";
				if(driver.findElement(By.xpath(v3)).getAttribute("class").equalsIgnoreCase("strike")) {
					String old_am1 = driver.findElement(By.xpath(v3)).getText();
					String am2 = driver.findElement(By.xpath(v4)).getText();
					sheet.getRow(num).createCell(2).setCellValue(old_am1);
					sheet.getRow(num).createCell(3).setCellValue(am2);
				}
				else {
					String am3 = driver.findElement(By.xpath(v3)).getText();
					sheet.getRow(num).createCell(3).setCellValue(am3);
				}
			}
			++num;
			FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Repository\\OrangeHRM_Excel.xlsx");
		    book.write(out);
		}
	}
}
