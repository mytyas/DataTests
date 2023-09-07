package BaseUtilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Functions {
    protected WebDriver driver;
    protected WebDriverWait waitForElement;

    public Functions(WebDriver driver) {
        this.driver = driver;
        this.waitForElement = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //This method will wait for element to be clickable, and than click on it, for failure it will show NoSuchElementException with element name
    protected void clickWithWait(By locator, String elementName) {
        try {
            waitForElementToBeClickable(locator);
            click(locator);
        } catch (NoSuchElementException e) {
            Assert.fail("Element " + elementName + " with locator " + locator + " is not visible on page.");
        }
    }

    // ------------>     HELPER METHODS     <----------------------
    //Helper methods are used without try/catch

    //This method will get title of the page
    public String getTitle() {
        return driver.getTitle();
    }

    //This method will click on previosly defined locator
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    //This method will wait for element to be visible (seconds are defined in constructor of Functions class)
    protected void waitForElementToBeVisible(By locator) {
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //This method will wait for element to be clickable (seconds are defined in constructor of Functions class)
    protected void waitForElementToBeClickable(By locator) {
        waitForElement.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //This method will wait for element to be present in HTML DOM (seconds are defined in constructor of Functions class)
    protected void waitForElementToBePresent(By locator) {
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ------------>    END OF HELPER METHODS     <----------------------

    //This method will send desired key combination for element, and for failure it will show NoSuchElementException with element name
    protected void sendKeys(By locator, String text, String elementName) {
        try {
            waitForElementToBePresent(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        } catch (NoSuchElementException|IllegalArgumentException e) {
            Assert.fail("Could not find " + elementName + " with locator " + locator + ", or property from dictionary is not found");
        }
    }

    //This method will click on desired element of a drop down, for failure it will show NoSuchElementException with element name
    protected void dropDownSelect(By locator, String text, String elementName) {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(text);
        } catch (NoSuchElementException|NullPointerException e) {
            Assert.fail("Could not find " + elementName + " with locator " + locator + ", or property from dictionary is not found");
        }

    }

    protected String readDropdownMenu(By locator){
        return driver.findElement(locator).getText();
    }

    //This method will return the boolean value (True or False) for display property of some element
    //For failure it will show NoSuchElementException or TimeOutException with element name
    protected Boolean elementIsDisplayed(By locator, String elementName) {
        try {
            try {
                waitForElementToBePresent(locator);
            } catch (TimeoutException |NullPointerException e) {
                Assert.fail("Could not find " + elementName + " with locator " + locator);
            }
            return driver.findElement(locator).isDisplayed();

        } catch (NoSuchElementException e) {
            Assert.fail("Could not find " + elementName + " with locator " + locator);
            return null;
        }
    }

    //This method will get inner text of an element
    protected void getPageSource() {
       String p = driver.getPageSource();
       System.out.println(p);

    }
    protected void waitForUrl(String url){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(url));
        }



}


