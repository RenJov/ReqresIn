package in.reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    final static Logger logger = Logger.getLogger(LoginTest.class);

    @Test
    public void loginSuccessfulTest() {

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("email", "eve.holt@reqres.in");
        body.put("password", "cityslicka");

        // Print request body
        logger.info("=== POST Login Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/login");
        logger.info("=== POST Login Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        String actualToken = response.jsonPath().getString("token");
        Assert.assertNotNull(actualToken, "token in the response shouldn't be null");
    }

    @Test
    public void loginUnuccessfulTest() {

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("email", "peter@klaven");

        // Print request body
        logger.info("=== POST Login Request ===");
        logger.info(body.toString(2));

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/login");
        logger.info("=== POST Login Response ===");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 400, "Expected Status Code: 400, Actual Status Code: "+response.getStatusCode());

        // Verify response body
        String actualError = response.jsonPath().getString("error");
        Assert.assertNotNull(actualError, "error in the response shouldn't be null");
    }
}
