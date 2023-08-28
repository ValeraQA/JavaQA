package com.javaqa.pages.impl;

import com.javaqa.exception.SwagLabsTafException;
import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OverviewPage extends BasePage {

    @FindBy(className = "title")
    private WebElement cartTitle;
    @FindBy(className = "summary_info")
    private WebElement summaryInfo;
    @FindBy(className = "summary_total_label")
    private WebElement summaryTotalLabel;
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "cancel")
    private WebElement cancelCheckout;
    @FindBy(id = "finish")
    private WebElement finishButton;

    public OverviewPage verifyOverviewPageDisplayed() {
        waitForPageLoaded();
        assertThat(cartTitle.isDisplayed()).as("Overview page is not displayed").isTrue();
        assertThat(finishButton.isDisplayed()).as("Overview page is not displayed").isTrue();
        assertThat(summaryInfo.isDisplayed()).as("Overview page is not displayed").isTrue();
        assertThat(summaryTotalLabel.isDisplayed()).as("Overview page is not displayed").isTrue();

        assertThat(cartTitle.getText()).as("Page title is wrong").isEqualTo("Checkout: Overview");
        return this;
    }

    public OverviewPage verifyProductsArePresentedOnOverviewPage(String... productsName) {
        for (String productName : productsName) {
            WebElement productElement = getProductCardElementWithName(productName);
            assertThat(productElement.isDisplayed()).as("Product is not displayed: " + productElement).isTrue();
        }
        return this;
    }

    public OverviewPage verifyNumberOfItemsOnOverviewPage(int numberOfItems) {
        assertThat(cartItems.size()).as("Number of items is not equal").isEqualTo(numberOfItems);
        return this;
    }

    public OverviewPage verifyTotalValueOnOverviewPage(double totalValue) {
        assertThat(summaryTotalLabel.getText()).as("Total values are not equal").isEqualTo("Total: $" + totalValue);
        return this;
    }

    public InventoryPage clickOnCancelCheckoutButton() {
        cancelCheckout.click();
        return PagesHolder.getInventoryPage();
    }

    public CompletePage clickOnFinishButton() {
        finishButton.click();
        return PagesHolder.getCompletePage();
    }

    @SneakyThrows
    private WebElement getProductCardElementWithName(String productName) {
        return cartItems.stream()
                .filter(it -> it.findElement(By.xpath(".//div/a/div")).getText().contains(productName))
                .findFirst().orElseThrow(() -> new SwagLabsTafException("Product was not found: " + productName));
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return finishButton;
    }
}
