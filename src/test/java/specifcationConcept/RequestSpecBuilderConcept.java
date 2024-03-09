package specifcationConcept;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This class deals with Request Spec builder class which can helps us to write
 * or create a request spec so that it can be used in different different as
 * required.
 */
public class RequestSpecBuilderConcept {

/**	
 * We can pass the request spec ref variable to the request directly
 * The major advantage is that we can create our own request specfication and can pass the same in different methods as and whenever required
 * This class is using the gorest api
 * 
 * @return 
 */
	@Test
	public static RequestSpecification generate_req_spec() {
		RequestSpecification reqspec = new RequestSpecBuilder()
				                          .setBaseUri("https://gorest.co.in")
				                          .addHeader("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
				                          .setContentType(ContentType.JSON).build();
		return reqspec;
	}

	
	public void getUserInfo_with_Req_Spec()
	{
		given().log().all()
		.spec(generate_req_spec()) // Here we are calling the req_spec method that will return the RequestSpecification ref_variable
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
		
	}
	
	@Test
	public void getUserInfo_with_query_Req_Spec()
	{
		given().log().all()
		.queryParam("name", "Kiran")
		.queryParam("status", "active")
		.spec(generate_req_spec()) // Here we are calling the req_spec method that will return the RequestSpecification ref_variable
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(200);
		
		
		
	}
	
}
