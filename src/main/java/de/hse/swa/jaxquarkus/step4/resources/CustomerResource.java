package de.hse.swa.jaxquarkus.step4.resources;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.orm.ContractOrm;
import de.hse.swa.jaxquarkus.step4.orm.CustomerOrm;
import de.hse.swa.jaxquarkus.step4.orm.UserOrm;
import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("/customer")

public class CustomerResource {
	@ApplicationScoped
	
	@Inject
	CustomerOrm customerOrm;
	
	@Inject
	UserOrm userOrm;
	
	@Inject
	ContractOrm contractOrm;
	
	 @Context
	 HttpServerRequest request;

	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<Customer> getCustomers(@QueryParam("userId") Long userId, @QueryParam("contractId") Long contractId) {
	     List<Customer> customers = new ArrayList<>();
	     System.out.println("CustomerResource/getCustomers");
	     if(userId != null) {
	    	 System.out.println("CustomerResource/getUserById");
	    	 customers.add(userOrm.getUserById(userId).get(0).customerU());
	    	 return customers;
	     }
	     else if(contractId != null) {
	    	 System.out.println("CustomerResource/getContract");
	    	 customers.add(contractOrm.getContract(contractId).getCustomerC());
	    	 return customers;
	     }
	     else {
	    	 return customerOrm.getCustomers();
	     }
	 }
	 
	 @GET
	 @Path("{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Customer getCustomer(@PathParam("id") Long id) {
		 System.out.println("CustomerResource/getCustomer");
		 return customerOrm.getCustomer(id);
	 }
	 
	 @PUT
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String addCustomer(Customer customer) {
	     System.out.println("CustomerResource/addCustomer");
		 return customerOrm.addCustomer(customer);
	 } 
	 
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void updateCustomer(Customer customer) {
	     System.out.println("CustomerResource/updateCustomer");
		 customerOrm.updateCustomer(customer);
	 }
	 
	 @DELETE
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void removeCustomer(Customer customer) {
		 System.out.println("CustomerResource/removeCustomer");
		 customerOrm.removeCustomer(customer);
	 }
}