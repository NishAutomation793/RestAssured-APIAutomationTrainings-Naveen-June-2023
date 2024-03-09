package deserializationConcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class GetProductAPIDeseralizationTest {

	
	@Test
	public void getProductsInfoTest_with_normal_Pojo()
	{
		Response response =given().log().all()
		    .baseUri("https://fakestoreapi.com/")
		       .when().log().all()
		           .get("products");

	
	ObjectMapper ob=new ObjectMapper();
	
	//Here we are mapping the Json Response with the Pojo class we created. We are storing it inside the ProductPOJOclass array because we are getting the entire JSON ARray response.
	try {
	ProductPojoClass product[] = ob.readValue(response.getBody().asString(),ProductPojoClass[].class);
	//Fetching the Values from POJO class after deserialization.

	for(ProductPojoClass pj:product)
	{
		System.out.println("ID is: "+pj.getId());
		System.out.println("Title is: "+pj.getTitle());
		System.out.println("Price is: "+pj.getPrice());
		System.out.println("Description is: "+pj.getDescription());
		System.out.println("Category is: "+pj.getCategory());
		System.out.println("Image is : "+pj.getImage());
	
		System.out.println("Rating Rate is: "+pj.getRating().getRate());
		System.out.println("Rating Rate is: "+pj.getRating().getCount());

		
	}
	
	
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	}
	
	
	@Test
	public void getProductsInfoTest_with_Lombok_Pojo()
	{
		Response response =given().log().all()
		    .baseUri("https://fakestoreapi.com/")
		       .when().log().all()
		           .get("products");

	
	ObjectMapper ob=new ObjectMapper();
	
	//Here we are mapping the Json Response with the Pojo class we created. We are storing it inside the ProductPOJOclass array because we are getting the entire JSON ARray response.
	try {
	ProductLombokPojoClass product[] = ob.readValue(response.getBody().asString(),ProductLombokPojoClass[].class);
	//Fetching the Values from POJO class after deserialization.

	for(ProductLombokPojoClass pj:product)
	{
		System.out.println("ID is: "+pj.getId());
		System.out.println("Title is: "+pj.getTitle());
		System.out.println("Price is: "+pj.getPrice());
		System.out.println("Description is: "+pj.getDescription());
		System.out.println("Category is: "+pj.getCategory());
		System.out.println("Image is : "+pj.getImage());
	
		System.out.println("Rating Rate is: "+pj.getRating().getRate());
		System.out.println("Rating Rate is: "+pj.getRating().getCount());

	    System.out.println("----------------------");	
	}
	
	
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	}
	
	
}
