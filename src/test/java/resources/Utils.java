package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqCommonValues;
	public static ResponseSpecification resCommonValues;
	
	public RequestSpecification requestSpecification() throws IOException{
		
		if (reqCommonValues==null)
		{
		PrintStream logger=new PrintStream(new FileOutputStream("logs.txt"));
				
				
		reqCommonValues = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseURL"))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).setRelaxedHTTPSValidation()
				.addFilter(RequestLoggingFilter.logRequestTo(logger))
				.addFilter(ResponseLoggingFilter.logResponseTo(logger))
				.setUrlEncodingEnabled(false).build();
		return reqCommonValues;
		}
		return reqCommonValues;
	}
	
	public ResponseSpecification responseSpecification(){
		resCommonValues= new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		return resCommonValues;
	} 
	
	public String getGlobalProperties(String Key) throws IOException{
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream("C:\\Automation\\Workspace\\RestAssured\\AutomationFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		System.out.println( prop.getProperty(Key));
		return prop.getProperty(Key);
				
	}
	
	public String getJsonPath(String key, Response response){
		String respAsString=response.asString();
		JsonPath js= new JsonPath(respAsString);
		return js.get(key);
	}
}
