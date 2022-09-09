package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void changeCurrency(String currency){
        getInstance(CommonUtil.class).clickElement("HomePage.currencyButton");
        getInstance(CommonUtil.class).clickElement("HomePage.currencyValue", currency);
    }

    public void searchProduct(String productName){
        moveToHomePage();
        getInstance(CommonUtil.class).sendKeysToElement("HomePage.searchText", productName);
        getInstance(CommonUtil.class).clickElement("HomePage.searchButton");

    }

    public String getCartTotal(){
        String total = getInstance(CommonUtil.class).getElement("HomePage.cartTotal").getText().split("-")[1].trim();
        return total;
    }

    public void openMenuOption(String option){
        getInstance(CommonUtil.class).clickElement("HomePage.icons", option);
    }
}
