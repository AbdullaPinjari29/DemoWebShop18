package com.hybridframework.practice.scenarios;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demowebshop.base.BaseClass;
import com.demowebshop.pages.SearchPage_TC3;

public class SearchTest_TC3 extends BaseClass{

	SearchPage_TC3 searchPage_TC3;
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		searchPage_TC3 = new SearchPage_TC3(driver);
		searchPage_TC3.SearchingProduct(config.getValidProductName());
		Assert.assertTrue(searchPage_TC3.iscomputerDisplayed());
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		searchPage_TC3 = new SearchPage_TC3(driver);
		searchPage_TC3.SearchingProduct(config.getInvalidProductName());
		Assert.assertTrue(searchPage_TC3.isinvalidProductMsgDisplayed());
	}
	
	@Test(priority=3)
	public void verifyEmptySearch() {
		searchPage_TC3 = new SearchPage_TC3(driver);
		searchPage_TC3.SearchingProduct("");
		Assert.assertEquals(searchPage_TC3.getActualAlertMsg(),config.getExpectedAlertMsg());
		searchPage_TC3.acceptSearchAnyProductAlert();
	}
	
	@Test(priority=4,dataProvider="ProductNameData")
	public void verifyingSearchWithDDT(String productNameDDT,boolean expectedSearchResult) throws Exception {
		searchPage_TC3 = new SearchPage_TC3(driver);
		
		searchPage_TC3.SearchingProduct(productNameDDT);
		Thread.sleep(4000);
		
		if(expectedSearchResult) {
			Assert.assertTrue(searchPage_TC3.isSearchResultsDisplayed());
		}
		else {
			Assert.assertTrue(searchPage_TC3.isinvalidProductMsgDisplayed());
		}
	}
	
	@DataProvider(name="ProductNameData")
	public Object[][] getData(){
		return new Object[][] {
			{"Desktop PC with CDRW",true},
			{"Computing and Internet",true},
			{"Digital SLR Camera 12.2 Mpixel",true},
			{"Smartphone",true},
			{"Black & White Diamond Heart",true},	
			{"invalidProductSearch",false}
		};
	}
	
}	
