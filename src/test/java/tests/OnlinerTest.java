package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class OnlinerTest extends BaseTest {

    @Test(invocationCount = 5)
    public void validateSignIn() {
        //https://www.onliner.by/sdapi/user.api/login
        //"{login: "g435435b@gmail.com", password: "123"}"
        //{"login: "", password: ""}
        given()
                .body("\"{login: \"ga@gmail.com\", password: \"123\"}\"")
                .header("Content-type", "application/json")
                .log().all()
        .when()
                .post("https://www.onliner.by/sdapi/user.api/login")
        .then()
                .log().all()
                .statusCode(400)
                .body("errors[0].message", equalTo("Неверный логин или пароль"));
    }
}
