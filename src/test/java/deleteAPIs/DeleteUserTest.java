package deleteAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class DeleteUserTest {
	
	private String randomEmailGenerate() {
		return "apiautomation@" + System.currentTimeMillis() + "mail.com";
	}

	@Test
	public void updateUserTest() {

		UserLombokPojoClass user = new UserLombokPojoClass("Nishant", "active", "male", randomEmailGenerate());

		RestAssured.baseURI = "https://gorest.co.in";

		//1. Post Call to successfully Create a User

		Response postResponse =

				given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
						.header("Authorization",
								"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
						.contentType(ContentType.JSON)
						.body(user) // Here actually serialization takes place
						.when().post("/public/v2/users");

		JsonPath js=postResponse.jsonPath();
		Integer userId=js.get("id");
		
		System.out.println("User Id after Post Call is : "+userId);

		//2 . Delete Call to delete the created user
		
		given().log().all()
		.header("Authorization",
				"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		.contentType(ContentType.JSON)
		.when()
		.delete("/public/v2/users/"+userId)
		.then().log().all()
        .assertThat()
        .statusCode(204)
        .statusLine(containsString("No Content"));
		
	//3. Get Call to fetch he delete user
        
		
		given().log().all()
		.header("Authorization",
				"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		.contentType(ContentType.JSON)
		.when()
		.get("/public/v2/users/"+userId)
		.then().log().all()
        .assertThat()
        .statusCode(404)
        .statusLine(containsString("Not Found"))
        .and()
        .body("message",equalTo("Resource not found"));
	
	}
}
