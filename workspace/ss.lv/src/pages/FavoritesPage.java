package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoritesPage extends SsLvPage {
	// locators of page; can be also be keeped in property file
	static final String NO_RESULT_XPATH = "//*[contains(@style,'border:1px #418c4e solid')]";
	static final String RESULT_CHECKBOXES_XPATH = "//td[contains(@class,'msga2')]/input[@type='checkbox']";

	public FavoritesPage(WebDriver driver) {
		super(driver);
		// add waiting until results will be downloaded or no-result found
		waitForSeconds(2);
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElements(By.xpath(NO_RESULT_XPATH)).size() > 0
						|| webDriver.findElements(By.xpath(RESULT_CHECKBOXES_XPATH)).size() > 0;
			}
		});

	}

}
