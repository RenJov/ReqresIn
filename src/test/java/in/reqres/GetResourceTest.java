package in.reqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetResourceTest extends BaseTest{

    final static Logger logger = Logger.getLogger(GetResourceTest.class);

    @Test
    public void getListResourceTest() {

        // Add query parameter
        spec.queryParam("page", "2");

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/unknown");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());
    }

    @Test
    public void getSingleResourceTest() {

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/unknown/2");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status Code: 200, Actual Status Code: "+response.getStatusCode());
    }

    @Test
    public void getSingleResourceNotFoundTest() {

        // Get response for Single User API
        Response response = RestAssured.given(spec).get("/unknown/123456789");
        response.prettyPrint();

        // Verify response status code
        Assert.assertEquals(response.getStatusCode(), 404, "Expected Status Code: 404, Actual Status Code: "+response.getStatusCode());
    }
}
