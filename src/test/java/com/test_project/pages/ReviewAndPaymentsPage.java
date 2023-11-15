package com.test_project.pages;


import com.test_project.utility.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReviewAndPaymentsPage extends BasePage{

    @FindBy(xpath = "//button[@class='action primary checkout']")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//span[@data-th='Cart Subtotal']")
    private WebElement cartSubtotalText;



    public void pressPlaceOrderButton(){
        //will do a force click on the required element
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", placeOrderButton);
    }

    public Double getSubtotalAfterCheckout(){
        return Double.parseDouble( cartSubtotalText.getText().substring(1));
    }



}
