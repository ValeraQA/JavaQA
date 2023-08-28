package com.javaqa.impl;

import com.javaqa.BaseTest;
import com.javaqa.enums.Products;
import com.javaqa.enums.Sorting;
import com.javaqa.exception.SwagLabsTafException;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.javaqa.enums.Products.BACKPACK;
import static com.javaqa.enums.Products.FLEECE_JACKET;
import static com.javaqa.enums.UserCredentials.STANDARD_USER;
import static com.javaqa.pages.PagesHolder.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InventoryPageTest extends BaseTest {

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
    public void verifyUserIsAbleToAddProductToShoppingCart() {
        getInventoryPage()
                .clickOnAddToCartForProductWithName(BACKPACK.getProductName())
                .userSeesRemoveFromCartButtonForProduct(BACKPACK.getProductName());
        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(1);

        getInventoryPage()
                .clickOnAddToCartForProductWithName(FLEECE_JACKET.getProductName())
                .userSeesRemoveFromCartButtonForProduct(FLEECE_JACKET.getProductName());
        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(2)
                .clickOnShoppingCartIcon()
                .verifyShoppingCartPageDisplayed()
                .verifyNumberOfItemsOnShoppingCartPage(2)
                .verifyProductsArePresentedOnShoppingCartPage(BACKPACK.getProductName(), FLEECE_JACKET.getProductName());
    }

    @Test
    public void verifyUserIsAbleToAddAndRemoveProductFromShoppingCart() {
        getInventoryPage()
                .clickOnAddToCartForProductWithName(BACKPACK.getProductName())
                .userSeesRemoveFromCartButtonForProduct(BACKPACK.getProductName());
        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(1)
                .clickOnShoppingCartIcon()
                .verifyShoppingCartPageDisplayed()
                .verifyNumberOfItemsOnShoppingCartPage(1)
                .verifyProductsArePresentedOnShoppingCartPage(BACKPACK.getProductName())
                .clickOnContinueShoppingButton()
                .clickOnRemoveForProductWithName(BACKPACK.getProductName());
        getPageHeaderWidget()
                .verifyNumberOfItemsOnTheCartBadge(0)
                .clickOnShoppingCartIcon()
                .verifyShoppingCartPageDisplayed()
                .verifyNoProductAreDisplayedOnShoppingCartPage();
    }

    @Test
    public void verifySortingDisplaysProductsCorrectly() {
        List<String> initialList = Products.getInitialProductsList();
        getInventoryPage()
                .checkProductOrderIsEqualToList(initialList);

        List<String> productsFromZtoA = Products.getProductsListFromZtoA();
        getInventoryPage()
                .clickOnSortingAndSelect(Sorting.NAME_Z_A)
                .checkProductOrderIsEqualToList(productsFromZtoA);

        List<String> productsFromAtoZ = Products.getProductsListFromAtoZ();
        getInventoryPage()
                .clickOnSortingAndSelect(Sorting.NAME_A_Z)
                .checkProductOrderIsEqualToList(productsFromAtoZ);

        List<String> productsFromLowToHighPrice = Products.getProductsListFromLowToHighPrice();
        getInventoryPage()
                .clickOnSortingAndSelect(Sorting.PRICE_L_H)
                .checkProductOrderIsEqualToList(productsFromLowToHighPrice);

        List<String> productsFromHighToLowPrice = Products.getProductsListFromHighToLowPrice();
        getInventoryPage()
                .clickOnSortingAndSelect(Sorting.PRICE_H_L)
                .checkProductOrderIsEqualToList(productsFromHighToLowPrice);
    }

    @Test
    public void verifyCatchSwagLabsTafExceptionForNotFoundProduct() {
        assertThatThrownBy(() ->
                getInventoryPage()
                        .clickOnAddToCartForProductWithName(RandomStringUtils.randomAlphabetic(10)))
                .isInstanceOf(SwagLabsTafException.class)
                .hasMessageContaining("Product was not found");
    }
}
