package de.hse.swa.jaxquarkus.step4;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.model.User;
import de.hse.swa.jaxquarkus.step4.orm.CustomerOrm;
import de.hse.swa.jaxquarkus.step4.orm.UserOrm;

import io.quarkus.test.junit.QuarkusTest;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserTests {
	
	@Inject
    UserOrm userOrm;
    @Inject 
    CustomerOrm customerOrm;
    
    private static User testUser = new User("testEmail", "testUsername", "testPassword", "testFirstName", "testLastName", true, "testPhoneNumber1");
    private static Customer testCustomer = new Customer("testName", "testAddress1", "testAddress2");
    
	@Test
	@Order(1)
	public void testAddUser() {
		System.out.println("Testing addUser");
		
		given().contentType(MediaType.APPLICATION_JSON).body(testCustomer).when().put("/customer").then().statusCode(200)
			.body(is("Customer added"));
		
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("/customer");
		response.then().statusCode(200);
		
		List<Customer> customers = Arrays.asList(response.getBody().as(Customer[].class));
		Assertions.assertEquals( 1, customers.size());
		Assertions.assertEquals( testCustomer.getName(), customers.get(0).getName());
		testCustomer.setId(customers.get(0).getId());
		
		given().pathParam("customerId", testCustomer.getId()).contentType(MediaType.APPLICATION_JSON).body(testUser)
		.when().put("/user/{customerId}").then().statusCode(200);
		
		response = given().contentType(MediaType.APPLICATION_JSON).when().get("/user");
		response.then().statusCode(200);
		
		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		Assertions.assertEquals( 1, users.size());
		Assertions.assertEquals( testUser.getUsername(), users.get(0).getUsername());
		testUser.setId(users.get(0).getId());
		
		System.out.println("testAddUser finished");
	}
	
	@Test
	@Order(2)
	public void testGetAllUsers() {
		System.out.println("Testing getAllUsers");	
<<<<<<< HEAD
=======

>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("/user");
		response.then().statusCode(200);

		List<User> usrs = Arrays.asList(response.getBody().as(User[].class));
		Assertions.assertEquals(testUser.getLastName(), usrs.get(0).getLastName());
		Assertions.assertEquals(1, usrs.size());
<<<<<<< HEAD
=======
		
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
		System.out.println("testGetAllUsers finished");
	}
	
//	@Test
//	@Order(3)
//	public void testGetUsers() {
//		System.out.println("Testing getUsers");	
//		Response response = given().contentType(MediaType.APPLICATION_JSON)
//		.when().get("/user");
//		response.then().statusCode(200);
//		List<User> users = Arrays.asList(response.getBody().as(User[].class));
//		//Assertions.assertEquals(testUser.getFirstName(), users.get(0).getFirstName());
//		Assertions.assertEquals(testUser.getLastName(), users.get(0).getLastName());
//		System.out.println("testGetUsers finished");
//	}
//	
//	@Test
//	@Order(4)
//	public void testGetUser() {
//		System.out.println("Testing getUser");
//		
//		Response response = given().queryParam("customerId", testCustomer.getId()).contentType(MediaType.APPLICATION_JSON)
//		.when().get("/user");
//		response.then().statusCode(200);
//		System.out.println("UserResource/getUser/backToTest");
//		
//		List<User> user = Arrays.asList(response.getBody().as(User[].class));
//		//Assertions.assertEquals(testUser.getFirstName(), user.get(0).getFirstName());
//		Assertions.assertEquals(testUser.getLastName(), user.get(0).getLastName());
//		
//		System.out.println("testGetUser finished");
//	}

	@Test
	@Order(3)
	public void testUpdateUser() {
		System.out.println("Testing UpdateUser");
		
		testUser.setLastName("michael");
		Response response = given().contentType(MediaType.APPLICATION_JSON).body(testUser).when().post("/user");
		response.then().statusCode(204);
		response = given().queryParam("customerId", testCustomer.getId()).contentType(MediaType.APPLICATION_JSON).when()
			.get("user");
		response.then().statusCode(200);
		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		Assertions.assertEquals(testUser.getLastName(), users.get(0).getLastName());
		
		System.out.println("testUpdateUser finished");
	}
	
	@Test
	@Order(4)
	public void testRemoveUser() {
		System.out.println("Testing removeUser");
		
		Response response = given().contentType(MediaType.APPLICATION_JSON).body(testUser).when().delete("/user");
		response.then().statusCode(204);

		response = given().contentType(MediaType.APPLICATION_JSON).when().get("/user");
		response.then().statusCode(200);

		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		Assertions.assertEquals(0, users.size());
		
		System.out.println("testRemoveUser finished");
	}
	
	@Test
	@Order(5)
	public void testRemoveall() {
		// Delete all
		customerOrm.removeAll();
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
