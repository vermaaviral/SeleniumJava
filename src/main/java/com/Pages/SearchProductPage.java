package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchProductPage extends BasePage{

    public SearchProductPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchedProductName(){
        CommonUtil util = new CommonUtil(driver);
        String product = util.getElement("SearchProductPage.searchHeading").getText().split("-")[1].trim();
        return product;
    }

    public void matchSearchedProduct(String productName){
        CommonUtil util = new CommonUtil(driver);
        List<WebElement> products = util.getElements("SearchProductPage.searchedProductNames");
        for(WebElement product: products){
            String name = product.getText().toLowerCase();
            Assert.assertTrue(name.matches("(.*)"+productName.toLowerCase()+"(.*)"));
        }
        addLog("pass", "Matching Product displayed contains the searched keyword " + productName );
    }

    public ProductDetailsPage openSearchedProduct(String productName){
        CommonUtil util = new CommonUtil(driver);
        List<WebElement> products = util.getElements("SearchProductPage.searchedProductNames");
        WebElement product =  products.get(0);
        util.clickElement(product);
        return new ProductDetailsPage(driver);
    }
}
