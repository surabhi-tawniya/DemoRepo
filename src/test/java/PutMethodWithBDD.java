import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.*;


public class PutMethodWithBDD {

    @Test
    public void putMethodWithBDDApproach() {

        String requestBody = "{\n" +
                "\t\"firstName\" : \"shivansh\",\n" +
                "\t\"lastName\" : \"Oza\",\n" +
                "\t\"email\" : \"shivanshoza@gmail.com\",\n" +
                "\t\"salary\" : \"20000\"\n" +
                "}";


        String requestEmployeeId = "2";

        RestAssured.given()
                .baseUri("http://localhost:8088")
                .pathParam("employee_id", requestEmployeeId)
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .put("/employees/{employee_id}")
                .then()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("firstName", Matchers.equalTo("shivansh"))
                .body("lastName", Matchers.equalTo("Oza"))
                .body("salary", Matchers.equalTo(20000))
                .body("email", Matchers.equalTo("shivanshoza@gmail.com"));
    }
}
