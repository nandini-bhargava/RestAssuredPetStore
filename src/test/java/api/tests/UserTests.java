package api.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker fake;
	User userpayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		//Data
		fake = new Faker();
		userpayload = new User();
		userpayload.setId(fake.idNumber().hashCode());
		userpayload.setUsername(fake.name().username());
		userpayload.setFirstName(fake.name().firstName());
		userpayload.setLastName(fake.name().lastName());
		userpayload.setEmail(fake.internet().emailAddress());
		userpayload.setPassword(fake.internet().password(5, 10));
		userpayload.setPhone(fake.phoneNumber().cellPhone());
		
		//logs
		logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("********Creating User******");
		Response response = UserEndpoints.createUser(userpayload);
		System.out.println("create user");
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********User is created ******");
	}
	
	@Test(priority=2)
	public 	void testGetUser() {
		
		logger.info("********Reading User Info ******");
		Response response= UserEndpoints.readUser(userpayload.getUsername());
				response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
		logger.info("*******User info is displayed ******");
	}
	

	@Test(priority=3)
	public void testUpdateUser() {
		
		//Update data
		
		userpayload.setFirstName(fake.name().firstName());
		userpayload.setLastName(fake.name().lastName());
		userpayload.setEmail(fake.internet().emailAddress());
		
		logger.info("********Updating  User Info ******");
		Response response=UserEndpoints.updateUser(userpayload, this.userpayload.getUsername());
		System.out.println("update user");
		response.then().log().all();
		
		logger.info("********User is Updated ******");
	}
	@Test(priority=4)
	public void testDeleteUser() {
		
		logger.info("********Deleting User ******");
		Response response = UserEndpoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		logger.info("********User is deleted ******");
	}
}
