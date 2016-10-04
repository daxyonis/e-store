package com.emaciejko.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements DomainObject{
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNb;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;
    
    private Customer(){}
    
    public Customer(String firstName){
	this.firstName = firstName;
    }
      
    public Customer(String firstName, String lastName, String email,
	    String phone, String city, String province){
	
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNb = phone;
	this.city = city;
	this.province = province;	
    }
    
    @Override
    public Long getId() {
	return id;
    }
    @Override
    public void setId(Long id) {
	this.id = id;	
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
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
	return (firstName + " " + lastName + ", " +  phoneNb + ", " + email + ", " + city + ", " + province);
    }
    
    
    

}