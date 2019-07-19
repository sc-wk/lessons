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

import com.abc.cricket.model.Player;
import com.abc.cricket.model.Team;
import com.abc.cricket.model.view.PlayerCreateRequest;
import com.abc.cricket.model.view.TeamCreateRequest;
import com.abc.cricket.service.TeamService;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamService service;
	
	@RequestMapping
	public List<Team> getTeams() {
		return service.findAll();
	}
	
	@RequestMapping("/{id}")
	public Team getTeam(@PathVariable int id) {
		return service.find(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public int createTeam(@RequestBody TeamCreateRequest req) {
		Team t = req.convert();
		
		Team tCreated = service.create(t);
		return tCreated.getId();
	}
	
	@RequestMapping(value="/{id}/players", method=RequestMethod.PUT)
	public void addPlayer(@PathVariable int id, @RequestBody PlayerCreateRequest req) {
		Player p = req.convert();
		
		service.addPlayers(id, p);
	}
	
	@RequestMapping(value="/{id}/players/{playerId}", method=RequestMethod.DELETE)
	public Player removePlayer(@PathVariable int id, @PathVariable int playerId) {
		return service.removePlayer(id, playerId);
	}
}
