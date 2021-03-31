import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteMethodWithBDD {

	@Test
	public void deleteMethodWithBDDApproach() {
	    String requestEmployeeId = "1";
	    RestAssured.given() //precondition is to give the base uri
				.baseUri("http://localhost:8088/")
				.pathParam("employee_id", requestEmployeeId)
				.when() //actual action is to perform the delete request
				.delete("employees/{employee_id}")
				.then() // will return the ValidatedResponse from the Response object
				.statusCode(200);
	}
}
