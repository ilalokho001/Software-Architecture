package de.hse.swa.jaxquarkus.step4;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.SequenceGenerator;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import de.hse.swa.jaxquarkus.step4.model.Contract;
import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.model.User;
import de.hse.swa.jaxquarkus.step4.orm.CustomerOrm;

import io.quarkus.test.junit.QuarkusTest;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class ContractTests {
	@Inject 
	CustomerOrm customerOrm;
	@SequenceGenerator(name = "contractSeq", sequenceName = "ZSEQ_CONTRACT_ID", allocationSize = 1, initialValue = 1)
	
	private static Customer testCustomer = new Customer("testName1", "testAddress1_1", "testAddress2_1)");
	private static User testUser = new User("testEmail1", "testUsername1", "testPassword1", "testfirstName1", "testLastName1", true, "testphoneNumber1_1");
	private static Contract testContract = new Contract("testCompany", "testStartDate", "testEndDate", "testInternetProtocol1", 1.0, "testUser1", "testUser2", "testLicenseKey");
	
	@Test
	@Order(1)
	public void testAddContract() {
		System.out.println("Testing addContract");
		
		given().contentType(MediaType.APPLICATION_JSON).body(testCustomer).when().put("/customer").then().statusCode(200).body(is("Customer added"));
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("/customer");
		response.then().statusCode(200);
		List<Customer> customers = Arrays.asList(response.getBody().as(Customer[].class));
		testCustomer.setId(customers.get(0).getId());
		
		 given().pathParam("customerId", testCustomer.getId()).contentType(MediaType.APPLICATION_JSON).body(testContract).when().put("contract/{customerId}").then().statusCode(200).body(is("" + testCustomer.getId())); 
		 Response contractRes = given().contentType(MediaType.APPLICATION_JSON).when().get("contract");
		 contractRes.then().statusCode(200);
		 List<Contract> contracts = Arrays.asList(contractRes.getBody().as(Contract[].class));
		 testContract.setId(contracts.get(0).getId());
		 
		 System.out.println("testAddContract finished");
	}
	
	@Test
	@Order(2)
	public void testAddConnectionToUserContract() {
		System.out.println("Testing addConnectionToUserContract");
		
		given().pathParam("customerId", testCustomer.getId()).contentType(MediaType.APPLICATION_JSON)
		.body(testUser).when().put("/user/{customerId}").then().statusCode(200).body(is("" + testCustomer.getId()));  
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("/user");		      	    	
 	    response.then().statusCode(200);
 	    List<User> users = Arrays.asList(response.getBody().as(User[].class));
 	    testUser.setId(users.get(0).getId());
 	    
 	   given().queryParam("userId", testUser.getId()).contentType(MediaType.APPLICATION_JSON).body(testContract).when().post("contract").then().statusCode(200).body(is("true"));
	
 	   System.out.println("testAddConnectionToUserContract finished");
	}
	
	@Test
	@Order(3)
	public void testGetContracts() {
		System.out.println("Testing getContracts");
		
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("contract");
		response.then().statusCode(200);
		List<Contract> contracts = Arrays.asList(response.getBody().as(Contract[].class));
		Assertions.assertEquals(testContract.getVersion(), contracts.get(0).getVersion());
		Assertions.assertEquals(1, contracts.size());
		testContract.setLicenseKey(contracts.get(0).getLicenseKey());
		
		System.out.println("testGetContracts finished");
	}
	
	@Test
	@Order(4)
	public void testGetContractsByCustomer() {
		System.out.println("Testing getContractsByCustomer");
		
		Response response = given().queryParam("customerId", testCustomer.getId()).contentType(MediaType.APPLICATION_JSON).when().get("contract");
		response.then().statusCode(200);
		List<Contract> contracts = Arrays.asList(response.getBody().as(Contract[].class));
		Assertions.assertEquals(testContract.getLicenseKey(), contracts.get(0).getLicenseKey());
		
		System.out.println("testGetContractsByCutomer finished");
	}
	
	@Test
	@Order(5)
	public void testGetContractByLicenseKey() {
		System.out.println("Testing getContractsByLicenseKey");
		
		Response response = given().queryParam("licenseKey", testContract.getLicenseKey()).contentType(MediaType.APPLICATION_JSON).when().get("contract");
		response.then().statusCode(200);
		List<Contract> contracts = Arrays.asList(response.getBody().as(Contract[].class));
		Assertions.assertEquals(testContract.getLicenseKey(), contracts.get(0).getLicenseKey());
		
		System.out.println("testGetContractsByLicenseKey finished");
	}
	
	@Test
	@Order(6)
	public void testUpdateContract() {
		System.out.println("Testing updateContract");
		
		testContract.setEndDate("01.03.2021");
		given().contentType(MediaType.APPLICATION_JSON).body(testContract).when().post("contract").then().statusCode(200).body(is("true"));
		Response response = given().queryParam("endDate", "01.03.2021").contentType(MediaType.APPLICATION_JSON).when().get("contract");
		response.then().statusCode(200);
		List<Contract> contracts = Arrays.asList(response.getBody().as(Contract[].class));
		Assertions.assertEquals(testContract.getId(), contracts.get(0).getId());;
		Assertions.assertEquals(1, contracts.size());
		
		System.out.println("testUpdateContract finished");
	}
	
	@Test
	@Order(7)
	public void testRemoveContract() {
		System.out.println("Testing removeContract");
		
		given().queryParam("id", testContract.getId()).contentType(MediaType.APPLICATION_JSON).body(testContract)
		.when().delete("contract").then().statusCode(204);
		Response response = given().contentType(MediaType.APPLICATION_JSON).when().get("contract");
		response.then().statusCode(200);
		List<Contract> contracts = Arrays.asList(response.getBody().as(Contract[].class));
		Assertions.assertEquals(0, contracts.size());
	
		System.out.println("testRemoveContract finished");
	}
}