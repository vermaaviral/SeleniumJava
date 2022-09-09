package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void proceedToCheckout(){
        getInstance(CommonUtil.class).clickElement("CartPage.checkoutButton");
    }

    public void proceedToShopping(){
        getInstance(CommonUtil.class).clickElement("CartPage.shopButton");
    }

}
