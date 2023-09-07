package THzDatabaseTests;

import BaseUtilities.Base;
import THzDatabase.THzDatabasePage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class THzDatabaseTests extends Base {
final By agreeButton= By.xpath("//input[@name='agree']");

    @Test
    public void getDatabase() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        THzDatabasePage tHzDatabasePage = new THzDatabasePage(driver);
        tHzDatabasePage.goToPage();
        tHzDatabasePage.click(agreeButton);
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("List");

        //List the links on a page
        List<WebElement> listOfElements=driver.findElements(By.xpath("//ul[@id='sampleList']/descendant::a"));
        System.out.println("Broj linkova u bazi: "+listOfElements.size());
        String[] linksUrls = new String[listOfElements.size()];
        String[] names = new String[listOfElements.size()];/**Creates an array to store the extracted urls*/

        for (int i = 0; i < (listOfElements).size(); i++) /**  Extracts the text from the list of web elements and stores them in a
         string array**/ {
            WebElement E1 = listOfElements.get(i);
            String name = E1.getText();
            names[i] = name;
        }

        for (int i = 0; i < (listOfElements).size(); i++) /**  Extracts the urls from the list of web elements and stores them in a
         string array**/ {
            WebElement E1 = listOfElements.get(i);
            String url = E1.getAttribute("href");
            linksUrls[i] = url;
        }
        /**stampa linkove**/
        System.out.println("Linkovi:");
        for (int i = 0; i < (listOfElements).size(); i++) {
            System.out.println(linksUrls[i]);
        }
        /**stampa nazive jedinjenja**/
        System.out.println("Nazivi Jedinjenja:");
        for (int i = 0; i < (listOfElements).size(); i++) {
            System.out.println(names[i]);
        }

/**otvaranje stranice po stranice**/

        /**upisivanje u tabelu**/
        for (int k = 0; k < (listOfElements).size(); k++) {
            XSSFRow row = sheet.createRow(k);
            row.createCell(0).setCellValue(names[k]);
            row.createCell(1).setCellValue(linksUrls[k]);
        }

        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("THzBaza.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("THzBaza.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < (listOfElements).size(); i++) {
            tHzDatabasePage.goToPage(linksUrls[i]);
            if (!driver.findElements(By.xpath("(//div[@class='plot'])[1]")).isEmpty()){
                //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='plot'])[1]")));
            String url1=driver.findElement(By.xpath("(//div[@class='plot'])[1]/descendant::iframe")).getAttribute("src");
            int a= url1.lastIndexOf("Draw2");
            int b=url1.lastIndexOf("file");
            String url2=url1.substring(0,a);
            String url3= url1.substring(b,url1.length());
            String url4=url2+url3;
            System.out.println(url4);
            driver.get(url4);
            String one = driver.getPageSource().toString();
            try{
                FileWriter fw=new FileWriter((i+1)+".txt");
                fw.write(one);
                fw.close();
            }catch(Exception e){System.out.println(e);}}
            else {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//img)[1]")));
                String url1=driver.findElement(By.xpath("")).getAttribute("");
                int a= url1.lastIndexOf("Draw2");
                int b=url1.lastIndexOf("file");
                String url2=url1.substring(0,a);
                String url3= url1.substring(b,url1.length());
                String url4=url2+url3;
                System.out.println(url4);
                driver.get(url4);
                String one = driver.getPageSource().toString();
                try{
                    FileWriter fw=new FileWriter((i+1)+".txt");
                    fw.write(one);
                    fw.close();
                }catch(Exception e){System.out.println(e);}
            }
        }
    }
}


