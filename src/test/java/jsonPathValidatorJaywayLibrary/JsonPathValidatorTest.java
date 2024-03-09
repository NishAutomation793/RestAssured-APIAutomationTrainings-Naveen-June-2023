package jsonPathValidatorJaywayLibrary;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * This call will demonstrate how we can validate the Json Response via JsonPath
 * Class coming from Jayway Library
 * 
 * Here we will be taking the circuits APi and FakeStrore APi to test various
 * scenarios
 */
public class JsonPathValidatorTest {

	@Test
	public void getCircuitsDataAPiwith_Year() {

		RestAssured.baseURI = "http://ergast.com";

		Response response = given().log().all().pathParam("year", "2017").when().log().all()
				.get("/api/f1/{year}/circuits.json");

		String jsonResponse = response.asString(); // Here we are converting the response variable to String so to
													// perform multiple data validation over it

		System.out.println("Entire Json Response is: " + jsonResponse);

		// Fetching total Circuits used in 2017

		int totalCicruits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()");

		System.out.println("Total Cicruits are: " + totalCicruits);

		// Fetching List of all Circuits Used with Name
		// We can't fetch both circuitId and Name in a single go via code as we can do
		// the same from JsonPath Validator browser
		// $.MRData.CircuitTable.Circuits[*].circuitId,circuitName -> Not bringing any
		// data, The main issue behind this is that it is returning both string and
		// String value .Hence we need to store it inside List of HashMap<String,
		// String>

		List<HashMap<String, String>> circuitIdNameList = JsonPath.read(jsonResponse,
				"$.MRData.CircuitTable.Circuits[*].[\"circuitId\",\"circuitName\"]");

		System.out.println("List of All Circuits Used with Id and Name is:" + circuitIdNameList);

		// Fetching Only Name of Circuit List
		List<String> circuitsUsed = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits[*].circuitName");
		System.out.println("List of All Circuits with Name is: " + circuitsUsed);

	}

	@Test
	public void fakeStoreAPiJsonPathValidatorTest() {

		RestAssured.baseURI = "https://fakestoreapi.com/";

		Response response = given().log().all().when().log().all().get("/products");

		String jsonResponse = response.asString(); // Here we are converting the response variable to String so to
													// perform multiple data validation over it

		// Fetching the entire Json Response
		System.out.println("Entire Json Response is: " + jsonResponse);

		// Fetching rate List where rate <=3
		List<Float> rateList = JsonPath.read(jsonResponse, "$[?(@.rating.rate<=3)].rating.rate");

		System.out.println("Rate List Is: " + rateList);
		// Fetching title and price of the Product where Category=Jewelery
		// We are using \ (backslash) to ignore the double comma

		List<HashMap<String, Object>> titlePriceList = JsonPath.read(jsonResponse,
				"$[?(@.category=='jewelery')].[\"title\",\"price\"]");

		// Here now e can either print the entire list or we can iterate the list as
		// below

		for (HashMap<String, Object> map : titlePriceList) {
			String title = (String) map.get("title");
			Object price = (Object) map.get("price");

			System.out.println("Title of the Product is: " + title);
			System.out.println("Price of the Product is: " + price);

		}

		System.out.println("************************");

		// Fetching three Attributes value
		// fetch title and jewellery category of the products where rate is between 2
		// and 4:

		List<HashMap<String, Object>> titlePriceIdList = JsonPath.read(jsonResponse,
				"$[?(@.category == 'jewelery' && @.rating.rate >=2 && @.rating.rate <= 4)].[\"title\",\"price\",\"id\"]");
		for (HashMap<String, Object> map : titlePriceIdList) {
			String title = (String) map.get("title");
			Object price = (Object) map.get("price");
			int id = (Integer) map.get("id");

			System.out.println("Title of the Product is: " + title);
			System.out.println("Price of the Product is: " + price);
			System.out.println("Id of the Product is: " + id);

		}

	}

}
