package GooglePageTests;

import BaseUtilities.Base;
import Google.GooglePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class GoogleTests extends Base {
        final By googleSearch = By.name("q");
        final By ajaxBox = By.xpath("//div[@class='erkvQe']");
        final By ajaxBoxResults = By.xpath("//div[@class='erkvQe']/descendant::*");
        String ajaxBoxResultsText;

    @Test /**This test sends keys to google search field and returns all ajax results*/
    public void ajaxTest(){
        GooglePage googlePage = new GooglePage(driver);
        googlePage.openPage();
        googlePage.sendText(googleSearch,"beograd");
        googlePage.validateVisible(ajaxBox);
        googlePage.getResultsText(ajaxBoxResults, ajaxBoxResultsText);
    }
}
