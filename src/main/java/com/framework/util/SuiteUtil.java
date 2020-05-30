package com.framework.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class SuiteUtil {
	private static String resultsPath;
	private static PropertiesUtil properties = new PropertiesUtil();
	static Logger logger = Logger.getLogger(SuiteUtil.class);
	
	public static String getCurrentDateTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
		
	}
	
	
	public static String createResultsFolder() {
		String timeStamp = getCurrentDateTime().replaceAll(":", "_");
		String _ResultsFolderPath = System.getProperty("user.dir")+"/Results/Run_"+timeStamp+"/";
		File dir = new File(_ResultsFolderPath);
		if(!dir.exists()) {
			new File(_ResultsFolderPath).mkdirs();
		}
		resultsPath= _ResultsFolderPath;
		String screenshotsFolder = SuiteUtil.getCurrentResultsPath()+"/Screenshots/";
		File dir2 = new File(screenshotsFolder);
		if(!dir2.exists()) {
			new File(screenshotsFolder).mkdirs();
		}
		String HTMLReports = SuiteUtil.getCurrentResultsPath()+"/HTML Reports/";
		File dir3 = new File(HTMLReports);
		if(!dir3.exists()) {
			new File(HTMLReports).mkdirs();
		}
		
		
		return _ResultsFolderPath;
	}

	public static String getCurrentResultsPath() {
		return resultsPath;
	}
	
	public static String getHTMLReportsPath(){
		return resultsPath+"/HTML Reports";
	}
	
	public static String getTimeDifference(String startTime, String endTime) {
		///String dateStart = "01/14/2012 09:29:58";
		//String dateStop = "01/15/2012 10:31:48";
		String timeDiff = "" ;
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(startTime);
			d2 = format.parse(endTime);

			DateTime dt1 = new DateTime(d1);
			DateTime dt2 = new DateTime(d2);

			timeDiff += Hours.hoursBetween(dt1, dt2).getHours() % 24 + " hours ";
			timeDiff += Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " minutes ";
			timeDiff += Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " seconds.";

		 } catch (Exception e) {
			e.printStackTrace();
		 }
		
		return timeDiff;

	  }
	
	public static void launchResults()  {
		String launchStatus = properties.getLaunchReport();
		String executionMode = properties.getExecutionMode();
		if((launchStatus.equalsIgnoreCase("True") || launchStatus.equalsIgnoreCase("Yes"))
			&& executionMode.equalsIgnoreCase("Local")) {
				File htmlFile = new File(resultsPath+"Summary Report.html");
				try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e) {
					System.err.println("Error in launching Summary HTML Report.....! ");
				}
		}
		else {
			logger.info("Html Reports Generation is Disabled.");
		}
		
	}
	public static void launchLog()  {
		
			File htmlFile = new File("./Results/ErrorLog.txt");
			try {
				Desktop.getDesktop().edit(htmlFile);
			} catch (IOException e) {
				System.err.println("Error in launching Summary HTML Report.....! ");
			}
		
		
	}
	
	/**
	 * Function to get the separator string to be used for directories and files based on the current OS
	 * @return The file separator string
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}
	
	public static String getSystemUser(){
		return System.getProperty("user.name");
	}
	
	public static String getOSName(){
		return System.getProperty("os.name");
	}
	public static String getOSVersion(){
		return System.getProperty("os.version");
	}
	
	public static String getWorkSpaceDirectory(){
		return System.getProperty("user.dir");
	}
	
	public static String getUserHomeDirectory(){
		return System.getProperty("user.home");
	}
	
	public static String getTextInRedColor(String Log) {
			return "<font color='#FF5733'><b>"+Log+"</b></font>";
	}
	public static String getTextInRedColor(int Log) {
		return "<font color='#FF5733'><b>"+Log+"</b></font>";
	}
	

}
