package com.utility;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testbase.TestBase;

public class SeleniumUtilities extends TestBase
{

	public static String homeWindow = null;

	/**
	 * Method to switch to the newly opened window
	 */
	public static void switchToWindow(WebDriver driver)
	{
		homeWindow = driver.getWindowHandle();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
			System.out.println("Successfully switched to new window");
		}
	}

	/**
	 * To navigate to the main window from child window
	 */
	public static void switchToMainWindow(WebDriver driver) {
		for (String window : driver.getWindowHandles()) {
			if (!window.equals(homeWindow)) {
				driver.switchTo().window(window);
				driver.close();
			}
			driver.switchTo().window(homeWindow);
			System.out.println("Successfully switched to main window");
		}
	}


	/**
	 * Method to accept alert
	 */
	public static void accept_Alert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("alert accepted successfully");
		} catch (Exception e) {
			System.out.println("Alert is not accepted" + e.toString());
		}
	}

	/**
	 * This method returns the no.of windows present
	 *
	 * @return count
	 */
	public static int getWindowCount(WebDriver driver) {
		return driver.getWindowHandles().size();
	}

	/**
	 * To switch into frame
	 */
	public static void frames(WebElement frameElement, WebDriver driver) {
		try {
			driver.switchTo().frame(frameElement);
			System.out.println("successfully switched to frame");
		} catch (Exception e) {
			System.out.println("failed while switching to frame");
		}
	}

	/**
	 * Bring back to default frame
	 */
	public static void switchToDefaultcontent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
			System.out.println("successfully switched to default frame");
		} catch (NoSuchElementException e) {
			System.out.println("failed switched to default frame");
		}
	}

	/**
	 * Navigate to Url
	 */
	public static void navigateToUrl(String url, WebDriver driver) {
		try {
			driver.navigate().to(url);
			System.out.println("Application launched successfully to" + url);

		} 
		catch (Exception e) {
			System.out.println("Failed to load the url" + url + e.getMessage());
		}
	}

	/**
	 * Close the current window
	 */
	public static void closeBrowser(WebDriver driver) 
	{
		try 
		{
			driver.close();
			System.out.println("Killing Chrome driver process after");
			driver.quit();
			System.out.println("Browser closed successfully");
		} 
		catch (Exception e) 
		{
			System.out.println("Browser is not closed");
		}
	}

	/**
	 * Clear end enter text in text box
	 */
	public static void setText(WebElement element, String value, String fieldName)
	{
		try
		{
			element.clear();
			element.sendKeys(value);
			System.out.println("Killing Chrome driver process after");
			System.out.println(value + " entered in " + fieldName + " textbox successfully");
		}
		catch (Exception e)
		{
			System.out.println("Killing Chrome driver process after");
			System.out.println("failed to enter" + value + "into" + "textbox " + element.toString());
		}
	}

	/**
	 * Verifying the visibility of element only for assert conditions
	 */

	public static boolean isElementPresent(WebElement element, WebDriver driver) {
		boolean elementPresent = false;
		try {
			waitForElementVisibility(element, driver);
			if (element.isDisplayed()) {
				elementPresent = true;
			}
			System.out.println("Element displayed successfully");
		} catch (Exception e) {
			System.out.println("Verify Element Present failed" + e.toString());
		}
		return elementPresent;
	}

	/**
	 * Verifying the visibility of element only for assert conditions
	 */

	public static boolean isElementNotPresent(WebElement element) {
		boolean elementNotPresent = true;
		try {
			if (element.isDisplayed()) {
				elementNotPresent = false;
			}
			System.out.println("Element is Displayed successfully");
		} catch (Exception e) {
			System.out.println("Verify Element Present failed" + e.getMessage());
		}
		return elementNotPresent;
	}

	/**
	 * Method to click the element
	 */
	public static void click(WebElement element, String fieldName) {
		try {
			element.click();
			System.out.println(fieldName + " is clicked successfully");

		} catch (Exception e) {
			System.out.println(element.toString() + "element is not clicked" + e.getMessage());
		}
	}

	/**
	 * getting the text from non editable field
	 */

	public static String getText(WebElement element, WebDriver driver, String fieldName) {
		String text = null;
		try {
			waitForElementVisibility(element, driver);
			if (element.getText() != null) {
				text = element.getText();
			}
			System.out.println(fieldName + " retrieved successfully from element");
		} catch (Exception e) {
			System.out.println("text is not retrieved from element" + element.toString() + e.getMessage());
		}
		return text;
	}

	/**
	 * Method to get the value from textbox
	 */
	public static String getValue(WebElement element, WebDriver driver) {
		String value = null;
		try {
			waitForElementVisibility(element, driver);
			if (element.getAttribute("value") != null) {
				value = element.getAttribute("value");
			}
			System.out.println("text retrieved successfully from element" + element.toString());
		} catch (Exception e) {
			System.out.println("text is not retrieved from element" + element.toString() + e.getMessage());
		}
		return value;
	}

	/**
	 * Method to select the option from dropdown by value
	 */ 
	public static void selectByValue(WebElement element, String value) {
		try {
			Select obj_select = new Select(element);
			obj_select.selectByValue(value);
			System.out.println(value + "selected from dropdown " + element.toString());
		} 
		catch (Exception e) {
			System.out.println("failed to select" + value + "from " + element.toString());
		}
	}

	/**
	 * Method to select the option from dropdown by index
	 */
	public static void selectByIndex(WebElement element, int index) {
		try {
			Select obj_select = new Select(element);
			obj_select.selectByIndex(index);
			System.out.println(index + "index selected from dropdown " + element.toString());
		} 
		catch (Exception e) {
			System.out.println("failed to select" + index + "index" + "from " + element.toString());
		}
	}

	/**
	 * Method retrieve the value selected in the drop down
	 */
	public String getValueSelectedInTheDropDown(WebElement element) {
		String getSelectedItem = "";
		try {
			Select obj_select = new Select(element);
			getSelectedItem = obj_select.getFirstSelectedOption().getText();
			System.out.println("Successfully fetched fetched the selected item from the drop down.");
			return getSelectedItem;
		}
		catch (Exception e) {
			System.out.println("Failed to retrieve the selected value from the drop down" + element.toString());
			return getSelectedItem;
		}

	}

	/**
	 * To pause execution until get expected elements visibility
	 */
	public static void waitForElementVisibility(WebElement element, WebDriver driver) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * To pause execution until get expected elements clickable
	 */
	public static void waitAndClick(WebElement element, WebDriver driver, String fieldName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			System.out.println(fieldName + " is clicked successfully");
		} 
		catch (Exception e) {
			System.out.println("Element is not clickable at this point exception");
			System.out.println(element.toString() + "element is not clicked" + e.getMessage());
		}
	}

	/**
	 * Method to perform mouseover action
	 */
	public static void mouseOver(WebElement element, WebDriver driver) {
		try {
			Actions action = new Actions(driver); 
			action.moveToElement(element).build().perform();
			System.out.println("Mouseover to the element" + element.toString() + "is success");
		}
		catch (Exception e) {
			System.out.println("Mouseover to the element" + element.toString() + "is failed");
		}
	}

	/**
	 * Method to get random string
	 */
	public static String getRandomString(int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		if (sb.length() == 0)
			sb.append("test");
		return sb.toString();
	}

	/**
	 * Method to get first selected value from dropdown
	 */
	public String dropDownSelectedValue(WebElement element) {
		Select select = new Select(element);
		String selectedOption = select.getFirstSelectedOption().getText();
		return selectedOption;
	}

	/**
	 * To get default selected value from drop down
	 */

	public static String getDefaultDropDownValue(WebElement element) throws InterruptedException {

		Select obj_select = new Select(element);
		return obj_select.getFirstSelectedOption().getText();

	}

	/**
	 * Method to perform javascript click
	 */
	public static void clickjs(WebElement element, WebDriver driver) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
			System.out.println(element.toString() + "element " + element.getText() + " is clicked successfully");
		} 
		catch (Exception e) {
			System.out.println(element.toString() + "element is not clicked" + e.getMessage());
		}
	}

	/**
	 * Method to perform click
	 */
	public static void clickAction(WebElement element, WebDriver driver) {
		try {
			Actions elementToClick = new Actions(driver);
			elementToClick.click().perform();
			System.out.println(element.toString() + "element is clicked successfully Using Actions");

		} 
		catch (Exception e) {
			System.out.println(element.toString() + "element is not clicked" + e.getMessage());
		}
	}

	//wait
	public static void wait(int sec)
	{
		try {
			Thread.sleep(sec);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method to scroll page up for element visibility using java script
	 */
	public static void jsScrollPageUp(WebElement element, WebDriver driver) {
		try {
			int yScrollPosition = element.getLocation().getY();
			System.out.println("yScrollPosition:" + yScrollPosition);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, " + -yScrollPosition + ")", "");
			System.out.println("scroll page up" + "page is scrolled up successfully");
		} catch (Exception e) {
			System.out.println("page is not scrolled up ");
		}
	}

	/**
	 * Method to scroll page up for element visibility using java script
	 */
	public static void jsScrollPageDown(WebElement element, WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			System.out.println("scroll page down" + "page is scrolled down successfully");
		} catch (Exception e) {
			System.out.println("page is not scrolled up ");
		}
	}

	/**
	 * quit Browser
	 */
	public static void quitBrowser(WebDriver driver) {
		try {
			driver.quit();
			System.out.println("Browser quited successfully");
		} catch (Exception e) {
			System.out.println("Browser quited successfully: " + e);
		}
	}

	/**
	 * wait For Specific text to be present
	 */
	public static void waitForTextToBePresent(WebElement element, String text, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * method to scroll down
	 */
	public static void scrollDownToElementView(String elementXpath, WebDriver driver) throws Throwable {
		try {
			WebElement element = driver.findElement(By.xpath(elementXpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (WebDriverException we) {
			System.out.println("Unable to scroll to the web element: " + we);
			System.out.println("Unable to scroll to the webelement: " + we);

		}
	}

	/**
	 * method to scroll down
	 */
	public static void scrollUpToElementView(String elementXpath, WebDriver driver) throws Throwable {
		try {
			WebElement element = driver.findElement(By.xpath(elementXpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		} catch (WebDriverException we) {
			System.out.println("Unable to scroll to the web element: " + we);
			System.out.println("Unable to scroll to the webelement: " + we);

		}
	}



	public static void SelectAll() throws AWTException {
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_CONTROL);
		rbt.keyPress(KeyEvent.VK_A);
		rbt.keyPress(KeyEvent.VK_DOWN);


		rbt.keyRelease(KeyEvent.VK_CONTROL);
		rbt.keyRelease(KeyEvent.VK_A);
	}

	public static void DeleteAll() throws AWTException {
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_DELETE);
		rbt.keyRelease(KeyEvent.VK_DELETE);
	}

	public static void KeysDown() throws AWTException {
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_DOWN);
		rbt.keyRelease(KeyEvent.VK_DOWN);
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);
	}
}