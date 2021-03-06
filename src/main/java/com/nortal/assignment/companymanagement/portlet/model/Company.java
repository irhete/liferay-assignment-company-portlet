package com.nortal.assignment.companymanagement.portlet.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement
	private long id;

	@XmlElement
	private String name;

	@XmlElement
	private String description;

	@XmlElement
	private int year;

	@XmlTransient
	private Addresses addresses = new Addresses();

	public Company(String name, String description, int year) {
		this.name = name;
		this.description = description;
		this.year = year;
	}

	public Company(long id, String name, String description, int year) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.year = year;
	}

	public Company() {
	}

	@XmlTransient
	public long getId() {
		return id;
	}

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public Addresses getAddresses() {
		return addresses;
	}

	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}

	public void addAddress(Address address) {
		addresses.getAddresses().add(address);
	}

	public Address getAddress(long addressId) {
		int i = 0;
		while (addresses.getAddresses().get(i).getId() != addressId) {
			i++;
		}
		return addresses.getAddresses().get(i);
	}

}
