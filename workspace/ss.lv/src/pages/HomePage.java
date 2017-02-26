package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends SsLvPage {
	
	static final String URL = "https://www.ss.lv/";
	// locators of page; can be also be keeped in property file
	static final String RU_XPATH = "//*[@href='/ru/']";
	static final String ELECTRONICS_XPATH = "//*[@class='a1' and @href='/ru/electronics/']";

	public HomePage(WebDriver driver) {
		super(driver);
		driver.get(URL);
	}

	/**
	 * Sets the language to Russian.
	 *
	 * @return the home page
	 */
	public HomePage setLanguageRussion() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(RU_XPATH))).click();
		return this;
	}

	/**
	 * Open electronics page.
	 *
	 * @return the electronics page
	 */
	public ElectronicsPage openElectronicsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ELECTRONICS_XPATH))).click();
		return new ElectronicsPage(driver);
	}

}
