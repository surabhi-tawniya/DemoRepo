import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class PostMethodWithBDD {


    @Test
    public void postMethodWithBDDApproach() {
        String requestBody = "{\n" +
                "\t\"firstName\" : \"Elon1\",\n" +
                "\t\"lastName\" : \"Musk1\",\n" +
                "\t\"email\" : \"elonmusk@abc.com\",\n" +
                "\t\"salary\" : \"3000\"\n" +
                "}";


        RestAssured.given()
                .baseUri("http://localhost:8088")
                .body(requestBody) // returns request specification
                .contentType(ContentType.JSON) // request specification
                .when()   // returns request specification
                .post("/employees") // returns response of the request
                .then() // validatable response
                .statusCode(201) // response specification
                .body("id", Matchers.notNullValue()) //returns response specification
                .body("firstName", Matchers.equalTo("Elon1"))
                .body("lastName", Matchers.equalTo("Musk1"))
                .body("salary", Matchers.equalTo(3000))
                .body("email", Matchers.equalTo("elonmusk@abc.com"));
    }
}

     /*   RestAssured.given();
        RequestSpecification request = RestAssured.given();
        Response response = request.post("/employee");
        request.baseUri("http://localhost:8088");
        request.body(requestBody);
        request.contentType(ContentType.JSON);
        request.when();
        request.post("/employees");
        response.then();
        response.statusCode(201);
        response.body("id", Matchers.notNullValue());
        response.body("firstName", Matchers.equalTo("Elon1"));
        response.body("lastName", Matchers.equalTo("Musk1"));
        response.body("salary", Matchers.equalTo(3000));
        response.body("email", Matchers.equalTo("elonmusk@abc.com")); */

