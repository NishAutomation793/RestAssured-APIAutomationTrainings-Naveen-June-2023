package schemaValidation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserSchemaValidationTest {
	
	
	private String randomEmailGenerate() {
		return "apiautomation@" + System.currentTimeMillis() + "mail.com";
	}

	@Test
	public void createUserSchemaValidation() {

		UserLombokPojoClass user = new UserLombokPojoClass("Nishant", "active", "male", randomEmailGenerate());

		RestAssured.baseURI = "https://gorest.co.in";

		// Post Call to successfully Create a User


				given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
						.header("Authorization",
								"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
						.contentType(ContentType.JSON).body(user) // Here actually serialization takes place
						.when().post("/public/v2/users")
						.then().log().all()
						.assertThat()
						.body(matchesJsonSchemaInClasspath("createUserSchema.json"));

}
	
	@Test
	public void getUserSchemaValidation()
	{
		RestAssured.baseURI = "https://gorest.co.in";

		// Post Call to successfully Create a User


				given().log().all() // This is actually generating a 0 value of id while doing the post request. So we have to include the JsonInclude property in Lombok Pojo class so to include only Non_Null values
						.header("Authorization",
								"Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
						.contentType(ContentType.JSON)
						.when()
						.get("/public/v2/users")
						.then().log().all()
						.assertThat()
						.body(matchesJsonSchemaInClasspath("getUserSchema.json"));
	
	}
}