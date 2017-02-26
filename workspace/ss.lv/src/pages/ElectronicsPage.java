/*
 * 
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElectronicsPage extends SsLvPage{
	//locators of page; can be also be keeped in property file
	static final String SEARCH_XPATH = "//a[@href='/ru/electronics/search/']";

	public ElectronicsPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Open advanced search.
	 *
	 * @return the advanced search page
	 */
	public AdvancedSearchPage openAdvancedSearch(){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_XPATH))).click();
		return new AdvancedSearchPage(driver);
	}

}
