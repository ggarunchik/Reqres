package adapters.resourseslist;

import adapters.MainAdapter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.resourceslist.ResourcesList;
import models.resourceslist.SingleResource;
import org.apache.http.protocol.HTTP;

import static io.restassured.RestAssured.given;

public class ResourcesAdapter extends MainAdapter {

    public ResourcesList get() {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                        .when()
                        .get("https://reqres.in/api/unknown")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), ResourcesList.class);
    }

    public SingleResource get(int resourceID) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                        .when()
                        .get("https://reqres.in/api/unknown/{resourceID}", resourceID)
                        .then()
                        .log().body()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();

        return gson.fromJson(response.asString().trim(), SingleResource.class);
    }

    public String getError(int resourceID) {

        Response response =
                given()
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().all()
                        .when()
                        .get("https://reqres.in/api/unknown/{resourceID}", resourceID)
                        .then()
                        .log().body()
                        .statusCode(404)
                        .contentType(ContentType.JSON).extract().response();

        return response.asString().trim();
    }
}
