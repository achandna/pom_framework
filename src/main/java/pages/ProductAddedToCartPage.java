package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class ProductAddedToCartPage {
	WebDriver driver;
	public ProductAddedToCartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickProceedToCheckout(){
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
	}
	
	public void clickContinueShopping(){
		driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();
	}
	
	public void waitForProductAddedToCartPageToAppear(){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Proceed to checkout']")));
	}
}
