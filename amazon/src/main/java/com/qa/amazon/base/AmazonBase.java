
package com.qa.amazon.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.qa.amazon.util.UtilClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class AmazonBase  {

	public static WebDriver driver;
	public static String browser;
	public Properties prop;
	public FileInputStream fis;
	public File file;
	public String url;
	public static ExtentReports extent;
	public static ExtentTest extentTest;

	public AmazonBase() throws IOException
	{
		try {
		file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\amazon\\property\\config.properties");
		
		fis=new FileInputStream(file);
		
		prop=new Properties();
		
		prop.load(fis);
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		}
	
	public void init()
	{
		browser=prop.getProperty("browser");
		url=prop.getProperty("url");
		if (browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\browser\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"\\src\\test\\resources\\browser\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	//driver.manage().timeouts().pageLoadTimeout(UtilClass.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	//driver.manage().timeouts().implicitlyWait(UtilClass.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(url);
		
        }
	

}
	
	