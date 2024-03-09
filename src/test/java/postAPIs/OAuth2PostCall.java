package postAPIs;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuth2PostCall {

	static String accessToken;
	
	@BeforeMethod
	public static void generateAccessTokenOauth2()
	{
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		 accessToken=
				given().log().all()
		.header("Content-Type","application/x-www-form-urlencoded")
		.formParam("grant_type", "client_credentials")
		.formParam("client_id", "Vu1M41311HemtKrGkcJV3X2M6hA3hFg7")
		.formParam("client_secret", "mBRtwFAVNJ8JBQ9M")
		.when()
		.post("/v1/security/oauth2/token")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.extract()
		.path("access_token");
		
		System.out.println("Access Token for Oauth 2.0 is :" +accessToken);
	
	}
	
	@Test
	public void userAuthwithOAuth2()
	{
	
		//Fetching the results from GET APi using the access_Token we get in the POst call
		
		
		Response flightResponse=
				given().log().all()
		.header("Authorization","Bearer "+accessToken)
		.queryParam("origin","PAR")
		.queryParam("maxPrice",200)
		.when()
		.get("/v1/shopping/flight-destinations")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.extract().response();
		
		
		JsonPath js=flightResponse.jsonPath();
		//Printing entire Json Response
		
		List<Object> list=js.getList("data");
		System.out.println("List of Entire Response Data is -----------------"+"\n"+list);
		
		
		//Printing list of Origins
		
		List<String> originList =js.getList("data.origin");
	    System.out.println("Origin List is ---> "+ "\n" +originList);
	
	
	
	//Printing list of Destinations
	
	List<String> destinationList =js.getList("data.destination");
    System.out.println("Desination List is ---> "+ "\n" +destinationList);
	
    
    //Printing List of Total Prices
    
    List<Double> totalPrice=js.getList("data.price.total");
    
    System.out.println("Total Price List is: "+totalPrice);
    
    List<Object> obj=js.getList("dictionaries.locations[]");
    
    System.out.println("Locations List is ----------------"+obj);
    
    
	}
	
	
}
