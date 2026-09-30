package config;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class DriverExtension implements BeforeEachCallback, AfterEachCallback {

    private final DriverFactory factory = new DriverFactory();

    @Override
    public void beforeEach(ExtensionContext context) {
        factory.setupDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        factory.getDriver().quit();
    }

    public WebDriver getDriver() {
        return factory.getDriver();
    }
}
