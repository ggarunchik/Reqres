package tests;

import adapters.resourseslist.ResourcesAdapter;
import adapters.users.UsersAdapter;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.resourceslist.ResourcesList;
import models.resourceslist.SingleResource;
import models.users.JobUser;
import models.users.User;
import models.users.UsersList;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class ReqresTests extends BaseTest {

    @Test(description = "Testing LIST USERS API")
    public void testList() throws FileNotFoundException {
        UsersList expectedList;
        expectedList = gson.fromJson(new FileReader("src/test/java/resources/expectedList.json"), UsersList.class);

        UsersList list = new UsersAdapter().get(1);
        assertEquals(list, expectedList);
    }

    @Test(description = "Testing CREATE user API")
    public void createUserTest() {
        JobUser user = new UsersAdapter().post(new JobUser("name", "job", "", ""));

    }

    @Test(description = "Testing SINGLE USER API")
    public void getSingleUserTest() {
        String expected = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\":\"Weaver\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg\"}," +
                "\"ad\":{\"company\":\"StatusCode Weekly\",\"url\":\"http://statuscode.org/\",\"text\":\"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.\"}}";
        //https://reqres.in/api/users/2
        Response response = when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(response.asString().trim(), expected);
    }

    @Test(description = "Testing SINGLE USER NOT FOUND API")
    public void userNotFountTest() {
        String expected = "{}";
        Response response = when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().body()
                .statusCode(404)
                .contentType(ContentType.JSON).extract().response();
        assertEquals(response.asString().trim(), expected);
    }

    @Test(description = "Testing LIST <RESOURCE> API")
    public void listOfResourcesTest() throws FileNotFoundException {
        ResourcesList expectedResourcesList;
        expectedResourcesList = gson.fromJson(new FileReader("src/test/java/resources/expectedResourcesList.json"), ResourcesList.class);

        ResourcesList actualResourcesList = new ResourcesAdapter().get();
        assertEquals(actualResourcesList, expectedResourcesList);
    }

    @Test(description = "Testing SINGLE <RESOURCE> API")
    public void listOfSingleResourcesTest() throws FileNotFoundException {
        SingleResource expectedResourcesList;
        expectedResourcesList = gson.fromJson(new FileReader("src/test/java/resources/expectedSingleResource.json"), SingleResource.class);

        SingleResource actualResourcesList = new ResourcesAdapter().get(2);
        assertEquals(actualResourcesList, expectedResourcesList);
    }

    @Test(description = "Testing SINGLE <RESOURCE> NOT FOUND API")
    public void listOfSingleResourcesNotFoundTest() throws FileNotFoundException {
        String expected = "{}";
        String actualResourcesList = new ResourcesAdapter().getError(23);
        assertEquals(actualResourcesList, expected);
    }

    @Test(description = "Testing UPDATE [PUT] API" )
    public void updateUser(){
        JobUser expectedUser = new JobUser("morpheus", "zion", null, null);
        JobUser actualUserUpdateResponse = new UsersAdapter().put(new JobUser("morpheus", "zion", "", ""));
        assertEquals(actualUserUpdateResponse, expectedUser );
    }

    @Test(description = "Testing UPDATE [PUT] API" )
    public void patchUser(){
        JobUser expectedUser = new JobUser("morpheus", "zion", null, null);
        JobUser actualUserUpdateResponse = new UsersAdapter().patch(new JobUser("morpheus", "zion", "", ""));
        assertEquals(actualUserUpdateResponse, expectedUser);
    }

    @Test(description = "Testing DELETE USER API" )
    public void deleteUser(){
        JobUser expectedUser = null;
        JobUser actualUserUpdateResponse = new UsersAdapter().delete(2);
        assertEquals(actualUserUpdateResponse, expectedUser);
    }
}
