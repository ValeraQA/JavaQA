package com.javaqa.pages.impl;

import com.javaqa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.javaqa.utils.Constants.CLASS;
import static com.javaqa.utils.Constants.ERROR;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {

    @FindBy(className = "login_logo")
    private WebElement pageLogo;
    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement userPass;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(className = "error-message-container")
    private WebElement errorMessage;
    @FindBy(className = "error-button")
    private WebElement closeErrorButton;
    @FindBy(xpath = "//input[@id='user-name']/following-sibling::*[name()='svg']")
    private WebElement userNameRedCross;
    @FindBy(xpath = "//input[@id='password']/following-sibling::*[name()='svg']")
    private WebElement userPassRedCross;

    public LoginPage enterCredential(String login, String password) {
        userName.clear();
        userName.sendKeys(login);
        userPass.clear();
        userPass.sendKeys(password);
        return this;
    }

    public void submitLogin() {
        loginButton.submit();
    }

    public LoginPage verifyErrorMessageDisplayed(String expectedError) {
        assertThat(errorMessage.isDisplayed()).as("Error message is not displayed").isTrue();
        assertThat(errorMessage.getText()).as("Wrong error message displayed").isEqualTo(expectedError);
        return this;
    }

    public LoginPage verifyInputFieldsAreHighlightedRed() {
        assertThat(userNameRedCross.isDisplayed()).as("Error cross is not displayed for user name").isTrue();
        assertThat(userPassRedCross.isDisplayed()).as("Error cross is not displayed for user pass").isTrue();
        assertThat(userName.getAttribute(CLASS)).as("User name is not highlighted with red error").contains(ERROR);
        assertThat(userPass.getAttribute(CLASS)).as("User pass is not highlighted with red error").contains(ERROR);
        return this;
    }

    public LoginPage verifyErrorMessageIsClosedAndFieldsAreNotHighlightedRed() {
        assertThat(errorMessage.getText()).as("Error message is displayed").isEqualTo(EMPTY);
        assertThat(userName.getAttribute(CLASS)).as("User name is highlighted with red error").doesNotContain(ERROR);
        assertThat(userPass.getAttribute(CLASS)).as("User pass is highlighted with red error").doesNotContain(ERROR);
        return this;
    }

    public LoginPage clickOnCloseErrorButton() {
        closeErrorButton.click();
        return this;
    }

    public LoginPage verifyLoginPageIsDisplayed() {
        assertThat(pageLogo.isDisplayed()).as("Login page is not displayed").isTrue();
        assertThat(userName.isDisplayed()).as("Login page is not displayed").isTrue();
        assertThat(userPass.isDisplayed()).as("Login page is not displayed").isTrue();
        assertThat(loginButton.isDisplayed()).as("Login page is not displayed").isTrue();
        return this;
    }

    public LoginPage openPage() {
        super.openPage();
        return this;
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return pageLogo;
    }
}
