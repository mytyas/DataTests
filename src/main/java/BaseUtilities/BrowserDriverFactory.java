package BaseUtilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;


    public BrowserDriverFactory(String browser) {
        this.browser = browser;

    }

    public WebDriver createDriver () {
//        System.out.println("Create driver: " + browser);

        switch (browser) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
            }
            case "firefox" -> {
                System.setProperty("webdriver.firefox.marionette", "src/main/resources/geckodriver.exe");
                driver.set(new FirefoxDriver());
            }
            default -> {

                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver.set(new ChromeDriver());
            }
        }

        return driver.get();
    }

    public WebDriver createChromeWithProfile(String profile) {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-data-dir=src/main/resources/Profiles/" + profile);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }

    public WebDriver createChromeWithMobileEmulation(String deviceName) {

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }
}
