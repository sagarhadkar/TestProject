
package com.qa.amazon.pages;

import java.io.IOException;
import java.util.Base64;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.amazon.base.AmazonBase;

public class LoginPage extends AmazonBase{

		public LoginPage() throws IOException {
		//	super();
		PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
		}
		
		//My creation
		//sign in button
		@FindBy(partialLinkText="Hello")
		WebElement signin;
		
		//sign in button 2
		@FindBy(partialLinkText="Sign")
		//@FindBy(xpath="//*[@id=\"nav-flyout-ya-signin\"]/a/span")
		WebElement btnsignin;

		@FindBy(name="email")
		WebElement emailid;
		
		@FindBy(id="continue")
		WebElement btnContinue;
		
		@FindBy(xpath="//a[contains(text(),'OTP')]")
		WebElement otp;
		
		@FindBy(name="password")
		WebElement password;
		
		@FindBy(xpath="//span[@id='auth-signin-button']")
		WebElement btnSubmit;
		
		//Actions:
		public String validateLoginPageTitle(){
			return driver.getTitle();
		}
		
		public boolean validateSignLink(){
			return signin.isEnabled();
		}
		
		
		// method to sign in
		public HomePage login(String email_id, String Pass) throws InterruptedException, IOException
		{
			Actions a = new Actions(driver);
			a.moveToElement(signin).build().perform();
			//signin.click();
			Thread.sleep(1000);
			Actions a1 = new Actions(driver);
			a1.moveToElement(btnsignin).click().build().perform();
			//btnsignin.click();
			Thread.sleep(1500);
		emailid.sendKeys(email_id);
			btnContinue.click();
			Thread.sleep(1000);
			//WebDriverWait wait=new WebDriverWait(driver,5);
			//wait.until(ExpectedConditions.visibilityOf(password));
			
			String Passd	=  new String(Base64.getDecoder().decode(Pass.getBytes()));
			password.sendKeys(Passd);
			
	a1.moveToElement(btnSubmit).click();
			
			Thread.sleep(1000);
			return new HomePage();
		}

		private String String(byte[] decode) {
			// TODO Auto-generated method stub
			return null;
		}
		


		
}


