package tests;

import config.DriverExtension;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.User;
import utils.UserApiClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Вход в аккаунт")
public class LoginTest {

    @RegisterExtension
    final DriverExtension extension = new DriverExtension();

    private final UserApiClient userApiClient = new UserApiClient();

    private User user;
    private String accessToken;

    @AfterEach
    void deleteUser() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Step("Создать тестового пользователя")
    private void createTestUser() {
        user = User.random();
        accessToken = userApiClient.createUser(user);
    }

    @Step("Проверить успешный вход")
    private void verifyLoginSuccess(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoad();
        assertTrue(mainPage.isConstructorDisplayed(),
                "После успешного входа должен отображаться конструктор");
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void loginViaMainPageButton() {
        createTestUser();
        WebDriver driver = extension.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        verifyLoginSuccess(driver);
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный Кабинет'")
    public void loginViaPersonalCabinetLink() {
        createTestUser();
        WebDriver driver = extension.getDriver();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickPersonalCabinetLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        verifyLoginSuccess(driver);
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' на странице регистрации")
    public void loginViaRegisterPage() {
        createTestUser();
        WebDriver driver = extension.getDriver();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        verifyLoginSuccess(driver);
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' на странице восстановления пароля")
    public void loginViaForgotPasswordPage() {
        createTestUser();
        WebDriver driver = extension.getDriver();

        ForgotPasswordPage forgotPage = new ForgotPasswordPage(driver);
        forgotPage.open();
        forgotPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();
        loginPage.login(user.getEmail(), user.getPassword());

        verifyLoginSuccess(driver);
    }
}
