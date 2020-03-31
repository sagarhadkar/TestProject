
package com.qa.amazon.testcase;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.amazon.base.AmazonBase;
import com.qa.amazon.pages.HomePage;
import com.qa.amazon.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.ExtentReports;

import junit.framework.Assert;

public class HomePageTest extends AmazonBase {

	public LoginPage login;
	public HomePage home;

//---
	public ExtentReports extent;
	public ExtentTest extentTest;

	
	
	@BeforeTest
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", false);
		extent.addSystemInfo("Host Name", "Sagar Window");
		extent.addSystemInfo("User Name", "Sagar QA");
		extent.addSystemInfo("Environment", "QA");
		
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	}
	
	
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	//----
	public HomePageTest() throws IOException  {
	super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException, InterruptedException
	{
		init();
		login= new LoginPage();
		//home=login.login(prop.getProperty("emailid"), prop.getProperty("password"));
		home= new HomePage();
		
	

		}
	
	@Test(priority=1)
	public void radioButtonsPageTitleTest()
	{
		extentTest = extent.startTest("radioButtonsPageTitleTest");
		home.validateLanguageRadioButton();
		Assert.assertTrue(home.verifyRadioButtonsPageTitle().contains("Language"));
		
		
	}
	
	@Test(priority=2)	
	public void changeLanguageTest() throws InterruptedException
	{
		extentTest = extent.startTest("changeLanguageTest");
		home.validateLanguageRadioButton();
		home.verifyChangeLanguages();
	}
	
	
	@Test(priority=3)
		public void cancelPageTest()
		{
		extentTest = extent.startTest("cancelPageTest");
			home.validateLanguageRadioButton();
			home.verifyCancelPage();
		}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			String screenshotPath=HomePageTest.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); 
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
}
	
	}


	