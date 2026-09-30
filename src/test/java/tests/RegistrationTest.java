package tests;

import config.DriverExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.User;
import utils.UserApiClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Регистрация")
public class RegistrationTest {

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

    @Test
    @DisplayName("Успешная регистрация перенаправляет на страницу логина")
    public void successfulRegistration() {
        User user = User.random();
        WebDriver driver = extension.getDriver();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForPageLoad();

        assertTrue(loginPage.isLoginFormDisplayed(),
                "После успешной регистрации должна отображаться форма входа");

        loginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForPageLoad();

        accessToken = loginPage.getTokenFromLocalStorage();
    }

    @Test
    @DisplayName("Пароль меньше 6 символов вызывает ошибку")
    public void invalidPasswordShowsError() {
        WebDriver driver = extension.getDriver();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.fillName("Тест Пользователь");
        registerPage.fillEmail("test_short_pwd@test.com");
        registerPage.fillPassword("12345");
        registerPage.clickRegisterButton();

        assertTrue(registerPage.isPasswordErrorDisplayed(),
                "Должна появиться ошибка 'Некорректный пароль'");
    }
}
