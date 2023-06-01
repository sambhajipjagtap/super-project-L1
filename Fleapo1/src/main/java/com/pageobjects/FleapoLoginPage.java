package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testbase.TestBase;
import com.utility.SeleniumUtilities;

public class FleapoLoginPage extends TestBase
{
	public FleapoLoginPage()
	{
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author sambhaji
	 * This method is used for login fleapo
	 */
	
	//Click on Let's Start
	@FindBy (xpath = "//*[@type='button']")
	private WebElement letsStart;
	
	//mobile number
	@FindBy (xpath = "//*[@class='form-control ']")
	private WebElement mobile;
	
	//Click on Continue
	@FindBy (xpath = "//*[@type='submit']")
	private WebElement clickOnContinue;
	
	//Otp
	@FindBy (xpath = "//*[@aria-label='Please enter verification code. Digit 1']")
	private WebElement otp;
	
	
	public WebElement getLogin2(String mobileNumber1, String num)
	{
		SeleniumUtilities.click(letsStart, "Click on let's start");
		mobile.sendKeys(mobileNumber1);
		SeleniumUtilities.click(clickOnContinue, "click on continue");
		otp.sendKeys(num);
		return mobile;
		
	}
}
