package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage proceedToCheckout(){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("CartPage.checkoutButton");
        return new CheckoutPage(driver);
    }

    public void proceedToShopping(){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("CartPage.shopButton");
    }

}
