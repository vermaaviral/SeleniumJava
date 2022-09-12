package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;


public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectCheckoutAsNewCustomer(String option){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsSelected("CheckoutPage.checkoutOptions", option);
        if(status==false)
            util.clickElement("CheckoutPage.checkoutOptions", option);
        util.clickElement("CheckoutPage.checkoutButtons", "account");
        addSleep(2000);
    }

    public void selectCheckoutAsReturningCustomer(String username, String password){
        CommonUtil util = new CommonUtil(driver);
        util.sendKeysToElement("AccountPage.inputEmail", username);
        util.sendKeysToElement("AccountPage.inputPassword", password);
        util.clickElement("CheckoutPage.checkoutButtons", "login");
    }

    private void enterAddress(String address){
        CommonUtil util = new CommonUtil(driver);
        String[] addressDetails = address.split(",");
        util.sendKeysToElement("CheckoutPage.billingDetails",addressDetails[0],"address-1");
        util.sendKeysToElement("CheckoutPage.billingDetails",addressDetails[1],"city");
        util.sendKeysToElement("CheckoutPage.billingDetails",addressDetails[2],"postcode");
        util.selectDropdown("CheckoutPage.billingDetails",addressDetails[3],"country");
        addSleep(1000);
        util.selectDropdown("CheckoutPage.billingDetails",addressDetails[4],"zone");
    }

    private void enterUserDetails(String userDetails){
        CommonUtil util = new CommonUtil(driver);
        String[] details = userDetails.split(",");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[0],"firstname");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[1],"lastname");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[2],"email");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[3],"telephone");
    }

    private void deliverDetailsCheckbox(boolean option){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsSelected("CheckoutPage.billingDeliveryCheckbox");
        if((option == true && status ==false) || (option == false && status ==true))
            util.clickElement("CheckoutPage.billingDeliveryCheckbox");
    }

    public void enterBillingDetailsForGuest(String userDetails, String address, Boolean option){
        CommonUtil util = new CommonUtil(driver);
        enterUserDetails(userDetails);
        enterAddress(address);
        deliverDetailsCheckbox(option);
        util.clickElement("CheckoutPage.checkoutButtons", "guest");
        addSleep(2000);
    }

    private void selectBillingDetailsOptionForLoginUser(String option){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsSelected("CheckoutPage.billingOption", option);
        if(!status)
            util.clickElement("CheckoutPage.billingOption", option);
        addSleep(2000);
    }

    private void selectDeliveryDetailsOptionForLoginUser(String option){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsSelected("CheckoutPage.deliveryOption", option);
        if(!status)
            util.clickElement("CheckoutPage.deliveryOption", option);
        addSleep(2000);
    }

    private void chooseAddressForLoginUser(String address){
        CommonUtil util = new CommonUtil(driver);
        util.selectDropdown("CheckoutPage.addressDropdown", address);
    }

    private void enterBillingAddressForLoginUser(String billingAddress){
        CommonUtil util = new CommonUtil(driver);
        String[] details = billingAddress.split(",");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[0],"firstname");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[1],"lastname");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[2],"address-1");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[3],"city");
        util.sendKeysToElement("CheckoutPage.billingDetails",details[4],"postcode");
        util.selectDropdown("CheckoutPage.billingDetails",details[5],"country");
        addSleep(1000);
        util.selectDropdown("CheckoutPage.billingDetails",details[6],"zone");
    }

    public void enterBillingDetailsForLoginUser(String option, String billingAddress){
        CommonUtil util = new CommonUtil(driver);
        selectBillingDetailsOptionForLoginUser(option);
        switch (option){
            case "new":
                enterBillingAddressForLoginUser(billingAddress);
                break;
            case "existing":
                chooseAddressForLoginUser(billingAddress);
                break;
        }
        util.clickElement("CheckoutPage.checkoutButtons", "payment-address");
        addSleep(2000);
    }

    public void enterDeliveryDetailsForLoginUser(String option, String deliveryAddress){
        CommonUtil util = new CommonUtil(driver);
        selectDeliveryDetailsOptionForLoginUser(option);
        switch (option){
            case "new":
                enterDeliverAddress(deliveryAddress);
                break;
            case "existing":
                chooseAddressForLoginUser(deliveryAddress);
                break;
        }
        util.clickElement("CheckoutPage.checkoutButtons", "shipping-address");
        addSleep(2000);
    }


    public void checkoutPanels(String panelName,String option, String buttonName){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsEnabled("CheckoutPage.checkoutButtons", buttonName);

        if((option.equals("open") && status==false) || (option.equals("close") && status==true))
            util.clickElement("CheckoutPage.checkoutPanels", panelName);
    }

    public void enterDeliveryDetailsForGuest(String deliveryDetails){
        CommonUtil util = new CommonUtil(driver);
        enterDeliverAddress(deliveryDetails);
        util.clickElement("CheckoutPage.checkoutButtons", "guest-shipping");
        addSleep(2000);
    }

    private void enterDeliverAddress(String deliveryDetails){
        CommonUtil util = new CommonUtil(driver);
        String[] details = deliveryDetails.split(",");
        util.sendKeysToElement("CheckoutPage.deliveryDetails",details[0],"firstname");
        util.sendKeysToElement("CheckoutPage.deliveryDetails",details[1],"lastname");
        util.sendKeysToElement("CheckoutPage.deliveryDetails",details[2],"address-1");
        util.sendKeysToElement("CheckoutPage.deliveryDetails",details[3],"city");
        util.sendKeysToElement("CheckoutPage.deliveryDetails",details[4],"postcode");
        util.selectDropdown("CheckoutPage.deliveryDetails",details[5],"country");
        addSleep(1000);
        util.selectDropdown("CheckoutPage.deliveryDetails",details[6],"zone");
    }

    private void enterComments(String deliveryComments){
        CommonUtil util = new CommonUtil(driver);
        util.sendKeysToElement("CheckoutPage.deliveryComment",deliveryComments);
    }

    public void enterDeliveryMethod(String deliveryComments){
        CommonUtil util = new CommonUtil(driver);
        enterComments(deliveryComments);
        util.clickElement("CheckoutPage.checkoutButtons", "shipping-method");
        addSleep(2000);
    }

    public void enterPaymentMethodWithAgreementOption(String option, Boolean agreement){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsSelected("CheckoutPage.paymentMethod", option);
        if(!status)
            util.clickElement("CheckoutPage.paymentMethod", option);

        status = util.elementIsSelected("CheckoutPage.paymentAgree");
        if((status==false && agreement==true) || (status==true && agreement==false))
            util.clickElement("CheckoutPage.paymentAgree");
        util.clickElement("CheckoutPage.checkoutButtons", "payment-method");
        addSleep(2000);
    }

    public Boolean verifyWarningAlertForAgreement(){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsDisplayed("AccountPage.warningText");
        return status;
    }

    public void confirmOrder(){
        CommonUtil util = new CommonUtil(driver);
        util.clickElement("CheckoutPage.checkoutButtons", "confirm");
        addSleep(2000);
    }

    public Boolean verifyConfirmation(){
        CommonUtil util = new CommonUtil(driver);
        Boolean status = util.elementIsDisplayed("CheckoutPage.orderConfirmation");
        return status;
    }

}
