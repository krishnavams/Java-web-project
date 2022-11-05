package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.TreeMap;
import java.util.concurrent.TimeoutException;

public class SeleniumCore {
    WebDriver driver;
    WebDriverWait webdriver_wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    JavascriptExecutor script = (JavascriptExecutor) driver;
    Actions action = new Actions(driver);
    SoftAssert soft_assert = new SoftAssert();

    SeleniumCore(WebDriver Driver) {
        this.driver = driver;
    }

    public WebElement getElement(String locator, String by_type) {
        WebElement web_element;
        if (by_type.toLowerCase().equals("css")) {
            web_element = driver.findElement(By.cssSelector(locator));
        } else if (by_type.toLowerCase().equals("id")) {
            web_element = driver.findElement(By.id(locator));
        } else if (by_type.toLowerCase().equals("classname")) {
            web_element = driver.findElement(By.className(locator));
        } else if (by_type.toLowerCase().equals("name")) {
            web_element = driver.findElement(By.name(locator));
        } else if (by_type.toLowerCase().equals("partiallinktext")) {
            web_element = driver.findElement(By.partialLinkText(locator));
        } else if (by_type.toLowerCase().equals("linktext")) {
            web_element = driver.findElement(By.linkText(locator));
        } else if (by_type.toLowerCase().equals("tagname")) {
            web_element = driver.findElement(By.tagName(locator));
        } else {
            web_element = driver.findElement(By.xpath(locator));
        }
        return web_element;
    }

    public void clickElement(String locator, String by_type) {
        getElement(locator, by_type).click();
    }

    public void jsClickElement(String locator, String by_type) {
        script.executeScript("aruguments[0].click()", getElement(locator, by_type));
    }

    public void sendKeys(String locator, String by_type, String value) {
        getElement(locator, by_type).sendKeys(value);
    }

    public WebElement getText(String locator, String by_type) {
        return getElement(locator, by_type);
    }

    public void scrollToElement(String locator, String by_type) {
        script.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});",
                getElement(locator, by_type));
    }

    public Boolean elementVisible(String locator, String by_type) {
        try {
            if (by_type.toLowerCase().equals("css")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
            } else if (by_type.toLowerCase().equals("id")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
            } else if (by_type.toLowerCase().equals("classname")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
            } else if (by_type.toLowerCase().equals("name")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
            } else if (by_type.toLowerCase().equals("partiallinktext")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locator)));
            } else if (by_type.toLowerCase().equals("linktext")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
            } else if (by_type.toLowerCase().equals("tagname")) {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
            } else {
                webdriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void moveToElement(String locator, String by_type) {
        action.moveToElement(getElement(locator, by_type)).perform();
    }
    public void openNewWindow(){
        script.executeScript("window.open()");
    }
    public void clickVisibleElement(String locator, String by_type){
//        soft_assert.assertEquals(elementVisible(locator, by_type), true);
        clickElement(locator, by_type);
    }
}
