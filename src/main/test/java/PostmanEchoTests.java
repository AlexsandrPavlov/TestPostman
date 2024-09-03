import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.*;


public class PostmanEchoTests {
    String postmanEchoURL = "https://postman-echo.com";


    @Test
    public void testPostRawText() {

        RestAssured.baseURI = postmanEchoURL;
        String requestBody = "Hello, World!";


        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .post("/post")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data", equalTo(requestBody));

    }

    @Test
    public void testGetRequest() {
        Response response = RestAssured.given()
                .baseUri(postmanEchoURL)
                .basePath("/get")
                .queryParam("foo", "bar")
                .when()
                .get("/");

        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();
        assertEquals(actualStatusCode, expectedStatusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

    }

    @Test
    public void testPostFormData() {
        RestAssured.baseURI = postmanEchoURL;

        Response response = given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .header("Accept", "*/*") //
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", "5c27cd7d-6b16-4e5a-a0ef-191c9a3a275f")
                .header("User-Agent", "PostmanRuntime/7.6.1")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .extract().response();
    }

    @Test
    public void testPutRequest() {
        RestAssured.baseURI = postmanEchoURL;
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .header("Content-Type", "text/plain")
                .header("Cookie", "sails.sid=s%3AzTpnqphYUXumToE1VvvRaKBjqqyAqKmo.luZEJRv7scEapVFQQCsvbbD8Z1Tx%2BBL3qgHoShXjQ0Y")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .extract().response();

    }

    @Test
    public void testPatchRequest() {
        RestAssured.baseURI = postmanEchoURL;
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .header("Content-Type", "text/plain")
                .header("Cookie", "sails.sid=s%3AzTpnqphYUXumToE1VvvRaKBjqqyAqKmo.luZEJRv7scEapVFQQCsvbbD8Z1Tx%2BBL3qgHoShXjQ0Y")
                .body(requestBody)
                .when()
                .put("/patch")
                .then()
                .statusCode(404)
                .body("data", equalTo(requestBody))
                .extract().response();

    }

    @Test
    public void testDeleteRequest() {
        RestAssured.baseURI = postmanEchoURL;
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .header("Content-Type", "text/plain")
                .header("Cookie", "sails.sid=s%3AzTpnqphYUXumToE1VvvRaKBjqqyAqKmo.luZEJRv7scEapVFQQCsvbbD8Z1Tx%2BBL3qgHoShXjQ0Y")
                .body(requestBody)
                .when()
                .put("/delete")
                .then()
                .statusCode(404)
                .body("data", equalTo(requestBody))
                .extract().response();

    }
}
