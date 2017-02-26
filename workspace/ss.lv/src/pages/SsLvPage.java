package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//This is super class for all pages and contain common fields
public abstract class SsLvPage {
	WebDriver driver;
	WebDriverWait wait;

	public SsLvPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}
	
	//need to avoid execution of this method
	//but in some case where explicit and implicit waiting doesn't stable can be used
	public void waitForSeconds(int i){
		try {
			Thread.sleep(1000*i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
