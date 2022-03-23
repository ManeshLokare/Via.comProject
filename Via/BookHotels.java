package Via;

import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Network.UserAgent;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BookHotels {
	static WebDriver driver; 
	static FileInputStream fis; 
	static Properties prop;
  @Test(dataProvider = "dp")
  public void BookHotel(String email, String psd) throws InterruptedException {
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
					String title=driver.getTitle();
					System.out.println("title:"+title);
					assertTrue(title.contains(title));
		driver.findElement(By.xpath("//*[@id=\"Hotels\"]/a/span[2]")).click();	
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"destination\"]")).sendKeys("mumbai");
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"destination\"]")))
		.pause(1000)
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER)
		.build().perform(); 
		
		driver.findElement(By.xpath("//*[@id=\"checkIn\"]")).click();
		WebDriverWait wait1= new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement ele=driver.findElement(By.xpath("//*[@id=\"depart-cal\"]/div[3]/div[2]/div[5]/div[5]"));
		wait1.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
		
		driver.findElement(By.xpath("//*[@id=\"checkOut\"]")).click();
		WebDriverWait wait2= new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement element=	driver.findElement(By.xpath("//*[@id=\"return-cal\"]/div[3]/div[2]/div[5]/div[6]"));
	wait2.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			
		driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form/div[3]/div/div[4]/div")).click();
		driver.findElement(By.xpath("//*[@id=\"room1\"]/div[2]/div/div[1]")).click();	
		driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form/div[3]/div/div[5]/div[3]/div[2]")).click();
	
		Select a= new Select(driver.findElement(By.id("nationalityCountry")));
		a.selectByIndex(0);
		Select b= new Select(driver.findElement(By.id("residenceCountry")));
		b.selectByIndex(2);
		
		driver.findElement(By.xpath(prop.getProperty("searchhotels"))).click();
		
	/*	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement ele=driver.findElement(By.xpath(prop.getProperty("searchhotels")));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
		JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
		Actions actions=new Actions(driver);
		actions.moveToElement(ele).click().build().perform();*/
		//*[@id="0"]/div[4]/div[3]/div[1]
		
		driver.findElement(By.xpath("//*[@id=\"0\"]/div[4]/div[3]/div[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"roomHotel0\"]/div/div/div[3]/div[2]")).click();
		
	//	User details..
		Thread.sleep(5000);
		Select gender= new Select(driver.findElement(By.name("Room0AdultTitle0")));
			gender.selectByValue("Mr");
		driver.findElement(By.name("Room0AdultFirstName0")).sendKeys("manesh");
		driver.findElement(By.name("Room0AdultLastName0")).sendKeys("lokare");
		driver.findElement(By.name("panNumber")).sendKeys("AQKPL0896N");
		driver.findElement(By.id("contactMobile")).sendKeys("7057933124");
		driver.findElement(By.id("contactEmail")).sendKeys("saiswaroopreddych@gmail.com");
		driver.findElement(By.id("read_terms_label")).click();
		driver.findElement(By.id("makePayCTA")).click();
	/*	Actions acts=new Actions(driver);
		acts.moveToElement(driver.findElement(By.id("makePayCTA")))
		.pause(1000)//
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ARROW_DOWN)
		.sendKeys(Keys.ENTER)
		.build().perform(); */
		Thread.sleep(3000);
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
	//driver.close();
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "saiswaroopreddych@gmail.com","Sai12345"  },
     
    };
  }
}
