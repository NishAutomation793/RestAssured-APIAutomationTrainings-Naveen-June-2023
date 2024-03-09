package serializationConcept;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserPostCallSerializationDeserializationTest {

	private String randomEmailGenerate()
	{
		return "apiautomation@"+System.currentTimeMillis()+"mail.com";
		}
	
	
	@Test
	public void createUserPostCallTest()
	{
		UserLombokPojoClass user=new UserLombokPojoClass("Nishant","active","male",randomEmailGenerate());
		
		RestAssured.baseURI="https://gorest.co.in";

		//Post Call to successfully Create a User
		
		Response postResponse=
				
		given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .body(user)   // Here actually serialization takes place
		  .when()
		  .post("/public/v2/users");
		
		JsonPath js=postResponse.jsonPath();

		int actualId=js.get("id");
		
		System.out.println("Id Generate after Post call is: "+actualId);
		// Get Call to fetch the data via de-serialization

		System.out.println("GET API Call Started-------------");
		
		Response getResponse=
				given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .get("/public/v2/users/"+actualId);
		
		ObjectMapper mapper =new ObjectMapper();
		try {
		
			//De-Serealization Taking Place
	UserLombokPojoClass expectedResponse=mapper.readValue(getResponse.getBody().asString(),UserLombokPojoClass.class);
		
		System.out.println("Id is: "+expectedResponse.getId()+" Name is: "+expectedResponse.getName()+ " Gender is: "+expectedResponse.getGender());
		
		Assert.assertEquals(user.getName(),expectedResponse.getName());
		
		Assert.assertEquals(user.getGender(),expectedResponse.getGender());

		Assert.assertEquals(actualId,expectedResponse.getId());

		Assert.assertEquals(user.getEmail(),expectedResponse.getEmail());

		Assert.assertEquals(user.getStatus(),expectedResponse.getStatus());
	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * This method demonstrate the use case with Builder Pattern
	 */
	@Test
	public void createUserPostCall_with_Builder_pattern_Test()
	{
		
		//This is another way of creating the object of any Class using builder Pattern
		UserLombokPojoClass user=new UserLombokPojoClass.UserLombokPojoClassBuilder()
				.name("Manisha")
				.status("inactive")
				.email(randomEmailGenerate())
				.gender("female")
				.build();
		
		RestAssured.baseURI="https://gorest.co.in";

		//Post Call to successfully Create a User
		
		Response postResponse=
				
		given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .body(user)   // Here actually serialization takes place
		  .when()
		  .post("/public/v2/users");
		
		JsonPath js=postResponse.jsonPath();

		int actualId=js.get("id");
		
		System.out.println("Id Generate after Post call is: "+actualId);
		// Get Call to fetch the data via de-serialization

		System.out.println("GET API Call Started-------------");
		
		Response getResponse=
				given().log().all()
		  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
		  .contentType(ContentType.JSON)
		  .get("/public/v2/users/"+actualId);
		
		ObjectMapper mapper =new ObjectMapper();
		try {
		
			//De-Serealization Taking Place
	UserLombokPojoClass expectedResponse=mapper.readValue(getResponse.getBody().asString(),UserLombokPojoClass.class);
		
		System.out.println("Id is: "+expectedResponse.getId()+" Name is: "+expectedResponse.getName()+ " Gender is: "+expectedResponse.getGender());
		
		Assert.assertEquals(user.getName(),expectedResponse.getName());
		
		Assert.assertEquals(user.getGender(),expectedResponse.getGender());

		Assert.assertEquals(actualId,expectedResponse.getId());

		Assert.assertEquals(user.getEmail(),expectedResponse.getEmail());

		Assert.assertEquals(user.getStatus(),expectedResponse.getStatus());
	
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
}
