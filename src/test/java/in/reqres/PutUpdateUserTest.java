package in.reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutUpdateUserTest extends BaseTest {

    final static Logger logger = Logger.getLogger(PutUpdateUserTest.class);

    @Test
    public void putUpdateUserTest() {

        // Create new user
        Response createUserResponse = createUser();
        logger.info("=== POST Create User Response ===");
        createUserResponse.prettyPrint();

        // Set path parameter
        spec.pathParam("id", createUserResponse.jsonPath().getInt("id"));

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("name", "Renaldy");
        body.put("job", "Software Development Engineer in Test");

        // Print request body
        logger.info("=== PUT Update User Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).put("/users/{id}");
        logger.info("=== PUT Update User Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, body.get("name"), "Expected name: "+body.get("name")+"Actual name: "+actualName);

        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals(actualJob, body.get("job"), "Expected name: "+body.get("job")+"Actual name: "+actualJob);

        String actualUpdatedAt = response.jsonPath().getString("updatedAt");
        Assert.assertNotNull(actualUpdatedAt, "updatedAt in the response shouldn't be null");
    }
}
