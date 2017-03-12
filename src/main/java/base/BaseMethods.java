package base;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.MarionetteDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.RETURN;
import org.apache.log4j.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.gargoylesoftware.htmlunit.BrowserVersion;



















//import io.github.bonigarcia.wdm.ChromeDriverManager;
//import io.github.bonigarcia.wdm.MarionetteDriverManager;
//import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * This class is a generic class where all generic methods are written.
 * 
 * @author karan
 *
 */

public class BaseMethods {
	
	static Logger logger = Logger.getLogger(BaseMethods.class);
	protected static WebDriver driver;
	private String selectDataSet=null;
	private int datasetIndex=0;
	Select select;
	WebElement dateWidget;
	List<WebElement> rows;
	List<WebElement> columns;
	static String fileInput = "config.properties";
	static String browserSelected = getDataFromPropertyFile("browser").trim();

	
	
		
	/**
	 * Return instance of browser from openBrowser() method
	 * @return
	 * driver
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	
		
//	@BeforeTest
	public void setUp() throws Exception {

		openBrowser();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@AfterTest
	public void quitTest() {
		driver.quit();
	}

	/**
	 * Reads property from a file of the parameter is given in arguments
	 * @param variableName
	 * @return
	 * Value of a parameter passed from the file
	 */
	public static String getDataFromPropertyFile(String variableName) {
		String value = null;
		try (FileReader reader = new FileReader(fileInput)) {
			Properties properties = new Properties();
			properties.load(reader);
			value = properties.getProperty(variableName);
			logger.info(variableName+" : "+"value : "+value);
		} catch (IOException e) {
			logger.error("config.properties not found");
			e.printStackTrace();
		}
		return value.trim();

	}
	
	/**
	 * Reads appURL from config field
	 *  
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public String openUrl() throws InterruptedException, IOException {
		String url = null;
		try (FileReader reader = new FileReader(fileInput)) {

			System.out.println("Opened property file");
			Properties properties = new Properties();
			properties.load(reader);
			url = properties.getProperty("baseURL");
			logger.info("URL : "+url);

		} catch (IOException e) {
			logger.error("config.properties not found");
			e.printStackTrace();
		}
		return url;

	}

	/**
	 * Run on a specific browser which is specified in config.properties 
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 */

	public static WebDriver openBrowser() throws InterruptedException, MalformedURLException {
		System.out.println("Method called: openBrowser");
		try{
			driver = null;
			logger.info("Browser selected : "+browserSelected);
			if (browserSelected.equalsIgnoreCase("firefox")) {
				logger.info("Launching firefox browser");
				File pathToBinary = new File("/home/abhishek/firefox/firefox");
				FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
				FirefoxProfile firefoxProfile = new FirefoxProfile();       
				driver = new FirefoxDriver(ffBinary,firefoxProfile);
				//logger.info("firefox selected");
				// Code needs to be written to select chrome, IE and other browsers.
			} else if (browserSelected.equalsIgnoreCase("chrome")) {
				logger.info("launching chrome browser");
				System.setProperty("webdriver.chrome.driver", "ChromeDriver/chromedriver");
				driver = new ChromeDriver();
				//logger.info("Opened browser is chrome");
			} else if (browserSelected.equalsIgnoreCase("phantom")) {
				logger.info("launching phantom browser");
				Capabilities caps = new DesiredCapabilities();
				((DesiredCapabilities) caps).setJavascriptEnabled(true);
				((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
				((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"/home/abhishek/Documents/anil/phantomjs/bin/phantomjs");
				driver = new PhantomJSDriver(caps);
				//logger.info("opened browser is phantom");

			} else if (browserSelected.equalsIgnoreCase("browserstack")) {
				logger.info("launching browserstack");
				String URL = "https://" + getDataFromPropertyFile("bsUserName") + ":" + getDataFromPropertyFile("bsKey") + "@hub-cloud.browserstack.com/wd/hub";
				System.out.println("URL : "+URL);
			    DesiredCapabilities caps = new DesiredCapabilities();
			    caps.setCapability("browser", "Chrome");
			    caps.setCapability("browser_version", "56");
			    caps.setCapability("os", "Windows");
			    caps.setCapability("os_version", "10");
			    caps.setCapability("browserstack.debug", "true");

			    driver = new RemoteWebDriver(new URL(URL), caps);

			}
			
			else if (browserSelected.equalsIgnoreCase("html")) {

//				driver = new HtmlUnitDriver(BrowserVersion.CHROME);
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Browser Selected :-- "+browserSelected);

		} catch (WebDriverException e) {
			e.printStackTrace();
			logger.error("There is some problem with browser config");
		}
		return driver;

	}





		public static WebDriver openBrowser1() throws InterruptedException {
			try {
				driver = null;
				System.setProperty("webdriver.gecko.driver", "/home/abhishek/Documents/anil/geckodriver");
				System.out.println(":)>>>>" + browserSelected);
				if (browserSelected.equalsIgnoreCase("firefox")) {
	
					MarionetteDriverManager.getInstance().setup();
					driver = new FirefoxDriver();
					System.out.println("firefox selected");
				} else if (browserSelected.equalsIgnoreCase("chrome")) {
	
					ChromeDriverManager.getInstance().setup();
					driver = new ChromeDriver();
					logger.info("Opened browser is chrome");
				} else if (browserSelected.equalsIgnoreCase("phantom")) {
	
					PhantomJsDriverManager.getInstance().setup("2.1.1");
					PhantomJSDriverService service = new PhantomJSDriverService.Builder().usingAnyFreePort()
							.usingPhantomJSExecutable(Paths.get(System.getProperty("phantomjs.binary.path")).toFile())
							.build();
					DesiredCapabilities caps = new DesiredCapabilities();
					driver = new PhantomJSDriver(service, caps);
					logger.info("opened browser is phantom");
	
				} else if (browserSelected.equalsIgnoreCase("html")) {
	
					driver = new HtmlUnitDriver(BrowserVersion.CHROME);
				}
				System.out.println("Browser Selected :--");
	
			} catch (WebDriverException e) {
				e.printStackTrace();
				System.out.println("There is some problem with browser config");
			}
			return driver;
		}

	/**
	 * 
	 * @param month
	 * @param year
	 * @param date
	 * @throws InterruptedException
	 */
	public void selectDate(String month, String year, String date) throws InterruptedException {
		WebElement mon = driver.findElement(By.cssSelector(".ui-datepicker-month"));
		if (mon.isDisplayed()) {
			select = new Select(mon);
			select.selectByVisibleText(month);
		} else {
			logger.info(mon + "is not visible in headless mode");
		}

		select = new Select(driver.findElement(By.cssSelector(".ui-datepicker-year")));
		Thread.sleep(5000);
		select.selectByVisibleText(year);
		dateWidget = driver.findElement(By.id("ui-datepicker-div"));

		rows = dateWidget.findElements(By.tagName("tr"));
		columns = dateWidget.findElements(By.tagName("td"));

		for (WebElement col : columns) {

			if (col.getText().equals(date)) {
				col.findElement(By.linkText(date)).click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			}

		}
	}
	/**
	 * Fetch current URL
	 * @return
	 */
	public String getCurUrl() {
		return driver.getCurrentUrl();

	}
	/**
	 *  
	 * @param navURL
	 */

	public void navigateTo(String navURL) {
		driver.navigate().to(navURL);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void compareElementPresent(String xpath, String expectedText) {
		Assert.assertEquals(driver.findElement(By.xpath(xpath)).getText(), expectedText);
	}

	/**
	 * @param File
	 *            Name
	 * @param Sheet
	 *            Name
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];

			for (int i = 1; i < totalNoOfRows; i++) {

				for (int j = 0; j < totalNoOfCols; j++) {
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
	
	/*
	 * This function will fetch the dataset value to be used for 'getVariableFromXML' and 'getListVariableFromXML' methods
	 */
	public String getSelectDataSet() {
		return selectDataSet;
	}
	
	
	/*
	 * it will set the value of dataset to be choosen for variable and listVariables
	 */

	public void setSelectDataSet(String selectDataSet) {
		this.selectDataSet = selectDataSet;
	}


	/*
	 * This function will fetch the index of the dataset to be used for 'getVariableFromXML' and 'getListVariableFromXML' methods
	 */
	public int getDataSetValue() {
		return datasetIndex;
	}

	/*
	 * it will set the index of variable and listVariables data to be used
	 */
	public void setDataSetValue(int datasetIndex) {
		this.datasetIndex = datasetIndex;
	}



	
	/*
	 * This function will return the String Value.
	 * We just need to keep the variable name to be fetched in code as the tag name in xml file.
	 * Example :-	
	 	<variable dataset="test1">
			<titleName>Node 2</titleName>
			<name>Name Of The Movie</name>
		</variable>
		titleName,name will be the variable name from which we are going to fetch data
		getVariableFromXML("name") will return 'Name Of The Movie' value.
	 * 
	 * dataset is used for fetching the data frrom specific dataset. for example :- we might have many dataset in xml and we want to fetch
	 * data from dataset='test' , so, in this case, it will ignore rest of the datasets and will pick the data from 'test' dataset only.
	 * 
	 */
	public String getVariableFromXML(String variableName) {
		 
		  SAXBuilder builder = new SAXBuilder();
		  String className=this.getClass().getSimpleName();
		  className=className.concat(".xml");
		  String xmlFileName = this.getClass().getResource(className).getPath();
		  System.out.println("digesting input file --->>>>>"+xmlFileName);
		  File xmlFile = new File(xmlFileName);  
		  String nodeValue=null;
		  if(xmlFile.exists()){
		  try {
			  
	 		Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			//System.out.println(rootNode.getChild("variable").getAttribute("dataset").getValue());
			List list = rootNode.getChildren("variable");
			
			for(int temp=0;temp<list.size();temp++){
				Element node =(Element) list.get(temp);
				if(node.hasAttributes()){
				if(node.getAttribute("dataset").getValue().toString().equals(selectDataSet)){
					datasetIndex=temp;
					System.out.println("try");
					break;
				}
				}
			}
			System.out.println(datasetIndex);
			Element node = (Element) list.get(datasetIndex);
			nodeValue=node.getChild(variableName).getValue();
			

			} catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		  }
		  else
			  System.out.println("Throw Error"); //We need to create exception here
		  return nodeValue;
		}
	
	
	/*
	 * The below given method will return the list of the variable included in "listVariable" node.
	 * And will get the child tags(only <var>)into the list
	 * Example :-
	 * 	<listVariable dataset="test1">
		<list1>
			<var>value1</var>
			<var>value2</var>
		</list1>
		</listVariable>
	 *  In this xml file, list1 is the variable name and passing this variable name will get all the child element value with var tag into
	 *  a list and will return that list.
	 *  
	 *  dataset  value will define like, from which listVariable data, we are planning to get data.
	 *  we can set it by function  "setSelectDataSet(String selectDataSet)"
	 *  If, we don't use this function, data will get fetched from first listVariable available in xml file
	 */
	
	public ArrayList<String> getListFromXMLTest(String listVariableName) {
		  SAXBuilder builder = new SAXBuilder();
		  String className=this.getClass().getSimpleName();
		  className=className.concat(".xml");
		  String xmlFileName = this.getClass().getResource(className).getPath();
		  File xmlFile = new File(xmlFileName);  
		  String nodeValue=null;
		  ArrayList<String> listOfData = new ArrayList<String>();
		  if(xmlFile.exists()){
		  try {
	 		Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("listVariable");
			for(int temp=0;temp<list.size();temp++){
				Element node =(Element) list.get(temp);
				if(node.hasAttributes()){
				if(node.getAttribute("dataset").getValue().toString().equals(selectDataSet)){
					datasetIndex=temp;
					break;
				}
				}
			}
			Element variableNode = (Element) list.get(datasetIndex);
			variableNode=variableNode.getChild(listVariableName);
			List<Element> listChild = variableNode.getChildren("var");
			for(int temp1=0;temp1<listChild.size();temp1++){
				Element node1=(Element) listChild.get(temp1);
				listOfData.add(temp1, node1.getValue());
			}
			

			} catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		  }
		  else
			  System.out.println("Throw Error"); //We need to create exception here
		  return listOfData;
		}


}
