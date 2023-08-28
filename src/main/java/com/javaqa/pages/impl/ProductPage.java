package com.javaqa.pages.impl;

import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage extends BasePage {

    @FindBy(id = "back-to-products")
    private WebElement backToInventory;

    @FindBy(className = "inventory_details_name")
    private WebElement productName;
    @FindBy(className = "inventory_details_desc")
    private WebElement productDesc;
    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;
    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;
    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    private WebElement removeFromCartButton;

    public ProductPage verifyProductPageDisplayedForProduct(String name, String price) {
        waitForPageLoaded();
        assertThat(backToInventory.isDisplayed()).as("Product page is not displayed").isTrue();
        assertThat(productName.isDisplayed()).as("Product page is not displayed").isTrue();
        assertThat(productDesc.isDisplayed()).as("Product page is not displayed").isTrue();
        assertThat(productPrice.isDisplayed()).as("Product page is not displayed").isTrue();

        assertThat(productName.getText()).as("Product name not matched").isEqualTo(name);
        assertThat(productPrice.getText()).as("Product price not matched").isEqualTo("$" + price);
        return this;
    }

    public InventoryPage clickOnBackToInventoryButton() {
        backToInventory.click();
        return PagesHolder.getInventoryPage();
    }

    public ProductPage clickOnAddToCartButton() {
        addToCartButton.click();
        return PagesHolder.getProductPage();
    }

    public ProductPage clickOnRemoveButton() {
        removeFromCartButton.click();
        return PagesHolder.getProductPage();
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return productName;
    }
}
