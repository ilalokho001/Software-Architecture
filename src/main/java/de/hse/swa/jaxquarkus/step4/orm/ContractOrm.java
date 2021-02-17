package de.hse.swa.jaxquarkus.step4.orm;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hse.swa.jaxquarkus.step4.model.Contract;
import de.hse.swa.jaxquarkus.step4.model.Customer;
import de.hse.swa.jaxquarkus.step4.model.User;

@ApplicationScoped
public class ContractOrm {
	@Inject
	EntityManager em;
	
	@Inject
	UserOrm userOrm;
	
	@Inject
	CustomerOrm customerOrm;
    
    public List<Contract> getContracts() {
    	TypedQuery<Contract> query = em.createQuery("SELECT u FROM Contract u", Contract.class);
    	List<Contract> results = query.getResultList();
    	return results;
   }
   
   public Contract getContract(Long id) {
  	 	return em.find(Contract.class, id);
   }
   
   public List<Contract> getContractByCustomer(Long customerId) {
	   System.out.println("ContractOrm/getContractByCustomer");
<<<<<<< HEAD
	   TypedQuery<Contract> query = em.createQuery("SELECT u FROM Contract u WHERE customer_Id =: value", Contract.class);
=======
	   TypedQuery<Contract> query = em.createQuery("SELECT u FROM Contract u WHERE customerId =: value", Contract.class);
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
	   query.setParameter("value", customerId);
	   List<Contract> results = query.getResultList();
	   return results;
   }
   
   public List<Contract> getContractsByUser(Long userIdC) {
<<<<<<< HEAD
	   System.out.println("UserOrm/getContractsByUser");
=======
	   System.out.println("ContractOrm/getContractsByUser");
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
	   return userOrm.getUserById(userIdC).get(0).getContracts();
   }
   
   public List<Contract> getContractByLicenseKey(String licenseKey) {
		System.out.println("ContractOrm/getContractByLicensekey");
		TypedQuery<Contract> query = em.createQuery("SELECT u FROM Contract u WHERE u.licenseKey =: value", Contract.class);
		query.setParameter("value", licenseKey);
		List<Contract> results = query.getResultList();
		return results;
	}
   
   @Transactional 
   public String addContract(Contract contract, Long customerId) {
<<<<<<< HEAD
	 //  UUID key = UUID.randomUUID();
	  // contract.setLicenseKey();
	   Customer customer = customerOrm.getCustomer(customerId);
	   customer.getContracts().add(contract);
	   System.out.println(customerId);
	   System.out.println(contract);
	   contract.setCustomerC(customer);
=======
	   UUID key = UUID.randomUUID();
	   contract.setLicenseKey("License Key: " + key);
	   Customer customer = customerOrm.getCustomer(customerId);
	   customer.getContracts().add(contract);
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
	   em.persist(contract);
	   em.merge(customer);
	   return "" + getContractByLicenseKey(contract.getLicenseKey()).get(0).getId();
   } 
   
   @Transactional
   public Boolean updateContract(Contract contract) {
	   em.merge(contract);
	   return true;
   }
   
   @Transactional
   public Boolean addConnectionToUserContract(Long userId, Long contractId) {
	   System.out.println("ContractOrm/addConnectionToUserContract");
	   Contract contract = getContract(contractId);
	   List<User> user = userOrm.getUserById(userId);
	   user.get(0).getContracts().add(contract);
	   contract.getUsers().add(user.get(0));
	   updateContract(contract);
	   userOrm.updateUser(user.get(0));
	   return true;
   }
   
   @Transactional
	public Boolean removeConnectionToUserContract(Long contractId, Long userId) {
<<<<<<< HEAD
		return	em.createQuery("DELETE FROM user_contract WHERE UserId =: value1 AND ContractId =: value2")
=======
		return	em.createQuery("DELETE FROM user_contract WHERE userId =: value1 AND contractId =: value2")
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
		.setParameter("value1", userId)
		.setParameter("value2", contractId)
		.executeUpdate() == 1;
	}

	@Transactional
	public Boolean removeAllConnectionsUserContract(Long userId){
<<<<<<< HEAD
		return em.createQuery("DELETE FROM user_contract WHERE UserId =: value1")
=======
		return em.createQuery("DELETE FROM user_contract WHERE userId =: value1")
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
		.setParameter("value1", userId)
		.executeUpdate() == 1;
	}

	@Transactional
	public Boolean removeAllConnectionsContractUser(Long contractId){
<<<<<<< HEAD
		return	em.createQuery("DELETE FROM user_contract WHERE ContractId =: value1")
=======
		return	em.createQuery("DELETE FROM user_contract WHERE contractId =: value1")
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
		.setParameter("value1", contractId)
		.executeUpdate() == 1;
	}
	
   @Transactional
   public String removeContract(Contract contract) {
	   System.out.println("ContractOrm/removeContract");
	   em.createQuery("DELETE FROM Contract WHERE id =: value")
	   .setParameter("value", contract.getId())
	   .executeUpdate();
	   return "Contract has been deleted.";
   }
 
<<<<<<< HEAD
}
=======
}
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
