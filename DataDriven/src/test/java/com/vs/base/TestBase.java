package com.vs.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.vs.utility.ExcelReader;
import com.vs.utility.ExtentManager;
import com.vs.utility.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excels\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public static WebElement dropdown;
	@BeforeSuite
	public void setUp() throws IOException {

		if (driver == null) {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			log.debug("Config File Loaded!!!!!");
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR File Loaded!!!!!");
			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("FireFox driver Loaded!!!!!");
			}
			if (config.getProperty("browser").equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome driver Loaded!!!!!");
			}
			
			driver.get(config.getProperty("url"));
			log.debug("Navigating to URL "+config.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlywait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}
	
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void click(String locator) {
		
		if(locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		else if(locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if(locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on "+locator);
	
	}
	
		public void type(String locator, String value) {
		
		if(locator.endsWith("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		else if(locator.endsWith("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}
		else if(locator.endsWith("_id")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in "+locator+" value as : "+value);
	
	}
		
		public void select(String locator,String value) {
			
			if(locator.endsWith("_xpath")) {
				dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
			}
			else if(locator.endsWith("_css")) {
				dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			}
			else if(locator.endsWith("_id")) {
				dropdown = driver.findElement(By.id(OR.getProperty(locator)));
			}
			test.log(LogStatus.INFO, "Selecting from dropdown "+locator+" value as : "+value);
			
		}
		
		public static void verifyEquals(String actual,String expected) throws IOException {
			
			try {
				Assert.assertEquals(actual, expected);
			} catch (Throwable e) {
				TestUtil.captureScreenShot();
				test.log(LogStatus.FAIL, " verification failed with exception : "+e.getMessage());
				test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			}
			
		}
	

	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
			log.debug("Execution done");
		}
	}

}
