
package com.qa.amazon.pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.amazon.base.AmazonBase;

public class HomePage extends AmazonBase{

	public HomePage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[contains(text(),'EN') and @class='icp-nav-language' ]")
	WebElement lanradiobtn;
	
	@FindBy(xpath="//input[@type='radio' and @value='hi_IN']/following-sibling::i")	
	WebElement lanchanradiobtn;
	
	@FindBy(className="a-button-input")
	WebElement cancelbtn;
	
	
	
	public void validateLanguageRadioButton()
	{
		

		lanradiobtn.sendKeys(Keys.ENTER);
				
	}
	
	//Actions
	
	public String verifyRadioButtonsPageTitle()
	{
		lanradiobtn.click();
		return driver.getTitle();
	}
	
	
	public void verifyChangeLanguages() throws InterruptedException
	{
		
	if(lanchanradiobtn.isSelected()==false)
		{
		lanchanradiobtn.click();
		}
	}
	
	public void verifyCancelPage()
	
	{
		
		cancelbtn.click();
	}

}