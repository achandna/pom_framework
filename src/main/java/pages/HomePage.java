package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	static WebDriver driver;
	

	public HomePage(WebDriver driver){
		System.out.println("I am in constructor");
		this.driver = driver;
	}
	
	// To click the sign in button present on the Index page
	public void clickSignIn(){
		System.out.println("I am in clickSignIn");
		driver.findElement(By.className("login")).click();
	}
	
	// To click the contact us button present on the Index page
	public void clickContactUs(){
		driver.findElement(By.xpath("//div[@id = 'contact-link']/a")).click();
	}
	
	//click Women Menu link
	public void clickWomanMenu(){
		driver.findElement(By.xpath("//a[@title = 'Women']")).click();
	}
	
	//Verify account Name
	public String verifyAccountName(){
		return driver.findElement(By.xpath("//a[@class='account']/span")).getText();
		
	}
	
	//Verify correct user is logged in
	public String verifyCorrectUserLoggedIn(){
		return verifyAccountName();
	}
	
	

}
