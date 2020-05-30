package com.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.testng.SkipException;

public class Excelutil {
	
	private String testManagerFile = "./TestManager.xls";
	private HSSFRow row=null;
	private HSSFCell cell=null;
	private TestUtil testutil;
	private Report report;
	public Excelutil(TestUtil testutil) {
		this.testutil = testutil;
		this.report = testutil.getReport();
	}
	
	public Excelutil() {
		
	}
	static Logger logger = Logger.getLogger(Excelutil.class);
	
	public Hashtable<String, String> getData(String sheetName){
		Hashtable<String, String> testData = new Hashtable<String, String>();
		String testCaseName = testutil.getTestcaseID();
		int iteration = testutil.getCurrentIteration();
		String	path = "./TestData/"+testutil.getScenario()+".xls";
		testData =  getCurrentTestData(path, sheetName, testCaseName,iteration);
		return testData;
	}
	public String getData(String sheetName, String columnName){
		Hashtable<String, String> testdata = getData(sheetName);
		if(!testdata.containsKey(columnName)){
			report.log("Exception", "'"+columnName+"'Column is not available in '"+sheetName+"' sheet", Status.FAIL);
			logger.error("'"+columnName+"'Column is not available in '"+sheetName+"' sheet");
			throw new SkipException("'"+columnName+"'Column is not available in '"+sheetName+"' sheet");
		}
		return testdata.get(columnName);
	}
	
	
	 Hashtable<String, String> getCurrentTestData(String path, String sheetName, String testCaseName, int iteration){
			Hashtable<String, String> testData = new Hashtable<String, String>();
			HSSFWorkbook workbook = getWorkBook(path);
			if(workbook==null){
				return null;
			}
			HSSFSheet sheet = workbook.getSheet(sheetName);
			if(sheet==null) {
				throw new FrameworkException("'"+sheetName+"' Excel Sheet Not Available In "+path+" Excel File ");
			}
			int lastColumns = sheet.getRow(0).getLastCellNum();
			int lastRow = sheet.getLastRowNum();
			String columnName="";
			String columnData="";
			int testcaseidColNum = getSheetColumnIndex(path, sheetName,"TCID");
			int itrCol = getSheetColumnIndex(path, sheetName,"Run(s)");
			boolean testCaseFoundStatus = false;
			for (int i = 0; i <= lastRow; i++) {
				row = sheet.getRow(i);
				if(row!=null) {
					String temptc = getCellValueAsString(row.getCell(testcaseidColNum));
					String tempItr = getCellValueAsString(row.getCell(itrCol));
					if(testCaseName.equals(temptc) && tempItr.equals(String.valueOf(iteration))) {
						testCaseFoundStatus = true;
						for (int j = 0; j <= lastColumns; j++) {
							cell = row.getCell(j);
							if(cell!=null) {
								columnName = getCellValueAsString(sheet.getRow(0).getCell(j));
								columnData = getCellValueAsString(row.getCell(j));
								testData.put(columnName, columnData);
							}
						}
					}
				}
				if(testCaseFoundStatus) {
					break;
				}
			}

return testData;
}
	
	
	public Hashtable<String, String> readTestManager(String sheetName, String testcaseID) {
		Hashtable<String, String> data = new Hashtable<String, String>();
		HSSFWorkbook workbook = getWorkBook(testManagerFile);
		HSSFSheet sheet = workbook.getSheet(sheetName);
		if(sheet==null) {
			
			logger.warn("'"+sheetName+"' Excel Sheet Not Available In TestManager Excel File ");
			logger.info("Make Sure TestManager Excel 'Sheet Name' and 'SuiteName' in Config Propertie Both Should Match.");
			logger.info("Execution Terminated..........!");
			SuiteUtil.launchLog();
			System.exit(0);
		}
		int lastRow = sheet.getLastRowNum();
		int lastcell = sheet.getRow(0).getLastCellNum();
		boolean status=false;
		int emptyRows=0;
		int testcaseCol = getSheetColumnIndex(testManagerFile, sheetName,"TCID");
		for (int i = 0; i <= lastRow; i++) {
			row = sheet.getRow(i);
			if(row!=null) {
				String temp = getCellValueAsString(row.getCell(testcaseCol));
				if(temp.equals(""))
					emptyRows++;
				// comparing testcase name
				if(temp.equals(testcaseID)) {
					status = true;
					for (int j = 0; j <= lastcell; j++) {
						cell = row.getCell(j);
						if(cell!=null) {
							String cellData = getCellValueAsString(cell);
							String colHeading = getCellValueAsString(sheet.getRow(0).getCell(j));
							data.put(colHeading, cellData);
						}
						
					}
				}
				
				if(status || emptyRows>6) {
					break; // exiting from rows searching after found test case
				}
			}
			
		}
		
		if(status)
		  return data;
		else {
			
			throw new FrameworkException(testcaseID+ " is not found in TestManager Excel file........!");
			
		}
		
		
	}
	private int getSheetColumnIndex(String path, String sheetname, String columnName){
		Hashtable<String, Integer> columns = getSheetColumnIndex(path, sheetname);
		if(columns.containsKey(columnName)) {
			return columns.get(columnName);
		}else {
			throw new FrameworkException("Exception", "'"+columnName+"' is not available in '"+sheetname+"' excel file");
		}
	}
	
	private Hashtable<String, Integer> getSheetColumnIndex(String path, String sheetname){
		Hashtable<String, Integer> columns = new Hashtable<String, Integer>();
		HSSFWorkbook workbook = getWorkBook(path);
		HSSFSheet sheet = workbook.getSheet(sheetname);
		int lastColumns = sheet.getRow(0).getLastCellNum();
		String colName= "";
		for (int i = 0; i <= lastColumns; i++) {
			
			cell = sheet.getRow(0).getCell(i);
			if(cell !=null) {
				colName = getCellValueAsString(cell);
				columns.put(colName, i);
			}
		}
		
		return columns;
	}
	
	
	
	private HSSFWorkbook getWorkBook(String filepath) {
		FileInputStream fis = null;
		HSSFWorkbook workbook = null;
		try {
			fis = new FileInputStream(filepath);
			workbook = new HSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
			report.log("ERROR", e.getMessage(), Status.FAIL);
			System.err.println("invalid File path: "+filepath);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error while reading workbook from file: "+filepath);
			report.log("ERROR", e.getMessage(), Status.FAIL);
			
		}
		
		if(workbook!=null) {
				return workbook;
		}
		else {
			throw new SkipException("Error while reading workbook from file: "+filepath); 
		}
		
	}
	
	private String getCellValueAsString(HSSFCell cell) {

		DataFormatter format = new DataFormatter();
		return format.formatCellValue(cell).trim();

	}
	
	@SuppressWarnings("unused")
	private String getCellValue(HSSFCell cell) {
		HSSFWorkbook workbook = cell.getSheet().getWorkbook();
		if (cell == null)
			return "";
		
		switch (cell.getCellTypeEnum()) {

			case STRING  :  return cell.getStringCellValue();
			case NUMERIC :  return String.valueOf(cell.getNumericCellValue());
			case FORMULA :
				FormulaEvaluator formulaEvaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();
							DataFormatter dataFormatter = new DataFormatter();
							return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell(cell));
			case  BLANK:	return "";
			case ERROR   :  throw new FrameworkException("Error in formula within this cell! " + "Error code: "+ cell.getErrorCellValue());
			case BOOLEAN :  return String.valueOf(cell.getBooleanCellValue());
				
			default:        throw new FrameworkException("Error in this cell! " + "Error code: "+ cell.getErrorCellValue());
		}
	}
	
	ArrayList<String> getActionKeywords(String scenario,String currentTestCase, int currentIteration) {
		String filepath = "./TestData/"+scenario+".xls";
		HSSFWorkbook workbook = getWorkBook(filepath);
		HSSFSheet sheet =  workbook.getSheet("Keywords");
			String testCaseName="";
			String testIteration = "";
			int lastRow = sheet.getLastRowNum();
			ArrayList<String> keywordContainer = new ArrayList<>();
			for (int i = 1; i <= lastRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(0);
					if (cell != null && row.getCell(1) != null) {
						testCaseName = cell.getStringCellValue();
						testIteration = getCellValueAsString(row.getCell(1));
						if (currentTestCase.equals(testCaseName) && testIteration.equals(String.valueOf(currentIteration))) {
							for (int j = 4; j < row.getLastCellNum(); j++) {
								String runMode = row.getCell(2).getStringCellValue();
								if (runMode != null & runMode.equalsIgnoreCase("yes") & !runMode.isEmpty()) {
									try {
										// Checking if Cell contains a blank Value
										if (!row.getCell(j).getStringCellValue().isEmpty()) {
											keywordContainer.add(row.getCell(j).getStringCellValue());
										}else {
											break;
										}
									} catch (Exception e) {
										break;
									}

								} else {
									report.log("Error"," Test case is Skipped Because RunMode is not defined as Yes in ActionKeywords ExcelSheet.", Status.ERROR);
									throw new SkipException(currentTestCase
											+ " Test case is Skipped because RunMode is not defined as Yes in ActionKeywords ExcelSheet in "+scenario+" Excel Workbook");
								}
							}
							break;
						}
					}
				}
			}
//			if (keywordContainer.isEmpty()) {
//				throw new FrameworkException("Actions Keywords not specified in ActionKeywords Sheet.");
//			}
			return keywordContainer;
		}
	
	
	public void putData(String sheetName, String columnName, String data){
			String testCaseName = testutil.getTestcaseID();
			int iteration = testutil.getCurrentIteration();
			String path = "./TestData/"+testutil.getScenario()+".xls";
			row = getRow(path, sheetName, testCaseName,iteration);
			int columnNum = getSheetColumnIndex(path, sheetName,columnName);
			cell = row.getCell(columnNum);
			if(cell!=null) {
				row.getCell(columnNum).setCellValue(data);
			}else {
					row.createCell(columnNum).setCellValue(data);
				}
			HSSFWorkbook workbook = row.getSheet().getWorkbook();
			saveWorkBook(path, workbook);
	
}
	
private HSSFRow getRow(String path, String sheetName, String testcaseName, int iteration) {
	HSSFRow row = null;
	
	HSSFWorkbook workbook = getWorkBook(path);
	HSSFSheet sheet = workbook.getSheet(sheetName);
	if(sheet==null) {
		report.log("Exception", sheetName+" excel sheet not available in "+path+"excel file.....", Status.FAIL);
	}
	int lastRow = sheet.getLastRowNum();
	int testcaseidColNum = getSheetColumnIndex(path, sheetName).get("TCID");
	int itrCol = getSheetColumnIndex(path, sheetName).get("Run(s)");
	boolean testCaseFoundStatus = false;
	for (int i = 0; i <= lastRow; i++) {
		row = sheet.getRow(i);
		if(row!=null) {
			String temptc = getCellValueAsString(row.getCell(testcaseidColNum));
			String tempItr = getCellValueAsString(row.getCell(itrCol));
			if(testcaseName.equals(temptc) && tempItr.equals(String.valueOf(iteration))) {
				testCaseFoundStatus = true;
				break;
			}
		}
	}
	
	if(testCaseFoundStatus) {
		return row;
	}else{
		report.log("Exception", testcaseName+" with Iteration: "+iteration+" is not found in "+sheetName+" excel sheet", Status.FAIL);
		throw new FrameworkException(testcaseName+" with Iteration: "+iteration+" is not found in "+sheetName+" excel sheet");
	}
}

	private void saveWorkBook(String path, HSSFWorkbook workbook) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String getSettingsMode() {
		HSSFWorkbook workbook = getWorkBook(testManagerFile);
		HSSFSheet sheet = workbook.getSheet("FrameworkSettings");
		if(sheet==null) {
			return null;
		}
		
		return sheet.getRow(1).getCell(1).getStringCellValue();
	}
	
	

}
