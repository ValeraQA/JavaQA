package com.javaqa.pages;

import com.javaqa.driver.WebDriverHolder;
import com.javaqa.pages.impl.widget.BurgerMenuWidget;
import com.javaqa.utils.PropertiesHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final long DEFAULT_TIMEOUT = PropertiesHandler.getPropertyLong("default.timeout");
    private static final long POLLING_TIMEOUT = PropertiesHandler.getPropertyLong("polling.timeout");

    protected String baseUrl = PropertiesHandler.getProperty("base.url");

    public BasePage() {
        PageFactory.initElements(WebDriverHolder.getInstance(), this);
    }

    public BasePage openPage() {
        WebDriverHolder.getInstance().get(getPageURL());
        waitForPageLoaded();
        return this;
    }

    public BurgerMenuWidget clickOnBurgerMenuButton() {
        return PagesHolder.getPageHeaderWidget().clickOnBurgerMenu();
    }

    public BasePage waitForPageLoaded() {
        getDriverWait().until(ExpectedConditions.visibilityOf(getPageLoadingElement()));
        return this;
    }

    protected String getPageURL() {
        return baseUrl;
    }

    protected abstract WebElement getPageLoadingElement();

    private FluentWait<WebDriver> getDriverWait() {
        return new WebDriverWait(WebDriverHolder.getInstance(),
                Duration.ofSeconds(DEFAULT_TIMEOUT)).pollingEvery(Duration.ofMillis(POLLING_TIMEOUT));
    }
}
