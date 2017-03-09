package pages.basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListingPage {
	WebDriver driver;
	Actions action;
	WebDriverWait wait;

	/**This construction is to initialize the instance of driver object
	 * 
	 * @param driver
	 */
	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	/**
	 * Hover on the product by Number[starting from 1]
	 * @param productNumber
	 */
	public void hoverOnProductbyNumber(int productNumber){
		WebElement element = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+productNumber+"]"));
		System.out.println(element.getText().toString()+"----"+element.getAttribute("class"));
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	
	/**
	 * This will select the product to view quickly.
	 * @param productNumber
	 */
	public void clickOnQuickViewByProductNumber(int productNumber){
		hoverOnProductbyNumber(productNumber);
		driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+productNumber+"]//a[@class = 'quick-view']")).click();
	}
	
	/**
	 * To click on a product by Number[starting from 1]
	 * @param productNumber
	 */
	public void clickOnProductByProductNumber(int productNumber){
		wait = new WebDriverWait(driver, 60);
		WebElement element = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+productNumber+"]//img"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
