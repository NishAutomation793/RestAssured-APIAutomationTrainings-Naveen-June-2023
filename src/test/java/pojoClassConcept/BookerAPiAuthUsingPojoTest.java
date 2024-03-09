package pojoClassConcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookerAPiAuthUsingPojoTest {

	
	CredentialsBookerPojo cred=new CredentialsBookerPojo("admin","password123");
	
	@Test
	public void generateAuthTokenwithPojoClassObj()
	
	{
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String tokenId = 
				given().log().all()
		.contentType(ContentType.JSON)
		.when()
		.body(cred)
		.when()
		.post("/auth")
		.then().log().all()
		.assertThat()
		.extract().path("token");
		
		System.out.println("Token id is: "+tokenId);
	}	
	
	
}
