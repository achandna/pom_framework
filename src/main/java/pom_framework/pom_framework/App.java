package pom_framework.pom_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "/home/abhishek/Documents/anil/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://google.com");
		System.out.println("Hello World!");
	}
}
