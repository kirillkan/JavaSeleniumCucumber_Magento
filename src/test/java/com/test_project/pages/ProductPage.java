package com.test_project.pages;

import com.test_project.utility.BrowserUtil;
import com.test_project.utility.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//input[@id='qty']")
    private WebElement qtyField;

    @FindBy(xpath = "//a[.='shopping cart']")
    private WebElement shoppingCartLink;

    @FindBy(xpath = "//span[@class='price']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='swatch-option text']")
    private WebElement productSelectFirstSize;

    @FindBy(xpath = "//div[@class='swatch-option color']")
    private WebElement productSelectFirstColor;


    public void enterQtyAndAddToCart(String qty) {
        qtyField.sendKeys(Keys.DELETE + qty);

        BrowserUtil.waitforclickablility(addToCartButton, 10);

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", addToCartButton);
    }

    public void youAddedToShoppingCartLinkClick() {
        BrowserUtil.waitForVisibility(shoppingCartLink, 10);
        shoppingCartLink.click();
    }

    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText().substring(1));
    }


    public void tryToSelectSizeAndColor() {
            try {
                BrowserUtil.waitforclickablility(productSelectFirstSize,10);
               productSelectFirstSize.click();
                BrowserUtil.waitforclickablility(productSelectFirstColor,10);
               productSelectFirstColor.click();
            } catch (NoSuchElementException e) {

            }
    }



}
