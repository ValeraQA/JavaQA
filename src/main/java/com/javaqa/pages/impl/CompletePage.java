package com.javaqa.pages.impl;

import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class CompletePage extends BasePage {

    @FindBy(className = "title")
    private WebElement completeTitle;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    @FindBy(className = "complete-text")
    private WebElement completeText;
    @FindBy(name = "back-to-products")
    private WebElement backToProducts;

    public CompletePage verifyCompletePageDisplayed() {
        waitForPageLoaded();
        assertThat(completeTitle.isDisplayed()).as("Complete page is not displayed").isTrue();
        assertThat(completeHeader.isDisplayed()).as("Complete page is not displayed").isTrue();
        assertThat(completeText.isDisplayed()).as("Complete page is not displayed").isTrue();

        assertThat(completeTitle.getText()).as("Page title is wrong").isEqualTo("Checkout: Complete!");
        return this;
    }

    public InventoryPage clickOnBackHomeButton() {
        backToProducts.click();
        return PagesHolder.getInventoryPage();
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return completeTitle;
    }
}
