package utils;

import config.AppConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {

    public RequestSpecification spec() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(AppConfig.BASE_URL)
                .basePath("/api");
    }

    public RequestSpecification specWithToken(String token) {
        return spec().header("Authorization", token);
    }
}
