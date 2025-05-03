package Common;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Base {
    public WebDriver driver = null;
	public  WebDriver Driver_setup(String name) throws IOException {
	  try {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--headless=new"); // Required in CI
		options.addArguments("--disable-gpu");  // Avoid GPU issues in CI
		options.addArguments("--no-sandbox");   // Required for CI
		options.addArguments("--disable-dev-shm-usage"); // Required for CI
		options.addArguments("--remote-allow-origins=*");
		String chromeDriverPath = "";
		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")) {
		    // Windows path
		    chromeDriverPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Repository", "chromedriver-win64", "chromedriver.exe").toString();
		} else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
		    // Linux or Mac path
		    chromeDriverPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Repository", "chromedriver-linux64", "chromedriver").toString();
		}
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		String brow = Prop().getProperty("Browser");
		if(brow.equals("Chrome")) {
		  System.out.println("Started chrome");
		  driver = new ChromeDriver(options);
		  System.out.println("Finished chrome");
		}
		else if(brow.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(brow.equals("Edge")) {
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Not a valid Browser");
		}
		System.out.println("This is the url : "+new Base().Prop().getProperty(name));
		driver.get(new Base().Prop().getProperty(name));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	  }
	  catch(Exception e) {
		  System.out.println("This is the failure message : "+e.getMessage());
	  }
	  return driver;
	}
	
	public  Properties Prop() throws IOException {
		Path configPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Repository", "Config.properties");
		FileReader reader = new FileReader(configPath.toFile());
        Properties props=new Properties();
        props.load(reader);
		return props;
	}
	
	public Properties db_Prop() throws IOException {
		 Path dbConfigPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Repository", "db_config.properties");
		 FileReader dbReader = new FileReader(dbConfigPath.toFile());
         Properties props=new Properties();
         props.load(dbReader);
 		return props;
 	}
	
	public Connection Database_Statement(Connection connection,Statement statement) throws Exception {
		String url = db_Prop().getProperty("db.url");
        String username = db_Prop().getProperty("db.username");
        String password = db_Prop().getProperty("db.password");
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Establish the connection
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
	}
	
	public Statement DBstatement(Connection connection, Statement statement) throws SQLException {
		statement = connection.createStatement();
		return statement;
	}
	public ResultSet Data_Result(String query,ResultSet resultSet,Statement statement) throws SQLException {
		resultSet = statement.executeQuery(query);
		return resultSet;
	}
	
	public void DatabaseClose(ResultSet resultSet,Statement statement,Connection connection) throws SQLException {
		resultSet.close();
        statement.close();
        connection.close();
	}
	
	public XSSFWorkbook excel(){
		XSSFWorkbook workbook = null;
		try {
			Path excelPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Repository", "OrangeHRM_Excel.xlsx");
			FileInputStream fs = new FileInputStream(excelPath.toFile());
			workbook = new XSSFWorkbook(fs);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return workbook;
	}
	
	public void WrightExcel(XSSFWorkbook workbook) throws Exception {
		Path excelPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Repository", "OrangeHRM_Excel.xlsx");
		FileOutputStream fos = new FileOutputStream(excelPath.toFile());
		workbook.write(fos);
		fos.close();
	}
	
	public void ExplicitStop(WebDriver driver1,By val,int timeout) {
		new WebDriverWait(driver1,Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.visibilityOfElementLocated(val));
	}
	
	public void TakeImage(String name,WebDriver driver1) throws Exception {
		TakesScreenshot scrShot =((TakesScreenshot)driver1);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		Path destPath = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "Images", name + ".jpg");
		File DestFile = destPath.toFile();
		FileUtils.copyFile(SrcFile, DestFile);
	}	
/*	public Response KeyCreation() throws Exception {
		String v = "{\r\n"
				+ "  \"username\": \""+new Base().Prop().getProperty("Jira_username")+"\",\r\n"
				+ "  \"password\": \""+new Base().Prop().getProperty("Jira_password")+"\"\r\n"
				+ "}";
		RestAssured.baseURI = "http://localhost:8080";
		Response res = RestAssured.given().contentType(ContentType.JSON).and().body(v).when().post("/rest/auth/1/session");
		return res;
	}
	
	public String GetFromKey(Response res,String name) {
		JsonPath jp = new JsonPath(res.then().extract().asPrettyString().toString());
//		System.out.println(jp.getString("session.value"));
		System.out.println(res.getStatusCode());
		return jp.getString("session."+name);
	}
	
	public String CreateIssue(String name1,String n,String v,String sum,String desc) {
		String v1 = "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \""+name1+"\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \""+sum+"\",\r\n"
				+ "       \"description\": \"Sample defect to check in postman\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}";

		String a = ""+n+"="+v+"";
		RestAssured.baseURI = "http://localhost:8080";
		Response res = RestAssured.given().header("Cookie",a).contentType(ContentType.JSON).body(v1).when().post("/rest/api/2/issue");
		System.out.println(res.statusCode());
		JsonPath jp = new JsonPath(res.then().extract().asPrettyString().toString());
		return jp.getString("id");
	}
	
	public void Create_Command(String id,String n,String v,String text) {
		String n1 = "{\r\n"
				+ "    \"body\": \""+text+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
		String a1 = ""+n+"="+v+"";
		String h = "/rest/api/2/issue/"+id+"/comment";
		RestAssured.baseURI = "http://localhost:8080";
		Response r = RestAssured.given().header("Cookie",a1).contentType(ContentType.JSON).body(n1).when().post(h);
		System.out.println("Craeting command : "+r.getStatusCode());
	}
	*/
}
