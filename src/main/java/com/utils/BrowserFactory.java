package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private BrowserFactory(){

    }

    private static BrowserFactory instance = null;

    public static BrowserFactory getInstance(){

        if(instance == null)
            instance = new BrowserFactory();
        return instance;
    }

    public WebDriver getBrowser(String browser){
        WebDriver driver= null;
        ConfigReader configReader = ConfigReader.getInstance();
        try{
            switch(browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    if(configReader.getProp("selenium_grid").equals("true"))
                        driver = new RemoteWebDriver(new URL(configReader.getProp("selenium_hub")), chromeOptions);
                    else
                        driver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if(configReader.getProp("selenium_grid").equals("true"))
                        driver = new RemoteWebDriver(new URL(configReader.getProp("selenium_hub")), firefoxOptions);
                    else
                        driver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
                    break;
                default: System.out.println("Invalid browser option");
                    break;
            }
        }catch (MalformedURLException e){
            System.out.println("Error in getting url");
        }
        return driver;
    }

}
