package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage{

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("ProductDetailsPage.addToCart");
        addSleep(2000);
    }

    public void updateQuantity(String quantity){
        CommonUtil util = new CommonUtil(driver);
        util.sendKeysToElement("ProductDetailsPage.quantity", quantity);
    }
}
