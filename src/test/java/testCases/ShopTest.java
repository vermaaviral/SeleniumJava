package testCases;

import com.Pages.*;
import com.utils.CommonUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShopTest extends BaseTest{

    @Test(groups ={"Regression", "Smoke"})
    public void shopWithoutLoginAsGuest(){
        page.getInstance(HomePage.class).searchProduct(data.getValue(env+".shopProduct"));
        page.getInstance(SearchProductPage.class).openSearchedProduct(data.getValue(env+".shopProduct"));
        page.getInstance(ProductDetailsPage.class).updateQuantity("2");
        page.getInstance(ProductDetailsPage.class).addToCart();
        page.getInstance(CommonUtil.class).assertEqualString(page.getInstance(HomePage.class).getCartTotal(), "$246.40");
        page.getInstance(HomePage.class).openMenuOption("Shopping Cart");
        page.getInstance(CartPage.class).proceedToCheckout();
        page.getInstance(CheckoutPage.class).selectCheckoutAsNewCustomer("guest");
        page.getInstance(CheckoutPage.class).enterBillingDetailsForGuest(data.getValue((env+".guestDetails")),data.getValue((env+".guestAddress")), true);
        page.getInstance(CheckoutPage.class).enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        page.getInstance(CheckoutPage.class).enterPaymentMethodWithAgreementOption("cod", true);
        page.getInstance(CheckoutPage.class).confirmOrder();
        boolean status = page.getInstance(CheckoutPage.class).verifyConfirmation();
        Assert.assertTrue(status);
    }

    @Test(groups ={"Regression"})
    public void verifyWarningOnPaymentWithoutAgreeTerms(){
        page.getInstance(HomePage.class).searchProduct(data.getValue(env+".shopProduct"));
        page.getInstance(SearchProductPage.class).openSearchedProduct(data.getValue(env+".shopProduct"));
        page.getInstance(ProductDetailsPage.class).updateQuantity("2");
        page.getInstance(ProductDetailsPage.class).addToCart();
        page.getInstance(HomePage.class).openMenuOption("Shopping Cart");
        page.getInstance(CartPage.class).proceedToCheckout();
        page.getInstance(CheckoutPage.class).selectCheckoutAsNewCustomer("guest");
        page.getInstance(CheckoutPage.class).enterBillingDetailsForGuest(data.getValue((env+".guestDetails")),data.getValue((env+".guestAddress")), true);
        page.getInstance(CheckoutPage.class).enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        page.getInstance(CheckoutPage.class).enterPaymentMethodWithAgreementOption("cod", false);
        Boolean status = page.getInstance(CheckoutPage.class).verifyWarningAlertForAgreement();
        Assert.assertTrue(status);
    }

    @Test(groups ={"Regression"})
    public void shopAsGuestWithBillingAndDeliveryDetailsDifferent(){
        page.getInstance(HomePage.class).searchProduct(data.getValue(env+".shopProduct"));
        page.getInstance(SearchProductPage.class).openSearchedProduct(data.getValue(env+".shopProduct"));
        page.getInstance(ProductDetailsPage.class).updateQuantity("2");
        page.getInstance(ProductDetailsPage.class).addToCart();
        page.getInstance(HomePage.class).openMenuOption("Shopping Cart");
        page.getInstance(CartPage.class).proceedToCheckout();
        page.getInstance(CheckoutPage.class).selectCheckoutAsNewCustomer("guest");
        page.getInstance(CheckoutPage.class).enterBillingDetailsForGuest(data.getValue((env+".guestDetails")),data.getValue((env+".guestAddress")), false);
        page.getInstance(CheckoutPage.class).enterDeliveryDetailsForGuest(data.getValue((env+".deliveryDetails")));
        page.getInstance(CheckoutPage.class).enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        page.getInstance(CheckoutPage.class).enterPaymentMethodWithAgreementOption("cod", true);
        page.getInstance(CheckoutPage.class).confirmOrder();
        Boolean status = page.getInstance(CheckoutPage.class).verifyConfirmation();
        Assert.assertTrue(status);
    }

    @Test(groups ={"Regression", "Smoke"})
    public void shopAsLoginUserWithExistingAddress(){
        page.getInstance(AccountPage.class).login(data.getValue(env+".username"),data.getValue(env+".password"));
        page.getInstance(HomePage.class).searchProduct(data.getValue(env+".shopProduct"));
        page.getInstance(SearchProductPage.class).openSearchedProduct(data.getValue(env+".shopProduct"));
        page.getInstance(ProductDetailsPage.class).updateQuantity("2");
        page.getInstance(ProductDetailsPage.class).addToCart();
        page.getInstance(HomePage.class).openMenuOption("Shopping Cart");
        page.getInstance(CartPage.class).proceedToCheckout();
        page.getInstance(CheckoutPage.class).enterBillingDetailsForLoginUser("existing",data.getValue(env+".existingAddress"));
        page.getInstance(CheckoutPage.class).enterDeliveryDetailsForLoginUser("existing",data.getValue(env+".existingAddress"));
        page.getInstance(CheckoutPage.class).enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        page.getInstance(CheckoutPage.class).enterPaymentMethodWithAgreementOption("cod", true);
        page.getInstance(CheckoutPage.class).confirmOrder();
        Boolean status = page.getInstance(CheckoutPage.class).verifyConfirmation();
        Assert.assertTrue(status);
        page.getInstance(AccountPage.class).logout();
    }

    @Test(groups ={"Regression"}, dependsOnMethods = { "shopAsLoginUserWithExistingAddress" })
    public void shopAsLoginUserWithNewAddress(){
        page.getInstance(AccountPage.class).login(data.getValue(env+".username"),data.getValue(env+".password"));
        page.getInstance(HomePage.class).searchProduct(data.getValue(env+".shopProduct"));
        page.getInstance(SearchProductPage.class).openSearchedProduct(data.getValue(env+".shopProduct"));
        page.getInstance(ProductDetailsPage.class).updateQuantity("2");
        page.getInstance(ProductDetailsPage.class).addToCart();
        page.getInstance(HomePage.class).openMenuOption("Shopping Cart");
        page.getInstance(CartPage.class).proceedToCheckout();
        page.getInstance(CheckoutPage.class).enterBillingDetailsForLoginUser("new",data.getValue(env+".newBillingAddress"));
        page.getInstance(CheckoutPage.class).enterDeliveryDetailsForLoginUser("new",data.getValue(env+".newBillingAddress"));
        page.getInstance(CheckoutPage.class).enterDeliveryMethod(data.getValue((env+".deliveryInstruction")));
        page.getInstance(CheckoutPage.class).enterPaymentMethodWithAgreementOption("cod", true);
        page.getInstance(CheckoutPage.class).confirmOrder();
        Boolean status = page.getInstance(CheckoutPage.class).verifyConfirmation();
        Assert.assertTrue(status);
        page.getInstance(AccountPage.class).logout();
    }

}
