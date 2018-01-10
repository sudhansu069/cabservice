package com.target.cabservice.dto;
/**
 *  Drop Location which can be represented using the Latitude and Longitude of the Member Home Location or the name of the location
 * @author sudhansu
 *
 */
public class Location {

	private String description;

	private double latitude;
	private double longitude;
	
	private double distanceFromSrc;

	public Location(String description) {

		this.description = description;
	 
	}
	

	public Location(double latitude, double longitude,Location srclocation) {

		this.latitude = latitude;
		this.longitude = longitude;
		
		this.distanceFromSrc  = Math.sqrt(Math.abs(srclocation.getLatitude()-this.getLatitude())*Math.abs(srclocation.getLatitude()-this.getLatitude()) + Math.abs(srclocation.getLongitude()-this.getLongitude())*Math.abs(srclocation.getLongitude()-this.getLongitude()));
	}
	
	
	
	

	public double getDistanceFromSrc() {
		return distanceFromSrc;
	}


	public void setDistanceFromSrc(double distanceFromSrc) {
		this.distanceFromSrc = distanceFromSrc;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	

}
