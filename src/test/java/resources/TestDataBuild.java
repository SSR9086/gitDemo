package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.placesJSON.AddPlaces;
import pojo.placesJSON.Location;

public class TestDataBuild {
	
	public AddPlaces addPlaceAPIPayload(int accuracy, String name, String phone_number, String address, String website, String language){
		// Construct payload using the serialization
				List<String> myList = new ArrayList<String>();
				myList.add("Shoe");
				myList.add("ShoeList");

				AddPlaces p = new AddPlaces();

				p.setAccuracy(accuracy);
				p.setName(name);
				p.setPhone_number(phone_number);
				p.setAddress(address);
				p.setWebsite(website);
				p.setLanguage(language);
				p.setTypes(myList);

				Location l = new Location();
				l.setLat(-38.383494);
				l.setLng(33.427362);
				p.setLocation(l);
				return p;
	}
		
	public String DeletePlacePayload(String placeId){
		return "{\"place_id\":\""+placeId+"\"}";
	}
}
