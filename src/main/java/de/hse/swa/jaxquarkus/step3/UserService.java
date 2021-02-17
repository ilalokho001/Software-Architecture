package de.hse.swa.jaxquarkus.step3;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

	public static List<User> users = new ArrayList<User>();
	public static Long id = 1L;
	
    public List<User> getUsers() {
        return users;
    }
    
    public List<User> addUser(User user) {
    	user.setId(id++);
    	users.add(user);
        return users;
    }
    
    public User getUser(Long oid) {
        return users.get(oid.intValue());
    }
    
    public List<User> updateUser(User user) {
    	for (int index = 0; index < users.size(); ++index) {
    		if (users.get(index).getId().equals(user.getId())) {
    			users.set(index, user);
    			break;
    		}
    	}
        return users;
    }
    
    public List<User> removeUser(User user) {
    	for (int index = 0; index < users.size(); ++index) {
    		if (users.get(index).getId().equals(user.getId())) {
    			users.remove(index);
    			break;
    		}
    	}
        return users;
    }
    
    public void removeAllUsers() {
        users = new ArrayList<User>();
        id = 1L;
    }

}