package br.ufpi.loes.oracleTest.core.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {
	private DesiredCapabilities caps;
	private WebDriver driver;
	
	
	public WebDriverManager() {
		loadWebDriver();
	}
	
	private void loadWebDriver(){
		caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);

		caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX
				+ "userAgent", "My User Agent - Chrome");
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
				new String[]{"--web-security=false", "--ssl-protocol=any",
						"--ignore-ssl-errors=true",
						"--webdriver-loglevel=DEBUG"});

		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"src//main//resources//phantomjs.exe");
		driver = new PhantomJSDriver(caps);
	}
	
	public int countVisibleElements(String url){
		driver.get(url);
		WebElement element = driver.findElement(By.tagName("body"));
		List<WebElement> children = element.findElements(By.xpath(".//*"));

		int count = 0;
		for (WebElement webElement : children) {
			if (webElement.isDisplayed()) {
				count++;
			}
		}
		return count;
	}
}
