package de.hse.swa.jaxquarkus.step3;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
public class Step3Test {

	private static User firstUser = new User(
			"firstUser",
			"firstPasswd",
			"First User",
			true);

	private static User secondUser = new User(
			"secondUser",
			"secondPasswd",
			"Second User",
			true);

	@BeforeEach
	public void clearUserList() {
		Response response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(firstUser)
				.when()
				.delete("/step3/users");
		response.then()
		.statusCode(204);
	}
	
	@Test
	public void testStep3EndpointPost_1() {
		Response response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(firstUser)
				.when()
				.post("/step3/users");
		response.then()
		.statusCode(200);

		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		assert(users != null && users.size() == 1);
		assert(users.get(0).getFullName().equals(firstUser.getFullName()));
	}

	@Test
	public void testStep3EndpointPost_2() {
		Response response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(firstUser)
				.when()
				.post("/step3/users");
		response.then()
		.statusCode(200);
		
		response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(secondUser)
				.when()
				.post("/step3/users");
		response.then()
		.statusCode(200);

		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		assert(users != null && users.size() == 2);
		assert(users.get(1).getFullName().equals(secondUser.getFullName()));
		assert(users.get(1).getId().equals(2L));
	}
	
	@Test
	public void testStep3EndpointPut_1() {

		Response response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(firstUser)
				.when()
				.post("/step3/users");
		response.then()
		.statusCode(200);
		
		response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(secondUser)
				.when()
				.post("/step3/users");
		response.then()
		.statusCode(200);
		
		response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.when()
				.get("/step3/users");
		response.then()
		.statusCode(200);

		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		assert(users != null && users.size() == 2);
		
		User user = users.get(1);
		user.setFullName("John");
		
		response = 
				given()
				.contentType(MediaType.APPLICATION_JSON)
				.body(user)
				.when()
				.put("/step3/users");
		response.then()
		.statusCode(200);
		
		users = Arrays.asList(response.getBody().as(User[].class));
		assert(users != null && users.size() == 2);
		assert(users.get(1).getFullName().equals("John"));
		assert(users.get(1).getId().equals(2L));
	}


}