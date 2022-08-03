package com.utils;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private DriverFactory(){

    }

    private static DriverFactory instance = null;
    public static ThreadLocal<WebDriver> driver= new ThreadLocal<>();

    public static DriverFactory getInstance(){

        if(instance == null)
            instance = new DriverFactory();
        return instance;
    }

    public void setDriver(WebDriver driverParam){
        driver.set(driverParam);
    }

    public synchronized WebDriver getDriver(){
        return driver.get();
    }

    public void removeDriver(){
        driver.remove();
    }
}
