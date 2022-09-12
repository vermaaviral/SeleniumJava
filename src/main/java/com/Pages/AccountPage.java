package com.Pages;

import org.openqa.selenium.WebDriver;
import com.utils.CommonUtil;

public class AccountPage extends BasePage{


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("HomePage.icons", "My Account");
        util.clickElement("HomePage.myAccountOptions", "Login");
        util.sendKeysToElement("AccountPage.inputEmail", username);
        util.sendKeysToElement("AccountPage.inputPassword", password);
        util.clickElement("AccountPage.loginButton");
    }

    public void logout(){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("HomePage.icons", "My Account");
        util.clickElement("HomePage.myAccountOptions", "Logout");
    }

    public void selectListOperation(String option){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("AccountPage.operationList", option);
    }

    public void verifyInvalidLoginText(String expectedResult){
        CommonUtil util = new CommonUtil(driver);
        util.assertEqualResult("AccountPage.warningText", expectedResult);
    }
}
