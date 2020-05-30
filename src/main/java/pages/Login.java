package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {
	
	WebDriver driver;
	public Login(WebDriver driver){
		this.driver =driver;
	}

	@FindBy(name="email")
	public WebElement txt_userId;
	
	@FindBy(name="password")
	public WebElement txt_password;
	
	@FindBy(xpath = "//div[text()='Login']")
	public WebElement btn_Login;
	
	public void doLogin(String username, String passwod){
		txt_userId.sendKeys(username);
		txt_password.sendKeys(passwod);
		btn_Login.click();
	}
	
}
