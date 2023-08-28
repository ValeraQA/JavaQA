package com.javaqa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class PropertiesHandler {

    private PropertiesHandler() {
    }

    private static final String PROPS_FILE = "swag_labs.properties";

    private static Properties properties;

    public static String getProperty(String propertyName) {
        return getPropertiesFromFile(PROPS_FILE).getProperty(propertyName);
    }

    public static Long getPropertyLong(String propertyName) {
        return Long.parseLong(getPropertiesFromFile(PROPS_FILE).getProperty(propertyName));
    }

    private static Properties getPropertiesFromFile(String pathToFile) {
        if (Objects.isNull(properties)) {
            Properties props = new Properties();
            try (final InputStream stream = PropertiesHandler.class.getClassLoader().getResourceAsStream(pathToFile)) {
                props.load(stream);
            } catch (IOException ignored) {
            }
            return props;
        }
        return properties;
    }
}
