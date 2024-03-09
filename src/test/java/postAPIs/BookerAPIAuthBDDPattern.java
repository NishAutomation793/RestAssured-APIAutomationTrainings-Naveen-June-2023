package postAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
/**
 * This class demonstrate examples of POST call with BDD patterns to follow
 */
public class BookerAPIAuthBDDPattern {

	
	@Test
	public void generateAuthTokenwithStringJsonBody()
	
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenId = 
				given().log().all()
		.contentType(ContentType.JSON)
		.when()
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.extract().path("token");
		
		System.out.println("Token id is: "+tokenId);
	}	
	
	@Test
	public void generateAuthTokenwithJsonFile()
	
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenId = 
				given().log().all()
		.contentType(ContentType.JSON)
		.when()
		.body(new File(".\\src\\test\\resources\\testData\\basicAuth.json"))
		.when()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.extract().path("token");
		
		System.out.println("Token id is: "+tokenId);
		
		
	}	
	
	
}
