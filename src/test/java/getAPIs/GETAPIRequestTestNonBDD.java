package getAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class has code written with NON BDD approach format
 */

public class GETAPIRequestTestNonBDD {

	RequestSpecification request;

	@BeforeTest
	public void setUp() {

		// Forming the request for the API get call
		RestAssured.baseURI = "https://gorest.co.in/";

		request = RestAssured.given();

		request.header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd");

	}

	/**
	 * This method ins concentrating on GORest API to test the GET call with
	 * Response data
	 */
	@Test
	public void getAllUsersAPiRequestTest() {

		// Sending Request with some resources
		Response response = request.get("public/v2/users");

		// 1.Fetching status code
		int statusCode = response.statusCode();
		System.out.println("Status code for the GET APi is: " + statusCode);

		Assert.assertEquals(statusCode, 200);

		// 2. Fetching success message
		String statusMess = response.getStatusLine();
		System.out.println("Status Message is : " + statusMess);

		Assert.assertTrue(statusMess.contains("OK"));

		// 3. Fetching response Body

		System.out.println("Response body is:-> ");
		response.prettyPrint(); // This will automatically print the response body.

		// 4. Fetching response Body : Another way

		String resBodyData = response.getBody().asPrettyString();

		System.out.println("Another way of fetching Respponse Body Data is: " + resBodyData);

		// 5. Fetching particular header information from Response Header

		String contentHeader = response.header("Content-Type");

		System.out.println("Content-Type Response Header value is:" + contentHeader);

		// 6. Fetch all Response Header Information

		List<Header> contentHeadersList = response.headers().asList();

		System.out.println("Total response headers are: " + contentHeadersList.size());

		System.out.println("------------------------------------------");

		System.out.println("All Response Headers are :-> ");

		for (Header h : contentHeadersList) {
			System.out.println(h.getName() + ":" + h.getValue());

		}

	}

	/**
	 * This method will concentrate on how to fetch a particular user details with
	 * Query Paramaters
	 */
	@Test
	public void getUserAPIRequestTestwithQueryParam() {

		request.queryParam("name", "Kiran");
		request.queryParam("status", "active");

		// Getting response with other information
		Response response = request.get("public/v2/users");

		// 1.Fetching status code
		int statusCode = response.statusCode();
		System.out.println("Status code for the GET APi is: " + statusCode);

		Assert.assertEquals(statusCode, 200);

		// 2. Fetching success message
		String statusMess = response.getStatusLine();
		System.out.println("Status Message is : " + statusMess);

		Assert.assertTrue(statusMess.contains("OK"));

		// 3. Fetching response Body

		System.out.println("Response body is:-> ");
		response.prettyPrint(); // This will automatically print the response body.

	}

	@Test
	public void getUserAPIRequestTestwithQueryParam_Using_HashMap() {

		Map<String, String> queryHashmap = new HashMap<String, String>();

		queryHashmap.put("name", "Kiran");
		queryHashmap.put("status", "active");

		request.queryParams(queryHashmap); // In this method we can directly put the hashmap for multiple query Params.

		// Getting response with other information
		Response response = request.get("public/v2/users");

		// 1.Fetching status code
		int statusCode = response.statusCode();
		System.out.println("Status code for the GET APi is: " + statusCode);

		Assert.assertEquals(statusCode, 200);

		// 2. Fetching success message
		String statusMess = response.getStatusLine();
		System.out.println("Status Message is : " + statusMess);

		Assert.assertTrue(statusMess.contains("OK"));

		// 3. Fetching response Body

		System.out.println("Response body is:-> ");
		response.prettyPrint(); // This will automatically print the response body.

	}

}
