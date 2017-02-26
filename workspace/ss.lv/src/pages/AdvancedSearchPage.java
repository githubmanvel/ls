package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class AdvancedSearchPage extends SsLvPage {
	
	//locators of page; can be also be keeped in property file
	static final String SEARCH_TEXT_ID = "ptxt";
	static final String SUBMIT_ID = "sbtn";
	static final String TITLE_XPATH = "//*[@class='headtitle']";
	static final String AUTOCOMLETE_POPUP_ID = "preload_txt";
	static final String REGION_ID  = "s_region_select";
	static final String MAX_PTICE_XPATH = "//input[contains(@name,'max')]";
	static final String MIN_PRICE_XPATH = "//input[contains(@name,'min')]";

	
	public AdvancedSearchPage(WebDriver driver) {
		super(driver);
		//find out until page be really downloaded by waiting one of the elements
		wait.until(ExpectedConditions.elementToBeClickable(By.id(SEARCH_TEXT_ID)));
	}

	
	/**
	 * Fill search text input.
	 *
	 * @param searchText the search text
	 * @return the advanced search page
	 */
	public AdvancedSearchPage fillSearchTextInput(String searchText) {
		driver.findElement(By.id(SEARCH_TEXT_ID)).sendKeys(searchText);
		
		//need this wait and click after it for removing autocomplete popup
		waitForSeconds(2);
		driver.findElement(By.xpath(TITLE_XPATH)).click();

		return this;

	}

	/**
	 * Select region.
	 *
	 * @param visibleText the visible text
	 * @return the advanced search page
	 */
	public AdvancedSearchPage selectRegion(String visibleText) {
		new Select(driver.findElement(By.id(REGION_ID))).selectByVisibleText(visibleText);
		return this;

	}

	/**
	 * Perform search.
	 *
	 * @return the search results page
	 */
	public SearchResultsPage performSearch() {
		driver.findElement(By.id(SUBMIT_ID)).click();
		return new SearchResultsPage(driver);
	}

	
	/**
	 * Fill min price.
	 *
	 * @param minPrice the min price
	 * @return the advanced search page
	 */
	public AdvancedSearchPage fillMinPrice(String minPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MIN_PRICE_XPATH))).sendKeys(minPrice);
		return this;
	}

	/**
	 * Fill max price.
	 *
	 * @param maxPrice the max price
	 * @return the advanced search page
	 */
	public AdvancedSearchPage fillMaxPrice(String maxPrice) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MAX_PTICE_XPATH))).sendKeys(maxPrice);
		return this;
	}

}
