package specifcationConcept;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

/**
 * This class will tell as we can create our own Response Specification code
 * similarly we created for Request Specification and then can pass the entrire
 * common code for validation in different methods.
 */
public class ResponseSpecBuilderConcept {

	
	public static ResponseSpecification generate_response_spec()
	{
		ResponseSpecification responseSpec=new ResponseSpecBuilder()
		                                 .expectStatusCode(200)
		                                 .expectStatusLine(containsString("OK"))
		                                 .expectHeader("Content-Type", containsString("application/json"))
		                                 .expectBody(notNullValue())
		                                 .expectBody("id",hasSize(10))
		                                 .build();
		
		return responseSpec;
	}
	
	public static ResponseSpecification generate_response_spec_401_Auth_Fail()
	{
		ResponseSpecification responseSpec=new ResponseSpecBuilder()
		                                 .expectStatusCode(401)
		                                 .expectStatusLine(containsString("Unauthorized"))
		                                 .expectBody("message",equalTo("Invalid token"))
		                                 .build();
		
		return responseSpec;
	}
	
	
	@Test
	public void validateUserInfo_with_Response_Spec()
	{
//Importing Request Spec from different class as created earlier
		
		given().log().all()
		.spec(RequestSpecBuilderConcept.generate_req_spec()) // Here we are calling the req_spec method from different class By ClassName.methodName
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(generate_response_spec()); //Passing responsespecification ref_variable
		
		
	}
	
	@Test
	public void validateUserInfo_with_wrong_token()
	{
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.header("Authorization","Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd678")
		.when()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(generate_response_spec_401_Auth_Fail());
		
	}
}
