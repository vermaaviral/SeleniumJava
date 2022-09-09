package com.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utils.DriverFactory;
import com.utils.ExtentFactory;
import com.utils.ExtentSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportingListener implements ISuiteListener, ITestListener {

    static ExtentReports setup;
    ExtentTest test;
    LocalDateTime myDateObj = LocalDateTime.now();
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
    String formattedDate = myDateObj.format(myFormatObj);

    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
        File dir = new File(System.getProperty("user.dir") + "/test-output/" + formattedDate +"/screenshot");
        if (!dir.exists()){
            dir.mkdirs();
        }
        setup = ExtentSetup.setupExtentReport(formattedDate);
    }


    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
        setup.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = setup.createTest(result.getMethod().getMethodName());
        ExtentFactory.getInstance().setExtent(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, result.getMethod().getMethodName() + " is Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) DriverFactory.getInstance().getDriver());
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(System.getProperty("user.dir") + "/test-output/" + formattedDate + "/screenshot/" + result.getName() + ".png");
            FileUtils.copyFile(SrcFile, DestFile);
        }catch (Exception e){
            System.out.println("Error while taking screenshot");
        }
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
        ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath( "/test-output/" + formattedDate + "/screenshot/" + result.getName() + ".png");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        ExtentFactory.getInstance().getExtent().log(Status.SKIP,result.getMethod().getMethodName()+ " is Skipped");
        if(result.wasRetried())
            setup.removeTest(ExtentFactory.getInstance().getExtent());
    }


}
