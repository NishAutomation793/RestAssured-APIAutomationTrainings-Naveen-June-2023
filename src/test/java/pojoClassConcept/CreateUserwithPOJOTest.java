package pojoClassConcept;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserwithPOJOTest {

	
	public String generateRandomEmail()
	{
		return "apiautomation@"+System.currentTimeMillis()+"gmail.com";
		
	}
	
	@Test
	public void addUserwithPojoClassTest()
	{
		CreateUserPojoClass cu=new CreateUserPojoClass("Nishant",generateRandomEmail(),"Male", "active");

		
		RestAssured.baseURI="https://gorest.co.in";

		//Post Call to successfully Create a User
		
		int userId=
		given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .body(cu)
		  .when()
		  .post("public/v2/users")
		  .then().log().all()
		  .assertThat()
		  .statusCode(201)
		  .body("name",equalTo(cu.getName()))
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
		  .body("email",equalTo(cu.getEmail()));
		  
		  
	}

}
