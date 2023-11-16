package com.test_project.steps;

import com.test_project.pages.*;
import com.test_project.utility.ConfigurationReader;
import com.test_project.utility.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class StepDefs {

    double actualSubtotalAfterCheckout;
    double expectedSubtotal;
    double productPrice;
    String quantityEnteredOnProductPage;
    String quantityVisibleOnShippingPage;
    String quantityVisibleOnShoppingCartPage;

    ProductPage productPage = new ProductPage();
    LandingPage landingPage = new LandingPage();
    ShippingPage shippingPage = new ShippingPage();
    ReviewAndPaymentsPage reviewAndPaymentsPage = new ReviewAndPaymentsPage();
    OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Given("user is on magento shop's landing page")
    public void userIsOnMagentoShopSLandingPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @And("user searches for {string}")
    public void userSearchesFor(String productCategory) {
        landingPage.sendKeysToSearchAndEnter(productCategory);
    }

    @And("user opens product page")
    public void userOpensFirstProductPage() {
        searchResultPage.clickFirstProduct();
    }


    @And("user sets {string} and clicks add to cart")
    public void userSetsAndClicksAddToCart(String productQuantity) {
        productPage.tryToSelectSizeAndColor();
        productPage.enterQtyAndAddToCart(productQuantity);
        productPrice = productPage.getProductPrice();
        expectedSubtotal = productPrice * Integer.parseInt(productQuantity);
        quantityEnteredOnProductPage = productQuantity;
    }

    @And("user clicks shopping cart link")
    public void userClicksShoppingCartLinkAndClicksNavigateToCheckout() {
        productPage.youAddedToShoppingCartLinkClick();
    }

    @And("user clicks proceed to checkout in shopping cart page")
    public void userClicksCheckoutInShoppingCartPage() {
        quantityVisibleOnShoppingCartPage = shoppingCartPage.getQtyVisibleInCartPage();
        shoppingCartPage.clickProceedToCheckoutButton();
    }

    @And("user enters valid user information and selects {string}")
    public void userEntersValidUserInformationAndSelects(String shippingMethod) {
        shippingPage.populateShippingAddressTextFieldsWithValidData();
        shippingPage.selectShippingMethodByText(shippingMethod);
        quantityVisibleOnShippingPage = shippingPage.getQtyVisibleInShippingPage();
        shippingPage.clickNextButton();
    }

    @Then("user performs checkout successfully and able to see {string}")
    public void userPerformsCheckoutSuccessfullyAndAbleToSee(String expectedConfirmationMsg) {
        actualSubtotalAfterCheckout = reviewAndPaymentsPage.getSubtotalAfterCheckout();
        reviewAndPaymentsPage.pressPlaceOrderButton();
        String actualConfirmationMsg = orderConfirmationPage.confirmationMsgText();

        //optional print statements
        System.out.println("----------------------------------------");
        System.out.println("expectedConfirmationMsg(expected!) = " + expectedConfirmationMsg);
        System.out.println("actualConfirmationMsg(actual) = " + actualConfirmationMsg);
        System.out.println("----------------------------------------");
        System.out.println("expectedSubtotal(expected!) = " + productPrice + " * " + quantityEnteredOnProductPage + " = " + expectedSubtotal);
        System.out.println("actualSubtotalAfterCheckout(actual) = " + actualSubtotalAfterCheckout);
        System.out.println("----------------------------------------");
        System.out.println("quantityEnteredOnProductPage(expected!) = " + quantityEnteredOnProductPage);
        System.out.println("quantityVisibleOnShippingPage(actual) = " + quantityVisibleOnShippingPage);
        System.out.println("----------------------------------------");
        System.out.println("quantityEnteredOnProductPage(expected!) = " + quantityEnteredOnProductPage);
        System.out.println("quantityVisibleOnShoppingCartPage(actual) = " + quantityVisibleOnShoppingCartPage);
        System.out.println("----------------------------------------");

        Assert.assertEquals(expectedConfirmationMsg, actualConfirmationMsg);
    }

    @Then("user can see proper value in order subtotal")
    public void userCanSeeProperValueInOrderSubtotal() {
        Assert.assertEquals(expectedSubtotal, actualSubtotalAfterCheckout, 0.0);
    }

    @Then("title is {string}")
    public void titleIs(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();

        //optional print statements
        System.out.println("expectedTitle = " + expectedTitle);
        System.out.println("actualTitle = " + actualTitle);
        System.out.println("----------------------------------------");

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Then("quantity chosen on product page matches with shipping page")
    public void quantityChosenOnProductPageMatchesWithCheckoutPage() {
        Assert.assertEquals(quantityEnteredOnProductPage, quantityVisibleOnShippingPage);
    }

    @Then("quantity chosen on product page matches with shopping cart page")
    public void quantityChosenOnProductPageMatchesWithShoppingCartPage() {
        Assert.assertEquals(quantityEnteredOnProductPage, quantityVisibleOnShoppingCartPage);
    }



}
