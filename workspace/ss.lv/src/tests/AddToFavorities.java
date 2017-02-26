package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.SearchResultsPage;

public class AddToFavorities {

	WebDriver driver;

	// will set SystemProperty if it's not set. Path depends on machine
	@BeforeClass
	public static void setProperty() {
		System.setProperty("webdriver.gecko.driver", "D:\\SeleniumWebDriver\\geckodriver.exe");

	}

	@Before
	public void launchBrowser() {
		// launch new browser
		driver = new FirefoxDriver();
		// maximize browser
		driver.manage().window().maximize();
	}

	@Test
	public void addToFavorites() throws InterruptedException {

		SearchResultsPage resultsPage = new HomePage(driver)
				.setLanguageRussion() //switch to Russian language
				.openElectronicsPage() //go to the section “Электротехника”
				.openAdvancedSearch()  //open search page
				.fillSearchTextInput("Компьютер") //enter the search phrase
				.selectRegion("Рига") //select region search parameter 
				//NOTE there are bug this parameter is not showing correct in search results
				.performSearch() //click search
				.sortByPriceAsc() //sort the results by price ascending 
				.setDealType("Продажа") //select option 'Продажа' in "Тип сделки" dropdown
				.openAdvancedSearch() //open “Расширенный поиск”
				.fillMaxPrice("300")//enter search option price between 160 and 300
				.fillMinPrice("160")
				.performSearch()
				.selectRandomResults(3) //choose 3 random ads.
				.addToFavorites() //press “Добавить выбранные в закладки”
				.confirmDialog()
				.scrollPageToTop();//scroll page to top to avoid accidental clicking on advertisement 

		// Open “Закладки” and check that the ads on the page match the previously selected
		resultsPage.openFavotitesPage();

		for (String id : resultsPage.getSelectedIds()) {
			Assert.assertTrue("Page consists added element",
					driver.findElement(By.xpath("//*[@id='tr_" + id + "']")).isDisplayed());
		}

	}

	@After
	public void closeBroser() {
		// close the browser
		if (driver != null)// check for null to avoid NullPointerException
			driver.quit();
	}

}
