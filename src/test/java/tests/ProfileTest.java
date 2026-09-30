package tests;

import config.DriverExtension;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.User;
import utils.UserApiClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Личный кабинет")
public class ProfileTest {

    @RegisterExtension
    final DriverExtension extension = new DriverExtension();

    private final UserApiClient userApiClient = new UserApiClient();

    private String accessToken;

    @AfterEach
    void deleteUser() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Step("Создать пользователя и войти в аккаунт")
    private void createAndLoginUser() {
        User user = User.random();
        accessToken = userApiClient.createUser(user);

        WebDriver driver = extension.getDriver();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(user.getEmail(), user.getPassword());

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на 'Личный Кабинет'")
    public void navigateToProfile() {
        createAndLoginUser();
        WebDriver driver = extension.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalCabinetLink();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForPageLoad();

        assertTrue(profilePage.isLogoutButtonDisplayed(),
                "В личном кабинете должна отображаться кнопка 'Выход'");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на 'Конструктор'")
    public void navigateFromProfileToConstructorViaLink() {
        createAndLoginUser();
        WebDriver driver = extension.getDriver();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.open();
        profilePage.waitForPageLoad();
        profilePage.clickConstructorLink();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoad();

        assertTrue(mainPage.isConstructorDisplayed(),
                "Должен отображаться конструктор с вкладками ингредиентов");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип")
    public void navigateFromProfileToConstructorViaLogo() {
        createAndLoginUser();
        WebDriver driver = extension.getDriver();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.open();
        profilePage.waitForPageLoad();
        profilePage.clickLogo();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoad();

        assertTrue(mainPage.isConstructorDisplayed(),
                "Должен отображаться конструктор с вкладками ингредиентов");
    }

    @Test
    @DisplayName("Выход по кнопке 'Выход' в личном кабинете")
    public void logoutFromProfile() {
        createAndLoginUser();
        WebDriver driver = extension.getDriver();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.open();
        profilePage.waitForPageLoad();
        profilePage.clickLogoutButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();

        assertTrue(loginPage.isLoginFormDisplayed(),
                "После выхода должна отображаться форма входа");
    }
}
