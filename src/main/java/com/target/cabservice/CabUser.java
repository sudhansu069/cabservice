package com.target.cabservice;
/**
 * CabUser - > Represents the Member registered for Can Services
 * @author sudhansu
 *
 */
public class CabUser {

	private int Id;

	private String gender;

	private String dropPoint;

	public CabUser(int id, String gender, String dropPoint) {
		super();
		Id = id;
		this.gender = gender;
		this.dropPoint = dropPoint;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDropPoint() {
		return dropPoint;
	}

	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}

}
