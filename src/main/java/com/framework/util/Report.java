package com.framework.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Report {
	private TestUtil testutil;
	public Report(TestUtil testutil){
		this.testutil = testutil;
	}
	
	public void log(String testStep, String stepDescription, Status status){
		HTMLReportGenerator html = new HTMLReportGenerator();
		
		String keyword = testutil.getKeyword();
		int iteration  = testutil.getCurrentIteration();
		String timestamp = SuiteUtil.getCurrentDateTime();
		String reportFilePath = testutil.getTestLogFilePath();
		testutil.setStepNumber(testutil.getStepNumber()+1);
		int stepNo = testutil.getStepNumber();
		int passSteps = testutil.getPassSteps();
		int failSteps = testutil.getFailSteps();
		String screenshotPath = new String();
		switch (status) {
		
		case ERROR:
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "ERROR", "None", reportFilePath);
					break;
		case FAIL:
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "FAIL", "None", reportFilePath);
					testutil.setFailSteps(++failSteps);
			break;
			
		case FAIL_SCREENSHOT:
					screenshotPath = takeScreenshot();
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "FAIL", screenshotPath, reportFilePath);
					testutil.setFailSteps(++failSteps);
			break;
			
		case PASS:
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "PASS", "None", reportFilePath);
					testutil.setPassSteps(++passSteps);
			break;
			
		case PASS_SCREENSHOT:
					screenshotPath = takeScreenshot();
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "PASS", screenshotPath, reportFilePath);
					testutil.setPassSteps(++passSteps);
			break;
			
		case DONE:
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "DONE", "None", reportFilePath);
					
			break;
			
		case SKIP:
					html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "SKIP", "None", reportFilePath);
					
			break;
			
		case SCREENSHOT:
			screenshotPath = takeScreenshot();
			html.appendTestLog(keyword, iteration, stepNo, testStep, stepDescription, timestamp, "SCREENSHOT", screenshotPath, reportFilePath);
			
			break;
			
		}
		
	}

	
	private String takeScreenshot() {
		WebDriver driver = testutil.getDriver();
		String scenario = testutil.getScenario();
		String testcase = testutil.getTestcaseID();
		String browser= testutil.getBrowser();
		int iteration = testutil.getCurrentIteration();
		String screenshotPath = SuiteUtil.getCurrentResultsPath()+"Screenshots/"+
				scenario+"_"+testcase+"_Iteration "+iteration+"_"+browser+System.currentTimeMillis()+".png";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}
	
	public void consolidateScreenshotsInWordDoc() {
	
		try {
			String screenshotsConsolidatedFolderPath = SuiteUtil.getCurrentResultsPath()
					+ SuiteUtil.getFileSeparator()
					+ "Screenshots (Consolidated)";
			new File(screenshotsConsolidatedFolderPath).mkdir();
			
			String scenario = testutil.getScenario();
			String testcase = testutil.getTestcaseID();
			String browser= testutil.getBrowser();
			int iteration = testutil.getCurrentIteration();
			String testcaseName = 
					scenario+"_"+testcase+"_Iteration "+iteration+"_"+browser;
			
			WordDocumentManager documentManager = new WordDocumentManager(
					screenshotsConsolidatedFolderPath,testcaseName);

			String screenshotsFolderPath = SuiteUtil.getCurrentResultsPath()+"/Screenshots/";
			File screenshotsFolder = new File(screenshotsFolderPath);

			FilenameFilter filenameFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String fileName) {
					return fileName.contains(testcaseName);
				}
			};
			File[] screenshots = screenshotsFolder.listFiles(filenameFilter);
			if (screenshots != null && screenshots.length > 0) {
				documentManager.createDocument();

				for (File screenshot : screenshots) {
					documentManager.addPicture(screenshot);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
