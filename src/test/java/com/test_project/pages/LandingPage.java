package com.test_project.pages;

import com.test_project.utility.BrowserUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage{

    @FindBy(id = "search")
    private WebElement inputSearchTextField;

    @FindBy(xpath = "//div[@class='products wrapper grid products-grid']//li")
    private WebElement firstProduct;



    public void sendKeysToSearchAndEnter(String text){
        inputSearchTextField.sendKeys(text+Keys.ENTER);
    }

    public void clickFirstProduct(){
        BrowserUtil.waitforclickablility(firstProduct,10);
        firstProduct.click();
    }



}
