package com.Pages;

import com.aventstack.extentreports.Status;
import com.utils.CommonUtil;
import com.utils.ExtentFactory;
import com.utils.YamlReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public void addLog(String status, String message){

        try {
            switch (status) {
                case "pass":
                    ExtentFactory.getInstance().getExtent().log(Status.PASS, message);
                    break;
                case "fail":
                    ExtentFactory.getInstance().getExtent().log(Status.FAIL, message);
                    break;
                case "skip":
                    ExtentFactory.getInstance().getExtent().log(Status.SKIP, message);
                    break;
            }
        }catch (Exception e){}
    }

    public void moveToHomePage(){
        getInstance(CommonUtil.class).clickElement("HomePage.homeLogo");
    }

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {

        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void addSleep(long time){
        try{
            Thread.sleep(time);
            addLog("pass", "Halt Execution for: " + (time/1000) + "seconds");
        }catch (Exception e){}
    }

}
