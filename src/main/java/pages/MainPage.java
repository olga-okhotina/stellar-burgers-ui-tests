package pages;

import config.AppConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final String URL = AppConfig.BASE_URL;

    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By personalCabinetLink = By.xpath("//a[contains(.,'Личный Кабинет')]");

    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(URL);
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать 'Личный Кабинет' в шапке")
    public void clickPersonalCabinetLink() {
        wait.until(ExpectedConditions.elementToBeClickable(personalCabinetLink)).click();
    }

    @Step("Дождаться загрузки главной страницы")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
    }

    @Step("Проверить, что конструктор отображается")
    public boolean isConstructorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab)).isDisplayed();
    }

    @Step("Нажать на вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажать на вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Нажать на вкладку 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверить, что вкладка 'Булки' активна")
    public boolean isBunsTabActive() {
        WebElement span = wait.until(ExpectedConditions.presenceOfElementLocated(bunsTab));
        String tabClass = span.findElement(By.xpath("..")).getAttribute("class");
        return tabClass.contains("current");
    }

    @Step("Проверить, что вкладка 'Соусы' активна")
    public boolean isSaucesTabActive() {
        WebElement span = wait.until(ExpectedConditions.presenceOfElementLocated(saucesTab));
        String tabClass = span.findElement(By.xpath("..")).getAttribute("class");
        return tabClass.contains("current");
    }

    @Step("Проверить, что вкладка 'Начинки' активна")
    public boolean isFillingsTabActive() {
        WebElement span = wait.until(ExpectedConditions.presenceOfElementLocated(fillingsTab));
        String tabClass = span.findElement(By.xpath("..")).getAttribute("class");
        return tabClass.contains("current");
    }

}
