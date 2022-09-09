package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage{

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(){
        getInstance(CommonUtil.class).clickElement("ProductDetailsPage.addToCart");
        addSleep(2000);
    }

    public void updateQuantity(String quantity){
        getInstance(CommonUtil.class).sendKeysToElement("ProductDetailsPage.quantity", quantity);
    }
}
