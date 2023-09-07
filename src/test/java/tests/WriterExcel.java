package tests;

import BaseUtilities.Base;
import PageObjectModel.Automobile.EnterVehicleData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.io.File;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriterExcel extends Base {

    @Test
    public void writeXLSXFileTest() throws Exception {
        EnterVehicleData enterVehicleData = new EnterVehicleData(driver);
        enterVehicleData.goToPage();
        enterVehicleData.goToAutomobile();

        //Create a blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("DropdownMenus");

        //List the dropdowns on a page
        List<WebElement> listOfElements=driver.findElements(By.xpath("//select"));

        //Count a number of the dropdowns on a page
        int xpathCount = listOfElements.size();

        //Print the number of the dropdowns on a page
        System.out.println("Number of the dropdown menus: "+ xpathCount);

        //Create an array of dropdown menus (size = number of dropdown menus)
        String[] ids=new String[xpathCount];

        //Fill the array with id values of dropdown elements
        System.out.println("List of the dropdown elements:");
        for(WebElement ele:listOfElements)
        {
            ids[listOfElements.indexOf(ele)]=ele.getAttribute("id"); /**gets the id of each element from the
         WebElement list and writes it into the array of string type. Index of an array element is index of a webelement
         in the Weblist**/
            System.out.println((listOfElements.indexOf(ele)+1)+". "+ele.getAttribute("id")); /**Prints the id of an webelement from the Webelement list **/
        }

        //Create k rows in a sheet. k is equal to the number od dropdown elements
        for (int k = 0; k < xpathCount; k++) {
            Select dropdown = new Select(driver.findElement(By.id(ids[k]))); /**locate the k-th dropdown menu on the webpage**/
            List<WebElement> dd = dropdown.getOptions(); /**get contents of the k-th dropdown menu, write a list of them**/
            XSSFRow row = sheet.createRow(k); /** create k-th row corresponding to k-th dropdown menu**/
            System.out.println("Size of " + ids[k]+" dropdown: "+dd.size()); /**prints the size of k-th dropdown menu**/
            for (int j = 1; j < dd.size(); j++) {
                //Create j columns, corresponding to k-th dropdown menu size
                //Specific row number
                row.createCell(0).setCellValue(ids[k]); /** write k-th dropdown menu title into the sheet row**/
                row.createCell(j).setCellValue(dd.get(j).getAttribute("value")); /**write the values of k-th
                 dropdown menu into the cells**/
            }}

            try {
                //Write the workbook in file system
                FileOutputStream out = new FileOutputStream(new File("DropdownMenus.xlsx"));
                workbook.write(out);
                out.close();
                System.out.println("DropdownMenus.xlsx written successfully on disk.");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

