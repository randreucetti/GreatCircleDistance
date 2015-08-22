package com.randreucetti.model;

public class Customer implements Comparable<Customer> {
	private Integer user_id;
	private String name;
	private double latitude;
	private double longitude;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public String toString() {
		return "Customer [user_id=" + user_id + ", name=" + name + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

	@Override
	public int compareTo(Customer o) {
		if (user_id == null) {
			return 1;
		} else if (o.getUser_id() == null) {
			return -1;
		} else {
			return user_id.compareTo(o.getUser_id());
		}
	}
}
