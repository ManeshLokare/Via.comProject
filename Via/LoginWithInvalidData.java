package Via;

import org.testng.annotations.*;

import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginWithInvalidData {
	static WebDriver driver; 
	static FileInputStream fis; 
	static Properties prop;
  @Test(dataProvider = "dp")
  public void InvalidData(String email, String psd) {
	  System.out.println(email+"----"+psd);
		//driver.findElement(By.xpath("//*[@id=\"wzrk-cancel\"]")).click();
		driver.findElement(By.xpath(prop.getProperty("nothanks"))).click();
		//driver.findElement(By.xpath("//*[@id=\"SignIn\"]/div")).click();
		driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		//driver.findElement(By.id("loginIdText")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.id("loginIdText")).sendKeys(email);
		//driver.findElement(By.id("passwordText")).sendKeys(prop.getProperty("psd"));
		driver.findElement(By.id("passwordText")).sendKeys(psd);
		//driver.findElement(By.xpath("//*[@id=\"loginValidate\"]")).click();
		driver.findElement(By.xpath(prop.getProperty("login"))).click();
		
		// check login success or not 
		String msg=driver.findElement(By.xpath("//*[@id=\"viaAlert\"]/div/div")).getText();
		assertTrue(msg.contains(msg));
	
  }
  @BeforeMethod
  public void beforeMethod() throws IOException {
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
  public Object[][] dp() {
    return new Object[][] {
    	new Object[] { "sai@gmail.com", "sais1234" },
        new Object[] { "saiswaroopreddych@gmail.com", "sais1234" },
       new Object[] { "saisw@gmail.com", "Sai12345" },
    };
  }
}
