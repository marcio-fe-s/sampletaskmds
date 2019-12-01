package com.arvoia.sampletask.model;

import java.util.ArrayList;
import java.util.List;

public class Offer {
	private Integer available;
	private List<Vehicle> vehicles = new ArrayList<>();

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
