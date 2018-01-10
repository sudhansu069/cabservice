package com.target.cabservice;



public class DropLocationDistanceValueDataPair {

	 private String dropLoacation;
	 private String  distance;
	 
	 public DropLocationDistanceValueDataPair(String dropLoacation,String distance) {
		 
		 
		 this.dropLoacation = dropLoacation;
		 this.distance = distance;
	 }

	public String getDropLoacation() {
		return dropLoacation;
	}

	public void setDropLoacation(String dropLoacation) {
		this.dropLoacation = dropLoacation;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	 
	 
}
