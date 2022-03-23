package Via;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
	ExtentSparkReporter htmlreportloc;
	ExtentReports report;
	ExtentTest test;
	
	 WebDriver driver; 
	 FileInputStream fis; 
	 Properties prop;
  @Test(dataProvider = "login")
  public void Login(String email, String psd)  {
	  
	  		test = report.createTest("Login");
	  		
			driver.findElement(By.xpath(prop.getProperty("nothanks"))).click();
			driver.findElement(By.xpath(prop.getProperty("signin"))).click();
			driver.findElement(By.id("loginIdText")).sendKeys(email);
			driver.findElement(By.id("passwordText")).sendKeys(psd);
			driver.findElement(By.xpath(prop.getProperty("login"))).click();
			
			// check login success or not 
			String title=driver.getTitle();
			System.out.println("title:"+title);
			assertTrue(title.contains(title));
			
  }
  
 
  @BeforeMethod
  public void beforeMethod() throws IOException {
	  //define html localtion using ExtentSparkReporter class
	  htmlreportloc=new ExtentSparkReporter("D:\\Project\\login.html");
	  //create ExtentReports class
	  report=new ExtentReports();
	  report.attachReporter(htmlreportloc);
	  //to beautfy the report
	  htmlreportloc.config().setTheme(Theme.DARK);
	  fis=new FileInputStream(new File("C:\\Users\\LENOVO\\OneDrive\\Documents\\LTI_Training\\LTIFinalProject\\setting.properties"));
	  prop=new Properties();
	  prop.load(fis);
		
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath"));
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  Thread.sleep(1000);
	  driver.close();
  }
  @DataProvider
  public Object[][] login() {
    return new Object[][] {
    new Object[] { "saiswaroopreddych@gmail.com","Sai12345" },
    
    // we provide key value so its taking that input only..
    };
  }
  
  
}
