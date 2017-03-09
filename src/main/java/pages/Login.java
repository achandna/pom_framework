package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	static WebDriver driver;
	
	/**
	 * Defined constructor for Login page
	 * @param driver
	 */
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 *  Enter email id for Sign In
	 * @param emailId
	 */
	public void enterEmailforSignIn(String emailId){
		driver.findElement(By.id("email")).sendKeys(emailId);
	}
	
	/**
	 *  Enter password for Sign In
	 * @param password
	 */
	public void enterPassword(String password){
		driver.findElement(By.id("passwd")).sendKeys(password);
	}
	
	/**
	 *  click SignIn button to Sign In
	 */
	public void clickSignInButton(){
		driver.findElement(By.id("SubmitLogin")).click();
	}
	
	/**
	 * Method to login to the system
	 * @param emailId
	 * @param password
	 */
	public void login(String emailId, String password){
		enterEmailforSignIn(emailId);
		enterPassword(password);
		clickSignInButton();
	}

}
