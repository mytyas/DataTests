package tests;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


import BaseUtilities.Base;
import PageObjectModel.Automobile.EnterVehicleData;


import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import org.testng.annotations.Test;


public class WriterCSV extends Base {
    @Test /**This simple test selects all dropdown menus from the string array, gets the option values and stores them in separated csv files  */
    public void writeCSVFileTest() throws Exception {
        EnterVehicleData enterVehicleData = new EnterVehicleData(driver);
        enterVehicleData.goToPage();
        enterVehicleData.goToAutomobile();

        for (int k = 0; k < 9; k++) {


            String[] ids = new String[]{"make", "numberofseats", "fuel", "country", "occupation", "insurancesum", "meritrating", "damageinsurance", "courtesycar"};
            CSVWriter writecsv = new CSVWriter(new FileWriter(ids[k] + "_vehicle.csv"));


            Select dropdown = new Select(driver.findElement(By.id(ids[k])));

            //Get all options


            List<WebElement> dd = dropdown.getOptions();
            //Get the length
            System.out.println(dd.size());

            // Loop to print one by one
            writecsv.writeNext(new String[]{ids[k]});
            for (int j = 1; j < dd.size(); j++) {
                System.out.println(dd.get(j).getAttribute("value"));
                writecsv.writeNext(new String[]{dd.get(j).getAttribute("value")});
            }

            writecsv.close();
        }
    }
    }
