package Tables;

import BaseUtilities.Functions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicTablePage extends Functions {
    public DynamicTablePage(WebDriver driver) {
        super(driver);}

        public void openPage(){
            driver.manage().window().maximize();
            driver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
        }

        public int getNumberOfRowsHeader(){
         return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/thead/tr")).size();
        }

        public int getNumberOfRowsFooter(){
            return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tfoot/tr")).size();
        }

        public int getNumberOfRowsBody(){
            return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tbody/tr")).size();
        }

        public int getNumberOfColumnsBody() {
            return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tbody/tr[1]/td")).size();
        }

            public int getNumberOfColumnsHeader(){
                return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/thead/tr/th")).size();
            }

    public int getNumberOfColumnsFooter(){
        return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tfoot/tr/th")).size() + driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tfoot/tr/td")).size();

    }

    public int getColumnHeight(int i){

    if (i>0){
        return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tbody/tr/td["+i+"]")).size();}
    else{
        return driver.findElements(By.xpath("//table[@class='tsc_table_s13']/tbody/tr")).size() + getNumberOfRowsFooter();
    }

    }

    public String getCellText(int j){
        return driver.findElement(By.xpath("//table[@class='tsc_table_s13']/thead/tr/th["+(j+1)+"]")).getText();
    }
}


