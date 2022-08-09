package in.reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PatchUpdateUserTest extends BaseTest {

    final static Logger logger = Logger.getLogger(PatchUpdateUserTest.class);

    @Test
    public void patchUpdateUserTest() {

        // Create new user
        Response createUserResponse = createUser();
        logger.info("=== POST Create User Response ===");
        createUserResponse.prettyPrint();

        // Set path parameter
        spec.pathParam("id", createUserResponse.jsonPath().getInt("id"));

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("job", "Software Development Engineer in Test");

        // Print request body
        logger.info("=== PATCH Update User Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).patch("/users/{id}");
        logger.info("=== PATCH Update User Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals(actualJob, body.get("job"), "Expected name: "+body.get("job")+"Actual name: "+actualJob);

        String actualUpdatedAt = response.jsonPath().getString("updatedAt");
        Assert.assertNotNull(actualUpdatedAt, "updatedAt in the response shouldn't be null");
    }
}
