package in.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTest extends BaseTest {

    final static Logger logger = Logger.getLogger(DeleteUserTest.class);

    @Test
    public void deleteUserTest() {

        // Create new user
        Response createUserResponse = createUser();
        logger.info("=== POST Create User Response ===");
        createUserResponse.prettyPrint();

        // Set path parameter
        spec.pathParam("id", createUserResponse.jsonPath().getInt("id"));

        // Delete user
        Response deleteResponse = RestAssured.given(spec).delete("/users/{id}");

        // Verify response status code
        Assert.assertEquals(deleteResponse.getStatusCode(), 204, "Expected Status Code: 204, Actual Status Code: "+deleteResponse.getStatusCode());

        // Verify user is deleted
        Response getResponse = RestAssured.given(spec).get("/users/{id}");
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Expected Status Code: 404, Actual Status Code: "+getResponse.getStatusCode());
    }
}
