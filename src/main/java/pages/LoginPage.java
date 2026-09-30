package pages;

import config.AppConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final String URL = AppConfig.BASE_URL + "/login";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath("//input[@type='text']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу логина")
    public void open() {
        driver.get(URL);
    }

    @Step("Ввести email: {email}")
    public void fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void fillPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Войти с email: {email}")
    public void login(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginButton();
    }

    @Step("Дождаться загрузки страницы логина")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Проверить, что форма входа отображается")
    public boolean isLoginFormDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }

    @Step("Получить токен авторизации из Local Storage")
    public String getTokenFromLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return localStorage.getItem('accessToken')");
    }
}
