package com.javaqa;

import com.javaqa.driver.WebDriverHolder;
import com.javaqa.utils.PropertiesHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public abstract class BaseTest {

    @BeforeTest
    public static void init() {
        WebDriverHolder.getInstance().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(PropertiesHandler.getPropertyLong("default.timeout")));
        WebDriverHolder.getInstance().manage().window().maximize();
    }

    @AfterTest
    public static void tearDown() {
        WebDriverHolder.destroy();
    }
}
