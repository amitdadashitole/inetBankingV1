package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig rc = new ReadConfig();
	public String baseURL=rc.getApplicationURL();//"http://demo.guru99.com/v4/";
	public String username=rc.getUsername();//"mngr322265";
	public String password=rc.getPassword();//"yjUmEzU";
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",rc.getChromepath());
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\amits\\eclipse-workspace\\inetBankingV1\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.geecko.driver",rc.getChromepath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",rc.getChromepath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
		logger.info("URL is opened");
		
		
		
	}
	 public void captureScreen(WebDriver driver,String tname) throws IOException
	 {
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File source=ts.getScreenshotAs(OutputType.FILE);
		 File target=new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		 FileUtils.copyFile(source, target);
		 System.out.println("Screenshot Taken");
	 }
	 public String randomestring() {
			
			String generatedstring= RandomStringUtils.randomAlphabetic(8);
			return generatedstring;
		}
	
	@AfterClass
	public void tearDown()
	{
		 driver.quit();
	}
}
