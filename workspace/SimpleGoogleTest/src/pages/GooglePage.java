package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
	// all constants can be also saved in property file
	public static final String GOOGLE_URL = "http://www.google.com.ua";
	public static final String SEARCH_FIELD_ID = "lst-ib";
	public static final String SEARCH_RESULTS_XPATH = "//div[@class='g']//*[@class='r']/a";
	public static final String NO_RESULTS_XPATH = "//*[contains(@class,'med card-section')]";
	public static final int WAIT_TIMEOUT = 10;

	private WebDriver driver;

	public GooglePage(WebDriver driver) {
		this.driver = driver;
		driver.get(GOOGLE_URL);
		// add waiting until google page will be downloaded and search field be
		// available
		new WebDriverWait(driver, WAIT_TIMEOUT)
				.until(ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_FIELD_ID)));
	}

	public GooglePage performSearch(String inputText) {
		WebElement element = driver.findElement(By.id(SEARCH_FIELD_ID));
		element.sendKeys(inputText);
		element.sendKeys(Keys.RETURN);
		// add waiting until google results will be downloaded or no-result
		// found
		new WebDriverWait(driver, WAIT_TIMEOUT).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElements(By.xpath(NO_RESULTS_XPATH)).size() > 0
						|| webDriver.findElements(By.xpath(SEARCH_RESULTS_XPATH)).size() > 0;
			}
		});
		return this;
	}

	public List<WebElement> getSearchResults() {
		return driver.findElements(By.xpath(SEARCH_RESULTS_XPATH));
	}

}
