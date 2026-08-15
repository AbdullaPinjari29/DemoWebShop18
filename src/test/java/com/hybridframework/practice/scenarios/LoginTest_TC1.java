package com.hybridframework.practice.scenarios;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demowebshop.base.BaseClass;
import com.demowebshop.pages.LoginPage_TC1;
import com.demowebshop.utility.ExcelUtil;

@Test(groups={"Sanity","Master"})
public class LoginTest_TC1 extends BaseClass{

	 LoginPage_TC1 loginPage_TC1;
	 
	@Test(priority=1)
	public void verifyValidLogin() {
		loginPage_TC1 = new LoginPage_TC1(driver);
		
		loginPage_TC1.Login(config.getValidEmail(), config.getValidPassword());
		Assert.assertEquals(loginPage_TC1.getActualHomepageSuccessText(),config.getExpectedHomePageSuccessMsg());
	}
	
	@Test(priority=2)
	public void verifyInvalidUsernameandPasswordLogin() {
		loginPage_TC1 = new LoginPage_TC1(driver);
		
		loginPage_TC1.Login(config.getInvalidEmail(), config.getInvalidPassword());
		Assert.assertTrue(loginPage_TC1.isLoginErrorDisplayed());
		
	}
	
	@Test(priority=3)
	public void verifyValidUsernameInvalidPasswordLogin() {
		loginPage_TC1 = new LoginPage_TC1(driver);
		
		loginPage_TC1.Login(config.getValidEmail(), config.getInvalidPassword());
		Assert.assertTrue(loginPage_TC1.isLoginErrorDisplayed());
		
	}
	
	@Test(priority=4)
	public void verifyInvalidUsernameValidPasswordLogin() {
		loginPage_TC1 = new LoginPage_TC1(driver);
		
		loginPage_TC1.Login(config.getInvalidEmail(), config.getValidPassword());
		Assert.assertTrue(loginPage_TC1.isLoginErrorDisplayed());	
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		loginPage_TC1 = new LoginPage_TC1(driver);
		
		loginPage_TC1.Login("","");
		Assert.assertTrue(loginPage_TC1.isLoginErrorDisplayed());
	}
	
	@Test(priority=6,dataProvider="loginDetails")
	public void verifyValidLoginDDT(String userName,String password,String expectedResult) {
		loginPage_TC1 = new LoginPage_TC1(driver);
		
		loginPage_TC1.Login(userName,password);
		
		if(expectedResult.equalsIgnoreCase("Valid")) {
			Assert.assertEquals(loginPage_TC1.getActualHomepageSuccessText(),config.getExpectedHomePageSuccessMsg());
		}
		else {
			Assert.assertTrue(loginPage_TC1.isLoginErrorDisplayed());
		}
		
	}
	
	
	@DataProvider(name="loginDetails")
	public Object[][] getData() throws Exception{
		  ExcelUtil excel = new ExcelUtil();
          return excel.readExcelData("TestData.xlsx", "LoginData");
	}

	
	
}
