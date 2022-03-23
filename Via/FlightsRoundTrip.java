package Via;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class FlightsRoundTrip {
	static WebDriver driver; 
	static FileInputStream fis; 
	static Properties prop;
  @Test(dataProvider = "dp")
  public void RoundTrip(String email, String psd) throws InterruptedException {

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
		//flight booking for round trip
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='round-trip']")).click();
		// select source from
		driver.findElement(By.name("source")).sendKeys("Chennai");
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.name("source")))
		.pause(2000).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).build().perform();
		// select destination to
		driver.findElement(By.name("destination")).sendKeys("Bangalore");
		Actions act1=new Actions(driver);
		act1.moveToElement(driver.findElement(By.name("destination")))
		.pause(2000).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).build().perform();
		// select onward dates
		driver.findElement(By.name("departure")).click();
		WebDriverWait wait1= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element1=driver.findElement(By.xpath("//*[@id=\"depart-cal\"]/div[3]/div[2]/div[5]/div[5]"));
		wait1.until(ExpectedConditions.elementToBeClickable(element1));
		element1.click();
		// select return date
		driver.findElement(By.name("return")).click();
		WebDriverWait wait2= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element2=driver.findElement(By.xpath("//*[@id=\"return-cal\"]/div[3]/div[2]/div[5]/div[6]"));
		wait2.until(ExpectedConditions.elementToBeClickable(element2));
		element2.click();
		// choose flights and book ticket	
		driver.findElement(By.xpath("//*[@id=\"search-flight-btn\"]")).click();
		driver.findElement(By.id("cheap_flight")).click();
		driver.findElement(By.xpath("//*[@id=\"cheap_flight_container\"]/div[2]/div/div[4]")).click();
		driver.findElement(By.xpath(prop.getProperty("selectflights1"))).click();
		driver.findElement(By.xpath(prop.getProperty("selectflights2"))).click();
		driver.findElement(By.xpath(prop.getProperty("bookflights"))).click();
		//driver.findElement(By.id("repiceContBook")).click();
		//user details
		Thread.sleep(8000);
		Select gender= new Select(driver.findElement(By.id("adult1Title")));
		gender.selectByValue("Mr");
		driver.findElement(By.id("adult1FirstName")).sendKeys("manesh");
		driver.findElement(By.id("adult1Surname")).sendKeys("lokare");
		driver.findElement(By.id("contactMobile")).sendKeys("7057933124");
		driver.findElement(By.id("contactEmail")).sendKeys("saiswaroopreddych@gmail.com");
		driver.findElement(By.id("isCabOpted_label")).click();
		driver.findElement(By.id("msgInfoChkBox_label")).click();
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
		driver.manage().window().maximize();

  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  Thread.sleep(2000);
	 // driver.close();
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
        new Object[] { "saiswaroopreddych@gmail.com","Sai12345" },

    };
  }
}
