package com.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.inject.spi.Element;
import com.testbase.TestBase;
import com.utility.SeleniumUtilities;


public class FleapoHomePage extends TestBase
{


	public FleapoHomePage()
	{
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author sambhaji
	 * This is for profile
	 */

	//Page name
	@FindBy (xpath = "//*[@type='text']")
	private WebElement pageName;

	public void getPageName(String pName) 
	{
		String s = Keys.chord(Keys.CONTROL, "a");
		pageName.sendKeys(s);
		pageName.sendKeys(Keys.BACK_SPACE);
		pageName.sendKeys(pName);
	}
	//Intro message
	@FindBy (xpath = "//*[@id='IntroMessage']")
	private WebElement introMessage;

	public void introMessage(String intro) 
	{
		introMessage.clear();
		introMessage.sendKeys(intro);
	}

	//Add social links
	@FindBy (xpath = "//button[@class='cta button w-full undefined rounded-full text-black bg-blue-light']")
	private WebElement addSocialLinks;

	//Select platform
	@FindBy (xpath = "//select[@id='platform']")
	private WebElement selectPlatform;

	//userfour
	@FindBy (xpath ="//input[@class='pl-[135px!important] mt-2']")
	private WebElement link;

	//add
	@FindBy (xpath = "//button[@type='submit']")
	private WebElement addLink;

	public void addSocialLink(String links)
	{
		addSocialLinks.click();
		SeleniumUtilities.selectByIndex(selectPlatform, 2);
		link.sendKeys(links);
		addLink.click();
	}

	//enable messaging
	@FindBy (xpath = "//button[@type='button' and @class='cta button w-full mt-2 rounded-full text-white bg-black']")
	private WebElement enableMessaging;

	//Monetization settings
	@FindBy (xpath = "//h5[normalize-space()='Monetization Settings']")
	private WebElement setting;

	public void enableMessaging()
	{
		enableMessaging.click();
		setting.click();
	}

	//Monetization settings
	@FindBy (xpath = "//div[@class='flex-center-between']//div[@class='cursor-pointer']")
	private WebElement monaSetting;

	public void monetizationSetting() throws InterruptedException
	{
		monaSetting.click();
	}

	//Stripe express
	@FindBy (xpath = "//div[@class='PressableCore PressableCore--cursor--text PressableCore--height--medium PressableCore--radius--all PressableCore--width PressableCore--width--auto PressableField CodePuncher-pressable Box-root Flex-inlineFlex']")
	private WebElement stripeExpress;


	public void stripeExpress(String otp1) throws InterruptedException
	{
		driver.get("https://connect.stripe.com/internal_express_login/acct_1NC1tjQ9A3tDxlfL/yYtfWbEckRsz");
		SeleniumUtilities.switchToWindow(driver);
		Thread.sleep(10000);
		stripeExpress.click();
		stripeExpress.sendKeys(otp1);
		
	}
	
}