package com.demowebshop.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage_TC2{

    WebDriver driver;
    Logger logger=LogManager.getLogger(this.getClass());
	
	public RegisterPage_TC2(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//a[text()='Register']") 
	private WebElement HomePageRegisterBtn;
	
	@FindBy (xpath="//input[@id='gender-male']") 
	private WebElement genderBtn;
	
	@FindBy (xpath="//input[@id='FirstName']") 
	private WebElement FirstName;
	
	@FindBy (xpath="//input[@id='LastName']") 
	private WebElement LastName;
	
	@FindBy (xpath="//input[@id='Email']") 
	private WebElement Email;
	
	@FindBy (xpath="//input[@id='Password']") 
	private WebElement Password;
	
	@FindBy (xpath="//input[@id='ConfirmPassword']") 
	private WebElement ConfirmPassword;
	
	@FindBy (xpath="//input[@id='register-button']") 
	private WebElement registerBtn;
	
	@FindBy (xpath="//div[normalize-space(text())='Your registration completed']") 
	private WebElement registerSuccessMsg;
	
	@FindBy (xpath="//input[contains(@class,'button-1 register-continue-button')]") 
	private WebElement continueBtn;
	
	@FindBy(xpath="//li[text()='The specified email already exists']")
	private WebElement userAlreadyExistMsg;
	
	@FindBy(xpath="//span[@class='field-validation-error']/child::span")//First,Last,Email,Pass
	private WebElement AllErrorWarningMsg;
	
	public void clickOnRegister() {
		logger.info("**Clicking on HomePageRegisterBtn***");
		HomePageRegisterBtn.click();
	}
	
	public void clickOnGenderBtn() {
		logger.info("**Clicking on genderBtn***");
		genderBtn.click();
	}
	
	public void enterRegisterDetails(String FistName,String LastName, String EmailAddress,String Password) {
		logger.info("Entering Register Credentials");
		
	    this.FirstName.sendKeys(FistName);
		this.LastName.sendKeys(LastName);
		Email.sendKeys(EmailAddress);
		this.Password.sendKeys(Password);
		this.ConfirmPassword.sendKeys(Password);
		registerBtn.click();
	}
	
	public boolean isRegisterSuccessmsgDisplayed() {
		logger.debug("Verifying Is Register Success Message Displayed");
		return registerSuccessMsg.isDisplayed();
	}
	
	public void clickOnContinue() {
		logger.info(" **Clicking on Continue Btn** ");
		continueBtn.click();
	}
	
	public String getActualUserAlreadyExistMsgtext() {
		logger.debug("Getting ActualUserAlreadyExistMsgtext ");
		return userAlreadyExistMsg.getText();
	}
	
	public boolean isAllErrorWarningMsgDisplayed() {
		logger.debug("Checking if AllErrorWarningMsgDisplayed");
		return AllErrorWarningMsg.isDisplayed();
	}
}
