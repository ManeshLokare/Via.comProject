package Via;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FlightsOneWay {
	static WebDriver driver; 
	static FileInputStream fis; 
	static Properties prop;
  @Test(dataProvider = "dp")
  public void OneWayTrip(String email, String psd) throws InterruptedException {
	  System.out.println(email+"----"+psd);
		driver.findElement(By.xpath(prop.getProperty("nothanks"))).click();
		driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		driver.findElement(By.id("loginIdText")).sendKeys(email);
		driver.findElement(By.id("passwordText")).sendKeys(psd);
		driver.findElement(By.xpath(prop.getProperty("login"))).click();	
		// check login success or not 
		String title=driver.getTitle();
		System.out.println("title:"+title);
		assertTrue(title.contains(title));
		driver.findElement(By.linkText("Flights")).click();
		
		//flight booking for oneway
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='one-way']")).click();
		driver.findElement(By.name("source")).sendKeys("pune");
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.name("source")))
		.pause(2000)
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER)
		.build().perform();
		
		driver.findElement(By.name("destination")).sendKeys("delhi");
		Actions act1=new Actions(driver);
		act1.moveToElement(driver.findElement(By.name("destination")))
		.pause(2000)
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER)
		.build().perform();
	
		driver.findElement(By.name("departure")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"depart-cal\"]/div[3]/div[2]/div[5]/div[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"search-flight-btn\"]")).click();
		
		driver.findElement(By.id("cheap_flight")).click();
		driver.findElement(By.xpath("//*[@id=\"cheap_flight_container\"]/div[2]/div/div[6]")).click();
		driver.findElement(By.xpath(prop.getProperty("bookticket"))).click();
		
		//user details
		Thread.sleep(8000);
		Select gender= new Select(driver.findElement(By.id("adult1Title")));
		gender.selectByValue("Mr");
		driver.findElement(By.id("adult1FirstName")).sendKeys("manesh");
		driver.findElement(By.id("adult1Surname")).sendKeys("lokare");
		driver.findElement(By.id("contactMobile")).sendKeys("7057933124");
		driver.findElement(By.id("contactEmail")).sendKeys("saiswaroopreddych@gmail.com");
		driver.findElement(By.id("isCabOpted_label")).click();
	//	driver.findElement(By.id("msgInfoChkBox_label")).click();
		driver.findElement(By.id("makePayCTA")).click();
		driver.findElement(By.id("confirmProceedPayBtn")).click(); 

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
	 //  driver.close();
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
        new Object[] { "saiswaroopreddych@gmail.com","Sai12345" },

    };
  }
}
