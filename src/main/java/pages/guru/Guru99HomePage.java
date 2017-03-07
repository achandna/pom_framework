package pages.guru;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

//import base.BaseMethods; 

public class Guru99HomePage {

    static WebDriver driver;

    static By homePageUserName = By.xpath("//table//tr[@class='heading3']");

    

    public Guru99HomePage(WebDriver driver){

        this.driver = driver;

    }

    

    //Get the User name from Home Page

        public static String getHomePageDashboardUserName(){

         return    driver.findElement(homePageUserName).getText();

        }

}