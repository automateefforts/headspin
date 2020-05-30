package com.framework.util;

import org.openqa.selenium.WebDriver;

public class TestUtil {
	
	private String testcaseID;
	private String browser;
	private String scenario;
	private WebDriver driver;
	private String testDescription;
	private int currentIteration;
	private String testLogFilePath;
	private String keyword;
	private Excelutil excel;
	private PropertiesUtil properties;
	
	private int passSteps;
	private int failSteps;
	public Excelutil getExcelUtil() {
		return excel;
	}

	public void setExcelUtil(Excelutil excel) {
		this.excel = excel;
	}

	public PropertiesUtil getProperties() {
		return properties;
	}

	public void setProperties(PropertiesUtil properties) {
		this.properties = properties;
	}

	private int totalSteps;
	private int stepNumber;
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	private Report report;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	public int getPassSteps() {
		return passSteps;
	}

	public void setPassSteps(int passSteps) {
		this.passSteps = passSteps;
	}

	public int getFailSteps() {
		return failSteps;
	}

	public void setFailSteps(int failSteps) {
		this.failSteps = failSteps;
	}

	public int getTotalSteps() {
		return totalSteps;
	}

	public void setTotalSteps(int totalSteps) {
		this.totalSteps = totalSteps;
	}

	public String getTestLogFilePath() {
		return testLogFilePath;
	}

	public void setTestLogFilePath(String testLogFilePath) {
		this.testLogFilePath = testLogFilePath;
	}

	public int getCurrentIteration() {
		return currentIteration;
	}

	public void setCurrentIteration(int currentIteration) {
		this.currentIteration = currentIteration;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	

	public String getTestcaseID() {
		return testcaseID;
	}

	public void setTestcaseID(String testcaseID) {
		this.testcaseID = testcaseID;
	}

}
