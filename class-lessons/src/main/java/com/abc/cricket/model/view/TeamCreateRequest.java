/**
 * 
 */
package com.abc.cricket.model.view;

import com.abc.cricket.model.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;

/**
 * @author Soham Chakravarti
 *
 */
@ToString
public class TeamCreateRequest {

	@JsonIgnore
	private Team team = new Team();
	
	public String getName() {
		return team.getName();
	}
	public void setName(String name) {
		team.setName(name);
	}
	
	public Team convert() {
		return team;
	}
}
