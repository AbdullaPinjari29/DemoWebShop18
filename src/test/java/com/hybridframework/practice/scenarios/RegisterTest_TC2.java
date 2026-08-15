package com.hybridframework.practice.scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demowebshop.base.BaseClass;
import com.demowebshop.pages.RegisterPage_TC2;
import com.demowebshop.utility.Utilities;
@Test(groups={"Regression","Master"})
public class RegisterTest_TC2 extends BaseClass{

	RegisterPage_TC2 registerPage_TC2;
	
	@Test(priority=1)
	public void verifyRegisterwithALLField() throws Exception {
		registerPage_TC2 = new RegisterPage_TC2(driver);
		
		registerPage_TC2.clickOnRegister();
		registerPage_TC2.clickOnGenderBtn();
		registerPage_TC2.enterRegisterDetails(config.getFirstName(), config.getLastName(), Utilities.generateDynamicEmail(),config.getValidPassword());
		Assert.assertTrue(registerPage_TC2.isRegisterSuccessmsgDisplayed());
		registerPage_TC2.clickOnContinue();
		//Utilities.captureScreenShot(driver);
	}
	
	@Test(priority=2)
	public void verifyRegisterwithMandatoryField() throws Exception {
		registerPage_TC2 = new RegisterPage_TC2(driver);
		
		registerPage_TC2.clickOnRegister();
		registerPage_TC2.enterRegisterDetails(config.getFirstName(), config.getLastName(), Utilities.generateDynamicEmail(),config.getValidPassword());
		Assert.assertTrue(registerPage_TC2.isRegisterSuccessmsgDisplayed());
		registerPage_TC2.clickOnContinue();
	}
	
	@Test(priority=3)
	public void verifyRegisterwithExistingEmail() throws Exception {
        registerPage_TC2 = new RegisterPage_TC2(driver);
		
		registerPage_TC2.clickOnRegister();
		registerPage_TC2.clickOnGenderBtn();
		registerPage_TC2.enterRegisterDetails(config.getFirstName(), config.getLastName(), config.getEmailAddress(),config.getValidPassword());
		Assert.assertEquals(registerPage_TC2.getActualUserAlreadyExistMsgtext(),config.getExpectedUserAlreadyExistMsg());
	}
	
	@Test(priority=4)
	public void verifyRegisterwithoutProvidingCredentials() throws Exception {
        registerPage_TC2 = new RegisterPage_TC2(driver);
		
		registerPage_TC2.clickOnRegister();
		registerPage_TC2.enterRegisterDetails("","","","");
		Assert.assertTrue(registerPage_TC2.isAllErrorWarningMsgDisplayed());
	}
	
}
