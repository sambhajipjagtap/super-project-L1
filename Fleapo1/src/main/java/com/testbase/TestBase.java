package com.testbase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase 
{
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	protected static Properties prop;
	static FileInputStream fis;
	public static WebDriver driver;
	@BeforeSuite
	public void start() throws IOException
	{
		log.info("Execution started");
		launchApp();
	}


	public static void loadConfig() throws IOException 
	{
		prop = new Properties();
		try {
			fis = new FileInputStream("resources/config.properties");
			prop.load(fis);
			log.info("Config.Property file loaded");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	} 
	public static void launchApp() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		loadConfig();
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox browser opend sucessfully");
		}

		else if (browserName.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);

		}

		else if (browserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


	}
}
