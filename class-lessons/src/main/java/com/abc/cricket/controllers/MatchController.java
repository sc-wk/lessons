/**
 * 
 */
package com.abc.cricket.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.cricket.model.Match;
import com.abc.cricket.model.view.MatchCreateRequest;
import com.abc.cricket.service.MatchService;
import com.abc.cricket.service.StadiumService;
import com.abc.cricket.service.TeamService;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
@RequestMapping("/matches")
public class MatchController {
	
	@Autowired
	private MatchService service;

	@Autowired 
	private TeamService teamService;
	
	@Autowired
	private StadiumService stadiumService;
	
	
	@RequestMapping
	public List<Match> getMatches() {
		return service.findAll();
	}
	
	@RequestMapping("/{id}")
	public Match getMatch(@PathVariable int id) {
		return service.find(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public int createMatch(@Valid @RequestBody MatchCreateRequest req) {
		Match m = req.convert(teamService::get, stadiumService::get);
		
		Match mCreated = service.create(m);
		return mCreated.getId();
	}
	
	
}
