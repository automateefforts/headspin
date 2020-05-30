package com.framework.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class WebDriverListner implements WebDriverEventListener, ITestListener {
	
	static Logger logger = Logger.getLogger(WebDriverListner.class);
	@Override
	public void beforeAlertAccept(WebDriver driver) {
		
		
	}

	@Override
	public void afterAlertAccept(WebDriver driver) {
		
		logger.info("Alert is accepted succesful...");
	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {
		
		logger.info("Alert is dismissed succesful...");
	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {
		
		
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		logger.info(url+" is navigated");
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		
		
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		
		
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		
		
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		
		
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		
		
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		
		
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		
		
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		
		
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		
		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		
		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		
		
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		
		
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		
		
	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		
		
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		
		
		
	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		
		
	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("------------------------------------------------------");
		logger.info(result.getMethod().getTestClass().getName()+" is Started");
	}
// ****************************** TestNG listeners *******************************
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getTestClass().getName()+" is PASSED");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info(result.getMethod().getTestClass().getName()+" is FAILED "+System.lineSeparator()+result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info(result.getMethod().getTestClass().getName()+" is skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started.");
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test Suite Finished.");
	}

}
