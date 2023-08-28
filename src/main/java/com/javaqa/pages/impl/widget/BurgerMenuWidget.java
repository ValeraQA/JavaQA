package com.javaqa.pages.impl.widget;

import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import com.javaqa.pages.impl.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class BurgerMenuWidget extends BasePage {

    @FindBy(className = "bm-item-list")
    private WebElement burgerMenu;
    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;
    @FindBy(id = "about_sidebar_link")
    private WebElement about;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;
    @FindBy(id = "reset_sidebar_link")
    private WebElement reset;
    @FindBy(xpath = "//div[@class='bm-cross-button']")
    private WebElement closeBurgerMenu;

    public BurgerMenuWidget verifyBurgerMenuIsDisplayed() {
        waitForPageLoaded();
        assertThat(burgerMenu.isDisplayed()).as("Burger menu is not displayed").isTrue();
        assertThat(allItems.isDisplayed()).as("Burger menu is not displayed").isTrue();
        assertThat(about.isDisplayed()).as("Burger menu is not displayed").isTrue();
        assertThat(logout.isDisplayed()).as("Burger menu is not displayed").isTrue();
        assertThat(reset.isDisplayed()).as("Burger menu is not displayed").isTrue();
        assertThat(closeBurgerMenu.isDisplayed()).as("Burger menu is not displayed").isTrue();
        return this;
    }

    public LoginPage clickOnLogoutButton() {
        logout.click();
        return PagesHolder.getLoginPage();
    }

    public void clickOnResetButton() {
        waitForPageLoaded();
        reset.click();
        closeBurgerMenu.click();
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return burgerMenu;
    }
}
