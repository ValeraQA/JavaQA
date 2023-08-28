package com.javaqa.pages.impl;

import com.javaqa.driver.WebDriverHolder;
import com.javaqa.exception.SwagLabsTafException;
import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement cartTitle;

    @FindBy(id = "continue-shopping")
    private WebElement continueShopping;
    @FindBy(id = "checkout")
    private WebElement checkout;
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    public ShoppingCartPage verifyShoppingCartPageDisplayed() {
        waitForPageLoaded();
        assertThat(cartTitle.isDisplayed()).as("Shopping Cart page is not displayed").isTrue();
        assertThat(continueShopping.isDisplayed()).as("Shopping Cart page is not displayed").isTrue();
        assertThat(checkout.isDisplayed()).as("Shopping Cart page is not displayed").isTrue();

        assertThat(cartTitle.getText()).as("Page title is wrong").isEqualTo("Your Cart");
        return this;
    }

    public ShoppingCartPage verifyNumberOfItemsOnShoppingCartPage(int numberOfItems) {
        assertThat(cartItems.size()).as("Number of items is not equal").isEqualTo(numberOfItems);
        return this;
    }

    public ShoppingCartPage verifyProductsArePresentedOnShoppingCartPage(String... productsName) {
        for (String productName : productsName) {
            WebElement productElement = getProductCardElementWithName(productName);
            assertThat(productElement.isDisplayed()).as("Product is not displayed: " + productElement).isTrue();
        }
        return this;
    }

    public ShoppingCartPage verifyNoProductAreDisplayedOnShoppingCartPage() {
        assertThat(WebDriverHolder.getInstance().findElements(By.className("cart_item")))
                .as("No products should be displayed").isEmpty();
        return this;
    }

    public InventoryPage clickOnContinueShoppingButton() {
        continueShopping.click();
        return PagesHolder.getInventoryPage();
    }

    public CheckoutPage clickOnCheckoutButton() {
        checkout.click();
        return PagesHolder.getCheckoutPage();
    }

    @SneakyThrows
    public ShoppingCartPage clickOnRemoveForProductWithName(String productName) {
        WebElement productCard = getProductCardElementWithName(productName);
        try {
            productCard.findElement(By.xpath(".//button[contains(text(),'Remove')]")).click();
        } catch (NoSuchElementException e) {
            throw new SwagLabsTafException("Button is not found for the product");
        }
        return this;
    }

    @SneakyThrows
    private WebElement getProductCardElementWithName(String productName) {
        return cartItems.stream()
                .filter(it -> it.findElement(By.xpath(".//div/a/div")).getText().contains(productName))
                .findFirst().orElseThrow(() -> new SwagLabsTafException("Product was not found: " + productName));
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return cartTitle;
    }
}
