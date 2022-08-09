package in.reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostCreateUserTest extends BaseTest {

    final static Logger logger = Logger.getLogger(PostCreateUserTest.class);

    @Test
    public void postCreateUserTest() {

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("name", "Renaldy");
        body.put("job", "SDET");

        // Print request body
        logger.info("=== POST Create User Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/users");
        logger.info("=== POST Create User Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 201, "Expected Status Code: 201, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, body.get("name"), "Expected name: "+body.get("name")+"Actual name: "+actualName);

        String actualJob = response.jsonPath().getString("job");
        Assert.assertEquals(actualJob, body.get("job"), "Expected name: "+body.get("job")+"Actual name: "+actualJob);

        int actualId = response.jsonPath().getInt("id");
        Assert.assertNotNull(actualId, "id in the response shouldn't be null");

        String actualCreatedAt = response.jsonPath().getString("createdAt");
        Assert.assertNotNull(actualCreatedAt, "createdAt in the response shouldn't be null");
    }
}
