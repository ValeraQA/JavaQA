package com.javaqa.impl;

import com.javaqa.BaseTest;
import org.testng.annotations.Test;

import static com.javaqa.enums.UserCredentials.*;
import static com.javaqa.pages.PagesHolder.*;
import static com.javaqa.utils.Constants.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class LoginPageTest extends BaseTest {

    @Test
    public void verifyUserIsAbleToLogin() {
        getLoginPage()
                .openPage()
                .verifyLoginPageIsDisplayed()
                .enterCredential(STANDARD_USER.getUserLogin(), STANDARD_USER.getUserPass())
                .submitLogin();

        getPageHeaderWidget()
                .verifyPageHeaderIsDisplayed();
        getInventoryPage()
                .verifyInventoryPageDisplayed();
    }

    @Test
    public void verifyValidationsArePresentedOnLoginScreen() {
        getLoginPage()
                .openPage()
                .verifyLoginPageIsDisplayed()
                .enterCredential(EMPTY, EMPTY)
                .submitLogin();
        getLoginPage()
                .verifyInputFieldsAreHighlightedRed()
                .verifyErrorMessageDisplayed(USER_NAME_REQUIRED);


        getLoginPage()
                .enterCredential(STANDARD_USER.getUserLogin(), EMPTY)
                .submitLogin();
        getLoginPage()
                .verifyInputFieldsAreHighlightedRed()
                .verifyErrorMessageDisplayed(USER_PASS_REQUIRED);


        getLoginPage()
                .enterCredential(NON_EXISTING_USER.getUserLogin(), NON_EXISTING_USER.getUserPass())
                .submitLogin();
        getLoginPage()
                .verifyInputFieldsAreHighlightedRed()
                .verifyErrorMessageDisplayed(USER_IS_NOT_MATCHED);
    }

    @Test
    public void verifyUserIsAbleToCleanErrorMessageOnLoginScreen() {
        getLoginPage()
                .openPage()
                .verifyLoginPageIsDisplayed()
                .enterCredential(EMPTY, EMPTY)
                .submitLogin();
        getLoginPage()
                .verifyInputFieldsAreHighlightedRed()
                .verifyErrorMessageDisplayed(USER_NAME_REQUIRED)
                .clickOnCloseErrorButton()
                .verifyErrorMessageIsClosedAndFieldsAreNotHighlightedRed();
    }

    @Test
    public void verifyLockedUserIsNotAbleToLogin() {
        getLoginPage()
                .openPage()
                .verifyLoginPageIsDisplayed()
                .enterCredential(LOCKED_OUT_USER.getUserLogin(), LOCKED_OUT_USER.getUserPass())
                .submitLogin();
        getLoginPage()
                .verifyInputFieldsAreHighlightedRed()
                .verifyErrorMessageDisplayed(USER_IS_LOCKED);
    }

    @Test
    public void verifyUserIsAbleToLogout() {
        getLoginPage()
                .openPage()
                .verifyLoginPageIsDisplayed()
                .enterCredential(STANDARD_USER.getUserLogin(), STANDARD_USER.getUserPass())
                .submitLogin();

        getInventoryPage()
                .clickOnBurgerMenuButton()
                .verifyBurgerMenuIsDisplayed()
                .clickOnLogoutButton()
                .verifyLoginPageIsDisplayed();
    }

    @Test(timeOut = 3000L)
    public void verifyTestFailWithPerformanceUserLogin() {
        getLoginPage()
                .openPage()
                .verifyLoginPageIsDisplayed()
                .enterCredential(PERFORMANCE_GLITCH_USER.getUserLogin(), PERFORMANCE_GLITCH_USER.getUserPass())
                .submitLogin();
        getInventoryPage()
                .verifyInventoryPageDisplayed();
    }
}
