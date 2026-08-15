package com.demowebshop.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class LoginPage_TC1 {
	
	WebDriver driver;
	Logger logger=LogManager.getLogger(this.getClass());
	
	public LoginPage_TC1(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//a[contains(text(),'Log in')]")
	private WebElement HomePageLoginInBtn;
	
	@FindBy(xpath ="//input[@id='Email']")
	private WebElement username;
	
	@FindBy(xpath="//input[@id='Password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@class='button-1 login-button']")
	private WebElement loginBtn;
	
	
	@FindBy(xpath="//h2[normalize-space(text())='Welcome to our store']")
	private WebElement Homepage;
	
	@FindBy(xpath="//span[contains(text(),'Login was unsuccessful. Please correct the errors and try again.')]")
	private WebElement loginError;
	
	public void Login(String UserName,String Password) {
		logger.info("***Login Functionality Started***");
		HomePageLoginInBtn.click();
		logger.info("Click on HomePageLoginInBtn Link");
		
		logger.info("Entering the Login Credentials");
		username.sendKeys(UserName);
		password.sendKeys(Password);
		loginBtn.click();
	}
	
	public String getActualHomepageSuccessText() {
		logger.debug("Getting ActualHomepageSuccessText");
		return Homepage.getText();
	}
	
	public boolean isLoginErrorDisplayed() {
		logger.debug("Checking isLoginErrorDisplayed");
		return loginError.isDisplayed();
	}

	

}
