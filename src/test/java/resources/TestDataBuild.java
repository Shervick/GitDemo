package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestDataBuild {
	
	public AddPlace addPlacepayload(int Accuracy,String Address, String Language, double setLat, double setLng,String Name,String Phone_number, String types1, String types2, String Website)
	{
		AddPlace pla=new AddPlace();
		pla.setAccuracy(Accuracy);
		pla.setAddress(Address);
		pla.setLanguage(Language);
		location loc=new location();
		loc.setLat(setLat);
		loc.setLng(setLng);
		pla.setLocation(loc);
		pla.setName(Name);
		pla.setPhone_number(Phone_number);
		List<String> mylist = new ArrayList<String>();
		mylist.add(types1);
		mylist.add(types2);
		pla.setTypes(mylist);
		pla.setWebsite(Website);
		return pla;
	}
	
	public String deletePlacepayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"   \t \r\n}";
		
	}

}
