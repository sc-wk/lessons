/**
 * 
 */
package com.abc.cricket.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.cricket.model.Address;
import com.abc.cricket.model.Match;
import com.abc.cricket.model.Team;

/**
 * @author Soham.Chakravarti
 *
 */
@RestController
public class MatchInfoController {

	@RequestMapping("/matches")
	public List<Match> getMatches() {
		/* creating mock */
		Match m1 = new Match();
		m1.setId(0);
		m1.setWhen(new Date());
		
		Address loc1 = new Address();
		loc1.setCity("Chennai");
		loc1.setCountry("India");
		m1.setLocation(loc1);
		
		Match m2 = new Match();
		m2.setId(1);
		m2.setWhen(new Date());
		
		Team m2team1 = new Team();
		m2team1.setName("New Zealand");
		m2.setTeam1(m2team1);
		
		return Arrays.asList(m1, m2);
	}
	
	@RequestMapping("/matches/{id}")
	public Match getMatch(@PathVariable long id) {
		List<Match> matches = getMatches();
		
		if(matches!=null && matches.size() <= id)
			return null;
		
		Match m = matches.get((int)id);
		return m;
	}
	
}
