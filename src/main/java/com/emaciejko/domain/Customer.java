package com.emaciejko.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Customer extends AbstractDomain{
        
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNb;
    
    @Embedded
    private Address billingAddress;
    
    @Embedded
    private Address shippingAddress;
    
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;
    
    public Customer(){}
    
    public Customer(String firstName){
	this.firstName = firstName;
    }
      
    public Customer(String firstName, String lastName, String email,
	    String phone, String city, String province){
	
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNb = phone;
	this.billingAddress = new Address();
	this.billingAddress.setCity(city);
	this.billingAddress.setProvince(Province.valueOf(province));
    }
      
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fistName) {
        this.firstName = fistName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNb() {
        return phoneNb;
    }
    public void setPhoneNb(String phoneNb) {
        this.phoneNb = phoneNb;
    }
    
    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
	return (firstName + " " + lastName + ", " +  phoneNb + ", " + email +
		(billingAddress != null ? (", " + billingAddress.getCity() + ", " + billingAddress.getProvince()) : ""));
    }
    
    
    

}
