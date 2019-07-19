/**
 * 
 */
package com.abc.cricket.model.view;

import com.abc.cricket.model.Stadium;

import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@ToString
public class StadiumCreateRequest {

	private final Stadium stadium = new Stadium();
	
	public String getName() {
		return stadium.getName();
	}
	public void setName(String name) {
		stadium.setName(name);
	}
	
	public String getCity() {
		return stadium.getCity();
	}
	public void setCity(String city) {
		stadium.setCity(city);
	}
	
	public String getCountry() {
		return stadium.getCountry();
	}
	public void setCountry(String country) {
		stadium.setCountry(country);
	}
	
	public Stadium convert() {
		return stadium;
	}
}
