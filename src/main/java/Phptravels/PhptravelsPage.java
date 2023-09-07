package Phptravels;

import BaseUtilities.Functions;
import org.openqa.selenium.WebDriver;

public class PhptravelsPage extends Functions {

    String url = "https://phptravels.com/";

    public PhptravelsPage(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
        driver.get(url);
        driver.manage().window().maximize();
    }

}
