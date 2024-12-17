package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	
	@Test(priority=1, dataProvider="Data",
			dataProviderClass=Dataproviders.class)
	public void testmultiplePOSTuser(String userID, 
			String UserName, String FirstName, 
			String LastName, String email, 
			String password, String phone) {
		
		User userpayload = new User();
		System.out.println(userID+UserName+FirstName);
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);
		
		Response response= UserEndpoints.createUser(userpayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	response.then().log().all();
	
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass = Dataproviders.class)
	public void testgetUser(String username) {
		
		Response response = UserEndpoints.readUser(username);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		AssertJUnit.assertEquals(response.body().jsonPath().getString("username"), username, "Username mismatch!");
	    
		response.then().log().all();
		
	}
	
	@Test(priority=3, dataProvider = "UserNames",dataProviderClass = Dataproviders.class)
	public void testDeleteUser(String username) {
		
		
		Response response= UserEndpoints.deleteUser(username);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
}
