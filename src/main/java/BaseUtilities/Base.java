package BaseUtilities;

import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.time.Duration;


public class Base {

    public WebDriver driver;

    protected String testSuiteName;
    protected String testName;
    protected String testMethodName;


    @Parameters({ "browser", "chromeProfile", "deviceName" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile,
                      @Optional String deviceName, ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();


        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        if (profile != null) {
            driver = factory.createChromeWithProfile(profile);
        } else if (deviceName != null) {
            driver = factory.createChromeWithMobileEmulation(deviceName);
        } else {
            driver = factory.createDriver();
        }

        // This sleep here is for instructor only. Students don't need this here
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.manage().window().maximize();


        this.testSuiteName = ctx.getSuite().getName();
        this.testName = testName;
        this.testMethodName = method.getName();
    }




    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close browser
        driver.quit();
    }




}