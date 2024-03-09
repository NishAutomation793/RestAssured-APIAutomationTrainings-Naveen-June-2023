package getAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;


/**
 * For this class we will use fakestore APi to test the GET API call
 * This class has code written with BDD approach format
 */
public class GetAPIRequestTestBDDApproach {
	
	@Test
	public void getProductsInfoTest()
	{
		given().log().all()
		    .baseUri("https://fakestoreapi.com/")
		       .when().log().all()
		           .get("products")
		              .then().log().all()
		                .assertThat()
		                   .statusCode(200)
		                     .and()
		                       .statusLine(containsString("OK"))
		                         .and()
		                           .header("Content-Encoding","gzip")
		                              .and()
		                                 .contentType(ContentType.JSON)
		                                   .and()
		                                     .body("$.size()",equalTo(20))
		                                        .and()
		                                          .body("id",notNullValue()) //response will fetch all the ids from the response array and match the expected value
		                                             .and()
		                                               .body("title", hasItem("Opna Women's Short Sleeve Moisture")); //response will fetch all the titles from the response array and match the expected value
		          
		
		
	}
	
	
  @Test
  public void getAllUsersAPiRequestTestBDDApproach()
  {

	  given().log().all()
	  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
	  .baseUri("https://gorest.co.in/")
	  .when().log().all()
	  .get("public/v2/users")
	  .then().log().all()
	  .assertThat()
	  .statusCode(200)
	  .and()
      .statusLine(containsString("OK"))
        .and()
            .contentType(ContentType.JSON)
                  .and()
                    .body("$.size()",equalTo(10))
                       .and()
                         .body("id",notNullValue()) //response will fetch all the ids from the response array and match the expected value
                            .and()
                              .body("name", hasItem("Sarla Verma")); //response will fetch all the titles from the response array and match the expected value
	
	  
	  
  }

  
  @Test
	public void getUserAPIRequestTestwithQueryParam()
  
	{
	  given().log().all()
	  .header("Authorization", "Bearer 4d14c7128346af6d94bd14f954ca47330b930da69b80f2a8359b7d0cf15828fd")
	  .baseUri("https://gorest.co.in/")
	  .queryParam("name", "Kiran")
	  .queryParam("status", "active")
	  .when().log().all()
	  .get("public/v2/users")
	  .then().log().all()
	  .assertThat()
	  .statusCode(200)
	  .and()
      .statusLine(containsString("OK"))
        .and()
            .contentType(ContentType.JSON)
                  .and()
                    .body("$.size()",equalTo(1))
                       .and()
                         .body("id",notNullValue()) //response will fetch all the ids from the response array and match the expected value
                            .and()
                              .body("name", hasItem("Prof. Kiran Abbott")); //response will fetch all the titles from the response array and match the expected value
	  
	  
	  
	  
	}

}
