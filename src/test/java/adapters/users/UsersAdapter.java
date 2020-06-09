package adapters.users;

import adapters.MainAdapter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.users.JobUser;
import models.users.UsersList;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class UsersAdapter extends MainAdapter {

    public UsersList get(int page) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                        .when()
                        .get(String.format("https://reqres.in/api/users?page=%s", page))
                        .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), UsersList.class);
    }

    public JobUser post(JobUser user) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(gson.toJson(user))
                        .log().all()
                        .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), JobUser.class);
    }

    public JobUser put(JobUser user) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(gson.toJson(user))
                        .when()
                        .put("https://reqres.in/api/users")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), JobUser.class);
    }

    public JobUser patch(JobUser user) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(gson.toJson(user))
                        .when()
                        .patch("https://reqres.in/api/users")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), JobUser.class);
    }

    public JobUser delete(int userID) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .when()
                        .delete("https://reqres.in/api/users/{userID}", userID)
                        .then()
                        .statusCode(204)
                        .contentType("").extract().response();

        return gson.fromJson(response.asString().trim(), JobUser.class);
    }
}
