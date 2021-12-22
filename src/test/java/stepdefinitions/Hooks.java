package stepdefinitions;

import io.cucumber.java.Before;


public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeDeletePlaceScenario() throws Throwable{
		PlaceAPI m=new PlaceAPI();
		if (PlaceAPI.place_id==null){
			
		m.add_place_payload_something_something_something_something_something_something(20, "shreyas", "9657632145", "CA street 30", "yahoo.com", "English-En");
		m.user_calls_something_with_something_http_request("AddPlaceAPIResource", "POST");
		m.verify_placeid_created_maps_to_something_using_something("shreyas", "GetPlaceAPIResource");
		}
	}

}
