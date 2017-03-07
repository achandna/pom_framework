package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDescriptionPage {
	WebDriver driver;
	public ProductDescriptionPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Click on add to cart button
	public void clickAddToCart(){
		driver.findElement(By.xpath("//p[@id= 'add_to_cart']//button")).click();
	}
	
	//Click on Send to friend button
	public void clickSendToFriend(){
		driver.findElement(By.id("send_friend_button")).click();
	}
	
	// get product Name
	public String getProductName(){
		return driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
	}
}
