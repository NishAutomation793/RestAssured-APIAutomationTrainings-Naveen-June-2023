package getAPIs;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

/**
 * This class is concerned as how to pass path parameters in the request
 */
public class GetAPiwithPathParam {

	
	@Test
	public void circuitAPiPathParams()
	{
		RestAssured.baseURI="http://ergast.com";
		
		given().log().all()
		.pathParam("year","2020")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body("MRData.CircuitTable.season", equalTo("2020"))
		.body("MRData.CircuitTable.Circuits", hasSize(14));
		
	}

	@DataProvider
	public Object[][] testDataforCircuitAPI()
	{
		return new Object [][]
				{
			     {"2016",21},
			     {"2017",20},
			     {"1966",9},
			     {"2023",22}	
			
				};
		
	}
	
	@Test(dataProvider ="testDataforCircuitAPI")
	public void circuitAPiUsingDataProviders(String seasonyear, int totalCircuits)
	{
		RestAssured.baseURI="http://ergast.com";
		
		given().log().all()
		.pathParam("year",seasonyear)
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body("MRData.CircuitTable.season", equalTo(seasonyear))
		.body("MRData.CircuitTable.Circuits", hasSize(totalCircuits));
		
		
	}
	
}
