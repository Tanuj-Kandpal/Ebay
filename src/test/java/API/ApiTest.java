package API;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    private static final String BASE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Test
    public void sendingGetRequest() {
        String response =
                given()
                        .baseUri(BASE_URL)
                        .when()
                        .get()
                        .then().extract().response().prettyPrint();

        System.out.println("Response: " + response);
    }

    @Test
    public void verifyBPICodes() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get()
                .then()
                .body("bpi", notNullValue())
                .body("bpi.USD.code", equalTo("USD"))
                .body("bpi.GBP.code", equalTo("GBP"))
                .body("bpi.EUR.code", equalTo("EUR"));

    }

    @Test
    public void verifyGBPDescription() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get()
                .then()
                .body("bpi", notNullValue())
                .body("bpi.GBP.description", equalTo("British Pound Sterling"));

    }

    @AfterTest()
    public void exit() {

        System.out.println("Verified Successfully");
    }


}
