package com.Pages;

import com.utils.YamlReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;
    protected YamlReader objectReader=new YamlReader("src/resources/ObjectRepository.yaml");
    protected WebDriverWait wait;


    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement getElementByXpath(String path){
        By element= By.xpath(yamlReader.getValue(path));
        return driver.findElement(element);
    }

    public WebElement getElementByCss(String path){
        By element= By.cssSelector(yamlReader.getValue(path));
        return driver.findElement(element);
    }

    public WebElement getElementByName(String path){
        By element= By.name(yamlReader.getValue(path));
        return driver.findElement(element);
    }

    public WebElement getElementById(String path){
        By element= By.id(yamlReader.getValue(path));
        return driver.findElement(element);
    }

    public void addLog(String status, String message){
        switch (status){
            case "pass": ExtentFactory.getInstance().getExtent().log(Status.PASS,message);
                        break;
            case "fail": ExtentFactory.getInstance().getExtent().log(Status.FAIL,message);
                        break;
            case "skip": ExtentFactory.getInstance().getExtent().log(Status.SKIP,message);
                        break;
        }
    }

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {

        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
