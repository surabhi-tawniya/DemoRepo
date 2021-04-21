import groovy.json.JsonException;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

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

    @Test
    public void validateWithExtractedObject(){
        String requestEmployeeId = "2";
        Employee expectedResponse =  new Employee(2l, "only", "fullstack", 2000, "onlyfullstack@abc.com");

        Employee actualResponse = RestAssured.given()
                                             .baseUri("http://localhost:8088/")
                                             .pathParam("employee_id", requestEmployeeId)
                                             .when()
                                             .get("employees/{employee_id}")
                                             .then()
                                             .statusCode(200)
                                             .extract()
                                             .as(Employee.class);

        Assert.assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void validateWithJsonFromFile() throws IOException, JsonException {
        String requestEmployeeId = "2";
        Employee expectedJsonResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/single-employee.json"));

        String actualJsonResponse = RestAssured.given()
                .baseUri("http://localhost:8088/")
                .pathParam("employee_id", requestEmployeeId)
                .when()
                .get("employees/{employee_id}")
                .asString();

        System.out.println(actualJsonResponse);
        JSONAssert.assertEquals(expectedJsonResponse, actualJsonResponse, true);

    }
}
