package com.javaqa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;

public class WebDriverHolder {

    private static WebDriver driverInstance;

    public static WebDriver getInstance() {
        return Optional.ofNullable(driverInstance).orElseGet(() -> {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            driverInstance = new ChromeDriver();
            return driverInstance;
        });
    }

    public static void destroy() {
        Optional.ofNullable(driverInstance).ifPresent(o -> {
            driverInstance.quit();
            driverInstance = null;
        });
    }
}
