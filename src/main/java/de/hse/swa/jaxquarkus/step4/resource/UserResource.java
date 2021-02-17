package de.hse.swa.jaxquarkus.step4.resources;

import java.util.List;

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


import de.hse.swa.jaxquarkus.step4.model.User;
import de.hse.swa.jaxquarkus.step4.orm.UserOrm;
import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("user")
public class UserResource {

    @Inject
    UserOrm orm;
    
    @Context
    HttpServerRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> getUser(@QueryParam("userId") Long userId, @QueryParam("customerId") Long customerId, @QueryParam("username") String username, @QueryParam("contractIdU") Long contractIdU) {
    	System.out.println("UserResource/getUser");
    	if(userId != null) {
    		System.out.println("getUserById");
    		return orm.getUserById(userId);
    	}
    	else if(username != null) {
    		System.out.println("getUserByUsername");
    		return orm.getUserByUsername(username);
    	}
    	else if(customerId != null) {
    		System.out.println("getUserByCustomer");
    		List<User> users = orm.getUserByCustomer(customerId);
    		System.out.println("Customer usrs: " + users);
    		return users;
    	}
    	else if(contractIdU != null) {
    		System.out.println("getUserByContract");
    		return orm.getUserByContract(contractIdU);
    	}
    	else {
    		System.out.println("getUsers");
    		return orm.getUsers();
    	}
    }

    @PUT
    @Path("{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(User user, @PathParam("customerId") Long customerId) {
        System.out.println("UserResource/addUser");
        return orm.addUser(user, customerId);
    } 
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user) {
    	System.out.println("UserResource/updateUser");
    	System.out.println(user);
    	orm.updateUser(user);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void removeUser(User user) {
    	System.out.println("UserResource/removeUser");
    	orm.removeUser(user);
    }
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String loginUser(User user) {
    	System.out.println("UserResource/loginUser");
    	return orm.login(user);
    }
}
