package Via;

import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Register {
	static WebDriver driver; 
	static FileInputStream fis; 
	static Properties prop;
  @Test(dataProvider = "register")
  public void Register(String email, String psd,String un,String mobno) {
		driver.findElement(By.xpath(prop.getProperty("nothanks"))).click();
		driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		driver.findElement(By.xpath(prop.getProperty("signup"))).click();
		driver.findElement(By.id("emailIdSignUp")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.id("passwordSignUp")).sendKeys(prop.getProperty("psd"));
		driver.findElement(By.id("nameSignUp")).sendKeys(prop.getProperty("un"));
		driver.findElement(By.id("mobileNoSignUp")).sendKeys(prop.getProperty("mobno"));
		driver.findElement(By.id("signUpValidate")).click();
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
  public void afterMethod() {
	 driver.close();
  }


  @DataProvider
  public Object[][] register() {
    return new Object[][] {
      new Object[] { "saiswaroopreddych@gmail.com", "Sai12345" ,"Sai swaroop reddy Chappidi","8897665708"},
     
    };
  }

}
