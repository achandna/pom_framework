package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class ProductAddedToCartPage {
	WebDriver driver;
	
	/**
	 * This constructor is to get Instance of driver to the Homepage class.
	 * @param driver
	 */
	public ProductAddedToCartPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Click to proceed to checkout
	 */
	public void clickProceedToCheckout(){
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
	}
	
	/**
	 *Click to continue shopping
	 */
	public void clickContinueShopping(){
		driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();
	}
	
	/**
	 * wait for the item to display in bag
	 */
	public void waitForProductAddedToCartPageToAppear(){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Proceed to checkout']")));
	}
}
