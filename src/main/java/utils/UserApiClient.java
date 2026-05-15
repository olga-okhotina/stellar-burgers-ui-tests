package utils;

import io.qameta.allure.Step;

import java.util.Map;

public class UserApiClient extends Client {

    private static final String REGISTER = "/auth/register";
    private static final String USER = "/auth/user";

    @Step("Создать тестового пользователя через API")
    public String createUser(User user) {
        return spec()
                .body(Map.of("email", user.getEmail(), "password", user.getPassword(), "name", user.getName()))
                .post(REGISTER)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @Step("Удалить тестового пользователя через API")
    public void deleteUser(String token) {
        specWithToken(token)
                .delete(USER)
                .then()
                .statusCode(202);
    }

}
