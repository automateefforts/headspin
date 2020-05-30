package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class PropertiesUtil {

	private File file;
	private final String CONFIG_FILE_PATH =  "./Config.properties";
//	private final String LOG4J_FILE_PATH =  "./src/test/resources/log4j.properties";
	private PropertiesConfiguration config;
	private PropertiesConfigurationLayout layout;
	private InputStreamReader inputStreamReader;
	public static String SettingsMode;
	private static Hashtable<String, String> excelSettings;
	

		public PropertiesUtil() {
			
			try {
			inputStreamReader = new InputStreamReader(new FileInputStream(CONFIG_FILE_PATH));
			config = new PropertiesConfiguration();
			layout = new PropertiesConfigurationLayout(config);
			layout.load(inputStreamReader);
			} catch (Exception e) {
				e.printStackTrace();
			}
			excelSettings = getSettings();
			SettingsMode = excelSettings.get("Settings Mode");
		}
	

	public String getProperty(String key) {
		return String.valueOf(config.getProperty(key.trim().trim()));
	}

	public boolean setProperty(String Key, String Value) {

		try {
			config.setProperty(Key, Value);
			layout.save(new FileWriter(file.getAbsolutePath(), false));
			layout.load(inputStreamReader);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getProject() {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("ProjectName")).trim();
		else 
			return excelSettings.get("Project Name");
	}
	
	public String getEnvironment () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("Environement")).trim();
		else 
			return excelSettings.get("Environment");
	}
	
	public String getSuite () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("SuiteName")).trim();
		else 
			return excelSettings.get("Suite Name");
		
	}
	
	public String getExecutionMode () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("ExecutionMode")).trim();
		else 
			return excelSettings.get("Execution Mode");
	}
	
	public String getApplicationURL () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("ApplicationURL")).trim();
		else 
			return excelSettings.get("Application URL");
	}
	public String getBrowserClose () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("BrowserClose")).trim();
		else 
			return excelSettings.get("Browser Close"); 
	}
	
	public String getImplicitTime () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("ImplicitWait")).trim();
		else 
			return excelSettings.get("Implicit Wait");
	}
	
	public String getPageLoadTime () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("PageloadWait")).trim();
		else 
			return excelSettings.get("Pageload Wait");
	}
	
	public String getParallelExecution () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("ParallelExecution")).trim();
		else 
			return excelSettings.get("Parallel Run");
	}
	
	public String getHtmlReport () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("HtmlReports")).trim();
		else 
			return excelSettings.get("Html Reports");
	}
	public String getTCVideoReport () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("TestCaseVideoReports")).trim();
		else 
			return excelSettings.get("Testcase Video Reports");
	}
	public String getSuiteVideoReport () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("SuiteVideoReport")).trim();
		else 
			return excelSettings.get("Suite Video Reports");
	}
	
	public String getDocumentReport () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("DocumentReports")).trim();
		else 
			return excelSettings.get("Document Reports");
	}
	
	public String getLaunchReport () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("LaunchHtmlResults")).trim();
		else 
			return excelSettings.get("Launch Reports");
	}
	
	public String getGridHost () {
		if(SettingsMode.equals("Properties File"))
			return String.valueOf(config.getProperty("GridHost")).trim();
		else 
			return excelSettings.get("Suite Name");
	}	
	
	
	
	public Hashtable<String, String> getSettings() {
		Hashtable<String, String> settings = new Hashtable<String, String>();
		String testManagerFile = "./TestManager.xls";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(testManagerFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HSSFSheet sheet = workbook.getSheet("FrameworkSettings");
		HSSFRow row;
		HSSFCell cell;
		int lastRow =sheet.getLastRowNum();
		for (int i = 0; i <= lastRow; i++) {
			row = sheet.getRow(i);
			if(row!=null) {
				String key = null, value = null;
				cell = row.getCell(0);
					if(cell!=null) {
						key = getCellValueAsString(cell);
						}
				cell = row.getCell(1);
					if(cell!=null){
						value =getCellValueAsString(cell);
					}
				settings.put(key, value);
					
					}
			}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return settings;
	}
			
	private String getCellValueAsString(HSSFCell cell) {

		DataFormatter format = new DataFormatter();
		return format.formatCellValue(cell).trim();

	}
}
