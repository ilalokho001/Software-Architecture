package de.hse.swa.jaxquarkus.step3;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.vertx.core.http.HttpServerRequest;

@RequestScoped
@Path("/step3/users")
public class UserResource {

    @Inject
    UserService service;
    
    @Context
    HttpServerRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return service.getUsers();
    }
    
    @GET
    @Path("id")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(Long id) {
        return service.getUser(id);
    }

    /**
     * Update an existing user
     * @param user
     * @return the updated list of users
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> addUser(User user) {
        return service.updateUser(user);
    } 
    
    /**
     * Create a new user
     * @param user
     * @return the new list of users
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> updateUser(User user) {
        return service.addUser(user);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void removeAllUsers() {
       service.removeAllUsers();
    }
}