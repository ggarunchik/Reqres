package adapters.register;

import adapters.MainAdapter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.register.RegisterSuccessful;
import models.register.RegisterUser;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class RegisterAdapter extends MainAdapter {

    public RegisterSuccessful post(RegisterUser registerUser){

        Response response =
                given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .body(gson.toJson(registerUser))
                .log().all()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), RegisterSuccessful.class);
    }
}
