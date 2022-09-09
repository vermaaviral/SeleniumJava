package com.Pages;

import org.openqa.selenium.WebDriver;
import com.utils.CommonUtil;

public class AccountPage extends BasePage{


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){
        getInstance(CommonUtil.class).clickElement("HomePage.icons", "My Account");
        getInstance(CommonUtil.class).clickElement("HomePage.myAccountOptions", "Login");
        getInstance(CommonUtil.class).sendKeysToElement("AccountPage.inputEmail", username);
        getInstance(CommonUtil.class).sendKeysToElement("AccountPage.inputPassword", password);
        getInstance(CommonUtil.class).clickElement("AccountPage.loginButton");
    }

    public void logout(){
        getInstance(CommonUtil.class).clickElement("HomePage.icons", "My Account");
        getInstance(CommonUtil.class).clickElement("HomePage.myAccountOptions", "Logout");
    }

    public void selectListOperation(String option){
        getInstance(CommonUtil.class).clickElement("AccountPage.operationList", option);
    }

    public void verifyInvalidLoginText(String expectedResult){
        getInstance(CommonUtil.class).assertEqualResult("AccountPage.warningText", expectedResult);
    }

    public void moveToAddressBook(){
        getInstance(HomePage.class).openMenuOption("My Account");
        getInstance(CommonUtil.class).clickElement("HomePage.myAccountOptions", "My Account");
        getInstance(CommonUtil.class).clickElement("AccountPage.operationList", "AddressBook");
    }
}
