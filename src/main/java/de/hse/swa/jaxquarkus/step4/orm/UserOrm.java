package de.hse.swa.jaxquarkus.step4.orm;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.model.User;



@ApplicationScoped
public class UserOrm {
    @Inject
    EntityManager em; 
    @Inject
    ContractOrm contractOrm;
    
    public List<User> getUsers() {
    	System.out.println("UserOrm/getUsers");
    	 TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
    	 List<User> results = query.getResultList();
    	 return results;
    }
    
    public List<User> getUserById(Long id) {
<<<<<<< HEAD
    	System.out.println("UserOrm/getUsersById");
    	TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE id =: value", User.class);
    	query.setParameter("value", id);
=======
    	System.out.println("UserOrm/getUsers");
    	TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
    	
    	List<User> results = query.getResultList();
    	return results;
    }
    
    public List<User> getUserByUsername(String username) {
    	System.out.println("UserOrm/getUserByUsername");
    	TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE username =: value", User.class);
    	
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
    	List<User> results = query.getResultList();
    	return results;
    }
    
<<<<<<< HEAD
    public List<User> getUserByUsername(String username) {
    	System.out.println("UserOrm/getUserByUsername");
    	TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE username =: value", User.class);
    	query.setParameter("value", username);
    	List<User> results = query.getResultList();
    	return results;
    }
    
=======
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
	public List<User> getUserByCustomer(Long customerId) {
		System.out.println("UserOrm/getUserByCustomer");
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE customerId =: value1", User.class);
		query.setParameter("value1", customerId);
		System.out.println("UserOrm/getUserByCompany/postQuery");
<<<<<<< HEAD

		List<User> results = query.getResultList();
		return results;
	}

=======

		List<User> results = query.getResultList();
		return results;
	}

>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
	public List<User> getUserByContract(Long contractId) {
		System.out.println("UserOrm/getUserByContract");
		return contractOrm.getContract(contractId).getUsers();
	}
	
    @Transactional 
    public String addUser(User user, Long customerId) {
    	TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username =: value1 OR u.email =: value2", User.class);
    	query.setParameter("value1", user.getUsername());
    	query.setParameter("value2",  user.getEmail());
    	
    	if (!query.getResultList().isEmpty()) {
    		return "User already exists";
    	}
    	
    	Customer customer = em.find(Customer.class, customerId);
    	customer.getUsers().add(user);
    	user.setCustomerU(customer);
    	em.persist(user);
    	em.merge(customer);
    	
    	return "" + getUserByUsername(user.getUsername()).get(0).getId();
    }
    
    @Transactional
    public void updateUser(User user) {
    	System.out.println("UserOrm/updateUser");
    	System.out.println(user);
    	
    	em.merge(user);
    	em.createQuery("UPDATE User SET isAdmin =: value1 WHERE id =: value2")
    			.setParameter("value1", user.isAdmin())
    			.setParameter("value2", user.getId())
    			.executeUpdate();
    }

    @Transactional
    public String removeUser(User user) {
    	System.out.println("UserOrm/removeUser");
    	em.createQuery("DELETE FROM User WHERE id =: value").setParameter("value", user.getId()).executeUpdate();
    	return "User has been removed";
    }
 
    /*
    @Transactional
    public void removeAllUsers() {
    	try {
    	    Query del = em.createQuery("DELETE FROM User WHERE id >= 0");
    	    del.executeUpdate();
    	} catch (SecurityException | IllegalStateException  e) {
    	    e.printStackTrace();
    	}
    	return;
    }
    */
    
    public String login(User user) {
    	String queryString = "SELECT u FROM User AS u WHERE u.username = :uname and u.password = :passwd";
    	TypedQuery<User> checkCredentials = em.createQuery(queryString, User.class);
    	checkCredentials.setParameter("uname", user.getUsername());
    	checkCredentials.setParameter("passwd", user.getPassword());
    	
    	if (checkCredentials.getResultList().isEmpty()) {
    		return "Username not found.";
    	}
    	
    	if(checkCredentials.getSingleResult().getPassword().equals(user.getPassword())) {
    		return "Password Correct";
    	}
    	else {
    		return "Password is incorrect";
    	}
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
