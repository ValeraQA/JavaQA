package com.javaqa.impl;

import com.javaqa.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.javaqa.enums.Products.*;
import static com.javaqa.enums.UserCredentials.STANDARD_USER;
import static com.javaqa.pages.PagesHolder.*;
import static com.javaqa.utils.Constants.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class CheckoutPageTest extends BaseTest {

    @BeforeMethod
    public void loginToApp() {
        getLoginPage()
                .openPage()
                .enterCredential(STANDARD_USER.getUserLogin(), STANDARD_USER.getUserPass())
                .submitLogin();
        getInventoryPage()
                .verifyInventoryPageDisplayed();
    }

    @AfterMethod(alwaysRun = true)
    public void resetAppState() {
        getPageHeaderWidget()
                .clickOnBurgerMenu()
                .clickOnResetButton();
    }

    @Test
    public void verifyUserIsAbleToProcessCheckoutSuccessfully() {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        String zipCode = RandomStringUtils.randomNumeric(5);

        getInventoryPage()
                .clickOnAddToCartForProductWithName(BACKPACK.getProductName())
                .clickOnAddToCartForProductWithName(BOLT_T_SHIRT.getProductName())
                .clickOnAddToCartForProductWithName(BIKE_LIGHT.getProductName());

        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(3)
                .clickOnShoppingCartIcon()
                .verifyNumberOfItemsOnShoppingCartPage(3)
                .clickOnCheckoutButton()
                .verifyCheckoutPageDisplayed()
                .enterOrderInformationAndClickContinue(firstName, lastName, zipCode)
                .clickOnContinueButton()
                .verifyOverviewPageDisplayed()
                .verifyNumberOfItemsOnOverviewPage(3)
                .verifyProductsArePresentedOnOverviewPage(BACKPACK.getProductName(), BOLT_T_SHIRT.getProductName(), BIKE_LIGHT.getProductName())
                .verifyTotalValueOnOverviewPage(60.45)
                .clickOnFinishButton()
                .verifyCompletePageDisplayed()
                .clickOnBackHomeButton()
                .verifyInventoryPageDisplayed();
    }

    @Test
    public void verifyUserIsAbleToCancelCheckout() {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);
        String zipCode = RandomStringUtils.randomNumeric(5);

        getInventoryPage()
                .clickOnAddToCartForProductWithName(BACKPACK.getProductName())
                .clickOnAddToCartForProductWithName(BOLT_T_SHIRT.getProductName())
                .clickOnAddToCartForProductWithName(BIKE_LIGHT.getProductName());

        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(3)
                .clickOnShoppingCartIcon()
                .verifyNumberOfItemsOnShoppingCartPage(3)
                .clickOnCheckoutButton()
                .verifyCheckoutPageDisplayed()
                .enterOrderInformationAndClickContinue(firstName, lastName, zipCode)
                .clickOnContinueButton()
                .verifyOverviewPageDisplayed()
                .verifyNumberOfItemsOnOverviewPage(3)
                .verifyProductsArePresentedOnOverviewPage(BACKPACK.getProductName(), BOLT_T_SHIRT.getProductName(), BIKE_LIGHT.getProductName())
                .verifyTotalValueOnOverviewPage(60.45)
                .clickOnCancelCheckoutButton()
                .verifyInventoryPageDisplayed();
        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(3);
    }

    @Test
    public void verifyUserIsAbleToCancelCheckoutAndRemoveProductFromCart() {
        getInventoryPage()
                .clickOnAddToCartForProductWithName(BACKPACK.getProductName())
                .clickOnAddToCartForProductWithName(BOLT_T_SHIRT.getProductName())
                .clickOnAddToCartForProductWithName(BIKE_LIGHT.getProductName());

        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(3)
                .clickOnShoppingCartIcon()
                .verifyNumberOfItemsOnShoppingCartPage(3)
                .clickOnCheckoutButton()
                .verifyCheckoutPageDisplayed()
                .clickOnCancelCheckoutButton()
                .verifyShoppingCartPageDisplayed()
                .verifyNumberOfItemsOnShoppingCartPage(3)
                .clickOnRemoveForProductWithName(BOLT_T_SHIRT.getProductName())
                .verifyNumberOfItemsOnShoppingCartPage(2)
                .clickOnRemoveForProductWithName(BACKPACK.getProductName())
                .verifyNumberOfItemsOnShoppingCartPage(1)
                .clickOnRemoveForProductWithName(BIKE_LIGHT.getProductName())
                .verifyNoProductAreDisplayedOnShoppingCartPage();
    }

    @Test
    public void verifyValidationsArePresentedOnCheckoutInformation() {
        String firstName = RandomStringUtils.randomAlphabetic(5);
        String lastName = RandomStringUtils.randomAlphabetic(5);

        getInventoryPage()
                .clickOnAddToCartForProductWithName(BACKPACK.getProductName());

        getPageHeaderWidget()
                .clickOnShoppingCartIcon()
                .clickOnCheckoutButton()
                .verifyCheckoutPageDisplayed()
                .enterOrderInformationAndClickContinue(EMPTY, EMPTY, EMPTY)
                .clickOnContinueButton();
        getCheckoutPage()
                .verifyErrorMessageDisplayed(FIRST_NAME_REQUIRED)
                .verifyInputFieldsAreHighlightedRed()
                .clickOnCloseErrorButton()
                .verifyErrorMessageIsClosedAndFieldsAreNotHighlightedRed();

        getCheckoutPage()
                .enterOrderInformationAndClickContinue(firstName, EMPTY, EMPTY)
                .clickOnContinueButton();
        getCheckoutPage()
                .verifyErrorMessageDisplayed(LAST_NAME_REQUIRED)
                .verifyInputFieldsAreHighlightedRed()
                .clickOnCloseErrorButton()
                .verifyErrorMessageIsClosedAndFieldsAreNotHighlightedRed();

        getCheckoutPage()
                .enterOrderInformationAndClickContinue(firstName, lastName, EMPTY)
                .clickOnContinueButton();
        getCheckoutPage()
                .verifyErrorMessageDisplayed(ZIP_CODE_REQUIRED)
                .verifyInputFieldsAreHighlightedRed()
                .clickOnCloseErrorButton()
                .verifyErrorMessageIsClosedAndFieldsAreNotHighlightedRed();
    }
}
