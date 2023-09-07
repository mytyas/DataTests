package ComputerDatabase;

import BaseUtilities.Functions;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComputerDatabasePage extends Functions {
    public ComputerDatabasePage(WebDriver driver) {
        super(driver);
    }
    String url = "https://computer-database.gatling.io/computers";


    public void openPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void sendText(By locator, String text){
        sendKeys(locator,text,"element");

    }
    public void validateVisible(By locator){
       elementIsDisplayed(locator,"element");
            System.out.println(locator.toString()+" is visible");
       };

    public String getResultsText(By locator){
        return driver.findElement(locator).getText();
    }

    public void filterByName(String name, By locator1, By locator2){
        sendText(locator1, name);
        click(locator2);
    }

    public void addNewComputer(By locator){
        click(locator);
    }
    public int getNumberOfRowsBody(){
        return driver.findElements(By.xpath("//table/tbody/tr/td[1]")).size();
    }

    public int getNumberOfColumnsBody() {
        return driver.findElements(By.xpath("//table/tbody/tr[1]/td")).size();
    }
    public String validateClickable(By locator)
    {
          return driver.findElement(locator).getAttribute("href");
           }
           public void selectCompany(By locator, String text, String elementName){
        dropDownSelect(locator, text, elementName);
           }
}
