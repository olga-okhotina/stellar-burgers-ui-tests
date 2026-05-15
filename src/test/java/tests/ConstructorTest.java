package tests;

import config.DriverExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Конструктор — переходы к разделам")
public class ConstructorTest {

    @RegisterExtension
    final DriverExtension extension = new DriverExtension();

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    void openMainPage() {
        driver = extension.getDriver();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void navigateToBuns() {

        mainPage.clickFillingsTab();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> mainPage.isFillingsTabActive());

        mainPage.clickBunsTab();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> mainPage.isBunsTabActive());

        assertTrue(mainPage.isBunsTabActive(), "Вкладка 'Булки' должна стать активной");
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void navigateToSauces() {
        mainPage.clickSaucesTab();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> mainPage.isSaucesTabActive());

        assertTrue(mainPage.isSaucesTabActive(), "Вкладка 'Соусы' должна стать активной");
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void navigateToFillings() {
        mainPage.clickFillingsTab();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(d -> mainPage.isFillingsTabActive());

        assertTrue(mainPage.isFillingsTabActive(), "Вкладка 'Начинки' должна стать активной");
    }
}
