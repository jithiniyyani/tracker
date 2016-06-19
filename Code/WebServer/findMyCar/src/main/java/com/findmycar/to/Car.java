package com.findmycar.to;

import java.util.List;

public class Car {

	// business values
	private String registrationNo;

	private String manufacturer;

	private String model;

	private String color;

	private List<String> images;

	private boolean stolen;
	// technical values
	private String carId;

	public Car() {
		
	}

	public Car(String registrationNo, String manufacturer, String model,
			String color, List<String> images, String carId, boolean stolen) {
		this.registrationNo = registrationNo;
		this.manufacturer = manufacturer;
		this.model = model;
		this.color = color;
		this.images = images;
		this.carId = carId;
		this.stolen = stolen;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public boolean isStolen() {
		return stolen;
	}

	public void setStolen(boolean stolen) {
		this.stolen = stolen;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	
}
