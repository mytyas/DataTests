package Godaddy;

import BaseUtilities.Functions;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.bouncycastle.tsp.TSPUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.w3c.dom.ls.LSOutput;

import java.util.Locale;

public class GoDaddyPage extends Functions {
    String url = "https://www.godaddy.com/";
    public GoDaddyPage(WebDriver driver) {
        super(driver);
    }

    public void openPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }
    public void goToPage(String url1){
        driver.get(url1);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }
    public String getSrc(){
        return driver.getPageSource().replace("&amp;","&");
    }

    public void validate(String exp, String act){
        Assert.assertEquals(act,exp);
    }
    public void validatePart(String total, String part){
        Assert.assertTrue(total.contains(part));
    }
    public void validateVisible(By locator){
        waitForElementToBeVisible(locator);
        System.out.println(locator.toString()+" is visible");
    }
    public void validateUrl(String url){
        waitForUrl(url);
    }
    public void sendText(By locator, String text){
        sendKeys(locator,text,"element");

    }
    public void getLocatorText(By locator, String text){
        text = driver.findElement(locator).getText();
        System.out.println(text);
    }






}
