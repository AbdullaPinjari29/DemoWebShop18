package com.demowebshop.base;

import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static ConfigReader config;
	public static Logger logger;

	@BeforeMethod(groups={"Sanity","Regression","Master"})
    @Parameters({"browser","operatingSystem"})
	public void browserSetUp(String br, String os) throws Exception {

		logger = LogManager.getLogger(this.getClass());
		config = new ConfigReader();

		logger.info("Browser setup started");

		/*
		if (config.getBrowserName().equalsIgnoreCase("Chrome")) {
			logger.info("Launching Chrome Browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (config.getBrowserName().equalsIgnoreCase("Edge")) {
			logger.info("Launching Edge Browser");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		else if (config.getBrowserName().equalsIgnoreCase("FireFox")) {
			logger.info("Launching FireFox Browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (config.getBrowserName().equalsIgnoreCase("Internet Explorer")) {
			logger.info("Launching IE Browser");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} */

		
		if(config.getExecutionEnvironment().equalsIgnoreCase("remote"))
		{
            DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
				
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		if(config.getExecutionEnvironment().equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase()) {
			case "chrome":
				logger.info("Launching Chrome Browser");

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();

				/*ChromeOptions cOptions = new ChromeOptions();
				cOptions.addArguments("--headless=new");
				driver = new ChromeDriver(cOptions);*/

				break;

			case "edge":
				logger.info("Launching Edge Browser");

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

				/*EdgeOptions eOptions = new EdgeOptions();
				eOptions.addArguments("--headless=new");
				driver = new EdgeDriver(eOptions);*/

				break;

			case "firefox":
				logger.info("Launching FireFox Browser");

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				/*
				 FirefoxOptions fOptions = new FirefoxOptions();
                 fOptions.addArguments("--headless");
                 driver = new FirefoxDriver(fOptions);
				 */
				break;

			case "internetexplorer":
				logger.info("Launching InternetExplorer Browser");

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;

			}
		}
		
		

		logger.info("Getting DemoWebShop URL");

		driver.get(config.getUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.getPageLoadTimeout()));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplictWait()));

	}

	@AfterMethod(groups={"Sanity","Regression","Master"})
	public void browserTearDown() {
		logger.info("Closing the Browser");
		driver.quit();
	}

}
