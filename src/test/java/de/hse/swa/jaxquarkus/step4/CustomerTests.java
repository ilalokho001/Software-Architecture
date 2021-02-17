package de.hse.swa.jaxquarkus.step4;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import de.hse.swa.jaxquarkus.step4.model.Contract;
import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.model.User;
import de.hse.swa.jaxquarkus.step4.orm.*;

import io.quarkus.test.junit.QuarkusTest;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerTests {
	
	private static Customer testCustomer1 = new Customer("testName1", "testAddress1_1", "testAddress2_1)");
	private static Customer testCustomer2 = new Customer("testName2", "testAddress1_2", "testAddress2_2");
	private static User testUser1 = new User("testEmail1", "testUsername1", "testPassword1", "testfirstName1", "testLastName1", true, "testphoneNumber1_1");
	private static User testUser2 = new User("testEmail2", "testUsername2", "testPassword2", "testFirstName2", "testLastName2", false, "testphoneNumber1_2");
	private static Contract testContract = new Contract("testCompany", "testStartDate", "testEndDate", "testInternetProtocol1", 1.0, "testUser1", "testUser2", "testLicenseKey");
	
	@Inject
	CustomerOrm customerOrm;
	
	

	@Test
	@Order(1)
	public void testAddCustomer() {
		System.out.println("Testing addCustomer");
				
		Response response = given().contentType(MediaType.APPLICATION_JSON).body(testCustomer1).when().put("/customer");
		response.then().statusCode(200)
		.body(is("Customer added"));
		
		response = given().contentType(MediaType.APPLICATION_JSON).body(testCustomer2).when().put("/customer");
		response.then().statusCode(200)
		.body(is("Customer added"));
		
		System.out.println("testAddCustomer finished");
	}
	
	
	
	@Test 
	@Order(2)
	public void testGetCustomers() {
		System.out.println("Testing getCustomers");
		
		Response response =given().contentType(MediaType.APPLICATION_JSON).when().get("/customer");
		response.then().statusCode(200);
		List<Customer> customers = Arrays.asList(response.getBody().as(Customer[].class));
		Assertions.assertEquals(testCustomer1.getName(),  customers.get(0).getName());
		Assertions.assertEquals(testCustomer2.getName(),  customers.get(1).getName());
		testCustomer1.setId(customers.get(0).getId());
		testCustomer2.setId(customers.get(1).getId());
		
		System.out.println("testGetCustomers finished");
	}
	
	@Test
	@Order(3)
	public void testGetCustomer() {
		System.out.println("Testing getCustomer");
		
		Response response = given().pathParam("id", testCustomer1.getId()).contentType(MediaType.APPLICATION_JSON).when().get("/customer/{id}");
		response.then().statusCode(200);
		Customer customer = response.getBody().as(Customer.class);
		Assertions.assertEquals(testCustomer1.getName(), customer.getName());
		
		System.out.println("testGetCustomer finished");
	}
	
	@Test
	@Order(4)
	public void testUpdateCustomer() {
		System.out.println("Testing updateCustomer");
		
		testCustomer1.setAddressA("FlandernstraBe 1, Esslingen, Germany, 73732");
		Response response = given().contentType(MediaType.APPLICATION_JSON).body(testCustomer1).when().post("/customer");
		response.then().statusCode(204);
		response = given().pathParam("id", testCustomer1.getId()).contentType(MediaType.APPLICATION_JSON).when().get("customer/{id}");
		response.then().statusCode(200);
		Customer customer = response.getBody().as(Customer.class);
		Assertions.assertEquals(testCustomer1.getAddressA(), customer.getAddressA());
		
		System.out.println("testUpdateCustomer finished");
	}
	
	@Test
	@Order(5)
	public void testRemoveCustomer() {
		System.out.println("Testing removeCustomer");
		
		given().pathParam("customerId", testCustomer2.getId()).contentType(MediaType.APPLICATION_JSON).body(testUser2).when().put("user/{customerId}").then().statusCode(200);
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("/user");
		response.then().statusCode(200);
		List<User> users = Arrays.asList(response.getBody().as(User[].class));
		Assertions.assertEquals(testUser2.getUsername(), users.get(0).getUsername());
		Assertions.assertEquals(1, users.size());
		testUser2.setId(users.get(0).getId());
		
		given().pathParam("customerId", testCustomer2.getId()).contentType(MediaType.APPLICATION_JSON).body(testUser1).when().put("/user/{customerId}").then().statusCode(200);
		response = given().contentType(MediaType.APPLICATION_JSON).when().get("/user");
		response.then().statusCode(200);
		List<User> moreUsers = Arrays.asList(response.getBody().as(User[].class));
		Assertions.assertEquals(2, moreUsers.size());
		Assertions.assertEquals(testUser2.getUsername(), moreUsers.get(0).getUsername());
		Assertions.assertEquals(testUser1.getUsername(), moreUsers.get(1).getUsername());
		testUser1.setId(moreUsers.get(1).getId());
			
		given().pathParam("customerId", testCustomer2.getId()).contentType(MediaType.APPLICATION_JSON).body(testContract)
		.when().put("contract/{customerId}").then().statusCode(200);
		Response contractRes = given().contentType(MediaType.APPLICATION_JSON).when().get("contract");
		contractRes.then().statusCode(200);
		List<Contract> contracts = Arrays.asList(contractRes.getBody().as(Contract[].class));
		Assertions.assertEquals(testContract.getLicenseKey(), contracts.get(0).getLicenseKey());
		testContract.setId(contracts.get(0).getId());
		
		
		given().queryParam("userId", testUser2.getId()).contentType(MediaType.APPLICATION_JSON).body(testContract)
		.when().post("contract").then().statusCode(200).body(is("true"));
		response = given().contentType(MediaType.APPLICATION_JSON).body(testCustomer1).when().delete("/customer");
		response.then().statusCode(204);
		
		System.out.println("testRemoveCustomer finished");
	}
	
	@Test
	@Order(6)
	public void removeAll(){
		//Delete all
			customerOrm.removeAll();			
	}
}	