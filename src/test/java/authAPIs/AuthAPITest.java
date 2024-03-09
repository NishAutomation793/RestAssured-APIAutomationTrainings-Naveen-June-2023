package authAPIs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthAPITest {

	@BeforeTest
	public void allureReportSetup()
	{
		RestAssured.filters(new AllureRestAssured());
		
	}
	
	@Test
	public void jwtTokenAuthTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		String token = given().log().all().contentType(ContentType.JSON)
				.body("{\r\n" + "                \"username\": \"mor_2314\",\r\n"
						+ "                \"password\": \"83r5^_\"\r\n" + "            }")
				.when().post("/auth/login").then().log().all().assertThat().statusCode(200).and().extract()
				.path("token");

		System.out.println("JWT Token After Post Call is: " + token);

//Fetching separately Payload, Header and Signature from JWT Token

		String payLoad = token.split("\\.")[0]; // Here we are using \\ to escape the . character if it comes during
												// split in the string
		String header = token.split("\\.")[1];
		String signature = token.split("\\.")[2];

		System.out.println("PayLoad is: " + payLoad);
		System.out.println("Header is: " + header);
		System.out.println("Signature is: " + signature);

	}

	@Test
	public void basicAuthTest()

	{
		RestAssured.baseURI = "https://the-internet.herokuapp.com";

		String responseBody = given().log().all().contentType(ContentType.JSON).auth().basic("admin", "admin").when()
				.get("/basic_auth").then().log().all().assertThat().statusCode(200).and().extract().response()
				.toString();

		System.out.println("Response Body after Basic Auth Is: " + responseBody);

	}

	/**
	 * This is advance version of Basic Auth and Quite fast as compared to Basic Auth
	 */
	@Test
	public void basicPreemptiveAuthTest()

	{
		RestAssured.baseURI = "https://the-internet.herokuapp.com";

		String responseBody = given().log().all()
				.contentType(ContentType.JSON)
				.auth().preemptive().basic("admin","admin")
				.when()
				.get("/basic_auth").then().log().all().assertThat().statusCode(200).and().extract().response()
				.toString();

		System.out.println("Response Body after Basic Auth Is: " + responseBody);

	}
	
	
	@Test
	public void basicDigestiveAuthTest()

	{
		RestAssured.baseURI = "https://the-internet.herokuapp.com";

		String responseBody = given().log().all()
				.contentType(ContentType.JSON)
				.auth().digest("admin","admin")
				.when()
				.get("/basic_auth").then().log().all().assertThat().statusCode(200).and().extract().response()
				.toString();

		System.out.println("Response Body after Basic Auth Is: " + responseBody);

	}
	
	@Test
	public void authwithAPIKeyTest()

	{
		RestAssured.baseURI = "http://api.weatherapi.com";

		String responseBody = given().log().all()
				.contentType(ContentType.JSON)
				.queryParam("q","London")
				.queryParam("aqi","no")
				.queryParam("key","75b14c99a55a484d8f8164415233009")
				.when()
				.get("/v1/current.json").then().log().all().assertThat().statusCode(200).and().extract().response()
				.toString();

		System.out.println("Response Body after Basic Auth Is: " + responseBody);

	}
	
	
}
