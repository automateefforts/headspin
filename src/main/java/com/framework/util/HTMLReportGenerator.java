package com.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class HTMLReportGenerator{

	public HTMLReportGenerator() {
		
	}	
	
	private void createSummaryHtmlTitle(String titile) {
//		String icon = SuiteUtil.RESOURCE_PATH+"/Uploads/polaris.ico";
		String icon = "/Uploads/polaris.ico";
		//define a HTML String Builder
        StringBuilder html=new StringBuilder();
        //append html header and title
        html.append("<html> \r\n" + 
        		"	<head>\r\n" + 
        		"		<title>"+titile+"</title>\r\n" + 
        		"		<meta charset='UTF-8'>\r\n" + 
        		"		<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>\r\n" + 
        		"		<link rel='shortcut icon' href='"+icon+"'>\r\n" + 
        		"		<style>\r\n" + 
        		"			 * {\r\n" + 
        		"				margin: 0px; \r\n" + 
        		"				padding: 0px; \r\n" + 
        		"				box-sizing: border-box;\r\n" + 
        		"			}\r\n" + 
        		"			body, html {\r\n" + 
        		"				height: 100%;\r\n" + 
        		"				font-family: sans-serif;\r\n" + 
        		"			}\r\n" + 
        		"			.limiter {\r\n" + 
        		"			  width: 100%;\r\n" + 
        		"			  margin: 0 auto;\r\n" + 
        		"			}\r\n" + 
        		"			.container-headTable {\r\n" + 
        		"			  width: 100%;\r\n" + 
        		"			  min-height: 0vh;\r\n" + 
        		"			  background: #c850c0;\r\n" + 
        		"			  background: -webkit-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: -o-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: -moz-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  display: -webkit-box;\r\n" + 
        		"			  display: -webkit-flex;\r\n" + 
        		"			  display: -moz-box;\r\n" + 
        		"			  display: -ms-flexbox;\r\n" + 
        		"			  display: flex;\r\n" + 
        		"			  align-items: bottom;\r\n" + 
        		"			  justify-content: center;\r\n" + 
        		"			  flex-wrap: wrap;\r\n" + 
        		"			  padding: 20px 0px;\r\n" + 
        		"			}\r\n" + 
        		"			.wrap-headTable {\r\n" + 
        		"			  width: 800px;\r\n" + 
        		"			}\r\n" + 
        		"			.container-headerTable {\r\n" + 
        		"			  width: 100%;\r\n" + 
        		"			  min-height: 0vh;\r\n" + 
        		"			  background: #c850c0;\r\n" + 
        		"			  background: -webkit-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: -o-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: -moz-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  display: -webkit-box;\r\n" + 
        		"			  display: -webkit-flex;\r\n" + 
        		"			  display: -moz-box;\r\n" + 
        		"			  display: -ms-flexbox;\r\n" + 
        		"			  display: flex;\r\n" + 
        		"			  align-items: top;\r\n" + 
        		"			  justify-content: center;\r\n" + 
        		"			  flex-wrap: wrap;\r\n" + 
        		"			  padding: 10px 10px;\r\n" + 
        		"			}\r\n" + 
        		"			.wrap-headerTable {\r\n" + 
        		"			  width: 1200px;\r\n" + 
        		"			}\r\n" + 
        		"			.container-footerTable {\r\n" + 
        		"			  width: 100%;\r\n" + 
        		"			  min-height: 100vh;\r\n" + 
        		"			  background: #c850c0;\r\n" + 
        		"			  background: -webkit-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: -o-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: -moz-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  background: linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
        		"			  display: -webkit-box;\r\n" + 
        		"			  display: -webkit-flex;\r\n" + 
        		"			  display: -moz-box;\r\n" + 
        		"			  display: -ms-flexbox;\r\n" + 
        		"			  display: flex;\r\n" + 
        		"			  align-items: top;\r\n" + 
        		"			  justify-content: center;\r\n" + 
        		"			  flex-wrap: wrap;\r\n" + 
        		"			  padding: 15px 10px;\r\n" + 
        		"			}\r\n" + 
        		"			.wrap-footerTable {\r\n" + 
        		"			  width: 1200px;\r\n" + 
        		"			}\r\n" + 
        		"			table {\r\n" + 
        		"			  border-spacing: 1;\r\n" + 
        		"			  border-collapse: collapse;\r\n" + 
        		"			  background: white;\r\n" + 
        		"			  border-radius: 10px;\r\n" + 
        		"			  overflow: hidden;\r\n" + 
        		"			  width: 100%;\r\n" + 
        		"			  margin: 0 auto;\r\n" + 
        		"			  position: relative;\r\n" + 
        		"			}\r\n" + 
        		"			table * {\r\n" + 
        		"			  position: relative;\r\n" + 
        		"			}\r\n" + 
        		"			table td, table th {\r\n" + 
        		"			  padding-left: 15px;\r\n" + 
        		"			  padding-right: 15px;\r\n" + 
        		"			}\r\n" + 
        		"			/* table head row background colour */\r\n" + 
        		"			table thead tr {\r\n" + 
        		"			  height: 35px;\r\n" + 
        		"			  background: #36304a; \r\n" + 
        		"			}\r\n" + 
        		"			.footer_total,\r\n" + 
        		"			.footer_skip,\r\n" + 
        		"			.footer_fail,\r\n" + 
        		"			.footer_pass\r\n" + 
        		"			{\r\n" + 
        		"			  height: 27px;\r\n" + 
        		"			  background: #36304a; \r\n" + 
        		"			}\r\n" + 
        		"			.fr1\r\n" + 
        		"			{\r\n" + 
        		"				height: 10px;\r\n" + 
        		"				background: white;\r\n" + 
        		"				\r\n" + 
        		"			}\r\n" + 
        		"			\r\n" + 
        		"			table tbody tr {\r\n" + 
        		"			  height: 20px;\r\n" + 
        		"			}\r\n" + 
        		"			table td, table th {\r\n" + 
        		"			  text-align: left;\r\n" + 
        		"			}\r\n" + 
        		"			/* content headers */\r\n" + 
        		"			.headerTable_tr th{\r\n" + 
        		"			  font-family: calibri;\r\n" + 
        		"			  font-size: 18px;\r\n" + 
        		"			  color: #fff;\r\n" + 
        		"			  line-height: 1.2;\r\n" + 
        		"			  font-weight: unset;\r\n" + 
        		"			}\r\n" + 
        		"			.tr_content td.td_fstatus{\r\n" + 
        		"			  font-family: comic ms;\r\n" + 
        		"			  font-size: 18px;\r\n" + 
        		"			  color: #ff0016;\r\n" + 
        		"			  line-height: 1.2;\r\n" + 
        		"			  font-weight: bold;\r\n" + 
        		"			  text-align: left;\r\n" + 
        		"			  padding-right: 15px;\r\n" + 
        		"			}\r\n" + 
        		"			.tr_content td.td_pstatus{\r\n" + 
        		"			  font-family: comic ms;\r\n" + 
        		"			  font-size: 18px;\r\n" + 
        		"			  color: #1bdc30;\r\n" + 
        		"			  line-height: 1.2;\r\n" + 
        		"			  font-weight: bold;\r\n" + 
        		"			  text-align: left;\r\n" + 
        		"			  padding-right: 15px;\r\n" + 
        		"			}\r\n" + 
        		"			/* footer table */\r\n" + 
        		"			.footer_tr td.footer_total, td.footer_skip, td.footer_pass, td.footer_fail {\r\n" + 
        		"			  font-family: comic ms;\r\n" + 
        		"			  font-size: 18px;\r\n" + 
        		"			  color: white;\r\n" + 
        		"			  line-height: 1;\r\n" + 
        		"			  font-weight: unset;\r\n" + 
        		"			  text-align: center;\r\n" + 
        		"			}\r\n" + 
        		"			.footer_tr td.footer_skip{\r\n" + 
        		"			  color: #fdee22; /* yellow */\r\n" + 
        		"			}\r\n" + 
        		"			.footer_tr td.footer_pass{\r\n" + 
        		"			  color: #20ea1c; /* green */\r\n" + 
        		"			}\r\n" + 
        		"			.footer_tr td.footer_fail{\r\n" + 
        		"			  color: #f73131;  /* red */\r\n" + 
        		"			}\r\n" + 
        		"			td.fr1 {\r\n" + 
        		"			  font-family: auto;\r\n" + 
        		"			  font-size: 15px;\r\n" + 
        		"			  color: #086d1e;\r\n" + 
        		"			  line-height: 0;\r\n" + 
        		"			  font-weight: bold;\r\n" + 
        		"			  text-align: center;\r\n" + 
        		"			}\r\n" + 
        		"			/* Title header */	\r\n" + 
        		"			.tablehead1 th{\r\n" + 
        		"			  font-family: calibri;\r\n" + 
        		"			  font-size: 30px;\r\n" + 
        		"			  color: #fff;\r\n" + 
        		"			  line-height: 1;\r\n" + 
        		"			  font-weight: unset;\r\n" + 
        		"			  text-align: center;\r\n" + 
        		"			}\r\n" + 
        		"			/* Header table details	*/\r\n" + 
        		"			.tablehead2 th{\r\n" + 
        		"			  font-family: calibri;\r\n" + 
        		"			  font-size: 17px;\r\n" + 
        		"			  color: white;\r\n" + 
        		"			  line-height: 1;\r\n" + 
        		"			  font-weight: unset;\r\n" + 
        		"			  text-align: left;\r\n" + 
        		"			}\r\n" + 
        		"			tbody tr:nth-child(even) {\r\n" + 
        		"			  background-color: #f5f5f5;\r\n" + 
        		"			}\r\n" + 
        		"			/* content data */\r\n" + 
        		"			tbody tr {\r\n" + 
        		"			  font-family: Trebuchet MS;\r\n" + 
        		"			  font-size: 15px;\r\n" + 
        		"			  color: black;\r\n" + 
        		"			  line-height: 1.2;\r\n" + 
        		"			  font-weight: unset;\r\n" + 
        		"			}\r\n" + 
        		"			tbody tr.tr_content:hover {\r\n" + 
        		"			  color: Blue;\r\n" + 
        		"			  background-color: #f5f5f5;\r\n" + 
        		"			  cursor: pointer;\r\n" + 
        		"			}\r\n" + 
        		"			.hcolumn1 {\r\n" + 
        		"			  width: 30px;\r\n" + 
        		"			  padding-left: 20px;\r\n" + 
        		"			  text-align: center;\r\n" + 
        		"			}\r\n" + 
        		"			/* table content */\r\n" + 
        		"			.td_scenario,\r\n" + 
        		"			.td_TCID,\r\n" + 
        		"			.td_iteration,\r\n" + 
        		"			.td_desc,\r\n" + 
        		"			.td_timespan,\r\n" + 
        		"			.td_browser,\r\n" + 
        		"			.td_pstatus,\r\n" + 
        		"			.td_fstatus,\r\n" + 
        		"			.th_scenario,\r\n" + 
        		"			.th_iteration,\r\n" + 
        		"			.th_desc,\r\n" + 
        		"			.th_timespan,\r\n" + 
        		"			.th_browser,\r\n" + 
        		"			.th_status\r\n" + 
        		"			{\r\n" + 
        		"				padding-left: 15px;\r\n" + 
        		"				padding-right: 15px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_desc,\r\n" + 
        		"			.td_timespan,\r\n" + 
        		"			.th_timespan,\r\n" + 
        		"			.td_browser,\r\n" + 
        		"			.th_browser,\r\n" + 
        		"			.td_pstatus,\r\n" + 
        		"			.td_fstatus,\r\n" + 
        		"			.td_iteration,\r\n" + 
        		"			.th_iteration,\r\n" + 
        		"			.th_status{\r\n" + 
        		"				text-align: center;\r\n" + 
        		"			}\r\n" + 
        		"			.td_scenario,\r\n" + 
        		"			.th_scenario {\r\n" + 
        		"			  width: 150px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_TCID,\r\n" + 
        		"			.td_TCID\r\n" + 
        		"			{\r\n" + 
        		"			  width: 100px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_iteration,\r\n" + 
        		"			.td_iteration\r\n" + 
        		"			{\r\n" + 
        		"			  width: 110px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_desc,\r\n" + 
        		"			.td_desc \r\n" + 
        		"			{\r\n" + 
        		"			  width: 450px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_timespan,\r\n" + 
        		"			.td_timespan\r\n" + 
        		"			{\r\n" + 
        		"			  width: 150px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_browser,\r\n" + 
        		"			.td_browser\r\n" + 
        		"			{\r\n" + 
        		"			  width: 20px;\r\n" + 
        		"			}\r\n" + 
        		"			.th_status,\r\n" + 
        		"			.td_fstatus,\r\n" + 
        		"			.td_pstatus\r\n" + 
        		"			{\r\n" + 
        		"				 width: 30px;\r\n" + 
        		"			}\r\n" + 
        		"			.fr1\r\n" + 
        		"			{\r\n" + 
        		"				text-align:center;\r\n" + 
        		"			}\r\n" + 
        		"			\r\n" + 
        		"			@media screen and (max-width: 992px) {\r\n" + 
        		"			  table {\r\n" + 
        		"				display: block;\r\n" + 
        		"			  }\r\n" + 
        		"			  table > *, table tr, table td, table th {\r\n" + 
        		"				display: block;\r\n" + 
        		"			  }\r\n" + 
        		"			  table thead {\r\n" + 
        		"				display: none;\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr {\r\n" + 
        		"				height: auto;\r\n" + 
        		"				padding: 20px 0;\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr td {\r\n" + 
        		"				padding-left: 30% !important;\r\n" + 
        		"				margin-bottom: 10px;\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr td:last-child {\r\n" + 
        		"				margin-bottom: 0;\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr td:before {\r\n" + 
        		"				font-family: Trebuchet MS;\r\n" + 
        		"				font-size: 14px;\r\n" + 
        		"				color: #999999;\r\n" + 
        		"				line-height: 1.2;\r\n" + 
        		"				font-weight: unset;\r\n" + 
        		"				position: absolute;\r\n" + 
        		"				width: 40%;\r\n" + 
        		"				left: 30px;\r\n" + 
        		"				top: 0;\r\n" + 
        		"			  }\r\n" + 
        		"			  \r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(1):before {\r\n" + 
        		"				content: 'Test Scenario';\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(2):before {\r\n" + 
        		"				content: 'TC ID';\r\n" + 
        		"			  } \r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(3):before {\r\n" + 
        		"				content: 'Iteration';\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(4):before {\r\n" + 
        		"				content: 'Description';\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(5):before {\r\n" + 
        		"				content: 'Timestamp';\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(6):before {\r\n" + 
        		"				content: 'Browser';\r\n" + 
        		"			  }\r\n" + 
        		"			  table tbody tr.tr_content td:nth-child(7):before {\r\n" + 
        		"				content: 'Status';\r\n" + 
        		"				\r\n" + 
        		"			  }\r\n" + 
        		"			  .td_desc,	\r\n" + 
        		"			  .td_iteration,	\r\n" + 
        		"			  .td_timespan,\r\n" + 
        		"			  .td_browser,\r\n" + 
        		"			  .td_pstatus,\r\n" + 
        		"			  .td_fstatus{\r\n" + 
        		"				text-align: left;\r\n" + 
        		"			  }\r\n" + 
        		"			  .td_timespan,\r\n" + 
        		"			  .td_browser,\r\n" + 
        		"			  .td_scenario,\r\n" + 
        		"			  .td_TCID,\r\n" + 
        		"			  .td_iteration,\r\n" + 
        		"			  .td_desc {\r\n" + 
        		"				width: 100%;\r\n" + 
        		"			  }\r\n" + 
        		"			  tbody tr {\r\n" + 
        		"				font-size: 14px;\r\n" + 
        		"			  }\r\n" + 
        		"			}\r\n" + 
        		"			@media (max-width: 576px) {\r\n" + 
        		"			  .container-headerTable {\r\n" + 
        		"				padding-left: 15px;\r\n" + 
        		"				padding-right: 15px;\r\n" + 
        		"			  }\r\n" + 
        		"			}\r\n" + 
        		"		</style>\r\n" + 
        		"	</head>\r\n" + 
        		"<body>");   
        saveSummaryHTML(html);
	}
	
	public void createSummaryHtmlHeader(String projectName, String _Environment,
			String _SuiteType, String _OS, String _DateAndTime) {
//		PropertiesUtil properties = new PropertiesUtil();
		
		createSummaryHtmlTitle("Selenium Automation Results"); 
		StringBuilder html = new StringBuilder();
		html.append("<div class='limiter'>\r\n" + 
				"    <div class='container-headTable'>\r\n" + 
				"        <div class='wrap-headTable'>\r\n" + 
				"            <div class='headTable'>\r\n" + 
				"                <table>\r\n" + 
				"                    <thead>\r\n" + 
				"                        <tr class='tablehead1'>\r\n" + 
				"                            <th class='hcolumn1' colspan='4'>"+projectName+" - Automation Results</th>\r\n" + 
				"                        </tr>\r\n" + 
				"                        <tr class='tablehead2'>\r\n" + 
				"							<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test Suite Type</th>\r\n" + 
				"							 <th>:&nbsp;&nbsp;&nbsp;"+_SuiteType+"</th>\r\n" + 
				"							 <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Operting System</th>\r\n" + 
				"							<th>:&nbsp;&nbsp;&nbsp;"+_OS+"</th>  \r\n" + 
				"                        </tr>\r\n" + 
				"                        <tr class='tablehead2'>\r\n" + 
				"						<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test Environment</th>\r\n" + 
				"							<th>:&nbsp;&nbsp;&nbsp;"+_Environment+"</th>\r\n" + 
				"                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date & Time</th>\r\n" + 
				"                            <th>:&nbsp;&nbsp;&nbsp;"+_DateAndTime+"</th>\r\n" + 
				"                        </tr>" + 
				"                    </thead>\r\n" + 
				"                    <tbody>\r\n" + 
				"                </table>\r\n" + 
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </div>");
		saveSummaryHTML(html);
		createSummaryHtmlTableHeaders();
	}
	
	private void createSummaryHtmlTableHeaders() {
		StringBuilder html = new StringBuilder();
		html.append("<div class='container-headerTable'>\r\n" + 
				"    <div class='wrap-headerTable'>\r\n" + 
				"        <div class='headerTable'>\r\n" + 
				"            <table>\r\n" + 
				"                <thead>\r\n" + 
				"                    <tr class='headerTable_tr'>\r\n" + 
				"                        <th class='th_scenario'>Test Scenario</th>\r\n" + 
				"                        <th class='th_TCID'>Test Case</th>\r\n" + 
				"                        <th class='th_iteration'>Run(s)</th>\r\n" + 
				"                        <th class='th_desc'>Test Case Description</th>\r\n" + 
				"                        <th class='th_timespan'>Time Span</th>\r\n" + 
				"                        <th class='th_browser'>Browser</th>\r\n" + 
				"                        <th class='th_status'>Status</th>\r\n" + 
				"                    </tr>\r\n" + 
				"                </thead>\r\n" + 
				"                <tbody>");
		saveSummaryHTML(html);
		
	}
	
//	public void appendTestCaseToSummaryHtml(String Scenario,  String _TCID,int _Iteration,
//			String _Description, String _StartTime, String _Browser, String _Status, String link) {
		public void appendTestCaseToSummaryHtml(String Scenario,  String _TCID,
				String _Description, String _StartTime, String _Browser, String _Status, String link, int runNo) {
		String status = "";
		if(_Status.equalsIgnoreCase("Failed")) {
			status = "td_fstatus";
		}else if(_Status.equalsIgnoreCase("Passed")) {
			status = "td_pstatus";
		}else {
			status = "td_sstatus";
		}
		StringBuilder html = new StringBuilder();
		html.append("				 <tr class='tr_content'>\r\n" + 
				"                        <td class='td_scenario'>"+Scenario+"</td>\r\n" + 
				"                        <td class='td_TCID'>"+_TCID+"</td>\r\n" + 
//				"                        <td class='td_iteration'><a target='blank' href='"+link+"' >Iteration"+_Iteration+"</a></td>\r\n" + 
				"                        <td class='td_iteration'><a target='blank' href='"+link+"' >Run "+runNo+"</a></td>\r\n" + 
				"                        <td class='td_desc'>"+_Description+"</td>\r\n" + 
				"                        <td class='td_timespan'><small>"+_StartTime+"</small></td>\r\n" + 
				"                        <td class='td_browser'>"+_Browser+"</td>\r\n" + 
				"                        <td class='"+status+"'>"+_Status+"</td>\r\n" + 
				"                    </tr>");
		saveSummaryHTML(html);
	}
	
//	public  void createSummaryHtmlFooter(String duration, int _Total, int _skipped, int _Passed, int _Failed) {
		public  void createSummaryHtmlFooter(String duration, int _Total, int _Passed, int _Failed) {
		StringBuilder html = new StringBuilder();
		html.append("				</tbody>\r\n" + 
				"               </table>\r\n" + 
				"            </div>\r\n" + 
				"         </div>\r\n" + 
				"      </div>  \r\n" + 
				"	  <div class='container-footerTable'>\r\n" + 
				"         <div class='wrap-footerTable'>\r\n" + 
				"			<div class='div_footer'>\r\n" + 
				"			<table>\r\n" + 
				"				<tbody>\r\n" + 
				"					<tr>\r\n" + 
				"						<td class='fr1' colspan='4' > Total Duration: "+duration+"</td>\r\n" + 
				"					</tr>\r\n" + 
				"					<tr class='footer_tr'>\r\n" + 
				"					  <td class='footer_total'>Total : "+_Total+"</th>\r\n" + 
//				"					  <td class='footer_skip'>Skipped : "+_skipped+"</th>\r\n" + 
				"					  <td class='footer_skip'></th>\r\n" + 
				"					  <td class='footer_pass'>Passed : "+_Passed+"</th>\r\n" + 
				"					  <td class='footer_fail'>Failed : "+_Failed+"</th>\r\n" + 
				"                     </tr>\r\n" + 
				"				</tbody>\r\n" + 
				"			</table>\r\n" + 
				"			</div>\r\n" + 
				"        </div>\r\n" + 
				"      </div> \r\n" + 
				"	</body>\r\n" + 
				"</html>");
		saveSummaryHTML(html);
	}
	
	private void saveSummaryHTML(StringBuilder content) {
		String filePath = SuiteUtil.getCurrentResultsPath()+"Summary Report.html";
		writeToFile(content, filePath);
		
	}
	
	private String saveTestLogHTML(StringBuilder content,String filePath) {
		//String testcase = testCaseName; //SuiteUtil.currentTestCase;
//		String browser = SuiteUtil.currentBrowser;
//		int iteration = 1;
//		String filePath = SuiteUtil.getCurrentResultsPath()+"HTML Reports/"+testCaseName+"_"+browser+"_iteration"+iteration+".html";
		new File(SuiteUtil.getCurrentResultsPath()+"/HTML Reports/").mkdirs();
		writeToFile(content, filePath);
		return filePath;
		
	}
	    private void writeToFile(StringBuilder content, String filePath){
	    	StringBuilder oldHTMLContent = new StringBuilder();
//	    	String filePath = SuiteUtil.getCurrentRunPath()+"/Summary Report.html";
	        File file = new File(filePath);
	        // if file does exists, then delete and create a new file
	        if (!file.exists()) {
	            try {
//	                File newFileName = new File(projectPath + File.separator+ "backup_"+fileName);
//	                file.renameTo(newFileName);
	                file.createNewFile();
	                oldHTMLContent = content;
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }else {
	    		try {
	    			BufferedReader in = new BufferedReader(new FileReader(filePath));
	    			String str;
	    			while ((str = in.readLine()) != null) {
	    				oldHTMLContent.append(str+"\n");
	    			}
	    			in.close();
	    		} catch (IOException e) {
	    		}
	    		oldHTMLContent.append(content.toString());
	        }
	        //write to file with OutputStreamWriter
	        OutputStream outputStream;
			try {
				outputStream = new FileOutputStream(file.getAbsoluteFile());
				Writer writer=new OutputStreamWriter(outputStream);
				writer.write(oldHTMLContent.toString());
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	    }
	    public void createTestLogHeader(String projectName, String scenario, String testcase, int iteration,
	    		String Browser,String dateAndTime, String executeMode, String filePath) {
	    	createTestLogTitle(testcase, Browser, iteration, filePath);
	    	StringBuilder content = new StringBuilder();
	    	content.append("<div class='limiter'>\r\n" + 
	    			"    <div class='container-headTable'>\r\n" + 
	    			"        <div class='wrap-headTable'>\r\n" + 
	    			"            <div class='headTable'>\r\n" + 
	    			"                <table>\r\n" + 
	    			"                    <thead>\r\n" + 
	    			"                        <tr class='tablehead1'>\r\n" + 
	    			"                            <th class='hcolumn1' colspan='4'>"+projectName+"_"+scenario+"_"+testcase+" - Automation Results</th>\r\n" + 
	    			"                        </tr>\r\n" + 
	    			"						<tr class='tablehead2'>\r\n" + 
	    			"                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Browser</th>\r\n" + 
	    			"                            <th>"+Browser+"</th>\r\n" + 
	    			"                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Iteration</th>\r\n" + 
	    			"                            <th>"+iteration+"</th>\r\n" + 
	    			"                        </tr>\r\n" + 
	    			"                        <tr class='tablehead2'>\r\n" + 
	    			"                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date & Time</th>\r\n" + 
	    			"                            <th>"+dateAndTime+"</th>\r\n" + 
	    			"                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Executed On</th>\r\n" + 
	    			"                            <th>"+executeMode+"</th>\r\n" + 
	    			"                        </tr>  \r\n" + 
	    			"                    </thead>\r\n" + 
	    			"                    <tbody>\r\n" + 
	    			"                </table>\r\n" + 
	    			"            </div>\r\n" + 
	    			"        </div>\r\n" + 
	    			"    </div>\r\n" + 
	    			"<div class='container-headerTable'>\r\n" + 
	    			"    <div class='wrap-headerTable'>\r\n" + 
	    			"        <div class='headerTable'>\r\n" + 
	    			"            <table class='contentTable'>\r\n" + 
	    			"				<col width='60px' />\r\n" + 
	    			"				<col width='380px' />\r\n" + 
	    			"				<col width='380px' />\r\n" + 
	    			"				<col width='85px' />\r\n" + 
	    			"				<col width='100px' />\r\n" + 
	    			"				<col width='200px' />\r\n" + 
	    			"                <thead>" + 
	    			"                    <tr class='headerTable_tr'>\r\n" + 
	    			"                        <th class='th_stepno'>S.No</th>\r\n" + 
	    			" <th class='th_step'>Test Step&nbsp;&nbsp;\r\n" + 
	    			"						<label class='switch'>\r\n" + 
	    			"								<input onclick='expandKeywords()' type='checkbox' checked>\r\n" + 
	    			"								<span class='slider round'></span>\r\n" + 
	    			"							</label>\r\n" + 
	    			"						</th>\r\n"+
	    			"                        <th class='th_desc'>Test Step Description</th>\r\n" + 
	    			"                        <th class='th_time'>Time</th>\r\n" + 
	    			"						 <th class='th_status'>Status</th>\r\n" + 
	    			"                        <th class='th_screenshot' >Screenshot&nbsp;\r\n" + 
	    			"							<label class='switch'>\r\n" + 
	    			"								<input onclick='imgToggle()' type='checkbox' checked>\r\n" + 
	    			"								<span class='slider round'></span>\r\n" + 
	    			"							</label>\r\n" + 
	    			"						</th>	\r\n" + 
	    			"                    </tr>\r\n" + 
	    			"                </thead>\r\n");
	    	saveTestLogHTML(content, filePath);
	    }
	    
	    public void createTestLogKeyword(String keyword, int iteration, String filePath) {
	    	StringBuilder content = new StringBuilder();
	    	content.append("</tbody><tbody style='display:table-row-group'>"
	    			+ "				<tr>\r\n" + 
//	    			"					<td></td>"+			
	    			"					<td class='keywordlnk' name = 'keywordlink' colspan='2' onclick='toggleMenu(\""+keyword+iteration+"\")' > -- Keyword: "+keyword+" </td>\r\n" +
	    			"					<td class='keywordlnk'colspan='4'></td>"+
	    			"				</tr>");
	    	saveTestLogHTML(content, filePath);
	    	System.out.println();
	    }
	    
	    public void appendTestLog(String keyword,int iteration, int sno, String teststep, String stepDescription, 
	    		String timestamp, String status, String screenshotPath,String filePath) {
	    	String strSno = String.valueOf(sno);
	    	StringBuilder content = new StringBuilder();
	    	String _status = "";
			if(status.equalsIgnoreCase("FAIL") || status.equalsIgnoreCase("Error")) {
				_status = "td_fstatus";
				teststep = getTextInRedColor(teststep);
				stepDescription = getTextInRedColor(stepDescription);
				timestamp = getTextInRedColor(timestamp);
				strSno = getTextInRedColor(strSno);
				 
			}else if(status.equalsIgnoreCase("PASS")) {
				_status = "td_pstatus";
			}else
				_status = "td_Ostatus";
	    	
	    	if(!screenshotPath.equalsIgnoreCase("None")) {
	    		String relativeSSPath = "../"+screenshotPath.substring(screenshotPath.indexOf("Screenshots"));
	    		
	    		
	    		content.append(
	    				"<tr class='tr_content' name='"+keyword+iteration+"'>\r\n" + 
	    				"   <td class='td_stepno'>"+strSno+"</td>\r\n" + 
	    				"   <td class='td_step'>"+teststep+"</td>\r\n" + 
	    				"   <td class='td_desc'>"+stepDescription+"</td>\r\n" + 
	    				"   <td class='td_time'><small>"+timestamp+"</small></td>\r\n" + 
	    				"   <td class='"+_status+"'>"+status+"</td>\r\n" + 
	    				"   <td class='td_screenshot'>\r\n" + 
	    				"      <div class='screenshot'>\r\n" + 
	    				"      <figure class='swing'>\r\n" + 
	    				"         <img  onclick='imgopen(\""+relativeSSPath+"\")' id='"+relativeSSPath+"' src='"+relativeSSPath+"' name='screenshot' alt='"+relativeSSPath+"' style='width:100%;max-width:300px'>\r\n" + 
	    				"      </figure>\r\n" + 
	    				"      <!-- The Modal -->\r\n" + 
	    				"      <div  id='modal"+relativeSSPath+"' class='modal'>\r\n" + 
	    				"         <img class='modal-content' id='"+relativeSSPath+"' src='"+relativeSSPath+"'>\r\n" + 
	    				"         <span  class='close'>x</span>\r\n" + 
	    				"         <div id='caption'>"+relativeSSPath+"</div>\r\n" + 
	    				"      </div>\r\n" + 
	    				"   </td>\r\n" + 
	    				"</tr>\r\n" + 
	    				"\r\n" + 
	    				"");
	    	}
	    	else {
	    		content.append("<tr class='tr_content' name='"+keyword+iteration+"'>\r\n" + 
		    			"                        <td class='td_stepno'>"+strSno+"</td>\r\n" + 
		    			"                        <td class='td_step'>"+teststep+"</td>\r\n" + 
		    			"                        <td class='td_desc'>"+stepDescription+"</td>\r\n" + 
		    			"                        <td class='td_time'><small>"+timestamp+"</small></td>\r\n" + 
		    			"                        <td class='"+_status+"'>"+status+"</td>\r\n" + 
		    			"                        <td class='td_screenshot'><strong><font color='#FF5733'>None</font></strong></td>\r\n" + 
		    			"                    </tr>");
	    	}
	    	saveTestLogHTML(content, filePath);
	    
	    }
	    
	    public String createTestLogFooter(String totalDurtion, int stepsPassed, int stepsFailed, 
	    		 String filePath) {
	    	StringBuilder content = new StringBuilder();
	    	content.append("</tbody>\r\n" + 
	    			"               </table>\r\n" + 
	    			"            </div>\r\n" + 
	    			"         </div>\r\n" + 
	    			"      </div>  \r\n" + 
	    			"	  <div class='container-footerTable'>\r\n" + 
	    			"         <div class='wrap-footerTable'>\r\n" + 
	    			"			<div class='div_footer'>\r\n" + 
	    			"			<table>\r\n" + 
	    			"				<tbody>\r\n" + 
	    			"					<tr>\r\n" + 
	    			"						<td class='fr1' colspan='2' > Total Duration:&nbsp;&nbsp;"+totalDurtion+"</td>\r\n" + 
	    			"					</tr>\r\n" + 
	    			"					<tr class='footer_tr'>\r\n" + 
	    			"					  <td class='footer_pass'>Steps Passed&nbsp;&nbsp;:&nbsp;&nbsp;"+stepsPassed+"</th>\r\n" + 
	    			"					  <td class='footer_fail'>Steps Failed&nbsp;&nbsp;:&nbsp;&nbsp;"+stepsFailed+"</th>\r\n" + 
	    			"                     </tr>\r\n" + 
	    			"				</tbody>\r\n" + 
	    			"			</table>\r\n" + 
	    			"			</div>\r\n" + 
	    			"        </div>\r\n" + 
	    			"      </div> \r\n" + 
	    			"	<button onclick='topFunction()' id='scruolUpBtn' title='Go to top'>Top</button>\r\n" + 
	    			"	</body>\r\n" + 
	    			"</html>");
	    	return saveTestLogHTML(content, filePath);
	    }
	    
	    private void createTestLogTitle(String testcase, String browser, int iteration, String filePath) {
	    	StringBuilder title = new StringBuilder();
//	    	String icon = SuiteUtil.RESOURCE_PATH+"/Uploads/polaris.ico";
	    	String icon = "/Uploads/polaris.ico";
	    	title.append("		<!DOCTYPE html> \r\n" + 
	    			"<html> \r\n" + 
	    			"	<head>\r\n" + 
	    			"		<title>"+testcase+" Automation Results</title>\r\n" + 
	    			"		<meta charset='UTF-8'>\r\n" + 
	    			"		<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'>\r\n" + 
	    			"		<link rel='shortcut icon' href='"+icon+"'>\r\n" + 
	    			"<style>/* The Modal (background) */\r\n" + 
	    			".modal {\r\n" + 
	    			"  display: none; /* Hidden by default */\r\n" + 
	    			"  position: fixed; /* Stay in place */\r\n" + 
	    			"  z-index: 1; /* Sit on top */\r\n" + 
	    			"  padding-top: 20px; /* Location of the box */\r\n" + 
	    			"  left: 0;\r\n" + 
	    			"  top: 0;\r\n" + 
	    			"  width: 100%; /* Full width */\r\n" + 
	    			"  height: 100%; /* Full height */\r\n" + 
	    			"  overflow: auto; /* Enable scroll if needed */\r\n" + 
	    			"  background-color: rgb(0,0,0); /* Fallback color */\r\n" + 
	    			"  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"/* Modal Content (image) */\r\n" + 
	    			".modal-content {\r\n" + 
	    			"  margin: auto;\r\n" + 
	    			"  display: block;\r\n" + 
	    			"  width: 90%;\r\n" + 
	    			"  height:90%;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"/* Caption of Modal Image */\r\n" + 
	    			"#caption {\r\n" + 
	    			"  margin: auto;\r\n" + 
	    			"  display: block;\r\n" + 
	    			"  width: 80%;\r\n" + 
	    			"  max-width: 700px;\r\n" + 
	    			"  text-align: center;\r\n" + 
	    			"  color: #ccc;\r\n" + 
	    			"  padding: 10px 0;\r\n" + 
	    			"  height: 150px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"/* Add Animation */\r\n" + 
	    			".modal-content, #caption {  \r\n" + 
	    			"  -webkit-animation-name: zoom;\r\n" + 
	    			"  -webkit-animation-duration: 0.6s;\r\n" + 
	    			"  animation-name: zoom;\r\n" + 
	    			"  animation-duration: 0.6s;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"@-webkit-keyframes zoom {\r\n" + 
	    			"  from {-webkit-transform:scale(0)} \r\n" + 
	    			"  to {-webkit-transform:scale(1)}\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"@keyframes zoom {\r\n" + 
	    			"  from {transform:scale(0)} \r\n" + 
	    			"  to {transform:scale(1)}\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"/* The Close Button */\r\n" + 
	    			".close {\r\n" + 
	    			"  position: absolute;\r\n" + 
	    			"    top: 0px;\r\n" + 
	    			"    right: 21px;\r\n" + 
	    			"    color: #f1f1f1;\r\n" + 
	    			"    font-size: 40px;\r\n" + 
	    			"    font-weight: bold;\r\n" + 
	    			"    transition: 0.3s;\r\n" + 
	    			"}\r\n" + 
	    			"#myModal:hover,\r\n" + 
	    			".close:hover,\r\n" + 
	    			".close:focus {\r\n" + 
	    			"  color: #bbb;\r\n" + 
	    			"  text-decoration: none;\r\n" + 
	    			"  cursor: pointer;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"img{\r\n" + 
	    			"  color: #bbb;\r\n" + 
	    			"  text-decoration: none;\r\n" + 
	    			"  cursor: Zoom-in;\r\n" + 
	    			"}\r\n" + 
	    			".modal-content:hover{\r\n" + 
	    			"	cursor: default;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"/* tbody img:hover {\r\n" + 
	    			"	color: red;\r\n" + 
	    			"	background-color: #f5f5f5;\r\n" + 
	    			"	cursor: pointer;\r\n" + 
	    			"} */\r\n" + 
	    			"\r\n" + 
	    			"/* 100% Image Width on Smaller Screens */\r\n" + 
	    			"@media only screen and (max-width: 700px){\r\n" + 
	    			"  .modal-content {\r\n" + 
	    			"    width: 100%;\r\n" + 
	    			"  }\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table th {\r\n" + 
	    			"	border: 1px solid #4158d0;\r\n" + 
	    			"	border-width: 0 0 1px 1px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table td {\r\n" + 
	    			"	border: 1px solid #4158d0;\r\n" + 
	    			"	border-width: 1px 0px 0 0;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* full page covering border  */\r\n" + 
	    			"\r\n" + 
	    			"* {\r\n" + 
	    			"	margin: 0px;\r\n" + 
	    			"	padding: 0px;\r\n" + 
	    			"	box-sizing: border-box;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* body, html {\r\n" + 
	    			"				height: 100%;\r\n" + 
	    			"				font-family: sans-serif;\r\n" + 
	    			"			} */\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* Screenshot related */\r\n" + 
	    			"\r\n" + 
	    			"img {\r\n" + 
	    			"	border: 1px solid white;\r\n" + 
	    			"	border-radius: 10px;\r\n" + 
	    			"	padding: 0px;\r\n" + 
	    			"	width: 175px;\r\n" + 
	    			"	height: 120px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"div.screenshot {\r\n" + 
	    			"	width: 100%;\r\n" + 
	    			"	background-color: white;\r\n" + 
	    			"	box-shadow: 0 20px 50px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);\r\n" + 
	    			"	margin-bottom: 10px;\r\n" + 
	    			"	margin-top: 10px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"body {background: #eee;}\r\n" + 
	    			".swing {\r\n" + 
	    			"    animation: swing ease-in-out 1s infinite alternate;\r\n" + 
	    			"    transform-origin: center -20px;\r\n" + 
	    			"    /* float:left; */\r\n" + 
	    			"    box-shadow: 5px 5px 10px rgba(0,0,0,0.5);\r\n" + 
	    			"}\r\n" + 
	    			".swing img {\r\n" + 
	    			"    border: 5px solid #f8f8f8;\r\n" + 
	    			"    display: block;\r\n" + 
	    			"}\r\n" + 
	    			".swing:after{\r\n" + 
	    			"    content: '';\r\n" + 
	    			"    position: absolute;  \r\n" + 
	    			"    width: 20px; height: 20px;  \r\n" + 
	    			"    border: 1px solid #999;\r\n" + 
	    			"    top: -10px; left: 50%;\r\n" + 
	    			"    z-index: 0;\r\n" + 
	    			"    border-bottom: none;\r\n" + 
	    			"    border-right: none;\r\n" + 
	    			"    transform: rotate(45deg);\r\n" + 
	    			"}\r\n" + 
	    			"/* nail */\r\n" + 
	    			".swing:before{\r\n" + 
	    			"    content: '';\r\n" + 
	    			"    position: absolute;\r\n" + 
	    			"    width: 5px; height: 5px;\r\n" + 
	    			"    top: -12px;left: 54%;\r\n" + 
	    			"    z-index: 5;\r\n" + 
	    			"    border-radius: 50% 50%;\r\n" + 
	    			"    background: #000;\r\n" + 
	    			"}\r\n" + 
	    			" \r\n" + 
	    			"@keyframes swing {\r\n" + 
	    			"    0% { transform: rotate(3deg); }\r\n" + 
	    			"    100% { transform: rotate(-3deg); }\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/*********************************/\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/*  Heading table */\r\n" + 
	    			"\r\n" + 
	    			".container-headTable {\r\n" + 
	    			"	width: 100%;\r\n" + 
	    			"	min-height: 0vh;\r\n" + 
	    			"	background: #c850c0;\r\n" + 
	    			"	background: -webkit-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: -o-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: -moz-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	display: -webkit-box;\r\n" + 
	    			"	display: -webkit-flex;\r\n" + 
	    			"	display: -moz-box;\r\n" + 
	    			"	display: -ms-flexbox;\r\n" + 
	    			"	display: flex;\r\n" + 
	    			"	align-items: bottom;\r\n" + 
	    			"	justify-content: center;\r\n" + 
	    			"	flex-wrap: wrap;\r\n" + 
	    			"	padding: 10px 0px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".wrap-headTable {\r\n" + 
	    			"	width: 800px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/*****************************/\r\n" + 
	    			"\r\n" + 
	    			".container-headerTable {\r\n" + 
	    			"	width: 100%;\r\n" + 
	    			"	min-height: 0vh;\r\n" + 
	    			"	background: #c850c0;\r\n" + 
	    			"	background: -webkit-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: -o-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: -moz-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	display: -webkit-box;\r\n" + 
	    			"	display: -webkit-flex;\r\n" + 
	    			"	display: -moz-box;\r\n" + 
	    			"	display: -ms-flexbox;\r\n" + 
	    			"	display: flex;\r\n" + 
	    			"	align-items: top;\r\n" + 
	    			"	justify-content: center;\r\n" + 
	    			"	flex-wrap: wrap;\r\n" + 
	    			"	padding: 0px 10px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".container-footerTable {\r\n" + 
	    			"	width: 100%;\r\n" + 
	    			"	min-height: 100vh;\r\n" + 
	    			"	background: #c850c0;\r\n" + 
	    			"	background: -webkit-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: -o-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: -moz-linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	background: linear-gradient(45deg, #4158d0, #c850c0);\r\n" + 
	    			"	display: -webkit-box;\r\n" + 
	    			"	display: -webkit-flex;\r\n" + 
	    			"	display: -moz-box;\r\n" + 
	    			"	display: -ms-flexbox;\r\n" + 
	    			"	display: flex;\r\n" + 
	    			"	align-items: top;\r\n" + 
	    			"	justify-content: center;\r\n" + 
	    			"	flex-wrap: wrap;\r\n" + 
	    			"	padding: 10px 10px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".wrap-footerTable {\r\n" + 
	    			"	width: 1100px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table {\r\n" + 
	    			"	border-spacing: 1;\r\n" + 
	    			"	border-collapse: collapse;\r\n" + 
	    			"	background: white;\r\n" + 
	    			"	border-radius: 10px;\r\n" + 
	    			"	overflow: hidden;\r\n" + 
	    			"	width: 100%;\r\n" + 
	    			"	margin: 0 auto;\r\n" + 
	    			"	position: relative;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table.contentTable {\r\n" + 
	    			"	width: 80%;\r\n" + 
	    			"	table-layout: fixed;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table.contentTable \r\n" + 
	    			"td.td_step,td.td_desc, td.td_time\r\n" + 
	    			" {\r\n" + 
	    			"	overflow: hidden;\r\n" + 
	    			"	text-overflow: ellipsis;\r\n" + 
	    			"	text-align:justify;\r\n" + 
	    			"}\r\n" + 
	    			"td.td_stepno{\r\n" + 
	    			"text-align:center;\r\n" + 
	    			"}\r\n" + 
	    			"table.footerTable {\r\n" + 
	    			"	width: 73%;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table td,\r\n" + 
	    			"table th {\r\n" + 
	    			"	padding-left: 15px;\r\n" + 
	    			"	padding-right: 10px;\r\n" + 
	    			"	padding-bottom: 3px;\r\n" + 
	    			"    padding-top: 3px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* table head row background colour */\r\n" + 
	    			"\r\n" + 
	    			"table thead tr {\r\n" + 
	    			"	height: 35px;\r\n" + 
	    			"	background: #36304a;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"table tbody tr {\r\n" + 
	    			"	height: 20px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* content headers */\r\n" + 
	    			"\r\n" + 
	    			".headerTable_tr th {\r\n" + 
	    			"	font-family: calibri;\r\n" + 
	    			"	font-size: 18px;\r\n" + 
	    			"	color: #fff;\r\n" + 
	    			"	line-height: 1.2;\r\n" + 
	    			"	font-weight: unset;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/******************************/\r\n" + 
	    			"\r\n" + 
	    			".tr_content td.td_fstatus {\r\n" + 
	    			"	font-family: comic ms;\r\n" + 
	    			"	font-size: 15px;\r\n" + 
	    			"	color: #ff0016;\r\n" + 
	    			"	line-height: 1.2;\r\n" + 
	    			"	font-weight: bold;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"	padding-right: 15px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".tr_content td.td_pstatus {\r\n" + 
	    			"	font-family: comic ms;\r\n" + 
	    			"	font-size: 15px;\r\n" + 
	    			"	color: #1bdc30;\r\n" + 
	    			"	line-height: 1.2;\r\n" + 
	    			"	font-weight: bold;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"	padding-right: 15px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".tr_content td.td_Ostatus {\r\n" + 
	    			"	font-family: comic ms;\r\n" + 
	    			"	font-size: 15px;\r\n" + 
	    			"	color: #6e62ca;\r\n" + 
	    			"	line-height: 1.2;\r\n" + 
	    			"	font-weight: bold;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"	padding-right: 15px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* footer table */\r\n" + 
	    			"\r\n" + 
	    			".footer_fail,\r\n" + 
	    			".footer_pass {\r\n" + 
	    			"	font-family: comic ms;\r\n" + 
	    			"	height: 27px;\r\n" + 
	    			"	background: #36304a;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"	font-size: 18px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* footer table row */\r\n" + 
	    			"\r\n" + 
	    			".fr1 {\r\n" + 
	    			"	height: 10px;\r\n" + 
	    			"	background: white;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".footer_tr td.footer_skip {\r\n" + 
	    			"	color: #e3c800;\r\n" + 
	    			"	/* yellow */\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".footer_tr td.footer_pass {\r\n" + 
	    			"	color: #20ea1c;\r\n" + 
	    			"	/* green */\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".footer_tr td.footer_fail {\r\n" + 
	    			"	color: #f73131;\r\n" + 
	    			"	/* red */\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"td.fr1 {\r\n" + 
	    			"	font-family: calibri;\r\n" + 
	    			"	font-size: 17px;\r\n" + 
	    			"	color: black;\r\n" + 
	    			"	line-height: 1;\r\n" + 
	    			"	font-weight: unset;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"/* Title header */\r\n" + 
	    			"\r\n" + 
	    			".tablehead1 th {\r\n" + 
	    			"	font-family: Copperplate Gothic Bold;\r\n" + 
	    			"	font-size: 1.4em;\r\n" + 
	    			"	color: #fff;\r\n" + 
	    			"	line-height: 1;\r\n" + 
	    			"	font-weight: unset;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* Header table details	*/\r\n" + 
	    			"\r\n" + 
	    			".tablehead2 th {\r\n" + 
	    			"	font-family: calibri;\r\n" + 
	    			"	font-size: 15px;\r\n" + 
	    			"	color: white;\r\n" + 
	    			"	line-height: 1;\r\n" + 
	    			"	font-weight: unset;\r\n" + 
	    			"	text-align: left;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"body {\r\n" + 
	    			"	background-color: #000000;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"tbody tr:nth-child(even) {\r\n" + 
	    			"	background-color: #f5f5f5;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* content data */\r\n" + 
	    			"\r\n" + 
	    			"tbody tr {\r\n" + 
	    			"	font-family: Trebuchet MS;\r\n" + 
	    			"	font-size: 13px;\r\n" + 
	    			"	color: black;\r\n" + 
	    			"	line-height: 1.2;\r\n" + 
	    			"	font-weight: unset;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"tbody tr.tr_content:hover {\r\n" + 
	    			"	color: black;\r\n" + 
	    			"	background-color: #b8f9f5;\r\n" + 
	    			"	display: table-row;\r\n" + 
	    			"	/*  font-weight:bold; */\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			".hcolumn1 {\r\n" + 
	    			"	width: 25px;\r\n" + 
	    			"	padding-left: 10px;\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".keyword_pass {\r\n" + 
	    			"	font-weight: bold;\r\n" + 
	    			"	color: green;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".keywordlnk {\r\n" + 
	    			"	font-weight: bold;\r\n" + 
	    			"	color: blue;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".keyword_fail {\r\n" + 
	    			"	font-weight: bold;\r\n" + 
	    			"	color: red;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".th_screenshot {\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"td.td_screenshot { \r\n" + 
	    			"\r\n" + 
	    			"	text-align: center;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".switch {\r\n" + 
	    			"	position: absolute;\r\n" + 
	    			"	display: inline-block;\r\n" + 
	    			"	width: 45px;\r\n" + 
	    			"	height: 19px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".switch input {\r\n" + 
	    			"	opacity: 0;\r\n" + 
	    			"	width: 0;\r\n" + 
	    			"	height: 0;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".slider {\r\n" + 
	    			"	position: absolute;\r\n" + 
	    			"	cursor: pointer;\r\n" + 
	    			"	top: 0;\r\n" + 
	    			"	left: 0;\r\n" + 
	    			"	right: 0;\r\n" + 
	    			"	bottom: 0;\r\n" + 
	    			"	background-color: #ccc;\r\n" + 
	    			"	-webkit-transition: .4s;\r\n" + 
	    			"	transition: .4s;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".slider:before {\r\n" + 
	    			"	position: absolute;\r\n" + 
	    			"	content: '';\r\n" + 
	    			"	height: 10px;\r\n" + 
	    			"	width: 10px;\r\n" + 
	    			"	left: 4px;\r\n" + 
	    			"	bottom: 4px;\r\n" + 
	    			"	background-color: white;\r\n" + 
	    			"	-webkit-transition: .4s;\r\n" + 
	    			"	transition: .4s;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"input:checked+.slider {\r\n" + 
	    			"	background-color: #2196F3;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"input:focus+.slider {\r\n" + 
	    			"	box-shadow: 0 0 1px #2196F3;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"input:checked+.slider:before {\r\n" + 
	    			"	-webkit-transform: translateX(26px);\r\n" + 
	    			"	-ms-transform: translateX(26px);\r\n" + 
	    			"	transform: translateX(26px);\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"/* Rounded sliders */\r\n" + 
	    			"\r\n" + 
	    			".slider.round {\r\n" + 
	    			"	border-radius: 34px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			".slider.round:before {\r\n" + 
	    			"	border-radius: 50%;\r\n" + 
	    			"}\r\n" + 
	    			"#scruolUpBtn {\r\n" + 
	    			"     display: none;\r\n" + 
	    			"    position: fixed;\r\n" + 
	    			"    bottom: 20px;\r\n" + 
	    			"    right: 13px;\r\n" + 
	    			"    /* z-index: 99; */\r\n" + 
	    			"    font-size: 18px;\r\n" + 
	    			"    /* border: none; */\r\n" + 
	    			"    outline: none;\r\n" + 
	    			"    background-color: #555;\r\n" + 
	    			"    color: white;\r\n" + 
	    			"    cursor: pointer;\r\n" + 
	    			"    padding: 7px;\r\n" + 
	    			"    border-radius: 45px;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"#scruolUpBtn:hover {\r\n" + 
	    			"  background-color: red;\r\n" + 
	    			"}\r\n" + 
	    			"html {\r\n" + 
	    			"  scroll-behavior: smooth;\r\n" + 
	    			"}\r\n" + 
	    			"}</style> \r\n" + 
	    			"<script>\r\n" + 
	    			"function imgopen(objID){\r\n" + 
	    			"// Get the modal\r\n" + 
	    			"var modal = document.getElementById('modal'+objID);\r\n" + 
	    			"// Get the image and insert it inside the modal - use its 'alt' text as a caption\r\n" + 
	    			"var img = document.getElementById(objID);\r\n" + 
	    			"var modalImg = document.getElementById(objID);\r\n" + 
	    			"var captionText = document.getElementById('caption');\r\n" + 
	    			"img.onclick = function(){\r\n" + 
	    			"  modal.style.display = 'block';\r\n" + 
	    			"  modalImg.src = this.src;\r\n" + 
	    			"  captionText.innerHTML = this.alt;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"// Get the <span> element that closes the modal\r\n" + 
	    			"\r\n" + 
	    			"var span = modal.getElementsByTagName('span')[0];\r\n" + 
	    			"span.onclick=function(){\r\n" + 
	    			"	 modal.style.display = 'none'\r\n" + 
	    			"}\r\n" + 
	    			"// When the user clicks anywhere outside of the modal, close it\r\n" + 
	    			"window.onclick = function(event) {\r\n" + 
	    			"  if (event.target == modal) {\r\n" + 
	    			"    modal.style.display = 'none';\r\n" + 
	    			"  }\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"function imgToggle() {\r\n" + 
	    			"	var ob = document.getElementsByName('screenshot');\r\n" + 
	    			"	var ob2 = document.getElementsByTagName('figure');\r\n" + 
	    			"\r\n" + 
	    			"	for (var i = 0; i < ob.length; i++) {\r\n" + 
	    			"		if (ob === null) {\r\n" + 
	    			"			break;\r\n" + 
	    			"		}\r\n" + 
	    			"		if (ob[i].style.display === 'none') {\r\n" + 
	    			"			try {\r\n" + 
	    			"				ob[i].style.display = 'table-row';\r\n" + 
	    			"				ob2[i].style.display = 'table-row';\r\n" + 
	    			"				\r\n" + 
	    			"			} catch (ex) {\r\n" + 
	    			"				ob[i].style.display = 'block';\r\n" + 
	    			"				ob2[i].style.display = 'block';\r\n" + 
	    			"			}\r\n" + 
	    			"		} else {\r\n" + 
	    			"			ob[i].style.display = 'none';\r\n" + 
	    			"			ob2[i].style.display = 'none';\r\n" + 
	    			"		}\r\n" + 
	    			"	}\r\n" + 
	    			"}\r\n" + 
	    			""+
	    			"function keywordColor() {\r\n" + 
	    			"var mainlist = document.getElementsByTagName('tbody');\r\n" + 
	    			"	//console.log(mainlist.length);\r\n" + 
	    			"	for (var i = 0; i < mainlist.length; i++) {\r\n" + 
	    			"		var sublist = mainlist[i].getElementsByClassName('td_fstatus');\r\n" + 
	    			"		var keywordEle = mainlist[i].getElementsByClassName('keywordlnk')[0];\r\n" + 
	    			"		if (sublist.length != 0) {\r\n" + 
	    			"			mainlist[i].getElementsByClassName('keywordlnk')[1].innerText='| FAILED |';\r\n" + 
	    			"			mainlist[i].getElementsByClassName('keywordlnk')[1].setAttribute('class', 'keyword_fail');\r\n" + 
	    			"			keywordEle.setAttribute('class', 'keyword_fail'); // kiran\r\n" + 
	    			"			\r\n" + 
	    			"		} else {\r\n" + 
	    			"			var sublist = mainlist[i].getElementsByClassName('td_pstatus');\r\n" + 
	    			"			if (sublist.length != 0) {\r\n" + 
	    			"				mainlist[i].getElementsByClassName('keywordlnk')[1].innerText='| PASSED |';\r\n" + 
	    			"				mainlist[i].getElementsByClassName('keywordlnk')[1].setAttribute('class', 'keyword_pass');\r\n" + 
	    			"				keywordEle.setAttribute('class', 'keyword_pass');\r\n" + 
	    			"			}\r\n" + 
	    			"		}\r\n" + 
	    			"	}\r\n" + 
	    			"	}" + 
	    			"	// When the user scrolls down 20px from the top of the document, show the button\r\n" + 
	    			"window.onscroll = function() {scrollFunction()};\r\n" + 
	    			"var mybutton ;\r\n" + 
	    			"function scrollFunction() {\r\n" + 
	    			"    mybutton = document.getElementById(\"scruolUpBtn\");\r\n" + 
	    			"  if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {\r\n" + 
	    			"    mybutton.style.display = 'block';\r\n" + 
	    			"  } else {\r\n" + 
	    			"    mybutton.style.display = 'none';\r\n" + 
	    			"  }\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"// When the user clicks on the button, scroll to the top of the document\r\n" + 
	    			"function topFunction() {\r\n" + 
	    			"  document.body.scrollTop = 0;\r\n" + 
	    			"  document.documentElement.scrollTop = 0;\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"function expandKeywords(){\r\n" + 
	    			"var keywordLinks = document.getElementsByName('keywordlink');\r\n" + 
	    			"	for (var i = 0; i < keywordLinks.length; i++) {\r\n" + 
	    			"			var innertext = keywordLinks[i].innerText;\r\n" + 
	    			"			if(innertext.includes('+')){\r\n" + 
	    			"				keywordLinks[i].click();\r\n" + 
	    			"				innertext = innertext.replace('+','--');\r\n" + 
	    			"				keywordLinks[i].innerText=innertext;\r\n" + 
	    			"			}\r\n" + 
	    			"			else{\r\n" + 
	    			"\r\n" + 
	    			"				keywordLinks[i].click();\r\n" + 
	    			"				innertext = innertext.replace('--','+');\r\n" + 
	    			"				keywordLinks[i].innerText=innertext;\r\n" + 
	    			"			}\r\n" + 
	    			"	}\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"function toggleMenu(objID) {\r\n" + 
	    			"	var ob = document.getElementsByName(objID);\r\n" + 
	    			"	for (var i = 0; i < ob.length; i++) {\r\n" + 
	    			"		if (ob === null) {\r\n" + 
	    			"			break;\r\n" + 
	    			"		}\r\n" + 
	    			"		if (ob[i].style.display === 'none') {\r\n" + 
	    			"\r\n" + 
	    			"			try {\r\n" + 
	    			"				ob[i].style.display = 'table-row';\r\n" + 
	    			"				\r\n" + 
	    			"			} catch (ex) {\r\n" + 
	    			"				ob[i].style.display = 'block';\r\n" + 
	    			"				\r\n" + 
	    			"					\r\n" + 
	    			"\r\n" + 
	    			"			}\r\n" + 
	    			"		} else {\r\n" + 
	    			"			ob[i].style.display = 'none';\r\n" + 
	    			"\r\n" + 
	    			"				\r\n" + 
	    			"		}\r\n" + 
	    			"	}\r\n" + 
	    			"	var parentEle = document.getElementsByName(objID)[0].parentNode\r\n" + 
	    			"	var inntext = parentEle.firstElementChild.firstElementChild.textContent\r\n" + 
	    			"				if(inntext.includes('+'))\r\n" + 
	    			"					parentEle.firstElementChild.firstElementChild.textContent = inntext.replace('+','--');\r\n" + 
	    			"				else\r\n" + 
	    			"					parentEle.firstElementChild.firstElementChild.textContent = inntext.replace('--','+');\r\n" + 
	    			"}\r\n" + 
	    			"\r\n" + 
	    			"\r\n" + 
	    			"	</script>" + 
	    			"</head>\r\n" + 
	    			"<body onload='keywordColor();'>");
	    	
	    	saveTestLogHTML(title,filePath);
	    }
	    
	    private  String getTextInRedColor(String Log) {
			return "<font color='#FF5733'><b>"+Log+"</b></font>";
	}
//	private String getTextInRedColor(int Log) {
//		return "<font color='#FF5733'><b>"+Log+"</b></font>";
//	}
}
