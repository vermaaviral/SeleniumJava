package testCases;

import com.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {

    public ConfigReader configReader= new ConfigReader();
    public static ThreadLocal<WebDriver> tldriver= new ThreadLocal<>();

    @BeforeMethod(alwaysRun=true)
    @Parameters(value={"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {
        switch(browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if(configReader.getProp("selenium_grid").equals("true"))
                    tldriver.set(new RemoteWebDriver(new URL(configReader.getProp("selenium_hub")), chromeOptions));
                else
                    tldriver.set(WebDriverManager.chromedriver().capabilities(chromeOptions).create());
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(configReader.getProp("selenium_grid").equals("true"))
                    tldriver.set(new RemoteWebDriver(new URL(configReader.getProp("selenium_hub")), firefoxOptions));
                else
                    tldriver.set(WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create());
                break;
            default: System.out.println("Invalid browser option");
                break;
        }

        getDriver().get("https://www.google.com/");
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() {
        getDriver().quit();
    }

    public static synchronized WebDriver getDriver() {
        return tldriver.get();
    }
}
