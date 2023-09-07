package GoDaddyTests;

import BaseUtilities.Base;
import Godaddy.GoDaddyPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;


public class GoDaddyTests extends Base {

    String titleExpected = "Domain Names, Websites, Hosting & Online Marketing Tools - GoDaddy UK";
    String urlExpected = "https://www.godaddy.com/en-uk";
    String domains = "https://www.godaddy.com/en-uk/domains";
    String available;
    final By domainsLink = By.xpath("(//button[normalize-space()='Domain Names'])[1]");
    final By domainsNamesBadge = By.xpath("//div[@data-subnav-lists='domain_names']");
    final By searchForDomainNamesLink = By.xpath("(//a[normalize-space()='Search for Domain Names'])[1]");
    final By domainNamesBadgeLinks = By.xpath("(//ul[@aria-hidden='false']//descendant::a)");
    final By searchBox = By.name("searchText");
    final By buyItButton = By.xpath("//button[@aria-label='Buy It']");
    final By isAvailable = By.tagName("h4");






    @Test
    public void testCase1() {
        GoDaddyPage goDaddyPage = new GoDaddyPage(driver);
        goDaddyPage.openPage();
    }

    @Test
    public void testCase2() {
        GoDaddyPage goDaddyPage = new GoDaddyPage(driver);
        goDaddyPage.openPage();
        System.out.println(goDaddyPage.getPageTitle());
    }

    @Test
    public void testCase3() {
        GoDaddyPage goDaddyPage = new GoDaddyPage(driver);
        goDaddyPage.openPage();
        System.out.println(goDaddyPage.getPageTitle());
        goDaddyPage.validate(goDaddyPage.getPageTitle(),
                titleExpected);
        System.out.println(goDaddyPage.getPageUrl());
        goDaddyPage.validate(goDaddyPage.getPageUrl(), urlExpected);
        System.out.println(goDaddyPage.getSrc());
        goDaddyPage.validatePart(goDaddyPage.getSrc(), titleExpected);
    }

    @Test
    /** This test opens the godaddy home page, clicks on the Domain Names link, gets all the links in Domain Names
     badge, consecutively opens each page and verifies whether the page title is present in the page source
     **/
    public void testCase4() {
        GoDaddyPage goDaddyPage = new GoDaddyPage(driver); /** Instantiation of a new driver*/
        goDaddyPage.openPage(); /** Opens the GoDaddy home page**/
        goDaddyPage.click(domainsLink); /** Clicks on the Domain Names link, opens the badge**/
        goDaddyPage.validateVisible(domainsNamesBadge); /** Verifies whether the Domain Names badge is visible **/
        List<WebElement> links = driver.findElements(domainNamesBadgeLinks); /** Creates
         a list of all the web elements in the Domain Names badge containing links***/

        String[] linksUrls = new String[links.size()]; /**Creates an array to store the extracted urls from the badge*/

        for (int i = 0; i < (links).size(); i++) /**  Extracts the urls from the list of web elements and stores them in a
         string array**/ {
            WebElement E1 = links.get(i);
            String url = E1.getAttribute("href");
            linksUrls[i] = url;
        }

        for (int i = 0; i < (links).size(); i++) {
            System.out.println(linksUrls[i]); /**uncomment if you wish to print the links list**/
            goDaddyPage.goToPage(linksUrls[i]); /** Goes to a web page from the i-th link in the array*/
            goDaddyPage.validateUrl(linksUrls[i]); /** Validates whether a page is opened*/
            goDaddyPage.validatePart(goDaddyPage.getSrc(), goDaddyPage.getPageTitle()); /** Validates whether
             the page title is present in the page source */
        }
    }

    @Test/** This test opens the GoDaddy home page, finds the buttons in the header, clicks on them, gets all the links from the buttons' badges,
     consecutively opens each page and verifies whether the page title is present in the page source
     **/
    public void testCase5(){
        GoDaddyPage goDaddyPage = new GoDaddyPage(driver); /** Instantiation of a new driver*/
        goDaddyPage.openPage(); /** Opens the GoDaddy home page**/
        List<WebElement> buttons = driver.findElements(By.xpath("//li[@data-track-eid-click]")); /** Locates the buttons in the header and creates
         a list of WebElements (buttons), sized buttons.size() ***/
        String[] buttonsRefs = new String[buttons.size()]; /**Creates an array where the corresponding string identificators
         of the buttons' badges will be stored and used for later locating*/
        for (int i = 0; i < (buttons).size(); i++) /**  Extracts the "data-track-name" from the list of WebElements (buttons) and stores them in a
         string array**/ {
            WebElement E1 = buttons.get(i);
            String ref = E1.getAttribute("data-track-name");
            buttonsRefs[i] = ref;
            System.out.println(ref); /**Prints the data-track-names of the buttons**/
        }

        for (int j=0;j<(buttons).size(); j++) {
            driver.findElement(By.xpath("//button[normalize-space()='" + buttonsRefs[j].replace("_", " " +
                    "").replace("and", "&") + "'][1]")).click(); /** Clicks on the j-th button, located by
             on-site generated variable xpath, opens j-th badge **/

            List<WebElement> links = driver.findElements(By.xpath("//div[@data-subnav-lists='" + buttonsRefs[j].toLowerCase(Locale.ROOT) + "']/descendant::a"));
            /** Creates a WebList of all links found in the j-th badge, located by on-site generated variable xpath.
             Note the difference between buttons' and badges' xpaths **/

            String[] linksUrls = new String[links.size()]; /**Creates an array to store the extracted urls from the j-th badge*/

            for (int i = 0; i < (links).size(); i++) /**  Extracts the urls from the WebList (links) and stores them in a
             string array**/ {
                WebElement E1 = links.get(i);
                String url = E1.getAttribute("href");
                linksUrls[i] = url;
            }

            for (int i = 0; i < (links).size(); i++) {
                System.out.println(linksUrls[i]); /**uncomment if you wish to print the links list**/
                goDaddyPage.goToPage(linksUrls[i]); /** Goes to a web page from the i-th link of the j-th badge in the array*/
                goDaddyPage.validateUrl(linksUrls[i]); /** Validates whether a page is opened*/
                goDaddyPage.validatePart(goDaddyPage.getSrc(), goDaddyPage.getPageTitle()); /** Validates whether
                 the page title is present in the page source */
            }
        }

    }
    @Test
    public void testCase6(){
        GoDaddyPage goDaddyPage = new GoDaddyPage(driver); /** Instantiation of a new driver*/
        goDaddyPage.openPage();
        goDaddyPage.click(domainsLink);
        goDaddyPage.validateVisible(domainsNamesBadge);
        goDaddyPage.click(searchForDomainNamesLink);
        goDaddyPage.validateUrl(domains);
        System.out.println(goDaddyPage.getPageTitle());
        goDaddyPage.validate(goDaddyPage.getPageTitle(),"GoDaddy Domain Search - Buy and Register Available Domain Names");
        goDaddyPage.validateVisible(searchBox);
        goDaddyPage.validateVisible(buyItButton);
        goDaddyPage.sendText(searchBox,"mydomain");
        goDaddyPage.click(buyItButton);
        goDaddyPage.validateUrl("https://uk.godaddy.com/domainsearch/find?domainToCheck=mydomain");//**odavde blokiraju bilo sta dalje**/
        goDaddyPage.validateVisible(isAvailable);
        goDaddyPage.getLocatorText(isAvailable, available);



    }
    }


