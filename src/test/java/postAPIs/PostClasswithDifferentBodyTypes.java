package postAPIs;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostClasswithDifferentBodyTypes {

	@Test
	public void postCallwithTextBodyType()
	{
		
		RestAssured.baseURI="http://httpbin.org";
		String bodyData="Hi My Name is Nishant Goel.";
		
		Response response=
				given().log().all()
		.contentType(ContentType.TEXT)
		.body(bodyData)
		.when()
		.post("/post");
		
		response.prettyPrint();
		
		Assert.assertEquals(bodyData,response.jsonPath().get("data"));
		
		
	}

	@Test
	public void postCallwithJavascriptBodyType()
	{
		
		RestAssured.baseURI="http://httpbin.org";
		String javascriptBodyData="function sum() {\r\n"
				+ "    x = 10;\r\n"
				+ "    y = 20;\r\n"
				+ "\r\n"
				+ "    console.log(x + y);\r\n"
				+ "\r\n"
				+ "}";
		
		Response response=
				given().log().all()
		.header("Content-Type","application/javascript")
		.body(javascriptBodyData)
		.when()
		.post("/post");
		
		response.prettyPrint();
				
		
	}

	
	@Test
	public void postCallwithHTMLBodyType()
	{
		
		RestAssured.baseURI="http://httpbin.org";
		String htmlBodyData="<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<h1>My First Heading</h1>\r\n"
				+ "\r\n"
				+ "<p>My first paragraph.</p>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "";
		
		Response response=
				given().log().all()
		.contentType(ContentType.HTML)
		.body(htmlBodyData)
		.when()
		.post("/post");
		
		response.prettyPrint();
				
	}

	@Test
	public void postCallwithXMLBodyType()
	{
		
		RestAssured.baseURI="http://httpbin.org";
		String htmlBodyData="<note>\r\n"
				+ "    <to>Tove</to>\r\n"
				+ "    <from>Jani</from>\r\n"
				+ "    <heading>Reminder</heading>\r\n"
				+ "    <body>Don't forget me this weekend!</body>\r\n"
				+ "</note>";
		
		Response response=
				given().log().all()
		.contentType(ContentType.XML)
		.body(htmlBodyData)
		.when()
		.post("/post");
		
		response.prettyPrint();
				
	}

	
	@Test
	public void postCallwithMultiPartFormDataBodyType()
	{
		
		RestAssured.baseURI="http://httpbin.org";
		
		Response response=
				
				given().log().all()
		.contentType(ContentType.MULTIPART)
		.multiPart("Name","Nishant")
		.multiPart("City","Aligarh")
		.multiPart("file",new File(".\\src\\test\\resources\\testData\\MultiPartaFormFile.png"))
		.when()
		.post("/post");
			
		response.prettyPrint();
				
	}

	

	/**
	 * For different types of files we need to supply the same in header section either as image/png or application/pdf
	 */
	@Test
	public void postCallwithBinaryBodyType()
	{
		
		RestAssured.baseURI="http://httpbin.org";
		
		Response response=
				
				given().log().all()
		.header("Content-Type","image/png")
		.body(new File(".\\src\\test\\resources\\testData\\MultiPartaFormFile.png"))
		.when()
		.post("/post");
			
		response.prettyPrint();
				
	}
}
