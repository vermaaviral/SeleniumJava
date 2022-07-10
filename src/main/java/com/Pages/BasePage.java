package com.Pages;

import com.utils.YamlReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    protected WebDriver driver;
    protected YamlReader yamlReader=new YamlReader("src/resources/ObjectRepository.yaml");

    public BasePage(WebDriver driver){
        this.driver=driver;
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
}
