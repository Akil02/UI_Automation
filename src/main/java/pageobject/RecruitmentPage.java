package pageobject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.Base;

public class RecruitmentPage {
   
   public WebDriver driver = null;
   public Statement statement = null;
   public ResultSet resultSet = null;
   public Connection connection = null;
   By len = By.xpath("//*[@class = 'oxd-table-body']//div[@class = 'oxd-table-card']");
   By delete = By.xpath("//*[@class = 'orangehrm-modal-footer']//button[2]");
   
   public RecruitmentPage(WebDriver driver) {
	   this.driver = driver;
   }
   
 /*  public void DBconnection() throws SQLException {
	   connection = new Base().Database_Statement(connection, statement);
	   statement = new Base().DBstatement(connection, statement);  
   }   */
   
 /*  public void Dbclose() throws SQLException {
	   resultSet.close();
       statement.close();
       connection.close();
   }  */
   public void HiredList() throws Exception {
	   connection = new Base().Database_Statement(connection, statement);
	   statement = new Base().DBstatement(connection, statement);
       Thread.sleep(1000);
	   boolean cond = false;
	   List<WebElement> a12 = driver.findElements(len);
	   for(int i = 1;i<=a12.size();i++) {
		   String v = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[6]//div";
		   if(driver.findElement(By.xpath(v)).getText().equals("Hired")) {
			   cond = true;
			   String vacancy1 = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[2]//div";
			   String candidate1 = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[3]//div";
			   String manager = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[4]//div";
			   String dod = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[5]//div";
			   String status1 = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[6]//div";
			   //
			   String vac = driver.findElement(By.xpath(vacancy1)).getText();
			   String can = driver.findElement(By.xpath(candidate1)).getText();
			   String man = driver.findElement(By.xpath(manager)).getText();
			   String dob = driver.findElement(By.xpath(dod)).getText();
			   String st = driver.findElement(By.xpath(status1)).getText();			
			   String query = "Insert into Recruitment_HiredEmployees values('"+vac+"','"+can+"','"+man+"',TO_DATE('"+dob+"','YY-MM-DD'),'"+st+"')";  
			   System.out.println(query);
			   resultSet = new Base().Data_Result(query, resultSet, statement);
		   }
	   }
	   if(cond == true) {
	     new Base().DatabaseClose(resultSet, statement, connection);
	   }
   }
   
   public void RejectedList() throws Exception {
	   connection = new Base().Database_Statement(connection, statement);
	   statement = new Base().DBstatement(connection, statement);
	   boolean cond = false;
	   List<WebElement> a = driver.findElements(len);
	   for(int i = 1;i<=a.size();i++) {
		   String v = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[6]//div";
		   if(driver.findElement(By.xpath(v)).getText().equals("Rejected")) {
			   String vacancy1 = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[2]//div";
			   String candidate1 = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[3]//div";
			   String manager = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[4]//div";
			   String dod = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[5]//div";
			   String status1 = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[6]//div";
			   //
			   String vac = driver.findElement(By.xpath(vacancy1)).getText();
			   String can = driver.findElement(By.xpath(candidate1)).getText();
			   String man = driver.findElement(By.xpath(manager)).getText();
			   String dob = driver.findElement(By.xpath(dod)).getText();
			   String st = driver.findElement(By.xpath(status1)).getText();			   
			   String query = "INSERT INTO Recruitment_FailedEmployees (vac, can, man, dob, st) " +
		               "VALUES ('" + vac + "', '" + can + "', '" + man + "', TO_DATE('" + dob + "', 'YYYY-MM-DD'), '" + st + "')";
			   resultSet = new Base().Data_Result(query, resultSet, statement);
		   }
	   }
	   if(cond == true) {
		   new Base().DatabaseClose(resultSet, statement, connection);
	   }
   }
   
   public void Rejected() throws Exception {
	   int i = 1;
	   List<WebElement> si = driver.findElements(len);
	   while(i != si.size()) {
		   String v = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[6]//div";
		   if(driver.findElement(By.xpath(v)).getText().equals("Rejected") || driver.findElement(By.xpath(v)).getText().equals("Interview Failed")) {
			   String del = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[7]//div//button[2]";
			   String name = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[2]//div";			   
			   driver.findElement(By.xpath(del)).click();
			   driver.findElement(delete).click();
			   Thread.sleep(8000);
			   
		   }
		   else {
			   ++i;
		   }
		   si = driver.findElements(len);
	   }
   }

}
