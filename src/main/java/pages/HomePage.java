package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 

public class HomePage {
	
	static WebDriver driver;
	static Logger logger = Logger.getLogger(HomePage.class);
	
	/**
	 * This constructor is to get Instance of driver to the Homepage class.
	 * @param driver
	 */
	public HomePage(WebDriver driver){
		System.out.println("I am in constructor");
		this.driver = driver;
	}
	
	/**
	* To click the sign in button present on the Index page
	* @param: no parameters are required
	*/ 
	public void clickSignInLink(){
		logger.info("I am in clickSignIn");
		driver.findElement(By.className("login")).click();
	}
	
	/**
	 *  To click the contact us button present on the Index page.
	 */
	public void clickContactUs(){
		driver.findElement(By.xpath("//div[@id = 'contact-link']/a")).click();
	}
	
	/**
	 * To click Women Menu link present in the menu
	 */
	public void clickWomanMenu(){
		logger.info("User clicks on Woman Menu");
		driver.findElement(By.xpath("//a[@title = 'Women']")).click();
	}
	
	/**
	 * Fetch account Name of logged in User
	 * @return
	 * String if Account Name exists, otherwise NULL
	 */
	public String verifyAccountName(){
		logger.info("User wants to verify account name");
		return driver.findElement(By.xpath("//a[@class='account']/span")).getText();
		
	}
	
	/**
	 * Verify correct user is logged in
	 * @return
	 */
	public String verifyCorrectUserLoggedIn(){
		logger.info("User wants to verify account name");
		return verifyAccountName();
	}
	
	

}
