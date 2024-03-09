package xmlPathExtraction;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


// For fetching all child node info we use parentNode.childNode
// For fetching any attribute info name we use parentNode.chiuldNode.@attributeName
public class GetCircuitXMLAPITest {

	
	@Test
	public void xmlResponseExtractionTest()
	{
		RestAssured.baseURI = "http://ergast.com";

		Response response = given().log().all()
				.pathParam("year", "2017")
				.when()
				.get("/api/f1/{year}/circuits.xml");

		String xmlResponse = response.body().asString();
		
		System.out.println("XML Response is: "+xmlResponse);
		
		//Creating object of XMLPath
		XmlPath xmlPath=new XmlPath(xmlResponse);
		
	   // Fetching all the circuitName from this XML response

		List<String> circuitName=xmlPath.getList("MRData.CircuitTable.Circuit.CircuitName");
		
		System.out.println("Circuit Name are: ");
		for(String s: circuitName)
		{
		   System.out.println(s);
			
		}
	// Fetching size of Total circuits :
		
		List<String> circuits=xmlPath.getList("MRData.CircuitTable.Circuit");
		
		System.out.println("Total Cicruits are: "+circuits.size());

	// Fetching all the circuits Ids of the circuits
		// Here we have to use the similar way we used for xpath creaion of child node and attributes concept
	
		List<String> circuitIds=xmlPath.getList("MRData.CircuitTable.Circuit.@circuitId");

		System.out.println("Circuit Ids are: "+circuitIds);
		
	// Fetching the locality where circuitId ="America"	
		
		String locality=xmlPath.get("**.findAll {it.@circuitId=='americas'}.Location.Locality").toString();

		System.out.println("Locality Value where Cicruit Id is Americas : "+locality);
	
	// Fetching the Longitude and Latitude where circuitId ="America"	
	
		String latValue=xmlPath.get("**.findAll {it.@circuitId=='americas'}.Location.@lat").toString();
		String longValue=xmlPath.get("**.findAll {it.@circuitId=='americas'}.Location.@long").toString();

	System.out.println("Latitude value is: "+latValue + " Longitude Value is: "+longValue);	
		
	// Fetching the locality where circuitId ="America" or circuitId ="bahrain"
	
	
	String localityData=xmlPath.get("**.findAll {it.@circuitId=='americas' || it.@circuitId=='bahrain'}.Location.Locality").toString();

	System.out.println("Locality Data with OR Condition : "+localityData);
	
		
		
	}
	
}
