package testCases;

import com.Pages.*;
import com.utils.CommonUtil;
import com.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopTest extends BaseTest{

    @Test(groups ={"Regression", "Smoke"})
    public void shopWithoutLoginAsGuest(){
        HomePage homePage = new HomePage(DriverFactory.getInstance().getDriver());
        CartPage cartPage = new CartPage(DriverFactory.getInstance().getDriver());

        SearchProductPage searchProductPage = homePage.searchProduct(data.getValue(env+".shopProduct"));
        ProductDetailsPage productDetailsPage = searchProductPage.openSearchedProduct(data.getValue(env+".shopProduct"));
        productDetailsPage.updateQuantity(data.getValue((env+".quantity")));
        productDetailsPage.addToCart();
        homePage.openMenuOption("Shopping Cart");
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.selectCheckoutAsNewCustomer("guest");
        checkoutPage.enterBillingDetailsForGuest(data.getValue((env+".guestDetails")),data.getValue((env+".guestAddress")), true);
        checkoutPage.enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        checkoutPage.enterPaymentMethodWithAgreementOption("cod", true);
        checkoutPage.confirmOrder();
        boolean status = checkoutPage.verifyConfirmation();
        Assert.assertTrue(status);
    }

    @Test(groups ={"Regression"})
    public void verifyWarningOnPaymentWithoutAgreeTerms(){
        HomePage homePage = new HomePage(DriverFactory.getInstance().getDriver());
        CartPage cartPage = new CartPage(DriverFactory.getInstance().getDriver());

        SearchProductPage searchProductPage = homePage.searchProduct(data.getValue(env+".shopProduct"));
        ProductDetailsPage productDetailsPage = searchProductPage.openSearchedProduct(data.getValue(env+".shopProduct"));
        productDetailsPage.updateQuantity(data.getValue((env+".quantity")));
        productDetailsPage.addToCart();
        homePage.openMenuOption("Shopping Cart");
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.selectCheckoutAsNewCustomer("guest");
        checkoutPage.enterBillingDetailsForGuest(data.getValue((env+".guestDetails")),data.getValue((env+".guestAddress")), true);
        checkoutPage.enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        checkoutPage.enterPaymentMethodWithAgreementOption("cod", false);
        Boolean status = checkoutPage.verifyWarningAlertForAgreement();
        Assert.assertTrue(status);
    }

    @Test(groups ={"Regression"})
    public void shopAsGuestWithBillingAndDeliveryDetailsDifferent(){
        HomePage homePage = new HomePage(DriverFactory.getInstance().getDriver());
        CartPage cartPage = new CartPage(DriverFactory.getInstance().getDriver());

        SearchProductPage searchProductPage = homePage.searchProduct(data.getValue(env+".shopProduct"));
        ProductDetailsPage productDetailsPage = searchProductPage.openSearchedProduct(data.getValue(env+".shopProduct"));
        productDetailsPage.updateQuantity(data.getValue((env+".quantity")));
        productDetailsPage.addToCart();
        homePage.openMenuOption("Shopping Cart");
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.selectCheckoutAsNewCustomer("guest");
        checkoutPage.enterBillingDetailsForGuest(data.getValue((env+".guestDetails")),data.getValue((env+".guestAddress")), false);
        checkoutPage.enterDeliveryDetailsForGuest(data.getValue((env+".deliveryDetails")));
        checkoutPage.enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        checkoutPage.enterPaymentMethodWithAgreementOption("cod", true);
        checkoutPage.confirmOrder();
        Boolean status = checkoutPage.verifyConfirmation();
        Assert.assertTrue(status);
    }

    @Test(groups ={"Regression", "Smoke"})
    public void shopAsLoginUserWithExistingAddress(){
        HomePage homePage = new HomePage(DriverFactory.getInstance().getDriver());
        CartPage cartPage = new CartPage(DriverFactory.getInstance().getDriver());
        AccountPage accountPage = new AccountPage(DriverFactory.getInstance().getDriver());

        accountPage.login(data.getValue(env+".username"),data.getValue(env+".password"));
        SearchProductPage searchProductPage = homePage.searchProduct(data.getValue(env+".shopProduct"));
        ProductDetailsPage productDetailsPage = searchProductPage.openSearchedProduct(data.getValue(env+".shopProduct"));
        productDetailsPage.updateQuantity(data.getValue((env+".quantity")));
        productDetailsPage.addToCart();
        homePage.openMenuOption("Shopping Cart");
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.enterBillingDetailsForLoginUser("existing",data.getValue(env+".existingAddress"));
        checkoutPage.enterDeliveryDetailsForLoginUser("existing",data.getValue(env+".existingAddress"));
        checkoutPage.enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        checkoutPage.enterPaymentMethodWithAgreementOption("cod", true);
        checkoutPage.confirmOrder();
        Boolean status = checkoutPage.verifyConfirmation();
        Assert.assertTrue(status);
        accountPage.logout();
    }

    @Test(groups ={"Regression"}, dependsOnMethods = { "shopAsLoginUserWithExistingAddress" })
    public void shopAsLoginUserWithNewAddress(){

        HomePage homePage = new HomePage(DriverFactory.getInstance().getDriver());
        CartPage cartPage = new CartPage(DriverFactory.getInstance().getDriver());
        AccountPage accountPage = new AccountPage(DriverFactory.getInstance().getDriver());

        accountPage.login(data.getValue(env+".username"),data.getValue(env+".password"));
        SearchProductPage searchProductPage = homePage.searchProduct(data.getValue(env+".shopProduct"));
        ProductDetailsPage productDetailsPage = searchProductPage.openSearchedProduct(data.getValue(env+".shopProduct"));
        productDetailsPage.updateQuantity(data.getValue((env+".quantity")));
        productDetailsPage.addToCart();
        homePage.openMenuOption("Shopping Cart");
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.enterBillingDetailsForLoginUser("new",data.getValue(env+".newBillingAddress"));
        checkoutPage.enterDeliveryDetailsForLoginUser("new",data.getValue(env+".newBillingAddress"));
        checkoutPage.enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        checkoutPage.enterPaymentMethodWithAgreementOption("cod", true);
        checkoutPage.confirmOrder();
        Boolean status = checkoutPage.verifyConfirmation();
        Assert.assertTrue(status);
        accountPage.logout();
    }

}
