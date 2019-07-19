/**
 * 
 */
package com.abc.cricket.model.view;

import com.abc.cricket.model.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@ToString
public class PlayerCreateRequest {

	@JsonIgnore
	private Player player = new Player();
	
	public String getFirstName() {
		return player.getFirstName();
	}
	public void setFirstName(String firstName) {
		player.setFirstName(firstName);
	}
	
	public String getLastName() {
		return player.getLastName();
	}
	public void setLastName(String lastName) {
		player.setLastName(lastName);
	}
	
	public Player convert() {
		return player;
	}
}
