package helpers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

public class HelperFunctions {

	protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
	protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal <String> platform = new ThreadLocal<String>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
	private static AppiumDriver server;

	private WebDriver webdriver;
    public void ElementHelper(WebDriver driver) {
        this.webdriver = webdriver;
    }

	  public AppiumDriver getDriver() {
		  return driver.get();
	  }
	  
	  public void setDriver(AppiumDriver driver1) {
		  driver.set(driver1);
	  }
	  
	  public Properties getProps() {
		  return props.get();
	  }
	  
	  public void setProps(Properties props2) {
		  props.set(props2);
	  }
	  
	  public HashMap<String, String> getStrings() {
		  return strings.get();
	  }
	  
	  public void setStrings(HashMap<String, String> strings2) {
		  strings.set(strings2);
	  }
	  
	  public String getPlatform() {
		  return platform.get();
	  }
	  
	  public void setPlatform(String platform2) {
		  platform.set(platform2);
	  }
	  
	  public String getDateTime() {
		  return dateTime.get();
	  }
	  
	  public void setDateTime(String dateTime2) {
		  dateTime.set(dateTime2);
	  }
	  
	  public String getDeviceName() {
		  return deviceName.get();
	  }
	  
	  public void setDeviceName(String deviceName2) {
		  deviceName.set(deviceName2);
	  }
	  
	  public <T> void waitForVisibility(MobileElement e) {
		  Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
		   .withTimeout(Duration.ofSeconds(30))
		   .pollingEvery(Duration.ofSeconds(5))
		   .ignoring(NoSuchElementException.class);
		   wait.until((Function<? super WebDriver, T>) ExpectedConditions.visibilityOf((WebElement) e)); 
	  }
	  
	  public void clear(MobileElement e) {
		  waitForVisibility(e);
		  e.clear();
	  }
	  
	  public void click(MobileElement e, String msg) {
		  waitForVisibility(e);
		  e.click();
	  }
	  
	  public void sendKeys(MobileElement e, String txt) {
		  waitForVisibility(e);
		  e.sendKeys(txt);
	  }
	  
	  public MobileElement scrollToElement() {	  
		  WebElement element = ((WebDriver) driver).findElement(By.id("my-id"));
		  Actions actions = new Actions((WebDriver) driver);
		  actions.moveToElement(element);
		  actions.perform();
		  return (MobileElement) element;
	  }
	  
	  public void closeApp() {
//		  ((InteractsWithApps) getDriver()).closeApp();
	  }
	  
	  public void launchApp() {
//		  ((InteractsWithApps) getDriver()).launchApp();
	  }
	  
	  public String getElementColorCode(WebElement webElement) {
	        String colorCode = webElement.getCssValue("color");
	        return Color.fromString(colorCode).asHex();
	    }
	  
	  public <V> boolean isElementDisplayed(WebElement element) {

	        try {
	            WebDriverWait wait = new WebDriverWait(webdriver, (Long) null);
	            wait.until((Function<? super WebDriver, V>) ExpectedConditions.visibilityOf(element));
	            return element.isDisplayed();
	        } catch (NoSuchElementException| TimeoutException e) {
	            return false;
	        }
	    }
	   
	  public void selectValueFromDropDown(WebElement dropDownElement, String dropDownValue) {
	        Select dropDown = new Select(dropDownElement);
	        dropDown.selectByVisibleText(dropDownValue);
	    }
	  
	  public String getValueFromDropDown(WebElement dropDownElement, int optionIndex) {
	        Select dropDown = new Select(dropDownElement);
	        List<WebElement> options = dropDown.getOptions();
	        return options.get(optionIndex).getText();
	    }
	  
	  public List<String> getAllOptionsFromDropDown(WebElement dropDownElement) {
	        Select dropDown = new Select(dropDownElement);
	        List<WebElement> allOptions = dropDown.getOptions();
	        allOptions.remove(dropDown.getFirstSelectedOption());
	        List<String> allDropDownValues = new ArrayList<>();
	        for (WebElement ele : allOptions) {
	            allDropDownValues.add(ele.getText());
	        }
	        return allDropDownValues;
	    }
	  
	    public List<String> getAllElementsText(List<WebElement> webElements) {
	        List<String> elementsText = new ArrayList<String>();

	        for (WebElement we : webElements) {
	            elementsText.add(we.getText());
	        }
	        return elementsText;
	    }

	    public void mouseHover(WebElement webElement) {
	        Actions action = new Actions((WebDriver) driver);
	        action.moveToElement(webElement).build().perform();
	    }
}
