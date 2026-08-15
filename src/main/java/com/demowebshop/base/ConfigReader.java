package com.demowebshop.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader{
	
	protected Properties configProp;
	protected Properties testDataProp;
	
	public ConfigReader() {
		try {
			loadProperties();
		} catch (Exception e) {
			
			e.toString();
		}
	}
	
	
	public void loadProperties() throws Exception {
		configProp = new Properties();
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		FileInputStream fis=new FileInputStream(f);
		configProp.load(fis);
		
		testDataProp= new Properties();
		File f1=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\testdata.properties");
		FileInputStream fis1= new FileInputStream(f1);
		testDataProp.load(fis1);
		
	}
	
	//configProp getter Method
	public String getExecutionEnvironment() {
	    return configProp.getProperty("execution_env");
	}

	public String getGridURL() {
	    return configProp.getProperty("grid_url");
	}
	
	public String getUrl() {
		return configProp.getProperty("url");
	}
	
	public String getBrowserName() {
		return configProp.getProperty("browsername");
	}
	
	public String getValidEmail() {
		return configProp.getProperty("validemail");
	}
	
	public String getValidPassword() {
		return configProp.getProperty("validpassword");
	}

	public int getPageLoadTimeout() {
		return Integer.parseInt(configProp.getProperty("pageloadtimeout"));
	}
	
	public int getImplictWait() {
		return Integer.parseInt(configProp.getProperty("implictwait"));
	}
	
/** --------testDataProp Getter Method--------------------------------------------------------------------------------------------*/
	public String getInvalidEmail() {
		return testDataProp.getProperty("invalidemail");
	}
	
	public String getInvalidPassword() {
		return testDataProp.getProperty("invalidpassword");
	}
	
	public String getExpectedHomePageSuccessMsg() {
		return testDataProp.getProperty("expectedHomePageSuccessMsg");
	}
	
	public String getFirstName() {
		return testDataProp.getProperty("fistName");
	}
	
	public String getLastName() {
		return testDataProp.getProperty("lastName");
	}
	
	public String getEmailAddress() {
		return testDataProp.getProperty("emailAddress");
	}
	
	public String getExpectedUserAlreadyExistMsg() {
		return testDataProp.getProperty("expectedUserAlreadyExistMsg");
	}
	
	public String getExpectedAlertMsg() {
		return testDataProp.getProperty("expectedAlertMsg");
	}
	
	public String getInvalidProductName() {
		return testDataProp.getProperty("invalidProductName");
	}

	public String getValidProductName() {
		return testDataProp.getProperty("validProductName");
	}
}
