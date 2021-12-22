package resources;
//enum is a special class in JAVA which has collection of constants and methods

public enum APIResources {
	
	AddPlaceAPIResource("maps/api/place/add/json"),
	GetPlaceAPIResource("maps/api/place/get/json"),
	DeletePlaceAPIResource("maps/api/place/delete/json"),
	UpdatePlaceAPIResource("asdasda");
	
	private String resourceUrl;
	
    APIResources(String resourceUrl){
    	this.resourceUrl=resourceUrl;
		
	}
    public String getResourceUrl(){
    	return resourceUrl;
    }
    
}
