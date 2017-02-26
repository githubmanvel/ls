package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends SsLvPage {

	// locators of page; can be also be keeped in property file
	static final String ORDER_PRICE_ASC_XPATH = "//a[@href='/ru/electronics/search-result/fDgSeF4belM=.html']";
	static final String DEAL_TYPE_XPATH = "(//select[contains(@class,'filter_sel')])[3]";
	static final String NO_RESULT_XPATH = "//*[contains(@style,'border:1px #418c4e solid')]";
	static final String RESULT_CHECKBOXES_XPATH = "//td[contains(@class,'msga2')]/input[@type='checkbox']";
	static final String SEARCH_XPATH = "//a[@href='/ru/electronics/search/']";
	static final String FAVORITES_XPATH = "//a[@href='/ru/favorites/']";
	static final String ADD_FAVORITES_ID = "a_fav_sel";
	static final String CONFIRMATION_ID = "alert_ok";

	// list with selected record ids
	private List<String> selectedIds = new ArrayList<>();

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		// add waiting until results will be downloaded or no-result found
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElements(By.xpath(NO_RESULT_XPATH)).size() > 0
						|| webDriver.findElements(By.xpath(RESULT_CHECKBOXES_XPATH)).size() > 0;
			}
		});
	}

	/**
	 * Gets the selected ids.
	 *
	 * @return the selected ids
	 */
	public List<String> getSelectedIds() {
		return selectedIds;
	}

	/**
	 * Sort by price ascending.
	 *
	 * @return the search results page
	 */
	public SearchResultsPage sortByPriceAsc() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ORDER_PRICE_ASC_XPATH))).click();
		return this;
	}

	/**
	 * Sets the deal type.
	 *
	 * @param visibleText the visible text
	 * @return the search results page
	 */
	public SearchResultsPage setDealType(String visibleText) {
		new Select(driver.findElement(By.xpath(DEAL_TYPE_XPATH))).selectByVisibleText(visibleText);
		return this;
	}

	/**
	 * Open advanced search.
	 *
	 * @return the advanced search page
	 */
	public AdvancedSearchPage openAdvancedSearch() {
		driver.findElement(By.xpath(SEARCH_XPATH)).click();
		return new AdvancedSearchPage(driver);
	}

	/**
	 * Select random results from first page
	 *
	 * @param count the count
	 * @return the search results page
	 */
	public SearchResultsPage selectRandomResults(int count) {
		Random randomGenerator = new Random();
		List<WebElement> checkBoxes = driver.findElements(By.xpath(RESULT_CHECKBOXES_XPATH));
		//check if there are search results 
		if (checkBoxes.size() > 0) {

			for (int i = 0; i < count;) {
				//get random number of result which is less then results count on first oage
				int rand = randomGenerator.nextInt(checkBoxes.size());
				//select only if it not already selected
				if (!checkBoxes.get(rand).isSelected()) {
					checkBoxes.get(rand).click();
					selectedIds.add(checkBoxes.get(rand).getAttribute("id").substring(1));
					i++;
				}
			}
		}
		return this;
	}

	/**
	 * Adds the to favorites.
	 *
	 * @return the search results page
	 */
	public SearchResultsPage addToFavorites() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id(ADD_FAVORITES_ID))).click();
		return this;
	}

	/**
	 * Confirm dialog.
	 *
	 * @return the search results page
	 */
	public SearchResultsPage confirmDialog() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id(CONFIRMATION_ID))).click();
		return this;
	}

	/**
	 * Scroll page to top.
	 *
	 * @return the search results page
	 */
	public SearchResultsPage scrollPageToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		return this;
	}

	/**
	 * Open favotites page.
	 *
	 * @return the favorites page
	 */
	public FavoritesPage openFavotitesPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FAVORITES_XPATH))).click();
		return new FavoritesPage(driver);

	}

}
