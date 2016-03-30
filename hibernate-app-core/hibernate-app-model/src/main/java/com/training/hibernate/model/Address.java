package com.training.hibernate.model;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Embeddable
@Table(name = "ADDRESS")
public class Address implements java.io.Serializable {
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "house_no")
	private int houseNo;

	@Column(name = "barangay")
	private String barangay;
	
	@Column(name = "subdivision")
	private String subdivision;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "zip_code")
	private String zipCode;

	public Address(){};

	public Address(String street, int houseNo, String barangay, String subdivision, String city, String zipCode){
		this.street = street;
		this.houseNo = houseNo;
		this.barangay = barangay;
		this.subdivision = subdivision;
		this.city = city;
		this.zipCode = zipCode;
	}

	public String getStreet(){
		return this.street;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public int getHouseNo(){
		return this.houseNo;
	}

	public void setHouseNo(int houseNo){
		this.houseNo = houseNo;
	}

	public String getBarangay(){
		return this.barangay;
	}

	public void setBarangay(String barangay){
		this.barangay = barangay;
	}
	
	public String getSubdivision(){
		return this.subdivision;
	}

	public void setSubdivision(String subdivision){
		this.subdivision = subdivision;
	}
	
	public String getCity(){
		return this.city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getZipCode(){
		return this.zipCode;
	}

	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}

	public String toString(){
		return this.houseNo + " " + this.street + " " + this.subdivision + " " + this.city; 
	}
}