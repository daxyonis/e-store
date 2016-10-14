package com.emaciejko.domain;

import javax.persistence.Embeddable;

/**
 * @author Eva
 *
 */
@Embeddable	
// Specifies a class whose instances are stored as an intrinsic part of an owning entity 
// and share the identity of the entity. Each of the persistent properties or fields of 
// the embedded object is mapped to the database table for the entity.
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private Province province;
    private String postalCode;
    
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
    public Province getProvince() {
        return province;
    }
    public void setProvince(Province province) {
        this.province = province;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    
}
