package de.hse.swa.jaxquarkus.step4.model;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")

public class Customer {
	@Id
    @SequenceGenerator(name = "customerSeq", sequenceName = "ZSEQ_CUSTOMER_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "customerSeq")

	@Column(name="id", unique=true)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="addressA")
	private String addressA;

	@Column(name="addressB")
	private String addressB;
	
	@OneToMany(mappedBy="customerU", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();
	
	@OneToMany(mappedBy="customerC", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	private List<Contract> contracts = new ArrayList<>();

	
	public Customer() {
	}
	
	public Customer(String name, String addressA, String addressB) {
		this.name = name;
		this.addressA = addressA;
		this.addressB = addressB;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddressA() {
		return addressA;
	}
	
	public void setAddressA(String addressA) {
		this.addressA = addressA;
	}
	
	public String getAddressB() {
		return addressB;
	}
	
	public void setAddressB(String addressB) {
		this.addressB = addressB;
	}
	
	@JsonbTransient
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@JsonbTransient
	public List<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> ab9a476d1286be1e5f5e7fad60101ebb057e292d
