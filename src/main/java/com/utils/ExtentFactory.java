package com.utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class ExtentFactory {

    private ExtentFactory(){

    }

    private static ExtentFactory instance = null;
    public static ThreadLocal<ExtentTest> test= new ThreadLocal<>();

    public static ExtentFactory getInstance(){

        if(instance == null)
            instance = new ExtentFactory();
        return instance;
    }

    public ExtentTest getExtent(){
        return test.get();
    }

    public void setExtent(ExtentTest param){
        test.set(param);
    }

    public void removeExtent(){
        test.remove();
    }

}
