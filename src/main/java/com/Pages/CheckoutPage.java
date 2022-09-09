package com.Pages;

import com.utils.CommonUtil;
import org.openqa.selenium.WebDriver;


public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectCheckoutAsNewCustomer(String option){
        Boolean status = getInstance(CommonUtil.class).elementIsSelected("CheckoutPage.checkoutOptions", option);
        if(status==false)
            getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutOptions", option);
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "account");
        addSleep(2000);
    }

    public void selectCheckoutAsReturningCustomer(String username, String password){
        getInstance(CommonUtil.class).sendKeysToElement("AccountPage.inputEmail", username);
        getInstance(CommonUtil.class).sendKeysToElement("AccountPage.inputPassword", password);
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "login");
    }

    private void enterAddress(String address){
        String[] addressDetails = address.split(",");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",addressDetails[0],"address-1");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",addressDetails[1],"city");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",addressDetails[2],"postcode");
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.billingDetails",addressDetails[3],"country");
        addSleep(1000);
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.billingDetails",addressDetails[4],"zone");
    }

    private void enterUserDetails(String userDetails){
        String[] details = userDetails.split(",");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[0],"firstname");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[1],"lastname");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[2],"email");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[3],"telephone");
    }

    private void deliverDetailsCheckbox(boolean option){
        Boolean status = getInstance(CommonUtil.class).elementIsSelected("CheckoutPage.billingDeliveryCheckbox");
        if((option == true && status ==false) || (option == false && status ==true))
            getInstance(CommonUtil.class).clickElement("CheckoutPage.billingDeliveryCheckbox");
    }

    public void enterBillingDetailsForGuest(String userDetails, String address, Boolean option){
        enterUserDetails(userDetails);
        enterAddress(address);
        deliverDetailsCheckbox(option);
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "guest");
        addSleep(2000);
    }

    private void selectBillingDetailsOptionForLoginUser(String option){
        Boolean status = getInstance(CommonUtil.class).elementIsSelected("CheckoutPage.billingOption", option);
        if(!status)
            getInstance(CommonUtil.class).clickElement("CheckoutPage.billingOption", option);
        addSleep(2000);
    }

    private void selectDeliveryDetailsOptionForLoginUser(String option){
        Boolean status = getInstance(CommonUtil.class).elementIsSelected("CheckoutPage.deliveryOption", option);
        if(!status)
            getInstance(CommonUtil.class).clickElement("CheckoutPage.deliveryOption", option);
        addSleep(2000);
    }

    private void chooseAddressForLoginUser(String address){
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.addressDropdown", address);
    }

    private void enterBillingAddressForLoginUser(String billingAddress){
        String[] details = billingAddress.split(",");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[0],"firstname");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[1],"lastname");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[2],"address-1");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[3],"city");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.billingDetails",details[4],"postcode");
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.billingDetails",details[5],"country");
        addSleep(1000);
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.billingDetails",details[6],"zone");
    }

    public void enterBillingDetailsForLoginUser(String option, String billingAddress){
        selectBillingDetailsOptionForLoginUser(option);
        switch (option){
            case "new":
                enterBillingAddressForLoginUser(billingAddress);
                break;
            case "existing":
                chooseAddressForLoginUser(billingAddress);
                break;
        }
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "payment-address");
        addSleep(2000);
    }

    public void enterDeliveryDetailsForLoginUser(String option, String deliveryAddress){
        selectDeliveryDetailsOptionForLoginUser(option);
        switch (option){
            case "new":
                enterDeliverAddress(deliveryAddress);
                break;
            case "existing":
                chooseAddressForLoginUser(deliveryAddress);
                break;
        }
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "shipping-address");
        addSleep(2000);
    }


    public void checkoutPanels(String panelName,String option, String buttonName){
        Boolean status = getInstance(CommonUtil.class).elementIsEnabled("CheckoutPage.checkoutButtons", buttonName);

        if((option.equals("open") && status==false) || (option.equals("close") && status==true))
            getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutPanels", panelName);
    }

    public void enterDeliveryDetailsForGuest(String deliveryDetails){
        enterDeliverAddress(deliveryDetails);
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "guest-shipping");
        addSleep(2000);
    }

    private void enterDeliverAddress(String deliveryDetails){
        String[] details = deliveryDetails.split(",");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.deliveryDetails",details[0],"firstname");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.deliveryDetails",details[1],"lastname");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.deliveryDetails",details[2],"address-1");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.deliveryDetails",details[3],"city");
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.deliveryDetails",details[4],"postcode");
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.deliveryDetails",details[5],"country");
        addSleep(1000);
        getInstance(CommonUtil.class).selectDropdown("CheckoutPage.deliveryDetails",details[6],"zone");
    }

    private void enterComments(String deliveryComments){
        getInstance(CommonUtil.class).sendKeysToElement("CheckoutPage.deliveryComment",deliveryComments);
    }

    public void enterDeliveryMethod(String deliveryComments){
        enterComments(deliveryComments);
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "shipping-method");
        addSleep(2000);
    }

    public void enterPaymentMethodWithAgreementOption(String option, Boolean agreement){
        Boolean status = getInstance(CommonUtil.class).elementIsSelected("CheckoutPage.paymentMethod", option);
        if(!status)
            getInstance(CommonUtil.class).clickElement("CheckoutPage.paymentMethod", option);

        status = getInstance(CommonUtil.class).elementIsSelected("CheckoutPage.paymentAgree");
        if((status==false && agreement==true) || (status==true && agreement==false))
            getInstance(CommonUtil.class).clickElement("CheckoutPage.paymentAgree");
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "payment-method");
        addSleep(2000);
    }

    public Boolean verifyWarningAlertForAgreement(){
        Boolean status = getInstance(CommonUtil.class).elementIsDisplayed("AccountPage.warningText");
        return status;
    }

    public void confirmOrder(){
        getInstance(CommonUtil.class).clickElement("CheckoutPage.checkoutButtons", "confirm");
        addSleep(2000);
    }

    public Boolean verifyConfirmation(){
        Boolean status = getInstance(CommonUtil.class).elementIsDisplayed("CheckoutPage.orderConfirmation");
        return status;
    }

}
