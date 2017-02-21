package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.GooglePage;

public class googleSearchTest {

	WebDriver driver;

	//will set SystemProperty if it's not set. Path depends on machine
	@BeforeClass 
	public static void setProperty() {
		System.setProperty("webdriver.gecko.driver", "D:\\SeleniumWebDriver\\geckodriver.exe");

	}

	@Before
	public void launchBrowser() {
		//launch new browser
		driver = new FirefoxDriver(); 
		//set implicit wait for page downloading
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); 
	}

	@Test
	public void testGoogleSearch() throws InterruptedException {

		//get search results using page object pattern
		List<WebElement> searchResults = new GooglePage(driver)
				.performSearch("Ortnec")
				.getSearchResults();
		
		//initialize actual result with empty String
		String actualResult = "";
		if (searchResults.size() > 0) {
			actualResult = searchResults.get(0).getAttribute("href");
		}

		//Check that first search result is "http://ortnec.com/"
		Assert.assertEquals("First search result", "http://ortnec.com/", actualResult);

		//Check that other results in the first page contains Ortnec 
		for (int i = 0; i < searchResults.size(); i++) {
			//open every link in a new tab
			searchResults.get(i).sendKeys(Keys.chord(Keys.CONTROL,Keys.RETURN));;
			//getAlltabs
		    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    //swith to second opened tab
		    driver.switchTo().window(tabs.get(1));
		   //check 'ortnec' text should be displayed on the page use XPath for case insensitive search
			Assert.assertTrue("Page consists element with ortnec text", driver.findElements(By.xpath("//*[contains(translate(text(),'ORTNEC','ortnec'), 'ortnec')]")).size() != 0);
			//close tab
		    driver.close();
		    //go back to the first google search result tab
			driver.switchTo().window(tabs.get(0));

		}
	}

	@After
	public void closeBroser() {
		//close browser
		if (driver != null)//check for null to avoid NullPointerException 
			driver.quit();
	}

}
