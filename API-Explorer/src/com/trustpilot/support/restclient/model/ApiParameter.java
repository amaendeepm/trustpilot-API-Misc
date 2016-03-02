package com.trustpilot.support.restclient.model;

class ApiParameter {
	
	String pName;
	String pValue;
	boolean isRequired;
	public ApiParameter(String name, String value, boolean isRequired) {
		this.pName = name;
		this.pValue = value;
		this.isRequired = isRequired;
	}        	
	
}
