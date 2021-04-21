import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import  static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetMethodWithBDD {
	

	@Test
	 public void getHttpMethodWithBDDApproach() {
		RestAssured.given()  //precondition is to give the base uri & given will return obj of requestSpecification

		.baseUri("http://localhost:8088/") // returns request specification

		.when()   // actual action is to perform the get request & returns object of RequestSpecification
	    
		.get("employees") // returns response of request
		
		.then() //returns validatable response
		
		.statusCode(200) // returns response specification
		
		.header("Content-Type", "application/json;charset=UTF-8") // returns response specification
		
		.body("[0].id",Matchers.equalTo(1)) //returns response specification
		
		.body("[0].firstName",Matchers.equalTo("saurabh"))
		
		.body("[0].lastName", Matchers.equalTo("oza"))
		
		.body("[0].salary", Matchers.equalTo(1000))
		
		.body("[0].email", Matchers.equalTo("saurabh@abc.com"));

    }
	
	 @Test
	    public void getWithIdMethodWithBDDApproach() {
	       
		 String requestEmployeeId = "2";
	       
		 RestAssured.given() //precondition is to give the base uri
	     
		 .baseUri("http://localhost:8088/")
	     
		 .pathParam("employee_id", requestEmployeeId) // returns request specification
	     
		 .when() //actual action is to perform the get request
	     
		 .get("employees/{employee_id}")
	     
		 .then() // will return the ValidatadleResponse from the Response object
	     
		 .statusCode(200)
	     
		 .header("Content-Type", "application/json;charset=UTF-8")
	     
		 .body("id", Matchers.equalTo(2))
	     
		 .body("firstName", Matchers.equalTo("only"))
	     
		 .body("lastName", Matchers.equalTo("fullstack"))
	     
		 .body("salary", Matchers.equalTo(2000))
	     
		 .body("email", Matchers.equalTo("onlyfullstack@abc.com"));
   }

}