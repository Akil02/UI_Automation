package pageobject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.Base;

public class LeavePage {
    
	public WebDriver driver = null;
	public Statement statement = null;
	public ResultSet resultSet = null;
	public Connection connection = null;
	By from_date = By.xpath("//*[@class = 'oxd-form-row']//div//div[1]//div//div[2]//div//div//input");
	By to_date = By.xpath("//*[@class = 'oxd-form-row']//div//div[2]//div//div[2]//div//div//input");
	By leave_status = By.xpath("//*[@class = 'oxd-form-row']//div//div[3]//div//div[2]//div//div//div");
	By leave_type = By.xpath("//*[@class = 'oxd-form-row']//div//div[4]//div//div[2]//div//div//div");
	By employee_name = By.xpath("//*[@class = 'oxd-form-row'][2]//div//div//div//div[2]//div//div//input");
	By sub_unit = By.xpath("//*[@class = 'oxd-form-row'][2]//div//div[2]//div//div[2]//div//div//div");
	By leave_submit = By.xpath("//*[@type = 'submit']");
	By Employee = By.xpath("//*[@class = 'oxd-table-body']//div[@class = 'oxd-table-card']");
	
	public LeavePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ApproveLeave() throws Exception {
		connection = new Base().Database_Statement(connection, statement);
		statement = new Base().DBstatement(connection, statement);
		if(driver.findElements(Employee).size() != 0) {
		  List<WebElement> a = driver.findElements(Employee);
		  int i = 1;
		  while(a.size() != 0) {
			  String val = "//*[@class = 'oxd-table-body']//div["+i+"]//div//div[9]//div//button[1]";
			  String d = driver.findElement(By.xpath("//*[@class = 'oxd-table-body']//div["+i+"]//div//div[2]//div")).getText();
			  String name = driver.findElement(By.xpath("//*[@class = 'oxd-table-body']//div["+i+"]//div//div[3]//div")).getText();
			  String type = driver.findElement(By.xpath("//*[@class = 'oxd-table-body']//div["+i+"]//div//div[4]//div")).getText();
			  String Bal = driver.findElement(By.xpath("//*[@class = 'oxd-table-body']//div["+i+"]//div//div[5]//div")).getText();
			  String num = driver.findElement(By.xpath("//*[@class = 'oxd-table-body']//div["+i+"]//div//div[6]//div")).getText();
			  String st = driver.findElement(By.xpath("//*[@class = 'oxd-table-body']//div["+i+"]//div//div[7]//div")).getText();
			  String from = d.substring(0,10);
			  String to = " ";
			  String day = "Full";
			  if(d.length() > 11) {
				  String c = d.substring(11,12);
				if(!c.equals("(")) {
			      to = d.substring(14,d.length());
				}
				else {
					day = "Half";
				}
			  }
			  String st2 = "";
			  for(int ind = 0;ind<st.length();ind++) {
				  if(st.substring(ind, ind+1).equals("(")) {
					  break;
				  }
				  st2 += st.substring(ind, ind+1);
			  }
			  st = st2.trim();
			  String query = "";
			  if(!to.equals(" ")) {
				  query  = "Insert into Employee_LeaveApprove values('"+name+"','"+type+"','"+Bal+"','"+num+"',to_date('"+from+"','YY-MM-DD'),to_date('"+to+"','YY-MM-DD'),'"+st+"','"+day+"')";
			  }
			  else if(to.equals("half")) {
				  query  = "Insert into Employee_LeaveApprove values('"+name+"','"+type+"','"+Bal+"','"+num+"',to_date('"+from+"','YY-MM-DD'),to_date('"+to+"','YY-MM-DD'),'"+st+"','"+day+"')";
			  }
			  else {
				  query  = "Insert into Employee_LeaveApprove values('"+name+"','"+type+"','"+Bal+"','"+num+"',to_date('"+from+"','YY-MM-DD'),null,'"+st+"','"+day+"')";
			  }
			  System.out.println(query);
			  resultSet = new Base().Data_Result(query, resultSet, statement);
			  driver.findElement(By.xpath(val)).click();
			  a = driver.findElements(Employee);
		  }
		  new Base().DatabaseClose(resultSet, statement, connection);
		}
		else {
			System.out.println("Not Present");
		}
	}
	
	public void RejectLeave() throws Exception {
		List<WebElement> rej = driver.findElements(Employee);
		if(rej.size() != 0) {
		//  rej.get(0).click();
		}
	}
	
	
}
