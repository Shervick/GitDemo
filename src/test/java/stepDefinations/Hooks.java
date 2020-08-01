package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeSenario() throws IOException {
		StepDefination placid =new StepDefination();
		if(StepDefination.place_id==null)
		{
		placid.add_place_payload(55, "35, side layout, cohen 12", "French-IN", -44.383494, 42.427362, "Houseline house", "(+91) 983 893 3944", "Rona park", "Cun", "http://google.com");
		placid.user_calls_with_post_http_request("AddPlaceAPI", "POST");
		placid.verify_placeid_created_maps_to_something_using_something("Houseline house", "getPlaceAPI");
		}
	}

}
