package com.cc.java.designpatterns;

/**
 * @author cclaudiu
 *
 * <br/><br/>
 * Use the BuilderPattern to help build the BuilderPatternDemo CLass
 * A Convenient Thread-Safe way to build partial or all the properties of the class
 * <br/>
 */

public class BuilderPatternDemo {
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public static class Builder {
		private String firstName;
		private String lastName;
		private String phoneNumber;
		
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public Builder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public BuilderPatternDemo build() {
			return new BuilderPatternDemo(firstName, lastName, phoneNumber);
		}
	}
	
	private BuilderPatternDemo(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
}