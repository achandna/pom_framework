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

	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
	}

	
	
	
	
	//Hover on the product by Number[starting from 1]
	public void hoverOnProductbyNumber(int productNumber){
		WebElement element = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+productNumber+"]"));
		System.out.println(element.getText().toString()+"----"+element.getAttribute("class"));
		action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	
	//This will select the product
	public void clickOnQuickViewByProductNumber(int productNumber){
		hoverOnProductbyNumber(productNumber);
		driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+productNumber+"]//a[@class = 'quick-view']")).click();
	}
	
	public void clickOnProductByProductNumber(int productNumber){
		wait = new WebDriverWait(driver, 60);
		WebElement element = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+productNumber+"]//img"));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
