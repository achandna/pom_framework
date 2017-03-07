package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	static WebDriver driver;
	
	//Defined constructor for Login page
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	
	// Enter email id for signin
	public void enterEmailforSignIn(String emailId){
		driver.findElement(By.id("email")).sendKeys(emailId);
	}
	
	// Enter passwordhn for signin
	public void enterPassword(String password){
		driver.findElement(By.id("passwd")).sendKeys(password);
	}
	
	// click SignIn button
	public void clickSignInButton(){
		driver.findElement(By.id("SubmitLogin")).click();
	}
	
	//Method to login to the system
	public void login(String emailId, String password){
		enterEmailforSignIn(emailId);
		enterPassword(password);
		clickSignInButton();
	}

}
