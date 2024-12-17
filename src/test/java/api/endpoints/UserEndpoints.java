package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.User;
import api.utilities.PropertiesReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	
	public static Response createUser(User payload){
		
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(PropertiesReader.getProperty("post_url"));
		
		return response;
	}
	
public static Response readUser(String username){
		
		Response response =given()
				.pathParam("username", username)
		.when()
		.get(PropertiesReader.getProperty("get_url"));
		
		return response;
	}

public static Response updateUser(User payload, String username){
	
	Response response =given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", username)
	.body(payload)
	.when()
	.put(PropertiesReader.getProperty("update_url"));
	
	return response;
}

public static Response deleteUser(String username){
	
	Response response =given()
	.pathParam("username", username)
	.when()
	.delete(PropertiesReader.getProperty("delete_url"));
	
	return response;
}
}
