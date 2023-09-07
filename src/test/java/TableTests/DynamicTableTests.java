package TableTests;

import BaseUtilities.Base;
import Tables.DynamicTablePage;
import Tables.TablePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DynamicTableTests extends Base {
    int Header_row_count;
    int Header_col_count;
    int Footer_row_count;
    int Footer_col_count;
    int Body_row_count;
    int Body_col_count;

    int i;
    int max, vis, maxI;

    @Test /**This test gets a dynamic table data from https://www.techlistic.com/p/demo-selenium-practice.html, prints it and finds the tallest column**/
    public void print_data() {

        DynamicTablePage dynamicTablePage = new DynamicTablePage(driver);
        dynamicTablePage.openPage();

        //Get number of rows In table body.
        Body_row_count = dynamicTablePage.getNumberOfRowsBody();
        System.out.println("Number Of Rows In table body = " + Body_row_count);
        //Get number of columns In table body.
        Body_col_count = dynamicTablePage.getNumberOfColumnsBody();
        System.out.println("Number Of Columns In table body = " + Body_col_count);
        //Get number of rows In table header.
        Header_row_count = dynamicTablePage.getNumberOfRowsHeader();
        System.out.println("Number Of Rows in Header = " + Header_row_count);
        //Get number of rows In table footer.
        Footer_row_count = dynamicTablePage.getNumberOfRowsFooter();
        System.out.println("Number Of Rows in Footer = " + Footer_row_count);
        //Get number of columns In table header.
        Header_col_count = dynamicTablePage.getNumberOfColumnsHeader();
        System.out.println("Number Of Columns in Header = " + Header_col_count);
        //Get number of columns In table footer.
        Footer_col_count = dynamicTablePage.getNumberOfColumnsFooter();
        System.out.println("Number Of Columns in Footer = " + Footer_col_count);

        //Instantiate String array of table header
        String[] kolone = new String[Header_col_count];

        //Read table header, print and and store it into an array
        for (int j=0;j<Header_col_count;j++){
            kolone[j]=dynamicTablePage.getCellText(j);
            System.out.println(kolone[j]);
        }

        //Print column heights, find the tallest one
        for (i = 0; i < Header_col_count; i++) {
            vis=dynamicTablePage.getColumnHeight(i);
            System.out.println("Column height " + kolone[i] + " is " + vis);
            max = dynamicTablePage.getColumnHeight(1);
            if (vis>max){
                max=vis;
                maxI=i;
            }
        }
        System.out.println("The tallest column is " + kolone[maxI]);
    }
}
