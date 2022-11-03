package com.triton.johnson_tap_app;

import java.io.Serializable;

public class BD_model implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String emailId;

	private boolean isSelected;

	public BD_model() {

	}

	public BD_model(String name, String emailId) {

		this.name = name;
		this.emailId = emailId;

	}

	public BD_model(String name, String emailId, boolean isSelected) {

		this.name = name;
		this.emailId = emailId;
		this.isSelected = isSelected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
