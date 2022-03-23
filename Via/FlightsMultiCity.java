package Via;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.sevenz.CLI;
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

public class FlightsMultiCity {
	static WebDriver driver; 
	static FileInputStream fis; 
	static Properties prop;
  @Test(dataProvider = "dp")
  public void MultiCity(String email, String psd) throws InterruptedException {
	  
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
		//flight booking for multi city
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@for='multi-city']")).click();
		// select source from
		driver.findElement(By.name("source_0")).sendKeys("pune");
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.name("source_0")))
		.pause(2000).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).build().perform();
		// select destination to
		driver.findElement(By.name("destination_0")).sendKeys("delhi");
		Actions act1=new Actions(driver);
		act1.moveToElement(driver.findElement(By.name("destination_0")))
		.pause(2000).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).build().perform();
		// select onward dates
		driver.findElement(By.name("departure_0")).click();
		WebDriverWait wait1= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element1=driver.findElement(By.xpath("//*[@id=\"depart-cal-0\"]/div[3]/div[2]/div[5]/div[5]"));
		wait1.until(ExpectedConditions.elementToBeClickable(element1));
		element1.click();
		driver.findElement(By.xpath("//label[@for='multi-city-1']")).click();
		driver.findElement(By.name("destination_1")).sendKeys("Bangalore");
		Actions act2=new Actions(driver);
		act2.moveToElement(driver.findElement(By.name("destination_1")))
		.pause(2000).sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER).build().perform();
		//select next date for city
		driver.findElement(By.name("departure_1")).click();
		WebDriverWait wait2= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element2=driver.findElement(By.xpath("//*[@id=\"depart-cal-1\"]/div[3]/div[2]/div[5]/div[6]"));
		wait2.until(ExpectedConditions.elementToBeClickable(element2));
		element2.click();
		// choose flights and book ticket	
		driver.findElement(By.xpath("//*[@id=\"search-flight-btn\"]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(prop.getProperty("flight1"))).click();
		driver.findElement(By.xpath(prop.getProperty("flight2"))).click();
		driver.findElement(By.xpath(prop.getProperty("bookflights"))).click();
		
		//user details
		Thread.sleep(15000);
				Select gender= new Select(driver.findElement(By.id("adult1Title")));
				gender.selectByValue("Mr");
				driver.findElement(By.id("adult1FirstName")).sendKeys("manesh");
				driver.findElement(By.id("adult1Surname")).sendKeys("lokare");
				
	/*		 Select day=new Select(driver.findElement(By.name("adult1DOBday")));
			 day.selectByValue("05");
			 Select month=new Select(driver.findElement(By.name("adult1DOBmonth")));
			 month.selectByValue("May");
			 Select year=new Select(driver.findElement(By.name("adult1DOByear")));
			 year.selectByValue("1997");*/
				
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
			driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  Thread.sleep(1000);
		 driver.close();
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
        new Object[] { "saiswaroopreddych@gmail.com","Sai12345" },

    };
  }
}
