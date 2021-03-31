import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ReqresTestPut {

    @Test
    public void testPut(){
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        String requestUserId = "2";

        RestAssured.given()
                .baseUri("https://reqres.in/")
                .pathParam("user_id",requestUserId)
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/users/{user_id}")
                .then()
                .statusCode(200)
              //  .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("morpheus"))
                .body("job", Matchers.equalTo("zion resident"));
    }
}
