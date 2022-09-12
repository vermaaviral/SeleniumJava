package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void changeCurrency(String currency){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("HomePage.currencyButton");
        util.clickElement("HomePage.currencyValue", currency);
    }

    public SearchProductPage searchProduct(String productName){
        CommonUtil util = new CommonUtil(driver);
        moveToHomePage();
        util.sendKeysToElement("HomePage.searchText", productName);
        util.clickElement("HomePage.searchButton");
        return new SearchProductPage(driver);
    }

    public String getCartTotal(){
        CommonUtil util = new CommonUtil(driver);
        String total = util.getElement("HomePage.cartTotal").getText().split("-")[1].trim();
        return total;
    }

    public void openMenuOption(String option){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("HomePage.icons", option);
    }
}
