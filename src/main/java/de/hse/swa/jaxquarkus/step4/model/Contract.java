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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Table(name="CONTRACT")

public class Contract{
	
	@Id
    @SequenceGenerator(name = "contractSeq", sequenceName = "ZSEQ_CONTRACT_ID", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "contractSeq")
	
	@Column(name="id", unique=true)
	private Long id;
	
	@Column(name="company", length = 64, unique = true)
	private String company;
	
	@Column(name="startDate")
	private String startDate;
	@Column(name="endDate")
	private String endDate;
	
	@Column(name="internetProtocol1")
	private String internetProtocol1;
	//@Column(name="internetProtocol2")
	//private String internetProtocol2;
	//@Column(name="internetProtocol3")
	//private String internetProtocol3;
	
	@Column(name="version")
	private Double version;
	
	@Column(name="user1", length = 64)
	private String user1;
	@Column(name="user2", length = 64)
	private String user2;
	
	@Column(name="licenseKey", length = 64)
	private String licenseKey;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "contracts")
    private List<User> users = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_Id", referencedColumnName="id")
    private Customer customerC;
	
	public Contract() {
	}

	//String internetProtocol2
	//String internetProtocol3
	public Contract(String company, String startDate, String endDate, String internetProtocol1, Double version, String user1, String user2, String licenseKey) {
		this.company = company;
		this.startDate = startDate;
		this.endDate = endDate;
		this.internetProtocol1 = internetProtocol1;
		//this.internetProtocol2 = internetProtocol2;
		//this.internetProtocol3 = internetProtocol3;
		this.version = version;
		this.user1 = user1;
		this.user2 = user2;
		this.licenseKey = licenseKey;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String string) {
		this.startDate = string;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getInternetProtocol1() {
		return internetProtocol1;
	}
	
	public void setInternetProtocol1(String internetProtocol1) {
		this.internetProtocol1 = internetProtocol1;
	}
	
	/*
	public String getInternetProtocol2() {
		return internetProtocol2;
	}
	
	public void setInternetProtocol2(String internetProtocol2) {
		this.internetProtocol2 = internetProtocol2;
	}
	
	public String getInternetProtocol3() {
		return internetProtocol3;
	}
	
	public void setInternetProtocol3(String internetProtocol3) {
		this.internetProtocol3 = internetProtocol3;
	}
	*/
	
	public Double getVersion() {
		return version;
	}
	
	public void setVersion(Double version) {
		this.version = version;
	}
	
	public String getUser1() {
		return user1;
	}
	
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	
	public String getUser2() {
		return user2;
	}
	
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	
	public String getLicenseKey() {
		return licenseKey;
	}
	
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	
    @JsonbTransient
    public List<User> getUsers(){
    	return this.users;
    }
    
    public void setUsers(List<User> users) {
		this.users = users;
	}
    
    @JsonbTransient
    public Customer getCustomerC() {
		return customerC;
	}

	public void setCustomerC(Customer customerC) {
		this.customerC = customerC;
	}
}

