package de.hse.swa.jaxquarkus.step4.orm;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jaxquarkus.step4.model.Contract;
import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.model.User;



@ApplicationScoped
public class CustomerOrm {
    @Inject
    EntityManager em; 

    @Inject
    ContractOrm contractOrm;
    
    @Inject
    UserOrm userOrm;
    
    /*
    @Transactional 
    public void createCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        em.persist(customer);
    }
    */
    
    public List<Customer> getCustomers() {
    	 TypedQuery<Customer> query = em.createQuery("SELECT u FROM Customer u", Customer.class);
    	 List<Customer> results = query.getResultList();
    	 return results;
    }
    
    public Customer getCustomer(Long id) {
   	 	return em.find(Customer.class, id);
    }
    
 

    @Transactional 
    public String addCustomer(Customer customer) {
    	try {
    		em.persist(customer);
    	}catch(Exception e) {
    		return "" + e;
    	}
    	return "Customer added";
    } 
    
    @Transactional
    public Customer updateCustomer(Customer customer) {
    	em.merge(customer);
    	return customer;
    }

    @Transactional
    public Boolean removeCustomer(Customer customer) {
    	
    	List<User> users = userOrm.getUserByCustomer(customer.getId());
    	if(!users.isEmpty()) {
    		for(User user : users) {
    			userOrm.removeUser(user);
    		}
    	}
    	
    	List<Contract> contracts = contractOrm.getContractByCustomer(customer.getId());
    	if(!contracts.isEmpty()) {
    		for(Contract contract : contracts) {
    			contractOrm.removeContract(contract);
    		}
    	}
    	return em.createQuery("DELETE FROM Customer WHERE id =: value1")
    			.setParameter("value1", customer.getId())
    			.executeUpdate() == 1;
    }
    
    @Transactional
    public void removeAll() {
    	em.createQuery("DELETE FROM User")
    	.executeUpdate();
    	em.createQuery("DELETE FROM Contract")
    	.executeUpdate();
    	em.createQuery("DELETE FROM Customer")
    	.executeUpdate();
    }
}
