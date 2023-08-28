package com.javaqa.pages;

import com.javaqa.pages.impl.*;
import com.javaqa.pages.impl.widget.BurgerMenuWidget;
import com.javaqa.pages.impl.widget.PageHeaderWidget;

import java.util.Optional;

public class PagesHolder {

    private static LoginPage loginPage;
    private static InventoryPage inventoryPage;
    private static ShoppingCartPage shoppingCartPage;
    private static CheckoutPage checkoutPage;
    private static OverviewPage overviewPage;
    private static ProductPage productPage;
    private static CompletePage completePage;
    private static BurgerMenuWidget burgerMenuWidget;
    private static PageHeaderWidget pageHeaderWidget;

    public static LoginPage getLoginPage() {
        return Optional.ofNullable(loginPage).orElseGet(() -> loginPage = new LoginPage());
    }

    public static InventoryPage getInventoryPage() {
        return Optional.ofNullable(inventoryPage).orElseGet(() -> inventoryPage = new InventoryPage());
    }

    public static ShoppingCartPage getShoppingCartPage() {
        return Optional.ofNullable(shoppingCartPage).orElseGet(() -> shoppingCartPage = new ShoppingCartPage());
    }

    public static CheckoutPage getCheckoutPage() {
        return Optional.ofNullable(checkoutPage).orElseGet(() -> checkoutPage = new CheckoutPage());
    }

    public static OverviewPage getOverviewPage() {
        return Optional.ofNullable(overviewPage).orElseGet(() -> overviewPage = new OverviewPage());
    }

    public static ProductPage getProductPage() {
        return Optional.ofNullable(productPage).orElseGet(() -> productPage = new ProductPage());
    }

    public static CompletePage getCompletePage() {
        return Optional.ofNullable(completePage).orElseGet(() -> completePage = new CompletePage());
    }

    public static BurgerMenuWidget getBurgerMenuWidget() {
        return Optional.ofNullable(burgerMenuWidget).orElseGet(() -> burgerMenuWidget = new BurgerMenuWidget());
    }

    public static PageHeaderWidget getPageHeaderWidget() {
        return Optional.ofNullable(pageHeaderWidget).orElseGet(() -> pageHeaderWidget = new PageHeaderWidget());
    }
}
