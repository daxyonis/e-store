package com.emaciejko.domain;

import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
// Designates a class whose mapping information is applied to the entities that inherit from it. 
// A mapped superclass has no separate table defined for it.
public class AbstractDomain implements DomainObject {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;    
    
    @Version
    private Integer version;
    
    private Date dateCreated;
    private Date lastUpdated;
    
    @Override
    public Integer getId() {
	return id;
    }
    @Override
    public void setId(Integer id) {
	this.id = id;	
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Date getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    
    @PreUpdate
    @PrePersist
    // Define callback method for pre-update and pre-persist events
    public void updateTimeStamps(){
	lastUpdated = new Date();
	if(dateCreated == null){
	    dateCreated = new Date();
	}
    }
    // NOTE : the previous solution for timestamps
    // can also be implemented using @CreationTimestamp and @UpdateTimestamp annotations
    // on the corresponding attributes : see http://www.thoughts-on-java.org/persist-creation-update-timestamps-hibernate/
    
}
