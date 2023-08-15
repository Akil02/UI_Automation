package Common;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.response.Response;
import pageobject.Personal_Details;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Listener implements ITestListener{
    public WebDriver driver = null;
	public ExtentSparkReporter reporter = null;
	public ExtentReports extend = null;
	public ExtentTest test = null;
	public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public Row row = null;
    public Cell cel = null;
    int rownum = 1;
	private static final Logger logger = LogManager.getLogger(Listener.class);
	Response r = null;
	String key = "";
	String n = "";
	String id = "";
	String val = "";
	
	public void onStart(ITestContext context) {	
		logger.info("Test Suite: " + context.getName() + " started.");
		System.out.println("onStart method started");
		String path = "C:\\Users\\A AKIL GANESH\\Eclipse java\\Automation_project\\src\\main\\java\\Images\\Report.html";
		reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("WebApllication");
		reporter.config().setDocumentTitle("Test result");
		extend = new ExtentReports();
		extend.attachReporter(reporter);
		extend.setSystemInfo("Tester", "Akil Ganesh");
		workbook = new Base().excel();
		sheet = workbook.getSheetAt(2);
		row = sheet.getRow(rownum);
		try {
			r = new Base().KeyCreation();
			key = new Base().GetFromKey(r,"value");
			cel = row.createCell(0);
			cel.setCellValue("Key");
			cel = row.createCell(1);
			cel.setCellValue(key);
			new Base().WrightExcel(workbook);
			n = new Base().GetFromKey(r,"name");
			System.out.println(n+"   : "+key);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite: " + context.getName() + " finished.");
		System.out.println("onFinish method started");
		extend.flush();
	}
	
	public void onTestStart(ITestResult result) {
		logger.info("Test Method: " + result.getName() + " started.");
		test = extend.createTest(result.getName());
		System.out.println("New Test Started" +result.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Method: " + result.getName() + " passed.");
		System.out.println("onTestSuccess Method" +result.getName());
		test.pass(result.getName());
	}

	public void onTestFailure(ITestResult result) {
		++rownum;
		String err = result.getName()+" got failed while doing sanity testing please look into this issue";
		logger.error("Test Method: " + result.getName() + " failed and the exception is : "+result.getThrowable());
		test.info(result.getName());
		test.fail("Failed in"+result.getName()+" method");
		test.warning(result.getThrowable());
		val = new Base().CreateIssue("OR", n, key, result.getName(), err);
		Row row = sheet.createRow(rownum);
		row = sheet.getRow(rownum);
		cel = row.createCell(0);
		cel.setCellValue(result.getName());
		cel = row.createCell(1);
		cel.setCellValue(val);
		try {
			new Base().WrightExcel(workbook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Base().Create_Command(val, n, key,err);
    }

	public void onTestSkipped(ITestResult result) {
		logger.warn("Test Method: " + result.getName() + " skipped.");
		System.out.println("onTestSkipped Method" +result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
	}
}
