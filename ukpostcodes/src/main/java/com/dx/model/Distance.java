package com.dx.model;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

public class Distance implements Serializable{

	private static final long serialVersionUID = -5006397376921705358L;
	private final static double EARTH_RADIUS = 6371; // radius in kilometers
	@Autowired
	PostCode postCodeFrom;
	@Autowired
	PostCode postCodeTo;
	Double distanceInKm;
	String distanceStringInKm;

	public Distance() {}
	
	public void calculateDistance(PostCode code1, PostCode code2) {
		this.postCodeFrom = code1;
		this.postCodeTo = code2;
		this.distanceInKm = (code1.getLatitude()*code2.getLongitude()==0)?0:calculateDistance(code1.getLatitude(), code1.getLongitude(), code2.getLatitude(), code2.getLongitude());
		this.distanceStringInKm = this.distanceInKm + " km";
	}
	
	public PostCode getPostCodeFrom() {
		return postCodeFrom;
	}

	public void setPostCodeFrom(PostCode postCodeFrom) {
		this.postCodeFrom = postCodeFrom;
	}

	public PostCode getPostCodeTo() {
		return postCodeTo;
	}

	public void setPostCodeTo(PostCode postCodeTo) {
		this.postCodeTo = postCodeTo;
	}

	public Double getDistanceInKm() {
		return distanceInKm;
	}

	public void setDistanceInKm(Double distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public String getDistanceStringInKm() {
		return distanceStringInKm;
	}

	public void setDistanceStringInKm(String distanceStringInKm) {
		this.distanceStringInKm = distanceStringInKm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distanceInKm == null) ? 0 : distanceInKm.hashCode());
		result = prime * result + ((distanceStringInKm == null) ? 0 : distanceStringInKm.hashCode());
		result = prime * result + ((postCodeFrom == null) ? 0 : postCodeFrom.hashCode());
		result = prime * result + ((postCodeTo == null) ? 0 : postCodeTo.hashCode());
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
		Distance other = (Distance) obj;
		if (distanceInKm == null) {
			if (other.distanceInKm != null)
				return false;
		} else if (!distanceInKm.equals(other.distanceInKm))
			return false;
		if (distanceStringInKm == null) {
			if (other.distanceStringInKm != null)
				return false;
		} else if (!distanceStringInKm.equals(other.distanceStringInKm))
			return false;
		if (postCodeFrom == null) {
			if (other.postCodeFrom != null)
				return false;
		} else if (!postCodeFrom.equals(other.postCodeFrom))
			return false;
		if (postCodeTo == null) {
			if (other.postCodeTo != null)
				return false;
		} else if (!postCodeTo.equals(other.postCodeTo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Distance [postCodeFrom=" + postCodeFrom + ", postCodeTo=" + postCodeTo + ", distanceInKm="
				+ distanceInKm + ", distanceStringInKm=" + distanceStringInKm + "]";
	}

	private double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
		// Using Haversine formula!
		double lon1Radians = Math.toRadians(longitude);
		double lon2Radians = Math.toRadians(longitude2);
		double lat1Radians = Math.toRadians(latitude);
		double lat2Radians = Math.toRadians(latitude2);
		double a = haversine(lat1Radians, lat2Radians) + Math.cos(lat1Radians) *
				Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (EARTH_RADIUS * c);
	}
	
	private double haversine(double deg1, double deg2) {
		return square(Math.sin((deg1 - deg2) / 2.0));
	}
	
	private double square(double x) {
		return x * x;
	}

}
