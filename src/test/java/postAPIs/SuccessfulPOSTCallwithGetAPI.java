package postAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SuccessfulPOSTCallwithGetAPI {

	@Test
	public void addSuccessfulUserValidateGetUser()
	{
		RestAssured.baseURI="https://gorest.co.in";

		//Post Call to successfully Create a User
		
		int userId=
		given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .body(new File(".\\\\src\\\\test\\\\resources\\\\testData\\\\createUser.json"))
		  .when()
		  .post("public/v2/users")
		  .then().log().all()
		  .assertThat()
		  .statusCode(201)
		  .body("name",equalTo("Nishant Goel"))
		  .and()
		  .statusLine(containsString("Created"))
		  .extract()
		  .path("id");
		  
		  System.out.println("User Id after Creating User is: "+userId);
		
		//Get Call to verify the user that we have created
		  
		  given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .get("public/v2/users/"+userId)
		  .then().log().all()
		  .assertThat()
		  .statusCode(200)
		  .and()
		  .body("id",equalTo(userId))
		  .and()
		  .body("email",equalTo("nish12345@gmail.com"));
		  
		  
	}
}
