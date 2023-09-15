package pageobject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {
	WebDriver driver ;
	Actions act;
	Select sel;
	SoftAssert asserts=new SoftAssert();
	Alert at;
	JavascriptExecutor js;
//	ReadPropertyFile obj=new ReadPropertyFile();
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeMethod
	public void startUp(Method method) throws InterruptedException, IOException {
		 test=extent.createTest(method.getName());
		
		driver = new ChromeDriver();
		test.log(Status.INFO,"Chrome browser launched");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		test.log(Status.INFO,"User maximize the window");
		driver.manage().window().maximize();
//		driver.get(obj.readKeyDetails("url"));
//		driver.navigate().to("facebook.com");
//		driver.navigate().refresh();
//		driver.navigate().back();
		test.log(Status.INFO,"User go to the facebook URL");
		driver.get("https://www.facebook.com/");
//		driver.get("https://demo.textboxtechnology.com/");
		Thread.sleep(3000);
	}
	@BeforeClass
	public void startClass() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\MySeleniumJavaProject\\July Java Project\\SeleniunJavaDataDriven\\Report\\AutomationReport.html");
		 extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	@AfterClass
	public void afterClass() {
		extent.flush();
	}
	
	public boolean isElementDisplayed(By variable) {
		return driver.findElement(variable).isDisplayed();
		
	}
	public void enterDetails(By locator,String value) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
	}
	
	public String getMyPageURL() {
		return driver.getCurrentUrl();
	}
	public void click(By loc) {
		driver.findElement(loc).click();
	}
	
	public void clickCheckBox(By loc) {
		driver.findElement(loc).click();
	}
	public boolean isSelected(By loc) {
		return driver.findElement(loc).isSelected();
	}
	public WebElement  ConvertByTOWebElement(By loc) {
		return driver.findElement(loc);
	}
	
	public void selectByValue(By loc, String value) {
		 sel=new Select(ConvertByTOWebElement(loc));
		sel.selectByValue(value);
	}
	public void selectByIndex(By loc, int value) {
		 sel=new Select(ConvertByTOWebElement(loc));
		sel.selectByIndex(value);
	}
	public void selectByVisibleTexty(By loc, String value) {
		 sel=new Select(ConvertByTOWebElement(loc));
		sel.selectByVisibleText(value);
	}
	/**
	 * This method is use to wait for an element 
	 * to be present on the page
	 * @param By locator
	 * @param time in long
	 * @author Abhishek.Dwivedi
	 * @return WebElement
	 * 
	 */
	
	public WebElement  waitForElementToPresent(By loc,long time) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.presenceOfElementLocated(loc));
	}
	
	public void fluentWait(long totalwait,long poolinginterval) {
		 Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
		.withTimeout(Duration.ofSeconds(60))
		.pollingEvery(Duration.ofSeconds(10))
		.ignoring(NoSuchElementException.class);
	}
	
	public void rightClick(By loc) {
		act=new Actions(driver);
		act.contextClick(ConvertByTOWebElement(loc)).build().perform();
		
	}
	public void mouseHover(By loc) {
		act=new Actions(driver);
		act.moveToElement(ConvertByTOWebElement(loc)).build().perform();
		
	}
	
	public String getText(By loc) {
		return driver.findElement(loc).getText();
	}
	
	public void acceptAlert() {
		at = driver.switchTo().alert();
		at.accept();
	}
	public void CancelAlert() {
		at = driver.switchTo().alert();
		at.dismiss();
	}
	public void sendKeysInAlert(String value) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		at= wait.until(ExpectedConditions.alertIsPresent());
		at.sendKeys(value);
		at.accept();
	}
	public WebDriverWait returnWait() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait;
	}
	
	public String getPageTitileJs() {
		 js=(JavascriptExecutor)driver;
		String title= js.executeScript("return document.title;").toString();
		return title;
	}
	public void clickJS(By loc) {
		js=(JavascriptExecutor)driver;
		js.executeAsyncScript("arguments[0].click();", ConvertByTOWebElement(loc));
	}
	public void TestScreenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dateTime = new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date());
		String screenName="screenshot"+dateTime;
		File dest = new File("C:\\MySeleniumJavaProject\\July Java Project\\MySeleniumFramework\\Screenshots\\"+screenName);
		FileUtils.copyFile(src, dest);

	}
	
	@AfterMethod
	public void tearDown(ITestResult iResult) throws IOException {
		if(ITestResult.FAILURE==iResult.getStatus()) {
			TestScreenShot();
		}
		if(ITestResult.SUCCESS==iResult.getStatus()) {
			System.out.println("Test cases pass");
		}
		if(ITestResult.SKIP==iResult.getStatus()) {
			System.out.println("Test cases Skipped");
		}
		asserts.assertAll();
		driver.close();
		driver.quit();
		
	}
	@DataProvider(name="UserDetails")
	public Object[][] returnData() {
		return new Object[][] 
		    	{
					{ "user", "pwd" },
		            { "abhishek", "dwivedi" },
		            { "testing", "Manual" },
		            { "Data", "Base" }
		        };
		
	}
	
	

}
