package com.javaqa.pages.impl;

import com.javaqa.enums.Sorting;
import com.javaqa.exception.SwagLabsTafException;
import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement productsTitle;
    @FindBy(className = "product_sort_container")
    private WebElement sortButton;
    @FindBy(id = "inventory_container")
    private WebElement inventoryProducts;
    @FindBy(className = "inventory_item")
    private List<WebElement> allInventoryItems;

    public InventoryPage verifyInventoryPageDisplayed() {
        waitForPageLoaded();
        assertThat(productsTitle.isDisplayed()).as("Inventory page is not displayed").isTrue();
        assertThat(sortButton.isDisplayed()).as("Inventory page is not displayed").isTrue();
        assertThat(inventoryProducts.isDisplayed()).as("Inventory page is not displayed").isTrue();
        return this;
    }

    public ProductPage clickOnProductCardWithName(String productName) {
        WebElement productCard = getProductCardElementWithName(productName);
        productCard.findElement(By.xpath(".//div/div/a")).click();

        return PagesHolder.getProductPage();
    }

    public InventoryPage clickOnSortingAndSelect(Sorting sortingType) {
        Select select = new Select(sortButton);
        select.selectByValue(sortingType.getSortingValue());
        return this;
    }

    public InventoryPage checkProductOrderIsEqualToList(List<String> productsList) {
        assertThat(getAllProductsFromPage()).as("Products list is not equal").isEqualTo(productsList);
        return this;
    }

    @SneakyThrows
    public InventoryPage userSeesRemoveFromCartButtonForProduct(String productName) {
        WebElement productCard = getProductCardElementWithName(productName);
        try {
            assertThat(productCard.findElement(By.xpath(".//button[contains(text(),'Remove')]")).isDisplayed()).isTrue();
        } catch (NoSuchElementException e) {
            throw new SwagLabsTafException("Button is not found for the product");
        }
        return this;
    }

    @SneakyThrows
    public InventoryPage clickOnAddToCartForProductWithName(String productName) {
        WebElement productCard = getProductCardElementWithName(productName);
        try {
            productCard.findElement(By.xpath(".//button[contains(text(),'Add to cart')]")).click();
        } catch (NoSuchElementException e) {
            throw new SwagLabsTafException("Button is not found for the product");
        }
        return this;
    }

    @SneakyThrows
    public InventoryPage clickOnRemoveForProductWithName(String productName) {
        WebElement productCard = getProductCardElementWithName(productName);
        try {
            productCard.findElement(By.xpath(".//button[contains(text(),'Remove')]")).click();
        } catch (NoSuchElementException e) {
            throw new SwagLabsTafException("Button is not found for the product");
        }
        return this;
    }

    private List<String> getAllProductsFromPage() {
        return allInventoryItems.stream().map(it -> it.findElement(By.xpath(".//a/div")).getText()).toList();
    }

    @SneakyThrows
    private WebElement getProductCardElementWithName(String productName) {
        return allInventoryItems.stream()
                .filter(it -> it.findElement(By.xpath(".//a/div")).getText().contains(productName))
                .findFirst().orElseThrow(() -> new SwagLabsTafException("Product was not found: " + productName));
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return productsTitle;
    }
}
