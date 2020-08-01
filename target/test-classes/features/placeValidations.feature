Feature: Validate Place API
@AddPlace @Regression
Scenario Outline: Varify if Add place  is beeging Successfully added using AddPlaceAPI
	Given Add Place Payload <Accuracy> "<Address>" "<Language>" <setLat> <setLng> "<Name>" "<Phone_number>" "<types1>" "<types2>" "<Website>"
	When User calls "AddPlaceAPI" with "Post" http request
	Then The API call is success with status code 200
	And "status" in responce body is "OK"
	And Verify place_Id created maps to "<Name>" using "getPlaceAPI"
Examples:
	|Accuracy |Address                   |Language   |setLat     |setLng    |Name 			  |Phone_number 	   |types1    |types2 |Website 		     |
	|56		  |37, side layout, cohen 14 |French-IN  |-46.383494 |44.427362 |Domeline house   |(+91) 983 893 3946  |Fanw park |Sould  |http://google.com |
	|57		  |38, side layout, cohen 15 |Spanish-IN |-47.383494 |45.427362 |Loomline house   |(+91) 983 893 3947  |Vane park |Sal    |http://google.com |

@DeletePlace @Regression	
Scenario: Verify if Delete Place functionality is working
	
	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "POST" http request 
	Then The API call is success with status code 200
	And "status" in responce body is "OK"
