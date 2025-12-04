package entity;

import jakarta.persistence.*;

@Entity
@Table(name= "BANK")
public class Bank {
    
@Id
@Column(name="BANKID")
private Long bankId; //PK
 
@Column(name="NAME")
private String name;

@Column(name="LOCATION")
private String location;
 

//getters and setters
//Bank ID
public Long getBankID(){return bankId;}
public void setBankID(Long bankId) {this.bankId = bankId;}

// Bank Name
public String getBankName(){return name;}
public void setBankName(String name) {this.name = name;}

//Bank Location
public String getBankLoaction(){return location;}
public void setBankLoaction(String location) {this.location = location;}

}
