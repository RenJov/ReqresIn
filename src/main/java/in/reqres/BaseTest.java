package in.reqres;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                build();
    }

    public Response createUser() {

        // Create JSON body
        JSONObject body = new JSONObject();
        body.put("name", "Renaldy");
        body.put("job", "SDET");

        // Get response
        Response response = RestAssured.given(spec).contentType(ContentType.JSON).body(body.toString()).post("/users");

        return response;
    }
}
