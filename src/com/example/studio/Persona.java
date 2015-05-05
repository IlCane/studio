package com.example.studio;

import java.io.Serializable;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@JsonIgnoreProperties(ignoreUnknown=true)
public class Persona implements Serializable{

	
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("first_name")
	private String first_name;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("last_name")
	private String last_name;
	
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}
}
