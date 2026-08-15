package com.demowebshop.pages;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;

public class SearchPage_TC3 {
	private WebDriver driver;
	private Logger logger=LogManager.getLogger(this.getClass());
	
	public SearchPage_TC3(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//input[@id='small-searchterms']")
	private WebElement SearchBar;
	
	@FindBy(xpath="//input[contains(@class,'button-1 search-box-button')]")
	private WebElement SearchBtn;
	
	@FindBy(xpath="//a[text()='Build your own cheap computer']")
	private WebElement computerDisplayMsg;
	
	@FindBy(xpath="//strong[normalize-space(text())='No products were found that matched your criteria.']")
	private WebElement invalidProductMsg;
	
	@FindBy(xpath="//div[@class='product-item']")
	private WebElement searchResult;
	
	public void SearchingProduct(String productName) {
		logger.info("**Searching with Product Name**");
		SearchBar.sendKeys(productName);
		
		logger.info("**Clicking on Search Btn**");
		SearchBtn.click();
	}
	
	public boolean iscomputerDisplayed() {
		logger.debug("*Computer is Displayed on HomePage*");
		return computerDisplayMsg.isDisplayed();
	}
	
	public boolean isinvalidProductMsgDisplayed() {
		logger.debug("*invalidProductMsg is displayed on HomePage");
		return invalidProductMsg.isDisplayed();
	}
	
	public String getActualAlertMsg() {
		logger.debug("Getting Alert Text");
		Alert a = driver.switchTo().alert();
		return a.getText();
	}
	
	public void acceptSearchAnyProductAlert() {
		logger.info("Accepting the Alert");
		driver.switchTo().alert().accept();
	}
	
	public boolean isSearchResultsDisplayed() {
		logger.debug("Checking if Search Results are Displayed,Applicable for All Products");
		return searchResult.isDisplayed();
	}
	
	
}
