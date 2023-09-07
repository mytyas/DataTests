package THzDatabase;

import BaseUtilities.Functions;
import org.openqa.selenium.WebDriver;
public class THzDatabasePage extends Functions{

    String url ="http://www.thzdb.org/";
    public THzDatabasePage(WebDriver driver) {
        super(driver);
    }
    public void goToPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }
    public void goToPage(String url1){
        driver.get(url1);
    }
}
