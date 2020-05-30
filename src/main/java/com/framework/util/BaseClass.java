package com.framework.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.SkipException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;
	private int implicitWait;
	private int pageloadWait;
	protected PropertiesUtil properties = new PropertiesUtil();
	public WebDriver getDriver(String _BrowserType) {
		Browser browser = Browser.valueOf(_BrowserType.toUpperCase());
		
		switch (browser) {
							case CHROME:
//											String chromeDriverPath  = "";
//											System.setProperty("webdriver.chrome.driver", chromeDriverPath);
											WebDriverManager.chromedriver().setup();
											ChromeOptions options = new ChromeOptions(); 
											options.addArguments("start-maximized");
											options.setExperimentalOption("useAutomationExtension", false);
											options.addArguments("log-level=3");
											options.addArguments("--silent");
											options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
											driver = new ChromeDriver(options); 
											break;
					
							case FIREFOX:
//											String geckoDriverPath  = "";
//											System.setProperty("webdriver.gecko.driver", geckoDriverPath);
											WebDriverManager.firefoxdriver().setup();
											driver = new FirefoxDriver();
											break;
								
							case INTERNET_EXPLORER:
//											String ieDriverPath  = "";
//											System.setProperty("webdriver.gecko.driver", ieDriverPath);
											WebDriverManager.iedriver().setup();
											driver = new InternetExplorerDriver();
											break;
								
							default:		throw new SkipException("Invalid Browser Type");
		
							
						}
		
						EventFiringWebDriver event1=new EventFiringWebDriver(driver);
						WebDriverListner listnerObj = new WebDriverListner();
						event1.register(listnerObj);
						driver = event1;
						driver.manage().window().maximize();
						implicitWait = Integer.parseInt(properties.getImplicitTime());
						pageloadWait =Integer.parseInt(properties.getPageLoadTime());
						driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
						driver.manage().timeouts().pageLoadTimeout(pageloadWait, TimeUnit.SECONDS);
						return driver;
	}

}
