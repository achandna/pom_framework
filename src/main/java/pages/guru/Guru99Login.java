package pages.guru;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

//mngr69019
//yzEdytE

public class Guru99Login {

    static WebDriver driver;

    static By user99GuruName = By.name("uid");

    static By password99Guru = By.name("password");

    static By titleText =By.className("barone");

    static By login = By.name("btnLogin");

    

    public Guru99Login(WebDriver driver){

        this.driver = driver;

    }

    //Set user name in textbox

    public static void setUserName(String strUserName){

        driver.findElement(user99GuruName).sendKeys(strUserName);;

    }

    

    //Set password in password textbox

    public static void setPassword(String strPassword){

         driver.findElement(password99Guru).sendKeys(strPassword);

    }

    

    //Click on login button

    public static void clickLogin(){

            driver.findElement(login).click();

    }

    

    //Get the title of Login Page

    public String getLoginTitle(){

     return    driver.findElement(titleText).getText();

    }

    /**

     * This POM method will be exposed in test case to login in the application

     * @param strUserName

     * @param strPasword

     * @return

     */

    public static void loginToGuru99(String strUserName,String strPasword){

        //Fill user name

        setUserName(strUserName);

        //Fill password

        setPassword(strPasword);

        //Click Login button

        clickLogin();        

        

    }

}