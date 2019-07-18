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

import com.abc.cricket.model.Match;
import com.abc.cricket.model.view.MatchCreateRequest;
import com.abc.cricket.service.MatchService;
import com.abc.cricket.service.TeamService;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
public class MatchController {
	
	@Autowired
	private MatchService service;

	@Autowired 
	private TeamService teamService;
	
	@RequestMapping("/matches")
	public List<Match> getMatches() {
		return service.getMatches();
	}
	
	@RequestMapping("/matches/{id}")
	public Match getMatch(@PathVariable int id) {
		return service.getMatch(id);
	}
	
	@RequestMapping(value="/matches", method=RequestMethod.POST)
	public int createMatch(@RequestBody MatchCreateRequest req) {
		Match m = req.convert(teamService::getTeam);
		
		Match mCreated = service.create(m);
		return mCreated.getId();
	}
	
	
}
