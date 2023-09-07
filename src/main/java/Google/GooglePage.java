package Google;

import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends Functions {
    String url = "https://www.google.com";
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void openPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void sendText(By locator, String text){
        sendKeys(locator,text,"element");

    }
    public void validateVisible(By locator){
        waitForElementToBeVisible(locator);
        System.out.println(locator.toString()+" is visible");
    }
    public void getResultsText(By locator, String results){
    results = driver.findElement(locator).getText();
        System.out.println(results);
    }

}
