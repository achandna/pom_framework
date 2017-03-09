package guru99tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.guru.Guru99HomePage;
import pages.guru.Guru99Login;
import base.BaseMethods;

public class CreateNewCustomer {
	

    WebDriver driver;

    Guru99Login objLogin;

    Guru99HomePage objHomePage;
    
    BaseMethods basemethod;

    

    @BeforeTest

    public void setup() throws InterruptedException, MalformedURLException{
    	
    	driver = BaseMethods.openBrowser();
    	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://demo.guru99.com/V4/");

    }

    /**

     * This test case will login in http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */

    @Test(priority=0)

    public void test_Home_Page_Appear_Correct(){

        //Create Login Page object

    objLogin = new Guru99Login(driver);

    //Verify login page title

    String loginPageTitle = objLogin.getLoginTitle();

    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application

    Guru99Login.loginToGuru99("mngr69019", "yzEdytE");

    // go the next page

    objHomePage = new Guru99HomePage(driver);

    //Verify home page

    Assert.assertTrue(Guru99HomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr69019"));

    }
}