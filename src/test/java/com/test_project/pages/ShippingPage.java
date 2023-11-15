package com.test_project.pages;


import com.test_project.utility.BrowserUtil;
import com.test_project.utility.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class ShippingPage extends BasePage{

    @FindBy(xpath = "//input[contains(@data-bind, 'textInput:')]")
    private WebElement inputCustomerEmail;

    @FindBy(xpath = "//*[@name='firstname']")
    private WebElement inputFirstname;

    @FindBy(xpath = "//*[@name='lastname']")
    private WebElement inputLastname;

    @FindBy(xpath = "//*[@name='street[0]']")
    private WebElement inputStreetAddress;

    @FindBy(xpath = "//*[@name='city']")
    private WebElement inputCity;

    @FindBy(xpath = "//*[@name='region_id']")
    private WebElement selectStateProvince;

    @FindBy(xpath = "//*[@name='postcode']")
    private WebElement inputPostcode;

    @FindBy(xpath = "//*[@name='country_id']")
    private WebElement selectCountry;

    @FindBy(xpath = "//*[@name='telephone']")
    private WebElement inputTelephone;

    @FindBy(xpath = "//input[@name='ko_unique_1']")
    private WebElement bestWayRadio;

    @FindBy(xpath = "//input[@name='ko_unique_2']")
    private WebElement flatRateRadio;

    @FindBy(xpath = "//span[@data-bind=\"i18n: 'Next'\"]")
    private WebElement nextButton;

    @FindBy(xpath = "//div[@class='block items-in-cart']")
    private WebElement orderSummaryExpandButton;

    @FindBy(xpath = "//div[@class='product-item-name-block']//span[@class='value']")
    private WebElement productQty;



    public String getQtyVisibleInShippingPage(){
        BrowserUtil.waitforclickablility(orderSummaryExpandButton,10);
        orderSummaryExpandButton.click();
        return productQty.getAttribute("textContent");
    }

    public void clickNextButton(){
        BrowserUtil.waitforclickablility(nextButton,10);
        nextButton.click();
    }

    public void populateShippingAddressTextFieldsWithValidData(){
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/testData/userMockTestData.xlsx","data1");
        List<Map<String, String>> dataList = excelUtil.getDataList();
        Map<String, String> dataMapUser1 = dataList.get(0);

        inputCustomerEmail.sendKeys(dataMapUser1.get("email"));
        inputFirstname.sendKeys(dataMapUser1.get("first name"));
        inputLastname.sendKeys(dataMapUser1.get("last name"));
        inputStreetAddress.sendKeys(dataMapUser1.get("address"));
        inputCity.sendKeys(dataMapUser1.get("city"));

        Select selectState = new Select(selectStateProvince);
        selectState.selectByVisibleText(dataMapUser1.get("state"));

        inputPostcode.sendKeys(dataMapUser1.get("zipcode"));

        Select selectCounty = new Select(selectCountry);
        selectCounty.selectByVisibleText(dataMapUser1.get("country"));

        inputTelephone.sendKeys(dataMapUser1.get("phone"));
    }
    
    public void selectShippingMethodByText(String shippingMethod){
        if(shippingMethod.equals("Best Way")){
            BrowserUtil.waitforclickablility(bestWayRadio,10);
            bestWayRadio.click();
        } else if (shippingMethod.equals("Flat Rate")) {
            BrowserUtil.waitforclickablility(flatRateRadio,10);
            flatRateRadio.click();
        }else {
            System.out.println("invalid input!");
        }
    }



}
