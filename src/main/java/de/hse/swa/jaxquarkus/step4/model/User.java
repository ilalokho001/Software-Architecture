package de.hse.swa.jaxquarkus.step4.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "USER")
public class User {
    // public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "ZSEQ_USER_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "userSeq")
    
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

<<<<<<< HEAD
    @Column(name = "phoneNumber")
    private String phoneNumber;
    
 
    @Column(name = "is_admin")
    private boolean isAdmin = false;
    
/*   @Column(name = "belongsToCompany")
   private String belongsToCompany;*/
=======
    @Column(name = "phoneNumber1")
    private String phoneNumber1;
    
    //@Column(name = "phoneNumber2")
    //private String phoneNumber2;
    
    @Column(name = "is_admin")
    private boolean isAdmin = false;
    
    @Column(name = "belongsToCompany")
    private String belongsToCompany;
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
    	name = "user_contract",
    	joinColumns = {@JoinColumn(name = "UserId", referencedColumnName="id")},
    	inverseJoinColumns = {@JoinColumn(name = "ContractId", referencedColumnName="id")}
    )

    private  List<Contract> contracts = new ArrayList<>();
       
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customerId", referencedColumnName="id")
    private Customer customerU;
    
    public User() {
    }

<<<<<<< HEAD
    public User(String email, String username, String password, String firstName, String lastName, boolean isAdmin, String phoneNumber) {
=======
    public User(String email, String username, String password, String firstName, String lastName, boolean isAdmin, String phoneNumber1, String belongsToCompany) {
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
<<<<<<< HEAD
        this.phoneNumber = phoneNumber;
        //this.belongsToCompany = belongsToCompany;
=======
        this.phoneNumber1 = phoneNumber1;
        //this.phoneNumber2 = phoneNumber2;
        this.belongsToCompany = belongsToCompany;
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // @JsonIgnore()
    public String getPassword() {
        return password;
    }

    // @JsonProperty()
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
<<<<<<< HEAD
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber1) {
    	this.phoneNumber = phoneNumber1;
    }
    
=======
    public String getPhoneNumber1() {
    	return phoneNumber1;
    }
    
    public void setPhoneNumber1(String phoneNumber1) {
    	this.phoneNumber1 = phoneNumber1;
    }
    
    /*
    public String getPhoneNumber2() {
    	return phoneNumber2;
    }
    
    public void setPhoneNumber2(String phoneNumber2) {
    	this.phoneNumber2 = phoneNumber2;
    }
    */
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
    
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    
<<<<<<< HEAD
 /*   public String getBelongsToCompany() {
=======
    public String getBelongsToCompany() {
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
    	return belongsToCompany;
    }
    
    public void setBelongsToCompany(String belongsToCompany) {
    	this.belongsToCompany = belongsToCompany;
<<<<<<< HEAD
    }*/
=======
    }
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
	
    @JsonbTransient
    public List<Contract> getContracts() {
    	return this.contracts;
    }
    
    public void setContracts(List<Contract> contracts) {
    	this.contracts = contracts;
    }
    
    @JsonbTransient
    public Customer customerU() {
	return customerU;
    }
    
    public void setCustomerU(Customer customerU) {
	this.customerU = customerU;
    }
}
