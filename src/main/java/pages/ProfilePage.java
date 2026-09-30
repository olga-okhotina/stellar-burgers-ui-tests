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

public class ProfilePage {
    private static final String URL = AppConfig.BASE_URL + "/account";

    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By constructorLink = By.xpath("//a[contains(.,'Конструктор')]");

    private final By logoLink = By.xpath("//a[@href='/'][not(contains(.,'Конструктор'))]");

    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу профиля")
    public void open() {
        driver.get(URL);
    }

    @Step("Дождаться загрузки страницы профиля")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.urlContains("/account"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }

    @Step("Нажать 'Конструктор' в шапке")
    public void clickConstructorLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(constructorLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

    @Step("Нажать на логотип Stellar Burgers")
    public void clickLogo() {
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(logoLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logo);
    }

    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    @Step("Проверить, что кнопка 'Выход' отображается")
    public boolean isLogoutButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
    }

}
