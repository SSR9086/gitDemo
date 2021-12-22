package stepdefinitions;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static io.restassured.RestAssured.given;

public class PlaceAPI extends Utils {

	RequestSpecification reqFinal;
	Response apiResp;
	// Response getResponse;
	TestDataBuild dataObj = new TestDataBuild();
	static String place_id;

	@Given("^Add Place payload \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_something_something_something_something_something_something(int accuracy, String name,
			String phone_number, String address, String website, String language) throws Throwable {
		reqFinal = given().spec(requestSpecification())
				.body(dataObj.addPlaceAPIPayload(accuracy, name, phone_number, address, website, language));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_something_with_something_http_request(String resourceUrl, String httpMethod) throws Throwable {
		APIResources rsrceUrl = APIResources.valueOf(resourceUrl);
		System.out.println(rsrceUrl.getResourceUrl());

		if (httpMethod.equalsIgnoreCase("GET"))
			apiResp = reqFinal.when().get(rsrceUrl.getResourceUrl());
		else if (httpMethod.equalsIgnoreCase("POST"))
			apiResp = reqFinal.when().post(rsrceUrl.getResourceUrl());
		else if (httpMethod.equalsIgnoreCase("DELETE"))
			apiResp = reqFinal.when().delete(rsrceUrl.getResourceUrl());
	}

	@Then("the API is executed successfully with status code {int}")
	public void the_api_is_executed_successfully_with_status_code(int statusCode) throws Throwable {

		apiResp.then().spec(responseSpecification()).extract().response();
		System.out.println(apiResp);
		assertEquals(apiResp.getStatusCode(), statusCode);

	}

	@And("verify if the {string} in response body is displayed as {string}")
	public void verify_if_the_something_in_response_body_is_displayed_as_something(String keyValue,
			String expectedValue) throws Throwable {
		String actualValue = getJsonPath(keyValue, apiResp);

		assertEquals(expectedValue, actualValue);
	}

	@And("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_placeid_created_maps_to_something_using_something(String expectedName, String resourceUrl)
			throws Throwable {
		//get the place id value from POST API response 
		place_id = getJsonPath("place_id", apiResp);
		
		//create request specification for GET API
		reqFinal = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		//run the GET API
		user_calls_something_with_something_http_request(resourceUrl, "GET");
		
		//retrieve name value from GET response
		String actualName = getJsonPath("name", apiResp);
		
		//assert name value got in POST response and name value got in GET response
		assertEquals(expectedName, actualName);
		}
	
	
	 @Given("Delete Place payload")
	    public void delete_place_payload() throws Throwable {
		 reqFinal= given().spec(requestSpecification()).body(dataObj.DeletePlacePayload(place_id));
	       
	    }
}