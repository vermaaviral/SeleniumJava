package com.utils;

import com.Pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CommonUtil extends BasePage {

    public CommonUtil(WebDriver driver) {
        super(driver);
    }

    public void moveToElement(String path) {
        WebElement element = getElement(path);
        scrollToElement(element);
        Actions action=new Actions(driver);
        action.moveToElement(element).build().perform();
        addLog("pass", "Scrolled to "+ path.split("\\.")[1] + " in " + path.split("\\.")[0] + " page");
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void openInNewTab(String path){
        moveToElement(path);
        WebElement element = getElement(path);
        Actions action=new Actions(driver);
        action.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
        addLog("pass", "Opened "+ path.split("\\.")[1] + " in new tab");
    }

    public void openInNewWindow(String path){
        moveToElement(path);
        WebElement element = getElement(path);
        Actions action=new Actions(driver);
        action.keyDown(Keys.SHIFT).click(element).keyUp(Keys.SHIFT).build().perform();
        addLog("pass", "Opened "+ path.split("\\.")[1] + " in new window");
    }

    public String getCurrentWindow(){
        return driver.getWindowHandle();
    }

    public Set<String> getAllWindows(){
        return driver.getWindowHandles();
    }

    public void switchToWindow(String targetWindow){
        driver.switchTo().window(targetWindow);
    }

    public List<String> getAllLinks(){
        List<WebElement> links=findElements(locateElement("tagname","a"));
        List<String> urlList=new ArrayList<>();
        for(WebElement e:links){
            urlList.add(e.getAttribute("href"));
        }
        addLog("pass","Total Links captured:"+urlList.size());
        return urlList;
    }

    public void verifyLink(String linkUrl){
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpConnect=(HttpURLConnection)url.openConnection();
            httpConnect.setConnectTimeout(5000);
            httpConnect.connect();

            int code=httpConnect.getResponseCode();
            if(code>=400)
                addLog("fail","Error verifying " +linkUrl + "--->"+ httpConnect.getResponseMessage());
            httpConnect.disconnect();
        }catch (Exception e){
            addLog("fail","Error verify Link URL");
        }
    }

    public void verifyAllLinks(List<String> linkUrl){
        getAllLinks().parallelStream().forEach(e-> verifyLink(e));
    }


    public By locateElement(String locatorType, String locatorValue){
        By element=null;
        switch(locatorType){
            case "xpath": element = By.xpath(locatorValue);
            break;
            case "css": element = By.cssSelector(locatorValue);
            break;
            case "tagName": element = By.tagName(locatorValue);
            break;
            case "name": element = By.name(locatorValue);
            break;
            case "id": element = By.id(locatorValue);
            break;
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        return element;
    }

    public WebElement getElement(String path){
        String[] val= splitValue(path);
        By element = locateElement(val[0].trim(),val[1].trim());
        return findElement(element);
    }

    public WebElement getElement(String path, String option){
        String[] val= splitValue(path);
        String locatorValue = val[1].trim().replaceFirst("OPTION", option);
        By element = locateElement(val[0].trim(),locatorValue);
        return findElement(element);
    }

    public List<WebElement> getElements(String path){
        String[] val= splitValue(path);
        By element = locateElement(val[0].trim(),val[1].trim());
        return findElements(element);
    }

    public WebElement findElement(By element){
        return driver.findElement(element);
    }

    public List<WebElement> findElements(By element){
        return driver.findElements(element);
    }

    public String[] splitValue(String path){
        String[] value = objectReader.getValue(path).split(":",2);
        return value;
    }

    public void clickElement(String path){
        WebElement element = getElement(path);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        addLog("pass", "Clicked on "+ path.split("\\.")[1] + " in " + path.split("\\.")[0] + " page");
    }

    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        addLog("pass", "Clicked on element: " + element);
    }

    public void clickElement(String path, String option){
        WebElement element = getElement(path, option);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        addLog("pass", "Clicked on "+ option + " in " + path.split("\\.")[1] + " in " + path.split("\\.")[0] + " page");
    }

    public void sendKeysToElement(String path, String value){
        WebElement element = getElement(path);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
        addLog("pass", "Entered value as "+ value + " for " + path.split("\\.")[1] + " in " + path.split("\\.")[0] + " page");
    }

    public void sendKeysToElement(String path, String value, String option){
        WebElement element = getElement(path,option);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
        addLog("pass", "Entered value as "+ value + " for " + option + " in " + path.split("\\.")[0] + " page");
    }

    public void selectDropdown(String path, String value){
        WebElement element = getElement(path);
        wait.until(ExpectedConditions.visibilityOf(element));
        Select s = new Select(element);
        s.selectByVisibleText(value);
        addLog("pass", "Select value "+ value + " from " + path.split("\\.")[1] + " dropdown in " + path.split("\\.")[0] + " page");
    }

    public void selectDropdown(String path, String value, String option){
        WebElement element = getElement(path,option);
        wait.until(ExpectedConditions.visibilityOf(element));
        Select s = new Select(element);
        s.selectByVisibleText(value);
        addLog("pass", "Select value "+ value + " from " + option + " dropdown in " + path.split("\\.")[0] + " page");
    }

    public void selectDropdownWithSearchOption(String path, String value, String option){
        WebElement element = getElement(path,option);
        wait.until(ExpectedConditions.visibilityOf(element));
        Select s = new Select(element);
        List<WebElement> elements = s.getOptions();
        for(WebElement e : elements){
            String text = e.getText();
            if(text.contains(value)){
                value =text;
                break;
            }
        }
        s.selectByVisibleText(value);
        addLog("pass", "Select value "+ value + " from " + option + " dropdown in " + path.split("\\.")[0] + " page");
    }

    public void selectDropdownWithSearchOption(String path, String value){
        WebElement element = getElement(path);
        wait.until(ExpectedConditions.visibilityOf(element));
        Select s = new Select(element);
        List<WebElement> elements = s.getOptions();
        for(WebElement e : elements){
            String text = e.getText();
            if(text.contains(value)){
                value =text;
                break;
            }
        }
        s.selectByVisibleText(value);
        addLog("pass", "Select value "+ value + " from " + path.split("\\.")[1] + " dropdown in " + path.split("\\.")[0] + " page");
    }

    public void assertEqualResult(String path, String expectedOutput){
        String actualOutput= getElement(path).getText().trim();
        Assert.assertEquals(actualOutput,expectedOutput);
        addLog("pass", "Assertion is passed by matching expected result " + expectedOutput + " with actual result " + actualOutput );
    }

    public void assertEqualString(String actualOutput, String expectedOutput){
        Assert.assertEquals(actualOutput,expectedOutput);
        addLog("pass", "Assertion is passed by matching expected result " + expectedOutput + " with actual result " + actualOutput );
    }

    public void assertEqualResult(String path, String option, String expectedOutput){
        String actualOutput= getElement(path, option).getText().trim();
        Assert.assertEquals(actualOutput,expectedOutput);
        addLog("pass", "Assertion is passed by matching expected result " + expectedOutput + " with actual result " + actualOutput );
    }

    public boolean elementIsSelected(String path){
        Boolean status = getElement(path).isSelected();
        addLog("pass", "Selection Status is " + status + " for element " +  path.split("\\.")[1]);
        return status;
    }

    public boolean elementIsSelected(String path, String option){
        Boolean status = getElement(path, option).isSelected();
        addLog("pass", "Selection Status is " + status + " for option " +  option);
        return status;
    }

    public boolean elementIsDisplayed(String path){
        Boolean status = getElement(path).isDisplayed();
        addLog("pass", "Display Status is " + status + " for element " +  path.split("\\.")[1]);
        return status;
    }

    public boolean elementIsDisplayed(String path, String option){
        Boolean status = getElement(path, option).isDisplayed();
        addLog("pass", "Display Status is " + status + " for option " +  option);
        return status;
    }

    public boolean elementIsEnabled(String path){
        Boolean status = getElement(path).isEnabled();
        addLog("pass", "Enable Status is " + status + " for element " +  path.split("\\.")[1]);
        return status;
    }

    public boolean elementIsEnabled(String path, String option){
        Boolean status = getElement(path, option).isEnabled();
        addLog("pass", "Enable Status is " + status + " for option " +  option);
        return status;
    }

    public String getsessionId(){
        SessionId s = ((RemoteWebDriver) driver).getSessionId();
        return s.toString();
    }
}
