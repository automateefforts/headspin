package com.framework.util;

import java.util.Properties;

import org.openqa.selenium.WebDriver;



/**
 * Abstract base class for reusable libraries created by the user
 * @author Cognizant
 */
public abstract class ReusableLibrary
{
	/**
	 * The {@link CraftDataTable} object (passed from the test script)
	 */
	protected Excelutil excel;
	/**
	 * The {@link SeleniumReport} object (passed from the test script)
	 */
	protected Report report;
	/**
	 * The {@link WebDriver} object
	 */
	protected WebDriver driver;
	/**
	 * The {@link ScriptHelper} object (required for calling one reusable library from another)
	 */
	protected TestUtil testutil;
	
	/**
	 * The {@link Properties} object with settings loaded from the framework properties file
	 */
	protected PropertiesUtil properties;
	/**
	 * The {@link FrameworkParameters} object
	 */
	//protected FrameworkParameters frameworkParameters;
	
	
	/**
	 * Constructor to initialize the {@link ScriptHelper} object and in turn the objects wrapped by it
	 * @param scriptHelper The {@link ScriptHelper} object
	 */
	public ReusableLibrary(TestUtil testutil)
	{
		this.testutil = testutil;
		this.excel = testutil.getExcelUtil();
		this.report = testutil.getReport();
		this.driver = testutil.getDriver();
		this.properties = testutil.getProperties();
		
	}
}