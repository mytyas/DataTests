package TableTests;

import BaseUtilities.Base;
import Tables.TablePage;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.testng.annotations.Test;



public class TableTest extends Base {
    int Row_count;
    int Col_count;
    String first_part = "//table[@id='customers']/tbody/tr[";
    String second_part = "]/th[";
    String second_partA = "]/td[";
    String third_part = "]";

    @Test /**This test locates a static table on the page https://www.techlistic.com/p/demo-selenium-practice.html, gets its size and prints the contents of the cells**/

    public void print_data() {

        TablePage tablePage = new TablePage(driver);
        tablePage.openPage();

        //Get number of rows In table.
        Row_count = tablePage.getNumberOfRows();
        System.out.println("Number Of Rows = " + Row_count);
        //Get number of columns In table.
        Col_count = tablePage.getNumberOfColumns();
        System.out.println("Number Of Columns = " + Col_count);


        //divided xpath In three parts to pass Row_count and Col_count values.

        //Used for loop for number of rows. Two loops are required for th is reserved only for header and td for the rest
        // of the cells

        for (int i = 1; i < Row_count + 1; i++) {

            //Used for loop for number of columns.
            for (int j = 1; j < Col_count + 1; j++) {

                //Prepared final xpath of specific cell as per values of i and j.
                //Will retrieve value from located cell and print It.
                if (i < 2) {
                    System.out.print(driver.findElement(By.xpath(first_part + i + second_part + j + third_part)).getText() + " ");
                } else {
                    System.out.print(driver.findElement(By.xpath(first_part + i + second_partA + j + third_part)).getText() + " ");
                }
            }
            System.out.println("");
        }
        }
    }