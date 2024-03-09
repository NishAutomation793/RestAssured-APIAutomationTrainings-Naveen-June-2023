package putPatchAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class UpdateUserTest {

	private String randomEmailGenerate() {
		return "apiautomation@" + System.currentTimeMillis() + "mail.com";
	}

	@Test
	public void updateUserTest_with_PUT_Call() {

		UserLombokPojoClass user = new UserLombokPojoClass("Nishant", "active", "male", randomEmailGenerate());

		RestAssured.baseURI = "https://gorest.co.in";

		// Post Call to successfully Create a User

		Response postResponse =

				given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
						.header("Authorization",
								"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
						.contentType(ContentType.JSON).body(user) // Here actually serialization takes place
						.when().post("/public/v2/users");

		JsonPath js=postResponse.jsonPath();
		Integer userId=js.get("id");
		
		System.out.println("User Id after Post Call is : "+userId);
		
//2. PUT call- Update User 
		
		//Updating the existing User we have to set the new values with the help of setters and with help of already generated ref_variable "user"

		user.setGender("female");
		user.setStatus("inactive");
		user.setName("Updated Name");
		
		given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
		.header("Authorization",
				"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		.contentType(ContentType.JSON)
		.body(user) // Here actually serialization takes place
		.when()
		.put("/public/v2/users/" +userId)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("status",equalTo("inactive"))
		.and()
		.body("name",equalTo("Updated Name"))
		.and()
		.body("gender",equalTo("female"));
		
	}

	@Test
	public void updateUserTest_with_PATCH_Call() {

		UserLombokPojoClass user = new UserLombokPojoClass("Nishant", "active", "male", randomEmailGenerate());

		RestAssured.baseURI = "https://gorest.co.in";

		// Post Call to successfully Create a User

		Response postResponse =

				given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
						.header("Authorization",
								"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
						.contentType(ContentType.JSON).body(user) // Here actually serialization takes place
						.when().post("/public/v2/users");

		JsonPath js=postResponse.jsonPath();
		Integer userId=js.get("id");
		
		System.out.println("User Id after Post Call is : "+userId);
		
//2. PATCH call- Update User partially
		
		//Updating the existing User we have to set the new values with the help of setters and with help of already generated ref_variable "user"

		user.setGender("female");
		user.setStatus("inactive");
		user.setName("Updated Name");
		
		given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
		.header("Authorization",
				"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		.contentType(ContentType.JSON)
		.body(user) // Here actually serialization takes place
		.when()
		.patch("/public/v2/users/" +userId)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("status",equalTo("inactive"))
		.and()
		.body("name",equalTo("Updated Name"))
		.and()
		.body("gender",equalTo("female"));
		
	}
}
