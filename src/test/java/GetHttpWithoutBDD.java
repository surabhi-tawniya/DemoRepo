import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetHttpWithoutBDD {

	@Test
	 public void getMethodWithoutBDDApproach() {
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://localhost:8088");
		
		Response response = request.get("/employees");
		
		// Let's print response body.
		String responseString = response.asString();
		System.out.println("Response Details:" + responseString);
		
		/*
         * To perform validation on response like status code or value, we need to get
         * ValidatableResponse type of response using then() method of Response
         * interface. ValidatableResponse is also an interface.
         */
		
		ValidatableResponse validatableResponse = response.then();
		
		// It will check if status code is 200
		validatableResponse.statusCode(200);
		
		// It will check if status line is as expected
		validatableResponse.header("Content-Type","application/json;charset=UTF-8");
		
		validatableResponse.body("[0].id",Matchers.equalTo(1));
		
		 validatableResponse.body("[0].firstName", Matchers.equalTo("saurabh"));
	       
		 validatableResponse.body("[0].lastName", Matchers.equalTo("oza"));
	     
		 validatableResponse.body("[0].salary", Matchers.equalTo(1000));
	     
		 validatableResponse.body("[0].email", Matchers.equalTo("saurabh@abc.com"));
	}
	
}
