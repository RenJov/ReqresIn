package in.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserTest extends BaseTest{

    final static Logger logger = Logger.getLogger(GetUserTest.class);

    @Test
    public void getListUserTest() {

        // Add query parameter
        spec.queryParam("page", "2");

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/users");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());
    }

    @Test
    public void getSingleUserTest() {

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/users/2");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());
    }

    @Test
    public void getSingleUserNotFoundTest() {

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/users/123456789");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 404, "Expected Status Code: 404, Actual Status Code: "+response.getStatusCode());
    }

    @Test
    public void getUserDelayedTest() {

        // Add query parameter
        spec.queryParam("delay", "3");

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/users");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());
    }
}
