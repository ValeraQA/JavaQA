package com.javaqa.impl;

import com.javaqa.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.javaqa.enums.Products.BIKE_LIGHT;
import static com.javaqa.enums.Products.ONESIE;
import static com.javaqa.enums.UserCredentials.STANDARD_USER;
import static com.javaqa.pages.PagesHolder.*;

public class ProductPageTest extends BaseTest {

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
    public void verifyUserIsAbleToCheckProductItemPages() {
        getInventoryPage()
                .clickOnProductCardWithName(BIKE_LIGHT.getProductName())
                .verifyProductPageDisplayedForProduct(BIKE_LIGHT.getProductName(), BIKE_LIGHT.getProductPrice().toString())
                .clickOnBackToInventoryButton()
                .clickOnProductCardWithName(ONESIE.getProductName())
                .verifyProductPageDisplayedForProduct(ONESIE.getProductName(), ONESIE.getProductPrice().toString());
    }

    @Test
    public void verifyUserIsAbleToAddProductFromProductsPage() {
        getInventoryPage()
                .clickOnProductCardWithName(BIKE_LIGHT.getProductName())
                .verifyProductPageDisplayedForProduct(BIKE_LIGHT.getProductName(), BIKE_LIGHT.getProductPrice().toString())
                .clickOnAddToCartButton();

        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(1)
                .clickOnShoppingCartIcon()
                .verifyShoppingCartPageDisplayed()
                .verifyNumberOfItemsOnShoppingCartPage(1)
                .verifyProductsArePresentedOnShoppingCartPage(BIKE_LIGHT.getProductName());
    }

    @Test
    public void verifyUserIsAbleToAddAndRemoveProductFromProductsPage() {
        getInventoryPage()
                .clickOnAddToCartForProductWithName(ONESIE.getProductName());

        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(1)
                .clickOnShoppingCartIcon()
                .verifyShoppingCartPageDisplayed()
                .verifyNumberOfItemsOnShoppingCartPage(1)
                .verifyProductsArePresentedOnShoppingCartPage(ONESIE.getProductName())
                .clickOnContinueShoppingButton();

        getInventoryPage()
                .clickOnProductCardWithName(ONESIE.getProductName())
                .verifyProductPageDisplayedForProduct(ONESIE.getProductName(), ONESIE.getProductPrice().toString())
                .clickOnRemoveButton();

        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(0)
                .clickOnShoppingCartIcon()
                .verifyShoppingCartPageDisplayed()
                .verifyNoProductAreDisplayedOnShoppingCartPage();
    }
}
