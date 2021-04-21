import groovy.json.JsonException;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.testng.annotations.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class ResponseValidatorTypes {

    @Test
    public void validateWithJsonPath(){
        String requestEmployeeId = "2";
        RestAssured.given()
                   .baseUri("http://localhost:8088/")
                   .pathParam("employee_id", requestEmployeeId)
                   .when()
                   .get("employees/{employee_id}")
                   .then()
                   .statusCode(200)
                   .header("Content-Type", "application/json;charset=UTF-8")
                   .body("id", Matchers.equalTo(2))
                   .body("firstName",Matchers.equalTo("only"))
                   .body("lastName",Matchers.equalTo("fullstack"))
                   .body("salary",Matchers.equalTo(2000))
                   .body("email",Matchers.equalTo("onlyfullstack@abc.com"));
    }

    @Test
    public void validateWithJSONString() throws JSONException {
        String requestEmployeeId = "2";
        String expectedJson = "{\"id\":2," +
                              "\"firstName\":\"only\"," +
                              "\"lastName\":\"fullstack\"," +
                              "\"salary\":2000," +
                              "\"email\":\"onlyfullstack@abc.com\"}";

        String response = RestAssured.given()
                                     .baseUri("http://localhost:8088/")
                                     .pathParam("employee_id", requestEmployeeId)
                                     .when()
                                     .get("employees/{employee_id}")
                                     .asString();

        System.out.println(response);
        JSONAssert.assertEquals(expectedJson , response ,true);
    }
}
