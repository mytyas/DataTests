package ComputerDatabaseTests;

import BaseUtilities.Base;
import ComputerDatabase.ComputerDatabasePage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;

public class ComputerDatabaseTests extends Base {
    final By searchBox = By.id("searchbox");
    final By searchButton = By.id("searchsubmit");
    final By header = By.cssSelector("section[id='main'] h1");
    final By nextButton = By.xpath("//a[contains(text(),'Next →')]");
    final By addNewComputerButton = By.xpath("//a[@id='add']");
    final By computerNameField = By.name("name");
    final By introducedField = By.name("introduced");
    final By discontinuedField = By.name("discontinued");
    final By alertMessage = By.xpath("/html[1]/body[1]/section[1]/div[1]");
    final By chooseCompanyDropdown = By.xpath("//select[@id='company']");
    final By createThisComputerButton = By.xpath("//input[@value='Create this computer']");
    final By deleteThisComputerButton = By.xpath("//input[@value='Delete this computer']");
    final By saveThisComputer = By.xpath("//input[@value='Save this computer']");

    int Body_row_count;
    int Body_col_count;
    int counter = 1;
    int r = 1;

    String results;
    String href;


    @Test
    /**This test launches Computer database page https://computer-database.gatling.io/computers,
     passes a query (Filter by computer name) and returns an excel table with multiple sheets*/
    public void filterByNameTestMultipleSheets() {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver); //Instantiates a new object driver
        computerDatabasePage.openPage(); //Opens the web page
        computerDatabasePage.filterByName("IBM", searchBox, searchButton); //Enters the search text into the searchBox, and clicks on the searchButton
        computerDatabasePage.getResultsText(header); //Returns the header information - search results

        //Create workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        //This is a loop for creation of multiple sheets in a table. Will execute the code inside until href is empty, as a condition for a non-clickable Next button
        do {
            XSSFSheet sheet = workbook.createSheet("Search Output" + counter); //creates the counter-th sheet in the workbook

            //Table dimensions

            //Get number of rows In table body and prints them
            Body_row_count = computerDatabasePage.getNumberOfRowsBody();
            System.out.println("Number Of Rows In table body = " + Body_row_count);
            //Get number of columns In table body and prints them
            Body_col_count = computerDatabasePage.getNumberOfColumnsBody();
            System.out.println("Number Of Columns In table body = " + Body_col_count);

            //Creates k rows in a sheet. k is equal to the table row size

            for (int k = 0; k < Body_row_count + 1; k++) {
                XSSFRow row = sheet.createRow(k);  //Creates the k-th row in the sheet

                if (k == 0) {
                    for (int j = 0; j < Body_col_count; j++) {
                        row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/thead/tr/th[" + (j + 1) + "]")).getText()); //This the table header
                    }
                } else {
                    for (int j = 0; j < Body_col_count; j++) {
                        //Create j columns, corresponding to table column size
                        row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/tbody/tr[" + (k) + "]/td[" + (j + 1) + "]")).getText()); //Extracts the text from the web table and writes it into the cell
                    }
                }
            }
            href = computerDatabasePage.validateClickable(nextButton); //returns href value
            computerDatabasePage.click(nextButton); //clicks next button for the next page
            counter++; //increase counter variable value
        } while (href != null); //Repeats until Next button is disabled
        //
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("SearchResults.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("SearchResults.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**This test adds a new computer to the database and verifies it is added*/
    public void addNewComputer() {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver);
        computerDatabasePage.openPage();
        computerDatabasePage.click(addNewComputerButton);
        computerDatabasePage.sendText(computerNameField, "RCA Viking pro");
        computerDatabasePage.sendText(introducedField, "2016-02-01");
        computerDatabasePage.sendText(discontinuedField, "2017-02-01");
        computerDatabasePage.selectCompany(chooseCompanyDropdown, "RCA", "RCA");
        computerDatabasePage.click(createThisComputerButton);
        computerDatabasePage.validateVisible(alertMessage);
        System.out.println(computerDatabasePage.getResultsText(alertMessage));
    }

    @Test /**This test searches for a computer, clicks on it, deletes it and verifies the operation*/
    public void deleteComputer() {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver);
        computerDatabasePage.openPage();
        computerDatabasePage.filterByName("IBM", searchBox, searchButton);
        computerDatabasePage.click(By.xpath("//a[normalize-space()='IBM 1401']"));
        computerDatabasePage.click(deleteThisComputerButton);
        computerDatabasePage.validateVisible(alertMessage);
        System.out.println(computerDatabasePage.getResultsText(alertMessage));

    }

    @Test /**This test searches for a computer, clicks on it, updates it with additional info and verifies the operation*/
    public void updateComputer() {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver);
        computerDatabasePage.openPage();
        computerDatabasePage.filterByName("IBM", searchBox, searchButton);
        computerDatabasePage.click(By.xpath("//a[normalize-space()='IBM 1401']"));
        computerDatabasePage.sendText(discontinuedField, "1960-02-01");
        computerDatabasePage.click(saveThisComputer);
        System.out.println(computerDatabasePage.getResultsText(alertMessage));
    }

    @Test/**This test downloads the whole computer database and stores it in multiple sheet excel file, 10 results per sheet, as table dimensions are such in the web app */
    public void getWholeDatabaseMultipleSheets () {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver);
        computerDatabasePage.openPage();
        //Create workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        //This is a loop for creation of multiple sheets in a table. Will execute the code inside until href is empty, as a condition for a non-clickable Next button
        do {
            XSSFSheet sheet = workbook.createSheet("Search Output" + counter);

            //Table dimensions

            //Get number of rows In table body.
            Body_row_count = computerDatabasePage.getNumberOfRowsBody();
            System.out.println("Number Of Rows In table body = " + Body_row_count);
            //Get number of columns In table body.
            Body_col_count = computerDatabasePage.getNumberOfColumnsBody();
            System.out.println("Number Of Columns In table body = " + Body_col_count);

            //Create k rows in a sheet. k is equal to the table row size

            for (int k = 0; k < Body_row_count + 1; k++) {
                XSSFRow row = sheet.createRow(k);
                if (k == 0) {
                    for (int j = 0; j < Body_col_count; j++) {
                        row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/thead/tr/th[" + (j + 1) + "]")).getText());
                    }
                } else {
                    for (int j = 0; j < Body_col_count; j++) {
                        //Create j columns, corresponding to table column size
                        //Specific row number{
                        row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/tbody/tr[" + (k) + "]/td[" + (j + 1) + "]")).getText());
                    }
                }
            }
            href = computerDatabasePage.validateClickable(nextButton); //return href value
            computerDatabasePage.click(nextButton); //click next button for the next page
            counter++; //increase counter variable value
        } while (href != null);
        //
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("DatabaseMultipleSheets.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("DatabaseMultipleSheets.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test/**This test downloads the whole computer database and stores it in single sheet excel file */
    public void getWholeDatabaseInOneSheet() {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver);
        computerDatabasePage.openPage();
        System.out.println(computerDatabasePage.getResultsText(header));
        int s = Integer.parseInt(computerDatabasePage.getResultsText(header).replaceAll("[^0-9]", ""));
        System.out.println(s);

        //Create workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Search Output");
        //Creates the table header
        Body_col_count = computerDatabasePage.getNumberOfColumnsBody();
        for (int k = 0; k < 1; k++) {
            XSSFRow row = sheet.createRow(r - 1);
            for (int j = 0; j < Body_col_count; j++) {
                row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/thead/tr/th[" + (j + 1) + "]")).getText());
            }
        }

        do {
            //Table dimensions

            //Get number of rows In table body.
            Body_row_count = computerDatabasePage.getNumberOfRowsBody();
            System.out.println("Number Of Rows In table body = " + Body_row_count);
            //Get number of columns In table body.
            Body_col_count = computerDatabasePage.getNumberOfColumnsBody();
            System.out.println("Number Of Columns In table body = " + Body_col_count);

            //Create r rows in a sheet. r is equal to number of the query results
            for (int k = 0; k < Body_row_count; k++) {
                XSSFRow row = sheet.createRow(r);//Creates r-th row
                for (int j = 0; j < Body_col_count; j++) {
                    row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/tbody/tr[" + (k + 1) + "]/td[" + (j + 1) + "]")).getText());
                }
                r++;
                System.out.println(r);
            }

            href = computerDatabasePage.validateClickable(nextButton); //return href value
            computerDatabasePage.click(nextButton); //click next button for the next page

        } while (r <= s && href != null);
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("DatabaseInOneSheet.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("DatabaseInOneSheet.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test/**This test searches the computer database, downloads the results and stores it in single sheet excel file */
    public void filterQueryInOneSheet() {
        ComputerDatabasePage computerDatabasePage = new ComputerDatabasePage(driver);
        computerDatabasePage.openPage();
        computerDatabasePage.filterByName("IBM", searchBox, searchButton);
        computerDatabasePage.getResultsText(header);

        System.out.println(computerDatabasePage.getResultsText(header));
        int s = Integer.parseInt(computerDatabasePage.getResultsText(header).replaceAll("[^0-9]", ""));
        System.out.println(s);

        //Create workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Search Output");
        //Creates the table header
        Body_col_count = computerDatabasePage.getNumberOfColumnsBody();
        for (int k = 0; k < 1; k++) {
            XSSFRow row = sheet.createRow(r - 1);
            for (int j = 0; j < Body_col_count; j++) {
                row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/thead/tr/th[" + (j + 1) + "]")).getText()); //Creates the table header
            }
        }


        do {
            //Table dimensions

            //Get number of rows In table body.
            Body_row_count = computerDatabasePage.getNumberOfRowsBody();
            System.out.println("Number Of Rows In table body = " + Body_row_count);
            //Get number of columns In table body.
            Body_col_count = computerDatabasePage.getNumberOfColumnsBody();
            System.out.println("Number Of Columns In table body = " + Body_col_count);

            //Create r rows in a sheet. r is equal to number of the query results
            for (int k = 0; k < Body_row_count; k++) {
                XSSFRow row = sheet.createRow(r);//Creates r-th row
                for (int j = 0; j < Body_col_count; j++) {
                    row.createCell(j).setCellValue(driver.findElement(By.xpath("//table/tbody/tr[" + (k + 1) + "]/td[" + (j + 1) + "]")).getText());
                }
                r++;
                System.out.println(r);
            }

            href = computerDatabasePage.validateClickable(nextButton); //return href value
            computerDatabasePage.click(nextButton); //click next button for the next page

        } while (r <= s && href != null);
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("QueryInOneSheet.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("QueryInOneSheet.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


