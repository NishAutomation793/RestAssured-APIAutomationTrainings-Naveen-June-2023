package complexPojoClassConcept;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import complexPojoClassConcept.PetPojoClass.Category;
import complexPojoClassConcept.PetPojoClass.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetSerializedDeserializedTestClass {

	@Test
	public void createPetTest_with_lombok_Pojo()
	{
		Category category =new Category(3,"dog");
		List<String>  photoUrls =Arrays.asList("https://www.dog.com","https://www.dog2.com");
		
		Tag tag1=new Tag(5,"red");
		Tag tag2=new Tag(6,"black");
		
		List<Tag> tag=Arrays.asList(tag1, tag2);
		
		PetPojoClass petUser=new PetPojoClass(300,category,"Ronney",photoUrls,tag,"available");
		
				
		RestAssured.baseURI="https://petstore.swagger.io/";
		Response response =
				given().log().all()
				.contentType(ContentType.JSON)
				.body(petUser)
		       .when()
		           .post("v2/pet");

		System.out.println("Response Code of Post call is: "+response.statusCode());
        response.prettyPrint();
		
		
	ObjectMapper ob=new ObjectMapper();
	
	//Here we are mapping the Json Response with the Pojo class we created. We are storing it inside the ProductPOJOclass array because we are getting the entire JSON ARray response.
	try {
	PetPojoClass pet = ob.readValue(response.getBody().asString(),PetPojoClass.class);
	//Fetching the Values from POJO class after deserialization.

	    System.out.println("Main Pet Id is: "+pet.getId());
		System.out.println("Category Id is : "+pet.getCategory().getId());
		System.out.println("Category Name is : "+pet.getCategory().getName());
		System.out.println("Name of the pet is : "+pet.getName());
		System.out.println("List of PhotoUrls are: "+pet.getPhotoUrls());
		System.out.println("Tag 1 Values are: "+pet.getTags().get(0));
		System.out.println("Tag 2 Values are: "+pet.getTags().get(1));
        System.out.println("Status Value is : "+pet.getStatus());
	
        //We can also write Assertions against pet variable and petUser variable
	
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	}

	
	@Test
	public void createPetTest_with_builderPattern_Pojo()
	{
		//Builder Pattern for Category Class
		Category category=new Category
				.CategoryBuilder()
				.id(4)
				.name("Animal")
				.build();
		
		List<String>  photoUrls =Arrays.asList("https://www.dog.com","https://www.dog2.com");

		Tag tag1=new Tag.TagBuilder().id(5).name("red").build();
		Tag tag2=new Tag.TagBuilder().id(6).name("blue").build();
		List<Tag>  tag =Arrays.asList(tag1, tag2);

		
		PetPojoClass petUser=new PetPojoClass
				.PetPojoClassBuilder()
				.id(300)
				.category(category)
				.name("Ronney")
				.photoUrls(photoUrls)
				.tags(tag)
				.status("Inavailable")
				.build();
		
		//PetPojoClass petUser=new PetPojoClass(300,category,"Ronney",photoUrls,tag,"available");
		
				
		RestAssured.baseURI="https://petstore.swagger.io/";
		Response response =
				given().log().all()
				.contentType(ContentType.JSON)
				.body(petUser)
		       .when()
		           .post("v2/pet");

		System.out.println("Response Code of Post call is: "+response.statusCode());
        response.prettyPrint();
		
		
	ObjectMapper ob=new ObjectMapper();
	
	//Here we are mapping the Json Response with the Pojo class we created. We are storing it inside the ProductPOJOclass array because we are getting the entire JSON ARray response.
	try {
	PetPojoClass pet = ob.readValue(response.getBody().asString(),PetPojoClass.class);
	//Fetching the Values from POJO class after deserialization.

	    System.out.println("Main Pet Id is: "+pet.getId());
		System.out.println("Category Id is : "+pet.getCategory().getId());
		System.out.println("Category Name is : "+pet.getCategory().getName());
		System.out.println("Name of the pet is : "+pet.getName());
		System.out.println("List of PhotoUrls are: "+pet.getPhotoUrls());
		System.out.println("Tag 1 Values are: "+pet.getTags().get(0));
		System.out.println("Tag 2 Values are: "+pet.getTags().get(1));
        System.out.println("Status Value is : "+pet.getStatus());
	
	
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
	
	}
	
}
