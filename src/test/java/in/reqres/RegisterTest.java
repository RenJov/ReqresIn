package in.reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    final static Logger logger = Logger.getLogger(RegisterTest.class);

    @Test
    public void registerSuccessfulTest() {

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "pistol");

        // Print request body
        logger.info("=== POST Register Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/register");
        logger.info("=== POST Register Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        int actualId = response.jsonPath().getInt("id");
        Assert.assertNotNull(actualId, "id in the response shouldn't be null");

        String actualToken = response.jsonPath().getString("token");
        Assert.assertNotNull(actualToken, "token in the response shouldn't be null");
    }

    @Test
    public void registerUnuccessfulTest() {

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("email", "eve.holt@reqres.in");

        // Print request body
        logger.info("=== POST Register Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/register");
        logger.info("=== POST Register Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 400, "Expected Status Code: 400, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        String actualError = response.jsonPath().getString("error");
        Assert.assertNotNull(actualError, "error in the response shouldn't be null");
    }
}
