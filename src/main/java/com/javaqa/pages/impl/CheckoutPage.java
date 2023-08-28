package com.javaqa.pages.impl;

import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.javaqa.utils.Constants.CLASS;
import static com.javaqa.utils.Constants.ERROR;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutPage extends BasePage {

    @FindBy(className = "title")
    private WebElement checkoutTitle;
    @FindBy(className = "error-message-container")
    private WebElement errorMessage;

    @FindBy(name = "firstName")
    private WebElement firstName;
    @FindBy(name = "lastName")
    private WebElement lastName;
    @FindBy(name = "postalCode")
    private WebElement postalCode;
    @FindBy(xpath = "//input[@name='firstName']/following-sibling::*[name()='svg']")
    private WebElement firstNameRedCross;
    @FindBy(xpath = "//input[@name='lastName']/following-sibling::*[name()='svg']")
    private WebElement lastNameRedCross;
    @FindBy(xpath = "//input[@name='postalCode']/following-sibling::*[name()='svg']")
    private WebElement postalCodeRedCross;

    @FindBy(className = "error-button")
    private WebElement closeErrorButton;
    @FindBy(id = "cancel")
    private WebElement cancelCheckout;
    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckoutPage verifyCheckoutPageDisplayed() {
        waitForPageLoaded();
        assertThat(checkoutTitle.isDisplayed()).as("Checkout page is not displayed").isTrue();
        assertThat(cancelCheckout.isDisplayed()).as("Checkout page is not displayed").isTrue();
        assertThat(continueButton.isDisplayed()).as("Checkout page is not displayed").isTrue();

        assertThat(firstName.isDisplayed()).as("Checkout page is not displayed").isTrue();
        assertThat(lastName.isDisplayed()).as("Checkout page is not displayed").isTrue();
        assertThat(postalCode.isDisplayed()).as("Checkout page is not displayed").isTrue();

        assertThat(checkoutTitle.getText()).as("Page title is wrong").isEqualTo("Checkout: Your Information");
        return this;
    }

    public CheckoutPage enterOrderInformationAndClickContinue(String firstNameInfo, String lastNameInfo, String postalCodeInfo) {
        firstName.clear();
        firstName.sendKeys(firstNameInfo);
        lastName.clear();
        lastName.sendKeys(lastNameInfo);
        postalCode.clear();
        postalCode.sendKeys(postalCodeInfo);

        return this;
    }

    public ShoppingCartPage clickOnCancelCheckoutButton() {
        cancelCheckout.click();
        return PagesHolder.getShoppingCartPage();
    }

    public OverviewPage clickOnContinueButton() {
        continueButton.click();
        return PagesHolder.getOverviewPage();
    }

    public CheckoutPage verifyErrorMessageDisplayed(String expectedError) {
        assertThat(errorMessage.isDisplayed()).as("Error message is not displayed").isTrue();
        assertThat(errorMessage.getText()).as("Wrong error message displayed").isEqualTo(expectedError);
        return this;
    }

    public CheckoutPage verifyInputFieldsAreHighlightedRed() {
        assertThat(firstNameRedCross.isDisplayed()).as("Error cross is not displayed for First name").isTrue();
        assertThat(lastNameRedCross.isDisplayed()).as("Error cross is not displayed for Last pass").isTrue();
        assertThat(postalCodeRedCross.isDisplayed()).as("Error cross is not displayed for Zip Code").isTrue();

        assertThat(firstName.getAttribute(CLASS)).as("First name is not highlighted with red error").contains(ERROR);
        assertThat(lastName.getAttribute(CLASS)).as("Last name is not highlighted with red error").contains(ERROR);
        assertThat(postalCode.getAttribute(CLASS)).as("Zip Code is not highlighted with red error").contains(ERROR);
        return this;
    }

    public CheckoutPage verifyErrorMessageIsClosedAndFieldsAreNotHighlightedRed() {
        assertThat(errorMessage.getText()).as("Error message is displayed").isEqualTo(EMPTY);
        assertThat(firstName.getAttribute(CLASS)).as("First name is highlighted with red error").doesNotContain(ERROR);
        assertThat(lastName.getAttribute(CLASS)).as("Last name is highlighted with red error").doesNotContain(ERROR);
        assertThat(postalCode.getAttribute(CLASS)).as("Zip Code is highlighted with red error").doesNotContain(ERROR);
        return this;
    }

    public CheckoutPage clickOnCloseErrorButton() {
        closeErrorButton.click();
        return this;
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return continueButton;
    }
}
