package Tables;

import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TablePage extends Functions {
    public TablePage(WebDriver driver) {
        super(driver);}

    public void openPage(){
        driver.manage().window().maximize();
        driver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
    }

    public int getNumberOfRows(){
            return driver.findElements(By.xpath("//table[@id='customers']/tbody/tr")).size();
        }

    public int getNumberOfColumns(){
        return driver.findElements(By.xpath("//table[@id='customers']/tbody/tr/th")).size();
    }
}
