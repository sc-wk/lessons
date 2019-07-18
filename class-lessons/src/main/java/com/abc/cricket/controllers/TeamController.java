/**
 * 
 */
package com.abc.cricket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.cricket.model.Team;
import com.abc.cricket.service.TeamService;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
public class TeamController {

	@Autowired
	private TeamService service;
	
	@RequestMapping("/teams")
	public List<Team> getTeams() {
		return service.getTeams();
	}
	
	@RequestMapping("/teams/{id}")
	public Team getTeam(@PathVariable int id) {
		return service.getTeam(id);
	}
	
	@RequestMapping(value="/teams", method=RequestMethod.POST)
	public int createTeam(@RequestBody Team t) {
		return service.createTeam(t);
	}
	
}
