package getAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FetchingJsonResponseJsonPath {

	/**
	 * This method will extract single data value from Json Array Response and
	 * validate the same
	 */
	@Test
	public void getProductInfoAPIExtractResponseBodyData() {
		
		RestAssured.baseURI = "https://fakestoreapi.com/";


		Response response = given().log().all()
				.when().log().all()
				.queryParam("limit", 5)
				.get("/products");

		JsonPath jp = response.jsonPath(); // Get a JsonPath view of the response body

		System.out.println("Response Data is: ");

		jp.prettyPrint(); // This will print the entire Json Response Array. Similar thing we can print
							// from response.prettyPrint()

		int firstProductId = jp.getInt("[0].id"); // This will fetch the id value of the first object of Json Array.

		System.out.println("First Id from Json Response Arrays is =" + firstProductId);

		String secondProductTitle = jp.getString("[1].title");

		System.out.println("Product Title of second json object is = " + secondProductTitle);

		double ratingRatethirdProduct = jp.getDouble("[2].rating.rate");

		double ratingCountthirdProduct = jp.getDouble("[2].rating.count");

		System.out.println("Rating Rate = " + ratingRatethirdProduct + " and Rating Count =" + ratingCountthirdProduct);

	}

	/**
	 * This method will extract List of data value from Json Array Response and
	 * validate the same
	 */
	@Test
	public void getProductInfoAPIExtractListDataFromResponse()

	{
		
		RestAssured.baseURI = "https://fakestoreapi.com/";

		Response response = given().log().all()
				.when().log().all()
				.get("/products");

		JsonPath js = response.jsonPath(); // This will bring the cursor to top of Json Response Array from where we can
											// extract anything

		
		//Fetching all ratingList of the Array
		List<String> ratingList = js.getList("rating");

		System.out.println(ratingList);

		
		//Fetching all Ids of the Array
		List<Integer> listIds = js.getList("id");

		System.out.println("List of Ids are: " + listIds);

		
		//Fetching all Titles of the Array
		List<String> listTitle = js.getList("title");

		System.out.println("List of Title are: " + listTitle);

		//Printing description of id number 5
		
		List<Integer> totalIds = js.getList("id");
		
		for( Integer i:totalIds)
		{
			if(i==5)
			{
				System.out.println("Description of Id number 5 is = "+js.getString("[5].description") + "Title of Id 5 is = " +js.getString("[5].title"));
			
				break;
			}
			
		}
		
	}
	
	/**
	 * This method will extract the data from single json object and not from the Json ARray
	 * Thsi method will use the Gorest users API
	 */
	@Test
	public void getSingleUserInfoExtractDatafromJsonObject()
	{
		

		RestAssured.baseURI="https://gorest.co.in";
		
		Response response =given().log().all()
				.pathParam("id","6743951")
				.when().log().all()
		          .get("/public/v2/users/{id}");
		
		JsonPath js=response.jsonPath();
		
		String name =js.get("name");
		System.out.println("Name is: "+name);
		
		
	}
	
	@Test
	public void circuitAPIExtractDatafromJsonResponseTest()
	{
		
RestAssured.baseURI="http://ergast.com";
		
		Response response =given().log().all()
		.pathParam("year","2020")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json");
		
		JsonPath js=response.jsonPath();
		
		List<String> circuitIdList=js.getList("MRData.CircuitTable.Circuits.circuitId");
		List<String> circuitNameList=js.getList("MRData.CircuitTable.Circuits.circuitName");
		
		List<Object> locationList=js.getList("MRData.CircuitTable.Circuits.Location");
		
		for( int i=0; i<circuitIdList.size(); i++)
		{
			String circuitId=circuitIdList.get(i);
			String circuitName=circuitNameList.get(i);
			
			//String location =locationList.get(i);
			
			System.out.println("Cicuit Id is: "+circuitId + "  Circuit Name is: "+circuitName);
			
		}
		System.out.println("Size of Location List is: "+locationList.size());
		System.out.println("Location List is:->");
		
		for(Object list : locationList)
		{
			
			System.out.println(list);
		}
		
		
	}

}
