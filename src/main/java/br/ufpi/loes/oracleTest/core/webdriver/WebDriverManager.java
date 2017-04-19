package br.ufpi.loes.oracleTest.core.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import br.ufpi.loes.oracleTest.core.graph.Event;
import br.ufpi.loes.oracleTest.core.graph.State;

public class WebDriverManager {
	private DesiredCapabilities caps;
	private WebDriver driver;
	
	
	public WebDriverManager() {
	}
	
	private WebDriver loadWebDriver(){
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
		return new PhantomJSDriver(caps);
	}
	
	private void closeDriver(WebDriver driver){
		driver.close();
		driver.quit();
	}
	public int countVisibleElements(String url){
		WebDriver driver = loadWebDriver();
		driver.get(url);
		WebElement element = driver.findElement(By.tagName("body"));
		List<WebElement> children = element.findElements(By.xpath(".//*"));

		int count = 0;
		for (WebElement webElement : children) {
			if (webElement.isDisplayed()) {
				count++;
			}
		}
		closeDriver(driver);
		return count;
	}
	
	public Boolean checkAction(Event event, State target){
		this.driver = loadWebDriver();
		
		driver.get(event.getUrl());
		WebElement element = driver.findElement(By.xpath(event.getElement()));
		if(element != null){
			executeAction(element, event.getEventType());
			if(driver.getCurrentUrl().equals(target.getCurrentUrl())){
				if(countVisibleElements(this.driver.getCurrentUrl()) != target.getCountVisibleElements() ){
					System.out.println("Possível problema encontrado, numero de elementos visíveis diferentes!" );
					closeDriver(this.driver);
					return false;
				}
				closeDriver(this.driver);
				return true;
			}else{
				System.out.println("Possível problema encontrado, páginas diferentes!" );
				closeDriver(this.driver);
				return false;
			}
		}else{
			System.out.println("Elemento não encontrado");
			closeDriver(this.driver);
			return false;
		}
		
	}

	private void executeAction(WebElement element, String eventType) {
		switch (eventType) {
			case "click" :
				element.click();
				break;
				
			case "dblclick":
				element.click();
				element.click();
				break;
				
			case "mouseover":
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
				
			default :
				break;
		}
	}
}
