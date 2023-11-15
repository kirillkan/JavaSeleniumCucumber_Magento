package com.test_project.pages;


import com.test_project.utility.BrowserUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

    @FindBy(xpath = "//td[@class='col qty']//input")
    private WebElement qtyCart;

    @FindBy(xpath = "(//button[@type='button'])[3]")
    private WebElement checkOutButton;



    public String getQtyVisibleInCartPage(){
        return qtyCart.getAttribute("value");
    }

    public void clickProceedToCheckoutButton(){
       BrowserUtil.waitforclickablility(checkOutButton,10);
        checkOutButton.click();
    }



}
