

package com.qa.amazon.testcase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qa.amazon.base.AmazonBase;
import com.qa.amazon.pages.HomePage;
import com.qa.amazon.pages.LoginPage;
import com.qa.amazon.util.UtilClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends AmazonBase{

	public LoginPage login;
	public HomePage home;
	public UtilClass util1;
	//----
	
	public ExtentReports extent;
	public ExtentTest extentTest;

	
	
	@BeforeSuite
	public void setExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", false);
		extent.addSystemInfo("Host Name", "Sagar Window");
		extent.addSystemInfo("User Name", "Sagar QA");
		extent.addSystemInfo("Environment", "QA");
		
	}
	
	@AfterSuite
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
	
	public LoginPageTest() throws IOException {
		//super();
	}
	@BeforeMethod
	public void setup() throws IOException
	{
		
			init();
			login=new LoginPage();
			util1=new UtilClass();
		
	}
	
	@Test(priority=1)
public void loginPageTitleTest(){
		extentTest = extent.startTest("loginPageTitleTest");
		String title = login.validateLoginPageTitle();
		Assert.assertTrue(title.contains("Amazon"));
		
		
	}
	
	
//	@Test(priority=2)
	public void signLinkTest(){
		extentTest = extent.startTest("signLinkTest");
		boolean flag = login.validateSignLink();
		Assert.assertTrue(flag);
	}
	
	//@Test (priority=3)
	public void logintoHome() throws InterruptedException, IOException 
	{
		extentTest = extent.startTest("logintoHome");
		home=login.login(prop.getProperty("emailid"), prop.getProperty("password"));
		
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			String screenshotPath=LoginPageTest.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
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
