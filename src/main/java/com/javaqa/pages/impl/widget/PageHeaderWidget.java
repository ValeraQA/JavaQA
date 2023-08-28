package com.javaqa.pages.impl.widget;

import com.javaqa.driver.WebDriverHolder;
import com.javaqa.pages.BasePage;
import com.javaqa.pages.PagesHolder;
import com.javaqa.pages.impl.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class PageHeaderWidget extends BasePage {

    @FindBy(className = "app_logo")
    private WebElement pageLogo;
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCart;
    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;
    @FindBy(className = "bm-burger-button")
    private WebElement burgerMenu;

    public PageHeaderWidget verifyPageHeaderIsDisplayed() {
        assertThat(pageLogo.isDisplayed()).as("Page logo is not displayed").isTrue();
        assertThat(shoppingCart.isDisplayed()).as("Shopping cart is not displayed").isTrue();
        assertThat(burgerMenu.isDisplayed()).as("Burger menu is not displayed").isTrue();

        assertThat(pageLogo.getText()).as("Page logo is wrong").isEqualTo("Swag Labs");
        return this;
    }

    public PageHeaderWidget verifyNumberOfItemsOnTheCartBadge(int numberOfItems) {
        if (numberOfItems == 0) {
            assertThat(WebDriverHolder.getInstance().findElements(By.className("shopping_cart_badge"))).isEmpty();
        } else {
            assertThat(shoppingCartBadge.isDisplayed()).as("Cart Badge is not displayed").isTrue();
            assertThat(shoppingCartBadge.getText()).as("Number of items is not equal").isEqualTo(String.valueOf(numberOfItems));
        }
        return this;
    }

    public ShoppingCartPage clickOnShoppingCartIcon() {
        shoppingCart.click();
        return PagesHolder.getShoppingCartPage();
    }

    public BurgerMenuWidget clickOnBurgerMenu() {
        burgerMenu.click();
        return PagesHolder.getBurgerMenuWidget();
    }

    @Override
    protected WebElement getPageLoadingElement() {
        return pageLogo;
    }
}
