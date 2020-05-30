package com.framework.util;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

public class TestNGBase extends BaseClass{
	
	private String suiteStartTime; 
	private String suiteEndTime; 
	private String testStartTime; 
	private String testEndTime; 
	private String suiteTotalDuration;
	private String testExecutionDuation;
	private static int totalPass;
	private static int totalFail;
	private static int totalTestcases;
	protected TestUtil testUtil;
	protected String testLogFilePath;
	protected Report report;
	protected Excelutil excel;
	public VideoRecorder TC_Video_Recorder ;
	public VideoRecorder Suite_Video_Recorder ;
	
	
	static Logger logger = Logger.getLogger(TestNGBase.class);
	@BeforeSuite(alwaysRun=true)
	public void beforeSuite(ITestContext context) {
		 String log4jConfigFile = "./src/test/resources/log4j.properties";
		 PropertyConfigurator.configure(log4jConfigFile);
		 setupParallelExecution(context);
			suiteStartTime = SuiteUtil.getCurrentDateTime();
			SuiteUtil.createResultsFolder();
			String suiteName = properties.getSuite();
			context.getCurrentXmlTest().getSuite().setName(suiteName);
			createSummaryReport();
			startSuiteVideoRecord();
	}
	
	
	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		testStartTime = SuiteUtil.getCurrentDateTime();
		String browser = testUtil.getBrowser();
		driver = getDriver(browser);
		testUtil.setDriver(driver);
		testUtil.setCurrentIteration(testUtil.getCurrentIteration()+1);
		testLogFilePath = createTestLog();
		testUtil.setTestLogFilePath(testLogFilePath);
		startTCVideoRecord();
		
		
	}

	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(properties.getBrowserClose().equalsIgnoreCase("True") || 
		   properties.getBrowserClose().equalsIgnoreCase("Yes")) {
			driver.close();
		}
		testEndTime = SuiteUtil.getCurrentDateTime();
		testExecutionDuation = SuiteUtil.getTimeDifference(testStartTime, testEndTime);
		String testStatus = null;
		if(result.getStatus() == ITestResult.FAILURE || testUtil.getFailSteps()!=0){
			++totalFail;
			testStatus = "Failed";
		}else if(result.getStatus() == ITestResult.SUCCESS && testUtil.getFailSteps()==0){
			++totalPass;
			testStatus = "Passed";
		}else {
			testStatus="Skipped";
		}
		
		if(properties.getDocumentReport().equalsIgnoreCase("True") ||
				properties.getDocumentReport().equalsIgnoreCase("Yes")	) {
				report.consolidateScreenshotsInWordDoc();
		}else {
			logger.info("Document Report generation is disabled");
		}
		
		totalTestcases = totalPass+totalFail;
		excel.putData("Keywords", "Status", testStatus);
		finishTestLog();
		if(!testStatus.equals("Skipped"))
			addTestSummaryReport(testStatus, testLogFilePath);
		stopTCVideoRecord();
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite(){
		suiteEndTime = SuiteUtil.getCurrentDateTime();
		suiteTotalDuration = SuiteUtil.getTimeDifference(suiteStartTime, suiteEndTime);
		finishSummaryReport();
		if(totalTestcases==0 && totalPass==0 && totalFail==0) {
			SuiteUtil.launchLog();
			throw new FrameworkException(System.lineSeparator()+"Minimum Single Testcase Is Need To Enable In Testmanager To Start Execution."
					+System.lineSeparator()+"Execution Terminated..........!");
		}
		else {
			SuiteUtil.launchResults();
		}
		
		stopSuiteVideoRecord();
	}
	
	@DataProvider
	public Object[][] dataProvider() {
		testUtil = new TestUtil();
		report = new Report(testUtil);
		testUtil.setReport(report);
		excel= new Excelutil(testUtil);
		testUtil.setProperties(properties);
		testUtil.setExcelUtil(excel);
		String testcase  = this.getClass().getSimpleName();
		testUtil.setTestcaseID(testcase);
		String TestManagerSheetName = properties.getSuite();
		Hashtable<String, String> testDetail = excel.readTestManager(TestManagerSheetName, testcase);
		String runMode = testDetail.get("RunMode");
		if(!runMode.equals("Yes")){
			throw new SkipException("Test case run mode not enabled in TestManager excel file........!");
		}
		String scenario = testDetail.get("Scenario");
		String browser = testDetail.get("Browser");
		String description = testDetail.get("Description");
		int runCount = Integer.parseInt(testDetail.get("Run(s)"));
		testUtil.setBrowser(browser);
		testUtil.setScenario(scenario);
		testUtil.setTestDescription(description);
		
		Object[][] data = new Object[runCount][1];
		for (int i = 0; i < runCount; i++) {
			data[i][0] = i+1;
		}
		return data;
	}
	
	private void createSummaryReport(){
		if(properties.getHtmlReport().equalsIgnoreCase("True") || properties.getHtmlReport().equalsIgnoreCase("Yes")) {
			HTMLReportGenerator html = new HTMLReportGenerator();
			String _OS = SuiteUtil.getOSName();
			html.createSummaryHtmlHeader(properties.getProject(), properties.getEnvironment(), properties.getSuite(), _OS, SuiteUtil.getCurrentDateTime());
		}
		else {
			logger.info("Html Reports Generation is Disabled. Hence Unable To Create Summary HTML Report");
		}
	}
	
	private void finishSummaryReport(){
		if(properties.getHtmlReport().equalsIgnoreCase("True") || properties.getHtmlReport().equalsIgnoreCase("Yes")) {
			HTMLReportGenerator html = new HTMLReportGenerator();
			html.createSummaryHtmlFooter(suiteTotalDuration, totalTestcases,totalPass, totalFail);
		}
		else {
			logger.info("Html Reports Generation is Disabled. Hence Unable To Finish Summary HTML Report");
		}
	}
	
	private void addTestSummaryReport(String status, String link){
		if(properties.getHtmlReport().equalsIgnoreCase("True") || properties.getHtmlReport().equalsIgnoreCase("Yes")) {
			HTMLReportGenerator html  = new HTMLReportGenerator();
			html.appendTestCaseToSummaryHtml(testUtil.getScenario(), 
			testUtil.getTestcaseID(), testUtil.getTestDescription(), 
			testExecutionDuation, testUtil.getBrowser(), status , link, testUtil.getCurrentIteration());
		}
		else {
			logger.info("Html Reports Generation is Disabled. Hence Unable To Add Testlog Summary HTML Report");
		}
		
	}
	private String createTestLog(){
		String testLogPath = SuiteUtil.getHTMLReportsPath()+"/"+testUtil.getScenario()+"_"+testUtil.getTestcaseID()+"_"+testUtil.getBrowser()+"_"+testUtil.getCurrentIteration()+".html";
		if(properties.getHtmlReport().equalsIgnoreCase("True") || properties.getHtmlReport().equalsIgnoreCase("Yes")) {
			HTMLReportGenerator html  = new HTMLReportGenerator();
			html.createTestLogHeader(properties.getProject(), testUtil.getScenario(), testUtil.getTestcaseID(), 
			testUtil.getCurrentIteration(), testUtil.getBrowser(), SuiteUtil.getCurrentDateTime(), properties.getExecutionMode(),testLogPath);
		}
		else {
			logger.info("Html Reports Generation is Disabled. Hence Unable To Create TestLog HTML Report");
		}
		return testLogPath;
	}
	private void finishTestLog(){
		HTMLReportGenerator html  = new HTMLReportGenerator();
		if(properties.getHtmlReport().equalsIgnoreCase("True") || properties.getHtmlReport().equalsIgnoreCase("Yes")) {
			html.createTestLogFooter(testExecutionDuation, testUtil.getPassSteps(),testUtil.getFailSteps(),  testLogFilePath);
		}
		else {
			logger.info("Html Reports Generation is Disabled. Hence Unable To Finish Testlog HTML Report");
		}
	}
	
	public void executeKeywords(String Scenario,String TestCaseName, int iterationNo) {
		ArrayList<String> keywordsContainer = excel.getActionKeywords(Scenario,TestCaseName,iterationNo);
		if(keywordsContainer.isEmpty()) {
			report.log("Error", "Keywords for Testcase: "+TestCaseName+" with Run(s): "+iterationNo+" are not available in "+Scenario+" Excel file"
					, Status.FAIL);
		}
		String tempKeyword = "";
		int tempIteration;
		Hashtable<String, Integer> keywordsCounter = new Hashtable<String, Integer>();
		HTMLReportGenerator html = new HTMLReportGenerator();
		try {
			for (String currentKeyword : keywordsContainer) {
				if(currentKeyword.contains(",")) {
					tempKeyword = currentKeyword.split(",")[0];
					tempIteration = Integer.parseInt(currentKeyword.split(",")[1]);
					keywordsCounter.put(tempKeyword, tempIteration);
				}else {
							tempKeyword = currentKeyword;
							if(keywordsCounter.containsKey(tempKeyword))
								keywordsCounter.put(tempKeyword, keywordsCounter.get(tempKeyword)+1);
							else
								keywordsCounter.put(tempKeyword, 1);
							if(keywordsCounter.get(tempKeyword)>iterationNo) {
								tempIteration = keywordsCounter.get(tempKeyword);
							}else
								tempIteration = iterationNo;
								
					}
				if(properties.getHtmlReport().equalsIgnoreCase("True") || properties.getHtmlReport().equalsIgnoreCase("Yes")) {
						html.createTestLogKeyword(tempKeyword,tempIteration, testUtil.getTestLogFilePath());
				}
				else {
					logger.info("Html Reports Generation is Disabled. Hence Unable To Add Keyword to Testlog HTML Report");
				}
				runKeyword(tempKeyword, testUtil);
			}
		} catch (InvocationTargetException Ix) {
			if(Ix.getMessage()==null) {
				exceptionHandler(Ix, Ix.getTargetException().toString());
			}else
			exceptionHandler(Ix, Ix.getMessage());
			throw new FrameworkException("Exception in Keyword: " + tempKeyword);

		} catch (FrameworkException fx) {
			exceptionHandler(fx, fx.getErrorName());
		} catch (Exception ex) {
			exceptionHandler(ex, "Error");
		}
	}
	
	
	private boolean runKeyword(String currentKeyword, TestUtil testUtil)
			throws IllegalAccessException, InvocationTargetException,
			ClassNotFoundException, InstantiationException {
		Boolean isMethodFound = false;
		final String CLASS_FILE_EXTENSION = ".class";
		File root = new File(System.getProperty("user.dir")+"/target/classes/");
		List<File> packageDirectories = getKeywordsFolders(root);
		for (File packageDirectory : packageDirectories) {
			File[] packageFiles = packageDirectory.listFiles();
			String packageName = packageDirectory.getName();
			for (int i = 0; i < packageFiles.length; i++) {
				File packageFile = packageFiles[i];
				String fileName = packageFile.getName();
				// We only want the .class files
				if (fileName.endsWith(CLASS_FILE_EXTENSION)) {
					// Remove the .class extension to get the class name
					String className = fileName.substring(0, fileName.length()
							- CLASS_FILE_EXTENSION.length());
					Class<?> reusableComponents;
					try {
					 reusableComponents = Class.forName(packageName+ "." + className);
					}
					catch(Exception e) {
						 reusableComponents = Class.forName("com.intellect.actionkeywords." + className);
					}
					Method executeComponent;
					try {
						// Convert the first letter of the method to lowercase
						// (in line with java naming conventions)
						currentKeyword = currentKeyword.substring(0, 1)
								.toLowerCase() + currentKeyword.substring(1);
						executeComponent = reusableComponents.getMethod(
								currentKeyword);
					} catch (NoSuchMethodException ex) {
						// If the method is not found in this class, search the
						// next class
						continue;
					}

						isMethodFound = true;
					//_______________________________________________________________________
					Constructor<?> ctor = reusableComponents
							.getDeclaredConstructors()[0];
					Object businessComponent = ctor.newInstance(testUtil);
					executeComponent.invoke(businessComponent, (Object[]) null);
					break;
				}
			}
			if(isMethodFound) break;
		}

		if (!isMethodFound) {
			throw new FrameworkException("Keyword '" + currentKeyword
					+ "' not found within any class "
					+ "inside the "+packageDirectories+" package");
		}
		return isMethodFound;
	}
	
	private List<File> getKeywordsFolders(File root) {
		  List<File> result = new ArrayList<>();
		  for (File file : root.listFiles()) {
		    if (file.isDirectory()) {
		    	String filepath = file.getAbsolutePath().toLowerCase(); 
		      if (filepath.contains("action") || filepath.contains("keywords")) {
		    	  	result.add(file);
		      }
		      result.addAll(getKeywordsFolders(file));
		    }
		  }
		  return result;
		}
	
	private void exceptionHandler(Exception ex, String exceptionName) {
		// Error reporting
		if (exceptionName == null) {
			exceptionName = ex.getCause().toString();
		}
		
		if (driver != null) {
			report.log("Exception","<b>Caused by: </b>"+exceptionName,Status.FAIL_SCREENSHOT);
			ex.printStackTrace();
			
		} else {
			report.log("Exception","<b>Caused by: </b>"+exceptionName,Status.FAIL);
			ex.printStackTrace();
		}
}
	
	private void startTCVideoRecord() {
		String stattus = properties.getTCVideoReport();
		String parallelRunStatus = properties.getParallelExecution();
		// test case level video record will happen without parallel execution mode.
		if((stattus.equalsIgnoreCase("True") ||stattus.equalsIgnoreCase("Yes")) && 
				(parallelRunStatus.equalsIgnoreCase("False") || parallelRunStatus.equalsIgnoreCase("No"))	) {
			String videoName = testUtil.getScenario()+"_"+ testUtil.getTestcaseID()+"_"+
					"Iteration "+testUtil.getCurrentIteration()+"_"+testUtil.getBrowser();
			this.TC_Video_Recorder = new VideoRecorder();
			try {
				this.TC_Video_Recorder.startRecording(videoName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void stopTCVideoRecord()  {
		String stattus = properties.getTCVideoReport();
		String parallelRunStatus = properties.getParallelExecution();
		// test case level video record will happen without parallel execution mode.
		if((stattus.equalsIgnoreCase("True") ||stattus.equalsIgnoreCase("Yes")) && 
			(parallelRunStatus.equalsIgnoreCase("False") || parallelRunStatus.equalsIgnoreCase("No"))	) {
			try {
				TC_Video_Recorder.stopRecording();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			logger.warn("Video Record At Testcase Level Not Possible In Parallel Execution Mode. Hence Testcase Execution Video Files Not Generated.");
		}
	}
	private void startSuiteVideoRecord() {
		String stattus = properties.getSuiteVideoReport();
		if(stattus.equalsIgnoreCase("True") || stattus.equalsIgnoreCase("Yes")) {
			String videoName = properties.getSuite()+" Suite Video";
			this.Suite_Video_Recorder = new VideoRecorder();
			try {
				this.Suite_Video_Recorder.startRecording(videoName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void stopSuiteVideoRecord()  {
		String stattus = properties.getSuiteVideoReport();
		if (stattus.equalsIgnoreCase("True") || stattus.equalsIgnoreCase("Yes")) {
			try {
				Suite_Video_Recorder.stopRecording();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setupParallelExecution(ITestContext context) {
		String parallelRun = properties.getParallelExecution();
		if(parallelRun.equalsIgnoreCase("True") || parallelRun.equalsIgnoreCase("Yes")) {
			context.getCurrentXmlTest().getSuite().setParallel("classes");
		}
	}
}
