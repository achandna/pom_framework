import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Test {

	public Test() {
		
		
		
	}
	
	@org.testng.annotations.Test
	public void launchChrome(){
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", "/home/abhishek/workspace_luna/pom_framework/ChromeDriver/chromedriver");
		WebDriver driver = new ChromeDriver();
//		logger.info("Opened browser is chrome");
	}

}
