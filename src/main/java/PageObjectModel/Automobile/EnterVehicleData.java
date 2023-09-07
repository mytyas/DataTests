package PageObjectModel.Automobile;

import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EnterVehicleData extends Functions {

    // Locators
    final private By make = By.id("make");
    final private By numberOfSeats = By.id("numberofseats");
    final private By fuel = By.id("fuel");
    final private By automobileButton = By.xpath("(//a[@id='nav_automobile'])[1]");
    String url = "http://sampleapp.tricentis.com/101/index.php";
    String source;

    public EnterVehicleData(WebDriver driver) {
        super(driver);
    }

    public void goToPage(){
    driver.get(url);
    driver.manage().window().maximize();
    }
    public void goToAutomobile(){
    driver.findElement(automobileButton).click();
    }

    public void getSource(){
        getPageSource();
    }


    public void readField(String readString){
        readDropdownMenu(make);
    }


    public void numberOfSeatsField(String numberOfSeatsString) {
        dropDownSelect(numberOfSeats, numberOfSeatsString, "Number of seats field");
    }

    public void fuelField(String fuelString) {
        dropDownSelect(fuel, fuelString, "Fuel input field");
    }
}

