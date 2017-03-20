/**
 * 
 */
package br.ufpi.loes.oracleTest.core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Ronyerison
 *
 */
public class TesteWebDriver {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		DesiredCapabilities caps = new DesiredCapabilities();
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
		WebDriver driver = new PhantomJSDriver(caps);

		driver.get("http://google.com");

		WebElement element = driver.findElement(By.tagName("body"));
		List<WebElement> children = element.findElements(By.xpath(".//*"));
		System.out.println("Elementos Filhos: " + children.size());
		List<WebElement> aux = new ArrayList<WebElement>();

		for (WebElement webElement : children) {
			if (webElement.isDisplayed()) {
				aux.add(webElement);
				System.out.println(webElement.getText() + " - "
						+ webElement.getAttribute("id") + " - "
						+ getElementXPath(driver, webElement));
			}
		}

		// WebElement e = driver.findElement(By.xpath("id(form:email)"));
		WebElement e = driver.findElement(By.id("form:email"));
		e.sendKeys("rony@rony.com");
		e.findElement(By.xpath("//*[@id='form:senha']"));
		e.sendKeys("123456");
		e.findElement(By.xpath("//*[@id='form:btnentrar']"));
		// e.findElement(By.id("form:btnentrar"));
		e.click();

		System.out.println(driver.getCurrentUrl());

		System.out.println("Elementos Exibidos: " + aux.size());

	}

	public static String getElementXPath(WebDriver driver, WebElement element) {
		// return
		// (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return
		// c.tagName}var a=0;var e=c.parentNode.childNodes;for(var
		// b=0;b<e.length;b++){var d=e[b];if(d===c){return
		// gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return
		// gPt(arguments[0]).toLowerCase();", element);
		return (String) ((JavascriptExecutor) driver).executeScript(
				"createXPathFromElement=function(a){var b=document.getElementsByTagName(\"*\");for(segs=[];a&&1==a.nodeType;a=a.parentNode)if(a.hasAttribute(\"id\")){for(var c=0,d=0;d<b.length&&(b[d].hasAttribute(\"id\")&&b[d].id==a.id&&c++,!(c>1));d++);if(1==c)return segs.unshift('id(\"'+a.getAttribute(\"id\")+'\")'),segs.join(\"/\");segs.unshift(a.localName.toLowerCase()+'[@id=\"'+a.getAttribute(\"id\")+'\"]')}else if(a.hasAttribute(\"class\"))segs.unshift(a.localName.toLowerCase()+'[@class=\"'+a.getAttribute(\"class\")+'\"]');else{for(i=1,sib=a.previousSibling;sib;sib=sib.previousSibling)sib.localName==a.localName&&i++;segs.unshift(a.localName.toLowerCase()+\"[\"+i+\"]\")}return segs.length?\"/\"+segs.join(\"/\"):null};return createXPathFromElement(arguments[0]).toLowerCase();",
				element);
	}

}
