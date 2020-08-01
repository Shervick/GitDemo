package stepDefinations;

import static io.restassured.RestAssured.given;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;	
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();
	@Given("Add Place Payload {int} {string} {string} {double} {double} {string} {string} {string} {string} {string}")
	public void add_place_payload(int Accuracy,String Address, String Language, double setLat, double setLng,String Name,String Phone_number, String types1, String types2, String Website) throws IOException {
		
				//Response Builder
						
				//Request
				res=given()
				.spec(requestSpecification())
				.body(data.addPlacepayload(Accuracy,Address,Language,setLat,setLng,Name,Phone_number,types1,types2,Website));
	}


	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		APIResources resourceAPI=APIResources.valueOf(resource);
		
		//Responce
		resspec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("Post")) 
		response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("Get"))
			response=res.when().get(resourceAPI.getResource());
	}
	@Then("The API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
		
	}
	@And("{string} in responce body is {string}")
	public void in_responce_body_is(String keyValue, String ExpectedValue) {
		
		assertEquals(getJsonPath(response,keyValue),ExpectedValue);
		
		
	}
	
	 @And("Verify place_Id created maps to {string} using {string}")
	    public void verify_placeid_created_maps_to_something_using_something(String expectedname, String resourcname) throws IOException {
		 //requestSpec
		 place_id= getJsonPath(response,"place_id");
		 res=given()
				 .spec(requestSpecification())
				 .queryParam("place_id", place_id);
		 user_calls_with_post_http_request(resourcname, "GET");
		 String actualname=getJsonPath(response,"name");
		 assertEquals(actualname,expectedname);
	 }
	 
	 @Given("DeletePlace Payload")
	    public void deleteplace_payload() throws Throwable {
	        res=given().spec(requestSpecification()).body(data.deletePlacepayload(place_id));
	    }
	    
	        




}
