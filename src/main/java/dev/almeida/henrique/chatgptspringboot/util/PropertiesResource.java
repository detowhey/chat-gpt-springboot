package dev.almeida.henrique.chatgptspringboot.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesResource {

    private static final Properties PROPERTIES = new Properties();

    private static FileInputStream instanceFileProperty() throws IOException {
        return new FileInputStream("src/main/resources/application.properties");
    }

    static {
        try {
            PROPERTIES.load(instanceFileProperty());
        } catch (IOException e) {
            log.error("Error loading properties file: ", e);
        }
    }

    public static String getProperty(String propertyKey) {
        return PROPERTIES.getProperty(propertyKey);
    }
}
