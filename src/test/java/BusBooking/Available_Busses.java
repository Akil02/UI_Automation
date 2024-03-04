package BusBooking;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Common.Base;
import PageObject_Bus_Booking.BusTicketsPage;
import PageObject_Bus_Booking.StartPage;

public class Available_Busses {

	public WebDriver driver;
	
	@Parameters("Bus")
	@BeforeClass
	public void Start(String name) throws Exception {
		driver = new Base().Driver_setup(name);
		
	}
	
	@Test
	public void run() throws Exception {
		new StartPage(driver).From("Chennai");
		new StartPage(driver).To("Coimbatore");
		new StartPage(driver).Date("11", "Dec", 2024);
		new StartPage(driver).Search();
		new BusTicketsPage(driver).AddBussed_Excel();
	}
}
