#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Validating Place APIs
@AddPlace @Regression
Scenario Outline: Verify if the place is being added successfully using the AddPlaceAPI
    Given Add Place payload "<accuracy>" "<name>" "<phone_number>" "<address>" "<website>" "<language>"
    When user calls "AddPlaceAPIResource" with "POST" http request
    Then the API is executed successfully with status code 200
    And verify if the "status" in response body is displayed as "OK"
    And verify if the "scope" in response body is displayed as "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPIResource"
Examples:
				| accuracy|	name          | phone_number      |address                   |  website         | language |
	    	| 50      |Frontline house| (+91) 983 893 3937|29, side layout, cohen 09 | http://google.com|French-IN |
	#    	|60       |Backline street|(+91) 988 888 9999 |45, main lane, pitstop 10 | https://yahoo.com|English-EN|
	
@DeletePlace @Regression
Scenario: Verify if delete place response using the DeletePlaceAPI
    Given Delete Place payload 
    When user calls "DeletePlaceAPIResource" with "DELETE" http request
    Then the API is executed successfully with status code 200
    And verify if the "status" in response body is displayed as "OK"
    