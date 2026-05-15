package pages;

import config.AppConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private static final String URL = AppConfig.BASE_URL + "/forgot-password";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get(URL);
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

}
