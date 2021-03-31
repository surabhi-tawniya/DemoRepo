import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqresTestGet {

	@Test
	
	public void reqresWithoutBDDApproach() {
		
		RequestSpecification request = RestAssured.given();
		request.baseUri("http://reqres.in");
		//request.queryParam("page", 1);
		//request.log();
		Response response = request.get("/api/users?page=1");
		
		String responseString = response.asString();
		System.out.println("Response Details:" + responseString);
		
		
	}
}
