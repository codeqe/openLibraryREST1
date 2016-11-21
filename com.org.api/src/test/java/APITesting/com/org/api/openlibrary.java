package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
public class openlibrary {
	
	/*NOTE: THIS IS A TEST NG SUITE TO TEST API USING JAVA and PROVIDES A SET OF BASIC TEST CASES FOR RESTful API.This has mostly the get rest api. 
	Similar cases can be written for other CRUD methods
	Test 01
	Simple get request for getting Json output and making sure the request successful
	Status code 200 , The authorization is generally required or not depends on the api. In this case it's not required. 
	The authorization if required will be in the get as part of appid parameters.
	CONTENT
	To request any content, the requested format can be specified using Accept: header or as part of the URL.
	The test is done for JSON format .
	e.g.  "name": "Sachi Rautroy",
	PLEASE UNCOMMENT/COMMENT IN CASE THAT YOU WANT TO RUN SPECIFIC CASES e.g. //@Test or Test@
	*/
	
	
	
	//@Test
	public void Test_01() {
		
		Response resp= when().get("http://openlibrary.org/authors/OL1A.json");
						
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println(resp.asString());
		System.out.println("######################################################");
    	}
	
	//Status code Error's e.g 404, this is the case of wrong path
	//@Test
	
	public void Test_02() {
		
		Response resp= when().
						get ("http://openlibrary.org/authors/OL1A1.json");
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(),404);
    	}
	
	
	//QUERY- How to use parameters with rest assured using the http resource with parameters e.g. a key value from the response
	//@Test
	
	
	public void Test_03() {
		
		Response resp= given().
						param("key", "/books/OL22562084M").
						when().
						get("http://openlibrary.org/");
	
		System.out.println(resp.getStatusCode());
		
		Assert.assertEquals(resp.getStatusCode(), 200);
		if(resp.getStatusCode()==200) {
		
			System.out.println("The Api is working fine");
		}	
		else { 
				System.out.println("The Api is not working fine");
		}
		}
	
	
	/*This is to print the JSON data as a sting that can be manipulated as needed. This case is to print the data as string only.
	History
	Change history of any object can be accessed by passing ?m=history query parameter to the resource url. */
	
	
	//@Test
	public void Test_04() {
		
		                Response resp= given().
						when().
						get ("http://openlibrary.org/books/OL1M.json?m=history");
		                System.out.println(resp.asString());
		                System.out.println(resp.getStatusCode());
		                Assert.assertEquals(resp.getStatusCode(), 200);
		}
	

	
	//This is to read a SPECIFIC JSON data from the list
	//@Test
		public void Test_05() {
			

			String weatherReport = given().
					        parameter("key","/books/OL1M").
					        //param("appid","389561bcd3460f7431a7dcde95198830").
					        when().
					        get("http://openlibrary.org/").
					        then().
					        contentType(ContentType.JSON).
					        extract().
					        path("$..[0].key");
					        
         //   System.out.println("The first Key has the values" + $..[0].key );

									
	}
	
	
    @Test
	public void Test_09() {
		
						Response resp = given().
								        parameter("key","/books/OL1M").
								        when().
								        get("http://openlibrary.org/query.json?type=/type/edition&authors");
						System.out.println(resp.asString());
						
						String keyAuthor = resp.
				     	then().
					    contentType(ContentType.JSON).
						extract().
						path("$..[0].key");
						System.out.println(keyAuthor);
								
						
		            /* This is coming from dev or database , In the REAL TEST FLOOR the dev wil provide the actual value.
					String expectedAuthor = null;
		                if(keyAuthor.equalsIgnoreCase(expectedAuthor)) {
		               	System.out.println("Testcase Pass");
		               }
		                else
		                	System.out.println("Testcase Fail"); */
		
}
	
	
	
	
	
	
}
		
    	
	
	
	

