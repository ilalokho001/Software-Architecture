package de.hse.swa.jaxquarkus.step4.resources;

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

import de.hse.swa.jaxquarkus.step4.model.Contract;
import de.hse.swa.jaxquarkus.step4.orm.ContractOrm;
import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("contract")

public class ContractResource {
	
	@ApplicationScoped
	
    @Inject
    ContractOrm orm;
    
    @Context
    HttpServerRequest request;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Contract> getContract(@QueryParam("userId") Long userId, @QueryParam("customerId") Long customerId, @QueryParam("licenseKey") String licenseKey) {
        System.out.println("ContractResource/getContract");
        if(userId != null) {
        	System.out.println("getContractByUser");
        	return orm.getContractsByUser(userId);
        }
        else if (customerId != null) {
        	System.out.println("getContractByCustomer");
        	return orm.getContractByCustomer(customerId);
        }
        else if (licenseKey != null) {
        	System.out.println("getContractByLicenseKey");
        	return orm.getContractByLicenseKey(licenseKey);
        }
        else {
        	System.out.println("getContracts");
        	return orm.getContracts();
        }
    }
    
    @PUT
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addContract(Contract contract, @PathParam("customerId") Long customerId) { 	
        System.out.println("ContractResource/addContract");
        return orm.addContract(contract, customerId);
    } 
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean updateContract(Contract contract, @QueryParam("userId") Long userId) {
        System.out.println("ContractResource/updateContract");
        if(userId == null) {
        	System.out.println("updateContract");
        	return orm.updateContract(contract);
        }
        else {
        	System.out.println("addConnectionToUserContract");
        	return orm.addConnectionToUserContract(userId, contract.getId());
        }
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeContract(Contract contract, @QueryParam("userId") Long userId) {
    	System.out.println("ContractResource/removeContract");
    	orm.removeContract(contract);
    }
}