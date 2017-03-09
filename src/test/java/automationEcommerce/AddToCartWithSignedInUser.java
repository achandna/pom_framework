package automationEcommerce;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.HomePage;
import pages.Login;
import pages.ProductAddedToCartPage;
import pages.ProductDescriptionPage;
import pages.WomenMenuPage;
import pages.basePages.ProductListingPage;
import base.BaseMethods;



public class AddToCartWithSignedInUser {
	
	WebDriver driver;
	HomePage homepage;
	Login login;
	ProductListingPage productListing;
	WomenMenuPage womenPage;
	ProductDescriptionPage productView;
	ProductAddedToCartPage productAdded;
	static Logger logger = LogManager.getLogger(BaseMethods.class);
	
    @BeforeTest

    public void setup() throws InterruptedException, MalformedURLException{
    	
    	driver = BaseMethods.openBrowser();
    	
    	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(BaseMethods.getDataFromPropertyFile("appURL"));

    }
    
    @Test
    public void addToCartThroughQuickView() throws InterruptedException{
    	
    	SoftAssert sAssert = new SoftAssert();
    	
    	homepage = new HomePage(driver);
    	homepage.clickSignIn();
    	    	
    	login = new Login(driver);
    	login.enterEmailforSignIn(BaseMethods.getDataFromPropertyFile("emailId"));
    	login.enterPassword(BaseMethods.getDataFromPropertyFile("password"));
    	login.clickSignInButton();
    	
    	String userName = homepage.verifyAccountName();
    	//Assertion to check if correct user is logged in
    	sAssert.assertTrue(userName.equals("Anil Chandna"), "User account Name did not match.\n Expected Name is:"+"Anil Chandna"+"\nActual UserName is:"+userName);
    	homepage.clickWomanMenu();
    	
    	womenPage = new WomenMenuPage(driver);
    	womenPage.clickOnProductByProductNumber(1);
    	
    	
    	productView = new ProductDescriptionPage(driver);
    	//Assertion to check if the product selected has correct name?
    	String producName = productView.getProductName();
    	sAssert.assertTrue(producName.equals("Faded Short Sleeve T-shirts"), "The expected name of the product does not match with the actual\nexpected:"+"Faded Short Sleeve T-shirts"+"\nactual:"+producName);
    	productView.clickAddToCart();
    	
    	productAdded = new ProductAddedToCartPage(driver);
    	productAdded.waitForProductAddedToCartPageToAppear();
//    	Thread.sleep(3000);
    	productAdded.clickProceedToCheckout();
    	
    	sAssert.assertAll();
    }
	
    
	
	

}
