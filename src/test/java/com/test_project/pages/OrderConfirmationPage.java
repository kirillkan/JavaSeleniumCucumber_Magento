package com.test_project.pages;

import com.test_project.utility.BrowserUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage {

    @FindBy(xpath = "//span[@class='base']")
    private WebElement confirmationMsg;

    public String confirmationMsgText(){
        BrowserUtil.waitForInTitle("Success Page",10);
        return confirmationMsg.getText();
    }



}
