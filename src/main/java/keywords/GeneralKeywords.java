package keywords;

import org.openqa.selenium.support.PageFactory;

import com.framework.util.ReusableLibrary;
import com.framework.util.Status;
import com.framework.util.TestUtil;

import common.CRM_Constants;
import pages.Login;

public class GeneralKeywords extends ReusableLibrary{
	
	public GeneralKeywords(TestUtil testutil) {
		super(testutil);
	}

	public void login() {
		
		String url = properties.getApplicationURL();
		driver.get(url);
		Login loginPage = PageFactory.initElements(driver, Login.class);
		String username = excel.getData(CRM_Constants.SHEET_TESTDATA, "UserName");
		String password = excel.getData(CRM_Constants.SHEET_TESTDATA, "Password");
		loginPage.doLogin(username, password);
		report.log("login", "login successful", Status.PASS_SCREENSHOT);
		
	}
	
	public void google() {
		driver.get("http://www.google.com");
		report.log("google", "login successful", Status.PASS_SCREENSHOT);
	}

}
